package cz.florixak.survival.manager;

import cz.florixak.survival.Survival;
import cz.florixak.survival.config.ConfigType;
import cz.florixak.survival.config.Messages;
import cz.florixak.survival.inventory.InventoryListener;
import cz.florixak.survival.utility.TextUtil;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.*;

public class JobsManager {

    public static ArrayList<UUID> nezamestnany = new ArrayList<UUID>();
    public static ArrayList<UUID> miner = new ArrayList<UUID>();
    public static ArrayList<UUID> digger = new ArrayList<UUID>();
    public static ArrayList<UUID> crafter = new ArrayList<UUID>();
    public static ArrayList<UUID> woodcutter = new ArrayList<UUID>();
    public static ArrayList<UUID> builder = new ArrayList<UUID>();
    public static ArrayList<UUID> farmer = new ArrayList<UUID>();
    public static ArrayList<UUID> killer = new ArrayList<UUID>();

    public static boolean isNezamestnany(UUID uuid) {
        return nezamestnany.contains(uuid);
    }
    public static boolean isMiner(UUID uuid) {
        return miner.contains(uuid);
    }
    public static boolean isDigger(UUID uuid) {
        return digger.contains(uuid);
    }
    public static boolean isCrafter(UUID uuid) {
        return crafter.contains(uuid);
    }
    public static boolean isWoodcutter(UUID uuid) {
        return woodcutter.contains(uuid);
    }
    public static boolean isBuilder(UUID uuid) {
        return builder.contains(uuid);
    }
    public static boolean isFarmer(UUID uuid) {
        return farmer.contains(uuid);
    }
    public static boolean isKiller(UUID uuid) {
        return killer.contains(uuid);
    }

    private Survival plugin;
    private FileConfiguration config;

    public JobsManager(Survival plugin) {
        this.plugin = plugin;
        config = plugin.getConfigManager().getFile(ConfigType.SETTINGS).getConfig();
    }

    public static String getJob(Player p) {
        if (isMiner(p.getUniqueId())) return "&fHorník - " + getJobLevel(p.getUniqueId()) + " lvl";
        if (isKiller(p.getUniqueId())) return "&fZabiják - " + getJobLevel(p.getUniqueId()) + " lvl";
        if (isDigger(p.getUniqueId())) return "&fKopáč - " + getJobLevel(p.getUniqueId()) + " lvl";
        if (isBuilder(p.getUniqueId())) return "&fStavitel - " + getJobLevel(p.getUniqueId()) + " lvl";
        if (isWoodcutter(p.getUniqueId())) return "&fDřeborubec - " + getJobLevel(p.getUniqueId()) + " lvl";
        if (isCrafter(p.getUniqueId())) return "&fŘemeslník - " + getJobLevel(p.getUniqueId()) + " lvl";
        if (isFarmer(p.getUniqueId())) return "&fFarmář - " + getJobLevel(p.getUniqueId()) + " lvl";
        return "&fNezaměstnaný";
    }

    public static String getJobLevel(UUID uuid) {
        if (isMiner(uuid)) {
            int data = Survival.plugin.data.getMinerBlocks(uuid);
            if (data < 500) {
                return "0";
            } else if (data < 1000) {
                return "1";
            } else if (data < 1500) {
                return "2";
            } else if (data < 2000) {
                return "3";
            }
        }
        if (isBuilder(uuid)) {
            int data = Survival.plugin.data.getBuilderBlocks(uuid);
            if (data < 250) {
                return "0";
            } else if (data < 500) {
                return "1";
            } else if (data < 750) {
                return "2";
            } else if (data < 1000) {
                return "3";
            }
        }
        if (isWoodcutter(uuid)) {
            int data = Survival.plugin.data.getWoodcutterBlocks(uuid);
            if (data < 500) {
                return "0";
            } else if (data < 750) {
                return "1";
            } else if (data < 1000) {
                return "2";
            } else if (data < 1250) {
                return "3";
            }
        }
        if (isDigger(uuid)) {
            int data = Survival.plugin.data.getDiggerBlocks(uuid);
            if (data < 500) {
                return "0";
            } else if (data < 1000) {
                return "1";
            } else if (data < 1500) {
                return "2";
            } else if (data < 2000) {
                return "3";
            }
        }
        if (isCrafter(uuid)) {
            int data = Survival.plugin.data.getCrafterItems(uuid);
            if (data < 250) {
                return "0";
            } else if (data < 500) {
                return "1";
            } else if (data < 750) {
                return "2";
            } else if (data < 1000) {
                return "3";
            }
        }
        if (isKiller(uuid)) {
            int data = Survival.plugin.data.getKillerKills(uuid);
            if (data < 250) {
                return "0";
            } else if (data < 500) {
                return "1";
            } else if (data < 750) {
                return "2";
            } else if (data < 1000) {
                return "3";
            }
        }
        if (isFarmer(uuid)) {
            int data = Survival.plugin.data.getFarmerBlocks(uuid);
            if (data < 250) {
                return "0";
            } else if (data < 500) {
                return "1";
            } else if (data < 750) {
                return "2";
            } else if (data < 1000) {
                return "3";
            }
        }
        return "";
    }

    public void ifNoJob(Player p) {
        if (!(isNezamestnany(p.getUniqueId())
                || isMiner(p.getUniqueId())
                || isDigger(p.getUniqueId())
                || isWoodcutter(p.getUniqueId())
                || isKiller(p.getUniqueId())
                || isFarmer(p.getUniqueId())
                || isCrafter(p.getUniqueId())
                || isBuilder(p.getUniqueId()))) {
            nezamestnany.add(p.getUniqueId());
            plugin.data.resetJobs(p.getUniqueId(), "*");
        }
    }

    public void leaveJob(Player p) {
        if (isNezamestnany(p.getUniqueId())) {
            p.sendMessage(Messages.JOBS_NOT_WORK.toString());
            return;
        }
        nezamestnany.add(p.getUniqueId());
        p.sendMessage(Messages.JOBS_RETIRED.toString().replace("%job_name%", TextUtil.color(getJob(p))));
        if (isMiner(p.getUniqueId())) {
            if (isMiner(p.getUniqueId())) miner.remove(p.getUniqueId());
            plugin.data.resetJobs(p.getUniqueId(), "miner");
            plugin.data.createPlayer(p);
        }
        if (isCrafter(p.getUniqueId())) {
            crafter.remove(p.getUniqueId());
            plugin.data.resetJobs(p.getUniqueId(), "crafter");
            plugin.data.createPlayer(p);
        }
        if (isKiller(p.getUniqueId())) {
            killer.remove(p.getUniqueId());
            plugin.data.resetJobs(p.getUniqueId(), "killer");
            plugin.data.createPlayer(p);
        }
        if (isDigger(p.getUniqueId())) {
            digger.remove(p.getUniqueId());
            plugin.data.resetJobs(p.getUniqueId(), "digger");
            plugin.data.createPlayer(p);
        }
        if (isWoodcutter(p.getUniqueId())) {
            woodcutter.remove(p.getUniqueId());
            plugin.data.resetJobs(p.getUniqueId(), "woodcutter");
            plugin.data.createPlayer(p);
        }
        if (isFarmer(p.getUniqueId())) {
            farmer.remove(p.getUniqueId());
            plugin.data.resetJobs(p.getUniqueId(), "farmer");
            plugin.data.createPlayer(p);
        }
        if (isBuilder(p.getUniqueId())) {
            builder.remove(p.getUniqueId());
            plugin.data.resetJobs(p.getUniqueId(), "builder");
            plugin.data.createPlayer(p);
        }
    }

    public void signAsMiner(Player p) {
        if (!(isMiner(p.getUniqueId()))) {
            if (isNezamestnany(p.getUniqueId())) {
                nezamestnany.remove(p.getUniqueId());
                miner.add(p.getUniqueId());
                p.sendMessage(Messages.JOBS_SELECTED.toString().replace("%job_name%", TextUtil.color(InventoryListener.itemStack.getItemMeta().getDisplayName())));
            } else {
                p.sendMessage(Messages.JOBS_MAX.toString());
            }
        } else {
            p.sendMessage(Messages.JOBS_ALREADY.toString());
        }
    }
    public void signAsWoodcutter(Player p) {
        if (!(isWoodcutter(p.getUniqueId()))) {
            if (isNezamestnany(p.getUniqueId())) {
                nezamestnany.remove(p.getUniqueId());
                woodcutter.add(p.getUniqueId());
                p.sendMessage(Messages.JOBS_SELECTED.toString().replace("%job_name%", TextUtil.color(InventoryListener.itemStack.getItemMeta().getDisplayName())));
            } else {
                p.sendMessage(Messages.JOBS_MAX.toString());
            }
        } else {
            p.sendMessage(Messages.JOBS_ALREADY.toString());
        }
    }
    public void signAsDigger(Player p) {
        if (!(isDigger(p.getUniqueId()))) {
            if (isNezamestnany(p.getUniqueId())) {
                nezamestnany.remove(p.getUniqueId());
                digger.add(p.getUniqueId());
                p.sendMessage(Messages.JOBS_SELECTED.toString().replace("%job_name%", TextUtil.color(InventoryListener.itemStack.getItemMeta().getDisplayName())));
            } else {
                p.sendMessage(Messages.JOBS_MAX.toString());
            }
        } else {
            p.sendMessage(Messages.JOBS_ALREADY.toString());
        }
    }
    public void signAsKiller(Player p) {
        if (!(isKiller(p.getUniqueId()))) {
            if (isNezamestnany(p.getUniqueId())) {
                nezamestnany.remove(p.getUniqueId());
                killer.add(p.getUniqueId());
                p.sendMessage(Messages.JOBS_SELECTED.toString().replace("%job_name%", TextUtil.color(InventoryListener.itemStack.getItemMeta().getDisplayName())));
            } else {
                p.sendMessage(Messages.JOBS_MAX.toString());
            }
        } else {
            p.sendMessage(Messages.JOBS_ALREADY.toString());
        }
    }
    public void signAsCrafter(Player p) {
        if (!(isCrafter(p.getUniqueId()))) {
            if (isNezamestnany(p.getUniqueId())) {
                nezamestnany.remove(p.getUniqueId());
                crafter.add(p.getUniqueId());
                p.sendMessage(Messages.JOBS_SELECTED.toString().replace("%job_name%", TextUtil.color(InventoryListener.itemStack.getItemMeta().getDisplayName())));
            } else {
                p.sendMessage(Messages.JOBS_MAX.toString());
            }
        } else {
            p.sendMessage(Messages.JOBS_ALREADY.toString());
        }
    }
    public void signAsBuilder(Player p) {
        if (!(isBuilder(p.getUniqueId()))) {
            if (isNezamestnany(p.getUniqueId())) {
                nezamestnany.remove(p.getUniqueId());
                builder.add(p.getUniqueId());
                p.sendMessage(Messages.JOBS_SELECTED.toString().replace("%job_name%", TextUtil.color(InventoryListener.itemStack.getItemMeta().getDisplayName())));
            } else {
                p.sendMessage(Messages.JOBS_MAX.toString());
            }
        } else {
            p.sendMessage(Messages.JOBS_ALREADY.toString());
        }
    }
    public void signAsFarmer(Player p) {
        if (!(isFarmer(p.getUniqueId()))) {
            if (isNezamestnany(p.getUniqueId())) {
                nezamestnany.remove(p.getUniqueId());
                farmer.add(p.getUniqueId());
                p.sendMessage(Messages.JOBS_SELECTED.toString().replace("%job_name%", TextUtil.color(InventoryListener.itemStack.getItemMeta().getDisplayName())));
            } else {
                p.sendMessage(Messages.JOBS_MAX.toString());
            }
        } else {
            p.sendMessage(Messages.JOBS_ALREADY.toString());
        }
    }
}