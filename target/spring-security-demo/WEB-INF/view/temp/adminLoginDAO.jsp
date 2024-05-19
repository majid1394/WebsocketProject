<%@page import="com.luv2code.springsecurity.demo.dao.Quiz.AdminDAOImp"%>
<%
String email=request.getParameter("email");
String password=request.getParameter("password");

String result= AdminDAOImp.login(email, password);

if(result.equals("true")){
	session.setAttribute("email", "admin");
	response.sendRedirect("adminHome.jsp");
}
else{
	response.sendRedirect("adminLogin.jsp");
}
%>