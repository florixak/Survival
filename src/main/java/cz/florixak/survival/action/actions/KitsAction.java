package cz.florixak.survival.action.actions;

import cz.florixak.survival.Survival;
import cz.florixak.survival.action.Action;
import cz.florixak.survival.config.ConfigType;
import cz.florixak.survival.config.Messages;
import cz.florixak.survival.inventory.InventoryListener;
import cz.florixak.survival.manager.KitsManager;
import cz.florixak.survival.utility.TextUtil;
import cz.florixak.survival.utility.TimeConvertor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.UUID;

public class KitsAction implements Action {

    public static HashMap<UUID, Integer> starter_cooldown = new HashMap<>();
    public static HashMap<UUID, Integer> knight_cooldown = new HashMap<>();
    public static HashMap<UUID, Integer> king_cooldown = new HashMap<>();
    public static HashMap<UUID, Integer> farmer_cooldown = new HashMap<>();
    public static HashMap<UUID, Integer> enchanter_cooldown = new HashMap<>();
    public static HashMap<UUID, Integer> cisar_cooldown = new HashMap<>();

    private int starter_cooldown_time;
    private int farmer_cooldown_time;
    private int knight_cooldown_time;
    private int king_cooldown_time;
    private int enchanter_cooldown_time;
    private int cisar_cooldown_time;

    private FileConfiguration config;

    @Override
    public String getIdentifier() {
        return "KITS";
    }

    @Override
    public void execute(Survival plugin, Player player, String data) {

        config = plugin.getConfigManager().getFile(ConfigType.SETTINGS).getConfig();

        KitsManager kitsManager = Survival.plugin.getKitsManager();

        UUID uuid = player.getUniqueId();

        starter_cooldown_time = config.getInt("kits.cooldowns.starter");
        farmer_cooldown_time = config.getInt("kits.cooldowns.farmer");
        knight_cooldown_time = config.getInt("kits.cooldowns.knight");
        king_cooldown_time = config.getInt("kits.cooldowns.king");
        enchanter_cooldown_time = config.getInt("kits.cooldowns.enchanter");
        cisar_cooldown_time = config.getInt("kits.cooldowns.cisar");

        if (data.equals("starter")) {
            if (!starter_cooldown.containsKey(uuid)) {
                kitsManager.starterToInv(player);

                player.sendMessage(Messages.KITS_BOUGHT.toString().replace("%kit_name%", TextUtil.color(InventoryListener.itemStack.getItemMeta().getDisplayName())));
                starter_cooldown.put(uuid, starter_cooldown_time);

                new BukkitRunnable(){

                    @Override
                    public void run() {
                        if (starter_cooldown.get(uuid) == 0){
                            starter_cooldown.remove(uuid);
                            cancel();
                            return;
                        }
                        starter_cooldown.put(uuid, starter_cooldown.get(uuid)-1);
                    }
                }.runTaskTimerAsynchronously(plugin, 0L, 20L);

            } else {
                player.sendMessage(Messages.KITS_COOLDOWN_MSG.toString().replace("%kit_name%", InventoryListener.itemStack.getItemMeta().getDisplayName())
                        .replace("%time_remaining%", "" + TimeConvertor.convertDay(starter_cooldown.get(uuid))));
            }
        }
        if (data.equals("farmer")) {
            if (!farmer_cooldown.containsKey(uuid)) {

                player.sendMessage(Messages.KITS_BOUGHT.toString().replace("%kit_name%", TextUtil.color(InventoryListener.itemStack.getItemMeta().getDisplayName())));
                kitsManager.farmerToInv(player);

                farmer_cooldown.put(uuid, farmer_cooldown_time);

                new BukkitRunnable(){
                    @Override
                    public void run() {

                        if (farmer_cooldown.get(uuid) == 0){
                            farmer_cooldown.remove(uuid);
                            cancel();
                            return;
                        }
                        farmer_cooldown.put(uuid, farmer_cooldown.get(uuid)-1);
                    }
                }.runTaskTimerAsynchronously(plugin, 0L, 20L);

            } else {
                player.sendMessage(Messages.KITS_COOLDOWN_MSG.toString().replace("%kit_name%", TextUtil.color(InventoryListener.itemStack.getItemMeta().getDisplayName()))
                        .replace("%time_remaining%", "" + TimeConvertor.convertDay(farmer_cooldown.get(uuid))));
            }
        }
        if (data.equals("king")) {
            if (!king_cooldown.containsKey(uuid)) {

                player.sendMessage(Messages.KITS_BOUGHT.toString().replace("%kit_name%", TextUtil.color(InventoryListener.itemStack.getItemMeta().getDisplayName())));
                kitsManager.kingToInv(player);

                king_cooldown.put(uuid, king_cooldown_time);

                new BukkitRunnable(){
                    @Override
                    public void run() {
                        if (king_cooldown.get(uuid) == 0){
                            king_cooldown.remove(uuid);
                            cancel();
                            return;
                        }
                        king_cooldown.put(uuid, king_cooldown.get(uuid)-1);
                    }
                }.runTaskTimerAsynchronously(plugin, 0L, 20L);

            } else {
                player.sendMessage(Messages.KITS_COOLDOWN_MSG.toString().replace("%kit_name%", TextUtil.color(InventoryListener.itemStack.getItemMeta().getDisplayName()))
                        .replace("%time_remaining%", "" + TimeConvertor.convertDay(king_cooldown.get(uuid))));
            }
        }
        if (data.equals("knight")) {
            if (!knight_cooldown.containsKey(uuid)) {
                player.sendMessage(Messages.KITS_BOUGHT.toString().replace("%kit_name%", TextUtil.color(InventoryListener.itemStack.getItemMeta().getDisplayName())));
                kitsManager.knightToInv(player);

                knight_cooldown.put(uuid, knight_cooldown_time);

                new BukkitRunnable(){
                    @Override
                    public void run() {
                        if (knight_cooldown.get(uuid) == 0){
                            knight_cooldown.remove(uuid);
                            cancel();
                            return;
                        }
                        knight_cooldown.put(uuid, knight_cooldown.get(uuid)-1);
                    }
                }.runTaskTimerAsynchronously(plugin, 0L, 20L);
            } else {
                player.sendMessage(Messages.KITS_COOLDOWN_MSG.toString().replace("%kit_name%", TextUtil.color(InventoryListener.itemStack.getItemMeta().getDisplayName()))
                        .replace("%time_remaining%", "" + TimeConvertor.convertDay(knight_cooldown.get(uuid))));
            }
        }
        if (data.equals("cisar")) {
            if (!cisar_cooldown.containsKey(uuid)) {

                player.sendMessage(Messages.KITS_BOUGHT.toString().replace("%kit_name%", TextUtil.color(InventoryListener.itemStack.getItemMeta().getDisplayName())));
                kitsManager.cisarToInv(player);

                cisar_cooldown.put(uuid, cisar_cooldown_time);

                new BukkitRunnable(){
                    @Override
                    public void run() {
                        if (cisar_cooldown.get(uuid) == 0){
                            cisar_cooldown.remove(uuid);
                            cancel();
                            return;
                        }
                        cisar_cooldown.put(uuid, cisar_cooldown.get(uuid)-1);
                    }
                }.runTaskTimerAsynchronously(plugin, 0L, 20L);
            } else {
                player.sendMessage(Messages.KITS_COOLDOWN_MSG.toString().replace("%kit_name%", TextUtil.color(InventoryListener.itemStack.getItemMeta().getDisplayName()))
                        .replace("%time_remaining%", "" + TimeConvertor.convertDay(cisar_cooldown.get(uuid))));
            }
        }
        if (data.equals("enchanter")) {
            if (!enchanter_cooldown.containsKey(uuid)) {

                player.sendMessage(Messages.KITS_BOUGHT.toString().replace("%kit_name%", TextUtil.color(InventoryListener.itemStack.getItemMeta().getDisplayName())));
                kitsManager.enchToInv(player);

                enchanter_cooldown.put(uuid, enchanter_cooldown_time);

                new BukkitRunnable(){
                    @Override
                    public void run() {
                        if (enchanter_cooldown.get(uuid) == 0){
                            enchanter_cooldown.remove(uuid);
                            cancel();
                            return;
                        }
                        enchanter_cooldown.put(uuid, enchanter_cooldown.get(uuid)-1);

                    }
                }.runTaskTimerAsynchronously(plugin, 0L, 20L);

            } else {

                player.sendMessage(Messages.KITS_COOLDOWN_MSG.toString().replace("%kit_name%", TextUtil.color(InventoryListener.itemStack.getItemMeta().getDisplayName()))
                        .replace("%time_remaining%", "" + TimeConvertor.convertDay(enchanter_cooldown.get(uuid))));
            }
        }
    }
}