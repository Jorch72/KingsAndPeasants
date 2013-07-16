/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Kings and Peasants Mod.
 *
 * Kings and Peasants is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 *
 * File Created @ [16 Jul 2013, 22:56:22 (GMT)]
 */
package vazkii.kap.client.render.tile;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

import org.lwjgl.opengl.GL11;

import vazkii.kap.client.model.ModelThrone;
import vazkii.kap.tile.TileEntityThrone;
import vazkii.kap.util.storage.CrestData;
import vazkii.kap.util.storage.KingdomData;
import vazkii.kap.util.storage.KingdomList;

public class RenderTileThrone extends TileEntitySpecialRenderer {

	ModelThrone model = new ModelThrone();

	@Override
	public void renderTileEntityAt(TileEntity tileentity, double d0, double d1, double d2, float f) {
		GL11.glPushMatrix();
		GL11.glTranslated(d0, d1, d2);
		GL11.glTranslatef(0.5F, 1.5F, 0.5F);
		GL11.glRotatef(180F, 1F, 0F, 0F);
		CrestData data = new CrestData(0, 0, (short) -1);
		TileEntityThrone throne = (TileEntityThrone) tileentity;
		for(KingdomData kingdom : KingdomList.kingdoms)
			if(kingdom.name.equals(throne.kingdom))
				data = kingdom.crest;

		model.render(data);
		GL11.glPopMatrix();
	}

}
