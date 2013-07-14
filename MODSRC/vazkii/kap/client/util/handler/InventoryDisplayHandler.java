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

import java.util.Arrays;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import vazkii.kap.client.util.helper.RenderHelper;
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
			int x = res.getScaledWidth() / 2 + 98;
			int y = res.getScaledHeight() / 2 - 58;

			if(!creative) {
				x -= 10;
				y -= 20;
			}

			if(!mc.thePlayer.getActivePotionEffects().isEmpty())
				x += 60;

			int mouseX = Mouse.getX() * res.getScaledWidth() / mc.displayWidth;
			int mouseY = res.getScaledHeight() - Mouse.getY() * res.getScaledHeight() / mc.displayHeight;

			GL11.glEnable(GL11.GL_BLEND);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			drawSideTab(x, y);
			GL11.glDisable(GL11.GL_BLEND);

			y += 4;

			net.minecraft.client.renderer.RenderHelper.disableStandardItemLighting();
			drawIcon(x, y, 0);
			if(mouseX - x >= 0 && mouseX - x <= 16 && mouseY - y >= 0 && mouseY - y < 16)
				RenderHelper.renderTooltip(mouseX, mouseY, 1347420415, -267386864, Arrays.asList(EnumChatFormatting.GOLD + "Gold: " + PlayerDataStorage.clientData.getGold()));
			y += 16;
			drawIcon(x, y, 1);
			if(mouseX - x >= 0 && mouseX - x <= 16 && mouseY - y >= 0 && mouseY - y < 16)
				RenderHelper.renderTooltip(mouseX, mouseY, 1347420415, -267386864, Arrays.asList(EnumChatFormatting.BLUE + "Renown: " + PlayerDataStorage.clientData.getReputation()));
		}
	}

	public static void drawIcon(int x, int y, int index) {
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

	private static void drawSideTab(int x, int y) {
		TextureManager tm = Minecraft.getMinecraft().renderEngine;

		tm.func_110577_a(new ResourceLocation(LibResources.GUI_SIDE_TAB));

		GL11.glColor4f(1F, 1F, 1F, 1F);
		Tessellator tess = Tessellator.instance;
		tess.startDrawingQuads();
		tess.addVertexWithUV(x, y + 64, 0, 0, 1);
		tess.addVertexWithUV(x + 32, y + 64, 0, 1, 1);
		tess.addVertexWithUV(x + 32, y, 0, 1, 0);
		tess.addVertexWithUV(x, y, 0, 0, 0);
		tess.draw();
	}

}
