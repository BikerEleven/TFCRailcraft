package com.bikereleven.tfclverailcraft.item;

import com.bioxx.tfc.api.Enums.EnumSize;
import com.bioxx.tfc.api.Enums.EnumWeight;

import net.minecraft.item.ItemStack;

public class ItemWireCore extends TLRItem {

	public ItemWireCore() {
		super();
		setUnlocalizedName("wirecore");
		
		maxStackSize = 16;
		size = EnumSize.MEDIUM;
		weight = EnumWeight.HEAVY;
	}

}
