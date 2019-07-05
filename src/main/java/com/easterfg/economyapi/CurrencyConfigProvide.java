package com.easterfg.economyapi;

import cn.nukkit.Player;
import cn.nukkit.utils.Config;
import cn.nukkit.utils.ConfigSection;
import com.sun.istack.internal.NotNull;

import java.io.File;

class CurrencyConfigProvide {

    private File playerDataFolder;

    private CurrencyConfigProvide(@NotNull EconomyAPI plugin) {
        File dataFolder = plugin.getDataFolder();
        if ( ! dataFolder.exists())
            dataFolder.mkdirs();
        playerDataFolder = new File(dataFolder.getAbsolutePath() + "/Player/");
        if (playerDataFolder.exists())
            playerDataFolder.mkdirs();
    }

    /**
     * 判断指定的玩家数据是否存在
     * @param player 玩家实例
     * @return 结果
     */
    public boolean exists(@NotNull  Player player) {
        return exists(player.getName());
    }

    /**
     * 判断指定的玩家数据是否存在
     * @param name 玩家实例
     * @return 结果
     */
    public boolean exists(@NotNull  String name) {
        return new File(playerDataFolder.getAbsolutePath() + name + ".yml").exists();
    }

    /**
     * 创建玩家数据
     * @param name 玩家名称
     */
    public void createConfig(@NotNull String name) {
        new Config(playerDataFolder.getAbsolutePath() + name + ".yml", Config.YAML,new ConfigSection("money", 0));
    }

    /**
     * 获取当前类的实例
     * @param plugin 插件实例
     * @return 实例
     */
    public static CurrencyConfigProvide getInstance(EconomyAPI plugin) {
        return new CurrencyConfigProvide(plugin);
    }

    /**
     * 获取玩家配置文件
     * @param name 玩家名称
     * @return Config
     */
    private Config getPlayerConfig(@NotNull String name) {
        return new Config(playerDataFolder.getAbsolutePath() + name + ".yml", Config.YAML,new ConfigSection("money", 0));
    }

    /**
     * 获取玩家的金币数量
     * @param player 玩家实例
     * @return int
     */
    public int myMoney(@NotNull Player player) {
        return myMoney(player.getName());
    }

    /**
     * 获取玩家的金币数量
     * @param name 玩家实例
     * @return int
     */
    public int myMoney(@NotNull String name) {
        return getPlayerConfig(name).getInt("money", 0);
    }

    /**
     * 设置玩玩家的金币
     * @param player 玩家
     * @param money 数量
     * @return 是否成功
     */
    public boolean setPlayerMoney(@NotNull Player player, int money) {
        return setPlayerMoney(player.getName(), money);
    }

    /**
     * 设置玩玩家的金币
     * @param name 玩家
     * @param money 数量
     * @return 是否成功
     */
    public boolean setPlayerMoney(@NotNull String name, int money) {
        Config config = getPlayerConfig(name);
        config.set("money", money);
        return config.save();
    }

    /**
     * 为指定玩家添加金币
     * @param player 玩家实例
     * @param money 数量
     * @return 是否成功
     */
    public boolean addPlayerMoney(@NotNull Player player, int money) {
        return addPlayerMoney(player.getName(), money);
    }

    /**
     * 为指定玩家添加金币
     * @param name 玩家实例
     * @param money 数量
     * @return 是否成功
     */
    public boolean addPlayerMoney(@NotNull String name, int money) {
        Config config = getPlayerConfig(name);
        int result = config.get("money", money) + money;
        config.set("money", result);
        return config.save();
    }

    /**
     * 扣除指定玩家金币
     * @param player 玩家实例
     * @param money 数量
     * @return 是否成功
     */
    public boolean reducePlayerMoney(@NotNull Player player, int money) {
        return reducePlayerMoney(player.getName(), money);
    }

    /**
     * 扣除指定玩家金币
     * @param name 玩家实例
     * @param money 数量
     * @return 是否成功
     */
    public boolean reducePlayerMoney(@NotNull String name, int money) {
        Config config = getPlayerConfig(name);
        int result = config.get("money", money) - money;
        config.set("money", result < 0 ? 0 : result);
        return config.save();
    }
}
