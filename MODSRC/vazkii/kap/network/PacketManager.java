/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Kings and Peasants Mod.
 *
 * Kings and Peasants is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 *
 * File Created @ [13 Jul 2013, 12:04:42 (GMT)]
 */
package vazkii.kap.network;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import vazkii.kap.KingsAndPeasants;
import vazkii.kap.core.lib.LibMisc;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

public final class PacketManager implements IPacketHandler {

	@Override
	public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player) {
		try {
			ByteArrayInputStream byteStream = new ByteArrayInputStream(packet.data);
			ObjectInputStream objStream = new ObjectInputStream(byteStream);
			IPacket ipacket = (IPacket) objStream.readObject();

			KingsAndPeasants.proxy.packetHandle(manager, ipacket, player);
		} catch(Throwable e) {
			e.printStackTrace();
		}
	}

	public static Packet250CustomPayload buildPacket(IPacket ipacket) {
		try {
			Packet250CustomPayload payload = new Packet250CustomPayload();
			ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
			ObjectOutputStream objStream = new ObjectOutputStream(byteStream);

			objStream.writeObject(ipacket);

			payload.channel = LibMisc.NETWORK_CHANNEL;
			payload.data = byteStream.toByteArray();
			payload.length = payload.data.length;

			return payload;
		} catch(IOException e) {
			e.printStackTrace();
		}

		return null;
	}

}
