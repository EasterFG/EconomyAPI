# EconomyAPI

## Introduction
This is a basic economic plugin.It can provide basic economic functions for your server.If you want to use it, you must use MCPE 1.2 and above.
Please click [here](https://github.com/EasterFG/EconomyAPI/releases) to download

## Basic Information
version: 1.1.3</br>
api: 1.0.5,1.0.6,1.0.7,1.0.8

## Usage API
You must first import this package
```Java
import com.easterfg.economyapi.EconomyAPI
```

You must call this method to get an instance that can be manipulated.
```Java
EconomyAPI.getInstance()
```

Finally, you can call other methods.

## Methods List
```Java
boolean EconomyAPI.exists(String name, int money)
boolean EconomyAPI.exists(Player player, int money)
//金币系统API
boolean EconomyAPI.setPlayerMoney(String name, int money)
boolean EconomyAPI.setPlayerMoney(Player player, int money)
boolean EconomyAPI.addPlayerMoney(String name, int money)
boolean EconomyAPI.addPlayerMoney(Player player, int money)
boolean EconomyAPI.reducePlayerMoney(String name, int money)
boolean EconomyAPI.reducePlayerMoney(Player player, int money)
int EconomyAPI.myMoney(String name)
int EconomyAPI.myMoney(Player player)
//点券系统API
boolean EconomyAPI.setPlayerPoint(String name, int money)
boolean EconomyAPI.setPlayerPoint(Player player, int money)
boolean EconomyAPI.addPlayerPoint(String name, int money)
boolean EconomyAPI.addPlayerPoint(Player player, int money)
boolean EconomyAPI.reducePlayerPoint(String name, int money)
boolean EconomyAPI.reducePlayerPoint(Player player, int money)
int EconomyAPI.myPoint(String name)
int EconomyAPI.myPoint(Player player)
```

## Bug feedback and communication
* E-mail: EasterFG@gmail.com
* Link ID: 00000000

