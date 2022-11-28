package cz.florixak.survival.command;

import cz.florixak.survival.Survival;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;

public abstract class Command {

    protected Survival plugin;

    private ArrayList<String> aliases = new ArrayList<String>();
    private ArrayList<String> requirements = new ArrayList<String>();
    private String description;
    private boolean onlyPlayer;

    public Command(Survival plugin) {
        this.plugin = plugin;
    }

    public ArrayList<String> getAliases() {
        return aliases;
    }

    public void setAliases(ArrayList<String> aliases) {
        this.aliases = aliases;
    }

    public void addAlias(String a) {
        aliases.add(a);
    }

    public void addRequirement(String r) {
        requirements.add(r);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<String> getRequirements() {
        return requirements;
    }

    public void setRequirements(ArrayList<String> requirements) {
        this.requirements = requirements;
    }

    public abstract boolean execute(CommandSender sender, String[] args) throws Exception;

    public boolean hasRequirement(CommandSender s, String r) {
        return s.hasPermission(r);
    }

    public boolean onlyPlayer() {
        return onlyPlayer;
    }

    public void setOnlyPlayer(boolean onlyPlayer) {
        this.onlyPlayer = onlyPlayer;
    }


}