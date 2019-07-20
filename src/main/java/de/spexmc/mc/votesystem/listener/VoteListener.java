package de.spexmc.mc.votesystem.listener;

import com.vexsoftware.votifier.model.Vote;
import com.vexsoftware.votifier.model.VotifierEvent;
import de.spexmc.mc.votesystem.util.objectmanager.VoteManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

/**
 * Created by Lara on 26.02.2019 for votesystem
 */
public class VoteListener implements Listener {

  @EventHandler
  public void onClick(InventoryClickEvent clickEvent) {
    final ItemStack currentItem = clickEvent.getCurrentItem();
    if (currentItem.getItemMeta().getDisplayName().contains("Vote")) {
      final Player votePlayer = (Player) clickEvent.getWhoClicked();
      VoteManager.determineVoter(votePlayer).sendVoteMessage();
    }
  }

  @EventHandler
  public void onVote(VotifierEvent votifierEvent) {
    final Vote vote = votifierEvent.getVote();
    final String voter = vote.getUsername();
  }
}