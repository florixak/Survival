package cz.florixak.survival.command.commands.admin;

import cz.florixak.survival.Survival;
import cz.florixak.survival.command.Command;
import cz.florixak.survival.manager.PlayerManager;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BuilderMode extends Command {

    public BuilderMode(Survival plugin) {
        super(plugin);

        this.addAlias("builder");
        this.addAlias("build");
        this.addAlias("stavitel");
        this.addRequirement("survival.builder-mode");
        this.setOnlyPlayer(true);
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) throws Exception {

        Player player = (Player) sender;

        if (PlayerManager.isInBuilderMode(player)) {
            PlayerManager.builderMode.remove(player.getUniqueId());
            player.sendMessage("Builder mode off");
            player.setGameMode(GameMode.SURVIVAL);
            return true;
        }

        PlayerManager.builderMode.add(player.getUniqueId());
        player.sendMessage("Builder mode on");
        player.setGameMode(GameMode.CREATIVE);

        return true;
    }
}
