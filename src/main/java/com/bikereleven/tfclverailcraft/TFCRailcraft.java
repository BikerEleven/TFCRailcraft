package com.bikereleven.tfclverailcraft;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

import com.bikereleven.tfclverailcraft.handler.ConfigurationHandler;
import com.bikereleven.tfclverailcraft.init.ModItems;
import com.bikereleven.tfclverailcraft.init.ModRecipes;
import com.bikereleven.tfclverailcraft.proxy.IProxy;
import com.bikereleven.tfclverailcraft.reference.Reference;
import com.bikereleven.tfclverailcraft.utility.LogHelper;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.MOD_VERSION, dependencies = Reference.MOD_DEPEND, guiFactory = Reference.GUI_FACTORY_CLASS)
public class TFCRailcraft {

	@Instance(value = Reference.MOD_ID)
	public static TFCRailcraft instance;

	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
	public static IProxy proxy;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		ConfigurationHandler.init(event.getSuggestedConfigurationFile());
		FMLCommonHandler.instance().bus().register(new ConfigurationHandler());

		ModItems.init();

		LogHelper.info("Finished PreInit phase");
	}

	@EventHandler
	public void load(FMLInitializationEvent event) {
		ModRecipes.init();
		
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {

	}

}
