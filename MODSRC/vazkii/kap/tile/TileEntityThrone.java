/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Kings and Peasants Mod.
 *
 * Kings and Peasants is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 *
 * File Created @ [16 Jul 2013, 23:04:37 (GMT)]
 */
package vazkii.kap.tile;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet;
import net.minecraft.tileentity.TileEntity;
import vazkii.kap.network.PacketManager;
import vazkii.kap.network.packet.PacketThroneSync;
import vazkii.kap.util.nbt.NBTManaged;
import vazkii.kap.util.nbt.NBTManager;

public class TileEntityThrone extends TileEntity {

	@NBTManaged("kingdom") public String kingdom = "";

	@Override
	public Packet getDescriptionPacket() {
		return PacketManager.buildPacket(new PacketThroneSync(this));
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
