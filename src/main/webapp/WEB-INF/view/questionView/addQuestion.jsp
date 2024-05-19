<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%
String email=(String)session.getAttribute("email");
if(email!=null){
    /*if(!email.equals("admin")){
        response.sendRedirect("adminLogin.jsp");
    }*/
}
else{
   /* response.sendRedirect("adminLogin.jsp");*/
}
%>


<html>
<head>

    <script src="${pageContext.request.contextPath}/resources/js/JQuery.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/bootsrap.js"></script>
 <%--   <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>--%>
<title>Create Question</title>
<link rel="stylesheet" type="text/css" href="style.css">

</head>


<body onload=startTimer()>


    <jsp:include page="header.jsp"></jsp:include>
    <jsp:include page="navigation.jsp"></jsp:include>


    <div id="section">
        <h2>Add Question</h2>

        <form modelAttribute="quiz"  >
            <center>
                <span style="color: red">You have only 2 minutes time to submit your question</span>
                </br>
            <p id="time">Time: </p>
            </center>
            <table cellpadding="10" >
                <tr>
                    <td><b>Question:</b></td>
                    <td><textarea rows="10" cols="100"  id="question" name="question" path="question" required ></textarea ></td>
                </tr>
                <tr>
                    <td><b>A:</b></td>
                    <td><textarea rows="2" cols="50" id="a" name="a" path="a" required></textarea></td>
                </tr>

                <tr>
                    <td><b>B:</b></td>
                    <td><textarea rows="2" cols="50" name="b" id="b" path="b" required></textarea></td>
                </tr>

                <tr>
                    <td><b>C:</b></td>
                    <td><textarea rows="2" cols="50" name="c" id="c" path="c" required></textarea></td>
                </tr>

                <tr>
                    <td><b>D:</b></td>
                    <td><textarea rows="2" cols="50" name="d" id="d" path="d" required></textarea></td>
                </tr>
                
                <tr>
                <td><b>Answer:</b></td>
                <td>
                 <input type="radio" name="answer" path="answer" value="A" required> A &nbsp;
                <input type="radio" name="answer" path="answer" value="B"> B &nbsp;
                <input type="radio" name="answer" path="answer" value="C"> C &nbsp;
                <input type="radio" name="answer" path="answer"  value="D"> D &nbsp;
                </td>
                </tr>

                <%--<tr>
                <td><b>Set:</b></td>
                <td>  <input type="radio" name="set" path="correctedAnswers" value="1" required> 1 &nbsp;
                <input type="radio" name="set" path="correctedAnswers" value="2"> 2 &nbsp;
                <input type="radio" name="set" path="correctedAnswers" value="3"> 3
                </td>
                </tr>--%>

                <tr>
                    <td colspan="2" align="center">
                        <input type="submit" value="Add"  onclick="return AjaxPostData(event)"/>
                    </td>
                </tr>
            </table>
        </form>
    </div>




    <script type="text/javascript">
        {
            var timer=120;
            function AjaxPostData(e) {
                e.preventDefault();
                var formdata=
                 {
                    correctedAnswers : $('input[name=set]:checked').val(),
                    answer : $('input[name=answer]:checked').val(),
                     a : document.getElementById('a').value,
                     b : document.getElementById('b').value,
                     c:  document.getElementById('c').value,
                     d : document.getElementById('d').value,
                    question : document.getElementById('question').value
                };

                $.ajax({
                    type: "POST",
                    dataType: "json",
                    contentType : "application/json",
                    url: "${pageContext.request.contextPath}/addQuiz",
                    data: JSON.stringify(formdata),
                    success: function(response){
                        if (response==true) {
                            alert("your question successfully added");
                                document.getElementById('question').value='';
                                document.getElementById('a').value='';
                                document.getElementById('b').value='';
                                document.getElementById('c').value='';
                                document.getElementById('d').value='';

                            $('input[name=set]').prop('checked',false);
                            $('input[name=answer]').prop('checked',false);
                            window.location.href="${pageContext.request.contextPath}/result";

                        }
                    },
                    error: function(data){
                        alert("some errors occured");
                        //get the status code

                    },


                });

            }
            function startTimer(){
                min=parseInt(timer/60);
                sec=parseInt(timer%60);
                document.getElementById("time").innerHTML = "<b>Time Left: </b>"+min.toString()+":"+sec.toString();
                timer--;
                var myVar=setTimeout(function()
                { startTimer(); }, 1000);
                if(timer<=0){
                    clearTimeout(myVar);

                    //window.location="resultDAO.jsp";
                    $.ajax({
                        type: "GET",
                        dataType: "html",
                        contentType : "text/html",
                        url: "${pageContext.request.contextPath}/changeQuestioner",
                        //data: JSON.stringify(formdata),
                        success: function(){
                            window.location.href="${pageContext.request.contextPath}/loggedUsers";
                        },
                        error: function(data){
                            var p=1;
                           /* alert("some errors occured");*/
                            //get the status code

                        },


                    });

                }

            }
        }



    </script>


</body>

</html>