/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Kings and Peasants Mod.
 *
 * Kings and Peasants is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 *
 * File Created @ [13 Jul 2013, 18:05:45 (GMT)]
 */
package vazkii.kap.client.util.handler;

import java.util.EnumSet;

import vazkii.kap.core.lib.LibMisc;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class ClientTickHandler implements ITickHandler {

	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData) {
		if(type.equals(EnumSet.of(TickType.CLIENT)))
			clientTick(true);
		else renderTick(true, (float) tickData[0]);
	}

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData) {
		if(type.equals(EnumSet.of(TickType.CLIENT)))
			clientTick(false);
		else renderTick(false, (float) tickData[0]);
	}

	private void clientTick(boolean start) {

	}

	private void renderTick(boolean start, float partTicks) {
		if(!start)
			InventoryDisplayHandler.renderInventoryOverlay();
	}

	@Override
	public EnumSet<TickType> ticks() {
		return EnumSet.of(TickType.CLIENT, TickType.RENDER);
	}

	@Override
	public String getLabel() {
		return LibMisc.MOD_ID + "_Client";
	}

}
