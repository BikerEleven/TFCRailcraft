package com.bikereleven.tfclverailcraft.item;

import com.bioxx.tfc.api.Enums.EnumSize;
import com.bioxx.tfc.api.Enums.EnumWeight;

import net.minecraft.item.ItemStack;

public class ItemServo extends TLRItem {

	public ItemServo() {
		super();
		setUnlocalizedName("servo");
		maxStackSize = 32;
	}

	@Override
	public boolean canStack() {
		return true;
	}

	@Override
	public EnumSize getSize(ItemStack iStack) {
		return EnumSize.SMALL;
	}

	@Override
	public EnumWeight getWeight(ItemStack iStack) {
		return EnumWeight.LIGHT;
	}

}
