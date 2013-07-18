/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Kings and Peasants Mod.
 *
 * Kings and Peasants is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 *
 * File Created @ [13 Jul 2013, 12:08:09 (GMT)]
 */
package vazkii.kap.core.proxy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.network.INetworkManager;
import net.minecraftforge.common.MinecraftForge;
import vazkii.kap.KingsAndPeasants;
import vazkii.kap.client.hud.HUDStatPopup;
import vazkii.kap.client.render.entity.RenderPeasant;
import vazkii.kap.client.render.tile.RenderTileBanner;
import vazkii.kap.client.render.tile.RenderTileThrone;
import vazkii.kap.client.util.handler.ClientTickHandler;
import vazkii.kap.core.lib.LibResources;
import vazkii.kap.entity.EntityPeasant;
import vazkii.kap.network.IPacket;
import vazkii.kap.tile.TileEntityBanner;
import vazkii.kap.tile.TileEntityThrone;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

public class ClientProxy extends CommonProxy {

	public static List<String> iconNames = new ArrayList();

	@Override
	public boolean isClient() {
		return true;
	}

	@Override
	public void packetHandle(INetworkManager manager, IPacket packet, Player player) {
		packet.handle_client(manager, player);
	}

	@Override
	public void registerTickHandler() {
		super.registerTickHandler();
		TickRegistry.registerTickHandler(new ClientTickHandler(), Side.CLIENT);
	}

	@Override
	public void initTileEntities() {
		super.initTileEntities();

		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBanner.class, new RenderTileBanner());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityThrone.class, new RenderTileThrone());
	}

	@Override
	public void initEntities() {
		super.initEntities();

		RenderingRegistry.registerEntityRenderingHandler(EntityPeasant.class, new RenderPeasant());
	}

	@Override
	public void registerSubscribers() {
		super.registerSubscribers();
		MinecraftForge.EVENT_BUS.register(new HUDStatPopup());
	}

	@Override
	public void readIconNames() {
		try {
			InputStream stream = KingsAndPeasants.class.getResourceAsStream(LibResources.NAMES_HERALDRY);
			BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

			String line;
			while((line = reader.readLine()) != null)
				iconNames.add(line);

			reader.close();
		} catch (IOException e) {
			throw new RuntimeException("(CraftHeraldy) Failed to load icon names!", e);
		}
	}
}
