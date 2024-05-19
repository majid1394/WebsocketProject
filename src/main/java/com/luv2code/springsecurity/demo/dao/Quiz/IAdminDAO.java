package com.luv2code.springsecurity.demo.dao.Quiz;

import com.luv2code.springsecurity.demo.entity.QuizEntity.QuizDB;
import com.luv2code.springsecurity.demo.entity.Users;

public interface IAdminDAO {
    public  String persisQuestion(QuizDB quizDB);
    public  QuizDB findQuestion() ;
    public QuizDB findQuestionById(int Id);
    public Users findUserByUsername(String username);
    public  void updateUser(Users users);



}
