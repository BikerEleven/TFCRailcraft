package com.bikereleven.tfclverailcraft.item;

import com.bioxx.tfc.api.Enums.EnumSize;
import com.bioxx.tfc.api.Enums.EnumWeight;

public class ItemFuelFurnace extends TLRItem {
	
	public ItemFuelFurnace(){
		
		super();
		setUnlocalizedName("fuelfurnace");
		
		maxStackSize = 1;
		size = EnumSize.LARGE;
		weight = EnumWeight.HEAVY;
		
	}
	
}
