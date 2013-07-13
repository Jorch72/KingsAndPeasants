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

import java.util.logging.Logger;

import net.minecraftforge.common.MinecraftForge;
import vazkii.kap.core.lib.LibMisc;
import vazkii.kap.core.proxy.CommonProxy;
import vazkii.kap.network.PacketManager;
import vazkii.kap.network.PlayerTracker;
import vazkii.kap.util.handler.MobStatHandler;
import vazkii.kap.util.handler.VillagerNamingHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = LibMisc.MOD_ID, name = LibMisc.MOD_NAME, version = LibMisc.VERSION)
@NetworkMod(clientSideRequired = true, channels = { LibMisc.NETWORK_CHANNEL }, packetHandler = PacketManager.class)
public final class KingsAndPeasants {

	@Instance(LibMisc.MOD_ID)
	public static KingsAndPeasants instance;

	@SidedProxy(serverSide = LibMisc.COMMON_PROXY, clientSide = LibMisc.CLIENT_PROXY)
	public static CommonProxy proxy;

	public Logger logger;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		instance = this;
		logger = event.getModLog();

		VillagerNamingHandler.init();
		MinecraftForge.EVENT_BUS.register(new MobStatHandler());

		proxy.registerTickHandler();
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		GameRegistry.registerPlayerTracker(new PlayerTracker());
	}
}
