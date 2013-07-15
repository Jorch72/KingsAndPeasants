/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Kings and Peasants Mod.
 *
 * Kings and Peasants is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 *
 * File Created @ [15 Jul 2013, 17:39:23 (GMT)]
 */
package vazkii.kap.util.storage;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import vazkii.kap.util.handler.CacheHelper;
import vazkii.kap.util.nbt.NBTManager;

public class KingdomList {

	private static final String TAG_KINGDOM_LIST = "kingdomList";

	public static List<KingdomData> kingdoms = new ArrayList();

	public static void writeToNBT(NBTTagCompound cmp) {
		NBTTagList list = new NBTTagList();
		for(KingdomData kingdom : kingdoms) {
			NBTTagCompound cmp1 = new NBTTagCompound();
			NBTManager.writeType(cmp1, kingdom);
			list.appendTag(cmp1);
		}
		cmp.setTag(TAG_KINGDOM_LIST, list);

		CacheHelper.injectNBTToFile(cmp);
	}

	public static void readFromNBT(NBTTagCompound cmp) {
		if(!cmp.hasKey(TAG_KINGDOM_LIST))
			return;

		NBTTagList list = cmp.getTagList(TAG_KINGDOM_LIST);
		for(int i = 0; i < list.tagCount(); i++) {
			NBTTagCompound cmp1 = (NBTTagCompound) list.tagAt(i);
			KingdomData tempData = new KingdomData(new CrestData(0, 0, (short) 0), "" , "");
			NBTManager.loadType(cmp1, tempData);
			kingdoms.add(tempData);
		}
	}
}