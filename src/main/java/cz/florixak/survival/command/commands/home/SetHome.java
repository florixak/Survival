package cz.florixak.survival.command.commands.home;

import cz.florixak.survival.Survival;
import cz.florixak.survival.command.Command;
import cz.florixak.survival.config.ConfigType;
import cz.florixak.survival.config.Messages;
import cz.florixak.survival.manager.HomeManager;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.group.Group;
import net.luckperms.api.model.user.User;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class SetHome extends Command {

    private FileConfiguration config;

    private HomeManager manager;

    private int low_vip;
    private int high_vip;
    private int highest_vip;
    private int admin_homes;
    private int hrac_homes;

    public SetHome(Survival plugin) {
        super(plugin);
        this.config = plugin.getConfigManager().getFile(ConfigType.SETTINGS).getConfig();

        this.manager = plugin.getHomeManager();

        this.admin_homes = config.getInt("home.max_homes.admin");
        this.low_vip = config.getInt("home.max_homes.vip_low");
        this.high_vip = config.getInt("home.max_homes.vip_high");
        this.highest_vip = config.getInt("home.max_homes.vip_highest");
        this.hrac_homes = config.getInt("home.max_homes.hrac");

        this.addAlias("sethome");
        this.setOnlyPlayer(true);
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) throws Exception {
        Player p = (Player) sender;

        if (args.length == 0) {
            p.sendMessage(Messages.HOME_USAGE_SETHOME.toString());
        }
        else if (args.length == 1){

            if (manager.exist(p.getUniqueId(), args[0])){
                p.sendMessage(Messages.HOME_ALREADY_EXISTS.toString().replace("%home_name%", args[0]));
                return true;
            }

            if (!manager.homeIsNull(p.getUniqueId())){

                User user = LuckPermsProvider.get().getUserManager().getUser(p.getUniqueId());
                Group hrac = LuckPermsProvider.get().getGroupManager().getGroup("default");

                if (user.getPrimaryGroup().equals(hrac.getName())){
                    if (!(manager.getHomes(p.getUniqueId()).size() < hrac_homes)){
                        p.sendMessage(Messages.HOME_MAX_HOMES.toString().replace("%max_homes%", "" + hrac_homes));
                        return true;
                    }
                }

                Group astronaut = LuckPermsProvider.get().getGroupManager().getGroup("astronaut");

                if (user.getPrimaryGroup().equals(astronaut.getName())){
                    if (!(manager.getHomes(p.getUniqueId()).size() < low_vip)){
                        p.sendMessage(Messages.HOME_MAX_HOMES.toString().replace("%max_homes%", "" + low_vip));
                        return true;
                    }
                }

                Group galactic = LuckPermsProvider.get().getGroupManager().getGroup("galactic");
                Group yt = LuckPermsProvider.get().getGroupManager().getGroup("youtuber");
                Group streamer = LuckPermsProvider.get().getGroupManager().getGroup("streamer");
                Group jr_builder = LuckPermsProvider.get().getGroupManager().getGroup("jr.builder");
                Group builder = LuckPermsProvider.get().getGroupManager().getGroup("builder");
                Group jr_mod = LuckPermsProvider.get().getGroupManager().getGroup("jr.mod");
                Group mod = LuckPermsProvider.get().getGroupManager().getGroup("mod");

                if (user.getPrimaryGroup().equals(galactic.getName()) ||
                        user.getPrimaryGroup().equals(yt.getName()) ||
                        user.getPrimaryGroup().equals(streamer.getName()) ||
                        user.getPrimaryGroup().equals(jr_builder.getName()) ||
                        user.getPrimaryGroup().equals(builder.getName()) ||
                        user.getPrimaryGroup().equals(jr_mod.getName()) ||
                        user.getPrimaryGroup().equals(mod.getName())){

                    if (!(manager.getHomes(p.getUniqueId()).size() < high_vip)){
                        p.sendMessage(Messages.HOME_MAX_HOMES.toString().replace("%max_homes%", "" + high_vip));
                        return true;
                    }
                }

                Group black_hole = LuckPermsProvider.get().getGroupManager().getGroup("blackhole");
                Group sr_builder = LuckPermsProvider.get().getGroupManager().getGroup("sr.builder");
                Group sr_mod = LuckPermsProvider.get().getGroupManager().getGroup("sr.mod");

                if (user.getPrimaryGroup().equals(black_hole.getName()) ||
                        user.getPrimaryGroup().equals(sr_builder.getName()) ||
                        user.getPrimaryGroup().equals(sr_mod.getName())){
                    if (!(manager.getHomes(p.getUniqueId()).size() < highest_vip)){
                        p.sendMessage(Messages.HOME_MAX_HOMES.toString().replace("%max_homes%", "" + highest_vip));
                        return true;
                    }
                }

                Group admin = LuckPermsProvider.get().getGroupManager().getGroup("admin");
                Group owner = LuckPermsProvider.get().getGroupManager().getGroup("owner");
                Group sr_developer = LuckPermsProvider.get().getGroupManager().getGroup("sr.developer");

                if (user.getPrimaryGroup().equals(owner.getName()) ||
                        user.getPrimaryGroup().equals(sr_developer.getName()) ||
                        user.getPrimaryGroup().equals(admin.getName())){
                    if (!(manager.getHomes(p.getUniqueId()).size() < admin_homes)){
                        p.sendMessage(Messages.HOME_MAX_HOMES.toString().replace("%max_homes%", "" + admin_homes));
                        return true;
                    }
                }
            }
            manager.addHome(p.getUniqueId(), p.getLocation(), args[0]);
            p.sendMessage(Messages.HOME_CREATED.toString().replace("%home_name%", args[0]));

        } else {
            p.sendMessage(Messages.HOME_USAGE_SETHOME.toString());
        }
        return true;
    }

}