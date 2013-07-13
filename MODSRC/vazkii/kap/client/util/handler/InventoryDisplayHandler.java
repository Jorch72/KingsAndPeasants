/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Kings and Peasants Mod.
 *
 * Kings and Peasants is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 *
 * File Created @ [13 Jul 2013, 18:07:16 (GMT)]
 */
package vazkii.kap.client.util.handler;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import vazkii.kap.core.lib.LibResources;
import vazkii.kap.util.storage.PlayerDataStorage;

public final class InventoryDisplayHandler {

	public static void renderInventoryOverlay() {
		Minecraft mc = Minecraft.getMinecraft();
		if(mc.currentScreen != null && mc.currentScreen instanceof GuiInventory || mc.currentScreen instanceof GuiContainerCreative) {
			boolean creative = mc.currentScreen instanceof GuiContainerCreative;

			if(PlayerDataStorage.clientData == null || creative && ((GuiContainerCreative) mc.currentScreen).getCurrentTabIndex() != CreativeTabs.tabInventory.getTabIndex())
				return;

			ScaledResolution res = new ScaledResolution(mc.gameSettings, mc.displayWidth, mc.displayHeight);
			int x = res.getScaledWidth() / 2 + 100;
			int y = res.getScaledHeight() / 2 - 60;

			if(!creative) {
				x -= 10;
				y -= 20;
			}

			if(!mc.thePlayer.getActivePotionEffects().isEmpty())
				x += 60;

			drawIcon(x, y, 0);
			mc.fontRenderer.drawStringWithShadow("Gold: " + PlayerDataStorage.clientData.getGold(), x + 18, y + 5, 0xFFC600);
			y += 16;
			drawIcon(x, y, 1);
			mc.fontRenderer.drawStringWithShadow("Rep: " + PlayerDataStorage.clientData.getReputation(), x + 18, y + 5, 0x0054FF);
		}
	}

	private static void drawIcon(int x, int y, int index) {
		TextureManager tm = Minecraft.getMinecraft().renderEngine;

		double u = index % 2 * 0.5;
		double v = index / 4;

		tm.func_110577_a(new ResourceLocation(LibResources.GUI_INDICATORS));

		GL11.glColor4f(1F, 1F, 1F, 1F);
		Tessellator tess = Tessellator.instance;
		tess.startDrawingQuads();
		tess.addVertexWithUV(x, y + 16, 0, u, v + 0.5);
		tess.addVertexWithUV(x + 16, y + 16, 0, u + 0.5, v + 0.5);
		tess.addVertexWithUV(x + 16, y, 0, u + 0.5, v);
		tess.addVertexWithUV(x, y, 0, u, v);
		tess.draw();
	}

}
