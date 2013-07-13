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

import vazkii.kap.network.IPacket;
import vazkii.kap.util.storage.PlayerDataStorage;
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
	public void handle_client() {
		PlayerDataStorage.clientData = data;
	}

	@Override
	public void handle_server() {
		// NO-OP
	}
}
