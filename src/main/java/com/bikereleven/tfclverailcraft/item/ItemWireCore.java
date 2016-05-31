package com.bikereleven.tfclverailcraft.item;

import com.bioxx.tfc.api.Enums.EnumSize;
import com.bioxx.tfc.api.Enums.EnumWeight;

import net.minecraft.item.ItemStack;

public class ItemWireCore extends TLRItem {

	public ItemWireCore() {
		super();
		setUnlocalizedName("wirecore");
		maxStackSize = 16;
	}

	@Override
	public boolean canStack() {
		return true;
	}

	@Override
	public EnumSize getSize(ItemStack iStack) {
		return EnumSize.MEDIUM;
	}

	@Override
	public EnumWeight getWeight(ItemStack iStack) {
		return EnumWeight.HEAVY;
	}

}
