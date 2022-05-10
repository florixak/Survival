package cz.florixak.survival.commands.admin;

import cz.florixak.survival.Survival;
import cz.florixak.survival.config.Messages;
import cz.florixak.survival.manager.commandManager.CommandInfo;
import cz.florixak.survival.manager.commandManager.PluginCommand;
import org.bukkit.command.CommandSender;

@CommandInfo(name = "survival", permission = "reload", requiresPlayer = false)
public class Reload extends PluginCommand {

    @Override
    public void perform(CommandSender sender, String[] args){

        if (args.length == 0){
            sender.sendMessage(Messages.RELOAD_USAGE.toString());
        } else if (args[0].equalsIgnoreCase("reload")) {
            Survival.plugin.getConfigManager().reloadFiles();
            sender.sendMessage(Messages.RELOAD_RELOADED.toString());
        } else {
            sender.sendMessage(Messages.RELOAD_USAGE.toString());
        }
    }
}