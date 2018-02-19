<%@ page import="com.javaprogrammer.userchatspring.model.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="contact-profile">
    <img src="/getResource?filename=${friendIdForMessage.picture}" alt="${friendIdForMessage.name} ${friendIdForMessage.surname}"/>
    <p>${friendIdForMessage.name} ${friendIdForMessage.surname}</p>

</div>
<div class="messages">
    <ul>


        <c:forEach items="${chat}" var="message">

            <c:if test="${message.fromId == user.id}">
                <li class="sent">
                    <img src="/getResource?filename=${user.picture}" alt=""/>
                    <p>${message.text}</p>
                </li>
            </c:if>

            <c:if test="${message.fromId == friendIdForMessage.id}">
                <li class="replies">
                    <img src="/getResource?filename=${friendIdForMessage.picture}" alt=""/>
                    <p>${message.text}</p>
                </li>
            </c:if>
        </c:forEach>
    </ul>
</div>