<%@ page import="com.javaprogrammer.userchatspring.model.ActiveStatus" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<spring:form modelAttribute="search">
    <ul>
        <c:forEach items="${search}" var="otherUser">

            <c:if test="${otherUser.userStatus.toString()=='ONLINE'}">
                <a href="/user?otherUserId=${otherUser.id}" >
                    <li class="contact">
                        <div class="wrap">
                            <span class="contact-status online"></span>
                            <img src="/getPic?filename=${otherUser.picture}">
                            <div class="meta">
                                <p class="name"> ${otherUser.name} ${otherUser.surname}</p>
                            </div>
                        </div>
                    </li>
                </a>
            </c:if>



            <c:if test="${otherUser.userStatus.toString()=='OFFLINE'}">
                <a href="/user?otherUserId=${otherUser.id}">
                    <li class="contact">
                        <div class="wrap">
                            <span class="contact-status busy"></span>
                            <img src="/getPic?filename=${otherUser.picture}">
                            <div class="meta">
                                <p class="name"> ${otherUser.name} ${otherUser.surname}</p>
                            </div>
                        </div>
                    </li>
                </a>
            </c:if>


        </c:forEach>
    </ul>
</spring:form>





