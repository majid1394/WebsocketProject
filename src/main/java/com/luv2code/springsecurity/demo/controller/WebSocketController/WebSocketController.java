package com.luv2code.springsecurity.demo.controller.WebSocketController;

import com.google.gson.Gson;
import com.luv2code.springsecurity.demo.dao.ActiveUserStore.ActiveUserStoreImpl;
import com.luv2code.springsecurity.demo.entity.Users;
import com.luv2code.springsecurity.demo.service.QuizServices.AdminQuizService;
import com.sun.javafx.collections.MappingChange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.google.gson.Gson;
import com.luv2code.springsecurity.demo.dao.ActiveUserStore.ActiveUserStoreImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.*;

import java.security.Principal;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
@Configuration
@Controller
public class WebSocketController {
    @Autowired
    private ActiveUserStoreImpl activeUserStore;

    @Autowired
    private SimpMessageSendingOperations messagingTemplate;


    @Autowired
    private SimpMessagingTemplate simpleMessageConverter;
    @Autowired
    private AdminQuizService adminQuizService;



    @MessageMapping("/message")
    @SendToUser("/topic/activeUsers")
    public void processMessageFromClient( ) throws Exception
    {
        for (int i=0; i<activeUserStore.getUsers().size(); i++)
        {
            //be khodash digar online bodane khodash ra nagoyad
       /*     if (userLogin!=activeUserStore.getUsers().get(i))
            {*/

                this.simpleMessageConverter.convertAndSendToUser(activeUserStore.getUsers().get(i), "/topic/activeUsers", activeUserStore.getUsers());

                //this.simpleMessageConverter.convertAndSendToUser(activeUserStore.getUsers().get(i), "/topic/update", userLogin);
         /*   }*/
        }
    }
    @MessageMapping("/requestResult")
    @SendToUser("/topic/testResult")
    public void TestResult(Authentication authentication) throws IOException {
        String username = authentication.getName();
        Users user = adminQuizService.getUserByUsername(username);
        Users QuestinerUser=adminQuizService.getUserByUsername(activeUserStore.getQuestionerUsername());

        Map<String, String> map = new HashMap<String, String>();
        map.put(username, String.valueOf(user.getScore()));

        Map<String, String> tempMAP = new HashMap<String, String>();
        tempMAP.put(QuestinerUser.getUsername(), String.valueOf(QuestinerUser.getScore()));


            for (int i = 0; i < activeUserStore.getUsers().size(); i++)
            {

                   this.simpleMessageConverter.convertAndSendToUser(activeUserStore.getUsers().get(i), "/topic/testResult", map);


            }

            for (int i = 0; i < activeUserStore.getUsers().size(); i++)
            {
               /* this.simpleMessageConverter.convertAndSendToUser(activeUserStore.getUsers().get(i), "/topic/testResult", map);*/
                if (!user.getUsername().equals(activeUserStore.getQuestionerUsername()))
                {
                    this.simpleMessageConverter.convertAndSendToUser(activeUserStore.getUsers().get(i), "/topic/testResult", tempMAP);
                }
            }

        /*this.simpleMessageConverter.convertAndSendToUser(activeUserStore.getQuestionerUsername(), "/topic/testResult", tempMAP);*/

      }


   @MessageMapping("/logout")
    @SendToUser("/topic/logout")
    public void logoutUser(Authentication authentication) throws Exception
   {
       List<String> users=new ArrayList<String>();
        for (int i=0; i<activeUserStore.getUsers().size(); i++)
        {
            if (activeUserStore.getUsers().get(i) != authentication.getName())
            {
              users.add(i, activeUserStore.getUsers().get(i));
            }
        }

       for (int i=0; i<users.size(); i++)
       {

        this.simpleMessageConverter.convertAndSendToUser(activeUserStore.getUsers().get(i), "/topic/logout", users);
         //this.simpleMessageConverter.convertAndSendToUser(activeUserStore.getUsers().get(i), "/topic/update", userLogin);
        }
         /*   }*/
     }
}

