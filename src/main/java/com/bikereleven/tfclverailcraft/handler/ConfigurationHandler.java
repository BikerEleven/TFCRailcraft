package com.bikereleven.tfclverailcraft.handler;

import java.io.File;
import java.io.IOException;

import com.bikereleven.tfclverailcraft.reference.Reference;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.common.config.Configuration;

public class ConfigurationHandler {

	public static Configuration config;
	public static Boolean powerMode = false;
	public static Boolean disableRailcraftRecipes = true;

	public static void init(File configFile) {

		if (config == null) {
			config = new Configuration(configFile);
			loadConfiguration();
		}

	}

	@SubscribeEvent
	public void onConfigChangedEvent(ConfigChangedEvent event) {
		if (event.modID.equalsIgnoreCase(Reference.MOD_ID)) {
			loadConfiguration();
		}
	}

	private static void loadConfiguration() {
		// Load config stuffs

		powerMode = config.getBoolean("powerMode", Configuration.CATEGORY_GENERAL, false,
				"If this is enabled then you are required to use the rolling machine.");
		disableRailcraftRecipes = config.getBoolean("disableRailcraftRecipes", Configuration.CATEGORY_GENERAL, true,
				"This will disable railcraft recipes that disrupt terrafirmacraft progression");

		if (config.hasChanged()) {
			config.save();
		}
	}

}
