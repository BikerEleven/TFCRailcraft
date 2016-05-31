package com.bikereleven.tfclverailcraft.item;

import java.util.List;

import com.bikereleven.tfclverailcraft.client.creativetab.TLRCreativeTab;
import com.bikereleven.tfclverailcraft.reference.Reference;
import com.bioxx.tfc.Items.ItemTerra;
import com.bioxx.tfc.api.Enums.EnumItemReach;
import com.bioxx.tfc.api.Enums.EnumSize;
import com.bioxx.tfc.api.Enums.EnumWeight;
import com.bioxx.tfc.api.Interfaces.ISize;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class TLRItem extends Item implements ISize {

	public TLRItem() {
		super();
		this.setCreativeTab(TLRCreativeTab.TLR_TAB);
		this.maxStackSize = 1;
	}

	@Override
	public String getUnlocalizedName() {
		return String.format("item.%s%s", Reference.RESOURCE_LOC_NAME,
				getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
	}

	@Override
	public String getUnlocalizedName(ItemStack iStack) {
		return String.format("item.%s%s", Reference.RESOURCE_LOC_NAME,
				getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister) {
		itemIcon = iconRegister
				.registerIcon(this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf('.') + 1));
	}

	protected String getUnwrappedUnlocalizedName(String unlocalizedName) {
		return unlocalizedName.substring(unlocalizedName.indexOf('.') + 1);
	}

	@Override
	public void addInformation(ItemStack is, EntityPlayer player, List arraylist, boolean flag) {
		ItemTerra.addSizeInformation(is, arraylist);
	}

	@Override
	public boolean canStack() {
		return false;
	}

	@Override
	public EnumItemReach getReach(ItemStack iStack) {
		return EnumItemReach.MEDIUM;
	}

	@Override
	public EnumSize getSize(ItemStack iStack) {
		return EnumSize.TINY;
	}

	@Override
	public EnumWeight getWeight(ItemStack iStack) {
		return EnumWeight.LIGHT;
	}

}
