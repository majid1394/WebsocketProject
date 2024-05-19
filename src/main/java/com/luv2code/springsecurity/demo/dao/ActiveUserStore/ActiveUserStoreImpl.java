package com.luv2code.springsecurity.demo.dao.ActiveUserStore;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("ActiveUserStoreImpl")
public class ActiveUserStoreImpl  {

    private List<String> users;
    private  boolean QuestionerSelected;  //note: The default value for a Boolean (object) is null and The default value for a boolean (primitive) is false.
    private boolean QuestionerSubmitedQuestion;
    private String QuestionerUsername;






    public List<String> getUsers() {
        return users;
    }


    public void setUsers(List<String> users) {
        this.users = users;
    }

    public boolean getQuestionerSelected() {
        return QuestionerSelected;
    }

    public void setQuestionerSelected(boolean questionerSelected) {
        QuestionerSelected = questionerSelected;
    }


    public boolean isQuestionerSubmitedQuestion() {
        return QuestionerSubmitedQuestion;
    }

    public void setQuestionerSubmitedQuestion(boolean questionerSubmitedQuestion) {
        QuestionerSubmitedQuestion = questionerSubmitedQuestion;
    }

    public String getQuestionerUsername() {
        return QuestionerUsername;
    }

    public void setQuestionerUsername(String questionerUsername) {
        QuestionerUsername = questionerUsername;
    }




    /*public void addActiveUserStore(users dto)
    {  this.users.add(dto);    }*/

  /*  public void ActiveUserStore() {
        users = new ArrayList<String>();

    }*/
}