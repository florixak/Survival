package cz.florixak.survival.manager.commandManager;

import cz.florixak.survival.config.Messages;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

public abstract class PluginCommand implements CommandExecutor {

    private final CommandInfo commandInfo;

    public PluginCommand(){
        commandInfo = getClass().getDeclaredAnnotation(CommandInfo.class);
        Objects.requireNonNull(commandInfo, "Commands must have CommandInfo annotations");
    }

    public CommandInfo getCommandInfo() {
        return commandInfo;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){

        if (!commandInfo.permission().isEmpty()){
            if (!sender.hasPermission(commandInfo.permission())){
                sender.sendMessage(Messages.NO_PERM.toString());
                return true;
            }
        }

        if (commandInfo.requiresPlayer()){
            if (!(sender instanceof Player)){
                sender.sendMessage(Messages.NOT_PLAYER.toString());
                return true;
            }
            perform((Player) sender, args);
            return true;
        }

        perform(sender, args);
        return true;
    }

    public void perform(Player player, String[] args){}
    public void perform(CommandSender sender, String[] args){}
}