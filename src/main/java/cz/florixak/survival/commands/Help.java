package cz.florixak.survival.commands;

import cz.florixak.survival.config.Messages;
import cz.florixak.survival.manager.commandManager.CommandInfo;
import cz.florixak.survival.manager.commandManager.PluginCommand;
import org.bukkit.entity.Player;

@CommandInfo(name = "help", permission = "", requiresPlayer = true)
public class Help extends PluginCommand {

    @Override
    public void perform(Player p, String[] args){

        if (args.length == 0 || args[0].equalsIgnoreCase("1")){
            p.sendMessage(Messages.HELP_TITLE1.toString());
            p.sendMessage(Messages.HELP_HELP1_LINE1.toString());
            p.sendMessage(Messages.HELP_HELP1_LINE2.toString());
            p.sendMessage(Messages.HELP_HELP1_LINE3.toString());
            p.sendMessage(Messages.HELP_HELP1_LINE4.toString());
            p.sendMessage(Messages.HELP_HELP1_LINE5.toString());
            p.sendMessage(Messages.HELP_TITLE1.toString());

        } else if (args[0].equalsIgnoreCase("2")){
            p.sendMessage(Messages.HELP_TITLE2.toString());
            p.sendMessage(Messages.HELP_HELP2_LINE1.toString());
            p.sendMessage(Messages.HELP_HELP2_LINE2.toString());
            p.sendMessage(Messages.HELP_HELP2_LINE3.toString());
            p.sendMessage(Messages.HELP_HELP2_LINE4.toString());
            p.sendMessage(Messages.HELP_HELP2_LINE5.toString());
            p.sendMessage(Messages.HELP_TITLE2.toString());

        } else if (args[0].equalsIgnoreCase("3")){
            p.sendMessage(Messages.HELP_TITLE3.toString());
            p.sendMessage(Messages.HELP_HELP3_LINE1.toString());
            p.sendMessage(Messages.HELP_HELP3_LINE2.toString());
            p.sendMessage(Messages.HELP_HELP3_LINE3.toString());
            p.sendMessage(Messages.HELP_HELP3_LINE4.toString());
            p.sendMessage(Messages.HELP_HELP3_LINE5.toString());
            p.sendMessage(Messages.HELP_TITLE3.toString());

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
    }
}