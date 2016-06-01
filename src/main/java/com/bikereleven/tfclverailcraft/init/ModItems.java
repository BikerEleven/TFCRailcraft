package com.bikereleven.tfclverailcraft.init;

import com.bikereleven.tfclverailcraft.item.*;

import cpw.mods.fml.common.registry.GameRegistry;
import mods.railcraft.common.fluids.Fluids;
import net.minecraftforge.fluids.FluidContainerRegistry;

public class ModItems {

	public static final TLRItem servo = new ItemServo();
	public static final TLRItem wireCore = new ItemWireCore();
	public static final TLRItem fuelFurnace = new ItemFuelFurnace();
	public static final TLRItem woodChestCart = new ItemWoodenMinecartChest();
	
	
	public static final ItemSteelCreoBucket steelCreoBucket = new ItemSteelCreoBucket();
	public static final ItemWoodenCreoBucket woodCreoBucket = new ItemWoodenCreoBucket();

	public static void init() {

		GameRegistry.registerItem(servo, servo.getRawUnlocalizedName());
		GameRegistry.registerItem(wireCore, wireCore.getRawUnlocalizedName());
		GameRegistry.registerItem(fuelFurnace, fuelFurnace.getRawUnlocalizedName());
		//GameRegistry.registerItem(woodChestCart, woodChestCart.getRawUnlocalizedName());
		
		registerFluidContainers();

	}
	
	public static void registerFluidContainers(){
		
		GameRegistry.registerItem(woodCreoBucket, woodCreoBucket.getRawUnlocalizedName());
		GameRegistry.registerItem(steelCreoBucket, steelCreoBucket.getRawUnlocalizedName());
		
		FluidContainerRegistry.FluidContainerData woodcontainer = new FluidContainerRegistry.FluidContainerData(Fluids.CREOSOTE.get(1000), woodCreoBucket.getItemStack(), woodCreoBucket.getContainerItem(null));
		FluidContainerRegistry.registerFluidContainer(woodcontainer);
		
		FluidContainerRegistry.FluidContainerData steelcontainer = new FluidContainerRegistry.FluidContainerData(Fluids.CREOSOTE.get(1000), steelCreoBucket.getItemStack(), steelCreoBucket.getContainerItem(null));
		FluidContainerRegistry.registerFluidContainer(steelcontainer);
		
	}

}
