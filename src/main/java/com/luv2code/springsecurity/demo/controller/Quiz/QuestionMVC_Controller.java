package com.luv2code.springsecurity.demo.controller.Quiz;

import com.luv2code.springsecurity.demo.dao.ActiveUserStore.ActiveUserStoreImpl;
import com.luv2code.springsecurity.demo.entity.QuizEntity.QuizDB;
import com.luv2code.springsecurity.demo.service.QuizServices.AdminQuizService;
import jdk.nashorn.internal.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Random;

@Controller
public class QuestionMVC_Controller {
    @Autowired(required=true)
    private AdminQuizService adminQuizService;
    @Autowired
    private ActiveUserStoreImpl activeUserStore;
    @Autowired
    private SimpMessagingTemplate simpleMessageConverter;

    String Questioner=null;


    //@RequestMapping(value = "/startPlay", method = RequestMethod.GET)
    //@ResponseBody(ModelAttribute QuizDB quizedb)
    @GetMapping("/startPlay")
    public String addQuestion(Model model, Authentication authentication) throws IOException, InterruptedException {

        //usernamePlayStarted is user that login now
        String username=authentication.getName();

        List<String> users = activeUserStore.getUsers();


        if (activeUserStore.getUsers().size()<2)
        {
            return "redirect:/loggedUsers";


          }




        //select an item from a list
        if (activeUserStore.getQuestionerSelected()==false)
        {
            Random randomizer = new Random();
             Questioner = users.get(randomizer.nextInt(users.size()));
             activeUserStore.setQuestionerUsername(Questioner);
            activeUserStore.setQuestionerSelected(true);


            if (Questioner.equals(username))
            {
                model.addAttribute("loginUser", authentication.getName());
//                model.addAttribute("activeUserStory",activeUserStore);
                return "/questionView/addQuestion";
            }
            else
            {
                QuizDB question=new QuizDB();
                question = adminQuizService.getQuestions();
                model.addAttribute("question", question);

                return "/questionView/test";

            }
        }
        else
            if (Questioner.equals(username))
            {
                model.addAttribute("loginUser", authentication.getName());
//                model.addAttribute("activeUserStory",activeUserStore);
                return "/questionView/addQuestion";
            }
            else
            {

            QuizDB question=new QuizDB();
            question = adminQuizService.getQuestions();
            model.addAttribute("question", question);
            model.addAttribute("submited",activeUserStore);

            for (int i=0; i<activeUserStore.getUsers().size(); i++)
            {
                if (activeUserStore.getUsers().get(i)!=Questioner)
                this.simpleMessageConverter.convertAndSendToUser(activeUserStore.getUsers().get(i), "/topic/QuestionSubmited", activeUserStore.isQuestionerSubmitedQuestion());
                //this.simpleMessageConverter.convertAndSendToUser(activeUserStore.getUsers().get(i), "/topic/update", userLogin);
         /*   }*/
            }

            return "/questionView/test";
        }


    }

    @RequestMapping(value = "/adminResult", method = RequestMethod.GET)
    public String addResult(final Locale locale, final Model model, final HttpServletRequest request, final HttpServletResponse response, Authentication authentication) throws IOException {
        //mySimpleUrlAuthenticationSuccessHandler.onAuthenticationSuccess(request,response,authentication);
        //model.addAttribute("users", activeUserStore.getUsers());
        model.addAttribute("loginUser", authentication.getName());
        return "temp/adminResult";
    }
    @RequestMapping(value = "/updateModel", method = RequestMethod.GET)
    public String updateModel(final Model model) throws IOException {
        QuizDB question=new QuizDB();
        question = adminQuizService.getQuestions();
        model.addAttribute("question", question);

        return "/questionView/test";
    }


    @RequestMapping(value = "/addQuestionDAO", method = RequestMethod.POST)
    public String addQuestionDAO(final Model model,HttpServletRequest request) throws IOException {
        //mySimpleUrlAuthenticationSuccessHandler.onAuthenticationSuccess(request,response,authentication);
        //model.addAttribute("users", activeUserStore.getUsers());
        //   model.addAttribute("loginUser", authentication.getName());
        //  model.addAttribute(request.getParameter("question"));
        return "/questionView/addQuestionDAO";
    }
   @RequestMapping(value = "/changeQuestioner", method = RequestMethod.GET)
    public String changeQuestioner(final Model model,Authentication authentication) throws IOException {
        activeUserStore.setQuestionerSelected(false);
        //mySimpleUrlAuthenticationSuccessHandler.onAuthenticationSuccess(request,response,authentication);
        //model.addAttribute("users", activeUserStore.getUsers());
        //   model.addAttribute("loginUser", authentication.getName());
        //  model.addAttribute(request.getParameter("question"));
      // model.addAttribute("loginUser", authentication.getName());

       return "redirect:/loggedUsers";
    }

    @RequestMapping(value = "/result", method = RequestMethod.GET)
    public String resultPage(final Model model,Authentication authentication) throws IOException {
        //activeUserStore.setQuestionerSelected(false);
        //mySimpleUrlAuthenticationSuccessHandler.onAuthenticationSuccess(request,response,authentication);
        //model.addAttribute("users", activeUserStore.getUsers());
        //   model.addAttribute("loginUser", authentication.getName());
        //  model.addAttribute(request.getParameter("question"));
      // model.addAttribute("loginUser", authentication.getName());

       return "/questionView/result";
    }











    /*    @RequestMapping(value = "/addQuestionDAO", method = RequestMethod.GET)
    public String addQuestionPage(final Model model,HttpServletRequest request) throws IOException {
        AdminQuizService adminQuizService = this.adminQuizService;
        //mySimpleUrlAuthenticationSuccessHandler.onAuthenticationSuccess(request,response,authentication);
        //model.addAttribute("users", activeUserStore.getUsers());
     //   model.addAttribute("loginUser", authentication.getName());
        model.addAttribute(request.getParameter("question"));
        return "/QuestionView/addQuestionDAO";
    }*/
}
