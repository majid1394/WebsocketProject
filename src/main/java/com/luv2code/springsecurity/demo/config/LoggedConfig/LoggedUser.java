package com.luv2code.springsecurity.demo.config.LoggedConfig;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

import com.luv2code.springsecurity.demo.dao.ActiveUserStore.ActiveUserStoreImpl;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class LoggedUser implements HttpSessionBindingListener {

    private String username;
    private ActiveUserStoreImpl activeUserStore;

    public LoggedUser(String username, ActiveUserStoreImpl activeUserStore) {

        this.username = username;


        this.activeUserStore = activeUserStore;


    }

    public LoggedUser() {
    }

    @Override
    public void valueBound(HttpSessionBindingEvent event) {
                   List<String> userList = new ArrayList<String>();
                   boolean isTrue=true;
                   int count=0;
                if (activeUserStore.getUsers()==null)
                {
                    userList.add(0, username);
                }
                else
                    {

                    for (int i=0; i<activeUserStore.getUsers().size(); i++)
                    {
                        if (activeUserStore.getUsers().get(i) .equals(((LoggedUser) event.getValue()).username))
                        {
                            isTrue=false;

                        }
                    }

                    if (isTrue==true)
                    {
                        for (int i = 0; i < activeUserStore.getUsers().size(); i++)
                        {
                            userList.add(i, activeUserStore.getUsers().get(i));
                        }
                        userList.add(activeUserStore.getUsers().size(), username);
                    }
                }

                if (userList.size()!=0 && isTrue==true)
                activeUserStore.setUsers(userList);


        /*List<String> users = activeUserStore.getUsers();
        LoggedUser user = (LoggedUser) event.getValue();

if (users!=null) {
    if (!users.contains(user.getUsername())) {
        users.add(user.getUsername());
    }
}*/
    }

    @Override
    public void valueUnbound(HttpSessionBindingEvent event) {

        List<String> users = activeUserStore.getUsers();


        LoggedUser user = (LoggedUser) event.getValue();



        if (users!=null && user!=null) {

            if (users.contains(user.getUsername())) {
                users.remove(user.getUsername());
            }
        }
        else {
            activeUserStore.setQuestionerSelected(false);
            activeUserStore.setQuestionerSubmitedQuestion(false);
        }


    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
