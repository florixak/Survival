package cz.florixak.survival.command.commands;

import cz.florixak.survival.Survival;
import cz.florixak.survival.command.Command;
import cz.florixak.survival.config.ConfigType;
import cz.florixak.survival.config.Messages;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.List;

public class Help extends Command {

    private FileConfiguration messages;

    public Help(Survival plugin) {
        super(plugin);
        this.messages = plugin.getConfigManager().getFile(ConfigType.MESSAGES).getConfig();

        this.addAlias("help");
        this.addAlias("pomoc");
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) throws Exception {

        Player p = (Player) sender;
        List<String> help = messages.getStringList("HELP.HELP");;

        if (args.length == 0 || args[0].equalsIgnoreCase("1")){
            for (int i = 0; i < help.size(); i++) {
                if (i < (5*100)/help.size()) {
                    p.sendMessage(help.get(i));
                }
            }

        } else if (args[0].equalsIgnoreCase("2")){
            for (int i = 0; i < help.size(); i++) {
                if (i < (5*100)/help.size()) {
                    p.sendMessage(help.get(i));
                }
            }

        } else if (args[0].equalsIgnoreCase("3")){
            for (int i = 0; i < help.size(); i++) {
                if (i < (5*100)/help.size()) {
                    p.sendMessage(help.get(i));
                }
            }

        } else if (args[0].equalsIgnoreCase("eco") || args[0].equalsIgnoreCase("economy")){
            p.sendMessage(Messages.HELP_ECO_TITLE.toString());
            p.sendMessage(Messages.HELP_ECO_LINE1.toString());
            p.sendMessage(Messages.HELP_ECO_LINE2.toString());
            p.sendMessage(Messages.HELP_ECO_LINE3.toString());
            p.sendMessage(Messages.HELP_ECO_LINE4.toString());
            p.sendMessage(Messages.HELP_ECO_TITLE.toString());

        } else if (args[0].equalsIgnoreCase("home")){
            p.sendMessage(Messages.HELP_HOME_TITLE.toString());
            p.sendMessage(Messages.HELP_HOME_LINE1.toString());
            p.sendMessage(Messages.HELP_HOME_LINE2.toString());
            p.sendMessage(Messages.HELP_HOME_LINE3.toString());
            p.sendMessage(Messages.HELP_HOME_LINE4.toString());
            p.sendMessage(Messages.HELP_HOME_TITLE.toString());

        } else if (args[0].equalsIgnoreCase("warp")) {
            p.sendMessage(Messages.HELP_WARP_TITLE.toString());
            p.sendMessage(Messages.HELP_WARP_LINE1.toString());
            p.sendMessage(Messages.HELP_WARP_LINE2.toString());
            p.sendMessage(Messages.HELP_WARP_TITLE.toString());

        } else if (args[0].equalsIgnoreCase("tpa")){
            p.sendMessage(Messages.HELP_TPA_TITLE.toString());
            p.sendMessage(Messages.HELP_TPA_LINE1.toString());
            p.sendMessage(Messages.HELP_TPA_LINE2.toString());
            p.sendMessage(Messages.HELP_TPA_LINE3.toString());
            p.sendMessage(Messages.HELP_TPA_LINE4.toString());
            p.sendMessage(Messages.HELP_TPA_TITLE.toString());

        } else {
            p.sendMessage(Messages.HELP_USAGE.toString());
        }
        return true;
    }
}