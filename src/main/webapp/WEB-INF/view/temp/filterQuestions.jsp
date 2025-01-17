<%@page import="org.json.simple.JSONObject"%>
<%@page import="com.luv2code.springsecurity.demo.dao.Quiz.AdminDAOImp"%>
<%@page import="org.json.simple.JSONArray"%>
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
        <br/><br/>
            
        <%
        String set=request.getParameter("set");
        JSONArray array= AdminDAOImp.getQuestions(set);
        JSONObject obj;
        
        String status=request.getParameter("status");
        
        if(status!=null){
            if(status.equals("false")){%>
                <p style="color:red">Some error occurred!</p>
            <%}
            else if(status.equals("true")){%>
                <p style="color:red">Record deleted!</p>
            <%}
        }
        
        for(int i=0;i<array.size();++i){
            obj=(JSONObject)array.get(i);
        
        %>    
          <table cellpadding="10">
                <tr>
                    <td>
                    <b><%=i+1+".&nbsp;"%></b><%=obj.get("question")%>
                    </td>
                </tr>
                
                <tr>
                    <td>
                    <b>A:&nbsp;</b><%=obj.get("a") %>
                    </td>
                </tr>
                
                <tr>
                    <td>
                    <b>B:&nbsp;</b><%=obj.get("b") %>
                    </td>
                </tr>
                
                <tr>
                    <td>
                    <b>C:&nbsp;</b><%=obj.get("c") %>
                    </td>
                </tr>

                <tr>
                    <td>
                    <b>D:&nbsp;</b><%=obj.get("d") %>
                    </td>
                </tr>
                
                <tr>
                    <td>
                    <b>Answer:&nbsp;</b><%=obj.get("answer") %>
                    </td>
                </tr>
                
                <tr>
                    <td>
                    <b><a href="deleteQuestion.jsp?id=<%=obj.get("