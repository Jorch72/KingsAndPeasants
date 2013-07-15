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

import vazkii.kap.block.ModBlocks;
import vazkii.kap.core.lib.LibMisc;
import vazkii.kap.core.proxy.CommonProxy;
import vazkii.kap.item.ModItems;
import vazkii.kap.network.PacketManager;
import vazkii.kap.network.PlayerTracker;
import vazkii.kap.util.handler.CacheHelper;
import vazkii.kap.util.handler.ConfigHandler;
import vazkii.kap.util.handler.GuiHandler;
import vazkii.kap.util.storage.KingdomList;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartedEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
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

		proxy.registerTickHandler();
		proxy.registerSubscribers();

		ConfigHandler.init(event.getSuggestedConfigurationFile());

		ModItems.init();
		ModBlocks.init();
		proxy.initTileEntities();

		NetworkRegistry.instance().registerGuiHandler(this, new GuiHandler());
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		GameRegistry.registerPlayerTracker(new PlayerTracker());

		proxy.readIconNames();
	}

	@EventHandler
	public void serverStarted(FMLServerStartedEvent event) {
		KingdomList.readFromNBT(CacheHelper.getCacheCompound());
	}
}
