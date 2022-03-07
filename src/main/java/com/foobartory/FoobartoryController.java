
package com.foobartory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.TaskExecutor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;


import java.security.Principal;

@Controller
public class FoobartoryController {

    @Autowired
    private SimpMessagingTemplate template;

    @Autowired
    @Qualifier("CustomTaskExecutor")
    private TaskExecutor taskExecutor;

    @MessageMapping("/start")
    @SendToUser("/queue/start")
    public String greeting(@Payload String message, Principal principal) throws Exception {
        taskExecutor.execute(new Foobartory(principal,template));
        return "started";
    }

}