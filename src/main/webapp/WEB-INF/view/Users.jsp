<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!------ Include the above in your HEAD tag ---------->


<!DOCTYPE html><html class=''>
<head>
        <link type="text/css"
              rel="stylesheet"
              href="${pageContext.request.contextPath}/resources/css/style.css">

        <link type="text/css"
              rel="stylesheet"
              href="${pageContext.request.contextPath}/resources/css/add-customer-style.css">

    <script src="${pageContext.request.contextPath}/resources/js/JQuery.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/bootsrap.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/stopm.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/sockjs.js"></script>



    <meta charset='UTF-8'><meta name="robots" content="noindex">

    <script>try{Typekit.load({ async: true });}catch(e){}</script>
    <style class="cp-pen-styles">body {
        display: flex;
        align-items: center;
        justify-content: center;
        min-height: 100vh;
        background: #2749ae;
        font-family: "proxima-nova", "Source Sans Pro", sans-serif;
        font-size: 1em;
        letter-spacing: 0.1px;
        color: #32465a;
        text-rendering: optimizeLegibility;
        text-shadow: 1px 1px 1px rgba(0, 0, 0, 0.004);
        -webkit-font-smoothing: antialiased;
    }
    .btn{
       /* color: #fff;*/
        background-color: #449d44;
        border-color: #255625;
    }

    #frame {
        width: 95%;
        min-width: 360px;
        max-width: 1000px;
        height: 92vh;
        min-height: 300px;
        max-height: 720px;
        background: #E6EAEA;
    }
    @media screen and (max-width: 360px) {
        #frame {
            width: 100%;
            height: 100vh;
        }
    }
    #frame #sidepanel {
        float: left;
        min-width: 280px;
        max-width: 340px;
        width: 30%;
        height: 90%;
        background: #2c3e50;
        color: #f5f5f5;
        overflow: hidden;
        position: relative;
    }
    @media screen and (max-width: 735px) {
        #frame #sidepanel {
            width: 58px;
            min-width: 58px;
        }
    }
    #frame #sidepanel #profile {
        width: 80%;
        margin: 25px auto;
    }
    @media screen and (max-width: 735px) {
        #frame #sidepanel #profile {
            width: 100%;
            margin: 0 auto;
            padding: 5px 0 0 0;
            background: #32465a;
        }
    }



    #frame #sidepanel #bottom-bar {
        position: absolute;
        width: 100%;
        bottom: 0;
    }
    #frame #sidepanel #bottom-bar button {
        float: left;
        border: none;
        width: 50%;
        padding: 10px 0;
        background: #32465a;
        color: #f5f5f5;
        cursor: pointer;
        font-size: 0.85em;
        font-family: "proxima-nova",  "Source Sans Pro", sans-serif;
    }
    @media screen and (max-width: 735px) {
        #frame #sidepanel #bottom-bar button {
            float: none;
            width: 100%;
            padding: 15px 0;
        }
    }

    #frame #sidepanel #bottom-bar button:hover {
        background: #435f7a;
    }
    #frame #sidepanel #bottom-bar button i {
        margin-right: 3px;
        font-size: 1em;
    }
    @media screen and (max-width: 735px) {
        #frame #sidepanel #bottom-bar button i {
            font-size: 1.3em;
        }
    }
    @media screen and (max-width: 735px) {
        #frame #sidepanel #bottom-bar button span {
            display: none;
        }
    }
    #frame .content {
        float: right;
        width: 60%;
        height: 100%;
        overflow: hidden;
        position: relative;
    }
    @media screen and (max-width: 735px) {
        #frame .content {
            width: calc(100% - 58px);
            min-width: 300px !important;
        }
    }
    @media screen and (min-width: 900px) {
        #frame .content {
            width: calc(100% - 340px);
        }
    }
    #frame .content .contact-profile {
        width: 100%;
        height: 60px;
        line-height: 60px;
        background: #f5f5f5;
    }
    #frame .content .contact-profile img {
        width: 40px;
        border-radius: 50%;
        float: left;
        margin: 9px 12px 0 9px;
    }
    #frame .content .contact-profile p {
        float: left;
    }
    #frame .content .contact-profile .social-media {
        float: right;
    }
    #frame .content .contact-profile .social-media i {
        margin-left: 14px;
        cursor: pointer;
    }
    #frame .content .contact-profile .social-media i:nth-last-child(1) {
        margin-right: 20px;
    }
    #frame .content .contact-profile .social-media i:hover {
        color: #435f7a;
    }
    #frame .content .messages {
        height: auto;
        min-height: calc(80% - 93px);
        max-height: calc(100% - 93px);

        overflow-x: hidden;
    }
    @media screen and (max-width: 735px) {
        #frame .content .messages {
            max-height: calc(100% - 105px);
        }
    }


    #frame .content .message-input {
        position: absolute;
        bottom: 0;
        width: 100%;
        z-index: 99;
    }
    #frame .content .message-input .wrap {
        position: relative;
    }
    #frame .content .message-input .wrap input {
        font-family: "proxima-nova",  "Source Sans Pro", sans-serif;
        float: left;
        border: none;
        width: calc(100% - 90px);
        padding: 11px 32px 10px 8px;
        font-size: 0.8em;
        color: #32465a;
    }
    @media screen and (max-width: 735px) {
        #frame .content .message-input .wrap input {
            padding: 15px 32px 16px 8px;
        }
    }
    #frame .content .message-input .wrap input:focus {
        outline: none;
    }
    #frame .content .message-input .wrap .attachment {
        position: absolute;
        right: 60px;
        z-index: 4;
        margin-top: 10px;
        font-size: 1.1em;
        color: #435f7a;
        opacity: .5;
        cursor: pointer;
    }
    @media screen and (max-width: 735px) {
        #frame .content .message-input .wrap .attachment {
            margin-top: 17px;
            right: 65px;
        }
    }
    #frame .content .message-input .wrap .attachment:hover {
        opacity: 1;
    }
    #frame .content .message-input .wrap button {
        float: right;
        border: none;
        width: 50px;
        padding: 12px 0;
        cursor: pointer;
        background: #32465a;
        color: #f5f5f5;
    }
    @media screen and (max-width: 735px) {
        #frame .content .message-input .wrap button {
            padding: 16px 0;
        }
    }
    #frame .content .message-input .wrap button:hover {
        background: #435f7a;
    }
    #frame .content .message-input .wrap button:focus {
        outline: none;
    }
    </style></head><body onload=connectByWebSocket(event)>

<!--

A concept for a chat interface.

Try writing a new message! :)


Follow me here:
Twitter: https://twitter.com/thatguyemil
Codepen: https://codepen.io/emilcarlsson/
Website: http://emilcarlsson.se/

-->
<div id="frame">
    <form:form action="${pageContext.request.contextPath}/notifyToClients" method="GET"  >
        <input type="submit" value="Logout"/>
    </form:form>
    <div id="sidepanel">
        <div id="profile">

                <p>Online Users</p>

        </div>
        <div id="search">

            <input type="text" placeholder="Search user online..." />
        </div>
        <div id="contacts">
            <ul>
                <li id="conversation">
                </li>
            </ul>
        </div>
        <div id="bottom-bar">
        </div>
    </div>




    <div class="content">







        <div class="messages">

            <img src="${pageContext.request.contextPath}/resources/Img/index.jpg" class="resize" alt="" />


        </div>
        <form:form action="${pageContext.request.contextPath}/startPlay" method="GET">
            <input type="submit" value="start play" id="startPlay" class="btn"/>
        </form:form>


    </div>
</div>

<script src='//production-assets.codepen.io/assets/common/stopExecutionOnTimeout-b2a7b3fe212eaa732349046d8416e00a9dec26eb7fd347590fbced3ab38af52e.js'></script><script src='https://code.jquery.com/jquery-2.2.4.min.js'></script>






</body>
<div>
    <%--onsubmit="return disconnectUser()"--%>

</div>


</html>



<script type="text/javascript">
   {

       function showGreeting(message) {
           $("#greetings").append("<tr><td>" + message + "</td></tr>");
       }

       function clearBox(elementID) {
           document.getElementById(elementID).innerHTML = "";
       }

       function disconnectUser(e) {
           e.preventDefault();
           ws.send("/app/logout", {}, JSON.stringify({'name': $("#name").val()}));
           return true;
       }


       function connectByWebSocket(e) {
           e.preventDefault();
           /*var socket = new WebSocket('ws://localhost:8080/greeting');*/
           var socket = new SockJS('/greeting');
           ws = Stomp.over(socket);
           ws.connect({}, function (frame) {
               // setConnected(true);
               console.log('Connectd' + frame);
               ws.send("/app/message", {}, JSON.stringify({'name': $("#name").val()}));


               ws.subscribe("/user/topic/activeUsers", function (activeUsers) {
                   if (activeUsers != undefined || activeUsers != null) {
                       if (JSON.parse(activeUsers.body).length <2)
                       $("#startPlay").prop("disabled",true)
                   else
                       $("#startPlay").prop("disabled",false);

                       //  setConnected(true)
                       document.getElementById('conversation').innerHTML = "";
                       $("#conversation").clear;
                       (JSON.parse(activeUsers.body)).forEach(function (item) {
                           //$("#conversation").append("<tr><td class=\"dot\"> " + item + "</td></tr>");
                           $("#conversation").append(
                               "<li >" +
                               "<span class='contact-status online'></span>"+
                               "<div class='meta'></div>"+
                               "<p class='name' >" + item +
                               "<span class='dot' >" + "</span>"+
                               "</p>"+"</li>");

                           /*           $("#conversation").append("<tr><td>"+"</td></tr>");*/

                       })
                   }
               }), function (error) {
                   alert("STOMP error " + error);
               };








           })


       }

   }

</script>



