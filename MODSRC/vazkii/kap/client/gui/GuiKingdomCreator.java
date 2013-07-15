/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Kings and Peasants Mod.
 *
 * Kings and Peasants is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 *
 * File Created @ [15 Jul 2013, 16:10:19 (GMT)]
 */
package vazkii.kap.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import vazkii.kap.core.lib.LibResources;
import vazkii.kap.util.storage.CrestData;
import vazkii.kap.util.storage.KingdomData;

public class GuiKingdomCreator extends GuiScreen {

	public KingdomData kingdom = new KingdomData(new CrestData(0, 0xFFFFFF, (short) 0), "", Minecraft.getMinecraft().thePlayer.username);

	GuiTextField textField;

	int xSize, ySize;

	@Override
	public void initGui() {
		super.initGui();

		xSize = 248;
		ySize = 140;

		int xStart = width / 2 - xSize / 2;
		int yStart = height / 2 - ySize / 2;

		buttonList.clear();
		buttonList.add(new GuiCrestButton(0, xStart + 20, yStart + 50, this));
		buttonList.add(new GuiButton(1, xStart + 140, yStart + 100, 100, 20, "Done"));

		textField = new GuiTextField(fontRenderer, xStart + 116, yStart + 52, 107, 14);
		textField.setCanLoseFocus(false);
		textField.setFocused(true);
		textField.setMaxStringLength(16);
		textField.setEnableBackgroundDrawing(false);
	}

	@Override
	public void drawScreen(int par1, int par2, float par3) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        mc.func_110434_K().func_110577_a(new ResourceLocation(LibResources.GUI_NEW_KINGDOM));
        int k = (width - xSize) / 2;
        int l = (height - ySize) / 2;
        drawTexturedModalRect(k, l, 0, 0, xSize, ySize);

        drawCenteredString(fontRenderer, "Heraldry:", k + 50, l + 35, 0xDDDDDD);
        drawCenteredString(fontRenderer, "Kingdom Name:", k + 170, l + 35, 0xDDDDDD);

        textField.drawTextBox();

		super.drawScreen(par1, par2, par3);
	}

	@Override
	protected void actionPerformed(GuiButton par1GuiButton) {
		super.actionPerformed(par1GuiButton);

		if(par1GuiButton.id == 0)
			mc.displayGuiScreen(new GuiCrestCreatorKingdom(this));
	}

	@Override
	protected void keyTyped(char par1, int par2) {
		super.keyTyped(par1, par2);
		textField.textboxKeyTyped(par1, par2);

		kingdom.name = textField.getText();
	}

	@Override
	protected void mouseClicked(int par1, int par2, int par3) {
		super.mouseClicked(par1, par2, par3);
		textField.mouseClicked(par1, par2, par3);
	}

}
