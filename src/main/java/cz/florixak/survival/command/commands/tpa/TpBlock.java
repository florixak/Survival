package cz.florixak.survival.command.commands.tpa;

import cz.florixak.survival.Survival;
import cz.florixak.survival.command.Command;
import cz.florixak.survival.config.Messages;
import cz.florixak.survival.manager.PlayerManager;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TpBlock extends Command {

    public TpBlock(Survival plugin) {
        super(plugin);

        this.addAlias("tpblock");
        this.addRequirement("survival.tpblock");
        this.setOnlyPlayer(true);
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) throws Exception {
        Player p = (Player) sender;

        if (!PlayerManager.hasTpBlock(p.getUniqueId())){
            PlayerManager.tpBlock.add(p.getUniqueId());
            p.sendMessage(Messages.TELEPORT_BLOCKED.toString());
            return true;
        }
        PlayerManager.tpBlock.remove(p.getUniqueId());
        p.sendMessage(Messages.TELEPORT_ALLOWED.toString());
        return true;
    }
}
