/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Kings and Peasants Mod.
 *
 * Kings and Peasants is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 *
 * File Created @ [15 Jul 2013, 01:13:51 (GMT)]
 */
package vazkii.kap.block;

import net.minecraft.block.Block;
import vazkii.kap.core.lib.LibBlockIDs;
import vazkii.kap.core.lib.LibBlockNames;
import vazkii.kap.item.ItemBlockThrone;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public final class ModBlocks {

	public static Block heraldryBlock;
	public static Block throne;

	public static void init() {
		heraldryBlock = new BlockHeraldry(LibBlockIDs.idBlockHeraldry).setUnlocalizedName(LibBlockNames.NAME_HERALDRY_BLOCK);;
		throne = new BlockThrone(LibBlockIDs.idThrone).setUnlocalizedName(LibBlockNames.NAME_THRONE);

		registerBlocks();
		nameBlocks();
	}

	private static void registerBlocks() {
		GameRegistry.registerBlock(throne, ItemBlockThrone.class, LibBlockNames.NAME_THRONE);
	}

	private static void nameBlocks() {
		LanguageRegistry.addName(throne, LibBlockNames.DISPLAY_NAME_THRONE);
	}

}
