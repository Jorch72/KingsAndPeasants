/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Kings and Peasants Mod.
 *
 * Kings and Peasants is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 *
 * File Created @ [13 Jul 2013, 12:08:14 (GMT)]
 */
package vazkii.kap.core.proxy;

import net.minecraft.network.INetworkManager;
import net.minecraftforge.common.MinecraftForge;
import vazkii.kap.core.lib.LibBlockNames;
import vazkii.kap.core.lib.LibMisc;
import vazkii.kap.network.IPacket;
import vazkii.kap.tile.TileEntityBanner;
import vazkii.kap.tile.TileEntityThrone;
import vazkii.kap.util.handler.MobStatHandler;
import vazkii.kap.util.handler.VillagerNamingHandler;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.common.registry.GameRegistry;

public class CommonProxy {

	public boolean isClient() {
		return false;
	}

	public void packetHandle(INetworkManager manager, IPacket packet, Player player) {
		packet.handle_server(manager, player);
	}

	public void registerTickHandler() {
		// NO-OP, will need a server tick handler sometime
	}

	public void initTileEntities() {
		GameRegistry.registerTileEntity(TileEntityBanner.class, tileName(LibBlockNames.NAME_HERALDRY_BLOCK));
		GameRegistry.registerTileEntity(TileEntityThrone.class, tileName(LibBlockNames.NAME_THRONE));
	}

	public void registerSubscribers() {
		VillagerNamingHandler.init();
		MinecraftForge.EVENT_BUS.register(new MobStatHandler());
	}

	public void readIconNames() {
		// NO-OP
	}

	private static String tileName(String name) {
		return LibMisc.MOD_ID + "_" + name;
	}
}
