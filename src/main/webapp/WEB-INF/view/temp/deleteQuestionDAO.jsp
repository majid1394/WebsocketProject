<%@page import="com.luv2code.springsecurity.demo.dao.Quiz.AdminDAOImp"%>
<%
String id=request.getParameter("id");
String set=request.getParameter("set");
String result= AdminDAOImp.deleteQuestion(id);

if(result.equals("true")){
	response.sendRedirect("filterQuestions.jsp?set="+set+"&status=true");
}
else{
	response.sendRedirect("filterQuestions.jsp?set="+set+"&status=false");
}
%>