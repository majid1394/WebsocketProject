<%@page import="com.luv2code.springsecurity.demo.dao.Quiz.AdminDAOImp"%>
<%@page import="com.luv2code.springsecurity.demo.dao.Quiz.UserDAO"%>
<%@page import="org.json.simple.JSONObject"%>
<%
String email=(String)session.getAttribute("email");
if(email!=null){
    if(!email.equals("admin")){
        response.sendRedirect("adminLogin.jsp");
    }
}
else{
    response.sendRedirect("adminLogin.jsp");
}
%>


<html>
<head>
<title>Code Warrior</title>
    <link type="text/css"
          rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/css/quiz_style.css">


</head>


<body>


    <jsp:include page="../questionView/header.jsp"></jsp:include>
    <jsp:include page="../questionView/navigation.jsp"></jsp:include>
      
    <div id="section">
        <h2>Edit Question</h2>
     
      <%
        String set=request.getParameter("set");
        String id=request.getParameter("id");
        %>
    
        <p>Are sure you want to delete this question?</p><br/>
        <a href="deleteQuestionDAO.jsp?id=<%=id%>&set=<%=set%>">Yes</a> &nbsp; <a href="filterQuestions.jsp?set=<%=set%>">No</a>
    </div>
</body>

</html>