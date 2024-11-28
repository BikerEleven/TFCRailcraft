package com.bikereleven.tfclverailcraft.proxy;

import com.bikereleven.tfclverailcraft.client.renderers.WoodenMinecartRenderer;
import com.bikereleven.tfclverailcraft.entity.EntityWoodenMinecartChest;

import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {

	@Override
	public void registerRenderers() {
		
		RenderingRegistry.registerEntityRenderingHandler(EntityWoodenMinecartChest.class, new WoodenMinecartRenderer());
		
	}

}
