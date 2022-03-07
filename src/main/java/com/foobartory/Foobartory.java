package com.foobartory;

import com.foobartory.events.EventHandler;
import com.foobartory.managers.RobotManager;
import com.foobartory.managers.StockManager;
import com.foobartory.runners.Robot;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.security.Principal;


public class Foobartory implements Runnable {

    private final Principal user;
    private final SimpMessagingTemplate template;

    public Foobartory(Principal user, SimpMessagingTemplate template){
        this.user = user;
        this.template = template;
    }

    @Override
    public void run() {
        EventHandler ev = new EventHandler(this.user,this.template);
        RobotManager manager = new RobotManager(ev);
        manager.start(2);
    }
}
