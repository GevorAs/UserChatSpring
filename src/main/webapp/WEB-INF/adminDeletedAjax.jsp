<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<h2 style="text-align: center">DELETED Users</h2>

<ul>
    <c:forEach items="${deleted}" var="deletedUser">


        <c:if test="${deletedUser.userStatus.toString()=='OFFLINE'}">
            <a href="/user?otherUserId=${deletedUser.id}">
                <li class="contact">
                    <div class="wrap">
                        <span class="contact-status busy"></span>
                        <img src="/getPic?filename=${deletedUser.picture}" style="float: left" >
                        <div class="meta">
                            <p class="name"
                               > ${deletedUser.name} ${deletedUser.surname}
                            <a href="/activateUser?deletedUserId=${deletedUser.id}" >
                                <input type="submit" value="Activate"
                                       style="background-color: #2c3e50;color: white;float:right">
                            </a>
                            </p>
                        </div>
                    </div>
                </li>
            </a>
        </c:if>


    </c:forEach>
</ul>