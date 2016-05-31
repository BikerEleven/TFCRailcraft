package com.bikereleven.tfclverailcraft.init;

import java.util.ArrayList;
import java.util.List;

import com.bikereleven.tfclverailcraft.handler.ConfigurationHandler;
import com.bikereleven.tfclverailcraft.utility.LogHelper;
import com.bioxx.tfc.api.TFCBlocks;
import com.bioxx.tfc.api.TFCItems;
import com.bioxx.tfc.api.Constant.Global;
import com.bioxx.tfc.api.Crafting.AnvilManager;
import com.bioxx.tfc.api.Crafting.AnvilRecipe;
import com.bioxx.tfc.api.Crafting.AnvilReq;
import com.bioxx.tfc.api.Crafting.BarrelManager;
import com.bioxx.tfc.api.Crafting.BarrelRecipe;
import com.bioxx.tfc.api.Crafting.PlanRecipe;
import com.bioxx.tfc.api.Enums.RuleEnum;

import cpw.mods.fml.common.registry.GameRegistry;
import mods.railcraft.common.items.RailcraftItem;
import mods.railcraft.common.items.RailcraftPartItems;
import mods.railcraft.common.items.RailcraftToolItems;
import mods.railcraft.common.plugins.forge.OreDictPlugin;
import mods.railcraft.common.blocks.RailcraftBlocks;
import mods.railcraft.common.blocks.aesthetics.cube.EnumCube;
import mods.railcraft.common.blocks.aesthetics.lantern.EnumLanternMetal;
import mods.railcraft.common.blocks.aesthetics.lantern.EnumLanternStone;
import mods.railcraft.common.blocks.machine.alpha.EnumMachineAlpha;
import mods.railcraft.common.blocks.machine.beta.EnumMachineBeta;
import mods.railcraft.common.blocks.machine.delta.EnumMachineDelta;
import mods.railcraft.common.blocks.machine.epsilon.EnumMachineEpsilon;
import mods.railcraft.common.blocks.machine.gamma.EnumMachineGamma;
import mods.railcraft.common.carts.EnumCart;
import mods.railcraft.common.fluids.Fluids;
import mods.railcraft.common.items.ItemPlate.EnumPlate;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class ModRecipes {

	public static void init() {

		updateOreDict();

		if (ConfigurationHandler.disableRailcraftRecipes) {
			removeRecipes();
		}

		overrideRecipes();

		// addAnvilRecipes(); This is now called by another event
		addBarrelRecipes();
		addCraftingRecipes();

	}

	private static void addBarrelRecipes() {

		ItemStack woodenTie = GameRegistry.findItemStack("Railcraft", "part.tie", 1);
		ItemStack creosoteWood = EnumCube.CREOSOTE_BLOCK.getItem();

		FluidStack creosote = new FluidStack(Fluids.CREOSOTE.get(), 1000);
		BarrelManager.getInstance()
				.addRecipe(new BarrelRecipe(new ItemStack(TFCItems.singlePlank, 1, OreDictionary.WILDCARD_VALUE),
						creosote, woodenTie, creosote).setMinTechLevel(0));
		BarrelManager.getInstance()
				.addRecipe(new BarrelRecipe(new ItemStack(TFCItems.logs, 1, OreDictionary.WILDCARD_VALUE), creosote,
						creosoteWood, creosote).setMinTechLevel(0));

	}

	public static void addAnvilRecipes() {

		AnvilManager manager = AnvilManager.getInstance();

		manager.addPlan("standardrail", new PlanRecipe(
				new RuleEnum[] { RuleEnum.PUNCHLAST, RuleEnum.PUNCHSECONDFROMLAST, RuleEnum.UPSETTHIRDFROMLAST }));
		manager.addPlan("advrail", new PlanRecipe(
				new RuleEnum[] { RuleEnum.PUNCHLAST, RuleEnum.PUNCHSECONDFROMLAST, RuleEnum.UPSETTHIRDFROMLAST }));
		manager.addPlan("hsrail", new PlanRecipe(
				new RuleEnum[] { RuleEnum.PUNCHLAST, RuleEnum.PUNCHSECONDFROMLAST, RuleEnum.UPSETTHIRDFROMLAST }));
		manager.addPlan("steelrail", new PlanRecipe(
				new RuleEnum[] { RuleEnum.PUNCHLAST, RuleEnum.PUNCHSECONDFROMLAST, RuleEnum.UPSETTHIRDFROMLAST }));
		manager.addPlan("electricrail", new PlanRecipe(
				new RuleEnum[] { RuleEnum.PUNCHLAST, RuleEnum.PUNCHSECONDFROMLAST, RuleEnum.UPSETTHIRDFROMLAST }));
		manager.addPlan("rebar", new PlanRecipe(new RuleEnum[] { RuleEnum.DRAWLAST, RuleEnum.ANY, RuleEnum.ANY }));
		manager.addPlan("wire", new PlanRecipe(
				new RuleEnum[] { RuleEnum.DRAWLAST, RuleEnum.HITSECONDFROMLAST, RuleEnum.UPSETTHIRDFROMLAST }));

		// Rails
		manager.addRecipe(
				new AnvilRecipe(new ItemStack(TFCItems.wroughtIronIngot), new ItemStack(TFCItems.wroughtIronIngot),
						"standardrail", false, AnvilReq.WROUGHTIRON, RailcraftItem.rail.getStack(8)));
		manager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.goldSheet), RailcraftItem.rail.getStack(), "advrail",
				false, AnvilReq.WROUGHTIRON, RailcraftItem.rail.getStack(8, 1)));
		manager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.steelIngot), new ItemStack(TFCItems.goldIngot),
				"hsrail", false, AnvilReq.WROUGHTIRON, RailcraftItem.rail.getStack(8, 3)));
		manager.addRecipe(
				new AnvilRecipe(new ItemStack(TFCItems.wroughtIronIngot), new ItemStack(TFCItems.redSteelIngot),
						"steelrail", false, AnvilReq.REDSTEEL, RailcraftItem.rail.getStack(8, 4)));
		manager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.copperSheet), RailcraftItem.rail.getStack(),
				"electricrail", false, AnvilReq.WROUGHTIRON, RailcraftItem.rail.getStack(8, 5)));
		// Rebar
		manager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.bronzeIngot), null, "rebar", false, AnvilReq.BRONZE,
				RailcraftItem.rebar.getStack(2)));
		manager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.bismuthBronzeIngot), null, "rebar", false,
				AnvilReq.BRONZE, RailcraftItem.rebar.getStack(2)));
		manager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.blackBronzeIngot), null, "rebar", false,
				AnvilReq.BRONZE, RailcraftItem.rebar.getStack(2)));
		manager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.wroughtIronIngot), null, "rebar", false,
				AnvilReq.WROUGHTIRON, RailcraftItem.rebar.getStack(4)));
		manager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.steelIngot), null, "rebar", false, AnvilReq.STEEL,
				RailcraftItem.rebar.getStack(8)));

		// Wire Core
		manager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.copperIngot), new ItemStack(TFCItems.leadIngot),
				"wire", false, AnvilReq.COPPER, new ItemStack(ModItems.wireCore)));

	}

	// This is for our few items
	private static void addCraftingRecipes() {

		GameRegistry.addRecipe(
				new ShapedOreRecipe(new ItemStack(ModItems.fuelFurnace), "RRR", "R R", "RRR", 'R', "itemRock"));
		GameRegistry.addShapedRecipe(EnumMachineDelta.WIRE.getItem(8), " P ", "PWP", " P ", 'P',
				new ItemStack(Items.paper), 'W', new ItemStack(ModItems.wireCore));

	}

	// This is where we fixup the railcraft recipes
	private static void overrideRecipes() {

		GameRegistry.addShapedRecipe(EnumMachineAlpha.COKE_OVEN.getItem(), "SFS", "FSF", "SFS", 'S',
				new ItemStack(TFCBlocks.sand), 'F', new ItemStack(TFCItems.fireBrick, 1, 1));

		GameRegistry.addRecipe(new ShapedOreRecipe(EnumMachineAlpha.TANK_WATER.getItem(6), "WWW", "IMI", "WWW", 'W',
				"plankWood", 'I', "ingotIron", 'M', new ItemStack(TFCItems.mortar)));

		GameRegistry.addRecipe(new ShapedOreRecipe(EnumMachineAlpha.SMOKER.getItem(1), " T ", "RCR", "   ", 'T',
				new ItemStack(TFCBlocks.thatch), 'R', new ItemStack(Items.redstone), 'C',
				new ItemStack(Items.cauldron)));

		GameRegistry.addRecipe(new ShapedOreRecipe(EnumMachineBeta.BOILER_FIREBOX_FLUID.getItem(), "FFF", "FSF", "FHF",
				'F', new ItemStack(TFCItems.fireBrick, 1, 1), 'S', new ItemStack(TFCItems.powder, 1, 3), 'H',
				new ItemStack(ModItems.fuelFurnace)));
		GameRegistry.addRecipe(new ShapedOreRecipe(EnumMachineBeta.BOILER_FIREBOX_SOLID.getItem(), "PBP", "ISI", "PHP",
				'P', "plateSteel", 'B', "bucketEmpty", 'I', new ItemStack(Blocks.iron_bars), 'S',
				new ItemStack(TFCItems.powder, 1, 3), 'H', new ItemStack(ModItems.fuelFurnace)));

		GameRegistry.addRecipe(new ShapedOreRecipe(RailcraftItem.gear.getStack(), " N ", "NBN", " N ", 'N',
				"oreSmallGold", 'B', RailcraftItem.gear.getStack(1, 3)));
		GameRegistry.addRecipe(new ShapedOreRecipe(EnumMachineBeta.ENGINE_STEAM_HOBBY.getItem(1), "NNN", " G ", "WPW",
				'N', "oreSmallGold", 'G', new ItemStack(Blocks.glass), 'W', RailcraftItem.gear.getStack(), 'P',
				new ItemStack(Blocks.piston)));

		GameRegistry.addRecipe(new ShapedOreRecipe(EnumMachineAlpha.PERSONAL_ANCHOR.getItem(1), "GOG", "DED", "GOG",
				'G', "ingotGold", 'O', new ItemStack(TFCBlocks.stoneIgEx, 1, 1), 'D', "gemEmerald", 'E',
				new ItemStack(Items.ender_pearl)));
		GameRegistry.addRecipe(new ShapedOreRecipe(EnumMachineAlpha.WORLD_ANCHOR.getItem(1), "GOG", "DED", "GOG", 'G',
				"ingotGold", 'O', new ItemStack(TFCBlocks.stoneIgEx, 1, 1), 'D', "gemDiamond", 'E',
				new ItemStack(Items.ender_pearl)));
		GameRegistry.addRecipe(new ShapedOreRecipe(EnumMachineAlpha.PASSIVE_ANCHOR.getItem(1), "GOG", "DED", "GOG", 'G',
				"ingotGold", 'O', new ItemStack(TFCBlocks.stoneIgEx, 1, 1), 'D', "dyeCyan", 'E',
				new ItemStack(Items.ender_pearl)));
		GameRegistry.addRecipe(new ShapedOreRecipe(EnumMachineBeta.SENTINEL.getItem(1), " E ", " O ", "OGO", 'E',
				new ItemStack(Items.ender_pearl), 'O', new ItemStack(TFCBlocks.stoneIgEx, 1, 1), 'G', "ingotGold"));

		GameRegistry.addRecipe(new ShapedOreRecipe(EnumMachineBeta.VOID_CHEST.getItem(1), "BBB", "BEB", "BBB", 'B',
				new ItemStack(TFCBlocks.stoneIgEx, 1, 1), 'E', new ItemStack(Items.ender_pearl)));

		GameRegistry.addRecipe(new ShapedOreRecipe(RailcraftItem.tie.getStack(1, 1), " R ", "BBB", "   ", 'R',
				RailcraftItem.rebar.getStack(), 'B',
				new ItemStack(TFCItems.stoneBrick, 1, OreDictionary.WILDCARD_VALUE)));

		// Controller, Recevier, Signal
		GameRegistry.addRecipe(new ShapedOreRecipe(RailcraftItem.circuit.getStack(1, 0), " SW", "MGR", "WRL", 'S',
				new ItemStack(Items.repeater), 'W', new ItemStack(TFCItems.woolCloth), 'M',
				new ItemStack(TFCItems.mortar), 'G', "ingotGold", 'R', new ItemStack(Items.redstone), 'L', "dyeBlue"));
		GameRegistry.addRecipe(new ShapedOreRecipe(RailcraftItem.circuit.getStack(1, 1), " SW", "MGR", "WRL", 'S',
				new ItemStack(Items.repeater), 'W', new ItemStack(TFCItems.burlapCloth), 'M',
				new ItemStack(TFCItems.mortar), 'G', "ingotGold", 'R', new ItemStack(Items.redstone), 'L', "dyeBlue"));
		GameRegistry.addRecipe(new ShapedOreRecipe(RailcraftItem.circuit.getStack(1, 2), " SW", "MGR", "WRL", 'S',
				new ItemStack(Items.repeater), 'W', new ItemStack(TFCItems.silkCloth), 'M',
				new ItemStack(TFCItems.mortar), 'G', "ingotGold", 'R', new ItemStack(Items.redstone), 'L', "dyeBlue"));

	}

	private static void removeRecipes() {

		ArrayList<ItemStack> disabledItems = new ArrayList<ItemStack>();

		if (!ConfigurationHandler.powerMode) {
			disabledItems.add(EnumMachineAlpha.STEAM_OVEN.getItem(4));
			disabledItems.add(EnumMachineAlpha.ROCK_CRUSHER.getItem(4));
		}

		disabledItems.add(RailcraftToolItems.getSteelArmor());
		disabledItems.add(RailcraftToolItems.getSteelAxe());
		disabledItems.add(RailcraftToolItems.getSteelBoots());
		disabledItems.add(RailcraftToolItems.getSteelHelm());
		disabledItems.add(RailcraftToolItems.getSteelHoe());
		disabledItems.add(RailcraftToolItems.getSteelLegs());
		disabledItems.add(RailcraftToolItems.getSteelPickaxe());
		disabledItems.add(RailcraftToolItems.getSteelShears());
		disabledItems.add(RailcraftToolItems.getSteelShovel());
		disabledItems.add(RailcraftToolItems.getSteelSword());

		disabledItems.add(EnumMachineAlpha.BLAST_FURNACE.getItem(4));
		disabledItems.add(EnumMachineAlpha.COKE_OVEN.getItem());
		disabledItems.add(EnumMachineAlpha.FEED_STATION.getItem());
		disabledItems.add(EnumMachineAlpha.TRADE_STATION.getItem());
		disabledItems.add(EnumMachineBeta.METALS_CHEST.getItem());

		disabledItems.add(EnumMachineBeta.METALS_CHEST.getItem());

		for (EnumLanternMetal lantern : EnumLanternMetal.NAMES.values()) {
			disabledItems.add(lantern.getItem());
		}

		for (EnumLanternStone lantern : EnumLanternStone.NAMES.values()) {
			disabledItems.add(lantern.getItem());
		}

		disabledItems.add(EnumCart.WORK.getCartItem());

		try {
			List<IRecipe> recipes = CraftingManager.getInstance().getRecipeList();

			for (int i = 0; i < recipes.size(); i++) {
				IRecipe recipe = recipes.get(i);

				if (recipe != null) {
					ItemStack result = recipe.getRecipeOutput();
					if (result != null) {
						if (OreDictPlugin.isOreType("ingotSteel", result) && result.stackSize == 9) {
							recipes.remove(i--);
						} else {
							for (ItemStack badItem : disabledItems) {
								if (result.isItemEqual(badItem)) {
									recipes.remove(i--);
								}
							}
						}
					}
				}

			}
		} catch (Exception err) {
			LogHelper.error("Ecountered an error removing a recipe");
			err.printStackTrace();
		}

	}

	private static void updateOreDict() {

		OreDictionary.registerOre("chest", new ItemStack(TFCBlocks.chest, 1, OreDictionary.WILDCARD_VALUE));

		OreDictionary.registerOre("plateSteel", RailcraftItem.plate.getStack(EnumPlate.STEEL));
		OreDictionary.registerOre("plateIron", RailcraftItem.plate.getStack(EnumPlate.IRON));
		OreDictionary.registerOre("plateCopper", RailcraftItem.plate.getStack(EnumPlate.COPPER));
		OreDictionary.registerOre("plateTin", RailcraftItem.plate.getStack(EnumPlate.TIN));

	}

}
