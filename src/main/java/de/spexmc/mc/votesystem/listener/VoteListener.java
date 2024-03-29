package de.spexmc.mc.votesystem.listener;

import java.util.UUID;

import de.spexmc.mc.votesystem.model.PlayerVote;
import de.spexmc.mc.votesystem.model.Voter;
import de.spexmc.mc.votesystem.util.mcutils.UUIDUtils;
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
    if (currentItem.getItemMeta().getDisplayName().contains("PlayerVote")) {
      final Player votePlayer = (Player) clickEvent.getWhoClicked();
      VoteManager.determineVoter(votePlayer).sendVoteMessage();
    }
  }

  @EventHandler
  public void onVote(VotifierEvent votifierEvent) {
    final PlayerVote playerVote = votifierEvent.getPlayerVote();
    final String voterName = playerVote.getUsername();
    final UUID voterUuid = UUIDUtils.getUUID(voterName);
    final Voter voter = VoteManager.determineVoter(voterUuid);
    voter.vote(playerVote);
  }
}
