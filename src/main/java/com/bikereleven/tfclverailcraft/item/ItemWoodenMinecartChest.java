package com.bikereleven.tfclverailcraft.item;

import java.util.List;

import com.bikereleven.tfclverailcraft.entity.EntityWoodenMinecartChest;
import com.bikereleven.tfclverailcraft.reference.Reference;
import com.bioxx.tfc.Items.ItemTerra;
import com.bioxx.tfc.api.Enums.EnumSize;
import com.bioxx.tfc.api.Enums.EnumWeight;

import net.minecraft.block.BlockRailBase;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemWoodenMinecartChest extends TLRItem {
	public int minecartType;

	public ItemWoodenMinecartChest() {
		super();
		maxStackSize = 1;
		minecartType = 1;
		setUnlocalizedName("woodchestcart");
		weight = EnumWeight.HEAVY;
		size = EnumSize.HUGE;
	}

	@Override
	public boolean onItemUse(ItemStack itemstack, EntityPlayer player, World world, int x, int y, int z, int side,
			float hitX, float hitY, float hitZ) {
		if (BlockRailBase.func_150051_a(world.getBlock(x, y, z))) {
			if (!world.isRemote) {
				world.spawnEntityInWorld(new EntityWoodenMinecartChest(world, x + 0.5F, y + 0.5F, z + 0.5F));
			}

			--itemstack.stackSize;
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void getSubItems(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
		par3List.add(new ItemStack(par1, 1, 0));
	}

}