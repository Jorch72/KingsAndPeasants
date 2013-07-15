/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Kings and Peasants Mod.
 *
 * Kings and Peasants is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 *
 * File Created @ [15 Jul 2013, 01:26:53 (GMT)]
 */
package vazkii.kap.item;

import net.minecraft.item.Item;
import vazkii.kap.core.lib.LibItemIDs;
import vazkii.kap.core.lib.LibItemNames;
import cpw.mods.fml.common.registry.LanguageRegistry;

public final class ModItems {

	public static Item heraldryItem;
	public static Item kingdomScroll;
	public static Item vassalScroll;

	public static void init() {
		heraldryItem = new ItemHeraldry(LibItemIDs.idItemHeraldry).setUnlocalizedName(LibItemNames.NAME_ITEM_HERALDRY);
		kingdomScroll = new ItemKingdomScroll(LibItemIDs.idKingdomScroll).setUnlocalizedName(LibItemNames.NAME_KINGDOM_SCROLL);
		vassalScroll = new ItemVassalScroll(LibItemIDs.idVassalScroll).setUnlocalizedName(LibItemNames.NAME_VASSAL_SCROLL);

		nameItems();
	}

	private static void nameItems() {
		LanguageRegistry.addName(kingdomScroll, LibItemNames.DISPLAY_NAME_KINGDOM_SCROLL);
		LanguageRegistry.addName(vassalScroll, LibItemNames.DISPLAY_NAME_VASSAL_SCROLL);
	}
}
