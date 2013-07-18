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

import net.minecraft.entity.EntityCreature;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import vazkii.kap.entity.occupation.Occupation;
import vazkii.kap.util.nbt.NBTManaged;
import vazkii.kap.util.nbt.NBTManager;
import vazkii.kap.util.storage.Coordinates;
import vazkii.kap.util.storage.KingdomData;
import vazkii.kap.util.storage.KingdomList;

public class EntityPeasant extends EntityCreature {

	private static final int OCCUPATION_DW = 22;
	private static final int LEVEL_DW = 23;
	private static final int KINGDOM_DW = 24;

	private static final String TAG_OCCUPATION = "occupation";
	private static final String TAG_LEVEL = "level";
	private static final String TAG_KINGDOM = "kingdom";

	@NBTManaged("coords") private Coordinates home1 = null;

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

	@Override
	public void onUpdate() {
		KingdomData kingdom = getKingdomData();
		if(kingdom != null)
			kingdom.population.remove(getCustomNameTag());

		super.onUpdate();
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();

		if(tasks.taskEntries.isEmpty())
			initAI();
	}

	@Override
	protected boolean isAIEnabled() {
		return true;
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound par1nbtTagCompound) {
		super.writeEntityToNBT(par1nbtTagCompound);

		par1nbtTagCompound.setInteger(TAG_OCCUPATION, getOccupation());
		par1nbtTagCompound.setInteger(TAG_LEVEL, getLevel());
		par1nbtTagCompound.setString(TAG_KINGDOM, getKingdom());

		NBTManager.writeType(par1nbtTagCompound, this);

		Occupation.occupations[getOccupation()].writeToNBT(this, par1nbtTagCompound);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound par1nbtTagCompound) {
		super.readEntityFromNBT(par1nbtTagCompound);

		setOccupation(par1nbtTagCompound.getInteger(TAG_OCCUPATION));
		setLevel(par1nbtTagCompound.getInteger(TAG_LEVEL));
		setKingdom(par1nbtTagCompound.getString(TAG_KINGDOM));

		NBTManager.loadType(par1nbtTagCompound, this);

		Occupation.occupations[getOccupation()].readFromNBT(this, par1nbtTagCompound);
	}

	@Override
	public float func_110174_bM() { // Get max distance from home
		return 8 + getLevel() * 8;
	}

	@Override
	public ChunkCoordinates func_110172_bL() { // Get home coordinates
		if(home1 == null) {
			KingdomData data = getKingdomData();
			if(data == null || !data.hasThrone(worldObj))
				return new ChunkCoordinates(0, 0, 0);

			return data.throneCoords.asChunkCoordinates();
		} else return home1.asChunkCoordinates();
	}

	@Override
	public boolean getAlwaysRenderNameTag() {
		return true;
	}

	public boolean updateHomeCoords(Coordinates coords) {
		KingdomData data = getKingdomData();
		if(data != null) {
			double distanceFromThrone = coords.distanceSqrdPlane(data.throneCoords);
			if(distanceFromThrone > data.getRadius())
				return false;

			home1 = coords;
			return true;
		}
		return false;
	}


	private void initAI() {
		Occupation.occupations[getOccupation()].addAI(this);
	}

	public int getOccupation() {
		return dataWatcher.getWatchableObjectInt(OCCUPATION_DW);
	}

	public int getLevel() {
		return dataWatcher.getWatchableObjectInt(LEVEL_DW);
	}

	public String getKingdom() {
		return dataWatcher.getWatchableObjectString(KINGDOM_DW);
	}

	public KingdomData getKingdomData() {
		String name = getKingdom();

		if(name.isEmpty()) {
			setDead();
			return null;
		}

		KingdomData kingdom = null;

		for(KingdomData data : KingdomList.kingdoms)
			if(data.name.equals(name)) {
				kingdom = data;
				break;
			}

		if(!kingdom.hasThrone(worldObj))
			setDead();

		return kingdom;
	}

	public void setOccupation(int occupation) {
		dataWatcher.updateObject(OCCUPATION_DW, occupation);
	}

	public void setLevel(int level) {
		dataWatcher.updateObject(LEVEL_DW, level);
	}

	public void setKingdom(String kingdom) {
		dataWatcher.updateObject(KINGDOM_DW, kingdom);
	}

}
