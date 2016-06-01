package com.bikereleven.tfclverailcraft.item;

import com.bioxx.tfc.api.TFCItems;

import net.minecraft.item.ItemStack;

public class ItemSteelCreoBucket extends ItemWoodenCreoBucket {
	
	public ItemSteelCreoBucket(){
		
		super();
		setUnlocalizedName("steelcreobucket");
		container = new ItemStack(TFCItems.redSteelBucketEmpty);
		
	}
	
}
