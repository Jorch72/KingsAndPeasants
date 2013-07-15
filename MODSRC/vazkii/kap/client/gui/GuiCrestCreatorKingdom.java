/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Kings and Peasants Mod.
 *
 * Kings and Peasants is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 *
 * File Created @ [15 Jul 2013, 16:35:20 (GMT)]
 */
package vazkii.kap.client.gui;

import net.minecraft.client.gui.GuiButton;

public class GuiCrestCreatorKingdom extends GuiCrestCreator {

	GuiKingdomCreator parent;

	public GuiCrestCreatorKingdom(GuiKingdomCreator parent) {
		super(null);
		this.parent = parent;
		currentCrest = parent.kingdom.crest.copy();
	}

	@Override
	protected void actionPerformed(GuiButton par1GuiButton) {
		if(par1GuiButton.id == 7) {
			parent.kingdom.crest = currentCrest;
			mc.displayGuiScreen(parent);
			return;
		}

		super.actionPerformed(par1GuiButton);
	}

	@Override
	protected void keyTyped(char par1, int par2) {
		if(par2 == 1)
			mc.displayGuiScreen(parent);
	}
}
