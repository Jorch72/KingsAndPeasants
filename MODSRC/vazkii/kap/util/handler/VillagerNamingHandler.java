/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Kings and Peasants Mod.
 *
 * Kings and Peasants is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 *
 * File Created @ [13 Jul 2013, 02:11:08 (GMT)]
 */
package vazkii.kap.util.handler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import vazkii.kap.KingsAndPeasants;
import vazkii.kap.core.lib.LibResources;

public final class VillagerNamingHandler {

	private static List<String> maleNames = new ArrayList();
	private static List<String> femaleNames = new ArrayList();
	private static List<String> lastNames = new ArrayList();

	public static void init() {
		loadVillagerNames();
		MinecraftForge.EVENT_BUS.register(new VillagerNamingHandler());
	}

	private static void loadVillagerNames() {
		try {
			loadNamesFromFile(LibResources.NAMES_MALE, maleNames);
			loadNamesFromFile(LibResources.NAMES_FEMALE, femaleNames);
			loadNamesFromFile(LibResources.NAMES_LAST, lastNames);
		} catch(IOException e) {
			throw new RuntimeException(e);
		}
	}

	private static void loadNamesFromFile(String loc, List<String> list) throws IOException {
		InputStream stream = KingsAndPeasants.class.getResourceAsStream(loc);
		BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
		String s;

		while((s = reader.readLine()) != null)
			list.add(s);
	}

	@ForgeSubscribe
	public void entityUpdate(LivingUpdateEvent event) {
		if(event.entityLiving instanceof EntityVillager && !((EntityLiving) event.entityLiving).hasCustomNameTag())
			applyRandomName((EntityVillager) event.entityLiving);
	}

	private void applyRandomName(EntityVillager villager) {
		boolean female = villager.worldObj.rand.nextBoolean();
		List<String> mainNames = female ? femaleNames : maleNames;

		String mainName = mainNames.get(villager.worldObj.rand.nextInt(mainNames.size()));
		String lastName = lastNames.get(villager.worldObj.rand.nextInt(lastNames.size()));

		villager.setCustomNameTag(mainName + " " + lastName);
	}
}
