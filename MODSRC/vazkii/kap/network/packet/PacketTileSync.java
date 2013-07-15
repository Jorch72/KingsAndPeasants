/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Kings and Peasants Mod.
 *
 * Kings and Peasants is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 *
 * File Created @ [15 Jul 2013, 01:17:42 (GMT)]
 */
package vazkii.kap.network.packet;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.network.INetworkManager;
import net.minecraft.tileentity.TileEntity;
import vazkii.kap.network.IPacket;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public abstract class PacketTileSync<T extends TileEntity> implements IPacket {

	private static final long serialVersionUID = 1741250512697277243L;
	int x, y, z;

	public PacketTileSync(T tile) {
		this.x = tile.xCoord;
		this.y = tile.yCoord;
		this.z = tile.zCoord;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void handle_client(INetworkManager manager, Player player) {
		Minecraft mc = Minecraft.getMinecraft();
		WorldClient world = mc.theWorld;

		TileEntity tile = world.getBlockTileEntity(x, y, z);
		if(tile != null)
			tileLoad((T) tile);
	}

	public abstract void tileLoad(T tile);

	@Override
	public final void handle_server(INetworkManager manager, Player player) {
		// NO-OP
	}
}
