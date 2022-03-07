package com.foobartory.managers;

import com.foobartory.events.EventHandler;
import com.foobartory.runners.Robot;

import java.util.ArrayList;
import java.util.List;

public class RobotManager {
    private final List<Robot> robots;

    private final StockManager stockManager;

    private final EventHandler eventHandler;

    public RobotManager(EventHandler eventHandler) {
        this.robots = new ArrayList<>();
        this.stockManager = new StockManager(eventHandler);
        this.eventHandler = eventHandler;
    }

    public void start(int initialRobots) {
        System.out.println("[RobotManager] Start the factory");
        for (int i = 1; i <= initialRobots; i++) {
            addRobotToProductionLine();
        }
    }

    private void addRobotToProductionLine() {
        Robot robot = new Robot(this,stockManager,eventHandler);
        robots.add(robot);
        robot.start();
    }

    public synchronized void addRobot() {
        System.out.println("[RobotManager] Buying a new robot");
        if (robots.size() < 30) {
            addRobotToProductionLine();
        } else {
            stop();
        }
    }

    private void stop() {
        for (Robot robot : robots) {
            robot.stop();
        }
        System.out.println("[RobotManager] END of factory");
    }

}
