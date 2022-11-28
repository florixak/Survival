package cz.florixak.survival.command.commands.admin;

import cz.florixak.survival.Survival;
import cz.florixak.survival.command.Command;
import cz.florixak.survival.config.Messages;
import org.bukkit.command.CommandSender;

public class Reload extends Command {

    public Reload(Survival plugin) {
        super(plugin);

        this.addAlias("survival");
        this.addRequirement("survival.reload");
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) throws Exception {
        if (args[0].equalsIgnoreCase("reload") || args[0].equalsIgnoreCase("rel")) {
            plugin.getConfigManager().reloadFiles();
            sender.sendMessage(Messages.RELOAD_RELOADED.toString());
        }
        return true;
    }
}