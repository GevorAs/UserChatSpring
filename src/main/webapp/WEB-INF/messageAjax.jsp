<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="contact-profile">
    <img src="/getPic?filename=${friendIdForMessage.picture}"/>
    <p>${friendIdForMessage.name} ${friendIdForMessage.surname}</p>

</div>
<div class="messages">
    <ul>


        <c:forEach items="${chat}" var="message">

            <c:if test="${message.fromId == user.id}">
                <li class="sent">
                    <img src="/getPic?filename=${message.picture}" width="80" alt="img" style="width: 40px;float: top">
                    <img src="/getPic?filename=${user.picture}" alt=""/>

                    <p>${message.text}</p>
                    <a href="/getFile?filename=${message.file}"
                       style="text-decoration: none">${ message.file.replaceAll("[0-9/_]","") }</a>
                        <%--<a href="/getPic?filename=${message.file}" style="text-decoration: none">${ message.picture.replaceAll("[0-9/_]","") }</a>--%>

                </li>
            </c:if>

            <c:if test="${message.fromId == friendIdForMessage.id}">
                <li class="replies">
                    <img src="/getPic?filename=${message.picture}" width="80" alt="img" style="width: 40px;float: top">
                    <img src="/getPic?filename=${friendIdForMessage.picture}" alt=""/>
                    <p>${message.text}</p>
                    <a href="/getFile?filename=${ message.file}"
                       style="text-decoration: none">${ message.file.replaceAll("[0-9/_]","") }</a>

                </li>
            </c:if>
        </c:forEach>
    </ul>
</div>