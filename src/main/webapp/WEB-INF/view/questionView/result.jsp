<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--<%@page import="com.UserDAO"%>--%>
<%@page import="com.luv2code.springsecurity.demo.dao.Quiz.AdminDAOImp"%>


<html>
<head>
<title>Code Warrior</title>


    <script src="${pageContext.request.contextPath}/resources/js/JQuery.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/bootsrap.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/stopm.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/sockjs.js"></script>

</head>

<body onload="connectByWebSocket()">

    <script type="text/javascript">
        function  startPlayAgain() {
            $.ajax({
                type: "POST",
                dataType: "text",
                contentType : "application/json",
                url: "${pageContext.request.contextPath}/playAgain",
                success: function(response){
                    window.location.href="${pageContext.request.contextPath}/loggedUsers";
                },
                error: function(data){
                    alert("some erros occured");
                    //get the status code
                },
            });
            window.location.href="${pageContext.request.contextPath}/playAgain";
        }

        window.onbeforeunload = function() { return "Warning: Your work will be lost!"; };
        function connectByWebSocket() {
            /*e.preventDefault();*/
            /*var socket = new WebSocket('ws://localhost:8080/greeting');*/
            var socket = new SockJS('/greeting');
            ws = Stomp.over(socket);
            ws.connect({}, function (frame) {
                // setConnected(true);
                console.log('Connectd' + frame);
                //ws.send("/app/message", {}, JSON.stringify({'name': $("#name").val()}));
                ws.send("/app/requestResult", {});


                ws.subscribe("/user/topic/AllUserFinishTEST", function (response) {
                    alert("All Of the users passed test");
                    $("#finishTest").append(
                    )
                })



                ws.subscribe("/user/topic/testResult", function (user) {
                    var userAndScore=(JSON.parse(user.body))
                    var p=Object.entries(userAndScore);
                    $("#score").append(
                        "<ul>"+
                        "<li >" +
                        "<div class='meta'></div>" +
                        "<p class='name' >" + "The Last Score Of "+p[0][0]+" "+":="+"................ "+p[0][1]+
                        "<span class='dot' >" + "</span>" +
                        "</p>" + "</li><ul>");
                })
            })
        }
    </script>

    <jsp:include page="header.jsp"></jsp:include>
    <input type='submit' value='start play again' class='btn' onclick="startPlayAgain()">
        
    <div id="section">
    <h2>Test Finished:</h2>
        <div id="finishTest"></div>
    <ul>
      <li>Your result is saved.</li>
      <li>Please wait while others finish their test.</li>
      <li>Please don't press back, close or reload this window.</li>
    </ul>  
    
    <br/>
    <h2>Happy Coding!! :) :)</h2>
    </div>
<div id="score"></div>



    
</body>

</html>