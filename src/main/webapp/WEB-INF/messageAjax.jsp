<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


    <c:forEach items="${chat}" var="message">

        <c:if test="${message.fromId == user.id}">
            <ul>

                <li class="sent">

                    <c:if test="${message.picture!=null}">
                        <a href="/getPic?filename=${message.picture}" target="_blank"
                           style="text-decoration: none"> <img src="/getPic?filename=${message.picture}" width="80"
                                                               style="width: 40px;float: right">
                        </a>
                    </c:if>
                    <img src="/getPic?filename=${user.picture}" alt=""/>
                    <br>
                    <span>${message.timestamp}</span><br>
                    <p>${message.text}</p>
                    <a href="/getFile?filename=${message.file}"
                       style="text-decoration: none">${ message.file.replaceAll("[0-9/_]","") }</a>
                        <%--<a href="/getPic?filename=${message.file}" style="text-decoration: none">${ message.picture.replaceAll("[0-9/_]","") }</a>--%>
                </li>
            </ul>
        </c:if>

        <c:if test="${message.fromId == friendIdForMessage.id}">
            <ul>
                <li class="replies">
                    <c:if test="${message.picture!=null}"> <a href="/getPic?filename=${message.picture}" target="_blank"
                                                              style="text-decoration: none"> <img
                            src="/getPic?filename=${message.picture}" width="80" style="width: 40px;float: left">
                    </a>
                    </c:if>
                    <img src="/getPic?filename=${friendIdForMessage.picture}" alt=""/>
                    <br>
                    <span style="float: right">${message.timestamp}</span><br>
                    <p>${message.text}</p>
                    <a href="/getFile?filename=${ message.file}"
                       style="text-decoration: none">${ message.file.replaceAll("[0-9/_]","") }</a>

                </li>

            </ul>
        </c:if>
    </c:forEach>
