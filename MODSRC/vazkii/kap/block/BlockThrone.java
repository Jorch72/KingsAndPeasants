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
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import vazkii.kap.tile.TileEntityThrone;
import vazkii.kap.util.handler.KAPCreativeTab;
import vazkii.kap.util.storage.KingdomData;
import vazkii.kap.util.storage.KingdomList;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockThrone extends BlockContainer {

	public BlockThrone(int par1) {
		super(par1, Material.wood);
		setBlockBounds(0F, 0F, 0F, 1F, 2F, 1F);
		setStepSound(soundWoodFootstep);
		setBlockUnbreakable();
		setCreativeTab(KAPCreativeTab.INSTANCE);
	}

	@Override
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {
		TileEntityThrone tile = (TileEntityThrone) par1World.getBlockTileEntity(par2, par3, par4);

		if(tile.kingdom.isEmpty()) {
			KingdomData data = null;

			for(KingdomData kingdom : KingdomList.kingdoms)
				if(kingdom.owner.equals(par5EntityPlayer.username))
					data = kingdom;

			if(data != null) {
				tile.kingdom = data.name;
				PacketDispatcher.sendPacketToPlayer(tile.getDescriptionPacket(), (Player) par5EntityPlayer);
				return true;
			}
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
}
