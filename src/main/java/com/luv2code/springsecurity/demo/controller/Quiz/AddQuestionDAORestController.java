package com.luv2code.springsecurity.demo.controller.Quiz;

import com.luv2code.springsecurity.demo.dao.ActiveUserStore.ActiveUserStoreImpl;
import com.luv2code.springsecurity.demo.entity.QuizEntity.QuizDB;
import com.luv2code.springsecurity.demo.entity.Users;
import com.luv2code.springsecurity.demo.service.QuizServices.AdminQuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@RestController
public class AddQuestionDAORestController {

    @Autowired(required=true)
    private AdminQuizService adminQuizService;
    @Autowired
    private SimpMessagingTemplate simpleMessageConverter;

    @Autowired
    private ActiveUserStoreImpl activeUserStore;
    @RequestMapping(value = "/addQuiz", method = RequestMethod.POST)
    public String addQuiz(@RequestBody  QuizDB quizDB,Authentication authentication) throws IOException
    {
        try
        {
            String result = adminQuizService.addQuestion(quizDB);
            activeUserStore.setQuestionerSubmitedQuestion(true);
                List<String> users=new ArrayList<String>();
                for (int i=0; i<activeUserStore.getUsers().size(); i++)
                {
                    if (activeUserStore.getUsers().get(i) != authentication.getName())
                    {
                        this.simpleMessageConverter.convertAndSendToUser(activeUserStore.getUsers().get(i), "/topic/SubmitedQuestion", quizDB);
                    }
                }
            return result;
        }
        catch (Exception e)
        {
            return null;
        }
        //mySimpleUrlAuthenticationSuccessHandler.onAuthenticationSuccess(request,response,authentication);
        //model.addAttribute("users", activeUserStore.getUsers());
        //   model.addAttribute("loginUser", authentication.getName());
        //  model.addAttribute(request.getParameter("question"));
    }

    @RequestMapping(value = "/CheckQuestionerSubmited", method = RequestMethod.GET)
    public boolean CheckQuestionSubmited(final Model model,Authentication authentication) throws IOException
    {
        boolean result= activeUserStore.isQuestionerSubmitedQuestion();
        return result;
    }
    @RequestMapping(value = "/checkAnswer", method = RequestMethod.POST)
    public String CheckQuestionSubmited(@RequestBody QuizDB UserAnswerSelected,Authentication authentication) throws IOException
    {
        String username=authentication.getName();
        QuizDB correctedAnswer = adminQuizService.getQuestionsByQuestionId(UserAnswerSelected.getId());
        Users user = adminQuizService.getUserByUsername(username);


        if (correctedAnswer!=null)
        {
            if (correctedAnswer.getAnswer().equals(UserAnswerSelected.getAnswer())) {
                int score = user.getScore() + 1;
                user.setScore(score);
                adminQuizService.updateScore(user);

                String questionerUsername = activeUserStore.getQuestionerUsername();
                Users questionerUser = adminQuizService.getUserByUsername(questionerUsername);
                questionerUser.setScore(score);
                adminQuizService.updateScore(questionerUser);
            }
        }
        else {
                int score = user.getScore() - 1;
                user.setScore(score);
                adminQuizService.updateScore(user);

                String questionerUsername = activeUserStore.getQuestionerUsername();
                Users questionerUser = adminQuizService.getUserByUsername(questionerUsername);
                questionerUser.setScore(score);
                adminQuizService.updateScore(questionerUser);
            }


//get user after update score;




        return "result";
}





}
