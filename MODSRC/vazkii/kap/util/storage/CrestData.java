package vazkii.kap.util.storage;

import java.io.Serializable;

import net.minecraft.nbt.NBTTagCompound;
import vazkii.kap.util.nbt.NBTManaged;
import vazkii.kap.util.nbt.NBTManager;

public class CrestData implements Serializable {

	private static final long serialVersionUID = -5350356530740862889L;
	@NBTManaged("color1") public int color1;
	@NBTManaged("color2") public int color2;
	@NBTManaged("icon") public short icon;

	public CrestData(int color1, int color2, short icon) {
		this.color1 = color1;
		this.color2 = color2;
		this.icon = icon;
	}

	public void writeToCmp(NBTTagCompound cmp) {
		NBTManager.writeType(cmp, this);
	}

	public static CrestData readFromCmp(NBTTagCompound cmp) {
		CrestData data = new CrestData(0, 0, (short) 0);
		NBTManager.loadType(cmp, data);

		return data;
	}

}
