package com.foobartory.events;

import com.foobartory.managers.StockManager;
import com.foobartory.runners.Robot;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.security.Principal;

public class EventHandler {

    private final Principal user;
    private final SimpMessagingTemplate template;
    private final Gson gsonBuilder;

    public EventHandler(Principal user, SimpMessagingTemplate template){
        this.user = user;
        this.template = template;
        this.gsonBuilder = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();
    }

    public synchronized void sendRobot(Robot robot){
        this.template.convertAndSendToUser(user.getName(),"/queue/robot",gsonBuilder.toJson(robot));
    }

    public synchronized void sendStock(StockManager stockManager){
        this.template.convertAndSendToUser(user.getName(),"/queue/stock",gsonBuilder.toJson(stockManager));
    }
}
