/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Kings and Peasants Mod.
 *
 * Kings and Peasants is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 *
 * File Created @ [13 Jul 2013, 11:37:44 (GMT)]
 */
package vazkii.kap.util.storage;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import vazkii.kap.KingsAndPeasants;
import vazkii.kap.core.lib.LibMisc;
import vazkii.kap.util.nbt.NBTManaged;
import vazkii.kap.util.nbt.NBTManager;

public final class PlayerDataStorage {

	private static final String TAG_NAME = "PlayerData";

	public static final Map<String, PlayerDataStorage> playerData = new HashMap();

	@NBTManaged("gold") private int gold;
	@NBTManaged("rep") private int rep;

	public int getGold() {
		return gold;
	}

	public int getReputation() {
		return rep;
	}

	public void setGold(int gold) {
		if(this.gold != gold) {
			this.gold = gold;
			update();
		}
	}

	public void setReputation(int rep) {
		if(this.rep != rep) {
			this.rep = rep;
			update();
		}
	}

	public EntityPlayer asEntityPlayer() {
		MinecraftServer server = MinecraftServer.getServer();
		return server == null ? null : server.getConfigurationManager().getPlayerForUsername(TAG_NAME);
	}

	public void update() {
		EntityPlayer player = asEntityPlayer();
		if(player == null) {
			KingsAndPeasants.instance.logger.log(Level.WARNING, "Tried to write to NBT non-existant player data!");
			return;
		}

		NBTTagCompound cmp = player.getEntityData();
		boolean hasKey = cmp.hasKey(TAG_NAME);

		NBTTagCompound cmp1 = hasKey ? cmp.getCompoundTag(TAG_NAME) : new NBTTagCompound();
		update(cmp1);

		if(!hasKey)
			cmp.setCompoundTag(TAG_NAME, cmp1);
	}

	public void update(NBTTagCompound cmp) {
		NBTManager.writeType(cmp, this);
	}

	public static void playerLogin(EntityPlayer player) {
		NBTTagCompound cmp = player.getEntityData();
		if(!cmp.hasKey(LibMisc.MOD_ID)) {
			PlayerDataStorage storage = new PlayerDataStorage();
			playerData.put(player.username, storage);

			NBTTagCompound cmp1 = new NBTTagCompound();
			NBTManager.writeType(cmp1, storage);
			cmp.setCompoundTag(TAG_NAME, cmp1);
		} else {
			NBTTagCompound cmp1 = cmp.getCompoundTag(TAG_NAME);

			PlayerDataStorage storage = new PlayerDataStorage();
			NBTManager.loadType(cmp1, storage);
			playerData.put(player.username, storage);
		}

	}

	public static void playerLogout(EntityPlayer player) {
		NBTTagCompound cmp = player.getEntityData();
		boolean hasKey = cmp.hasKey(TAG_NAME);

		NBTTagCompound cmp1 = hasKey ? cmp.getCompoundTag(TAG_NAME) : new NBTTagCompound();
		NBTManager.writeType(cmp1, playerData.get(player.username));

		if(!hasKey)
			cmp.setCompoundTag(TAG_NAME, cmp1);

		playerData.remove(player.username);
	}

}
