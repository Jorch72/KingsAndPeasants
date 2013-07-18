/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Kings and Peasants Mod.
 *
 * Kings and Peasants is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 *
 * File Created @ [18 Jul 2013, 00:52:11 (GMT)]
 */
package vazkii.kap.client.render.entity;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import vazkii.kap.core.lib.LibResources;
import vazkii.kap.entity.EntityPeasant;
import vazkii.kap.entity.occupation.Occupation;

public class RenderPeasant extends RenderBiped {

	boolean appliedTranslation = false;

	public RenderPeasant() {
		super(new ModelBiped(), 0.5F);
	}

	@Override
	protected boolean func_110813_b(EntityLivingBase par1EntityLivingBase) {
		boolean can = super.func_110813_b(par1EntityLivingBase);
		if(can) {
			GL11.glTranslatef(0F, 0.3F, 0F);
			appliedTranslation = true;
		}
		return can;
	}

	@Override
	protected void passSpecialRender(EntityLivingBase par1EntityLivingBase, double par2, double par4, double par6) {
		super.passSpecialRender(par1EntityLivingBase, par2, par4, par6);

		if(appliedTranslation) {
			GL11.glTranslatef(0F, -0.3F, 0F);
			renderLivingLabel(par1EntityLivingBase, Occupation.occupations[((EntityPeasant) par1EntityLivingBase).getOccupation()].getOccupationName(((EntityPeasant) par1EntityLivingBase).getLevel()), par2, par4 + 0.05, par6, 64);
			appliedTranslation = false;
		}
	}

	@Override
	protected ResourceLocation func_110775_a(Entity par1Entity) {
		return new ResourceLocation(LibResources.PREFIX_PEOPLE + Occupation.occupations[((EntityPeasant) par1Entity).getOccupation()].getTextureName((EntityPeasant) par1Entity));
	}
}
