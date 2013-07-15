/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Kings and Peasants Mod.
 *
 * Kings and Peasants is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 *
 * File Created @ [15 Jul 2013, 19:45:54 (GMT)]
 */
package vazkii.kap.network.packet;

import net.minecraft.network.INetworkManager;
import vazkii.kap.network.IPacket;
import vazkii.kap.util.storage.KingdomData;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class PacketKingdomSync implements IPacket {

	private static final long serialVersionUID = 80776465041198270L;
	KingdomData kingdom;

	public PacketKingdomSync(KingdomData kingdom) {
		this.kingdom = kingdom;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void handle_client(INetworkManager manager, Player player) {
		KingdomData.clientKingdom = kingdom;
	}

	@Override
	public void handle_server(INetworkManager manager, Player player) {
		// NO-OP
	}

}
