package cz.florixak.survival.commands;

import cz.florixak.survival.config.Messages;
import cz.florixak.survival.listeners.TradeListener;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class TradeCommand implements CommandExecutor {

    HashMap<Player, Player> requestTrade = new HashMap<Player, Player>();

    TradeListener tradeList;

    public TradeCommand(TradeListener listener){
        tradeList = listener;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){

        if (!(sender instanceof Player)){
            sender.sendMessage(Messages.NOT_PLAYER.toString());
            return true;
        }

        Player p = (Player) sender;

        if (args.length == 2) {
            if (args[0].equals("request")) {
                Player tradeWith = Bukkit.getPlayer(args[1]);
                if (Bukkit.getOnlinePlayers().contains(tradeWith)) {
//                    if (!(tradeWith == p)) {
                        p.sendMessage("Poslal jsi obchodní nabídku hráči " + args[1]);
                        requestTrade.put(tradeWith, p);
                        tradeWith.sendMessage("Hráč " + p.getName() + " ti poslal obchodní nabídku");
                        return true;
//                    } else {
//                        p.sendMessage("Nemůžeš sám sobě poslat obchodní nabídku");
//                        return true;
//                    }
                } else {
                    p.sendMessage(args[1] + " není online");
                    return true;
                }
            }
        } else if (args.length == 1){
            if (args[0].equals("accept")){
                if (requestTrade.containsKey(p)){
                    Player tradeWith = requestTrade.get(p);
                    if (Bukkit.getOnlinePlayers().contains(tradeWith)){

                        Inventory tradeInv = Bukkit.createInventory(null, 27, "TRADE INVENTORY");

//                        ItemStack glass = new ItemStack(XMaterial.GRAY_STAINED_GLASS_PANE.parseMaterial());
                        ItemStack button = new ItemStack(Material.REDSTONE_BLOCK);
//                        tradeInv.setItem(0, glass);
//                        tradeInv.setItem(1, glass);
//                        tradeInv.setItem(2, glass);
//                        tradeInv.setItem(3, glass);
//                        tradeInv.setItem(4, glass);
//                        tradeInv.setItem(5, glass);
//                        tradeInv.setItem(6, glass);
//                        tradeInv.setItem(7, glass);
//                        tradeInv.setItem(8, glass);
//                        tradeInv.setItem(9, glass);
//                        tradeInv.setItem(17, glass);
//                        tradeInv.setItem(18, glass);
//                        tradeInv.setItem(19, glass);
//                        tradeInv.setItem(20, glass);
//                        tradeInv.setItem(21, glass);
//                        tradeInv.setItem(22, glass);
//                        tradeInv.setItem(23, glass);
//                        tradeInv.setItem(24, glass);
//                        tradeInv.setItem(25, glass);
                        tradeInv.setItem(26, button);

                        p.openInventory(tradeInv);
                        tradeWith.openInventory(tradeInv);
                        requestTrade.remove(p);
                        tradeList.addPlayersToTradelist(p, tradeWith);
                    } else {
                        p.sendMessage("Hráč už není online");
                        requestTrade.remove(p);
                        return true;
                    }
                } else {
                    p.sendMessage("Není tu nikdo kdo by s tebou chtěl obchodovat");
                    return true;
                }
            }
        }
        return false;
    }
}