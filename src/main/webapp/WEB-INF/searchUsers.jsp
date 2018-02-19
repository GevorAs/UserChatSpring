<%@ page import="com.javaprogrammer.userchatspring.model.ActiveStatus" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div>

    <spring:form modelAttribute="search">
    <ul>
        <c:forEach items="${search}" var="user">

            <li><a href="/user?otherUser=${user.id}"><img src="/getResource?filename=${user.picture}">&nbsp; ${user.name}&nbsp;${user.surname}</a><a href="/sendRequest?toId=${user.id}"></a></li>

        </c:forEach>
    </ul>
    </spring:form>
</div>