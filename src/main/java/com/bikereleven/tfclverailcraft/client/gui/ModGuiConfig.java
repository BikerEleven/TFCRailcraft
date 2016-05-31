package com.bikereleven.tfclverailcraft.client.gui;

import java.util.List;

import com.bikereleven.tfclverailcraft.handler.ConfigurationHandler;
import com.bikereleven.tfclverailcraft.reference.Reference;

import cpw.mods.fml.client.config.GuiConfig;
import cpw.mods.fml.client.config.IConfigElement;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;

public class ModGuiConfig extends GuiConfig {

	public ModGuiConfig(GuiScreen guiScreen) {
		super(guiScreen,
				new ConfigElement(ConfigurationHandler.config.getCategory(Configuration.CATEGORY_GENERAL))
						.getChildElements(),
				Reference.MOD_ID, false, true,
				GuiConfig.getAbridgedConfigPath(ConfigurationHandler.config.toString()));
	}

}
