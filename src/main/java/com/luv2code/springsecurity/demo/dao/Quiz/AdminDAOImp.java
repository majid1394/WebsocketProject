package com.luv2code.springsecurity.demo.dao.Quiz;


import com.luv2code.springsecurity.demo.entity.QuizEntity.QuizDB;
import com.luv2code.springsecurity.demo.entity.Users;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class AdminDAOImp implements IAdminDAO {
    @PersistenceContext
    EntityManager entityManager;


    @Override
    public String persisQuestion(QuizDB quizDB) {


        entityManager.persist(quizDB);
        return "true";
    }

    @Override
    public QuizDB findQuestion() {
        Query query=entityManager.createNamedQuery("getQuizDB");
        List<QuizDB> quizDBs= query.getResultList();
            return quizDBs.get(quizDBs.size()-1);
        }
        @Override
    public QuizDB findQuestionById(int Id) {
        Query query=entityManager.createNamedQuery("getQuizDB_ById");
        query.setParameter("id",Id);
        List<QuizDB> quizDB= query.getResultList();
        if (quizDB.size() >0 )
        {
            return quizDB.get(0);
        }
         return  null;
        }

    @Override
    public Users findUserByUsername(String username) {
        Query query=entityManager.createNamedQuery("getUserByUsername");
        query.setParameter("username",username);
        List<Users> User= query.getResultList();
        if (User.size() >0 )
        {
            return User.get(0);
        }
        return  null;
    }

    @Override
    public void updateUser(Users users)
    {
       entityManager.merge(users);
    }


}

