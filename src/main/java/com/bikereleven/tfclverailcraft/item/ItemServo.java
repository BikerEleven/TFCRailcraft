package com.bikereleven.tfclverailcraft.item;

import com.bioxx.tfc.api.Enums.EnumSize;
import com.bioxx.tfc.api.Enums.EnumWeight;

import net.minecraft.item.ItemStack;

public class ItemServo extends TLRItem {

	public ItemServo() {
		super();
		setUnlocalizedName("servo");
		
		maxStackSize = 32;
		size = EnumSize.SMALL;
		weight = EnumWeight.LIGHT;
	}

}
