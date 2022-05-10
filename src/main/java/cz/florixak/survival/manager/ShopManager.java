package cz.florixak.survival.manager;

import cz.florixak.survival.Survival;
import cz.florixak.survival.config.ConfigType;
import cz.florixak.survival.utility.TextUtil;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class ShopManager {

    private FileConfiguration shop;

    private String shop_n;
    private int shop_s;
    private String weapons_n;
    private int weapons_s;
    private String armor_n;
    private int armor_s;
    private String blocks_n;
    private int blocks_s;
    private String food_n;
    private int food_s;
    private String spawner_n;
    private int spawner_s;
    private String garden_n;
    private int garden_s;
    private String other_n;
    private int other_s;

    private String prize;
    private double prize_int;

    public ShopManager(){}

//    public void openShop(Player p){
//
//        shop = Survival.plugin.getConfigManager().getFile(ConfigType.SHOP).getConfig();
//
//        shop_n = shop.getString("shop.gui.name");
//        shop_s = shop.getInt("shop.gui.slots");
//
//        weapons_n = shop.getString("shop.sections.weapons.gui.name");
//        armor_n = shop.getString("shop.sections.armor.gui.name");
//        blocks_n = shop.getString("shop.sections.blocks.gui.name");
//        food_n = shop.getString("shop.sections.food.gui.name");
//        spawner_n = shop.getString("shop.sections.spawner.gui.name");
//        garden_n = shop.getString("shop.sections.garden.gui.name");
//        other_n = shop.getString("shop.sections.other.gui.name");
//
//        Inventory shop = Bukkit.createInventory(null, shop_s, TextUtil.color(shop_n));
//
//        ItemStack vypln = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
//
//        ItemStack weapons = new ItemStack(Material.IRON_SWORD);
//        ItemStack armor = new ItemStack(Material.DIAMOND_CHESTPLATE);
//        ItemStack blocks = new ItemStack(Material.GRASS_BLOCK);
//        ItemStack food = new ItemStack(Material.COOKED_BEEF);
//        ItemStack spawner = new ItemStack(Material.SPAWNER);
//        ItemStack garden = new ItemStack(Material.OAK_SAPLING);
//        ItemStack other = new ItemStack(Material.SADDLE);
//
//        Survival.plugin.getItemManager().setItemMeta(vypln, " ", 1);
//
//        Survival.plugin.getItemManager().setItemMeta(blocks, blocks_n, 1);
//        Survival.plugin.getItemManager().addLore(blocks, null,  null, null);
//        Survival.plugin.getItemManager().setItemMeta(weapons, weapons_n, 1);
//        Survival.plugin.getItemManager().removeAttributes(weapons);
//        Survival.plugin.getItemManager().addLore(weapons, null, null, null);
//        Survival.plugin.getItemManager().setItemMeta(armor, armor_n, 1);
//        Survival.plugin.getItemManager().removeAttributes(armor);
//        Survival.plugin.getItemManager().addLore(armor, null, null, null);
//        Survival.plugin.getItemManager().setItemMeta(food, food_n, 1);
//        Survival.plugin.getItemManager().addLore(food, null, null, null);
//        Survival.plugin.getItemManager().setItemMeta(spawner, spawner_n, 1);
//        Survival.plugin.getItemManager().addLore(spawner, null, null, null);
//        Survival.plugin.getItemManager().setItemMeta(garden, garden_n, 1);
//        Survival.plugin.getItemManager().addLore(garden, null, null, null);
//        Survival.plugin.getItemManager().setItemMeta(other, other_n, 1);
//        Survival.plugin.getItemManager().addLore(other, null, null, null);
//
//
//        ItemStack[] menu_items = {
//                vypln, vypln, vypln, vypln, vypln, vypln, vypln, vypln, vypln,
//                vypln, blocks, vypln, weapons, vypln, armor, vypln, food, vypln,
//                vypln, vypln, vypln, vypln, vypln, vypln, vypln, vypln, vypln,
//                vypln, garden, vypln, spawner, vypln, other, vypln, vypln, vypln,
//                vypln, vypln, vypln, vypln, vypln, vypln, vypln, vypln, vypln
//        };
//        shop.setContents(menu_items);
//        p.openInventory(shop);
//    }

//    public void openBlocks1(Player p){
//        shop = Survival.plugin.getConfigManager().getFile(ConfigType.SHOP).getConfig();
//
//        blocks_n = shop.getString("shop.sections.blocks.gui.name");
//        blocks_s = shop.getInt("shop.sections.blocks.gui.slots");
//
//        prize = shop.getString("shop.prize");
//
//        Inventory blocks = Bukkit.createInventory(null, blocks_s, TextUtil.color(blocks_n + " - 1/7"));
//
//        ItemStack vypln = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
//        ItemStack next = new ItemStack(Material.LIME_BED);
//        ItemStack back = new ItemStack(Material.RED_BED);
//
//        ItemStack stone = new ItemStack(Material.STONE);
//        ItemStack granite = new ItemStack(Material.GRANITE);
//        ItemStack diorite = new ItemStack(Material.DIORITE);
//        ItemStack andesite = new ItemStack(Material.ANDESITE);
//        ItemStack deepslate = new ItemStack(Material.DEEPSLATE);
//        ItemStack cobbledDeepslate = new ItemStack(Material.COBBLED_DEEPSLATE);
//        ItemStack calcite = new ItemStack(Material.CALCITE);
//        ItemStack tuff = new ItemStack(Material.TUFF);
//        ItemStack dripstone = new ItemStack(Material.DRIPSTONE_BLOCK);
//        ItemStack grassBlock = new ItemStack(Material.GRASS_BLOCK);
//        ItemStack dirt = new ItemStack(Material.DIRT);
//        ItemStack coarseDirt = new ItemStack(Material.COARSE_DIRT);
//        ItemStack podzol = new ItemStack(Material.PODZOL);
//        ItemStack rootedDirt = new ItemStack(Material.ROOTED_DIRT);
//        ItemStack crimsonnylium = new ItemStack(Material.CRIMSON_NYLIUM);
//        ItemStack warpednylium = new ItemStack(Material.WARPED_NYLIUM);
//        ItemStack cobblestone = new ItemStack(Material.COBBLESTONE);
//        ItemStack bedrock = new ItemStack(Material.BEDROCK);
//        ItemStack sand = new ItemStack(Material.SAND);
//        ItemStack redSand = new ItemStack(Material.RED_SAND);
//        ItemStack gravel = new ItemStack(Material.GRAVEL);
//        ItemStack oakLog = new ItemStack(Material.OAK_LOG);
//        ItemStack spruceLog = new ItemStack(Material.SPRUCE_LOG);
//        ItemStack birchLog = new ItemStack(Material.BIRCH_LOG);
//        ItemStack jungleLog = new ItemStack(Material.JUNGLE_LOG);
//        ItemStack acaciaLog = new ItemStack(Material.ACACIA_LOG);
//        ItemStack darkLog = new ItemStack(Material.DARK_OAK_LOG);
//        ItemStack crimsonStem = new ItemStack(Material.CRIMSON_STEM);
//
//        Survival.plugin.getItemManager().setItemMeta(next, "&aDalší", 1);
//        Survival.plugin.getItemManager().setItemMeta(back, "&cZpátky", 1);
//        Survival.plugin.getItemManager().setItemMeta(vypln, " ", 1);
//
//        prize_int = shop.getDouble("shop.sections.blocks.contents.stone");
//        Survival.plugin.getItemManager().addLore(stone, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.granite");
//        Survival.plugin.getItemManager().addLore(granite, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.diorite");
//        Survival.plugin.getItemManager().addLore(diorite, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.andesite");
//        Survival.plugin.getItemManager().addLore(andesite, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.deepslate");
//        Survival.plugin.getItemManager().addLore(deepslate, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.cobbled_deepslate");
//        Survival.plugin.getItemManager().addLore(cobbledDeepslate, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.calcite");
//        Survival.plugin.getItemManager().addLore(calcite, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.tuff");
//        Survival.plugin.getItemManager().addLore(tuff, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.dripstone");
//        Survival.plugin.getItemManager().addLore(dripstone, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.grass_block");
//        Survival.plugin.getItemManager().addLore(grassBlock, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.dirt");
//        Survival.plugin.getItemManager().addLore(dirt, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.coarse_dirt");
//        Survival.plugin.getItemManager().addLore(coarseDirt, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.podzol");
//        Survival.plugin.getItemManager().addLore(podzol, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.rooted_dirt");
//        Survival.plugin.getItemManager().addLore(rootedDirt, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.crimson_nylium");
//        Survival.plugin.getItemManager().addLore(crimsonnylium, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.warped_nylium");
//        Survival.plugin.getItemManager().addLore(warpednylium, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.cobblestone");
//        Survival.plugin.getItemManager().addLore(cobblestone, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.bedrock");
//        Survival.plugin.getItemManager().addLore(bedrock, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.sand");
//        Survival.plugin.getItemManager().addLore(sand, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.red_sand");
//        Survival.plugin.getItemManager().addLore(redSand, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.gravel");
//        Survival.plugin.getItemManager().addLore(gravel, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.oak_log");
//        Survival.plugin.getItemManager().addLore(oakLog, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.spruce_log");
//        Survival.plugin.getItemManager().addLore(spruceLog, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.birch_log");
//        Survival.plugin.getItemManager().addLore(birchLog, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.jungle_log");
//        Survival.plugin.getItemManager().addLore(jungleLog, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.acacia_log");
//        Survival.plugin.getItemManager().addLore(acaciaLog, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.dark_oak_log");
//        Survival.plugin.getItemManager().addLore(darkLog, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.crimson_stem");
//        Survival.plugin.getItemManager().addLore(crimsonStem, " ", prize.replace("%prize%", "" + prize_int), null);
//
//        ItemStack[] menu_items = {
//                vypln, vypln, vypln, vypln, vypln, vypln, vypln, vypln, vypln,
//                vypln, stone, granite, diorite, andesite, deepslate, cobbledDeepslate, calcite, vypln,
//                vypln, tuff, dripstone, grassBlock, dirt, coarseDirt, podzol, rootedDirt, vypln,
//                vypln, crimsonnylium, warpednylium, cobblestone, bedrock, sand, redSand, gravel, vypln,
//                vypln, oakLog, spruceLog, birchLog, jungleLog, acaciaLog, darkLog, crimsonStem, vypln,
//                back, vypln, vypln, vypln, vypln, vypln, vypln, vypln, next
//        };
//        blocks.setContents(menu_items);
//        p.openInventory(blocks);
//    }
//    public void openBlocks2(Player p){
//        shop = Survival.plugin.getConfigManager().getFile(ConfigType.SHOP).getConfig();
//
//        blocks_n = shop.getString("shop.sections.blocks.gui.name");
//        blocks_s = shop.getInt("shop.sections.blocks.gui.slots");
//
//        prize = shop.getString("shop.prize");
//
//        Inventory blocks = Bukkit.createInventory(null, blocks_s, TextUtil.color(blocks_n + " - 2/7"));
//
//        ItemStack vypln = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
//        ItemStack next = new ItemStack(Material.LIME_BED);
//        ItemStack back = new ItemStack(Material.RED_BED);
//
//        ItemStack warpedStem = new ItemStack(Material.WARPED_STEM);
//        ItemStack sponge = new ItemStack(Material.SPONGE);
//        ItemStack glass = new ItemStack(Material.GLASS);
//        ItemStack tintedGlass = new ItemStack(Material.TINTED_GLASS);
//        ItemStack sandstone = new ItemStack(Material.SANDSTONE);
//        ItemStack chiseledStone = new ItemStack(Material.CHISELED_SANDSTONE);
//        ItemStack cutSandstone = new ItemStack(Material.CUT_SANDSTONE);
//        ItemStack whiteWool = new ItemStack(Material.WHITE_WOOL);
//        ItemStack orangeWool = new ItemStack(Material.ORANGE_WOOL);
//        ItemStack magentaWool = new ItemStack(Material.MAGENTA_WOOL);
//        ItemStack lightBlueWool = new ItemStack(Material.LIGHT_BLUE_WOOL);
//        ItemStack yellowWool = new ItemStack(Material.YELLOW_WOOL);
//        ItemStack limeWool = new ItemStack(Material.LIME_WOOL);
//        ItemStack pinkWool = new ItemStack(Material.PINK_WOOL);
//        ItemStack grayWool = new ItemStack(Material.GRAY_WOOL);
//        ItemStack lightGrayWool = new ItemStack(Material.LIGHT_GRAY_WOOL);
//        ItemStack cyanWool = new ItemStack(Material.CYAN_WOOL);
//        ItemStack purpleWool = new ItemStack(Material.PURPLE_WOOL);
//        ItemStack blueWool = new ItemStack(Material.BLUE_WOOL);
//        ItemStack brownWool = new ItemStack(Material.BROWN_WOOL);
//        ItemStack greenWool = new ItemStack(Material.GREEN_WOOL);
//        ItemStack redWool = new ItemStack(Material.RED_WOOL);
//        ItemStack blackWool = new ItemStack(Material.BLACK_WOOL);
//        ItemStack smoothQuartzBlock = new ItemStack(Material.SMOOTH_QUARTZ);
//        ItemStack smoothRedstone = new ItemStack(Material.SMOOTH_RED_SANDSTONE);
//        ItemStack smoothSandstone = new ItemStack(Material.SMOOTH_SANDSTONE);
//        ItemStack bricks = new ItemStack(Material.BRICKS);
//        ItemStack bookshelf = new ItemStack(Material.BOOKSHELF);
//
//        Survival.plugin.getItemManager().setItemMeta(next, "&aDalší", 1);
//        Survival.plugin.getItemManager().setItemMeta(back, "&cZpátky", 1);
//        Survival.plugin.getItemManager().setItemMeta(vypln, " ", 1);
//
//        prize_int = shop.getDouble("shop.sections.blocks.contents.warped_stem");
//        Survival.plugin.getItemManager().addLore(warpedStem, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.sponge");
//        Survival.plugin.getItemManager().addLore(sponge, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.glass");
//        Survival.plugin.getItemManager().addLore(glass, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.tinted_glass");
//        Survival.plugin.getItemManager().addLore(tintedGlass, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.sandstone");
//        Survival.plugin.getItemManager().addLore(sandstone, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.chiseled_sandstone");
//        Survival.plugin.getItemManager().addLore(chiseledStone, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.cut_sandstone");
//        Survival.plugin.getItemManager().addLore(cutSandstone, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.white_wool");
//        Survival.plugin.getItemManager().addLore(whiteWool, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.orange_wool");
//        Survival.plugin.getItemManager().addLore(orangeWool, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.magenta_wool");
//        Survival.plugin.getItemManager().addLore(magentaWool, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.light_blue_wool");
//        Survival.plugin.getItemManager().addLore(lightBlueWool, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.yellow_wool");
//        Survival.plugin.getItemManager().addLore(yellowWool, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.lime_wool");
//        Survival.plugin.getItemManager().addLore(limeWool, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.pink_wool");
//        Survival.plugin.getItemManager().addLore(pinkWool, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.gray_wool");
//        Survival.plugin.getItemManager().addLore(grayWool, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.light_gray_wool");
//        Survival.plugin.getItemManager().addLore(lightGrayWool, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.cyan_wool");
//        Survival.plugin.getItemManager().addLore(cyanWool, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.purple_wool");
//        Survival.plugin.getItemManager().addLore(purpleWool, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.blue_wool");
//        Survival.plugin.getItemManager().addLore(blueWool, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.brown_wool");
//        Survival.plugin.getItemManager().addLore(brownWool, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.green_wool");
//        Survival.plugin.getItemManager().addLore(greenWool, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.red_wool");
//        Survival.plugin.getItemManager().addLore(redWool, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.black_wool");
//        Survival.plugin.getItemManager().addLore(blackWool, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.smooth_quartz");
//        Survival.plugin.getItemManager().addLore(smoothQuartzBlock, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.smooth_redstone");
//        Survival.plugin.getItemManager().addLore(smoothRedstone, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.smooth_sandstone");
//        Survival.plugin.getItemManager().addLore(smoothSandstone, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.bricks");
//        Survival.plugin.getItemManager().addLore(bricks, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.bookshelf");
//        Survival.plugin.getItemManager().addLore(bookshelf, " ", prize.replace("%prize%", "" + prize_int), null);
//
//        ItemStack[] menu_items = {
//                vypln, vypln, vypln, vypln, vypln, vypln, vypln, vypln, vypln,
//                vypln, warpedStem, sponge, glass, tintedGlass, sandstone, chiseledStone, cutSandstone, vypln,
//                vypln, whiteWool, orangeWool, magentaWool, lightBlueWool, yellowWool, limeWool, pinkWool, vypln,
//                vypln, grayWool, lightGrayWool, cyanWool, purpleWool, blueWool, brownWool, greenWool, vypln,
//                vypln, redWool, blackWool, smoothQuartzBlock, smoothRedstone, smoothSandstone, bricks, bookshelf, vypln,
//                back, vypln, vypln, vypln, vypln, vypln, vypln, vypln, next
//        };
//        blocks.setContents(menu_items);
//        p.openInventory(blocks);
//    }
//    public void openBlocks3(Player p){
//        shop = Survival.plugin.getConfigManager().getFile(ConfigType.SHOP).getConfig();
//
//        blocks_n = shop.getString("shop.sections.blocks.gui.name");
//        blocks_s = shop.getInt("shop.sections.blocks.gui.slots");
//
//        prize = shop.getString("shop.prize");
//
//        Inventory blocks = Bukkit.createInventory(null, blocks_s, TextUtil.color(blocks_n  + " - 3/7"));
//
//        ItemStack vypln = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
//        ItemStack next = new ItemStack(Material.LIME_BED);
//        ItemStack back = new ItemStack(Material.RED_BED);
//
//        ItemStack mossyCobblestone = new ItemStack(Material.MOSSY_COBBLESTONE);
//        ItemStack obsidian = new ItemStack(Material.OBSIDIAN);
//        ItemStack purpurBlock = new ItemStack(Material.PURPUR_BLOCK);
//        ItemStack purpurPillar = new ItemStack(Material.PURPUR_PILLAR);
//        ItemStack ice = new ItemStack(Material.ICE);
//        ItemStack snowBlock = new ItemStack(Material.SNOW_BLOCK);
//        ItemStack clay = new ItemStack(Material.CLAY);
//        ItemStack pumpkin = new ItemStack(Material.PUMPKIN);
//        ItemStack carvedPumpkin = new ItemStack(Material.CARVED_PUMPKIN);
//        ItemStack jackOLantern = new ItemStack(Material.JACK_O_LANTERN);
//        ItemStack netherrack = new ItemStack(Material.NETHERRACK);
//        ItemStack soulSand = new ItemStack(Material.SOUL_SAND);
//        ItemStack soulSoil = new ItemStack(Material.SOUL_SOIL);
//        ItemStack basalt = new ItemStack(Material.BASALT);
//        ItemStack polishedBasalt = new ItemStack(Material.POLISHED_BASALT);
//        ItemStack smoothBasalt = new ItemStack(Material.SMOOTH_BASALT);
//        ItemStack glowstone = new ItemStack(Material.GLOWSTONE);
//        ItemStack stoneBricks = new ItemStack(Material.STONE_BRICKS);
//        ItemStack mossyStoneBricks = new ItemStack(Material.MOSSY_STONE_BRICKS);
//        ItemStack crackedStoneBricks = new ItemStack(Material.CRACKED_STONE_BRICKS);
//        ItemStack chiseledStoneBricks = new ItemStack(Material.CHISELED_STONE_BRICKS);
//        ItemStack deepslateBricks = new ItemStack(Material.DEEPSLATE_BRICKS);
//        ItemStack crackedDeepslateBricks = new ItemStack(Material.CRACKED_DEEPSLATE_BRICKS);
//        ItemStack deepslateTiles = new ItemStack(Material.DEEPSLATE_TILES);
//        ItemStack crackedDeepslateTiles = new ItemStack(Material.CRACKED_DEEPSLATE_TILES);
//        ItemStack chiseledDeepslate = new ItemStack(Material.CHISELED_DEEPSLATE);
//        ItemStack mycelium = new ItemStack(Material.MYCELIUM);
//        ItemStack netherBricks = new ItemStack(Material.NETHER_BRICKS);
//
//        Survival.plugin.getItemManager().setItemMeta(next, "&aDalší", 1);
//        Survival.plugin.getItemManager().setItemMeta(back, "&cZpátky", 1);
//        Survival.plugin.getItemManager().setItemMeta(vypln, " ", 1);
//
//        prize_int = shop.getDouble("shop.sections.blocks.contents.mossy_cobblestone");
//        Survival.plugin.getItemManager().addLore(mossyCobblestone, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.obsidian");
//        Survival.plugin.getItemManager().addLore(obsidian, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.purpur_block");
//        Survival.plugin.getItemManager().addLore(purpurBlock, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.purpur_pillar");
//        Survival.plugin.getItemManager().addLore(purpurPillar, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.ice");
//        Survival.plugin.getItemManager().addLore(ice, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.snow_block");
//        Survival.plugin.getItemManager().addLore(snowBlock, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.clay");
//        Survival.plugin.getItemManager().addLore(clay, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.pumpkin");
//        Survival.plugin.getItemManager().addLore(pumpkin, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.carved_pumpkin");
//        Survival.plugin.getItemManager().addLore(carvedPumpkin, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.jack_o_lantern");
//        Survival.plugin.getItemManager().addLore(jackOLantern, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.netherrack");
//        Survival.plugin.getItemManager().addLore(netherrack, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.soul_sand");
//        Survival.plugin.getItemManager().addLore(soulSand, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.soul_soil");
//        Survival.plugin.getItemManager().addLore(soulSoil, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.basalt");
//        Survival.plugin.getItemManager().addLore(basalt, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.polished_basalt");
//        Survival.plugin.getItemManager().addLore(polishedBasalt, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.smooth_basalt");
//        Survival.plugin.getItemManager().addLore(smoothBasalt, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.glowstone");
//        Survival.plugin.getItemManager().addLore(glowstone, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.stone_bricks");
//        Survival.plugin.getItemManager().addLore(stoneBricks, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.mossy_stone_bricks");
//        Survival.plugin.getItemManager().addLore(mossyStoneBricks, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.cracked_stone_bricks");
//        Survival.plugin.getItemManager().addLore(crackedStoneBricks, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.chiseled_stone_bricks");
//        Survival.plugin.getItemManager().addLore(chiseledStoneBricks, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.deepslate_bricks");
//        Survival.plugin.getItemManager().addLore(deepslateBricks, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.cracked_deepslate_bricks");
//        Survival.plugin.getItemManager().addLore(crackedDeepslateBricks, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.deepslate_tiles");
//        Survival.plugin.getItemManager().addLore(deepslateTiles, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.cracked_deepslate_tiles");
//        Survival.plugin.getItemManager().addLore(crackedDeepslateTiles, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.chiseled_deepslate");
//        Survival.plugin.getItemManager().addLore(chiseledDeepslate, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.mycelium");
//        Survival.plugin.getItemManager().addLore(mycelium, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.nether_bricks");
//        Survival.plugin.getItemManager().addLore(netherBricks, " ", prize.replace("%prize%", "" + prize_int), null);
//
//        ItemStack[] menu_items = {
//                vypln, vypln, vypln, vypln, vypln, vypln, vypln, vypln, vypln,
//                vypln, mossyCobblestone, obsidian, purpurBlock, purpurPillar, ice, snowBlock, clay, vypln,
//                vypln, pumpkin, carvedPumpkin, jackOLantern, netherrack, soulSand, soulSoil, basalt, vypln,
//                vypln, polishedBasalt, smoothBasalt, glowstone, stoneBricks, mossyStoneBricks, crackedStoneBricks, chiseledStoneBricks, vypln,
//                vypln, deepslateBricks, crackedDeepslateBricks, deepslateTiles, crackedDeepslateTiles, chiseledDeepslate, mycelium, netherBricks, vypln,
//                back, vypln, vypln, vypln, vypln, vypln, vypln, vypln, next
//        };
//        blocks.setContents(menu_items);
//        p.openInventory(blocks);
//    }
//    public void openBlocks4(Player p){
//        shop = Survival.plugin.getConfigManager().getFile(ConfigType.SHOP).getConfig();
//
//        blocks_n = shop.getString("shop.sections.blocks.gui.name");
//        blocks_s = shop.getInt("shop.sections.blocks.gui.slots");
//
//        prize = shop.getString("shop.prize");
//
//        Inventory blocks = Bukkit.createInventory(null, blocks_s, TextUtil.color(blocks_n + " - 4/7"));
//
//        ItemStack vypln = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
//        ItemStack next = new ItemStack(Material.LIME_BED);
//        ItemStack back = new ItemStack(Material.RED_BED);
//
//        ItemStack crackedNetherBricks = new ItemStack(Material.CRACKED_NETHER_BRICKS);
//        ItemStack chiseledNetherBricks = new ItemStack(Material.CHISELED_NETHER_BRICKS);
//        ItemStack endStone = new ItemStack(Material.END_STONE);
//        ItemStack endStoneBricks = new ItemStack(Material.END_STONE_BRICKS);
//        ItemStack chiseledQuartzBlock = new ItemStack(Material.CHISELED_QUARTZ_BLOCK);
//        ItemStack quartzBlock = new ItemStack(Material.QUARTZ_BLOCK);
//        ItemStack quartzBricks = new ItemStack(Material.QUARTZ_BRICKS);
//        ItemStack quartzPillar = new ItemStack(Material.QUARTZ_PILLAR);
//        ItemStack whiteTerracota = new ItemStack(Material.WHITE_TERRACOTTA);
//        ItemStack orangeTerracota = new ItemStack(Material.ORANGE_TERRACOTTA);
//        ItemStack magentaTerracota = new ItemStack(Material.MAGENTA_TERRACOTTA);
//        ItemStack lightBlueTerracota = new ItemStack(Material.LIGHT_BLUE_TERRACOTTA);
//        ItemStack yellowTerracota = new ItemStack(Material.YELLOW_TERRACOTTA);
//        ItemStack limeTerracota = new ItemStack(Material.LIME_TERRACOTTA);
//        ItemStack pinkTerracota = new ItemStack(Material.PINK_TERRACOTTA);
//        ItemStack grayTerracota = new ItemStack(Material.GRAY_TERRACOTTA);
//        ItemStack lightGrayTerracota = new ItemStack(Material.LIGHT_GRAY_TERRACOTTA);
//        ItemStack cyanTerracota = new ItemStack(Material.CYAN_TERRACOTTA);
//        ItemStack purpleTerracota = new ItemStack(Material.PURPLE_TERRACOTTA);
//        ItemStack blueTerracota = new ItemStack(Material.BLUE_TERRACOTTA);
//        ItemStack brownTerracota = new ItemStack(Material.BROWN_TERRACOTTA);
//        ItemStack greenTerracota = new ItemStack(Material.GREEN_TERRACOTTA);
//        ItemStack redTerracota = new ItemStack(Material.RED_TERRACOTTA);
//        ItemStack blackTerracota = new ItemStack(Material.BLACK_TERRACOTTA);
//        ItemStack terracota = new ItemStack(Material.TERRACOTTA);
//        ItemStack packedIce = new ItemStack(Material.PACKED_ICE);
//        ItemStack whiteGlass = new ItemStack(Material.WHITE_STAINED_GLASS);
//
//        Survival.plugin.getItemManager().setItemMeta(next, "&aDalší", 1);
//        Survival.plugin.getItemManager().setItemMeta(back, "&cZpátky", 1);
//        Survival.plugin.getItemManager().setItemMeta(vypln, " ", 1);
//
//        prize_int = shop.getDouble("shop.sections.blocks.contents.cracked_nether_bricks");
//        Survival.plugin.getItemManager().addLore(crackedNetherBricks, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.chiseled_nether_bricks");
//        Survival.plugin.getItemManager().addLore(chiseledNetherBricks, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.end_stone");
//        Survival.plugin.getItemManager().addLore(endStone, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.end_stone_bricks");
//        Survival.plugin.getItemManager().addLore(endStoneBricks, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.chiseled_quartz_block");
//        Survival.plugin.getItemManager().addLore(chiseledQuartzBlock, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.quartz_block");
//        Survival.plugin.getItemManager().addLore(quartzBlock, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.quartz_bricks");
//        Survival.plugin.getItemManager().addLore(quartzBricks, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.quartz_pillar");
//        Survival.plugin.getItemManager().addLore(quartzPillar, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.white_terracotta");
//        Survival.plugin.getItemManager().addLore(whiteTerracota, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.orange_terracotta");
//        Survival.plugin.getItemManager().addLore(orangeTerracota, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.magenta_terracotta");
//        Survival.plugin.getItemManager().addLore(magentaTerracota, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.light_blue_terracotta");
//        Survival.plugin.getItemManager().addLore(lightBlueTerracota, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.yellow_terracotta");
//        Survival.plugin.getItemManager().addLore(yellowTerracota, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.lime_terracotta");
//        Survival.plugin.getItemManager().addLore(limeTerracota, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.pink_terracotta");
//        Survival.plugin.getItemManager().addLore(pinkTerracota, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.gray_terracotta");
//        Survival.plugin.getItemManager().addLore(grayTerracota, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.light_gray_terracotta");
//        Survival.plugin.getItemManager().addLore(lightGrayTerracota, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.cyan_terracotta");
//        Survival.plugin.getItemManager().addLore(cyanTerracota, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.purple_terracotta");
//        Survival.plugin.getItemManager().addLore(purpleTerracota, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.blue_terracotta");
//        Survival.plugin.getItemManager().addLore(blueTerracota, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.brown_terracotta");
//        Survival.plugin.getItemManager().addLore(brownTerracota, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.green_terracotta");
//        Survival.plugin.getItemManager().addLore(greenTerracota, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.red_terracotta");
//        Survival.plugin.getItemManager().addLore(redTerracota, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.black_terracotta");
//        Survival.plugin.getItemManager().addLore(blackTerracota, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.terracotta");
//        Survival.plugin.getItemManager().addLore(terracota, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.packed_ice");
//        Survival.plugin.getItemManager().addLore(packedIce, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.white_glass");
//        Survival.plugin.getItemManager().addLore(whiteGlass, " ", prize.replace("%prize%", "" + prize_int), null);
//
//        ItemStack[] menu_items = {
//                vypln, vypln, vypln, vypln, vypln, vypln, vypln, vypln, vypln,
//                vypln, crackedNetherBricks, chiseledNetherBricks, endStone, endStoneBricks, chiseledQuartzBlock, quartzBlock, quartzBricks, vypln,
//                vypln, quartzPillar, whiteTerracota, orangeTerracota, magentaTerracota, lightBlueTerracota, yellowTerracota, limeTerracota, vypln,
//                vypln, limeTerracota, pinkTerracota, grayTerracota, lightGrayTerracota, cyanTerracota, purpleTerracota, blueTerracota, vypln,
//                vypln, brownTerracota, greenTerracota, redTerracota, blackTerracota, terracota, packedIce, whiteGlass, vypln,
//                back, vypln, vypln, vypln, vypln, vypln, vypln, vypln, next
//        };
//        blocks.setContents(menu_items);
//        p.openInventory(blocks);
//    }
//    public void openBlocks5(Player p){
//        shop = Survival.plugin.getConfigManager().getFile(ConfigType.SHOP).getConfig();
//
//        blocks_n = shop.getString("shop.sections.blocks.gui.name");
//        blocks_s = shop.getInt("shop.sections.blocks.gui.slots");
//
//        prize = shop.getString("shop.prize");
//
//        Inventory blocks = Bukkit.createInventory(null, blocks_s, TextUtil.color(blocks_n + " - 5/7"));
//
//        ItemStack vypln = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
//        ItemStack next = new ItemStack(Material.LIME_BED);
//        ItemStack back = new ItemStack(Material.RED_BED);
//
//        ItemStack orangeGlass = new ItemStack(Material.ORANGE_STAINED_GLASS);
//        ItemStack magentaGlass = new ItemStack(Material.MAGENTA_STAINED_GLASS);
//        ItemStack lightBlueGlass = new ItemStack(Material.LIGHT_BLUE_STAINED_GLASS);
//        ItemStack yellowGlass = new ItemStack(Material.YELLOW_STAINED_GLASS);
//        ItemStack limeGlass = new ItemStack(Material.LIME_STAINED_GLASS);
//        ItemStack pinkGlass = new ItemStack(Material.PINK_STAINED_GLASS);
//        ItemStack grayGlass = new ItemStack(Material.GRAY_STAINED_GLASS);
//        ItemStack lightGrayGlass = new ItemStack(Material.LIGHT_GRAY_STAINED_GLASS);
//        ItemStack cyanGlass = new ItemStack(Material.CYAN_STAINED_GLASS);
//        ItemStack purpleGlass = new ItemStack(Material.PURPLE_STAINED_GLASS);
//        ItemStack blueGlass = new ItemStack(Material.BLUE_STAINED_GLASS);
//        ItemStack brownGlass = new ItemStack(Material.BROWN_STAINED_GLASS);
//        ItemStack greenGlass = new ItemStack(Material.GREEN_STAINED_GLASS);
//        ItemStack redGlass = new ItemStack(Material.RED_STAINED_GLASS);
//        ItemStack blackGlass = new ItemStack(Material.BLACK_STAINED_GLASS);
//        ItemStack prismarine = new ItemStack(Material.PRISMARINE);
//        ItemStack prismarineBricks = new ItemStack(Material.PRISMARINE_BRICKS);
//        ItemStack darkPrismarine = new ItemStack(Material.DARK_PRISMARINE);
//        ItemStack seaLantern = new ItemStack(Material.SEA_LANTERN);
//        ItemStack redSandStone = new ItemStack(Material.RED_SANDSTONE);
//        ItemStack chiseledRedSandstone = new ItemStack(Material.CHISELED_RED_SANDSTONE);
//        ItemStack cutRedSandstone = new ItemStack(Material.CUT_RED_SANDSTONE);
//        ItemStack magmaBlock = new ItemStack(Material.MAGMA_BLOCK);
//        ItemStack netherWartBlock = new ItemStack(Material.NETHER_WART_BLOCK);
//        ItemStack warpedWartBlock = new ItemStack(Material.WARPED_WART_BLOCK);
//        ItemStack redNetherBricks = new ItemStack(Material.RED_NETHER_BRICKS);
//        ItemStack boneBlock = new ItemStack(Material.BONE_BLOCK);
//        ItemStack whiteConcrete = new ItemStack(Material.WHITE_CONCRETE);
//
//        Survival.plugin.getItemManager().setItemMeta(next, "&aDalší", 1);
//        Survival.plugin.getItemManager().setItemMeta(back, "&cZpátky", 1);
//        Survival.plugin.getItemManager().setItemMeta(vypln, " ", 1);
//
//        prize_int = shop.getDouble("shop.sections.blocks.contents.orange_glass");
//        Survival.plugin.getItemManager().addLore(orangeGlass, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.magenta_glass");
//        Survival.plugin.getItemManager().addLore(magentaGlass, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.light_blue_glass");
//        Survival.plugin.getItemManager().addLore(lightBlueGlass, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.yellow_glass");
//        Survival.plugin.getItemManager().addLore(yellowGlass, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.lime_glass");
//        Survival.plugin.getItemManager().addLore(limeGlass, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.pink_glass");
//        Survival.plugin.getItemManager().addLore(pinkGlass, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.gray_glass");
//        Survival.plugin.getItemManager().addLore(grayGlass, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.light_gray_glass");
//        Survival.plugin.getItemManager().addLore(lightGrayGlass, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.cyan_glass");
//        Survival.plugin.getItemManager().addLore(cyanGlass, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.purple_glass");
//        Survival.plugin.getItemManager().addLore(purpleGlass, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.blue_glass");
//        Survival.plugin.getItemManager().addLore(blueGlass, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.brown_glass");
//        Survival.plugin.getItemManager().addLore(brownGlass, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.green_glass");
//        Survival.plugin.getItemManager().addLore(greenGlass, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.red_glass");
//        Survival.plugin.getItemManager().addLore(redGlass, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.black_glass");
//        Survival.plugin.getItemManager().addLore(blackGlass, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.prismarine");
//        Survival.plugin.getItemManager().addLore(prismarine, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.prismarine_bricks");
//        Survival.plugin.getItemManager().addLore(prismarineBricks, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.dark_prismarine");
//        Survival.plugin.getItemManager().addLore(darkPrismarine, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.sea_lantern");
//        Survival.plugin.getItemManager().addLore(seaLantern, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.red_sandstone");
//        Survival.plugin.getItemManager().addLore(redSandStone, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.chiseled_red_sandstone");
//        Survival.plugin.getItemManager().addLore(chiseledRedSandstone, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.cut_red_sandstone");
//        Survival.plugin.getItemManager().addLore(cutRedSandstone, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.magma_block");
//        Survival.plugin.getItemManager().addLore(magmaBlock, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.nether_wart_block");
//        Survival.plugin.getItemManager().addLore(netherWartBlock, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.warped_wart_block");
//        Survival.plugin.getItemManager().addLore(warpedWartBlock, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.red_nether_bricks");
//        Survival.plugin.getItemManager().addLore(redNetherBricks, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.bone_block");
//        Survival.plugin.getItemManager().addLore(boneBlock, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.white_concrete");
//        Survival.plugin.getItemManager().addLore(whiteConcrete, " ", prize.replace("%prize%", "" + prize_int), null);
//
//        ItemStack[] menu_items = {
//                vypln, vypln, vypln, vypln, vypln, vypln, vypln, vypln, vypln,
//                vypln, orangeGlass, magentaGlass, lightBlueGlass, yellowGlass, limeGlass, pinkGlass, grayGlass, vypln,
//                vypln, lightGrayGlass, cyanGlass, purpleGlass, blueGlass, brownGlass, greenGlass, redGlass, vypln,
//                vypln, blackGlass, prismarine, prismarineBricks, darkPrismarine, seaLantern, redSandStone, chiseledRedSandstone, vypln,
//                vypln, cutRedSandstone, magmaBlock, netherWartBlock, warpedWartBlock, redNetherBricks, boneBlock, whiteConcrete, vypln,
//                back, vypln, vypln, vypln, vypln, vypln, vypln, vypln, next
//        };
//        blocks.setContents(menu_items);
//        p.openInventory(blocks);
//    }
//    public void openBlocks6(Player p){
//        shop = Survival.plugin.getConfigManager().getFile(ConfigType.SHOP).getConfig();
//
//        blocks_n = shop.getString("shop.sections.blocks.gui.name");
//        blocks_s = shop.getInt("shop.sections.blocks.gui.slots");
//
//        prize = shop.getString("shop.prize");
//
//        Inventory blocks = Bukkit.createInventory(null, blocks_s, TextUtil.color(blocks_n + " - 6/7"));
//
//        ItemStack vypln = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
//        ItemStack next = new ItemStack(Material.LIME_BED);
//        ItemStack back = new ItemStack(Material.RED_BED);
//
//        ItemStack orangeConcrete = new ItemStack(Material.ORANGE_CONCRETE);
//        ItemStack magentaConcrete = new ItemStack(Material.MAGENTA_CONCRETE);
//        ItemStack lightBlueConcrete = new ItemStack(Material.LIGHT_BLUE_CONCRETE);
//        ItemStack yellowConcrete = new ItemStack(Material.YELLOW_CONCRETE);
//        ItemStack limeConcrete = new ItemStack(Material.LIME_CONCRETE);
//        ItemStack pinkConcrete = new ItemStack(Material.PINK_CONCRETE);
//        ItemStack grayConcrete = new ItemStack(Material.GRAY_CONCRETE);
//        ItemStack lightGrayConcrete = new ItemStack(Material.LIGHT_GRAY_CONCRETE);
//        ItemStack cyanConcrete = new ItemStack(Material.CYAN_CONCRETE);
//        ItemStack purpleConcrete = new ItemStack(Material.PURPLE_CONCRETE);
//        ItemStack blueConcrete = new ItemStack(Material.BLUE_CONCRETE);
//        ItemStack brownConcrete = new ItemStack(Material.BROWN_CONCRETE);
//        ItemStack greenConcrete = new ItemStack(Material.GREEN_CONCRETE);
//        ItemStack redConcrete = new ItemStack(Material.RED_CONCRETE);
//        ItemStack blackConcrete = new ItemStack(Material.BLACK_CONCRETE);
//        ItemStack whiteConcretePowder = new ItemStack(Material.WHITE_CONCRETE_POWDER);
//        ItemStack orangeConcretePowder = new ItemStack(Material.ORANGE_CONCRETE_POWDER);
//        ItemStack pinkConcretePowder = new ItemStack(Material.PINK_CONCRETE_POWDER);
//        ItemStack lightBlueConcretePowder = new ItemStack(Material.LIGHT_BLUE_CONCRETE_POWDER);
//        ItemStack yellowConcretePowder = new ItemStack(Material.YELLOW_CONCRETE_POWDER);
//        ItemStack grayConcretePowder = new ItemStack(Material.GRAY_CONCRETE_POWDER);
//        ItemStack lightGrayConcretePowder = new ItemStack(Material.LIGHT_GRAY_CONCRETE_POWDER);
//        ItemStack cyanConcretePowder = new ItemStack(Material.CYAN_CONCRETE_POWDER);
//        ItemStack purpleConcretePowder = new ItemStack(Material.PURPLE_CONCRETE_POWDER);
//        ItemStack brownConcretePowder = new ItemStack(Material.BROWN_CONCRETE_POWDER);
//        ItemStack limeConcretePowder = new ItemStack(Material.LIME_CONCRETE_POWDER);
//        ItemStack greenConcretePowder = new ItemStack(Material.GREEN_CONCRETE_POWDER);
//
//        Survival.plugin.getItemManager().setItemMeta(next, "&aDalší", 1);
//        Survival.plugin.getItemManager().setItemMeta(back, "&cZpátky", 1);
//        Survival.plugin.getItemManager().setItemMeta(vypln, " ", 1);
//
//        prize_int = shop.getDouble("shop.sections.blocks.contents.orange_concrete");
//        Survival.plugin.getItemManager().addLore(orangeConcrete, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.magenta_concrete");
//        Survival.plugin.getItemManager().addLore(magentaConcrete, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.light_blue_concrete");
//        Survival.plugin.getItemManager().addLore(lightBlueConcrete, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.yellow_concrete");
//        Survival.plugin.getItemManager().addLore(yellowConcrete, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.lime_concrete");
//        Survival.plugin.getItemManager().addLore(limeConcrete, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.pink_concrete");
//        Survival.plugin.getItemManager().addLore(pinkConcrete, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.gray_concrete");
//        Survival.plugin.getItemManager().addLore(grayConcrete, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.light_gray_concrete");
//        Survival.plugin.getItemManager().addLore(lightGrayConcrete, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.cyan_concrete");
//        Survival.plugin.getItemManager().addLore(cyanConcrete, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.purple_concrete");
//        Survival.plugin.getItemManager().addLore(purpleConcrete, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.blue_concrete");
//        Survival.plugin.getItemManager().addLore(blueConcrete, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.brown_concrete");
//        Survival.plugin.getItemManager().addLore(brownConcrete, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.green_concrete");
//        Survival.plugin.getItemManager().addLore(greenConcrete, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.green_concrete");
//        Survival.plugin.getItemManager().addLore(grayConcrete, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.red_concrete");
//        Survival.plugin.getItemManager().addLore(redConcrete, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.black_concrete");
//        Survival.plugin.getItemManager().addLore(blackConcrete, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.white_concrete_powder");
//        Survival.plugin.getItemManager().addLore(whiteConcretePowder, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.orange_concrete_powder");
//        Survival.plugin.getItemManager().addLore(orangeConcretePowder, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.magenta_concrete_powder");
//        Survival.plugin.getItemManager().addLore(magentaConcrete, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.light_blue_concrete_powder");
//        Survival.plugin.getItemManager().addLore(lightBlueConcretePowder, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.yellow_concrete_powder");
//        Survival.plugin.getItemManager().addLore(yellowConcretePowder, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.lime_concrete_powder");
//        Survival.plugin.getItemManager().addLore(limeConcretePowder, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.pink_concrete_powder");
//        Survival.plugin.getItemManager().addLore(pinkConcretePowder, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.gray_concrete_powder");
//        Survival.plugin.getItemManager().addLore(grayConcretePowder, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.light_gray_concrete_powder");
//        Survival.plugin.getItemManager().addLore(lightGrayConcretePowder, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.cyan_concrete_powder");
//        Survival.plugin.getItemManager().addLore(cyanConcretePowder, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.purple_concrete_powder");
//        Survival.plugin.getItemManager().addLore(purpleConcretePowder, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.blue_concrete_powder");
//        Survival.plugin.getItemManager().addLore(lightBlueConcretePowder, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.brown_concrete_powder");
//        Survival.plugin.getItemManager().addLore(brownConcretePowder, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.green_concrete_powder");
//        Survival.plugin.getItemManager().addLore(greenConcretePowder, " ", prize.replace("%prize%", "" + prize_int), null);
//
//        ItemStack[] menu_items = {
//                vypln, vypln, vypln, vypln, vypln, vypln, vypln, vypln, vypln,
//                vypln, orangeConcrete, magentaConcrete, lightBlueConcrete, yellowConcrete, limeConcrete, pinkConcrete, grayConcrete, vypln,
//                vypln, lightGrayConcrete, cyanConcrete, purpleConcrete, blueConcrete, brownConcrete, greenConcrete, redConcrete, vypln,
//                vypln, blackConcrete, whiteConcretePowder, orangeConcretePowder, pinkConcretePowder, lightBlueConcretePowder, yellowConcretePowder, limeConcretePowder, vypln,
//                vypln, pinkConcretePowder, grayConcretePowder, lightGrayConcretePowder, cyanConcretePowder, purpleConcretePowder, brownConcretePowder, greenConcretePowder, vypln,
//                back, vypln, vypln, vypln, vypln, vypln, vypln, vypln, next
//        };
//        blocks.setContents(menu_items);
//        p.openInventory(blocks);
//    }
//    public void openBlocks7(Player p){
//        shop = Survival.plugin.getConfigManager().getFile(ConfigType.SHOP).getConfig();
//
//        blocks_n = shop.getString("shop.sections.blocks.gui.name");
//        blocks_s = shop.getInt("shop.sections.blocks.gui.slots");
//
//        prize = shop.getString("shop.prize");
//
//        Inventory blocks = Bukkit.createInventory(null, blocks_s, TextUtil.color(blocks_n + " - 7/7"));
//
//        ItemStack vypln = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
//        ItemStack next = new ItemStack(Material.LIME_BED);
//        ItemStack back = new ItemStack(Material.RED_BED);
//
//        ItemStack redConcretePowder = new ItemStack(Material.RED_CONCRETE_POWDER);
//        ItemStack blackConcretePowder = new ItemStack(Material.BLACK_CONCRETE_POWDER);
//        ItemStack deadTubeCoralBlock = new ItemStack(Material.DEAD_TUBE_CORAL_BLOCK);
//        ItemStack deadBrainCoralBlock = new ItemStack(Material.DEAD_BRAIN_CORAL_BLOCK);
//        ItemStack deadBubbleCoralBlock = new ItemStack(Material.DEAD_BUBBLE_CORAL_BLOCK);
//        ItemStack deadFireCoralBlock = new ItemStack(Material.DEAD_FIRE_CORAL_BLOCK);
//        ItemStack deadHornCoralBlock = new ItemStack(Material.DEAD_HORN_CORAL_BLOCK);
//        ItemStack tubeCoralBlock = new ItemStack(Material.TUBE_CORAL_BLOCK);
//        ItemStack brainCoralBlock = new ItemStack(Material.BRAIN_CORAL_BLOCK);
//        ItemStack bubbleCoralBlock = new ItemStack(Material.BUBBLE_CORAL_BLOCK);
//        ItemStack fireCoralBlock = new ItemStack(Material.FIRE_CORAL_BLOCK);
//        ItemStack hornCoralBlock = new ItemStack(Material.HORN_CORAL_BLOCK);
//        ItemStack blueIce = new ItemStack(Material.BLUE_ICE);
//        ItemStack driedKelpBlock = new ItemStack(Material.DRIED_KELP_BLOCK);
//        ItemStack cryingObsidian = new ItemStack(Material.CRYING_OBSIDIAN);
//        ItemStack blackstone = new ItemStack(Material.BLACKSTONE);
//        ItemStack gildedBlackstone = new ItemStack(Material.GILDED_BLACKSTONE);
//        ItemStack polishedBlackstone = new ItemStack(Material.POLISHED_BLACKSTONE);
//        ItemStack chiseledPolishedBlackstone = new ItemStack(Material.CHISELED_POLISHED_BLACKSTONE);
//        ItemStack polishedBlackstoneBricks = new ItemStack(Material.POLISHED_BLACKSTONE_BRICKS);
//        ItemStack crackedPolishedBlackstoneBricks = new ItemStack(Material.CRACKED_POLISHED_BLACKSTONE_BRICKS);
//
//        Survival.plugin.getItemManager().setItemMeta(next, "&aDalší", 1);
//        Survival.plugin.getItemManager().setItemMeta(back, "&cZpátky", 1);
//        Survival.plugin.getItemManager().setItemMeta(vypln, " ", 1);
//
//        prize_int = shop.getDouble("shop.sections.blocks.contents.red_concrete_powder");
//        Survival.plugin.getItemManager().addLore(redConcretePowder, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.black_concrete_powder");
//        Survival.plugin.getItemManager().addLore(blackConcretePowder, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.dead_tube_coral_block");
//        Survival.plugin.getItemManager().addLore(deadTubeCoralBlock, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.dead_brain_coral_block");
//        Survival.plugin.getItemManager().addLore(deadBrainCoralBlock, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.dead_bubble_coral_block");
//        Survival.plugin.getItemManager().addLore(deadBubbleCoralBlock, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.dead_fire_coral_block");
//        Survival.plugin.getItemManager().addLore(deadFireCoralBlock, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.dead_horn_coral_block");
//        Survival.plugin.getItemManager().addLore(deadHornCoralBlock, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.tube_coral_block");
//        Survival.plugin.getItemManager().addLore(tubeCoralBlock, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.brain_coral_block");
//        Survival.plugin.getItemManager().addLore(brainCoralBlock, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.bubble_coral_block");
//        Survival.plugin.getItemManager().addLore(bubbleCoralBlock, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.fire_coral_block");
//        Survival.plugin.getItemManager().addLore(fireCoralBlock, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.horn_coral_block");
//        Survival.plugin.getItemManager().addLore(hornCoralBlock, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.blue_ice");
//        Survival.plugin.getItemManager().addLore(blueIce, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.dried_kelp_block");
//        Survival.plugin.getItemManager().addLore(driedKelpBlock, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.crying_obsidian");
//        Survival.plugin.getItemManager().addLore(cryingObsidian, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.blackstone");
//        Survival.plugin.getItemManager().addLore(blackstone, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.gilded_blackstone");
//        Survival.plugin.getItemManager().addLore(gildedBlackstone, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.polished_blackstone");
//        Survival.plugin.getItemManager().addLore(polishedBlackstone, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.chiseled_polished_blackstone");
//        Survival.plugin.getItemManager().addLore(chiseledPolishedBlackstone, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.polished_blackstone_bricks");
//        Survival.plugin.getItemManager().addLore(polishedBlackstoneBricks, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.blocks.contents.cracked_polished_blackstone_bricks");
//        Survival.plugin.getItemManager().addLore(crackedPolishedBlackstoneBricks, " ", prize.replace("%prize%", "" + prize_int), null);
//
//        ItemStack[] menu_items = {
//                vypln, vypln, vypln, vypln, vypln, vypln, vypln, vypln, vypln,
//                vypln, redConcretePowder, blackConcretePowder, deadTubeCoralBlock, deadBrainCoralBlock, deadBubbleCoralBlock, deadFireCoralBlock, deadHornCoralBlock, vypln,
//                vypln, tubeCoralBlock, brainCoralBlock, bubbleCoralBlock, fireCoralBlock, hornCoralBlock, blueIce, driedKelpBlock, vypln,
//                vypln, cryingObsidian, blackstone, gildedBlackstone, polishedBlackstone, chiseledPolishedBlackstone, polishedBlackstoneBricks, crackedPolishedBlackstoneBricks, vypln,
//                vypln, vypln, vypln, vypln, vypln, vypln, vypln, vypln, vypln,
//                back, vypln, vypln, vypln, vypln, vypln, vypln, vypln, vypln
//        };
//        blocks.setContents(menu_items);
//        p.openInventory(blocks);
//    }
//
//    public void openWeapons(Player p){
//        shop = Survival.plugin.getConfigManager().getFile(ConfigType.SHOP).getConfig();
//
//        weapons_n = shop.getString("shop.sections.weapons.gui.name");
//        weapons_s = shop.getInt("shop.sections.weapons.gui.slots");
//
//        Inventory weapons = Bukkit.createInventory(null, weapons_s, TextUtil.color(weapons_n));
//
//        ItemStack vypln = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
//        ItemStack back = new ItemStack(Material.RED_BED);
//
//        ItemStack diamond_sword = new ItemStack(Material.DIAMOND_SWORD);
//        ItemStack diamond_pickaxe = new ItemStack(Material.DIAMOND_PICKAXE);
//        ItemStack diamond_axe = new ItemStack(Material.DIAMOND_AXE);
//        ItemStack diamond_shovel = new ItemStack(Material.DIAMOND_SHOVEL);
//        ItemStack diamond_hoe = new ItemStack(Material.DIAMOND_HOE);
//
//        ItemStack iron_sword = new ItemStack(Material.IRON_SWORD);
//        ItemStack iron_pickaxe = new ItemStack(Material.IRON_PICKAXE);
//        ItemStack iron_axe = new ItemStack(Material.IRON_AXE);
//        ItemStack iron_shovel = new ItemStack(Material.IRON_SHOVEL);
//        ItemStack iron_hoe = new ItemStack(Material.IRON_HOE);
//
//        ItemStack gold_sword = new ItemStack(Material.GOLDEN_SWORD);
//        ItemStack gold_pickaxe = new ItemStack(Material.GOLDEN_PICKAXE);
//        ItemStack gold_axe = new ItemStack(Material.GOLDEN_AXE);
//        ItemStack gold_shovel = new ItemStack(Material.GOLDEN_SHOVEL);
//        ItemStack gold_hoe = new ItemStack(Material.GOLDEN_HOE);
//
//        ItemStack stone_sword = new ItemStack(Material.STONE_SWORD);
//        ItemStack stone_pickaxe = new ItemStack(Material.STONE_PICKAXE);
//        ItemStack stone_axe = new ItemStack(Material.STONE_AXE);
//        ItemStack stone_shovel = new ItemStack(Material.STONE_SHOVEL);
//        ItemStack stone_hoe = new ItemStack(Material.STONE_HOE);
//
//        prize = shop.getString("shop.prize");
//
//        Survival.plugin.getItemManager().setItemMeta(back, "&cZpátky", 1);
//        Survival.plugin.getItemManager().setItemMeta(vypln, " ", 1);
//
//        prize_int = shop.getDouble("shop.sections.weapons.contents.diamond.sword");
//        Survival.plugin.getItemManager().addLore(diamond_sword, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.weapons.contents.diamond.pickaxe");
//        Survival.plugin.getItemManager().addLore(diamond_pickaxe, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.weapons.contents.diamond.axe");
//        Survival.plugin.getItemManager().addLore(diamond_axe, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.weapons.contents.diamond.shovel");
//        Survival.plugin.getItemManager().addLore(diamond_shovel, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.weapons.contents.diamond.hoe");
//        Survival.plugin.getItemManager().addLore(diamond_hoe, " ", prize.replace("%prize%", "" + prize_int), null);
//
//        prize_int = shop.getDouble("shop.sections.weapons.contents.iron.sword");
//        Survival.plugin.getItemManager().addLore(iron_sword, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.weapons.contents.iron.pickaxe");
//        Survival.plugin.getItemManager().addLore(iron_pickaxe, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.weapons.contents.iron.axe");
//        Survival.plugin.getItemManager().addLore(iron_axe, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.weapons.contents.iron.shovel");
//        Survival.plugin.getItemManager().addLore(iron_shovel, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.weapons.contents.iron.hoe");
//        Survival.plugin.getItemManager().addLore(iron_hoe, " ", prize.replace("%prize%", "" + prize_int), null);
//
//        prize_int = shop.getDouble("shop.sections.weapons.contents.golden.sword");
//        Survival.plugin.getItemManager().addLore(gold_sword, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.weapons.contents.golden.pickaxe");
//        Survival.plugin.getItemManager().addLore(gold_pickaxe, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.weapons.contents.golden.axe");
//        Survival.plugin.getItemManager().addLore(gold_axe, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.weapons.contents.golden.shovel");
//        Survival.plugin.getItemManager().addLore(gold_shovel, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.weapons.contents.golden.hoe");
//        Survival.plugin.getItemManager().addLore(gold_hoe, " ", prize.replace("%prize%", "" + prize_int), null);
//
//        prize_int = shop.getDouble("shop.sections.weapons.contents.stone.sword");
//        Survival.plugin.getItemManager().addLore(stone_sword, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.weapons.contents.stone.pickaxe");
//        Survival.plugin.getItemManager().addLore(stone_pickaxe, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.weapons.contents.stone.axe");
//        Survival.plugin.getItemManager().addLore(stone_axe, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.weapons.contents.stone.shovel");
//        Survival.plugin.getItemManager().addLore(stone_shovel, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.weapons.contents.stone.hoe");
//        Survival.plugin.getItemManager().addLore(stone_hoe, " ", prize.replace("%prize%", "" + prize_int), null);
//
//        ItemStack[] menu_items = {
//                vypln, vypln, vypln, vypln, vypln, vypln, vypln, vypln, vypln,
//                vypln, vypln, diamond_sword, diamond_pickaxe, diamond_axe, diamond_shovel, diamond_hoe, vypln, vypln,
//                vypln, vypln, iron_sword, iron_pickaxe, iron_axe, iron_shovel, iron_hoe, vypln, vypln,
//                vypln, vypln, gold_sword, gold_pickaxe, gold_axe, gold_shovel, gold_hoe, vypln, vypln,
//                vypln, vypln, stone_sword, stone_pickaxe, stone_axe, stone_shovel, stone_hoe, vypln, vypln,
//                back, vypln, vypln, vypln, vypln, vypln, vypln, vypln, vypln
//        };
//        weapons.setContents(menu_items);
//        p.openInventory(weapons);
//    }
//
//    public void openArmor(Player p){
//        shop = Survival.plugin.getConfigManager().getFile(ConfigType.SHOP).getConfig();
//
//        armor_n = shop.getString("shop.sections.armor.gui.name");
//        armor_s = shop.getInt("shop.sections.armor.gui.slots");
//
//        Inventory armor = Bukkit.createInventory(null, armor_s, TextUtil.color(armor_n));
//
//        ItemStack vypln = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
//        ItemStack back = new ItemStack(Material.RED_BED);
//
//        ItemStack diamond_helmet = new ItemStack(Material.DIAMOND_HELMET);
//        ItemStack diamond_chestplate = new ItemStack(Material.DIAMOND_CHESTPLATE);
//        ItemStack diamond_leggings = new ItemStack(Material.DIAMOND_LEGGINGS);
//        ItemStack diamond_boots = new ItemStack(Material.DIAMOND_BOOTS);
//
//        ItemStack iron_helmet = new ItemStack(Material.IRON_HELMET);
//        ItemStack iron_chestplate = new ItemStack(Material.IRON_CHESTPLATE);
//        ItemStack iron_leggings = new ItemStack(Material.IRON_LEGGINGS);
//        ItemStack iron_boots = new ItemStack(Material.IRON_BOOTS);
//
//        ItemStack gold_helmet = new ItemStack(Material.GOLDEN_HELMET);
//        ItemStack gold_chestplate = new ItemStack(Material.GOLDEN_CHESTPLATE);
//        ItemStack gold_leggings = new ItemStack(Material.GOLDEN_LEGGINGS);
//        ItemStack gold_boots = new ItemStack(Material.GOLDEN_BOOTS);
//
//        ItemStack chain_helmet = new ItemStack(Material.CHAINMAIL_HELMET);
//        ItemStack chain_chestplate = new ItemStack(Material.CHAINMAIL_CHESTPLATE);
//        ItemStack chain_leggings = new ItemStack(Material.CHAINMAIL_LEGGINGS);
//        ItemStack chain_boots = new ItemStack(Material.CHAINMAIL_BOOTS);
//
//        ItemStack leather_helmet = new ItemStack(Material.LEATHER_HELMET);
//        ItemStack leather_chestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
//        ItemStack leather_leggings = new ItemStack(Material.LEATHER_LEGGINGS);
//        ItemStack leather_boots = new ItemStack(Material.LEATHER_BOOTS);
//
//        prize = shop.getString("shop.prize");
//
//        Survival.plugin.getItemManager().setItemMeta(back, "&cZpátky", 1);
//        Survival.plugin.getItemManager().setItemMeta(vypln, " ", 1);
//
//        prize_int = shop.getDouble("shop.sections.armor.contents.diamond.helmet");
//        Survival.plugin.getItemManager().addLore(diamond_helmet, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.armor.contents.diamond.chestplate");
//        Survival.plugin.getItemManager().addLore(diamond_chestplate, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.armor.contents.diamond.leggings");
//        Survival.plugin.getItemManager().addLore(diamond_leggings, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.armor.contents.diamond.boots");
//        Survival.plugin.getItemManager().addLore(diamond_boots, " ", prize.replace("%prize%", "" + prize_int), null);
//
//        prize_int = shop.getDouble("shop.sections.armor.contents.iron.helmet");
//        Survival.plugin.getItemManager().addLore(iron_helmet, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.armor.contents.iron.chestplate");
//        Survival.plugin.getItemManager().addLore(iron_chestplate, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.armor.contents.iron.leggings");
//        Survival.plugin.getItemManager().addLore(iron_leggings, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.armor.contents.iron.boots");
//        Survival.plugin.getItemManager().addLore(iron_boots, " ", prize.replace("%prize%", "" + prize_int), null);
//
//        prize_int = shop.getDouble("shop.sections.armor.contents.golden.helmet");
//        Survival.plugin.getItemManager().addLore(gold_helmet, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.armor.contents.golden.chestplate");
//        Survival.plugin.getItemManager().addLore(gold_chestplate, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.armor.contents.golden.leggings");
//        Survival.plugin.getItemManager().addLore(gold_leggings, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.armor.contents.golden.boots");
//        Survival.plugin.getItemManager().addLore(gold_boots, " ", prize.replace("%prize%", "" + prize_int), null);
//
//        prize_int = shop.getDouble("shop.sections.armor.contents.chain.helmet");
//        Survival.plugin.getItemManager().addLore(chain_helmet, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.armor.contents.chain.chestplate");
//        Survival.plugin.getItemManager().addLore(chain_chestplate, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.armor.contents.chain.leggings");
//        Survival.plugin.getItemManager().addLore(chain_leggings, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.armor.contents.chain.boots");
//        Survival.plugin.getItemManager().addLore(chain_boots, " ", prize.replace("%prize%", "" + prize_int), null);
//
//        prize_int = shop.getDouble("shop.sections.armor.contents.leather.helmet");
//        Survival.plugin.getItemManager().addLore(leather_helmet, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.armor.contents.leather.chestplate");
//        Survival.plugin.getItemManager().addLore(leather_chestplate, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.armor.contents.leather.leggings");
//        Survival.plugin.getItemManager().addLore(leather_leggings, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.armor.contents.leather.boots");
//        Survival.plugin.getItemManager().addLore(leather_boots, " ", prize.replace("%prize%", "" + prize_int), null);
//
//        ItemStack[] menu_items = {
//                vypln, vypln, vypln, vypln, vypln, vypln, vypln, vypln, vypln,
//                vypln, vypln, diamond_helmet, iron_helmet, gold_helmet, chain_helmet, leather_helmet, vypln, vypln,
//                vypln, vypln, diamond_chestplate, iron_chestplate, gold_chestplate, chain_chestplate, leather_chestplate, vypln, vypln,
//                vypln, vypln, diamond_leggings, iron_leggings, gold_leggings, chain_leggings, leather_leggings, vypln, vypln,
//                vypln, vypln, diamond_boots,iron_boots, gold_boots, chain_boots, leather_boots, vypln, vypln,
//                back, vypln, vypln, vypln, vypln, vypln, vypln, vypln, vypln
//        };
//        armor.setContents(menu_items);
//        p.openInventory(armor);
//    }
//
//    public void openFood(Player p){
//        shop = Survival.plugin.getConfigManager().getFile(ConfigType.SHOP).getConfig();
//
//        food_n = shop.getString("shop.sections.food.gui.name");
//        food_s = shop.getInt("shop.sections.food.gui.slots");
//
//        Inventory food = Bukkit.createInventory(null, food_s, TextUtil.color(food_n));
//
//        ItemStack vypln = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
//        ItemStack back = new ItemStack(Material.RED_BED);
//
//        ItemStack apple = new ItemStack(Material.APPLE);
//        ItemStack gapple = new ItemStack(Material.GOLDEN_APPLE);
//        ItemStack egapple = new ItemStack(Material.ENCHANTED_GOLDEN_APPLE);
//        ItemStack carrot = new ItemStack(Material.CARROT);
//        ItemStack potato = new ItemStack(Material.BAKED_POTATO);
//        ItemStack melon = new ItemStack(Material.MELON_SLICE);
//        ItemStack bread = new ItemStack(Material.BREAD);
//        ItemStack soup = new ItemStack(Material.BEETROOT_SOUP);
//        ItemStack beef = new ItemStack(Material.COOKED_BEEF);
//        ItemStack porkchop = new ItemStack(Material.COOKED_PORKCHOP);
//        ItemStack chicken = new ItemStack(Material.COOKED_CHICKEN);
//        ItemStack rabbit = new ItemStack(Material.COOKED_RABBIT);
//        ItemStack cake = new ItemStack(Material.CAKE);
//
//        prize = shop.getString("shop.prize");
//
//        Survival.plugin.getItemManager().setItemMeta(back, "&cZpátky", 1);
//        Survival.plugin.getItemManager().setItemMeta(vypln, " ", 1);
//
//        prize_int = shop.getDouble("shop.sections.food.contents.apple");
//        Survival.plugin.getItemManager().addLore(apple, " ", prize.replace("%prize%", "" + prize_int), null);
//
//        prize_int = shop.getDouble("shop.sections.food.contents.golden_apple");
//        Survival.plugin.getItemManager().addLore(gapple, " ", prize.replace("%prize%", "" + prize_int), null);
//
//        prize_int = shop.getDouble("shop.sections.food.contents.enchanted_golden_apple");
//        Survival.plugin.getItemManager().addLore(egapple, " ", prize.replace("%prize%", "" + prize_int), null);
//
//        prize_int = shop.getDouble("shop.sections.food.contents.carrot");
//        Survival.plugin.getItemManager().addLore(carrot, " ", prize.replace("%prize%", "" + prize_int), null);
//
//        prize_int = shop.getDouble("shop.sections.food.contents.potato");
//        Survival.plugin.getItemManager().addLore(potato, " ", prize.replace("%prize%", "" + prize_int), null);
//
//        prize_int = shop.getDouble("shop.sections.food.contents.melon");
//        Survival.plugin.getItemManager().addLore(melon, " ", prize.replace("%prize%", "" + prize_int), null);
//
//        prize_int = shop.getDouble("shop.sections.food.contents.bread");
//        Survival.plugin.getItemManager().addLore(bread, " ", prize.replace("%prize%", "" + prize_int), null);
//
//        prize_int = shop.getDouble("shop.sections.food.contents.soup");
//        Survival.plugin.getItemManager().addLore(soup, " ", prize.replace("%prize%", "" + prize_int), null);
//
//        prize_int = shop.getDouble("shop.sections.food.contents.beef");
//        Survival.plugin.getItemManager().addLore(beef, " ", prize.replace("%prize%", "" + prize_int), null);
//
//        prize_int = shop.getDouble("shop.sections.food.contents.porkchop");
//        Survival.plugin.getItemManager().addLore(porkchop, " ", prize.replace("%prize%", "" + prize_int), null);
//
//        prize_int = shop.getDouble("shop.sections.food.contents.chicken");
//        Survival.plugin.getItemManager().addLore(chicken, " ", prize.replace("%prize%", "" + prize_int), null);
//
//        prize_int = shop.getDouble("shop.sections.food.contents.rabbit");
//        Survival.plugin.getItemManager().addLore(rabbit, " ", prize.replace("%prize%", "" + prize_int), null);
//
//        prize_int = shop.getDouble("shop.sections.food.contents.cake");
//        Survival.plugin.getItemManager().addLore(cake, " ", prize.replace("%prize%", "" + prize_int), null);
//
//        ItemStack[] menu_items = {
//                vypln, vypln, vypln, vypln, vypln, vypln, vypln, vypln, vypln,
//                vypln, apple, gapple, egapple, bread, carrot, potato, melon, vypln,
//                vypln, soup, beef, porkchop, chicken, rabbit, cake, vypln, vypln,
//                vypln, vypln, vypln, vypln, vypln, vypln, vypln, vypln, vypln,
//                back, vypln, vypln, vypln, vypln, vypln, vypln, vypln, vypln
//        };
//        food.setContents(menu_items);
//        p.openInventory(food);
//    }
//
//    public void openGarden(Player p){
//        shop = Survival.plugin.getConfigManager().getFile(ConfigType.SHOP).getConfig();
//
//        garden_n = shop.getString("shop.sections.garden.gui.name");
//        garden_s = shop.getInt("shop.sections.garden.gui.slots");
//
//        prize = shop.getString("shop.prize");
//
//        Inventory garden = Bukkit.createInventory(null, garden_s, TextUtil.color(garden_n));
//
//        ItemStack vypln = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
//        ItemStack back = new ItemStack(Material.RED_BED);
//
//        ItemStack wheat_seeds = new ItemStack(Material.WHEAT_SEEDS);
//        ItemStack melon_seeds = new ItemStack(Material.MELON_SEEDS);
//        ItemStack pumpkin_seeds = new ItemStack(Material.PUMPKIN_SEEDS);
//        ItemStack beedroot_seeds = new ItemStack(Material.BEETROOT_SEEDS);
//        ItemStack carrot = new ItemStack(Material.CARROT);
//        ItemStack potato = new ItemStack(Material.POTATO);
//        ItemStack bamboo = new ItemStack(Material.BAMBOO);
//        ItemStack sugar_cane = new ItemStack(Material.SUGAR_CANE);
//        ItemStack oak_sapling = new ItemStack(Material.OAK_SAPLING);
//        ItemStack spruce_sapling = new ItemStack(Material.SPRUCE_SAPLING);
//        ItemStack birch_sapling = new ItemStack(Material.BIRCH_SAPLING);
//        ItemStack jungle_sapling = new ItemStack(Material.JUNGLE_SAPLING);
//        ItemStack acacia_sapling = new ItemStack(Material.ACACIA_SAPLING);
//        ItemStack dark_sapling = new ItemStack(Material.DARK_OAK_SAPLING);
//        ItemStack brown_mushroom = new ItemStack(Material.BROWN_MUSHROOM);
//        ItemStack red_mushroom = new ItemStack(Material.RED_MUSHROOM);
//        ItemStack cactus = new ItemStack(Material.CACTUS);
//        ItemStack cocoa_beans = new ItemStack(Material.COCOA_BEANS);
////        ItemStack  = new ItemStack(Material.);
////        ItemStack  = new ItemStack(Material.);
////        ItemStack  = new ItemStack(Material.);
////        ItemStack  = new ItemStack(Material.);
//
//        Survival.plugin.getItemManager().setItemMeta(back, "&cZpátky", 1);
//        Survival.plugin.getItemManager().setItemMeta(vypln, " ", 1);
//
//        prize_int = shop.getDouble("shop.sections.garden.contents.wheat_seeds");
//        Survival.plugin.getItemManager().addLore(wheat_seeds, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.garden.contents.melon_seeds");
//        Survival.plugin.getItemManager().addLore(melon_seeds, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.garden.contents.pumpkin_seeds");
//        Survival.plugin.getItemManager().addLore(pumpkin_seeds, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.garden.contents.beetroot_seeds");
//        Survival.plugin.getItemManager().addLore(beedroot_seeds, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.garden.contents.carrot");
//        Survival.plugin.getItemManager().addLore(carrot, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.garden.contents.potato");
//        Survival.plugin.getItemManager().addLore(potato, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.garden.contents.bamboo");
//        Survival.plugin.getItemManager().addLore(bamboo, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.garden.contents.sugar_cane");
//        Survival.plugin.getItemManager().addLore(sugar_cane, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.garden.contents.oak_sapling");
//        Survival.plugin.getItemManager().addLore(oak_sapling, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.garden.contents.spruce_sapling");
//        Survival.plugin.getItemManager().addLore(spruce_sapling, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.garden.contents.birch_sapling");
//        Survival.plugin.getItemManager().addLore(birch_sapling, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.garden.contents.jungle_sapling");
//        Survival.plugin.getItemManager().addLore(jungle_sapling, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.garden.contents.acacia_sapling");
//        Survival.plugin.getItemManager().addLore(acacia_sapling, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.garden.contents.dark_oak_sapling");
//        Survival.plugin.getItemManager().addLore(dark_sapling, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.garden.contents.brown_mushroom");
//        Survival.plugin.getItemManager().addLore(brown_mushroom, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.garden.contents.red_mushroom");
//        Survival.plugin.getItemManager().addLore(red_mushroom, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.garden.contents.cactus");
//        Survival.plugin.getItemManager().addLore(cactus, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.garden.contents.cocoa_beans");
//        Survival.plugin.getItemManager().addLore(cocoa_beans, " ", prize.replace("%prize%", "" + prize_int), null);
//
//
//        ItemStack[] menu_items = {
//                vypln, vypln, vypln, vypln, vypln, vypln, vypln, vypln, vypln,
//                vypln, wheat_seeds, melon_seeds, pumpkin_seeds, beedroot_seeds, carrot, potato, bamboo, vypln,
//                vypln, sugar_cane, brown_mushroom, red_mushroom, oak_sapling, spruce_sapling, birch_sapling, acacia_sapling, vypln,
//                vypln, jungle_sapling, dark_sapling, vypln, vypln, vypln, vypln, vypln, vypln,
//                vypln, vypln, vypln, vypln, vypln, vypln, vypln, vypln, vypln,
//                back, vypln, vypln, vypln, vypln, vypln, vypln, vypln, vypln
//        };
//        garden.setContents(menu_items);
//        p.openInventory(garden);
//    }
//
//    public void openSpawner(Player p){
//
//        shop = Survival.plugin.getConfigManager().getFile(ConfigType.SHOP).getConfig();
//
//        spawner_n = shop.getString("shop.sections.spawner.gui.name");
//        spawner_s = shop.getInt("shop.sections.spawner.gui.slots");
//
//        Inventory spawner = Bukkit.createInventory(null, spawner_s, TextUtil.color(spawner_n));
//
//        ItemStack vypln = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
//        ItemStack back = new ItemStack(Material.RED_BED);
//
//        ItemStack zombie = new ItemStack(Material.SPAWNER);
//        ItemStack skeleton = new ItemStack(Material.SPAWNER);
//        ItemStack spider = new ItemStack(Material.SPAWNER);
//        ItemStack sheep = new ItemStack(Material.SPAWNER);
//        ItemStack chicken = new ItemStack(Material.SPAWNER);
//        ItemStack cow = new ItemStack(Material.SPAWNER);
//        ItemStack caveSpider = new ItemStack(Material.SPAWNER);
//        ItemStack horse = new ItemStack(Material.SPAWNER);
//        ItemStack pigman = new ItemStack(Material.SPAWNER);
//        ItemStack pig = new ItemStack(Material.SPAWNER);
//        ItemStack slime = new ItemStack(Material.SPAWNER);
//        ItemStack mushroomCow = new ItemStack(Material.SPAWNER);
//
//        prize = shop.getString("shop.prize");
//
//        Survival.plugin.getItemManager().setItemMeta(back, "&cZpátky", 1);
//        Survival.plugin.getItemManager().setItemMeta(vypln, " ", 1);
//
//        prize_int = shop.getDouble("shop.sections.spawner.contents.zombie");
//        Survival.plugin.getItemManager().setItemMeta(zombie, "Zombie Spawner", 1);
//        Survival.plugin.getItemManager().addLore(zombie, " ", prize.replace("%prize%", "" + prize_int), null);
//
//        prize_int = shop.getDouble("shop.sections.spawner.contents.skeleton");
//        Survival.plugin.getItemManager().setItemMeta(skeleton, "Skeleton Spawner", 1);
//        Survival.plugin.getItemManager().addLore(skeleton, " ", prize.replace("%prize%", "" + prize_int), null);
//
//        prize_int = shop.getDouble("shop.sections.spawner.contents.spider");
//        Survival.plugin.getItemManager().setItemMeta(spider, "Spider Spawner", 1);
//        Survival.plugin.getItemManager().addLore(spider, " ", prize.replace("%prize%", "" + prize_int), null);
//
//        prize_int = shop.getDouble("shop.sections.spawner.contents.sheep");
//        Survival.plugin.getItemManager().setItemMeta(sheep, "Sheep Spawner", 1);
//        Survival.plugin.getItemManager().addLore(sheep, " ", prize.replace("%prize%", "" + prize_int), null);
//
//        prize_int = shop.getDouble("shop.sections.spawner.contents.chicken");
//        Survival.plugin.getItemManager().setItemMeta(chicken, "Chicken Spawner", 1);
//        Survival.plugin.getItemManager().addLore(chicken, " ", prize.replace("%prize%", "" + prize_int), null);
//
//        prize_int = shop.getDouble("shop.sections.spawner.contents.cow");
//        Survival.plugin.getItemManager().setItemMeta(cow, "Cow Spawner", 1);
//        Survival.plugin.getItemManager().addLore(cow, " ", prize.replace("%prize%", "" + prize_int), null);
//
//        prize_int = shop.getDouble("shop.sections.spawner.contents.cave_spider");
//        Survival.plugin.getItemManager().setItemMeta(caveSpider, "Cave Spider Spawner", 1);
//        Survival.plugin.getItemManager().addLore(caveSpider, " ", prize.replace("%prize%", "" + prize_int), null);
//
//        prize_int = shop.getDouble("shop.sections.spawner.contents.horse");
//        Survival.plugin.getItemManager().setItemMeta(horse, "Horse Spawner", 1);
//        Survival.plugin.getItemManager().addLore(horse, " ", prize.replace("%prize%", "" + prize_int), null);
//
//        prize_int = shop.getDouble("shop.sections.spawner.contents.pig");
//        Survival.plugin.getItemManager().setItemMeta(pig, "Pig Spawner", 1);
//        Survival.plugin.getItemManager().addLore(pig, " ", prize.replace("%prize%", "" + prize_int), null);
//
//        prize_int = shop.getDouble("shop.sections.spawner.contents.pigman");
//        Survival.plugin.getItemManager().setItemMeta(pigman, "Pigman Spawner", 1);
//        Survival.plugin.getItemManager().addLore(pigman, " ", prize.replace("%prize%", "" + prize_int), null);
//
//        prize_int = shop.getDouble("shop.sections.spawner.contents.slime");
//        Survival.plugin.getItemManager().setItemMeta(slime, "Slime Spawner", 1);
//        Survival.plugin.getItemManager().addLore(slime, " ", prize.replace("%prize%", "" + prize_int), null);
//
//        prize_int = shop.getDouble("shop.sections.spawner.contents.mushroom_cow");
//        Survival.plugin.getItemManager().setItemMeta(mushroomCow, "Mushroom Cow Spawner", 1);
//        Survival.plugin.getItemManager().addLore(mushroomCow, " ", prize.replace("%prize%", "" + prize_int), null);
//
//
//        ItemStack[] menu_items = {
//                vypln, vypln, vypln, vypln, vypln, vypln, vypln, vypln, vypln,
//                vypln, zombie, skeleton, spider, caveSpider, chicken, cow, sheep, vypln,
//                vypln, horse, pig, pigman, slime, mushroomCow, vypln, vypln, vypln,
//                vypln, vypln, vypln, vypln, vypln, vypln, vypln, vypln, vypln,
//                back, vypln, vypln, vypln, vypln, vypln, vypln, vypln, vypln
//        };
//        spawner.setContents(menu_items);
//        p.openInventory(spawner);
//    }
//
//    public void openOther(Player p){
//        shop = Survival.plugin.getConfigManager().getFile(ConfigType.SHOP).getConfig();
//
//        other_n = shop.getString("shop.sections.other.gui.name");
//        other_s = shop.getInt("shop.sections.other.gui.slots");
//
//        prize = shop.getString("shop.prize");
//
//        Inventory other = Bukkit.createInventory(null, other_s, TextUtil.color(other_n));
//
//        ItemStack vypln = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
//        ItemStack back = new ItemStack(Material.RED_BED);
//
//        ItemStack saddle = new ItemStack(Material.SADDLE);
//        ItemStack elytra = new ItemStack(Material.ELYTRA);
//        ItemStack ender_pearl = new ItemStack(Material.ENDER_PEARL);
//        ItemStack ender_eye = new ItemStack(Material.ENDER_EYE);
//        ItemStack nether_wart = new ItemStack(Material.NETHER_WART);
//        ItemStack brewing = new ItemStack(Material.BREWING_STAND);
//        ItemStack glowstone_dust = new ItemStack(Material.GLOWSTONE_DUST);
//        ItemStack bucket = new ItemStack(Material.BUCKET);
//        ItemStack water_bucket = new ItemStack(Material.WATER_BUCKET);
//        ItemStack lava_bucket = new ItemStack(Material.LAVA_BUCKET);
//        ItemStack blaze_rod = new ItemStack(Material.BLAZE_ROD);
//        ItemStack ender_chest = new ItemStack(Material.ENDER_CHEST);
//        ItemStack shulker_shell = new ItemStack(Material.SHULKER_SHELL);
//        ItemStack shulker_box = new ItemStack(Material.SHULKER_BOX);
//
//        ItemStack anvil = new ItemStack(Material.ANVIL);
//        ItemStack beacon = new ItemStack(Material.BEACON);
//        ItemStack chest = new ItemStack(Material.CHEST);
//        ItemStack crafting_table = new ItemStack(Material.CRAFTING_TABLE);
//        ItemStack furnace = new ItemStack(Material.FURNACE);
//        ItemStack blast_furnace = new ItemStack(Material.BLAST_FURNACE);
//        ItemStack minecart = new ItemStack(Material.MINECART);
//        ItemStack rail = new ItemStack(Material.RAIL);
//        ItemStack powered_rail = new ItemStack(Material.POWERED_RAIL);
//        ItemStack enchant = new ItemStack(Material.ENCHANTING_TABLE);
//        ItemStack book = new ItemStack(Material.BOOK);
//        ItemStack end_crystal = new ItemStack(Material.END_CRYSTAL);
//        ItemStack slime_ball = new ItemStack(Material.SLIME_BALL);
//        ItemStack honey_block = new ItemStack(Material.HONEY_BLOCK);
//
//        Survival.plugin.getItemManager().setItemMeta(back, "&cZpátky", 1);
//        Survival.plugin.getItemManager().setItemMeta(vypln, " ", 1);
//
//        prize_int = shop.getDouble("shop.sections.other.contents.saddle");
//        Survival.plugin.getItemManager().addLore(saddle, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.other.contents.elytra");
//        Survival.plugin.getItemManager().addLore(elytra, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.other.contents.ender_pearl");
//        Survival.plugin.getItemManager().addLore(ender_pearl, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.other.contents.ender_eye");
//        Survival.plugin.getItemManager().addLore(ender_eye, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.other.contents.nether_wart");
//        Survival.plugin.getItemManager().addLore(nether_wart, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.other.contents.brewing_stand");
//        Survival.plugin.getItemManager().addLore(brewing, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.other.contents.glowstone_dust");
//        Survival.plugin.getItemManager().addLore(glowstone_dust, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.other.contents.bucket");
//        Survival.plugin.getItemManager().addLore(bucket, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.other.contents.water_bucket");
//        Survival.plugin.getItemManager().addLore(water_bucket, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.other.contents.lava_bucket");
//        Survival.plugin.getItemManager().addLore(lava_bucket, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.other.contents.blaze_rod");
//        Survival.plugin.getItemManager().addLore(blaze_rod, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.other.contents.ender_chest");
//        Survival.plugin.getItemManager().addLore(ender_chest, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.other.contents.shulker_shell");
//        Survival.plugin.getItemManager().addLore(shulker_shell, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.other.contents.shulker_box");
//        Survival.plugin.getItemManager().addLore(shulker_box, " ", prize.replace("%prize%", "" + prize_int), null);
//
//        prize_int = shop.getDouble("shop.sections.other.contents.anvil");
//        Survival.plugin.getItemManager().addLore(anvil, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.other.contents.beacon");
//        Survival.plugin.getItemManager().addLore(beacon, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.other.contents.chest");
//        Survival.plugin.getItemManager().addLore(chest, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.other.contents.crafting_table");
//        Survival.plugin.getItemManager().addLore(crafting_table, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.other.contents.anvil");
//        Survival.plugin.getItemManager().addLore(anvil, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.other.contents.beacon");
//        Survival.plugin.getItemManager().addLore(beacon, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.other.contents.chest");
//        Survival.plugin.getItemManager().addLore(chest, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.other.contents.crafting_table");
//        Survival.plugin.getItemManager().addLore(crafting_table, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.other.contents.furnace");
//        Survival.plugin.getItemManager().addLore(furnace, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.other.contents.blast_furnace");
//        Survival.plugin.getItemManager().addLore(blast_furnace, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.other.contents.minecart");
//        Survival.plugin.getItemManager().addLore(minecart, " ", prize.replace("%prize%", "" + prize_int), null);
//        Survival.plugin.getItemManager().setItemMeta(rail, null, 16);
//        prize_int = shop.getDouble("shop.sections.other.contents.rail");
//        Survival.plugin.getItemManager().addLore(rail, " ", prize.replace("%prize%", "" + prize_int), null);
//        Survival.plugin.getItemManager().setItemMeta(powered_rail, null, 16);
//        prize_int = shop.getDouble("shop.sections.other.contents.powered_rail");
//        Survival.plugin.getItemManager().addLore(powered_rail, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.other.contents.enchant_table");
//        Survival.plugin.getItemManager().addLore(enchant, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.other.contents.book");
//        Survival.plugin.getItemManager().setItemMeta(book, null, 16);
//        Survival.plugin.getItemManager().addLore(book, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.other.contents.end_crystal");
//        Survival.plugin.getItemManager().addLore(end_crystal, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.other.contents.slime_ball");
//        Survival.plugin.getItemManager().addLore(slime_ball, " ", prize.replace("%prize%", "" + prize_int), null);
//        prize_int = shop.getDouble("shop.sections.other.contents.honey_block");
//        Survival.plugin.getItemManager().addLore(honey_block, " ", prize.replace("%prize%", "" + prize_int), null);
//
//
//        ItemStack[] menu_items = {
//                vypln, vypln, vypln, vypln, vypln, vypln, vypln, vypln, vypln,
//                vypln, saddle, elytra, ender_pearl, ender_eye, nether_wart, brewing, glowstone_dust, vypln,
//                vypln, blaze_rod, bucket, water_bucket, lava_bucket, ender_chest, shulker_shell, shulker_box, vypln,
//                vypln, beacon, anvil, crafting_table, furnace, blast_furnace, chest, rail, vypln,
//                vypln, powered_rail, enchant, book, end_crystal, slime_ball, honey_block, vypln, vypln,
//                back, vypln, vypln, vypln, vypln, vypln, vypln, vypln, vypln
//        };
//        other.setContents(menu_items);
//        p.openInventory(other);
//    }
}