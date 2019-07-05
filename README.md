# EconomyAPI

## Introduction
This is a basic economic plugin.It can provide basic economic functions for your server.If you want to use it, you must use MCPE 1.2 and above.
Please click [here](https://github.com/EasterFG/EconomyAPI/releases) to download

## Basic Information
version: 1.0.1</br>
api: 1.0.5,1.0.6

## Commands
* /money set <name> <money> Set a player's gold coin.

* /money add <name> <money> Increase the player's gold coins.

* /money reduce <name> <money> Deduct the player's gold coins.

* /mymoney View my gold coins

* /pay <name> <money> Pay other documents gold coins

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
boolean EconomyAPI.setPlayerMoney(String name, int money)
boolean EconomyAPI.setPlayerMoney(Player player, int money)
boolean EconomyAPI.addPlayerMoney(String name, int money)
boolean EconomyAPI.addPlayerMoney(Player player, int money)
boolean EconomyAPI.reducePlayerMoney(String name, int money)
boolean EconomyAPI.reducePlayerMoney(Player player, int money)
boolean EconomyAPI.exists(String name, int money)
boolean EconomyAPI.exists(Player player, int money)
int EconomyAPI.myMoney(String name)
int EconomyAPI.myMoney(Player player)
```

## Bug feedback and communication
* E-mail: EasterFG@gmail.com
* Link ID: 00000000

