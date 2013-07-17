/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Kings and Peasants Mod.
 *
 * Kings and Peasants is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 *
 * File Created @ [16 Jul 2013, 22:53:53 (GMT)]
 */
package vazkii.kap.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import vazkii.kap.KingsAndPeasants;
import vazkii.kap.item.ItemHeraldry;
import vazkii.kap.item.ModItems;
import vazkii.kap.tile.TileEntityThrone;
import vazkii.kap.util.handler.KAPCreativeTab;
import vazkii.kap.util.storage.Coordinates;
import vazkii.kap.util.storage.KingdomData;
import vazkii.kap.util.storage.KingdomList;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockThrone extends BlockContainer {

	public BlockThrone(int par1) {
		super(par1, Material.wood);
		setBlockBounds(0F, 0F, 0F, 1F, 2F, 1F);
		setStepSound(soundWoodFootstep);
		setBlockUnbreakable();
		setCreativeTab(KAPCreativeTab.INSTANCE);

		EntityRegistry.registerModEntity(Seat.class, "SEAT", Integer.MAX_VALUE, KingsAndPeasants.instance, Integer.MAX_VALUE, 8, false);
	}

	@Override
	public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack) {
		int l = MathHelper.floor_double(par5EntityLivingBase.rotationYaw * 4D / 360D + 0.5D) & 3;
		par1World.setBlockMetadataWithNotify(par2, par3, par4, l, 2);

		if(par5EntityLivingBase instanceof EntityPlayer && !par1World.isRemote)
			((EntityPlayer) par5EntityLivingBase).addChatMessage("You have placed a throne, to bind it to your kingdom Right click it with a wooden sword in your hand. To remove it Shift-Right click it with an empty hand. " + EnumChatFormatting.RED + "Note: The throne can NOT BE REMOVED after it's bound to the kingdom.");
	}

	@Override
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {
		TileEntityThrone tile = (TileEntityThrone) par1World.getBlockTileEntity(par2, par3, par4);
		ItemStack held = par5EntityPlayer.getCurrentEquippedItem();

		KingdomData data = null;

		for(KingdomData kingdom : KingdomList.kingdoms)
			if(kingdom.owner.equals(par5EntityPlayer.username))
				data = kingdom;

		if(!par1World.isRemote) {
			boolean empty = tile.kingdom.isEmpty();
			if(empty) {
				if(data != null && !data.hasThrone(par1World) && held != null && held.itemID == Item.swordWood.itemID) {
					tile.kingdom = data.name;
					data.throneCoords = new Coordinates(par2, par3, par4);
					par5EntityPlayer.addChatMessage(EnumChatFormatting.BLUE + "Throne successfuly bound to the Kingdom of " + tile.kingdom + ".");
					PacketDispatcher.sendPacketToPlayer(tile.getDescriptionPacket(), (Player) par5EntityPlayer);
					return true;
				}
			}

			if(held == null) {
				if(par5EntityPlayer.isSneaking()) {
					if(empty) {
						par1World.spawnEntityInWorld(new EntityItem(par1World, par2 + 0.5, par3 + 0.5, par4 + 0.5, new ItemStack(ModBlocks.throne)));
						par1World.setBlockToAir(par2, par3, par4);
						return true;
					}
				} else {
					boolean canSit = empty;
					if(!canSit) {
						for(KingdomData kingdom : KingdomList.kingdoms) {
							if(kingdom.name.equals(tile.kingdom) && kingdom == data) {
								canSit = true;
								break;
							}
						}
					}

					System.out.println("do " + canSit);
					if(canSit) {
						Seat seat = new Seat(par1World, par2, par3, par4);
						par1World.spawnEntityInWorld(seat);
						par5EntityPlayer.mountEntity(seat);
					}

					return true;
				}
			}
		}

		if(data != null && tile.kingdom.equals(data.name) && held != null && held.itemID == ModItems.heraldryItem.itemID && held.getItemDamage() == 0) {
			ItemHeraldry.writeCrestData(held, data.crest);
			return true;
		}

		return false;
	}

	@Override
	public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5) {
		if(par1World.blockExists(par2, par3 + 1, par4) && !par1World.isAirBlock(par2, par3 + 1, par4)) {
			int blockID = par1World.getBlockId(par2, par3 + 1, par4);
			int meta = par1World.getBlockMetadata(par2, par3 + 1, par4);
			Block block = blocksList[blockID];
			block.dropBlockAsItem(par1World, par2, par3 + 1, par4, meta, 0);
			par1World.setBlockToAir(par2, par3 + 1, par4);
		}
	}

	@Override
	public int getRenderType() {
		return -1;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int par1, int par2) {
		return planks.getIcon(0, 1);
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityThrone();
	}

	public static class Seat extends Entity {

		public Seat(World world, int x, int y, int z) {
			this(world);

			setPosition(x + 0.5, y + 0.5, z + 0.5);
		}

		public Seat(World par1World) {
			super(par1World);

			setSize(0F, 0F);
		}

		@Override
		protected void entityInit() { }

		@Override
		protected void readEntityFromNBT(NBTTagCompound nbttagcompound) { }

		@Override
		protected void writeEntityToNBT(NBTTagCompound nbttagcompound) { }

		@Override
		public void onUpdate() {
			super.onUpdate();

			if(riddenByEntity == null)
				setDead();

			if((int) posY == posY) // Fix the client sometimes derping for some odd reason...
				posY -= 1.5;
		}
	}
}
