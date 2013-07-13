/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Kings and Peasants Mod.
 *
 * Kings and Peasants is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 *
 * File Created @ [13 Jul 2013, 12:08:09 (GMT)]
 */
package vazkii.kap.core.proxy;

import vazkii.kap.client.util.handler.ClientTickHandler;
import vazkii.kap.network.IPacket;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

public class ClientProxy extends CommonProxy {

	@Override
	public boolean isClient() {
		return true;
	}

	@Override
	public void packetHandle(IPacket packet) {
		packet.handle_client();
	}

	@Override
	public void registerTickHandler() {
		super.registerTickHandler();
		TickRegistry.registerTickHandler(new ClientTickHandler(), Side.CLIENT);
	}

}
