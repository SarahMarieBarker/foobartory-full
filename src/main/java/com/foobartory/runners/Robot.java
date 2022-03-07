package com.foobartory.runners;

import com.foobartory.events.EventHandler;
import com.foobartory.factory.ActionFactory;
import com.foobartory.managers.RobotManager;
import com.foobartory.managers.StockManager;
import com.foobartory.models.Action;
import com.google.gson.annotations.Expose;

import java.text.MessageFormat;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;

public class Robot implements Runnable {
    @Expose
    private final UUID serialNumber;
    @Expose
    private final AtomicBoolean running = new AtomicBoolean(false);
    RobotManager robotManager;
    private Thread thread;
    @Expose
    private Action currentAction;
    private StockManager stockManager;
    private EventHandler eventHandler;
    @Expose
    private boolean waiting;

    public Robot(RobotManager robotManager, StockManager stockManager, EventHandler eventHandler) {
        this.robotManager = robotManager;
        this.serialNumber = UUID.randomUUID();
        this.stockManager = stockManager;
        this.waiting = false;
        this.eventHandler = eventHandler;
    }

    public String getSerialNumber() {
        return serialNumber.toString();
    }

    public void start() {
        thread = new Thread(this);
        thread.start();
    }

    public void stop() {
        running.set(false);
        thread.interrupt();
        eventHandler.sendRobot(this);
    }

    boolean isRunning() {
        return running.get();
    }

    @Override
    public void run() {
        running.set(true);
        while (isRunning()) {
            Action action = ActionFactory.getAction(this,this.stockManager);
            try {
                if (this.currentAction != null && !this.currentAction.getActionType().equals(action.getActionType())) {
                    changeAction();
                }
                this.currentAction = action;
                executeAction(action);
            } catch (InterruptedException e) {
                System.out.println(MessageFormat.format("[Robot : {0}] Stopped", this.serialNumber));
            }
        }
    }

    private void changeAction() throws InterruptedException {
        this.waiting = true;
        eventHandler.sendRobot(this);
        System.out.println(MessageFormat.format("[Robot : {0}] Changing action", this.serialNumber));
        Thread.sleep(5000);
        this.waiting = false;
        eventHandler.sendRobot(this);
    }

    private void executeAction(Action action) throws InterruptedException {
        eventHandler.sendRobot(this);
        System.out.println(MessageFormat.format("[Robot : {0}] Starting to do action {1}", this.serialNumber, action.getActionType().toString()));
        Thread.sleep(action.getActionTime());
        updateResources(action);
    }

    private void updateResources(Action action) {
        if (action.getActionType().equals(Action.BUY.getActionType())) {
            robotManager.addRobot();
        } else {
            this.stockManager.updateResultingResources(action);
        }
    }
}