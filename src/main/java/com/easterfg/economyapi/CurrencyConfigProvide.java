package com.easterfg.economyapi;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.utils.Config;
import cn.nukkit.utils.ConfigSection;
import com.easterfg.economyapi.event.MoneyChangeEvent;
import com.easterfg.economyapi.event.PointChangeEvent;
import com.sun.istack.internal.NotNull;

import java.io.File;
import java.util.LinkedHashMap;

public class CurrencyConfigProvide {

    private File playerDataFolder;

    private CurrencyConfigProvide(@NotNull EconomyAPI plugin) {
        File dataFolder = plugin.getDataFolder();
        if (!dataFolder.exists()) {
            dataFolder.mkdirs();
        }
        playerDataFolder = new File(dataFolder, "Player");
        if (playerDataFolder.exists()) {
            playerDataFolder.mkdirs();
        }
    }

    /**
     * 判断指定的玩家数据是否存在
     *
     * @param player 玩家实例
     * @return 结果
     */
    public boolean exists(@NotNull Player player) {
        return exists(player.getName());
    }

    /**
     * 判断指定的玩家数据是否存在
     *
     * @param name 玩家实例
     * @return 结果
     */
    public boolean exists(@NotNull String name) {
        return new File(playerDataFolder, name + ".yml").exists();
    }

    /**
     * 创建玩家数据
     *
     * @param name 玩家名称
     */
    public Config createConfig(@NotNull String name) {
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        map.put("money", 0);
        map.put("point", 0);
        return new Config(new File(playerDataFolder, name + ".yml"), Config.YAML, new ConfigSection(map));
    }

    /**
     * 获取当前类的实例
     *
     * @param plugin 插件实例
     * @return 实例
     */
    public static CurrencyConfigProvide getInstance(EconomyAPI plugin) {
        return new CurrencyConfigProvide(plugin);
    }

    /**
     * 获取玩家配置文件
     *
     * @param name 玩家名称
     * @return Config
     */
    private Config getPlayerConfig(@NotNull String name) {
        if (this.exists(name)) {
            return new Config(new File(playerDataFolder, name + ".yml"), Config.YAML);
        } else {
            return createConfig(name);
        }
    }

    /**
     * 获取玩家的金币数量
     *
     * @param player 玩家实例
     * @return int
     */
    public int myMoney(@NotNull Player player) {
        return myMoney(player.getName());
    }

    /**
     * 获取玩家的金币数量
     *
     * @param name 玩家实例
     * @return int
     */
    public int myMoney(@NotNull String name) {
        return getPlayerConfig(name).getInt("money");
    }

    /**
     * 设置玩玩家的金币
     *
     * @param player 玩家
     * @param money  数量
     * @return 是否成功
     */
    public boolean setPlayerMoney(@NotNull Player player, int money) {
        return setPlayerMoney(player.getName(), money);
    }

    /**
     * 设置玩玩家的金币
     *
     * @param name  玩家
     * @param money 数量
     * @return 是否成功
     */
    public boolean setPlayerMoney(@NotNull String name, int money) {
        MoneyChangeEvent event = new MoneyChangeEvent(name, MoneyChangeEvent.ACTION_SET, money);
        Server.getInstance().getPluginManager().callEvent(event);
        if (!event.isCancelled()) {
            Config config = getPlayerConfig(name);
            config.set("money", event.getMoney());
            return config.save();
        }
        return false;
    }

    /**
     * 为指定玩家添加金币
     *
     * @param player 玩家实例
     * @param money  数量
     * @return 是否成功
     */
    public boolean addPlayerMoney(@NotNull Player player, int money) {
        return addPlayerMoney(player.getName(), money);
    }

    /**
     * 为指定玩家添加金币
     *
     * @param name  玩家实例
     * @param money 数量
     * @return 是否成功
     */
    public boolean addPlayerMoney(@NotNull String name, int money) {
        Config config = getPlayerConfig(name);
        int result = config.getInt("money") + money;
        MoneyChangeEvent event = new MoneyChangeEvent(name, MoneyChangeEvent.ACTION_ADD, result);
        Server.getInstance().getPluginManager().callEvent(event);
        if (!event.isCancelled()) {
            config.set("money", event.getMoney());
            return config.save();
        }
        return false;
    }

    /**
     * 扣除指定玩家金币
     *
     * @param player 玩家实例
     * @param money  数量
     * @return 是否成功
     */
    public boolean reducePlayerMoney(@NotNull Player player, int money) {
        return reducePlayerMoney(player.getName(), money);
    }

    /**
     * 扣除指定玩家金币
     *
     * @param name  玩家实例
     * @param money 数量
     * @return 是否成功
     */
    public boolean reducePlayerMoney(@NotNull String name, int money) {
        Config config = getPlayerConfig(name);
        int result = config.getInt("money", money) - money;
        MoneyChangeEvent event = new MoneyChangeEvent(name, MoneyChangeEvent.ACTION_REDUCE, result);
        Server.getInstance().getPluginManager().callEvent(event);
        if (!event.isCancelled()) {
            config.set("money", Math.max(event.getMoney(), 0));
            return config.save();
        }
        return false;
    }


    /**
     * 获取玩家的点券数量
     *
     * @param player 玩家实例
     * @return int
     */
    public int myPoint(@NotNull Player player) {
        return myPoint(player.getName());
    }

    /**
     * 获取玩家的点券数量
     *
     * @param name 玩家实例
     * @return int
     */
    public int myPoint(@NotNull String name) {
        return getPlayerConfig(name).getInt("point", 0);
    }

    /**
     * 设置玩玩家的金币
     *
     * @param player 玩家
     * @param point  数量
     * @return 是否成功
     */
    public boolean setPlayerPoint(@NotNull Player player, int point) {
        return setPlayerPoint(player.getName(), point);
    }

    /**
     * 设置玩玩家的点券
     *
     * @param name  玩家
     * @param point 数量
     * @return 是否成功
     */
    public boolean setPlayerPoint(@NotNull String name, int point) {
        Config config = getPlayerConfig(name);
        PointChangeEvent event = new PointChangeEvent(name, PointChangeEvent.ACTION_SET, point);
        Server.getInstance().getPluginManager().callEvent(event);
        if (!event.isCancelled()) {
            config.set("point", point);
            return config.save();
        }
        return false;
    }

    /**
     * 为指定玩家添加点券
     *
     * @param player 玩家实例
     * @param point  数量
     * @return 是否成功
     */
    public boolean addPlayerPoint(@NotNull Player player, int point) {
        return addPlayerPoint(player.getName(), point);
    }

    /**
     * 为指定玩家添加点券
     *
     * @param name  玩家实例
     * @param point 数量
     * @return 是否成功
     */
    public boolean addPlayerPoint(@NotNull String name, int point) {
        Config config = getPlayerConfig(name);
        int result = config.getInt("point") + point;
        PointChangeEvent event = new PointChangeEvent(name, PointChangeEvent.ACTION_ADD, result);
        Server.getInstance().getPluginManager().callEvent(event);
        if (!event.isCancelled()) {
            config.set("point", event.getMoney());
            return config.save();
        }
        return false;
    }

    /**
     * 扣除指定玩家点券
     *
     * @param player 玩家实例
     * @param point  数量
     * @return 是否成功
     */
    public boolean reducePlayerPoint(@NotNull Player player, int point) {
        return reducePlayerPoint(player.getName(), point);
    }

    /**
     * 扣除指定玩家点券
     *
     * @param name  玩家实例
     * @param point 数量
     * @return 是否成功
     */
    public boolean reducePlayerPoint(@NotNull String name, int point) {
        Config config = getPlayerConfig(name);
        int result = config.getInt("point", point) - point;
        PointChangeEvent event = new PointChangeEvent(name, PointChangeEvent.ACTION_REDUCE, result);
        Server.getInstance().getPluginManager().callEvent(event);
        if (!event.isCancelled()) {
            config.set("point", Math.max(event.getMoney(), 0));
            return config.save();
        }
        return false;
    }

}
