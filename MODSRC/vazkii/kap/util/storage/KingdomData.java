/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Kings and Peasants Mod.
 *
 * Kings and Peasants is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 *
 * File Created @ [15 Jul 2013, 16:05:47 (GMT)]
 */
package vazkii.kap.util.storage;

import java.io.Serializable;

import vazkii.kap.util.nbt.NBTManaged;

public class KingdomData implements Serializable {

	public static KingdomData clientKingdom;

	private static final long serialVersionUID = 5797594957265101922L;
	@NBTManaged("crest") public CrestData crest = new CrestData(0xFFFFFF, 0xFFFFFF, (short) -1);
	@NBTManaged("name") public String name;
	@NBTManaged("owner") public String owner;

	// TODO @NBTManager("stats") public KingdomStats stats;

	public KingdomData(CrestData crest, String name, String owner) {
		this.crest = crest;
		this.name = name;
		this.owner = owner;
	}
}
