package cz.florixak.survival.action;

import cz.florixak.survival.Survival;
import cz.florixak.survival.action.actions.*;
import cz.florixak.survival.utility.PlaceholderUtil;
import org.apache.commons.lang.StringUtils;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActionManager {

    private Survival plugin;
    private Map<String, Action> actions;

    public static ItemStack item;
    public static int cena;

    public ActionManager(Survival plugin) {
        this.plugin = plugin;
        actions = new HashMap<>();
        load();
    }

    private void load() {
        registerAction(
//                new MessageAction(),
                new BroadcastMessageAction(),
//                new CommandAction(),
//                new ConsoleCommandAction(),
//                new SoundAction(),
//                new PotionEffectAction(),
//                new GamemodeAction(),
//                new BungeeAction(),
                new CloseInventoryAction(),
//                new TitleAction(),
                new MenuAction(),
//                new CosmeticsAction(),
                new ShopAction(),
                new AmountAction(),
                new JobsAction(),
                new KitsAction()
        );
    }

    public void registerAction(Action... actions) {
        Arrays.asList(actions).forEach(action -> this.actions.put(action.getIdentifier(), action));
    }

    public void executeActions(Player player, List<String> items) {
        items.forEach(item -> {

            String actionName = StringUtils.substringBetween(item, "[", "]").toUpperCase();
            Action action = actionName.isEmpty() ? null : actions.get(actionName);

            if (action != null) {
                item = item.contains(" ") ? item.split(" ", 2)[1] : "";
                item = PlaceholderUtil.setPlaceholders(item, player);

                action.execute(plugin, player, item);
            }
        });
    }
}