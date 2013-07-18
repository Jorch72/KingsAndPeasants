/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Kings and Peasants Mod.
 *
 * Kings and Peasants is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 *
 * File Created @ [15 Jul 2013, 15:43:30 (GMT)]
 */
package vazkii.kap.item;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import vazkii.kap.core.lib.LibResources;
import vazkii.kap.entity.EntityPeasant;
import vazkii.kap.util.handler.KAPCreativeTab;
import vazkii.kap.util.storage.KingdomData;
import vazkii.kap.util.storage.KingdomList;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemVassalScroll extends Item {

	public ItemVassalScroll(int par1) {
		super(par1);
		setMaxStackSize(1);
		setCreativeTab(KAPCreativeTab.INSTANCE);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) {
		itemIcon = par1IconRegister.registerIcon(LibResources.ITEM_ICON_VASSAL_SCROLL);
	}

	@Override // TODO Debug code!!
	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10) {
		if(!par3World.isRemote) {
			KingdomData data = null;
			for(KingdomData kingdom : KingdomList.kingdoms)
				if(kingdom.owner.equals(par2EntityPlayer.username))
					data = kingdom;

			if(data == null)
				return true;

			EntityPeasant peasant = new EntityPeasant(par3World, data.name);
			peasant.setPosition(par4, par5 + 1.6, par6);
			par3World.spawnEntityInWorld(peasant);
		}

		return true;
	}
}
