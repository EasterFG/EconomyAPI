package com.easterfg.economyapi;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.utils.TextFormat;

import java.util.regex.Pattern;

class Utils {

    static boolean processingCommand(CommandSender sender, Command command, String label, String[] args, EconomyAPI plugin) {
        String cmd = command.getName();
        switch (cmd) {
            case "money":
            case "point":
                boolean mode = cmd.equals("point");
                if (args.length < 1 || args.length > 3) {
                    if (mode)
                        sender.sendMessage("§6使用方法: §a /point <set/add/reduce> <name> <money>");
                    else
                        sender.sendMessage("§6使用方法: §a /money <set/add/reduce> <name> <money>");
                    return true;
                }
                switch (args[0]) {
                    case "set":
                        if (args.length != 3) {
                            if (mode)
                                sender.sendMessage(TextFormat.GREEN + "使用方法:" + TextFormat.GOLD + "/point set <name> <money>");
                            else
                                sender.sendMessage(TextFormat.GREEN + "使用方法:" + TextFormat.GOLD + "/money set <name> <money>");
                        } else {
                            if (sender instanceof Player) {
                                Player target;
                                int money;
                                if ((target = plugin.getServer().getPlayer(args[1])) == null) {
                                    sender.sendMessage(TextFormat.RED + "无法找到该玩家!");
                                } else if (isInteger(args[2])) {
                                    money = Integer.parseInt(args[2]);
                                    if (money < 0) {
                                        sender.sendMessage(TextFormat.RED + "金额必须大于零!");
                                    } else {
                                        if (mode) {
                                            sender.sendMessage(TextFormat.GREEN + "成功设置玩家:" + TextFormat.GOLD + target.getName() + TextFormat.GREEN + "点券为" + TextFormat.YELLOW + money);
                                            EconomyAPI.getInstance().setPlayerPoint(target, money);
                                        } else {
                                            sender.sendMessage(TextFormat.GREEN + "成功设置玩家:" + TextFormat.GOLD + target.getName() + TextFormat.GREEN + "金币为" + TextFormat.YELLOW + money);
                                            EconomyAPI.getInstance().setPlayerMoney(target, money);
                                        }

                                    }
                                } else {
                                    sender.sendMessage(TextFormat.RED + "金额必须是一个数字!");
                                }
                            } else {
                                int money;
                                if (!EconomyAPI.getInstance().exists(args[1])) {
                                    sender.sendMessage(TextFormat.RED + "目标玩家不存在");
                                } else if (isInteger(args[2])) {
                                    money = Integer.parseInt(args[2]);
                                    if (money < 0) {
                                        sender.sendMessage(TextFormat.RED + "金额必须大于零!");
                                    } else {
                                        if (mode) {
                                            sender.sendMessage(TextFormat.GREEN + "成功设置玩家:" + TextFormat.GOLD + args[1] + TextFormat.GREEN + "点券为" + TextFormat.YELLOW + money);
                                            EconomyAPI.getInstance().setPlayerPoint(args[1], money);
                                        } else {
                                            sender.sendMessage(TextFormat.GREEN + "成功设置玩家:" + TextFormat.GOLD + args[1] + TextFormat.GREEN + "金币为" + TextFormat.YELLOW + money);
                                            EconomyAPI.getInstance().setPlayerMoney(args[1], money);
                                        }
                                    }
                                } else {
                                    sender.sendMessage(TextFormat.RED + "金额必须是一个数字!");
                                }
                            }
                        }
                        break;
                    case "add":
                        if (args.length != 3) {
                            if (mode)
                                sender.sendMessage(TextFormat.GREEN + "使用方法:" + TextFormat.GOLD + "/point add <name> <money>");
                            else
                                sender.sendMessage(TextFormat.GREEN + "使用方法:" + TextFormat.GOLD + "/money add <name> <money>");
                        } else {
                            if (sender instanceof Player) {
                                Player target;
                                int money;
                                if ((target = plugin.getServer().getPlayer(args[1])) == null) {
                                    sender.sendMessage(TextFormat.RED + "无法找到该玩家!");
                                } else if ((isInteger(args[2]))) {
                                    money = Integer.parseInt(args[2]);
                                    if (money < 0) {
                                        sender.sendMessage(TextFormat.RED + "金额必须大于零!");
                                    } else {
                                        if (mode) {
                                            sender.sendMessage(TextFormat.GREEN + "成功给予玩家:" + TextFormat.GOLD + target.getName() + TextFormat.YELLOW + money + TextFormat.GREEN + "点券");
                                            EconomyAPI.getInstance().addPlayerPoint(target, money);
                                        } else {
                                            sender.sendMessage(TextFormat.GREEN + "成功给予玩家:" + TextFormat.GOLD + target.getName() + TextFormat.YELLOW + money + TextFormat.GREEN + "金币");
                                            EconomyAPI.getInstance().addPlayerMoney(target, money);
                                        }
                                    }
                                } else {
                                    sender.sendMessage(TextFormat.RED + "金额必须是一个数字!");
                                }
                            } else {
                                int money;
                                if (!EconomyAPI.getInstance().exists(args[1])) {
                                    sender.sendMessage(TextFormat.RED + "目标玩家不存在");
                                } else if ((isInteger(args[2]))) {
                                    money = Integer.parseInt(args[2]);
                                    if (money < 0) {
                                        sender.sendMessage(TextFormat.RED + "金额必须大于零!");
                                    } else {
                                        if (mode) {
                                            sender.sendMessage(TextFormat.GREEN + "成功给予玩家:" + TextFormat.GOLD + args[1] + TextFormat.YELLOW + money + TextFormat.GREEN + "点券");
                                            EconomyAPI.getInstance().addPlayerPoint(args[1], money);
                                        } else {
                                            sender.sendMessage(TextFormat.GREEN + "成功给予玩家:" + TextFormat.GOLD + args[1] + TextFormat.YELLOW + money + TextFormat.GREEN + "金币");
                                            EconomyAPI.getInstance().addPlayerMoney(args[1], money);
                                        }
                                    }
                                } else {
                                    sender.sendMessage(TextFormat.RED + "金额必须是一个数字!");
                                }
                            }
                        }
                        break;
                    case "reduce":
                        if (args.length != 3) {
                            if (mode)
                                sender.sendMessage(TextFormat.GREEN + "使用方法:" + TextFormat.GOLD + "/point reduce <name> <money>");
                            else
                                sender.sendMessage(TextFormat.GREEN + "使用方法:" + TextFormat.GOLD + "/money reduce <name> <money>");
                        } else {
                            if (sender instanceof Player) {
                                Player target;
                                int money;
                                if ((target = plugin.getServer().getPlayer(args[1])) == null) {
                                    sender.sendMessage(TextFormat.RED + "无法找到该玩家!");
                                } else if ((isInteger(args[2]))) {
                                    money = Integer.parseInt(args[2]);
                                    if (money < 0) {
                                        sender.sendMessage(TextFormat.RED + "金额必须大于零!");
                                    } else {
                                        if (mode) {
                                            sender.sendMessage(TextFormat.GREEN + "成功扣除玩家:" + TextFormat.GOLD + target.getName() + TextFormat.YELLOW + money + TextFormat.GREEN + "点券");
                                            EconomyAPI.getInstance().reducePlayerPoint(target, money);
                                        } else {
                                            sender.sendMessage(TextFormat.GREEN + "成功扣除玩家:" + TextFormat.GOLD + target.getName() + TextFormat.YELLOW + money + TextFormat.GREEN + "金币");
                                            EconomyAPI.getInstance().reducePlayerMoney(target, money);
                                        }
                                    }
                                } else {
                                    sender.sendMessage(TextFormat.RED + "金额必须是一个数字!");
                                }
                            } else {
                                int money;
                                if (!EconomyAPI.getInstance().exists(args[1])) {
                                    sender.sendMessage(TextFormat.RED + "目标玩家不存在");
                                } else if ((isInteger(args[2]))) {
                                    money = Integer.parseInt(args[2]);
                                    if (money < 0) {
                                        sender.sendMessage(TextFormat.RED + "金额必须大于零!");
                                    } else {
                                        if (mode) {
                                            sender.sendMessage(TextFormat.GREEN + "成功扣除玩家:" + TextFormat.GOLD + args[1] + TextFormat.YELLOW + money + TextFormat.GREEN + "点券");
                                            EconomyAPI.getInstance().reducePlayerPoint(args[1], money);
                                        } else {
                                            sender.sendMessage(TextFormat.GREEN + "成功扣除玩家:" + TextFormat.GOLD + args[1] + TextFormat.YELLOW + money + TextFormat.GREEN + "金币");
                                            EconomyAPI.getInstance().reducePlayerMoney(args[1], money);
                                        }
                                    }
                                } else {
                                    sender.sendMessage(TextFormat.RED + "金额必须是一个数字!");
                                }
                            }
                        }
                        break;
                }
                break;
            case "mymoney":
                if (sender instanceof Player) {
                    if (args.length == 0) {
                        sender.sendMessage(TextFormat.GREEN + "你当前拥有" + TextFormat.YELLOW + EconomyAPI.getInstance().myMoney((Player) sender) + TextFormat.GREEN + "金币");
                    } else {
                        sender.sendMessage(TextFormat.GREEN + "使用方法:" + TextFormat.GOLD + "/mymoney");
                    }
                } else {
                    sender.sendMessage(TextFormat.RED + "你只能在游戏中使用这个命令!");
                }
                break;
            case "mypoint":
                if (sender instanceof Player) {
                    if (args.length == 0) {
                        sender.sendMessage(TextFormat.GREEN + "你当前拥有" + TextFormat.YELLOW + EconomyAPI.getInstance().myPoint((Player) sender) + TextFormat.GREEN + "点券");
                    } else {
                        sender.sendMessage(TextFormat.GREEN + "使用方法:" + TextFormat.GOLD + "/mypoint");
                    }
                } else {
                    sender.sendMessage(TextFormat.RED + "你只能在游戏中使用这个命令!");
                }
                break;
            case "pay":
                if (sender instanceof Player) {
                    if (args.length != 2) {
                        sender.sendMessage(TextFormat.GREEN + "使用方法:" + TextFormat.GOLD + "/reducemoney <name> <money>");
                    } else {
                        Player target;
                        int money;
                        if ((target = plugin.getServer().getPlayer(args[0])) == null)
                            sender.sendMessage(TextFormat.RED + "无法找到该玩家!");
                        else if (target.getName().equals(sender.getName())) {
                            sender.sendMessage(TextFormat.RED + "你无法和自己进行交易!");
                        } else if ((isInteger(args[1]))) {
                            money = Integer.parseInt(args[1]);
                            if (money < 0) {
                                sender.sendMessage(TextFormat.RED + "金额必须大于零!");
                            } else {
                                if (EconomyAPI.getInstance().myMoney((Player) sender) < money) {
                                    sender.sendMessage(TextFormat.RED + "你没有足够的金币");
                                } else {
                                    sender.sendMessage(TextFormat.GREEN + "成功付给玩家" + TextFormat.GOLD + target.getName() + TextFormat.GOLD + money + TextFormat.GREEN + "金币");
                                    EconomyAPI.getInstance().reducePlayerMoney((Player) sender, money);
                                    EconomyAPI.getInstance().addPlayerMoney(target, money);
                                }
                            }
                        } else {
                            sender.sendMessage(TextFormat.RED + "金额必须是一个数字!");
                        }

                    }
                } else {
                    sender.sendMessage(TextFormat.RED + "你只能在游戏中使用这个命令!");
                }
                break;
        }
        return true;
    }

    private static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }
}
