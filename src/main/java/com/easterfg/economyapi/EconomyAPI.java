package com.easterfg.economyapi;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.EventPriority;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerJoinEvent;
import cn.nukkit.plugin.PluginBase;

public class EconomyAPI extends PluginBase implements Listener {

    private static CurrencyConfigProvide provide;

    @Override
    public void onEnable() {
        this.getLogger().info("§aEconomyAPI开启成功");
        provide = CurrencyConfigProvide.getInstance(this);
        getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        this.getLogger().info("§aEconomyAPI插件关闭成功!感谢你的使用.");
    }

    static CurrencyConfigProvide getInstance() {
        return provide;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        return Utils.processingCommand(sender, command, label, args, this);
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (provide.exists(player)) {
            provide.createConfig(player.getName());
        }
    }


}
