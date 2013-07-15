/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Kings and Peasants Mod.
 *
 * Kings and Peasants is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 *
 * File Created @ [15 Jul 2013, 15:26:34 (GMT)]
 */
package vazkii.kap.item;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import vazkii.kap.KingsAndPeasants;
import vazkii.kap.core.lib.LibGuiIDs;
import vazkii.kap.core.lib.LibResources;
import vazkii.kap.util.handler.KAPCreativeTab;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemKingdomScroll extends Item {

	public ItemKingdomScroll(int par1) {
		super(par1);
		setMaxStackSize(1);
		setCreativeTab(KAPCreativeTab.INSTANCE);
	}

	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
		par3EntityPlayer.openGui(KingsAndPeasants.instance, LibGuiIDs.ID_NEW_KINGDOM, par2World, 0, 0, 0);
		return par1ItemStack;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) {
		itemIcon = par1IconRegister.registerIcon(LibResources.ITEM_ICON_KINGDOM_SCROLL);
	}

}
