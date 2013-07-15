package vazkii.kap.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiSlot;
import net.minecraft.client.renderer.Tessellator;

import org.lwjgl.opengl.GL11;

import vazkii.kap.client.util.helper.RenderHelper;
import vazkii.kap.core.lib.LibResources;
import vazkii.kap.core.proxy.ClientProxy;
import vazkii.kap.util.storage.CrestDataStorage;

public class GuiCrestList extends GuiSlot {

	GuiCrestCreator parent;

	public GuiCrestList(GuiCrestCreator parent) {
		super(Minecraft.getMinecraft(), 250, parent.height, 32, parent.height - 32, 36);
		this.parent = parent;
	}

	@Override
	protected int getSize() {
		return LibResources.ICON_COUNT;
	}

	@Override
	protected int getContentHeight() {
		return getSize() * 36;
	}

	@Override
	protected void elementClicked(int i, boolean flag) {
		parent.currentCrest.icon = (short) i;
	}

	@Override
	protected boolean isSelected(int i) {
		return parent.currentCrest.icon == i;
	}

	@Override
	protected void drawBackground() {
		//parent.drawDefaultBackground();
	}

	@Override
	protected void drawSlot(int i, int j, int k, int l, Tessellator tessellator) {
		CrestDataStorage crest = new CrestDataStorage(0, 0xFFFFFF, (short) i);
		GL11.glScalef(0.5F, 0.5F, 0.5F);
		RenderHelper.renderCrest(crest, (j + 10) * 2, k * 2, parent.zLevel() + 0.1);
		GL11.glScalef(2F, 2F, 2F);

		Minecraft.getMinecraft().fontRenderer.drawStringWithShadow(ClientProxy.iconNames.get(i), j + 50, k + 6, 0xFFFFFF);
	}

	@Override
	protected void overlayBackground(int par1, int par2, int par3, int par4) {
		if(Minecraft.getMinecraft().theWorld == null)
			super.overlayBackground(par1, par2, par3, par4);
		else {
			Tessellator tessellator = Tessellator.instance;
	        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	        tessellator.startDrawingQuads();
	        GL11.glDisable(GL11.GL_TEXTURE_2D);
	        tessellator.setColorRGBA_I(0, 0xFF);
	        tessellator.addVertex(0.0D, par2, 0.0D);
	        tessellator.addVertex(250, par2, 0.0D);
	        tessellator.addVertex(250, par1, 0.0D);
	        tessellator.addVertex(0.0D, par1, 0.0D);
	        tessellator.draw();
	        GL11.glEnable(GL11.GL_TEXTURE_2D);
		}
	}

	@Override
	protected void drawContainerBackground(Tessellator tess) {
		if(Minecraft.getMinecraft().theWorld == null)
			super.drawContainerBackground(tess);
		else {
	        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	        GL11.glDisable(GL11.GL_TEXTURE_2D);
	        tess.startDrawingQuads();
	        GL11.glEnable(GL11.GL_BLEND);
	        tess.setColorRGBA_I(0x000000, 0x99);
	        tess.addVertex(0, bottom, 0.0D);
	        tess.addVertex(250, bottom, 0.0D);
	        tess.addVertex(250, top, 0.0D);
	        tess.addVertex(0, top, 0.0D);
	        tess.draw();

	        tess.startDrawingQuads();
	        tess.setColorRGBA_I(0x000000, 0xFF);
	        tess.addVertex(250, bottom + 32, 0.0D);
	        tess.addVertex(255, bottom + 32, 0.0D);
	        tess.addVertex(255, top - 32, 0.0D);
	        tess.addVertex(250, top - 32, 0.0D);
	        tess.draw();
	        GL11.glEnable(GL11.GL_TEXTURE_2D);
	        GL11.glDisable(GL11.GL_BLEND);
		}
	}
}
