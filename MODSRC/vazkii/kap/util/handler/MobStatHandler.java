/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Kings and Peasants Mod.
 *
 * Kings and Peasants is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 *
 * File Created @ [13 Jul 2013, 16:31:53 (GMT)]
 */
package vazkii.kap.util.handler;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import vazkii.kap.network.PacketManager;
import vazkii.kap.network.packet.PacketPlayerData;
import vazkii.kap.util.storage.PlayerDataStorage;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;

public final class MobStatHandler {

	@ForgeSubscribe
	public void onLivingDeath(LivingDeathEvent event) {
		if(!event.entityLiving.worldObj.isRemote && event.source.getSourceOfDamage() instanceof EntityPlayer && event.entityLiving instanceof EntityLiving) {
			EntityPlayer player = (EntityPlayer) event.source.getSourceOfDamage();

			PlayerDataStorage data = PlayerDataStorage.playerData.get(player.username);
			int xp = ((EntityLiving) event.entityLiving).experienceValue;

			data.setGold(data.getGold() + xp);
			if(event.entityLiving instanceof IMob)
				data.setReputation(data.getReputation() + xp * 5);

			PacketDispatcher.sendPacketToPlayer(PacketManager.buildPacket(new PacketPlayerData(data)), (Player) player);
		}
	}

}
