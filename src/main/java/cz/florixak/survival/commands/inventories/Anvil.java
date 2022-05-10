package cz.florixak.survival.commands.inventories;

import cz.florixak.survival.config.Messages;
import cz.florixak.survival.manager.commandManager.CommandInfo;
import cz.florixak.survival.manager.commandManager.PluginCommand;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

@CommandInfo(name = "anvil", permission = "", requiresPlayer = true)
public class Anvil extends PluginCommand {

    @Override
    public void perform(Player p, String[] args){

        p.sendMessage(Messages.ANVIL_OPEN.toString());

        Inventory anvil = Bukkit.createInventory(null, InventoryType.ANVIL);
        p.openInventory(anvil);

    }
}
