/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Kings and Peasants Mod.
 *
 * Kings and Peasants is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 *
 * File Created @ [18 Jul 2013, 17:30:01 (GMT)]
 */
package vazkii.kap.client.render.block;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.world.IBlockAccess;

import org.lwjgl.opengl.GL11;

import vazkii.kap.KingsAndPeasants;
import vazkii.kap.tile.TileEntityThrone;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class RenderThrone implements ISimpleBlockRenderingHandler {

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
		GL11.glPushMatrix();
		GL11.glTranslatef(-0.5F, -0.8F, -0.5F);
		GL11.glScalef(0.75F, 0.75F, 0.75F);
		TileEntityRenderer.instance.renderTileEntityAt(new TileEntityThrone(), 0.0D, 0.0D, 0.0D, 0.0F);
		GL11.glPopMatrix();
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
		return false;
	}

	@Override
	public boolean shouldRender3DInInventory() {
		return true;
	}

	@Override
	public int getRenderId() {
		return KingsAndPeasants.throneBlockRenderID;
	}



}
