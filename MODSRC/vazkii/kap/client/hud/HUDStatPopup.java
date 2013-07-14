/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Kings and Peasants Mod.
 *
 * Kings and Peasants is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 *
 * File Created @ [14 Jul 2013, 19:45:53 (GMT)]
 */
package vazkii.kap.client.hud;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.client.Minecraft;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.event.ForgeSubscribe;

import org.lwjgl.opengl.GL11;

import vazkii.kap.client.util.handler.InventoryDisplayHandler;

public class HUDStatPopup {

	private static final int STAY_TIME = 30;
	public static final int WAIT_TIME = 4;

	public static List<Entry> entriesWaiting = new ArrayList();
	private static Map<Entry, Integer> entries = new HashMap();

	private static final EnumChatFormatting[] formattings = new EnumChatFormatting[] {
		EnumChatFormatting.GOLD, EnumChatFormatting.BLUE, EnumChatFormatting.RED, EnumChatFormatting.GREEN
	};

	@ForgeSubscribe
	public void renderHUD(RenderGameOverlayEvent.Post event) {
		if(event.type == ElementType.CROSSHAIRS)
			for(Entry entry : entries.keySet())
				renderEntry(entry, event);
	}

	private void renderEntry(Entry entry, RenderGameOverlayEvent.Post event) {
		Minecraft mc = Minecraft.getMinecraft();
		int time = entries.get(entry);

		int xPos = event.resolution.getScaledWidth() / 2 - 12 - mc.fontRenderer.getStringWidth(entry.msg);
		int yPos = event.resolution.getScaledHeight() / 2 + (STAY_TIME - time * 2) / (STAY_TIME / 30);
		int opacity = time * 300 / STAY_TIME;

		opacity = Math.max(0, Math.min(255, opacity));

		if(opacity > 0) {
			String displayMsg = formattings[entry.type] + entry.msg;
			GL11.glPushMatrix();
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			mc.fontRenderer.drawStringWithShadow(displayMsg, xPos, yPos, 0xFFFFFF + (opacity << 24));

			GL11.glColor4f(1F, 1F, 1F, opacity / 255F);
			GL11.glScalef(0.5F, 0.5F, 0.5F);
			InventoryDisplayHandler.drawIcon((xPos + mc.fontRenderer.getStringWidth(entry.msg)) * 2 + 2, yPos * 2, entry.type);
			GL11.glDisable(GL11.GL_BLEND);
			GL11.glPopMatrix();
		}
	}

	public static void tick() {
		List<Entry> removeEntries = new ArrayList();

		boolean foundIllegal = false;

		for(Entry entry : entries.keySet()) {
			int time = entries.get(entry);

			entries.put(entry, --time);

			time = entries.get(entry);
			if(time < 0)
				removeEntries.add(entry);

			if(time >= STAY_TIME - WAIT_TIME)
				foundIllegal = true;
		}

		for(Entry entry : removeEntries)
			entries.remove(entry);


		if(!entriesWaiting.isEmpty() && !foundIllegal) {
			Entry waitingEntry = entriesWaiting.get(0);
			entries.put(waitingEntry, STAY_TIME);
			entriesWaiting.remove(0);
		}
	}

	public static class Entry {

		protected int type;
		protected String msg;

		public Entry(int type, String msg) {
			this.type = type;
			this.msg = msg;
		}

	}

}
