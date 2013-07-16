/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Kings and Peasants Mod.
 *
 * Kings and Peasants is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 *
 * File Created @ [16 Jul 2013, 01:00:30 (GMT)]
 */
package vazkii.kap.client.gui;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import vazkii.kap.client.util.helper.RenderHelper;
import vazkii.kap.core.lib.LibResources;
import vazkii.kap.util.storage.KingdomData;

public class GuiKingdomInfo extends GuiScreen {

	int xSize, ySize;

	@Override
	public void initGui() {
		super.initGui();

		xSize = 195;
		ySize = 227;
	}

	@Override
	public void drawScreen(int par1, int par2, float par3) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        mc.func_110434_K().func_110577_a(new ResourceLocation(LibResources.GUI_KINGDOM_IFNO));
        int k = (width - xSize) / 2;
        int l = (height - ySize) / 2;
        KingdomData kingdom = KingdomData.clientKingdom;
        if(kingdom != null) {
            drawTexturedModalRect(k, l, 0, 0, xSize, ySize);
            RenderHelper.renderCrest(kingdom.crest, k + 66, l + 28, zLevel + 0.02);
            String name = "Kingdom of " + kingdom.name;
            int xCenter = k + xSize / 2;
            drawCenteredString(fontRenderer, name, k + xSize / 2, l + 10, 0xFFFFFF);
            int size = fontRenderer.getStringWidth(name) / 2;

            fontRenderer.drawStringWithShadow("King: " + kingdom.owner, k + 6, l + 100, 0xFFFFFF);
            fontRenderer.drawStringWithShadow("Population: " + (kingdom.population.size() + 1), k + 6, l + 113, 0xFFFFFF);
            fontRenderer.drawStringWithShadow("Vassals: " + kingdom.vassals.size(), k + 6, l + 126, 0xFFFFFF);

            // TODO More info!

            GL11.glEnable(GL11.GL_BLEND);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            GL11.glScalef(0.5F, 0.5F, 0.5F);
            mc.func_110434_K().func_110577_a(new ResourceLocation(LibResources.GUI_KINGDOM_IFNO));
            drawTexturedModalRect((xCenter - size - 28) * 2, (l + 8) * 2, xSize, 0, 48, 24);
            drawTexturedModalRect((xCenter + size + 4) * 2, (l + 8) * 2, xSize, 24, 48, 24);
            GL11.glScalef(2F, 2F, 2F);
            GL11.glDisable(GL11.GL_BLEND);
        }

		super.drawScreen(par1, par2, par3);
	}

}
