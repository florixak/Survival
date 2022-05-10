package cz.florixak.survival.action.actions;

import cz.florixak.survival.Survival;
import cz.florixak.survival.action.Action;
import cz.florixak.survival.manager.JobsManager;
import org.bukkit.entity.Player;

public class JobsAction implements Action {

    @Override
    public String getIdentifier() {
        return "JOBS";
    }

    @Override
    public void execute(Survival plugin, Player player, String data) {

        JobsManager jobsManager = Survival.plugin.getJobsManager();

        if (data.equals("leave")) {
            jobsManager.leaveJob(player);
        }
        if (data.equals("miner")) {
            jobsManager.signAsMiner(player);
        }
        if (data.equals("digger")) {
            jobsManager.signAsDigger(player);
        }
        if (data.equals("crafter")) {
            jobsManager.signAsCrafter(player);
        }
        if (data.equals("killer")) {
            jobsManager.signAsKiller(player);
        }
        if (data.equals("farmer")) {
            jobsManager.signAsFarmer(player);
        }
        if (data.equals("woodcutter")) {
            jobsManager.signAsWoodcutter(player);
        }
        if (data.equals("builder")) {
            jobsManager.signAsBuilder(player);
        }
    }
}