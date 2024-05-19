package com.luv2code.springsecurity.demo.entity.QuizEntity;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="quiz")
@NamedQueries({
        @NamedQuery(name = "getQuizDB", query = "SELECT m FROM QuizDB m order by m.id asc "),
        @NamedQuery(name = "getQuizDB_ById", query = "SELECT m FROM QuizDB m where m.id =:id")
})
public class QuizDB {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int Id;

    @Column(name="question")
    private String question;

    @Column(name="correctedAnswers")
    private String correctedAnswers;

    @Column(name="a")
    private String a;


    @Column(name="b")
    private String b;

    @Column(name="c")
    private String c;

    @Column(name="d")
    private String d;


    @Column(name="answer")
    private String answer;




    public QuizDB() {

    }


    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question)
    {
        this.question = question;
    }



    public String getAnswer()
    {
        return answer;
    }

    public void setAnswer(String answer)
    {
        this.answer = answer;
    }

    public String getSet() {
        return correctedAnswers;
    }

    public void setSet(String set) {
        this.correctedAnswers = set;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public String getD() {
        return d;
    }

    public void setD(String d) {
        this.d = d;
    }
}
