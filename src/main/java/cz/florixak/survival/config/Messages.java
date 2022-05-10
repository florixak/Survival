package cz.florixak.survival.config;

import cz.florixak.survival.utility.TextUtil;
import org.bukkit.configuration.file.FileConfiguration;

public enum Messages {

    PREFIX("PREFIX"),
    NO_PERM("NO_PERM"),
    NOT_PLAYER("NOT_PLAYER"),
    OFFLINE_PLAYER("OFFLINE_PLAYER"),
    NO_MONEY("NO_MONEY"),
    BLOCK_CANT_PLACE("BLOCKS.CANT_PLACE"),
    BLOCK_CANT_DESTROY("BLOCKS.CANT_DESTROY"),
    CANT_FIGHT("PVP.CANT_FIGHT"),
    WHEAT_GROW("BLOCKS.WHEAT_GROW"),
    CANT_DROP("DROP_ITEMS.DISABLED_MESSAGE"),

    PLAYER_JOIN("JOIN.MESSAGE"),
    PLAYER_QUIT("QUIT.MESSAGE"),

    JOIN_TITLE("JOIN.TITLE"),

    CHAT_COOLDOWN("CHAT.COOLDOWN"),

    MONEY("MONEY.MESSAGE"),

    RANDOMTP_NEW_COORDS("RANDOMTP.NEW_COORDS"),
    RANDOMTP_WAIT("RANDOMTP.WAIT"),
    RANDOMTP_YOU_CANT("RANDOMTP.YOU_CANT"),

    TPA_USAGE("TPA.USAGE"),
    TPA_SENT("TPA.SENT"),
    TPA_GAIN("TPA.GAIN"),
    TPA_ACCEPTED("TPA.ACCEPTED"),
    TPA_DENIED("TPA.DENIED"),
    TPA_NO_TP("TPA.NO_TP"),
    TPA_COOLDOWN("TPA.COOLDOWN"),
    TPA_CANT_TP_TO_SELF("TPA.CANT_TP_TO_SELF"),

    TELEPORTING("TELEPORT.TELEPORTING"),
    YOU_MOVED("TELEPORT.YOU_MOVED"),
    TELEPORT_BLOCKED("TELEPORT.BLOCKED"),
    TELEPORT_ALLOWED("TELEPORT.ALLOWED"),
    TELEPORT_BLOCKED_PLAYER("TELEPORT.BLOCKED_PLAYER"),
    TELEPORT_TELEPORTED("TELEPORT.TELEPORTED"),
    CANNOT_TELEPORT_NOW("TELEPORT.CANNOT_TELEPORT_NOW"),

    TP_USAGE("TP.USAGE"),

    HOME_USAGE_SETHOME("HOME.USAGE.SETHOME"),
    HOME_USAGE_HOME("HOME.USAGE.HOME"),
    HOME_USAGE_DELHOME("HOME.USAGE.DELHOME"),
    HOME_CREATED("HOME.CREATED"),
    HOME_DELETED("HOME.DELETED"),
    HOME_ALREADY_EXISTS("HOME.ALREADY_EXISTS"),
    HOME_NO_EXISTS("HOME.NO_EXISTS"),
    HOME_TELEPORTED("HOME.TELEPORTED"),
    HOME_NO_HOMES("HOME.NO_HOMES"),
    HOME_LIST("HOME.HOME_LIST"),
    HOME_MAX_HOMES("HOME.MAX_HOMES"),

    FLY_USAGE("FLY.USAGE"),
    FLY_ENABLED("FLY.ENABLED"),
    FLY_DISABLED("FLY.DISABLED"),
    FLY_WARN_MSG("FLY.WARN_MESSAGE"),
    FLY_ALREADY_ACTIVATED("FLY.ALREADY_ACTIVATED"),
    FLY_DAY_COOLDOWN_MSG("FLY.DAY_COOLDOWN_MESSAGE"),

    RESPAWN_BED_SPAWN_SET("RESPAWN.BED_SPAWN_SET"),
    RESPAWN_NO_BED_SPAWN("RESPAWN.NO_BED_SPAWN"),

    SPAWN_SET("SPAWN.SET"),
    SPAWN_DELETED("SPAWN.DELETED"),
    SPAWN_ALREADY_SET("SPAWN.ALREADY_SET"),
    SPAWN_TELEPORTED("SPAWN.TELEPORTED_MESSAGE"),
    SPAWN_NOT_EXIST("SPAWN.NOT_EXISTS"),

    HEAL_USAGE("HEAL.USAGE"),
    HEAL_BOUGHT("HEAL.BOUGHT"),
    HEAL_FULL_HP("HEAL.FULL_HP"),
    HEAL_WAIT("HEAL.WAIT_MESSAGE"),

    HELP_USAGE("HELP.USAGE"),

    HELP_TITLE1("HELP.TITLE.HELP_1"),
    HELP_TITLE2("HELP.TITLE.HELP_2"),
    HELP_TITLE3("HELP.TITLE.HELP_3"),
    HELP_ECO_TITLE("HELP.TITLE.ECONOMY"),
    HELP_HOME_TITLE("HELP.TITLE.HOME"),
    HELP_WARP_TITLE("HELP.TITLE.WARP"),
    HELP_TPA_TITLE("HELP.TITLE.TPA"),

    HELP_HELP1_LINE1("HELP.HELP_1.LINE1"),
    HELP_HELP1_LINE2("HELP.HELP_1.LINE2"),
    HELP_HELP1_LINE3("HELP.HELP_1.LINE3"),
    HELP_HELP1_LINE4("HELP.HELP_1.LINE4"),
    HELP_HELP1_LINE5("HELP.HELP_1.LINE5"),
    HELP_HELP2_LINE1("HELP.HELP_2.LINE1"),
    HELP_HELP2_LINE2("HELP.HELP_2.LINE2"),
    HELP_HELP2_LINE3("HELP.HELP_2.LINE3"),
    HELP_HELP2_LINE4("HELP.HELP_2.LINE4"),
    HELP_HELP2_LINE5("HELP.HELP_2.LINE5"),
    HELP_HELP3_LINE1("HELP.HELP_3.LINE1"),
    HELP_HELP3_LINE2("HELP.HELP_3.LINE2"),
    HELP_HELP3_LINE3("HELP.HELP_3.LINE3"),
    HELP_HELP3_LINE4("HELP.HELP_3.LINE4"),
    HELP_HELP3_LINE5("HELP.HELP_3.LINE5"),

    HELP_ECO_LINE1("HELP.ECONOMY.LINE1"),
    HELP_ECO_LINE2("HELP.ECONOMY.LINE2"),
    HELP_ECO_LINE3("HELP.ECONOMY.LINE3"),
    HELP_ECO_LINE4("HELP.ECONOMY.LINE4"),

    HELP_HOME_LINE1("HELP.HOME.LINE1"),
    HELP_HOME_LINE2("HELP.HOME.LINE2"),
    HELP_HOME_LINE3("HELP.HOME.LINE3"),
    HELP_HOME_LINE4("HELP.HOME.LINE4"),
    HELP_HOME_LINE5("HELP.HOME.LINE5"),

    HELP_WARP_LINE1("HELP.WARP.LINE1"),
    HELP_WARP_LINE2("HELP.WARP.LINE2"),
    HELP_WARP_LINE3("HELP.WARP.LINE3"),
    HELP_WARP_LINE4("HELP.WARP.LINE4"),
    HELP_WARP_LINE5("HELP.WARP.LINE5"),

    HELP_TPA_LINE1("HELP.TPA.LINE1"),
    HELP_TPA_LINE2("HELP.TPA.LINE2"),
    HELP_TPA_LINE3("HELP.TPA.LINE3"),
    HELP_TPA_LINE4("HELP.TPA.LINE4"),
    HELP_TPA_LINE5("HELP.TPA.LINE5"),

    STATS_TITLE("STATISTICS.TITLE"),
    STATS_USAGE("STATISTICS.USAGE"),
    STATS_SPECIFY_PLAYER("STATISTICS.SPECIFY_PLAYER"),
    STATS_NAME("STATISTICS.STATS.NAME"),
    STATS_MONEY("STATISTICS.STATS.MONEY"),
    STATS_PLAYER_KILLED("STATISTICS.STATS.PLAYER_KILLED"),
    STATS_DEATHS("STATISTICS.STATS.DEATHS"),
    STATS_MOB_KILLED("STATISTICS.STATS.MOB_KILLED"),

    WARP_USAGE("WARP.USAGE.WARP"),
    WARP_SETWARP_USAGE("WARP.USAGE.SETWARP"),
    WARP_DELWARP_USAGE("WARP.USAGE.DELWARP"),
    WARP_TELEPORTED("WARP.TELEPORTED"),
    WARP_ALREADY_EXISTS("WARP.ALREADY_EXISTS"),
    WARP_NOT_EXISTS("WARP.NOT_EXISTS"),
    WARP_NO_WARPS("WARP.NO_WARPS"),
    WARP_CREATED("WARP.CREATED"),
    WARP_DELETED("WARP.DELETED"),
    WARP_LIST("WARP.LIST"),

    ROULETTE_OPENING("ROULETTE.OPENING"),
    ROULETTE_NO_DIA("ROULETTE.NO_DIA"),
    ROULETTE_WINNER("ROULETTE.WINNER"),
    ROULETTE_LOSE("ROULETTE.LOSE"),
    ROULETTE_FULL_INV("ROULETTE.FULL_INVENTORY"),

    KITS_COOLDOWN_MSG("KITS.COOLDOWNS"),
    KITS_BOUGHT("KITS.BOUGHT"),
    KITS_USAGE("KITS.USAGE"),

    WB_OPEN("WORKBENCH.OPEN"),
    ANVIL_OPEN("ANVIL.OPEN"),
    ENDER_CHEST_OPEN("ENDER_CHEST.OPEN"),

    GAMEMODE_CHANGED("GAMEMODE.CHANGED"),

    CLEAR_CLEARED("CLEAR.CLEARED"),

    DEATH_MSG_PLAYER_KILLED("DEATH.MESSAGE.KILLED"),
    DEATH_MSG("DEATH.MESSAGE.DEATH"),
    DEATH_YOU_DIED("DEATH.MESSAGE.YOU_DIED"),
    DEATH_MSG_YOU_HAVE_KILLED("DEATH.MESSAGE.YOU_HAVE_KILLED"),
    DEATH_MSG_YOU_WERE_KILLED("DEATH.MESSAGE.YOU_WERE_KILLED"),

    JOBS_OPENING("JOBS.OPENING"),
    JOBS_SELECTED("JOBS.SELECTED"),
    JOBS_RETIRED("JOBS.RETIRED"),
    JOBS_MAX("JOBS.MAX"),
    JOBS_ALREADY("JOBS.ALREADY"),
    JOBS_NOT_WORK("JOBS.NOT_WORK"),
    JOBS_GAIN_MONEY("JOBS.GAIN_MONEY"),
    JOBS_NO_JOB_MONEY("JOBS.DAVKY"),
    JOBS_LEVEL_UP("JOBS.LEVEL_UP"),

    COORDINATES_ON("COORDINATES.ALLOWED"),
    COORDINATES_OFF("COORDINATES.DISABLED"),

    SHOP_OPENING("SHOP.OPENING"),
    SHOP_BOUGHT("SHOP.BOUGHT"),

    FULL_INVENTORY("FULL_INVENTORY"),

    PVPARENA_ENTER("PVPARENA.ENTER"),
    PVPARENA_LEAVE("PVPARENA.LEAVE"),
    PVPARENA_SET("PVPARENA.SET"),
    PVPARENA_EXIST("PVPARENA.EXIST"),
    PVPARENA_NOT_EXIST("PVPARENA.NOT_EXIST"),
    PVPARENA_DELETED("PVPARENA.DELETED"),

    RELOAD_RELOADED("RELOAD.RELOADED"),
    RELOAD_USAGE("RELOAD.USAGE");


    private static FileConfiguration config;
    private String path;

    Messages(String path) {
        this.path = path;
    }

    public static void setConfiguration(FileConfiguration c) {
        config = c;
    }

    @Override
    public String toString() {
        String message = config.getString("Messages." + this.path);

        if (message == null || message.isEmpty()) {
            return "Survival: message not found (" + this.path + ")";
        }

        String prefix = config.getString("Messages." + PREFIX.getPath());
        return TextUtil.color(message.replace("%prefix%", prefix != null && !prefix.isEmpty() ? prefix : ""));
    }

    public String getPath() {
        return this.path;
    }


}