package com.bikereleven.tfclverailcraft.item;

import com.bioxx.tfc.api.Enums.EnumSize;
import com.bioxx.tfc.api.Enums.EnumWeight;

public class ItemFuelFurnace extends TLRItem {
	
	public ItemFuelFurnace(){
		
		super();
		setUnlocalizedName("fuelfurnace");
		
		maxStackSize = 32;
		size = EnumSize.SMALL;
		weight = EnumWeight.LIGHT;
		
	}
	
}
