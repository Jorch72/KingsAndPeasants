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

public final class ModBlocks {

	public static Block heraldryBlock;

	public static void init() {
		heraldryBlock = new BlockHeraldry(LibBlockIDs.idBlockHeraldry).setUnlocalizedName(LibBlockNames.NAME_HERALDRY_BLOCK);;

		nameBlocks();
	}

	private static void nameBlocks() {

	}

}
