package cz.florixak.survival.listeners;

import cz.florixak.survival.Survival;
import cz.florixak.survival.config.ConfigType;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class TeleportManager {

    private FileConfiguration config;
    public HashMap<UUID, UUID> requests = new HashMap<>();
    private HashMap<UUID, Integer> cmd_cooldown = new HashMap<>();
    private int tpa_delay;
    private int cmd_cooldown_delay;

    public TeleportManager(Survival plugin) {
        this.config = plugin.getConfigManager().getFile(ConfigType.SETTINGS).getConfig();

        this.cmd_cooldown_delay = config.getInt("tpa.cooldown");
        this.tpa_delay = config.getInt("tpa.tp_delay");
    }

    public int getTpaDelay() {
        return tpa_delay;
    }
    public int getCmdCooldown() {
        return cmd_cooldown_delay;
    }

    public void addPlayerCmdCooldown(Player p, int cd) {
        cmd_cooldown.put(p.getUniqueId(), cd);
    }
    public int getPlayerCmdCooldown(Player p) {
        return cmd_cooldown.get(p.getUniqueId());
    }
    public void removePlayerCmdCooldown(Player p) {
        cmd_cooldown.remove(p.getUniqueId());
    }
    public boolean containsKey(Player p) {
        return cmd_cooldown.containsKey(p.getUniqueId());
    }

    public void clearRequests() {
        requests.clear();
    }
    public void removeRequest(Player player) {
        requests.remove(player.getUniqueId());
    }
    public void setRequest(Player target, Player player) {
        requests.put(target.getUniqueId(), player.getUniqueId());
    }
    public UUID getRequest(Player player) {
        return requests.get(player.getUniqueId());
    }

}
