<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="drop">
<ul class="drop_menu">

<%--<li><a href='adminHome.jsp'>Home</a></li>--%>
    <form:form action="${pageContext.request.contextPath}/loggedUsers"
               method="GET">
        <input type="submit" value="Home"
               style="color: #fff;
               background-color: #449d44;
                border-color: #255625;"/>
        <%--<li><a href='adminLogout.jsp'>Logout</a></li>--%>
    </form:form>

    <%--<li><a href='addQuestion.jsp'>Add</a></li>--%>
<%--    <form:form action="${pageContext.request.contextPath}/addQuestion"
               method="GET">
        <input type="submit" value="Add" />
        &lt;%&ndash;<li><a href='adminLogout.jsp'>Logout</a></li>&ndash;%&gt;
    </form:form>--%>
    <form:form action="${pageContext.request.contextPath}/adminResult"
               method="GET">
        <input type="submit" value="Result"
               style="color: #fff;
               background-color: #449d44;
                border-color: #255625;"/>

        <%--<li><a href='adminLogout.jsp'>Logout</a></li>--%>
    </form:form>

<%--<li><a href='adminResult.jsp'>Result</a></li>--%>
    <form:form action="${pageContext.request.contextPath}/logout"
               method="POST">
        <input type="submit" value="Logout"
               style="color: #fff;
               background-color: #449d44;
                border-color: #255625;"/>
        <%--<li><a href='adminLogout.jsp'>Logout</a></li>--%>
    </form:form>
</ul>
</div>




