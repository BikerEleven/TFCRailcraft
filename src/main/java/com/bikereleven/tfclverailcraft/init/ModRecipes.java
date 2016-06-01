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
import mods.railcraft.common.blocks.aesthetics.post.EnumPost;
import mods.railcraft.common.blocks.detector.EnumDetector;
import mods.railcraft.common.blocks.machine.alpha.EnumMachineAlpha;
import mods.railcraft.common.blocks.machine.beta.EnumMachineBeta;
import mods.railcraft.common.blocks.machine.delta.EnumMachineDelta;
import mods.railcraft.common.blocks.machine.epsilon.EnumMachineEpsilon;
import mods.railcraft.common.blocks.machine.gamma.EnumMachineGamma;
import mods.railcraft.common.blocks.signals.EnumSignal;
import mods.railcraft.common.blocks.tracks.EnumTrack;
import mods.railcraft.common.carts.EnumCart;
import mods.railcraft.common.core.Railcraft;
import mods.railcraft.common.fluids.Fluids;
import mods.railcraft.common.items.ItemPlate.EnumPlate;
import mods.railcraft.common.items.ItemRail.EnumRail;
import mods.railcraft.common.items.ItemRailbed.EnumRailbed;
import mods.railcraft.common.items.Metal;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

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

		FluidStack creosote = new FluidStack(Fluids.CREOSOTE.get(), 625);
		BarrelManager.getInstance()
				.addRecipe(new BarrelRecipe(new ItemStack(TFCItems.singlePlank, 1, OreDictionary.WILDCARD_VALUE),
						creosote, woodenTie, creosote).setMinTechLevel(0));
		BarrelManager.getInstance()
				.addRecipe(new BarrelRecipe(new ItemStack(TFCItems.logs, 1, OreDictionary.WILDCARD_VALUE), creosote,
						creosoteWood, creosote).setMinTechLevel(0));

	}

	public static void addAnvilRecipes() {

		AnvilManager manager = AnvilManager.getInstance();

		manager.addPlan("servo", new PlanRecipe(
				new RuleEnum[] { RuleEnum.UPSETANY, RuleEnum.HITSECONDFROMLAST, RuleEnum.BENDTHIRDFROMLAST }));
		manager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.wroughtIronIngot), new ItemStack(TFCItems.copperSheet),
				"servo", false, AnvilReq.COPPER, new ItemStack(ModItems.servo)));

		if (!ConfigurationHandler.powerMode) {

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
			manager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.wroughtIronIngot),
					new ItemStack(TFCItems.wroughtIronIngot), "standardrail", false, AnvilReq.WROUGHTIRON,
					RailcraftItem.rail.getStack(8, EnumRail.STANDARD)));
			manager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.goldSheet), RailcraftItem.rail.getStack(),
					"advrail", false, AnvilReq.WROUGHTIRON, RailcraftItem.rail.getStack(8, EnumRail.ADVANCED)));
			manager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.steelIngot), new ItemStack(TFCItems.goldIngot),
					"hsrail", false, AnvilReq.WROUGHTIRON, RailcraftItem.rail.getStack(8, EnumRail.SPEED)));
			manager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.wroughtIronIngot),
					new ItemStack(TFCItems.redSteelIngot), "steelrail", false, AnvilReq.REDSTEEL,
					RailcraftItem.rail.getStack(8, EnumRail.REINFORCED)));
			manager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.copperSheet), RailcraftItem.rail.getStack(),
					"electricrail", false, AnvilReq.WROUGHTIRON, RailcraftItem.rail.getStack(8, EnumRail.ELECTRIC)));
			// Rebar
			manager.addRecipe(new AnvilRecipe(new ItemStack(TFCItems.bronzeIngot), null, "rebar", false,
					AnvilReq.BRONZE, RailcraftItem.rebar.getStack(2)));
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

	}

	// This is for our few items
	private static void addCraftingRecipes() {

		GameRegistry.addRecipe(
				new ShapedOreRecipe(new ItemStack(ModItems.fuelFurnace), "RRR", "R R", "RRR", 'R', "itemRock"));
		GameRegistry.addShapedRecipe(EnumMachineDelta.WIRE.getItem(8), " P ", "PWP", " P ", 'P',
				new ItemStack(Items.paper), 'W', new ItemStack(ModItems.wireCore));

	}

	// This is where we override and fix the Railcraft recipes
	private static void overrideRecipes() {

		GameRegistry.addRecipe(new ShapelessOreRecipe(Metal.STEEL.getNugget(9), "ingotSteel"));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Items.gold_nugget, 9), "ingotGold"));

		GameRegistry.addRecipe(
				new ShapedOreRecipe(EnumPost.METAL_UNPAINTED.getItem(16), "III", " I ", "III", 'I', "ingotIron"));

		GameRegistry.addRecipe(new ShapedOreRecipe(EnumMachineAlpha.COKE_OVEN.getItem(), "SFS", "FSF", "SFS", 'S',
				"blockSand", 'F', new ItemStack(TFCItems.fireBrick, 1, 1)));

		GameRegistry.addRecipe(new ShapedOreRecipe(EnumMachineAlpha.TANK_WATER.getItem(6), "WWW", "IMI", "WWW", 'W',
				"plankWood", 'I', "ingotIron", 'M', new ItemStack(TFCItems.mortar)));

		GameRegistry.addRecipe(new ShapedOreRecipe(EnumMachineAlpha.SMOKER.getItem(1), " T ", "RCR", "   ", 'T',
				new ItemStack(TFCBlocks.thatch), 'R', new ItemStack(Items.redstone), 'C',
				new ItemStack(Items.cauldron)));

		GameRegistry.addRecipe(new ShapedOreRecipe(EnumMachineBeta.BOILER_FIREBOX_SOLID.getItem(), "FFF", "FSF", "FHF",
				'F', new ItemStack(TFCItems.fireBrick, 1, 1), 'S', new ItemStack(TFCItems.powder, 1, 3), 'H',
				new ItemStack(ModItems.fuelFurnace)));
		GameRegistry.addRecipe(new ShapedOreRecipe(EnumMachineBeta.BOILER_FIREBOX_FLUID.getItem(), "PBP", "ISI", "PHP",
				'P', "plateSteel", 'B', "bucketEmpty", 'I', new ItemStack(Blocks.iron_bars), 'S',
				new ItemStack(TFCItems.powder, 1, 3), 'H', new ItemStack(ModItems.fuelFurnace)));

		// Fix the steel(2) gear, gold(0), iron(1), bushing(3)
		GameRegistry.addRecipe(new ShapedOreRecipe(RailcraftItem.gear.getStack(1, 2), " N ", "NBN", " N ", 'N',
				"ingotSteel", 'B', RailcraftItem.gear.getStack(1, 3)));

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

		// Fixes the stone tie
		GameRegistry.addRecipe(new ShapedOreRecipe(RailcraftItem.tie.getStack(1, 1), " R ", "BBB", "   ", 'R',
				RailcraftItem.rebar.getStack(), 'B',
				new ItemStack(TFCItems.stoneBrick, 1, OreDictionary.WILDCARD_VALUE)));

		// Controller, Receiver, Signal circuits
		GameRegistry.addRecipe(new ShapedOreRecipe(RailcraftItem.circuit.getStack(1, 0), " SW", "MGR", "WRL", 'S',
				new ItemStack(Items.repeater), 'W', new ItemStack(TFCItems.woolCloth), 'M',
				new ItemStack(TFCItems.mortar), 'G', "ingotGold", 'R', new ItemStack(Items.redstone), 'L', "dyeBlue"));
		GameRegistry.addRecipe(new ShapedOreRecipe(RailcraftItem.circuit.getStack(1, 1), " SW", "MGR", "WRL", 'S',
				new ItemStack(Items.repeater), 'W', new ItemStack(TFCItems.burlapCloth), 'M',
				new ItemStack(TFCItems.mortar), 'G', "ingotGold", 'R', new ItemStack(Items.redstone), 'L', "dyeBlue"));
		GameRegistry.addRecipe(new ShapedOreRecipe(RailcraftItem.circuit.getStack(1, 2), " SW", "MGR", "WRL", 'S',
				new ItemStack(Items.repeater), 'W', new ItemStack(TFCItems.silkCloth), 'M',
				new ItemStack(TFCItems.mortar), 'G', "ingotGold", 'R', new ItemStack(Items.redstone), 'L', "dyeBlue"));

		GameRegistry.addRecipe(new ShapedOreRecipe(Items.comparator, " T ", "TQT", "SSS", 'T',
				new ItemStack(Blocks.redstone_torch), 'Q', "gemQuartz", 'S', "stone"));

		// Strengthened glass
		Block railGlass = GameRegistry.findBlock("Railcraft", "glass");
		if (railGlass != null)
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(railGlass), "GIG", "GSG", "GBG", 'G', "blockGlass",
					'I', "ingotIron", 'S', "dustSaltpeter", 'B', "bucketWater"));

		// Detectors
		GameRegistry.addRecipe(new ShapedOreRecipe(EnumDetector.EMPTY.getItem(), "BBB", "BPB", "BBB", 'B',
				"stoneBricks", 'P', new ItemStack(Blocks.stone_pressure_plate)));
		GameRegistry.addRecipe(new ShapedOreRecipe(EnumDetector.MOB.getItem(), "BBB", "BPB", "BBB", 'B',
				new ItemStack(TFCBlocks.thatch), 'P', new ItemStack(Blocks.stone_pressure_plate)));
		GameRegistry.addRecipe(new ShapedOreRecipe(EnumDetector.PLAYER.getItem(), "BBB", "BPB", "BBB", 'B',
				"stoneSmooth", 'P', new ItemStack(Blocks.stone_pressure_plate)));
		GameRegistry.addRecipe(new ShapedOreRecipe(EnumDetector.EXPLOSIVE.getItem(), "BBB", "BPB", "BBB", 'B',
				new ItemStack(Items.gunpowder), 'P', new ItemStack(Blocks.stone_pressure_plate)));
		GameRegistry.addRecipe(new ShapedOreRecipe(EnumDetector.ANIMAL.getItem(), "BBB", "BPB", "BBB", 'B', "logWood",
				'P', new ItemStack(Blocks.stone_pressure_plate)));
		GameRegistry.addRecipe(new ShapedOreRecipe(EnumDetector.TANK.getItem(), "BBB", "BPB", "BBB", 'B',
				new ItemStack(TFCItems.fireBrick, 1, 1), 'P', new ItemStack(Blocks.stone_pressure_plate)));
		GameRegistry.addRecipe(new ShapedOreRecipe(EnumDetector.AGE.getItem(), "BBB", "BPB", "BBB", 'B', "woodLumber",
				'P', new ItemStack(Blocks.stone_pressure_plate)));
		GameRegistry.addRecipe(new ShapedOreRecipe(EnumDetector.TRAIN.getItem(), "BBB", "BPB", "BBB", 'B',
				new ItemStack(TFCItems.wroughtIronIngot), 'P', new ItemStack(Blocks.stone_pressure_plate)));
		GameRegistry.addRecipe(new ShapedOreRecipe(EnumDetector.SHEEP.getItem(), "BBB", "BPB", "BBB", 'B',
				new ItemStack(TFCItems.woolCloth), 'P', new ItemStack(Blocks.stone_pressure_plate)));
		GameRegistry.addRecipe(new ShapedOreRecipe(EnumDetector.LOCOMOTIVE.getItem(), "BBB", "BPB", "BBB", 'B',
				new ItemStack(TFCBlocks.fireBrick), 'P', new ItemStack(Blocks.stone_pressure_plate)));
		GameRegistry.addRecipe(new ShapedOreRecipe(EnumDetector.ROUTING.getItem(2), "BBB", "BPB", "BBB", 'B',
				new ItemStack(TFCItems.wroughtIronSheet), 'P', new ItemStack(Blocks.stone_pressure_plate)));

		GameRegistry.addRecipe(new ShapedOreRecipe(EnumMachineGamma.ITEM_LOADER_ADVANCED.getItem(), "IRI", "RDR", "ISI",
				'I', "ingotSteel", 'R', new ItemStack(Items.redstone), 'D', EnumMachineGamma.ITEM_LOADER.getItem(), 'S',
				"itemShovelSteel"));

		GameRegistry.addRecipe(new ShapedOreRecipe(EnumMachineGamma.ITEM_UNLOADER_ADVANCED.getItem(), "IRI", "RDR",
				"ISI", 'I', "ingotSteel", 'R', new ItemStack(Items.redstone), 'D',
				EnumMachineGamma.ITEM_UNLOADER.getItem(), 'S', "itemShovelSteel"));

		GameRegistry.addRecipe(new ShapedOreRecipe(EnumMachineEpsilon.ENGRAVING_BENCH.getItem(), "TSB", "SWS", "PSP",
				'T', "itemPickRedSteel", 'S', "plateSteel", 'B', new ItemStack(Items.book), 'W',
				new ItemStack(Blocks.crafting_table), 'P', new ItemStack(Blocks.piston)));

		Item railGoggles = GameRegistry.findItem("Railcraft", "armor.goggles");
		if (railGoggles != null)
			GameRegistry.addRecipe(new ShapedOreRecipe(railGoggles, "GRG", "I I", "LLL", 'G', "paneGlassColorless", 'R',
					RailcraftItem.circuit.getStack(1, 1), 'I', "ingotSteel", 'L', "materialLeather"));

		// Carts
		GameRegistry.addRecipe(new ShapedOreRecipe(EnumCart.TRACK_RELAYER.getCartItem(), "DLD", "SBS", "PCP", 'D',
				"dyeYellow", 'L', new ItemStack(TFCBlocks.oilLamp, 1, OreDictionary.WILDCARD_VALUE), 'S', "dustSulfur",
				'B', Metal.STEEL.getBlock(), 'P', "itemPickRedSteel", 'C', EnumCart.BASIC.getCartItem()));

		GameRegistry.addRecipe(new ShapedOreRecipe(EnumCart.UNDERCUTTER.getCartItem(), "DLD", "SBS", "PCP", 'D',
				"dyeYellow", 'L', new ItemStack(TFCBlocks.oilLamp, 1, OreDictionary.WILDCARD_VALUE), 'S',
				new ItemStack(Blocks.piston), 'B', Metal.STEEL.getBlock(), 'P', "itemShovelRedSteel", 'C',
				EnumCart.BASIC.getCartItem()));

		GameRegistry.addRecipe(new ShapedOreRecipe(EnumCart.TRACK_LAYER.getCartItem(), "DLD", "SBS", "PCP", 'D',
				"dyeYellow", 'L', new ItemStack(TFCBlocks.oilLamp, 1, OreDictionary.WILDCARD_VALUE), 'S',
				new ItemStack(ModItems.servo), 'B', Metal.STEEL.getBlock(), 'P', new ItemStack(Blocks.dispenser), 'C',
				EnumCart.BASIC.getCartItem()));

		Item railCrowbar = GameRegistry.findItem("Railcraft", "tool.crowbar");
		if (railCrowbar != null)
			GameRegistry.addRecipe(new ShapedOreRecipe(EnumCart.TRACK_REMOVER.getCartItem(), "DLD", "SBS", "PCP", 'D',
					"dyeYellow", 'L', new ItemStack(TFCBlocks.oilLamp, 1, OreDictionary.WILDCARD_VALUE), 'S',
					new ItemStack(Blocks.piston), 'B', Metal.STEEL.getBlock(), 'P', new ItemStack(railCrowbar), 'C',
					EnumCart.BASIC.getCartItem()));

		GameRegistry.addRecipe(new ShapedOreRecipe(EnumCart.CARGO.getCartItem(), "B  ", "C  ", "   ", 'B', "barrelWood",
				'C', EnumCart.BASIC.getCartItem()));

		GameRegistry.addRecipe(new ShapedOreRecipe(EnumCart.LOCO_ELECTRIC.getCartItem(), "LP ", "PFP", "GCG", 'L',
				new ItemStack(TFCBlocks.oilLamp, 1, OreDictionary.WILDCARD_VALUE), 'P', "plateSteel", 'F',
				EnumMachineEpsilon.ELECTRIC_FEEDER.getItem(), 'G', RailcraftItem.gear.getStack(1, 2), 'C',
				EnumCart.BASIC.getCartItem()));

		GameRegistry.addRecipe(new ShapedOreRecipe(EnumCart.BORE.getCartItem(), "SMS", "FMF", " C ", 'S',
				Metal.STEEL.getBlock(), 'M', EnumCart.BASIC.getCartItem(), 'F', new ItemStack(ModItems.fuelFurnace),
				'C', EnumCart.CARGO.getCartItem()));

		// Replacing plates in recipes
		GameRegistry.addRecipe(new ShapedOreRecipe(EnumMachineBeta.BOILER_TANK_LOW_PRESSURE.getItem(), "P  ", "P  ",
				"   ", 'P', "plateIron"));
		GameRegistry.addRecipe(new ShapedOreRecipe(EnumMachineBeta.BOILER_TANK_HIGH_PRESSURE.getItem(), "P  ", "P  ",
				"   ", 'P', "plateSteel"));

		// Iron Tank
		GameRegistry.addRecipe(new ShapedOreRecipe(EnumMachineBeta.TANK_IRON_GAUGE.getItem(8), "GPG", "PGP", "GPG", 'P',
				"plateIron", 'G', "paneGlassColorless"));
		GameRegistry.addRecipe(new ShapedOreRecipe(EnumMachineBeta.TANK_IRON_VALVE.getItem(8), "BPB", "PLP", "BPB", 'P',
				"plateIron", 'B', new ItemStack(Blocks.iron_bars), 'L', new ItemStack(Blocks.lever)));
		GameRegistry.addRecipe(
				new ShapedOreRecipe(EnumMachineBeta.TANK_IRON_WALL.getItem(8), "PP ", "PP ", "   ", 'P', "plateIron"));
		// Steel Tank
		GameRegistry.addRecipe(new ShapedOreRecipe(EnumMachineBeta.TANK_STEEL_GAUGE.getItem(8), "GPG", "PGP", "GPG",
				'P', "plateSteel", 'G', "paneGlassColorless"));
		GameRegistry.addRecipe(new ShapedOreRecipe(EnumMachineBeta.TANK_STEEL_VALVE.getItem(8), "BPB", "PLP", "BPB",
				'P', "plateSteel", 'B', new ItemStack(Blocks.iron_bars), 'L', new ItemStack(Blocks.lever)));
		GameRegistry.addRecipe(new ShapedOreRecipe(EnumMachineBeta.TANK_STEEL_WALL.getItem(8), "PP ", "PP ", "   ", 'P',
				"plateSteel"));

		GameRegistry.addRecipe(new ShapedOreRecipe(EnumMachineBeta.ENGINE_STEAM_LOW.getItem(), "SSS", " G ", "IPI", 'S',
				"plateIron", 'G', "blockGlassColorless", 'I', RailcraftItem.gear.getStack(1, 1), 'P',
				new ItemStack(Blocks.piston)));

		GameRegistry.addRecipe(new ShapedOreRecipe(EnumMachineBeta.ENGINE_STEAM_HIGH.getItem(), "SSS", " G ", "IPI",
				'S', "plateSteel", 'G', "blockGlassColorless", 'I', RailcraftItem.gear.getStack(1, 2), 'P',
				new ItemStack(Blocks.piston)));

		//Now i'm replaceing glowstone lamps and signal lamps with oil lamps
		GameRegistry.addRecipe(new ShapedOreRecipe(EnumTrack.LOCOMOTIVE.getItem(16), "RLR", "RTR", "RLR", 'R',
				RailcraftItem.rail.getStack(), 'L', new ItemStack(TFCBlocks.oilLamp, 1, OreDictionary.WILDCARD_VALUE),
				'T', RailcraftItem.railbed.getStack()));

		GameRegistry.addRecipe(new ShapedOreRecipe(EnumSignal.BLOCK_SIGNAL.getItem(), "LRI", " SI", "   ", 'I',
				"ingotIron", 'L', new ItemStack(TFCBlocks.oilLamp, 1, OreDictionary.WILDCARD_VALUE), 'R',
				RailcraftItem.circuit.getStack(1, 2), 'S', "dyeBlack"));

		GameRegistry.addRecipe(new ShapedOreRecipe(EnumSignal.DUAL_HEAD_BLOCK_SIGNAL.getItem(), "LRI", " SI", "LCI",
				'I', "ingotIron", 'L', new ItemStack(TFCBlocks.oilLamp, 1, OreDictionary.WILDCARD_VALUE), 'R',
				RailcraftItem.circuit.getStack(1, 2), 'S', "dyeBlack", 'C', RailcraftItem.circuit.getStack(1, 1)));

		GameRegistry.addRecipe(new ShapedOreRecipe(EnumSignal.DISTANT_SIGNAL.getItem(), "LRI", " SI", "   ", 'I',
				"ingotIron", 'L', new ItemStack(TFCBlocks.oilLamp, 1, OreDictionary.WILDCARD_VALUE), 'R',
				RailcraftItem.circuit.getStack(1, 1), 'S', "dyeBlack"));

		GameRegistry.addRecipe(new ShapedOreRecipe(EnumSignal.DUAL_HEAD_DISTANT_SIGNAL.getItem(), "LCI", " SI", "LCI",
				'I', "ingotIron", 'L', new ItemStack(TFCBlocks.oilLamp, 1, OreDictionary.WILDCARD_VALUE), 'S',
				"dyeBlack", 'C', RailcraftItem.circuit.getStack(1, 1)));

		//Fixing plate ore dicts again
		GameRegistry.addRecipe(new ShapedOreRecipe(EnumMachineEpsilon.ELECTRIC_FEEDER.getItem(), "PIP", "III", "PIP",
				'P', "plateTin", 'I', "ingotCopper"));

		GameRegistry.addRecipe(new ShapedOreRecipe(EnumMachineEpsilon.FLUX_TRANSFORMER.getItem(2), "PIP", "IBI", "PIP",
				'P', "plateCopper", 'I', "ingotGold", 'B', new ItemStack(Blocks.redstone_block)));

		GameRegistry.addRecipe(new ShapedOreRecipe(EnumMachineEpsilon.FORCE_TRACK_EMITTER.getItem(1), "PIP", "IBI",
				"PIP", 'P', "plateTin", 'I', "ingotCopper", 'B', "gemExquisiteDiamond"));

		Block railFrame = GameRegistry.findBlock("Railcraft", "frame");
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(railFrame, 6), "PPP", "R R", "RRR", 'P', "plateIron",
				'R', RailcraftItem.rebar.getStack(1)));

		GameRegistry.addRecipe(new ShapedOreRecipe(EnumTrack.SLOW_BOOSTER.getItem(16), "R R", "GBG", "RDR", 'R',
				RailcraftItem.rail.getStack(1, EnumRail.WOOD), 'G', "ingotGold", 'B', RailcraftItem.railbed.getStack(),
				'D', new ItemStack(Items.redstone)));
		
		//And switch the overalls to use wool cloth
		GameRegistry.addRecipe(new ShapedOreRecipe(RailcraftToolItems.getOveralls(), "WWW", "WBW", "W W", 'W',
				new ItemStack(TFCItems.woolCloth), 'B', "dyeBlue"));

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

		disabledItems.add(Metal.COPPER.getIngot(9)); // Blocks to ingots
		disabledItems.add(Metal.TIN.getIngot(9));
		disabledItems.add(Metal.LEAD.getIngot(9));
		disabledItems.add(Metal.STEEL.getIngot(9));
		disabledItems.add(new ItemStack(Items.gold_ingot)); // Nuggets to ingot

		disabledItems.add(EnumMachineAlpha.BLAST_FURNACE.getItem(4));
		disabledItems.add(EnumMachineAlpha.COKE_OVEN.getItem());
		disabledItems.add(EnumMachineAlpha.FEED_STATION.getItem());
		disabledItems.add(EnumMachineAlpha.TRADE_STATION.getItem());
		disabledItems.add(EnumMachineBeta.METALS_CHEST.getItem());

		disabledItems.add(GameRegistry.findItemStack("Railcraft", "anvil", 1));

		for (EnumLanternMetal lantern : EnumLanternMetal.NAMES.values()) {
			disabledItems.add(lantern.getItem());
		}

		for (EnumLanternStone lantern : EnumLanternStone.NAMES.values()) {
			disabledItems.add(lantern.getItem());
		}

		disabledItems.add(EnumCart.WORK.getCartItem());

		
		//After we build the list we will traverse it
		try {
			List<IRecipe> recipes = CraftingManager.getInstance().getRecipeList();

			for (int i = 0; i < recipes.size(); i++) {
				IRecipe recipe = recipes.get(i);

				if (recipe != null) {
					ItemStack result = recipe.getRecipeOutput();
					if (result != null) {
						for (ItemStack badItem : disabledItems) {
							if (result.isItemEqual(badItem)) {
								recipes.remove(i--);
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

		OreDictionary.registerOre("gemQuartz", new ItemStack(TFCItems.gemAgate, 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("gemQuartz", new ItemStack(TFCItems.gemAmethyst, 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("gemQuartz", new ItemStack(TFCItems.gemBeryl, 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("gemQuartz", new ItemStack(TFCItems.gemGarnet, 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("gemQuartz", new ItemStack(TFCItems.gemJade, 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("gemQuartz", new ItemStack(TFCItems.gemJasper, 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("gemQuartz", new ItemStack(TFCItems.gemOpal, 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("gemQuartz", new ItemStack(TFCItems.gemRuby, 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("gemQuartz", new ItemStack(TFCItems.gemSapphire, 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("gemQuartz", new ItemStack(TFCItems.gemTopaz, 1, OreDictionary.WILDCARD_VALUE));
		OreDictionary.registerOre("gemQuartz", new ItemStack(TFCItems.gemTourmaline, 1, OreDictionary.WILDCARD_VALUE));

		OreDictionary.registerOre("plateSteel", RailcraftItem.plate.getStack(EnumPlate.STEEL));
		OreDictionary.registerOre("plateIron", RailcraftItem.plate.getStack(EnumPlate.IRON));
		OreDictionary.registerOre("plateCopper", RailcraftItem.plate.getStack(EnumPlate.COPPER));
		OreDictionary.registerOre("plateTin", RailcraftItem.plate.getStack(EnumPlate.TIN));

	}

}
