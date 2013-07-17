/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Kings and Peasants Mod.
 *
 * Kings and Peasants is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 *
 * File Created @ [17 Jul 2013, 00:42:25 (GMT)]
 */
package vazkii.kap.util.storage;

import java.io.Serializable;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import vazkii.kap.util.nbt.NBTManaged;

public class Coordinates implements Serializable {

	private static final long serialVersionUID = -1085208964203557792L;
	@NBTManaged("x") public int x;
	@NBTManaged("y") public int y;
	@NBTManaged("z") public int z;

	public Coordinates(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public int getBlockID(World world) {
		return world.getBlockId(x, y, z);
	}

	public int getMetadata(World world) {
		return world.getBlockMetadata(x, y, z);
	}

	public TileEntity getTileEntity(World world) {
		return world.getBlockTileEntity(x, y, z);
	}

	public ChunkCoordinates asChunkCoordinates() {
		return new ChunkCoordinates(x, y, z);
	}
}
