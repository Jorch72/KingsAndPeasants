/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Kings and Peasants Mod.
 *
 * Kings and Peasants is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 *
 * File Created @ [15 Jul 2013, 18:17:57 (GMT)]
 */
package vazkii.kap.network.packet;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.util.EnumChatFormatting;
import vazkii.kap.item.ModItems;
import vazkii.kap.network.IPacket;
import vazkii.kap.util.handler.CacheHelper;
import vazkii.kap.util.storage.KingdomData;
import vazkii.kap.util.storage.KingdomList;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class PacketCreateNewKingdom implements IPacket {

	private static final long serialVersionUID = 8902672505276503427L;
	KingdomData data;

	public PacketCreateNewKingdom(KingdomData data) {
		this.data = data;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void handle_client(INetworkManager manager, Player player) {
		handle_server(manager, player);
	}

	@Override
	public void handle_server(INetworkManager manager, Player player) {
		if(player instanceof EntityPlayer) {
			EntityPlayer eplayer = (EntityPlayer) player;

			EntityPlayer entityPlayer = (EntityPlayer) player;
			ItemStack item = entityPlayer.getCurrentEquippedItem();
			if(item == null || item.itemID != ModItems.kingdomScroll.itemID || data.name.isEmpty())
				return;

			String error = "";

			for(KingdomData kingdom : KingdomList.kingdoms) {
				if(kingdom.crest.equals(data.crest))
					error = "There already exists a kingdom with that crest!";

				if(kingdom.name.equals(data.name))
					error = "There already a kingdom with tht name!";

				if(kingdom.owner.equals(eplayer.username))
					error = "You are already the king of " + kingdom.name + "!";
			}

			if(error.isEmpty()) {
				data.owner = ((EntityPlayer) player).username;
				KingdomList.kingdoms.add(data);
				NBTTagCompound cmp = CacheHelper.getCacheCompound();
				KingdomList.writeToNBT(cmp);

				eplayer.inventory.setInventorySlotContents(eplayer.inventory.currentItem, null);
			} else eplayer.addChatMessage(EnumChatFormatting.RED + error);
		}
	}
}
