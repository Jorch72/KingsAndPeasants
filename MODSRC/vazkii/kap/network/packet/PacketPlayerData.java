/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Kings and Peasants Mod.
 *
 * Kings and Peasants is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 *
 * File Created @ [13 Jul 2013, 16:25:22 (GMT)]
 */
package vazkii.kap.network.packet;

import net.minecraft.network.INetworkManager;
import vazkii.kap.client.hud.HUDStatPopup;
import vazkii.kap.client.hud.HUDStatPopup.Entry;
import vazkii.kap.network.IPacket;
import vazkii.kap.util.storage.PlayerDataStorage;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class PacketPlayerData implements IPacket {

	private static final long serialVersionUID = -3757567626661917375L;
	public PlayerDataStorage data;

	public PacketPlayerData(PlayerDataStorage data) {
		this.data = data;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void handle_client(INetworkManager manager, Player player) {
		PlayerDataStorage oldData = PlayerDataStorage.clientData;
		PlayerDataStorage.clientData = data;

		if(oldData == null)
			return;

		int diffGold = data.getGold() - oldData.getGold();
		int diffRep = data.getReputation() - oldData.getReputation();

		if(diffGold != 0)
			HUDStatPopup.entriesWaiting.add(new Entry(0, diffGold > 0 ? "+" + diffGold : "" + diffGold));
		if(diffRep != 0)
			HUDStatPopup.entriesWaiting.add(new Entry(1, diffRep > 0 ? "+" + diffRep : "" + diffRep));
	}

	@Override
	public void handle_server(INetworkManager manager, Player player) {
		// NO-OP
	}
}
