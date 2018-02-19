<%@ page import="com.javaprogrammer.userchatspring.model.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<% User user = (User) request.getSession().getAttribute("user");%>--%>

<div class="contact-profile">
    <img src="/getResource?filename=${friend.picture}" alt="${friend.name} ${friend.surname}"/>
    <p>${friend.name} ${friend.surname}</p>

</div>
<div class="messages">
    <ul>


        <c:forEach items="${chat}" var="message">

            <c:if test="${message.fromId == newUser.id}">
                <li class="sent">
                    <img src="/getResource?filename=${newUser.picture}" alt=""/>
                    <p>${message.text}</p>
                </li>
            </c:if>

            <c:if test="${message.fromId == friend.id}">
                <li class="replies">
                    <img src="/getResource?filename=${friend.picture}" alt=""/>
                    <p>${message.text}</p>
                </li>
            </c:if>
        </c:forEach>
    </ul>
</div>