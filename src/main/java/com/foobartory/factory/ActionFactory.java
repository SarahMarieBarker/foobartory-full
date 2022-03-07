package com.foobartory.factory;

import com.foobartory.managers.StockManager;
import com.foobartory.models.Action;
import com.foobartory.runners.Robot;

import java.text.MessageFormat;

public class ActionFactory {

    public static Action chooseAction(StockManager stockManager) {
        if (stockManager.getEuros() >= 3 && stockManager.getFooSize() < 6) {
            return Action.MAKEFOO;
        }
        if (stockManager.getFoobarSize() >= 5) {
            return Action.SELLFOOBAR;
        }
        if (stockManager.getEuros() >= 3 && stockManager.getFooSize() >= 6) {
            return Action.BUY;
        }
        if (stockManager.getFooSize() < 1) {
            return Action.MAKEFOO;
        }
        if (stockManager.getBarSize() < 1) {
            return Action.MAKEBAR;
        }
        if (stockManager.getFoobarSize() < 5) {
            return Action.MAKEFOOBAR;
        }
        return Action.MAKEFOO;
    }

    public static synchronized Action getAction(Robot robot, StockManager stockManager) {
        Action action = ActionFactory.chooseAction(stockManager);
        System.out.println(MessageFormat.format("[ActionFactory][Robot {0}] Picking a new action : {1}",robot.getSerialNumber(),action.getActionType()));
        stockManager.updateCostResources(action);
        return action;
    }
}