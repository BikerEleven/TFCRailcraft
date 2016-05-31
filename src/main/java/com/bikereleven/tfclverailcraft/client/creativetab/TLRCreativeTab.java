package com.bikereleven.tfclverailcraft.client.creativetab;

import com.bikereleven.tfclverailcraft.init.ModItems;
import com.bikereleven.tfclverailcraft.reference.Reference;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;


public class TLRCreativeTab {
	public static final CreativeTabs TLR_TAB = new CreativeTabs(Reference.MOD_ID){

		@Override
		public Item getTabIconItem() {
			return ModItems.servo;
		}
		
	};
}
