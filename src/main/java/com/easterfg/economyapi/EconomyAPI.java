package com.easterfg.economyapi;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.EventPriority;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerJoinEvent;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.TextFormat;

import java.util.regex.Pattern;

public class EconomyAPI extends PluginBase implements Listener {

    private static CurrencyConfigProvide provide;

    @Override
    public void onEnable() {
        this.getLogger().info("EconomyAPI开启成功");
        provide = CurrencyConfigProvide.getInstance(this);
        getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {

    }

    public static CurrencyConfigProvide getInstance() {
        return provide;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
           String cmd = command.getName();
           if (cmd.equals("money")) {
               if (args.length < 1 || args.length > 3) {
                   sender.sendMessage("§6使用方法: §a /money <set/add/reduce> <name> <money>");
                   return true;
               }
               if (args[0].equals("set")) {
                   if (args.length != 3) {
                       sender.sendMessage(TextFormat.GREEN + "使用方法:" + TextFormat.GOLD + "/money set <name> <money>");
                   }else {
                       if (sender instanceof Player) {
                           Player target;
                           int money;
                           if ( (target = getServer().getPlayer(args[1])) == null) {
                               sender.sendMessage(TextFormat.RED + "无法找到该玩家!");
                           }else if (isInteger(args[2])) {
                               money = Integer.valueOf(args[2]);
                               if (money < 0) {
                                   sender.sendMessage(TextFormat.RED + "金额必须大于零!");
                               }else {
                                   sender.sendMessage(TextFormat.GREEN + "成功设置玩家:" + TextFormat.GOLD + target.getName() + TextFormat.GREEN +"金币为"+ TextFormat.YELLOW + money );
                                   provide.setPlayerMoney(target, money);
                               }
                           }else {
                               sender.sendMessage(TextFormat.RED + "金额必须是一个数字!");
                           }
                       }else {
                           int money;
                           if (!provide.exists(args[1])) {
                               sender.sendMessage(TextFormat.RED + "目标玩家不存在");
                           }else if (isInteger(args[2])) {
                               money = Integer.valueOf(args[2]);
                               if (money < 0) {
                                   sender.sendMessage(TextFormat.RED + "金额必须大于零!");
                               }else {
                                   sender.sendMessage(TextFormat.GREEN + "成功设置玩家:" + TextFormat.GOLD + args[1] + TextFormat.GREEN +"金币为"+ TextFormat.YELLOW + money );
                                   provide.setPlayerMoney(args[1], money);
                               }
                           }else {
                               sender.sendMessage(TextFormat.RED + "金额必须是一个数字!");
                           }
                       }
                   }
               }else if (args[0].equals("add")) {
                   if (args.length != 3) {
                       sender.sendMessage(TextFormat.GREEN + "使用方法:" + TextFormat.GOLD + "/money add <name> <money>");
                   }else {
                       if (sender instanceof Player) {
                           Player target;
                           int money;
                           if ( (target = getServer().getPlayer(args[1])) == null) {
                               sender.sendMessage(TextFormat.RED + "无法找到该玩家!");
                           }else if ((isInteger(args[2]))) {
                               money = Integer.valueOf(args[2]);
                               if (money < 0) {
                                   sender.sendMessage(TextFormat.RED + "金额必须大于零!");
                               }else {
                                   sender.sendMessage(TextFormat.GREEN + "成功给予玩家:" + TextFormat.GOLD + target.getName() + TextFormat.YELLOW + money + TextFormat.GREEN +"金币" );
                                   provide.addPlayerMoney(target, money);
                               }
                           }else {
                               sender.sendMessage(TextFormat.RED + "金额必须是一个数字!");
                           }
                       }else {
                           int money;
                           if (!provide.exists(args[1])) {
                               sender.sendMessage(TextFormat.RED + "目标玩家不存在");
                           }else if ((isInteger(args[2]))) {
                               money = Integer.valueOf(args[2]);
                               if (money < 0) {
                                   sender.sendMessage(TextFormat.RED + "金额必须大于零!");
                               }else {
                                   sender.sendMessage(TextFormat.GREEN + "成功给予玩家:" + TextFormat.GOLD + args[1] + TextFormat.YELLOW + money + TextFormat.GREEN +"金币" );
                                   provide.addPlayerMoney(args[1], money);
                               }
                           }else {
                               sender.sendMessage(TextFormat.RED + "金额必须是一个数字!");
                           }
                       }
                   }
               }else if (args[0].equals("reduce")) {
                   if (args.length != 3) {
                       sender.sendMessage(TextFormat.GREEN + "使用方法:" + TextFormat.GOLD + "/money reduce <name> <money>");
                   }else {
                       if (sender instanceof Player) {
                           Player target;
                           int money;
                           if ( (target = getServer().getPlayer(args[1])) == null) {
                               sender.sendMessage(TextFormat.RED + "无法找到该玩家!");
                           }else if ((isInteger(args[2]))) {
                               money = Integer.valueOf(args[2]);
                               if (money < 0) {
                                   sender.sendMessage(TextFormat.RED + "金额必须大于零!");
                               }else {
                                   sender.sendMessage(TextFormat.GREEN + "成功扣除玩家:" + TextFormat.GOLD + target.getName() + TextFormat.YELLOW + money + TextFormat.GREEN +"金币" );
                                   provide.reducePlayerMoney(target, money);
                               }
                           }else {
                               sender.sendMessage(TextFormat.RED + "金额必须是一个数字!");
                           }
                       } else {
                           int money;
                           if (!provide.exists(args[1])) {
                               sender.sendMessage(TextFormat.RED + "目标玩家不存在");
                           } else if ((isInteger(args[2]))) {
                               money = Integer.valueOf(args[2]);
                               if (money < 0) {
                                   sender.sendMessage(TextFormat.RED + "金额必须大于零!");
                               } else {
                                   sender.sendMessage(TextFormat.GREEN + "成功扣除玩家:" + TextFormat.GOLD + args[1] + TextFormat.YELLOW + money + TextFormat.GREEN + "金币");
                                   provide.reducePlayerMoney(args[1], money);
                               }
                           } else {
                               sender.sendMessage(TextFormat.RED + "金额必须是一个数字!");
                           }
                       }
                   }
               }
           }else if (cmd.equals("mymoney")) {
               if (sender instanceof  Player) {
                   if (args.length == 0) {
                       sender.sendMessage(TextFormat.GREEN + "你当前拥有" + TextFormat.YELLOW + provide.myMoney((Player) sender) + TextFormat.GREEN + "金币");
                   }else {
                       sender.sendMessage(TextFormat.GREEN + "使用方法:" + TextFormat.GOLD + "/mymoney");
                   }
               }else {
                   sender.sendMessage(TextFormat.RED + "你只能在游戏中使用这个命令!");
               }
           } else if (cmd.equals("pay")) {
               if (sender instanceof Player) {
                   if (args.length != 2) {
                       sender.sendMessage(TextFormat.GREEN + "使用方法:" + TextFormat.GOLD + "/reducemoney <name> <money>");
                   }else {
                       Player target;
                       int money;
                       if ((target = getServer().getPlayer(args[0])) == null)
                           sender.sendMessage(TextFormat.RED + "无法找到该玩家!");
                       else if (target.getName().equals(sender.getName())) {
                           sender.sendMessage(TextFormat.RED + "你无法和自己进行交易!");
                       } else if ((isInteger(args[1]))) {
                           money = Integer.valueOf(args[1]);
                           if (money < 0) {
                               sender.sendMessage(TextFormat.RED + "金额必须大于零!");
                           } else {
                              if (provide.myMoney((Player) sender) < money) {
                                  sender.sendMessage(TextFormat.RED + "你没有足够的金币");
                              }else {
                                  sender.sendMessage(TextFormat.GREEN + "成功付给玩家" + TextFormat.GOLD + target.getName() + TextFormat.GOLD + money + TextFormat.GREEN + "金币");
                                  provide.reducePlayerMoney((Player) sender, money);
                                  provide.addPlayerMoney(target, money);
                              }
                           }
                       } else {
                           sender.sendMessage(TextFormat.RED + "金额必须是一个数字!");
                       }

                   }
               }else {
                   sender.sendMessage(TextFormat.RED + "你只能在游戏中使用这个命令!");
               }
           }
        return true;
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (provide.exists(player)) {
            provide.createConfig(player.getName());
        }
    }

    private static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }
}
