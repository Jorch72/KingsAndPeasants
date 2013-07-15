/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Kings and Peasants Mod.
 *
 * Kings and Peasants is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 *
 * File Created @ [15 Jul 2013, 16:40:37 (GMT)]
 */
package vazkii.kap.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;

import org.lwjgl.opengl.GL11;

import vazkii.kap.client.util.helper.RenderHelper;

public class GuiCrestButton extends GuiButton {

	GuiKingdomCreator parent;

	public GuiCrestButton(int par1, int par2, int par3, GuiKingdomCreator parent) {
		super(par1, par2, par3, 64, 64, "");
		this.parent = parent;
	}

	@Override
	public void drawButton(Minecraft par1Minecraft, int par2, int par3) {
		RenderHelper.renderCrest(parent.kingdom.crest, xPosition, yPosition, zLevel + 0.02);
		if(par2 >= xPosition && par2 < xPosition + 64 && par3 >= yPosition && par3 < yPosition + 64) {
			GL11.glDisable(GL11.GL_DEPTH_TEST);
			drawRect(xPosition, yPosition, xPosition + 64, yPosition + 64, 0x33FFFFFF);
			GL11.glDisable(GL11.GL_DEPTH_TEST);
		}
	}

}
