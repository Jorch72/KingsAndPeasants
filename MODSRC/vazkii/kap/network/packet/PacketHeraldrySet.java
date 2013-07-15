/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Kings and Peasants Mod.
 *
 * Kings and Peasants is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 *
 * File Created @ [15 Jul 2013, 01:22:46 (GMT)]
 */
package vazkii.kap.network.packet;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.network.INetworkManager;
import vazkii.kap.item.ItemHeraldry;
import vazkii.kap.item.ModItems;
import vazkii.kap.network.IPacket;
import vazkii.kap.util.storage.CrestData;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class PacketHeraldrySet implements IPacket {

	private static final long serialVersionUID = 2520406953653403270L;
	CrestData crest;

	public PacketHeraldrySet(CrestData crest) {
		this.crest = crest;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void handle_client(INetworkManager manager, Player player) {
		handle_server(manager, player);
	}

	@Override
	public void handle_server(INetworkManager manager, Player player) {
		if(player instanceof EntityPlayer) {
			EntityPlayer entityPlayer = (EntityPlayer) player;
			ItemStack item = entityPlayer.getCurrentEquippedItem();
			if(item != null && item.itemID == ModItems.heraldryItem.itemID && item.getItemDamage() == 0) {
				ItemHeraldry.writeCrestData(item, crest);
				entityPlayer.addChatMessage("Crest edited!");
			}
		}
	}

}
