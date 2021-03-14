package me.razod.listeners;

import me.razod.chat.Mute;
import org.bukkit.ChatColor;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;

public class muteListener implements Listener {
    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        Player p = event.getPlayer();
        String m = event.getMessage();

        if (Mute.mute) {
            if (!p.hasPermission("uc.mute.bypass")) {
                event.setCancelled(true);
                event.getPlayer().sendMessage(ChatColor.AQUA + "Chat is currently muted!");
            } else {
                event.setCancelled(false);
            }
        }
    }
}
