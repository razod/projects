package com.nexh.wh;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.ChatColor;

public class Reload implements CommandExecutor  {
    private Main plugin = Main.getPlugin(Main.class);
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        Player p = (Player) sender;
        if(args.length == 0) {
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7------&3&lWhitelister &bby &3Nexh &7------"));
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&3/wh rl &8- &7Reload the whitelister config"));
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7------&3&lWhitelister &bby &3Nexh &7------"));
        } else if(args[0].equalsIgnoreCase("rl")) {
            if(!p.hasPermission("bc.reload")) { p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou don't have permission!")); return false;}
            plugin.reloadConfig();
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cReloaded the config!"));
        }
        return true;
    }
}
