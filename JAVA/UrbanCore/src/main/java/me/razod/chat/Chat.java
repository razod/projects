package me.razod.chat;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Chat implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player) sender;
        if (!player.hasPermission("uc.clearchat")) {player.sendMessage(ChatColor.translateAlternateColorCodes('&', " &cYou do not have permission!")); return true;}
        int i;
        for (i = 0; i < 300; i++) {
            Bukkit.broadcastMessage(" ");
        }
        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&b" + player.getName() + " &7cleared the chat!"));


        return true;
    }

}