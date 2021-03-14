package me.razod.punishments;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.BanList;


public class Ban implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;
        if(!p.hasPermission("uc.ban")) {p.sendMessage(ChatColor.translateAlternateColorCodes('&', " &cYou do not have permission!")); return false;}
        if(args.length != 2) {
            p.sendMessage(ChatColor.RED + "Usage: /ban (player) (reason)");
            return false;
        }
        if(Bukkit.getServer().getPlayer(args[0]) != null) {
            Player b = Bukkit.getServer().getPlayer(args[0]);
            if(p.getName().equals(b.getName())) {
                p.sendMessage(ChatColor.RED + "You cannot ban this player!");
                return false;
            } else if(b.hasPermission("uc.ban")) {
                p.sendMessage(ChatColor.RED + "You cannot ban this player!");
                return false;
            } else if(Bukkit.getBanList(BanList.Type.NAME).isBanned(b.getName())) {
                p.sendMessage(ChatColor.RED + "Player already has been banned");
                return false;
            } else {
                Bukkit.getBanList(BanList.Type.NAME).addBan(b.getName(), ChatColor.RED + "You have been permanently banned\n" + ChatColor.AQUA + "Reason: " + args[1], null, p.getName());
                b.getPlayer().kickPlayer(ChatColor.RED + "You have been permanently banned.\n" + ChatColor.AQUA + "Reason: " + args[1]);
                Bukkit.broadcastMessage("\n");
                Bukkit.broadcastMessage(ChatColor.RED + b.getName() + ChatColor.AQUA + " has been banned " + ChatColor.GRAY +"[FOREVER]");
                Bukkit.broadcastMessage("\n");
                return true;
            }
        } else {
            p.sendMessage(ChatColor.RED + "Player not found");
            return false;
        }
    }
}
