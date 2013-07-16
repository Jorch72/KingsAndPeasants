/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Kings and Peasants Mod.
 *
 * Kings and Peasants is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 *
 * File Created @ [16 Jul 2013, 23:05:33 (GMT)]
 */
package vazkii.kap.network.packet;

import vazkii.kap.tile.TileEntityThrone;

public class PacketThroneSync extends PacketTileSync<TileEntityThrone> {

	private static final long serialVersionUID = 7764785800860828969L;
	String kingdom;

	public PacketThroneSync(TileEntityThrone tile) {
		super(tile);
		kingdom = tile.kingdom;
	}

	@Override
	public void tileLoad(TileEntityThrone tile) {
		tile.kingdom = kingdom;
	}

}
