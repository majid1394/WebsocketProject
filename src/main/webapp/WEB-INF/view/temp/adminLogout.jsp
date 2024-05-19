<%
  /*  <form:form action="${pageContext.request.contextPath}/logout"
    method="POST">
    <input type="submit" value="Logout" />
    </form:form>
session.invalidate();
response.sendRedirect("adminLogin.jsp");
*/
session.invalidate();
response.sendRedirect("${pageContext.request.contextPath}/logout");
%>