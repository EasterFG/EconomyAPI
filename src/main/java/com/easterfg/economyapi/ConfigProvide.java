package com.easterfg.economyapi;

import cn.nukkit.Player;
import cn.nukkit.utils.Config;
import cn.nukkit.utils.ConfigSection;
import com.sun.istack.internal.NotNull;

import java.io.File;

class ConfigProvide {

    private File playerDataFolder;

    private ConfigProvide(@NotNull EconomyAPI plugin) {
        File dataFolder = plugin.getDataFolder();
        if ( ! dataFolder.exists())
            dataFolder.mkdirs();
        playerDataFolder = new File(dataFolder.getAbsolutePath() + "/Player/");
        if (playerDataFolder.exists())
            playerDataFolder.mkdirs();
    }

    public boolean exists(@NotNull  Player palyer) {
        return exists(palyer.getName());
    }

    public boolean exists(@NotNull  String name) {
        return new File(playerDataFolder.getAbsolutePath() + name + ".yml").exists();
    }

    public void createConfig(@NotNull String name) {
        new Config(playerDataFolder.getAbsolutePath() + name + ".yml", Config.YAML,new ConfigSection("money", 0));
    }

    public static ConfigProvide getInstance(EconomyAPI plugin) {
        return new ConfigProvide(plugin);
    }

    private Config getPlayerConfig(@NotNull Player player) {
        return getPlayerConfig(player.getName());
    }

    private Config getPlayerConfig(@NotNull String name) {
        return new Config(playerDataFolder.getAbsolutePath() + name + ".yml", Config.YAML,new ConfigSection("money", 0));
    }

    public int myMoney(@NotNull Player player) {
        return myMoney(player.getName());
    }

    public int myMoney(@NotNull String name) {
        return getPlayerConfig(name).getInt("money", 0);
    }

    public boolean setPlayerMoney(@NotNull Player player, int money) {
        return setPlayerMoney(player.getName(), money);
    }

    public boolean setPlayerMoney(@NotNull String name, int money) {
        Config config = getPlayerConfig(name);
        config.set("money", money);
        return config.save();
    }

    public boolean addPlayerMoney(@NotNull Player player, int money) {
        return addPlayerMoney(player.getName(), money);
    }

    public boolean addPlayerMoney(@NotNull String name, int money) {
        Config config = getPlayerConfig(name);
        int result = config.get("money", money) + money;
        config.set("money", result);
        return config.save();
    }

    public boolean reducePlayerMoney(@NotNull Player player, int money) {
        return reducePlayerMoney(player.getName(), money);
    }

    public boolean reducePlayerMoney(@NotNull String name, int money) {
        Config config = getPlayerConfig(name);
        int result = config.get("money", money) - money;
        config.set("money", result < 0 ? 0 : result);
        return config.save();
    }
}
