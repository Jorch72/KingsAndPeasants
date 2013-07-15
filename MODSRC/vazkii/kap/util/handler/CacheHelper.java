/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Kings and Peasants Mod.
 *
 * Kings and Peasants is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 *
 * File Created @ [15 Jul 2013, 18:02:21 (GMT)]
 */
package vazkii.kap.util.handler;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.WorldServer;
import vazkii.kap.core.lib.LibMisc;

public final class CacheHelper {

	private static File getCacheFile() throws IOException {
		MinecraftServer server = MinecraftServer.getServer();
		if(server == null)
			throw new NullPointerException("Can't cache something outside of server environment!");

		WorldServer world = server.worldServers[0];
		File loc = world.getChunkSaveLocation();
		File cacheFile = new File(loc, LibMisc.MOD_ID + ".dat");

		if(!cacheFile.exists())
			cacheFile.createNewFile();

		return cacheFile;
	}

	public static NBTTagCompound getCacheCompound() {
		File f;

		try {
			f = getCacheFile();
		} catch(IOException e) {
			throw new RuntimeException("Error creating file!", e);
		}

		if(f == null)
			throw new RuntimeException("Error getting file!");

		try {
			NBTTagCompound cmp = CompressedStreamTools.readCompressed(new FileInputStream(f));
			return cmp;
		} catch (IOException e) {
			NBTTagCompound cmp = new NBTTagCompound();
			try {
				CompressedStreamTools.writeCompressed(cmp, new FileOutputStream(f));
				return getCacheCompound();
			} catch (IOException e1) {
				return null;
			}
		}
	}

	public static boolean injectNBTToFile(NBTTagCompound cmp) {
		try {
			File f = getCacheFile();
			CompressedStreamTools.writeCompressed(cmp, new FileOutputStream(f));
			return true;
		} catch (IOException e) {
			return false;
		}
	}

}
