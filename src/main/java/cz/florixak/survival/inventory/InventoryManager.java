package cz.florixak.survival.inventory;


import cz.florixak.survival.Survival;
import cz.florixak.survival.inventory.inventories.CustomGUI;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class InventoryManager {

    private Survival plugin;

    private Map<String, AbstractInventory> inventories;

    public InventoryManager() {
        inventories = new HashMap<>();
    }

    public void onEnable(Survival plugin) {
        this.plugin = plugin;

        loadCustomMenus();

        inventories.values().forEach(AbstractInventory::onEnable);

        plugin.getServer().getPluginManager().registerEvents(new InventoryListener(), plugin);
    }

    private void loadCustomMenus() {
        // SHOP - CREATE
        File directory1 = new File(plugin.getDataFolder().getAbsolutePath() + File.separator + "shop_menus");
        if (!directory1.exists()) {
            directory1.mkdir();

            File file0 = new File(plugin.getDataFolder().getAbsolutePath() + File.separator + "shop_menus", "blocks1.yml");
            try {
                file0.createNewFile();
                InputStream inputStream0 = this.plugin.getResource("blocks1.yml");
                byte[] buffer0 = new byte[inputStream0.available()];
                inputStream0.read(buffer0);
                OutputStream outputStream0 = new FileOutputStream(file0);
                outputStream0.write(buffer0);
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
            File file0_1 = new File(plugin.getDataFolder().getAbsolutePath() + File.separator + "shop_menus", "blocks2.yml");
            try {
                file0_1.createNewFile();
                InputStream inputStream01 = this.plugin.getResource("blocks2.yml");
                byte[] buffer01 = new byte[inputStream01.available()];
                inputStream01.read(buffer01);
                OutputStream outputStream01 = new FileOutputStream(file0_1);
                outputStream01.write(buffer01);
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
            File file0_2 = new File(plugin.getDataFolder().getAbsolutePath() + File.separator + "shop_menus", "blocks3.yml");
            try {
                file0_2.createNewFile();
                InputStream inputStream02 = this.plugin.getResource("blocks3.yml");
                byte[] buffer02 = new byte[inputStream02.available()];
                inputStream02.read(buffer02);
                OutputStream outputStream02 = new FileOutputStream(file0_2);
                outputStream02.write(buffer02);
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
            File file0_3 = new File(plugin.getDataFolder().getAbsolutePath() + File.separator + "shop_menus", "blocks4.yml");
            try {
                file0_3.createNewFile();
                InputStream inputStream03 = this.plugin.getResource("blocks4.yml");
                byte[] buffer03 = new byte[inputStream03.available()];
                inputStream03.read(buffer03);
                OutputStream outputStream03 = new FileOutputStream(file0_3);
                outputStream03.write(buffer03);
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
            File file0_4 = new File(plugin.getDataFolder().getAbsolutePath() + File.separator + "shop_menus", "blocks5.yml");
            try {
                file0_4.createNewFile();
                InputStream inputStream04 = this.plugin.getResource("blocks5.yml");
                byte[] buffer04 = new byte[inputStream04.available()];
                inputStream04.read(buffer04);
                OutputStream outputStream04 = new FileOutputStream(file0_4);
                outputStream04.write(buffer04);
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
            File file0_5 = new File(plugin.getDataFolder().getAbsolutePath() + File.separator + "shop_menus", "blocks6.yml");
            try {
                file0_5.createNewFile();
                InputStream inputStream05 = this.plugin.getResource("blocks6.yml");
                byte[] buffer05 = new byte[inputStream05.available()];
                inputStream05.read(buffer05);
                OutputStream outputStream05 = new FileOutputStream(file0_5);
                outputStream05.write(buffer05);
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
            File file0_6 = new File(plugin.getDataFolder().getAbsolutePath() + File.separator + "shop_menus", "blocks7.yml");
            try {
                file0_6.createNewFile();
                InputStream inputStream06 = this.plugin.getResource("blocks7.yml");
                byte[] buffer06 = new byte[inputStream06.available()];
                inputStream06.read(buffer06);
                OutputStream outputStream06 = new FileOutputStream(file0_6);
                outputStream06.write(buffer06);
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }

            File file1 = new File(plugin.getDataFolder().getAbsolutePath() + File.separator + "shop_menus", "armor.yml");
            try {
                file1.createNewFile();
                InputStream inputStream1 = this.plugin.getResource("armor.yml");
                byte[] buffer1 = new byte[inputStream1.available()];
                inputStream1.read(buffer1);
                OutputStream outputStream1 = new FileOutputStream(file1);
                outputStream1.write(buffer1);
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }

            File file2 = new File(plugin.getDataFolder().getAbsolutePath() + File.separator + "shop_menus", "shop.yml");
            try {
                file2.createNewFile();
                InputStream inputStream2 = this.plugin.getResource("shop.yml");
                byte[] buffer2 = new byte[inputStream2.available()];
                inputStream2.read(buffer2);
                OutputStream outputStream2 = new FileOutputStream(file2);
                outputStream2.write(buffer2);
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }

            File file3 = new File(plugin.getDataFolder().getAbsolutePath() + File.separator + "shop_menus", "drops.yml");
            try {
                file3.createNewFile();
                InputStream inputStream3 = this.plugin.getResource("drops.yml");
                byte[] buffer3 = new byte[inputStream3.available()];
                inputStream3.read(buffer3);
                OutputStream outputStream3 = new FileOutputStream(file3);
                outputStream3.write(buffer3);
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }

            File file4 = new File(plugin.getDataFolder().getAbsolutePath() + File.separator + "shop_menus", "tools1.yml");
            try {
                file4.createNewFile();
                InputStream inputStream4 = this.plugin.getResource("tools1.yml");
                byte[] buffer4 = new byte[inputStream4.available()];
                inputStream4.read(buffer4);
                OutputStream outputStream4 = new FileOutputStream(file4);
                outputStream4.write(buffer4);
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }

            File file5 = new File(plugin.getDataFolder().getAbsolutePath() + File.separator + "shop_menus", "other.yml");
            try {
                file5.createNewFile();
                InputStream inputStream5 = this.plugin.getResource("other.yml");
                byte[] buffer5 = new byte[inputStream5.available()];
                inputStream5.read(buffer5);
                OutputStream outputStream5 = new FileOutputStream(file5);
                outputStream5.write(buffer5);
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }

            File file6 = new File(plugin.getDataFolder().getAbsolutePath() + File.separator + "shop_menus", "food.yml");
            try {
                file6.createNewFile();
                InputStream inputStream6 = this.plugin.getResource("food.yml");
                byte[] buffer6 = new byte[inputStream6.available()];
                inputStream6.read(buffer6);
                OutputStream outputStream6 = new FileOutputStream(file6);
                outputStream6.write(buffer6);
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }

            File file7 = new File(plugin.getDataFolder().getAbsolutePath() + File.separator + "shop_menus", "amount.yml");
            try {
                file7.createNewFile();
                InputStream inputStream7 = this.plugin.getResource("amount.yml");
                byte[] buffer7 = new byte[inputStream7.available()];
                inputStream7.read(buffer7);
                OutputStream outputStream7 = new FileOutputStream(file7);
                outputStream7.write(buffer7);
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }

            File file8 = new File(plugin.getDataFolder().getAbsolutePath() + File.separator + "shop_menus", "garden.yml");
            try {
                file8.createNewFile();
                InputStream inputStream8 = this.plugin.getResource("garden.yml");
                byte[] buffer8 = new byte[inputStream8.available()];
                inputStream8.read(buffer8);
                OutputStream outputStream8 = new FileOutputStream(file8);
                outputStream8.write(buffer8);
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }

            File file9 = new File(plugin.getDataFolder().getAbsolutePath() + File.separator + "shop_menus", "tools2.yml");
            try {
                file9.createNewFile();
                InputStream inputStream9 = this.plugin.getResource("tools2.yml");
                byte[] buffer9 = new byte[inputStream9.available()];
                inputStream9.read(buffer9);
                OutputStream outputStream9 = new FileOutputStream(file9);
                outputStream9.write(buffer9);
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }

        }

        // JOBS - CREATE
        File directory2 = new File(plugin.getDataFolder().getAbsolutePath() + File.separator + "jobs_menus");
        if (!directory2.exists()) {
            directory2.mkdir();

            File file10 = new File(plugin.getDataFolder().getAbsolutePath() + File.separator + "jobs_menus", "jobs.yml");
            try {
                file10.createNewFile();
                InputStream inputStream10 = this.plugin.getResource("jobs.yml");
                byte[] buffer10 = new byte[inputStream10.available()];
                inputStream10.read(buffer10);
                OutputStream outputStream10 = new FileOutputStream(file10);
                outputStream10.write(buffer10);
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }

        // KITS - CREATE
        File directory3 = new File(plugin.getDataFolder().getAbsolutePath() + File.separator + "kits_menus");
        if (!directory3.exists()) {
            directory3.mkdir();

            File file11 = new File(plugin.getDataFolder().getAbsolutePath() + File.separator + "kits_menus", "kits.yml");
            try {
                file11.createNewFile();
                InputStream inputStream11 = this.plugin.getResource("kits.yml");
                byte[] buffer11 = new byte[inputStream11.available()];
                inputStream11.read(buffer11);
                OutputStream outputStream11 = new FileOutputStream(file11);
                outputStream11.write(buffer11);
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }

            File file12 = new File(plugin.getDataFolder().getAbsolutePath() + File.separator + "kits_menus", "hrac.yml");
            try {
                file12.createNewFile();
                InputStream inputStream12 = this.plugin.getResource("hrac.yml");
                byte[] buffer12 = new byte[inputStream12.available()];
                inputStream12.read(buffer12);
                OutputStream outputStream12 = new FileOutputStream(file12);
                outputStream12.write(buffer12);
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }

            File file13 = new File(plugin.getDataFolder().getAbsolutePath() + File.separator + "kits_menus", "astronaut.yml");
            try {
                file13.createNewFile();
                InputStream inputStream13 = this.plugin.getResource("astronaut.yml");
                byte[] buffer13 = new byte[inputStream13.available()];
                inputStream13.read(buffer13);
                OutputStream outputStream13 = new FileOutputStream(file13);
                outputStream13.write(buffer13);
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }

            File file14 = new File(plugin.getDataFolder().getAbsolutePath() + File.separator + "kits_menus", "galactic.yml");
            try {
                file14.createNewFile();
                InputStream inputStream14 = this.plugin.getResource("galactic.yml");
                byte[] buffer14 = new byte[inputStream14.available()];
                inputStream14.read(buffer14);
                OutputStream outputStream14 = new FileOutputStream(file14);
                outputStream14.write(buffer14);
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }

            File file15 = new File(plugin.getDataFolder().getAbsolutePath() + File.separator + "kits_menus", "blackhole.yml");
            try {
                file15.createNewFile();
                InputStream inputStream15 = this.plugin.getResource("blackhole.yml");
                byte[] buffer15 = new byte[inputStream15.available()];
                inputStream15.read(buffer15);
                OutputStream outputStream15 = new FileOutputStream(file15);
                outputStream15.write(buffer15);
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }


        // SHOP - LOAD
        File[] yamlFiles1 = new File(plugin.getDataFolder().getAbsolutePath() + File.separator + "shop_menus").listFiles((dir, name) -> name.toLowerCase().endsWith(".yml"));
        if (yamlFiles1 == null) return;
        for (File file : yamlFiles1) {
            String name = file.getName().replace(".yml", "");
            if (inventories.containsKey(name)) {
                plugin.getLogger().warning("Inventory with name '" + file.getName() + "' already exists, skipping duplicate..");
                continue;
            }

            CustomGUI customGUI;
            try {
                customGUI = new CustomGUI(plugin, YamlConfiguration.loadConfiguration(file));
            } catch (Exception e) {
                plugin.getLogger().severe("Could not load file '" + name + "' (YAML error).");
                e.printStackTrace();
                continue;
            }

            inventories.put(name, customGUI);
            plugin.getLogger().info("Loaded custom menu '" + name + "'.");
        }

        // JOBS - LOAD
        File[] yamlFiles2 = new File(plugin.getDataFolder().getAbsolutePath() + File.separator + "jobs_menus").listFiles((dir, name) -> name.toLowerCase().endsWith(".yml"));
        if (yamlFiles2 == null) return;
        for (File file : yamlFiles2) {
            String name = file.getName().replace(".yml", "");
            if (inventories.containsKey(name)) {
                plugin.getLogger().warning("Inventory with name '" + file.getName() + "' already exists, skipping duplicate..");
                continue;
            }

            CustomGUI customGUI;
            try {
                customGUI = new CustomGUI(plugin, YamlConfiguration.loadConfiguration(file));
            } catch (Exception e) {
                plugin.getLogger().severe("Could not load file '" + name + "' (YAML error).");
                e.printStackTrace();
                continue;
            }

            inventories.put(name, customGUI);
            plugin.getLogger().info("Loaded custom menu '" + name + "'.");
        }

        // KITS - LOAD
        File[] yamlFiles3 = new File(plugin.getDataFolder().getAbsolutePath() + File.separator + "kits_menus").listFiles((dir, name) -> name.toLowerCase().endsWith(".yml"));
        if (yamlFiles3 == null) return;
        for (File file : yamlFiles3) {
            String name = file.getName().replace(".yml", "");
            if (inventories.containsKey(name)) {
                plugin.getLogger().warning("Inventory with name '" + file.getName() + "' already exists, skipping duplicate..");
                continue;
            }

            CustomGUI customGUI;
            try {
                customGUI = new CustomGUI(plugin, YamlConfiguration.loadConfiguration(file));
            } catch (Exception e) {
                plugin.getLogger().severe("Could not load file '" + name + "' (YAML error).");
                e.printStackTrace();
                continue;
            }

            inventories.put(name, customGUI);
            plugin.getLogger().info("Loaded custom menu '" + name + "'.");
        }
    }

    public void addInventory(String key, AbstractInventory inventory) {
        inventories.put(key, inventory);
    }

    public Map<String, AbstractInventory> getInventories() {
        return inventories;
    }

    public AbstractInventory getInventory(String key) {
        return inventories.get(key);
    }

    public void onDisable() {
        inventories.values().forEach(abstractInventory -> {
            for (UUID uuid : abstractInventory.getOpenInventories()) {
                Player player = Bukkit.getPlayer(uuid);
                if (player != null) player.closeInventory();
            }
            abstractInventory.getOpenInventories().clear();
        });
        inventories.clear();
    }
}
