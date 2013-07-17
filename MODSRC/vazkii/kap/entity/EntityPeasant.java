/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Kings and Peasants Mod.
 *
 * Kings and Peasants is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 *
 * File Created @ [17 Jul 2013, 22:59:36 (GMT)]
 */
package vazkii.kap.entity;

import net.minecraft.entity.EntityLiving;
import net.minecraft.world.World;

public class EntityPeasant extends EntityLiving {

	private static final int OCCUPATION_DW = 22;
	private static final int LEVEL_DW = 23;
	private static final int KINGDOM_DW = 24;

	public EntityPeasant(World par1World, String kingdom) {
		this(par1World);

		dataWatcher.updateObject(KINGDOM_DW, kingdom);
	}

	public EntityPeasant(World par1World) {
		super(par1World);

		dataWatcher.addObject(OCCUPATION_DW, 0);
		dataWatcher.addObject(LEVEL_DW, 0);
		dataWatcher.addObject(KINGDOM_DW, "");

		dataWatcher.setObjectWatched(OCCUPATION_DW);
		dataWatcher.setObjectWatched(LEVEL_DW);
		dataWatcher.setObjectWatched(KINGDOM_DW);
	}

}
