package me.razod.chat;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Mute implements CommandExecutor {
    public static boolean mute;
    public Mute() {
    }


    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player)sender;
        if (!player.hasPermission("uc.mute")) {player.sendMessage(ChatColor.translateAlternateColorCodes('&', " &cYou do not have permission!")); return true;}
        if (args.length == 0) {player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cUsage: /mute <on/off>")); return true;}
        if (args[0].equalsIgnoreCase("on")) {
            if (player.hasPermission("uc.mute")) {
                mute = true;
                Bukkit.getServer().broadcastMessage(ChatColor.AQUA + sender.getName() + ChatColor.GRAY + " muted global chat");
                return true;
            }  else {
                sender.sendMessage(ChatColor.RED + "You do not have permission!");
            }
        }
        if ((args[0].equalsIgnoreCase("off")) && (mute)) {
            if (player.hasPermission("uc.mute")) {
                mute = false;
                Bukkit.getServer().broadcastMessage(ChatColor.AQUA + sender.getName() + ChatColor.GRAY + " un-muted global chat");
                return true;
            } else {
                sender.sendMessage(ChatColor.RED + "You do not have permission!");
                return false;
            }
        }
        if ((args[0].equalsIgnoreCase("off")) && (!mute)) {
            player.sendMessage(ChatColor.AQUA + "Global chat is already un-muted!");
            return true;
        }



        return true;
    }


}