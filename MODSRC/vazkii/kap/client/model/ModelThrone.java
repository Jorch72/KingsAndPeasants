package vazkii.kap.client.model;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import vazkii.kap.client.util.helper.RenderHelper;
import vazkii.kap.core.lib.LibResources;
import vazkii.kap.util.storage.CrestData;

public class ModelThrone extends ModelBase {

	ModelRenderer Seat;
	ModelRenderer RightLeanBase;
	ModelRenderer RightLeanTop;
	ModelRenderer LeftLeanBase;
	ModelRenderer LeftLeanTop;
	ModelRenderer BackLeanBottom;
	ModelRenderer BackLeanMiddle;
	ModelRenderer BackLeanTop;
	ModelRenderer BackLeanSpike1;
	ModelRenderer BackLeanSpike2;
	ModelRenderer BackLeanSpike3;
	ModelRenderer BackLeanSpike4;
	ModelRenderer BackLeanSpike5;

	public ModelThrone() {
		textureWidth = 128;
		textureHeight = 64;

		Seat = new ModelRenderer(this, 32, 6);
		Seat.addBox(-5F, 0F, -8F, 10, 8, 14);
		Seat.setRotationPoint(0F, 16F, 0F);
		Seat.setTextureSize(128, 64);
		RightLeanBase = new ModelRenderer(this, 38, 28);
		RightLeanBase.addBox(-7F, 0F, -7F, 2, 14, 13);
		RightLeanBase.setRotationPoint(0F, 10F, 0F);
		RightLeanBase.setTextureSize(128, 64);
		RightLeanTop = new ModelRenderer(this, 0, 29);
		RightLeanTop.addBox(-8F, 8F, -8F, 4, 2, 15);
		RightLeanTop.setRotationPoint(0F, 0F, 0F);
		RightLeanTop.setTextureSize(128, 64);
		LeftLeanBase = new ModelRenderer(this, 68, 28);
		LeftLeanBase.addBox(5F, 0F, -7F, 2, 14, 13);
		LeftLeanBase.setRotationPoint(0F, 10F, 0F);
		LeftLeanBase.setTextureSize(128, 64);
		LeftLeanTop = new ModelRenderer(this, 0, 46);
		LeftLeanTop.addBox(4F, 8F, -8F, 4, 2, 15);
		LeftLeanTop.setRotationPoint(0F, 0F, 0F);
		LeftLeanTop.setTextureSize(128, 64);
		BackLeanBottom = new ModelRenderer(this, 0, 0);
		BackLeanBottom.addBox(-7F, 8F, 6F, 14, 16, 2);
		BackLeanBottom.setRotationPoint(0F, 0F, 0F);
		BackLeanBottom.setTextureSize(128, 64);
		BackLeanMiddle = new ModelRenderer(this, 0, 18);
		BackLeanMiddle.addBox(-6F, -2F, 6F, 12, 10, 1);
		BackLeanMiddle.setRotationPoint(0F, 0F, 0F);
		BackLeanMiddle.setTextureSize(128, 64);
		BackLeanTop = new ModelRenderer(this, 32, 0);
		BackLeanTop.addBox(-8F, -4F, 5F, 16, 3, 3);
		BackLeanTop.setRotationPoint(0F, 0F, 0F);
		BackLeanTop.setTextureSize(128, 64);
		BackLeanSpike1 = new ModelRenderer(this, 70, 0);
		BackLeanSpike1.addBox(-7F, -5F, 6F, 1, 1, 1);
		BackLeanSpike1.setRotationPoint(0F, 0F, 0F);
		BackLeanSpike1.setTextureSize(128, 64);
		BackLeanSpike2 = new ModelRenderer(this, 26, 23);
		BackLeanSpike2.addBox(-4F, -6F, 6F, 1, 2, 1);
		BackLeanSpike2.setRotationPoint(0F, 0F, 0F);
		BackLeanSpike2.setTextureSize(128, 64);
		BackLeanSpike3 = new ModelRenderer(this, 26, 18);
		BackLeanSpike3.addBox(-1F, -8F, 6F, 2, 4, 1);
		BackLeanSpike3.setRotationPoint(0F, 0F, 0F);
		BackLeanSpike3.setTextureSize(128, 64);
		BackLeanSpike4 = new ModelRenderer(this, 26, 26);
		BackLeanSpike4.addBox(3F, -6F, 6F, 1, 2, 1);
		BackLeanSpike4.setRotationPoint(0F, 0F, 0F);
		BackLeanSpike4.setTextureSize(128, 64);
		BackLeanSpike5 = new ModelRenderer(this, 70, 2);
		BackLeanSpike5.addBox(6F, -5F, 6F, 1, 1, 1);
		BackLeanSpike5.setRotationPoint(0F, 0F, 0F);
		BackLeanSpike5.setTextureSize(128, 64);
	}

	public void render(CrestData crest) {
		Minecraft.getMinecraft().renderEngine.func_110577_a(new ResourceLocation(LibResources.MODEL_THRONE));

		Seat.render(0.0625F);
		RightLeanBase.render(0.0625F);
		RightLeanTop.render(0.0625F);
		LeftLeanBase.render(0.0625F);
		LeftLeanTop.render(0.0625F);
		BackLeanBottom.render(0.0625F);
		BackLeanMiddle.render(0.0625F);
		BackLeanTop.render(0.0625F);
		BackLeanSpike1.render(0.0625F);
		BackLeanSpike2.render(0.0625F);
		BackLeanSpike3.render(0.0625F);
		BackLeanSpike4.render(0.0625F);
		BackLeanSpike5.render(0.0625F);

		boolean crestExists = crest != null && crest.icon != -1;

		if(crestExists) {
			GL11.glPushMatrix();
			float scale = 145F;
			float dimScale = 1F / scale;
			GL11.glRotatef(90F, 0F, 1F, 0F);
			GL11.glRotatef(-90F, 0F, 1F, 0F);
			GL11.glTranslatef(-0.22F, -0.05F, 0.361F);
			GL11.glScalef(dimScale, dimScale, dimScale);
			RenderHelper.renderCrest(crest, 0, 2, 0);
			GL11.glEnable(GL11.GL_LIGHTING);
			GL11.glPopMatrix();
		}
	}
}