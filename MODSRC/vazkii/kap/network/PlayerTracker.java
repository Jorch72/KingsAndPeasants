/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Kings and Peasants Mod.
 *
 * Kings and Peasants is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 *
 * File Created @ [13 Jul 2013, 11:39:29 (GMT)]
 */
package vazkii.kap.network;

import net.minecraft.entity.player.EntityPlayer;
import vazkii.kap.network.packet.PacketKingdomSync;
import vazkii.kap.util.storage.KingdomData;
import vazkii.kap.util.storage.KingdomList;
import vazkii.kap.util.storage.PlayerStats;
import cpw.mods.fml.common.IPlayerTracker;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;

public class PlayerTracker implements IPlayerTracker {

	@Override
	public void onPlayerLogin(EntityPlayer player) {
		PlayerStats.playerLogin(player);
		for(KingdomData kingdom : KingdomList.kingdoms)
			if(kingdom.owner.equals(player.username))
				PacketDispatcher.sendPacketToPlayer(PacketManager.buildPacket(new PacketKingdomSync(kingdom)), (Player) player);
	}

	@Override
	public void onPlayerLogout(EntityPlayer player) {
		PlayerStats.playerLogout(player);
	}

	@Override
	public void onPlayerChangedDimension(EntityPlayer player) {
	}

	@Override
	public void onPlayerRespawn(EntityPlayer player) {
	}

}
