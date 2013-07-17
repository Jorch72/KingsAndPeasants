/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Kings and Peasants Mod.
 *
 * Kings and Peasants is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 *
 * File Created @ [18 Jul 2013, 00:13:40 (GMT)]
 */
package vazkii.kap.entity.occupation;

import vazkii.kap.core.lib.LibResources;
import vazkii.kap.entity.EntityPeasant;

public class OccupationPeasant extends Occupation {

	public OccupationPeasant(int id) {
		super(id);
	}

	@Override
	public String getTextureName(EntityPeasant entity) {
		return LibResources.OCCUPATION_TEXTURE_PEASANT;
	}

	@Override
	public String getOccupationName(int level) {
		return "Peasant";
	}

	@Override
	public int getContractLevel() {
		return 1;
	}

	@Override
	public int getLevelUp(int currentLevel) {
		return -1;
	}

}
