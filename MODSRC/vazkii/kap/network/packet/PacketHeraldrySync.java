/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Kings and Peasants Mod.
 *
 * Kings and Peasants is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 *
 * File Created @ [15 Jul 2013, 01:20:57 (GMT)]
 */
package vazkii.kap.network.packet;

import vazkii.kap.tile.TileEntityBanner;
import vazkii.kap.util.storage.CrestDataStorage;

public class PacketHeraldrySync extends PacketTileSync<TileEntityBanner> {

	private static final long serialVersionUID = -2620216309990152212L;
	CrestDataStorage crest;

	public PacketHeraldrySync(TileEntityBanner tile) {
		super(tile);
		crest = tile.data;
	}

	@Override
	public void tileLoad(TileEntityBanner tile) {
		tile.data = crest;
	}

}
