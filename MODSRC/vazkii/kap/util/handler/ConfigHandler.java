/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Kings and Peasants Mod.
 *
 * Kings and Peasants is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 *
 * File Created @ [15 Jul 2013, 01:38:40 (GMT)]
 */
package vazkii.kap.util.handler;

import java.io.File;

import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.Property;
import vazkii.kap.core.lib.LibBlockIDs;
import vazkii.kap.core.lib.LibBlockNames;
import vazkii.kap.core.lib.LibItemIDs;
import vazkii.kap.core.lib.LibItemNames;

public final class ConfigHandler {

	private static final String OPTION_SCROLLING_TEXT = "scollingText.show";
	private static final String COMMENT_SCROLLING_TEXT = "Set to false to remove the scrolling text next to the crosshair when the playe recieves gold or renown.";

	public static boolean doScrollingText = true;

	private static Configuration config;

	public static void init(File file) {
		config = new Configuration(file);

		config.load();

		doScrollingText = loadGeneralBool(OPTION_SCROLLING_TEXT, doScrollingText, COMMENT_SCROLLING_TEXT);

		LibItemIDs.idItemHeraldry = loadItem(LibItemNames.NAME_ITEM_HERALDRY, LibItemIDs.DEFAULT_ITEM_HERALDRY);

		LibBlockIDs.idBlockHeraldry = loadBlock(LibBlockNames.NAME_HERALDRY_BLOCK, LibBlockIDs.DEFAULT_BLOCK_HERALDRY);

		config.save();
	}

	private static int loadItem(String label, int defaultID) {
		return config.getItem(label, defaultID).getInt(defaultID);
	}

	private static int loadBlock(String label, int defaultID) {
		return config.getBlock(label, defaultID).getInt(defaultID);
	}

	private static boolean loadGeneralBool(String label, boolean defaultValue, String comment) {
		Property prop = config.get(Configuration.CATEGORY_GENERAL, label, defaultValue);
		prop.comment = comment;
		return prop.getBoolean(defaultValue);
	}
}
