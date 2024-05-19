<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@page import="java.util.LinkedHashMap"%>
<%--<%@page import="com.UserDAO"%>--%>
<%@page import="com.luv2code.springsecurity.demo.dao.Quiz.AdminDAOImp"%>

<%--<%
String email=(String)session.getAttribute("email");
/*
if(email==null){
    response.sendRedirect("index.jsp");
}
*/

%>--%>

<html>
<head>


<title>Code Warrior</title>

    <link type="text/css"
          rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/css/quiz_style.css">
</head>
<jsp:include page="header.jsp" ></jsp:include>
<div>
    <center>

    </center>
    <script src="${pageContext.request.contextPath}/resources/js/stopm.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/sockjs.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/bootsrap.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/JQuery.js"></script>

<body  onload=QuestionerSubmitedQuestion()>

    <form >

        <div id="beforeSubmitQuestion">
            <span style='color: red' >Please wait while Questioner submit his/her question,
                it may takes 2 minuts</span>
        </div>
            <input type="submit" value="Logout"
                   style="color: #fff;
               background-color: #449d44;
                border-color: #255625;"onclick="return disconnect()"/>
            <%--<li><a href='adminLogout.jsp'>Logout</a></li>--%>



        <div id="section" <%--modelAttribute="question"--%> >
            <p id="time" ></p>
            <p id="test"></p>

            <p></b>Question:</p>
            <p id="question">
            <%--    <form:textarea rows="10" cols="100"  readonly="true"  name="question" path="question" value="question"/></p>--%>
            <br/>
        </div>
        <input type='submit' value='Submit' onclick="AjaxPostAnswer(event)"/>
    </form>



    <script type="text/javascript">

        function  disconnect() {
             $.ajax({
                type: "POST",
                dataType: "json",
                contentType : "application/json",
                url: "${pageContext.request.contextPath}/logout",
                //data: JSON.stringify(formdata),
                success: function(response)
                {
                    window.location.href="${pageContext.request.contextPath}/logout";
                }
            });
        }
        function  AjaxPostAnswer(e) {

            e.preventDefault();
            var Answers= document.getElementsByName('answer');
            var questionAsked=$('#questionAsked').val();
            var answer=null;
            var questionID=$('#questionID').val();


            for (var i = 0, length = Answers.length; i < length; i++)
            {
                if (Answers[i].checked)
                {
                    // do whatever you want with the checked radio
                     answer=(Answers[i].value);

                    // only one radio can be logically checked, don't check the rest
                    break;
                }
            }

            var ans={
                id:questionID,
                answer:answer,
                question:questionAsked,
                correctedAnswers :'0',
                a:'0',
                b:'0',
                c:'0',
                d:'0'
            }

            $.ajax({
                type: "POST",
                dataType: "text",
                contentType : "application/json",
                url: "${pageContext.request.contextPath}/checkAnswer",
                data: JSON.stringify(ans),
                success: function(response){
                  window.location.href="${pageContext.request.contextPath}/result";
                        /*       $('#section').show();
                               document.getElementById("beforeSubmitQuestion").style.display="none";
                               document.getElementById("test").innerHTML ="<span style='color: red'>You have only 2 min to answer this question, correct answer=10 score, incorrect answer=-2 score</span>"
                               startTimer();
                               var ques= localStorage.getItem('question');
                               ques=JSON.parse(ques);*/
                       //document.getElementById("beforeSubmitQuestion").style.visibility="visible";
                },
                error: function(data){
                    alert("some errors occured")
                    //get the status code

                },

            });


        }




        function connectByWebSocket() {
            /*e.preventDefault();*/
            /*var socket = new WebSocket('ws://localhost:8080/greeting');*/
            var socket = new SockJS('/greeting');
            ws = Stomp.over(socket);
            ws.connect({}, function (frame) {
                // setConnected(true);
                console.log('Connectd' + frame);
                /*ws.send("/app/message", {}, JSON.stringify({'name': $("#name").val()}));*/

                ws.subscribe("/user/topic/SubmitedQuestion", function (quizDB) {
                    if (quizDB != undefined || quizDB != null) {
                        $("#section").show();
                        document.getElementById("beforeSubmitQuestion").style.display="none";
                        startTimer();
                        document.getElementById("test").innerHTML =
                            "<span style='color: red' >" +
                            "You have only 1 min to answer this question, correct answer=10 score, incorrect answer=-2 score" +
                            "</span>";
                        localStorage.setItem('question',quizDB.body);

                        var question = JSON.parse(quizDB.body);
                        /*document.getElementsByName("question")[0].value = question.question;*/


                        $("#section").append(
                            "<p style='visibility: hidden'>" +
                            "<textarea rows='1' cols='1' readonly='true'  id='questionID' value='question'>" +question.id+"</textarea></p>"+
                            "<b></b>Question:</b>" +
                            "<p><textarea rows='10' cols='100' readonly='true'  id='questionAsked' value='question'>" +question.question+"</textarea></p>"+
                            "<p><b>A:</b>" + "<input type=radio name='answer' value='A'>" + question.a + "</p>" +
                            "<p><b>B:</b>" + "<input type=radio name='answer' value='B'>" + question.b + "</P>" +
                            "<p><b>C:</b>" + "<input type=radio name='answer' value='C'>" + question.c + "</p>" +
                            "<p><b>D:</b>" + "<input type=radio name='answer' value='D'>" + question.d + "</p>"+"</br>"
                        );
                    }

/*                    http://localhost:8080/startPlay?question=54654654654654645&answer=c*/

                        else
                        {
                            $("#section").hide();
                       }


                }), function (error) {
                    alert("STOMP error " + error);
                };


            })


        }

        /*http://localhost:8080/startPlay?question=54654654654654645&answer=c*/

        var timer=60;
        var min=0;
        var sec=0;
        function QuestionerSubmitedQuestion(){
            $('#section').hide();
            $.ajax({
                type: "GET",
                dataType: "json",
                dataType: "json",
                contentType : "application/json",
                url: "${pageContext.request.contextPath}/CheckQuestionerSubmited",
                //data: JSON.stringify(formdata),
                success: function(response){
                    if (response==true)
                    {
                        $('#section').show();
                        document.getElementById("beforeSubmitQuestion").style.display="none";
                        document.getElementById("test").innerHTML ="<span style='color: red'>You have only 2 min to answer this question, correct answer=10 score, incorrect answer=-2 score</span>"
                        startTimer();
                        var ques= localStorage.getItem('question');
                        ques=JSON.parse(ques);


                        if (ques!=null)
                        {
                            $("#section").append(
                                "<b></b>Question:</b>" +
                                "<p><textarea rows='10' cols='100' readonly='true'  id='questionSubmited' value='question'>" + ques.question + "</textarea></p>" +

                                "<p><b>A:</b>" + "<input type=radio name='answer' value='a'>" + ques.a + "</p>" +

                                "<p><b>B:</b>" + "<input type=radio name='answer' value='b'>" + ques.b + "</P>" +

                                "<p><b>C:</b>" + "<input type=radio name='answer' value='c'>" + ques.c + "</p>" +
                                "<p><b>D:</b>" + "<input type=radio name='answer' value='d'>" + ques.d + "</p>" + "</br>"
                            );
                        }


                        //document.getElementById("beforeSubmitQuestion").style.visibility="visible";

                    }
                    else {
                        connectByWebSocket();
                        $('#section').hide();
                    }
                },
                error: function(data){
                    alert("some errors occured")
                    //get the status code

                },

            });
        }


        function startTimer()
        {
            min=parseInt(timer/60);
            sec=parseInt(timer%60);
            document.getElementById("time").innerHTML = "<b>Time Left: </b>"+min.toString()+":"+sec.toString();
            timer--;
            var myVar=setTimeout(function()
            { startTimer(); }, 1000);
            if(timer<=0)
            {
                clearTimeout(myVar);
                document.getElementById("time").innerHTML = "<b>No Question Proveded for you , you back to home page </b>";
                window.location.href="${pageContext.request.contextPath}/changeQuestioner";
                window.location.href="${pageContext.request.contextPath}/loggedUsers";

            }

        }









    </script>

</body>
</div>

</html>