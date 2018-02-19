<%@ page import="com.javaprogrammer.userchatspring.model.ActiveStatus" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<spring:form modelAttribute="search">
    <ul>
        <c:forEach items="${search}" var="user">

            <c:if test="${user.userStatus.toString()=='ONLINE'}">
                <a href="/user?otherUserId=${user.id}" >
                    <li class="contact">
                        <div class="wrap">
                            <span class="contact-status online"></span>
                            <img src="/getResource?filename=${user.picture}">
                            <div class="meta">
                                <p class="name"> ${user.name} ${user.surname}</p>
                            </div>
                        </div>
                    </li>
                </a>
            </c:if>



            <c:if test="${user.userStatus.toString()=='OFFLINE'}">
                <a href="/user?otherUserId=${user.id}">
                    <li class="contact">
                        <div class="wrap">
                            <span class="contact-status busy"></span>
                            <img src="/getResource?filename=${user.picture}">
                            <div class="meta">
                                <p class="name"> ${user.name} ${user.surname}</p>
                            </div>
                        </div>
                    </li>
                </a>
            </c:if>


        </c:forEach>
    </ul>
</spring:form>





