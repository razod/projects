package com.nexh.wh;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class Main extends JavaPlugin implements Listener {
    public void onEnable() {
        loadConfig();
        this.getCommand("wh").setExecutor(new Reload());
        Bukkit.getServer().getPluginManager().registerEvents(this, this);
    }
    private void loadConfig() {
        getConfig().options().copyDefaults(true);
        saveConfig();
    }
    @EventHandler
    public void onPlayerPreLogin(AsyncPlayerPreLoginEvent evt) throws IOException, ParseException {
        String API_URL = getConfig().getString("api");
        String name = evt.getName();
        URL url = new URL(API_URL);
        URLConnection connection = url.openConnection();
        HttpURLConnection httpCon = (HttpURLConnection) connection;
        BufferedReader in = new BufferedReader(new InputStreamReader(httpCon.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(response.toString());
        JSONArray array = new JSONArray();
        array.add(obj);
        JSONArray array2 = (JSONArray) array.get(0);
        if(array2.contains(name)) {
            evt.allow();
        } else {
            String format = getConfig().getString("message");
            evt.disallow(AsyncPlayerPreLoginEvent.Result.KICK_OTHER, format);
        }

    }
}
