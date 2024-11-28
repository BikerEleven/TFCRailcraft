package com.bikereleven.tfclverailcraft.entity;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class TLREntity extends Entity {

	public TLREntity(World world) {
		super(world);
	}

	@Override
	protected void entityInit() {		
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound p_70037_1_) {}

	@Override
	protected void writeEntityToNBT(NBTTagCompound p_70014_1_) {}
	
}
