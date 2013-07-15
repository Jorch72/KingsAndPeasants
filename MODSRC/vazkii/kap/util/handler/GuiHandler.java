/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Kings and Peasants Mod.
 *
 * Kings and Peasants is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 *
 * File Created @ [15 Jul 2013, 01:53:28 (GMT)]
 */
package vazkii.kap.util.handler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import vazkii.kap.client.gui.GuiCrestCreator;
import vazkii.kap.core.lib.LibGuiIDs;
import cpw.mods.fml.common.network.IGuiHandler;

public final class GuiHandler implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world,	int x, int y, int z) {
		switch(ID) {
			case LibGuiIDs.ID_CREST_EDITOR :
				return new GuiCrestCreator(player.getCurrentEquippedItem());
		}

		return null;
	}

}
