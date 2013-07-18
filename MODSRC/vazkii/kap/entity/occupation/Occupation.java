/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Kings and Peasants Mod.
 *
 * Kings and Peasants is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 *
 * File Created @ [17 Jul 2013, 23:00:02 (GMT)]
 */
package vazkii.kap.entity.occupation;

import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import vazkii.kap.entity.EntityPeasant;

public abstract class Occupation {

	public static final Occupation[] occupations = new Occupation[11];

	public final int id;

	public static final Occupation peasant = new OccupationPeasant(0);

	public Occupation(int id) {
		if(occupations[id] != null)
			throw new IllegalArgumentException("Occupation ID " + id + " is already occupied.");

		this.id = id;
		occupations[id] = this;
	}

	public void entityInteract(EntityPeasant entity, EntityPlayer player) {

	}

	public void addAI(EntityPeasant entity) {
		addAI(entity, 0, new EntityAIWander(entity, 0.6F));
	}

	public final void addAI(EntityPeasant entity, int priority, EntityAIBase ai) {
		if(acceptAI(entity, priority, ai))
			entity.tasks.addTask(priority, ai);
	}

	public boolean acceptAI(EntityPeasant entity, int priority, EntityAIBase ai) {
		return true;
	}

	public void entityUpdate(EntityPeasant entity) {

	}

	public void writeToNBT(EntityPeasant entity, NBTTagCompound cmp) {

	}

	public void readFromNBT(EntityPeasant entity, NBTTagCompound cmp) {

	}

	public boolean isWorkingOccupation() {
		return false;
	}

	public abstract String getTextureName(EntityPeasant entity);

	public abstract String getOccupationName(int level);

	public abstract int getContractLevel();

	public abstract int getLevelUp(int currentLevel);

}