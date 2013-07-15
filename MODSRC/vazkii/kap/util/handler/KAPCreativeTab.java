/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Kings and Peasants Mod.
 *
 * Kings and Peasants is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 *
 * File Created @ [15 Jul 2013, 01:45:22 (GMT)]
 */
package vazkii.kap.util.handler;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import vazkii.kap.core.lib.LibMisc;
import vazkii.kap.item.ModItems;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class KAPCreativeTab extends CreativeTabs {

	public static final KAPCreativeTab INSTANCE = new KAPCreativeTab();

	public KAPCreativeTab() {
		super(LibMisc.MOD_ID);
		LanguageRegistry.instance().addStringLocalization("itemGroup." + LibMisc.MOD_ID, LibMisc.MOD_NAME);
	}

	@Override
	public ItemStack getIconItemStack() {
		return new ItemStack(ModItems.heraldryItem);
	}

}
