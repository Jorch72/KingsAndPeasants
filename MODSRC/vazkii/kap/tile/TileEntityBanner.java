package vazkii.kap.tile;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet;
import net.minecraft.tileentity.TileEntity;
import vazkii.kap.network.PacketManager;
import vazkii.kap.network.packet.PacketHeraldrySync;
import vazkii.kap.util.nbt.NBTManaged;
import vazkii.kap.util.nbt.NBTManager;
import vazkii.kap.util.storage.CrestDataStorage;

public class TileEntityBanner extends TileEntity {

	@NBTManaged("crest") public CrestDataStorage data = new CrestDataStorage(0xFFFFFF, 0xFFFFFF, (short) -1);
	@NBTManaged("locked") public boolean locked;

	@Override
	public Packet getDescriptionPacket() {
		return PacketManager.buildPacket(new PacketHeraldrySync(this));
	}

	@Override
	public void readFromNBT(NBTTagCompound par1nbtTagCompound) {
		super.readFromNBT(par1nbtTagCompound);

		NBTManager.loadType(par1nbtTagCompound, this);
	}

	@Override
	public void writeToNBT(NBTTagCompound par1nbtTagCompound) {
		super.writeToNBT(par1nbtTagCompound);

		NBTManager.writeType(par1nbtTagCompound, this);
	}

}
