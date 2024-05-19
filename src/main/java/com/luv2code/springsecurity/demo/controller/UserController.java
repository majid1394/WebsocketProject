package com.luv2code.springsecurity.demo.controller;


import com.luv2code.springsecurity.demo.config.LoggedConfig.MySimpleUrlAuthenticationSuccessHandler;
import com.luv2code.springsecurity.demo.dao.ActiveUserStore.ActiveUserStoreImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.converter.SimpleMessageConverter;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Controller
public class UserController {


    @Autowired
    ActiveUserStoreImpl activeUserStore;
    @Autowired
    MySimpleUrlAuthenticationSuccessHandler mySimpleUrlAuthenticationSuccessHandler;

    @Autowired
    private SimpMessagingTemplate simpleMessageConverter;


/*
    @Autowired
    IUserService userService;*/

    @RequestMapping(value = "/loggedUsers", method = RequestMethod.GET)
    public String getLoggedUsers(final Locale locale, final Model model, final HttpServletRequest request, final HttpServletResponse response, Authentication authentication) throws IOException {


      /*if (activeUserStore.getQuestionerUsername()!=null)
        {
            if (activeUserStore.getQuestionerUsername().equals(authentication.getName()))
            {
                activeUserStore.setQuestionerUsername(null);
                activeUserStore.setQuestionerSelected(false);
                activeUserStore.setQuestionerSubmitedQuestion(false);
            }

        }*/
        mySimpleUrlAuthenticationSuccessHandler.onAuthenticationSuccess(request,response,authentication);
        model.addAttribute("users", activeUserStore.getUsers());
        model.addAttribute("loginUser", authentication.getName());
        return "Users";
    }
    @RequestMapping(value = "/notifyToClients", method = RequestMethod.GET)
    public String notifyToClients(final Locale locale, final Model model, final HttpServletRequest request, final HttpServletResponse response, Authentication authentication) throws IOException
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
            this.simpleMessageConverter.convertAndSendToUser(activeUserStore.getUsers().get(i), "/topic/activeUsers", users);
            //this.simpleMessageConverter.convertAndSendToUser(activeUserStore.getUsers().get(i), "/topic/update", userLogin);
        }
        return "redirect:/logout";
    }

    @RequestMapping(value = "/playAgain", method = RequestMethod.GET)
    public String backToPlayAgain(final HttpServletRequest request, final HttpServletResponse response, Authentication authentication) throws IOException
    {
           if (activeUserStore.getQuestionerUsername()!=null)
        {
            if (activeUserStore.getQuestionerUsername().equals(authentication.getName()))
            {
                activeUserStore.setQuestionerUsername(null);
                activeUserStore.setQuestionerSelected(false);
                activeUserStore.setQuestionerSubmitedQuestion(false);
            }
        }
        return "Users";
    }

/*    @RequestMapping(value = "/loggedUsersFromSessionRegistry", method = RequestMethod.GET)
    public String getLoggedUsersFromSessionRegistry(final Locale locale, final Model model) {
        model.addAttribute("users", userService.getUsersFromSessionRegistry());
        return "users";
    }*/
}
