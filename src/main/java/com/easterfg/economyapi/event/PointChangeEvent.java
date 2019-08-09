package com.easterfg.economyapi.event;

import cn.nukkit.event.Cancellable;
import cn.nukkit.event.Event;

public class PointChangeEvent extends Event implements Cancellable {

    public static final int ACTION_ADD = 0;
    public static final int ACTION_REDUCE = 1;
    public static final int ACTION_SET = 2;

    private int action;
    private int money;

    private String name;

    public PointChangeEvent(String name, int action, int money) {
        this.name = name;
        this.action = action;
        this.money = money;
    }

    public int getAction() {
        return action;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getName() {
        return name;
    }

}
