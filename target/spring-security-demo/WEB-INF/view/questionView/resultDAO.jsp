<%@page import="java.util.LinkedHashMap"%>
<%@page import="com.luv2code.springsecurity.demo.dao.UserDAOImpl"%>
<%
String id=UserDAOImpl.getUserID((String)session.getAttribute("email"));
LinkedHashMap lhm=(LinkedHashMap)session.getAttribute("all_answers");
    UserDAOImpl.setResult(id, lhm);
session.invalidate();
response.sendRedirect("result.jsp");
%>