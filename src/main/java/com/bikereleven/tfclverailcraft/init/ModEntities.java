package com.bikereleven.tfclverailcraft.init;

import com.bikereleven.tfclverailcraft.TFCRailcraft;
import com.bikereleven.tfclverailcraft.entity.EntityWoodenMinecartChest;
import com.bikereleven.tfclverailcraft.reference.Reference;

import cpw.mods.fml.common.registry.EntityRegistry;

public class ModEntities {
	
	public static void init(){
		
		EntityRegistry.registerGlobalEntityID(EntityWoodenMinecartChest.class, "woodenMinecartChest", EntityRegistry.findGlobalUniqueEntityId());
		EntityRegistry.registerModEntity(EntityWoodenMinecartChest.class, "woodenMinecartChest", 12, TFCRailcraft.instance, 80, 5, true);
		
	}
	
}
