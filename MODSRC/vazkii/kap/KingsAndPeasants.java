/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Kings and Peasants Mod.
 *
 * Kings and Peasants is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 *
 * File Created @ [13 Jul 2013, 01:26:49 (GMT)]
 */
package vazkii.kap;

import vazkii.kap.core.lib.LibMisc;
import vazkii.kap.util.handler.VillagerNamingHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = LibMisc.MOD_ID, name = LibMisc.MOD_NAME, version = LibMisc.VERSION)
public final class KingsAndPeasants {

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		VillagerNamingHandler.init();
	}

}
