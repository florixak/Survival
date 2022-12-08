package cz.florixak.survival.utility.placeholderapi;

import cz.florixak.survival.Survival;
import cz.florixak.survival.action.ActionManager;
import cz.florixak.survival.action.actions.KitsAction;
import cz.florixak.survival.manager.JobsManager;
import cz.florixak.survival.manager.EconomyManager;
import cz.florixak.survival.utility.TextUtil;
import cz.florixak.survival.utility.TimeConvertor;
import cz.florixak.survival.utility.Utils;
import me.clip.placeholderapi.PlaceholderAPI;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.group.Group;
import net.luckperms.api.model.user.User;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class PlaceholderUtil {

    public static boolean PAPI;

    public static String setPlaceholders(String text, Player player) {

        JobsManager jobsManager = Survival.plugin.getJobsManager();
        EconomyManager economyManager = Survival.plugin.getEconomyManager();

        User user = LuckPermsProvider.get().getUserManager().getUser(player.getUniqueId());
        String prefix = user.getCachedData().getMetaData().getPrefix();
        Group group = LuckPermsProvider.get().getGroupManager().getGroup("sr.builder");

        if (text.contains("%player%") && player != null)
            text = text.replace("%player%", TextUtil.color(prefix) + "" + player.getName());

        if (text.contains("%ping%"))
            text = text.replace("%ping%", "" + player.getPing() + " ms");

        if (text.contains("%online%"))
            text = text.replace("%online%", String.valueOf(Bukkit.getServer().getOnlinePlayers().size()));

        if (text.contains("%online_max%"))
            text = text.replace("%online_max%", String.valueOf(Bukkit.getServer().getMaxPlayers()));

//        if (text.contains("%game_time%")) {
//            text = text.replace("%game_time%", TimeConvertor.convertTime(Bukkit.getServer().getWorld(player.getWorld().getName()).getTime()));
//        }

//        if (text.contains("%tps%"))
//            text = text.replace("%tps%", String.valueOf(Bukkit.getServer().spigot().get));

        if (text.contains("%money%")) {
            text = text.replace("%money%", String.valueOf(Utils.formatMoney(economyManager.get(player))));
        }

        if (text.contains("%location%") && player != null) {
            Location l = player.getLocation();
            text = text.replace("%location%", l.getBlockX() + ", " + l.getBlockY() + ", " + l.getBlockZ());
        }

        if (text.contains("%job%"))
            text = text.replace("%job%", TextUtil.color(jobsManager.getJob(player)));

        if (text.contains("%cena%") && player != null)
            text = text.replace("%cena%", String.valueOf(ActionManager.cena));
        if (text.contains("%cena_16%") && player != null)
            text = text.replace("%cena_16%", String.valueOf(ActionManager.cena * 16));
        if (text.contains("%cena_32%") && player != null)
            text = text.replace("%cena_32%", String.valueOf(ActionManager.cena * 32));
        if (text.contains("%cena_64%") && player != null)
            text = text.replace("%cena_64%", String.valueOf(ActionManager.cena * 64));

        if (text.contains("%canUseHrac%") || text.contains("%canUseAstronaut%") || text.contains("%canUseGalactic%") || text.contains("%canUseBlackHole%")) {
            if (text.contains("%canUseHrac%") && player.hasPermission("kits.default")) text = text.replace("%canUseHrac%", TextUtil.color("&aANO"));
            else text = text.replace("%canUseHrac%", TextUtil.color("&cNE"));
            if (text.contains("%canUseAstronaut%") && player.hasPermission("kits.astronaut")) text = text.replace("%canUseAstronaut%", TextUtil.color("&aANO"));
            else text = text.replace("%canUseAstronaut%", TextUtil.color("&cNE"));
            if (text.contains("%canUseGalactic%") && player.hasPermission("kits.galactic")) text = text.replace("%canUseGalactic%", TextUtil.color("&aANO"));
            else text = text.replace("%canUseGalactic%", TextUtil.color("&cNE"));
            if (text.contains("%canUseBlackHole%") && player.hasPermission("kits.blackhole")) text = text.replace("%canUseBlackHole%", TextUtil.color("&aANO"));
            else text = text.replace("%canUseBlackHole%", TextUtil.color("&cNE"));
        }

        if (text.contains("%starter_cooldown%")) {
            if ((KitsAction.starter_cooldown.get(player.getUniqueId()) == null) || (KitsAction.starter_cooldown.get(player.getUniqueId()) == 0))
                return (text = text.replace("%starter_cooldown%", TextUtil.color("&aNEAKTIVNÍ")));
            text = text.replace("%starter_cooldown%", TextUtil.color("&c" + TimeConvertor.convertDay(KitsAction.starter_cooldown.get(player.getUniqueId()))));
        }
        if (text.contains("%farmer_cooldown%")) {
            if ((KitsAction.farmer_cooldown.get(player.getUniqueId()) == null) || (KitsAction.starter_cooldown.get(player.getUniqueId()) == 0))
                return (text = text.replace("%farmer_cooldown%", TextUtil.color("&aNEAKTIVNÍ")));
            text = text.replace("%farmer_cooldown%", TextUtil.color("&c" + TimeConvertor.convertDay(KitsAction.farmer_cooldown.get(player.getUniqueId()))));
        }
        if (text.contains("%king_cooldown%")) {
            if ((KitsAction.king_cooldown.get(player.getUniqueId()) == null) || (KitsAction.starter_cooldown.get(player.getUniqueId()) == 0))
                return (text = text.replace("%king_cooldown%", TextUtil.color("&aNEAKTIVNÍ")));
            text = text.replace("%king_cooldown%", TextUtil.color("&c" + TimeConvertor.convertDay(KitsAction.king_cooldown.get(player.getUniqueId()))));
        }
        if (text.contains("%enchanter_cooldown%")) {
            if ((KitsAction.enchanter_cooldown.get(player.getUniqueId()) == null) || (KitsAction.starter_cooldown.get(player.getUniqueId()) == 0))
                return (text = text.replace("%enchanter_cooldown%", TextUtil.color("&aNEAKTIVNÍ")));
            text = text.replace("%enchanter_cooldown%", TextUtil.color("&c" + TimeConvertor.convertDay(KitsAction.enchanter_cooldown.get(player.getUniqueId()))));
        }
        if (text.contains("%cisar_cooldown%")) {
            if ((KitsAction.cisar_cooldown.get(player.getUniqueId()) == null) || (KitsAction.starter_cooldown.get(player.getUniqueId()) == 0))
                return (text = text.replace("%cisar_cooldown%", TextUtil.color("&aNEAKTIVNÍ")));
            text = text.replace("%cisar_cooldown%", TextUtil.color("&c" + TimeConvertor.convertDay(KitsAction.cisar_cooldown.get(player.getUniqueId()))));
        }
        if (text.contains("%knight_cooldown%")) {
            if ((KitsAction.knight_cooldown.get(player.getUniqueId()) == null) || (KitsAction.starter_cooldown.get(player.getUniqueId()) == 0))
                return (text = text.replace("%knight_cooldown%", TextUtil.color("&aNEAKTIVNÍ")));
            text = text.replace("%knight_cooldown%", TextUtil.color("&c" + TimeConvertor.convertDay(KitsAction.knight_cooldown.get(player.getUniqueId()))));
        }

        if (text.contains("%guild%")) {
            text = text.replace("%guild%", "coming soon..");
        }

        /*try {
            final String BUNGEEPATTERN = "%bungeecord(\w+)%";
            Pattern pattern = Pattern.compile(BUNGEE_PATTERN);
            Matcher matcher = pattern.matcher(text);
            while(matcher.find()) {
                text = matcher.replaceAll(String.valueOf(BungeeCord.getServerCount(player, matcher.group(1))));
            }
        }catch (Exception ex) {
            ex.printStackTrace();
        }*/

        if (PAPI && player != null) text = PlaceholderAPI.setPlaceholders(player, text);

        return text;

    }
}
