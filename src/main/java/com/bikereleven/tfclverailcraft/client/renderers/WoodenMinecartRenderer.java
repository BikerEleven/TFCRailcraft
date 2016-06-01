package com.bikereleven.tfclverailcraft.client.renderers;

import com.bikereleven.tfclverailcraft.reference.Reference;

import net.minecraft.client.renderer.entity.RenderMinecart;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.util.ResourceLocation;

public class WoodenMinecartRenderer extends RenderMinecart {

	private static final ResourceLocation minecartTextures = new ResourceLocation(Reference.MOD_ID,
			"textures/entities/woodenminecart.png");

	@Override
	protected ResourceLocation getEntityTexture(EntityMinecart p_110775_1_) {
		return minecartTextures;
	}
}
