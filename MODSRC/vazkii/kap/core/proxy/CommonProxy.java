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

import net.minecraftforge.common.MinecraftForge;
import vazkii.kap.network.IPacket;
import vazkii.kap.util.handler.MobStatHandler;
import vazkii.kap.util.handler.VillagerNamingHandler;

public class CommonProxy {

	public boolean isClient() {
		return false;
	}

	public void packetHandle(IPacket packet) {
		packet.handle_server();
	}

	public void registerTickHandler() {
		// NO-OP, will need a server tick handler sometime
	}

	public void registerSubscribers() {
		VillagerNamingHandler.init();
		MinecraftForge.EVENT_BUS.register(new MobStatHandler());
	}
}
