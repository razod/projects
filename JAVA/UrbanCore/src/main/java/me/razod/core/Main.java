package me.razod.core;

import java.util.ArrayList;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.java.JavaPlugin;
import me.razod.listeners.*;
import me.razod.chat.*;
import me.razod.punishments.*;

public class Main extends JavaPlugin {

    public Main() {
    }

    private void newCmd(String name, CommandExecutor file) {
        this.getCommand(name).setExecutor(file);
    }

    public void onEnable() {
        loadConfig(); // This is for later
        newCmd("mutechat", new Mute()); // The same as "this.getCommand("mute").setExecutor(new Mute());"
        newCmd("clearchat", new Chat());
        newCmd("ban", new Ban());
        newCmd("kick", new Kick());
        getServer().getPluginManager().registerEvents(new muteListener(), this);
    }

    private void loadConfig () {

        getConfig().addDefault("muted", new ArrayList<String>());
        getConfig().options().copyDefaults(true);
        saveConfig();
    }

}