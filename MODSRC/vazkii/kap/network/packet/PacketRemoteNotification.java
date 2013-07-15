/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Kings and Peasants Mod.
 *
 * Kings and Peasants is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 *
 * File Created @ [15 Jul 2013, 19:19:07 (GMT)]
 */
package vazkii.kap.network.packet;

import net.minecraft.network.INetworkManager;
import vazkii.kap.client.hud.HUDStatPopup;
import vazkii.kap.client.hud.HUDStatPopup.Entry;
import vazkii.kap.network.IPacket;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class PacketRemoteNotification implements IPacket {

	private static final long serialVersionUID = -5362321536931012386L;
	int type;
	String msg;
	String notification;

	public PacketRemoteNotification(int type, String msg) {
		this(type, msg, "");
	}

	public PacketRemoteNotification(int type, String msg, String notification) {
		this.type = type;
		this.msg = msg;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void handle_client(INetworkManager manager, Player player) {
		HUDStatPopup.entriesWaiting.add(new Entry(type, msg));
	}

	@Override
	public void handle_server(INetworkManager manager, Player player) {
		// NO-OP
	}
}
