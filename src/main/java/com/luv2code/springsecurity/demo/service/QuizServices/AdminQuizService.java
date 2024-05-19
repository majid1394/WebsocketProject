package com.luv2code.springsecurity.demo.service.QuizServices;

import com.luv2code.springsecurity.demo.entity.QuizEntity.QuizDB;
import com.luv2code.springsecurity.demo.entity.Users;
import org.json.simple.JSONArray;

public interface AdminQuizService {
  /*  public  String login(String email, String password);*/
    public  String addQuestion(QuizDB quizDB);

    public QuizDB getQuestions();
    public QuizDB getQuestionsByQuestionId(int Id);
    public Users getUserByUsername(String username);
     public  void  updateScore(Users users);

/*    public  JSONArray getResult() ;
    public boolean checkAnswer(String id, String answer);
    public JSONObject getQuestion(String id);
    public String deleteQuestion(String id);
    public  boolean checkAnswerExists(String id, LinkedHashMap lhm);*/
}

