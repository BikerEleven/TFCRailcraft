package com.bikereleven.tfclverailcraft.init;

import com.bikereleven.tfclverailcraft.item.*;

import cpw.mods.fml.common.registry.GameRegistry;

public class ModItems {

	public static final TLRItem servo = new ItemServo();
	public static final TLRItem wireCore = new ItemWireCore();
	public static final TLRItem fuelFurnace = new ItemFuelFurnace();

	public static void init() {

		GameRegistry.registerItem(servo, "servo");
		GameRegistry.registerItem(wireCore, "wirecore");
		GameRegistry.registerItem(fuelFurnace, "fuelfurnace");

	}

}
