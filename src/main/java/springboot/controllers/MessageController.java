/*
package springboot.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;
import springboot.models.Greeting;
import springboot.models.HelloMessage;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class MessageController {

    private  SimpMessagingTemplate template;


    @Autowired
    MessageController(SimpMessagingTemplate tm){
        this.template = tm;
    }

    @MessageMapping("/send/message")
    public void onReceivedMessage(String message){
        this.template.convertAndSend("/chat", new SimpleDateFormat("HH:mm:ss").format(new Date()) + " " + message);
    }
}*/
