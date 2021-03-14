package me.razod.punishments;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Kick implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;
        if(!p.hasPermission("uc.kick")) {p.sendMessage(ChatColor.translateAlternateColorCodes('&', " &cYou do not have permission!")); return false;}
        if(args.length != 2) {
            p.sendMessage(ChatColor.RED + "Usage: /kick (player) (reason)");
            return false;
        }
        Player b = Bukkit.getServer().getPlayer(args[0]);
        if(b != null) {
            b.getPlayer().kickPlayer(ChatColor.RED + "You have been kicked\n" + ChatColor.AQUA + "Reason: " + args[1]);
            return true;
        } else {
            p.sendMessage(ChatColor.RED + "Player not found");
            return false;
        }
    }
}
