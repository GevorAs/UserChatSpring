<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<spring:form modelAttribute="friendRequests">
    <ul>
        <c:forEach items="${friendRequests}" var="otherUser">

            <c:if test="${otherUser.userStatus.toString()=='ONLINE'}">

                <li class="contact" >
                    <div class="wrap">

                        <div class="meta">
                            <a href="/user?otherUserId=${otherUser.id}">
                                <span class="contact-status online"></span>
                                <img src="/getPic?filename=${otherUser.picture}">
                                <p class="name"> ${otherUser.name} ${otherUser.surname}</p>
                            </a>
                            <a href="#" style="float: left" class="action-button shadow animate blue"
                               onclick="acceptRequest(${otherUser.id})">Accept</a>
                            <a href="#" style="float: right" class="action-button shadow animate blue"
                               onclick="rejectRequest(${otherUser.id})"> Reject</a>
                        </div>
                    </div>
                </li>

            </c:if>


            <c:if test="${otherUser.userStatus.toString()=='OFFLINE'}">

                <li class="contact">
                    <div class="wrap">
                        <div class="meta">
                            <a href="/user?otherUserId=${otherUser.id}">
                                <span class="contact-status busy"></span>
                                <img src="/getPic?filename=${otherUser.picture}">
                                <p class="name"> ${otherUser.name} ${otherUser.surname}</p>
                            </a>
                            <a href="#" style="float: left" class="action-button shadow animate blue"
                               onclick="acceptRequest(${otherUser.id})">Accept</a>
                            <a href="#" style="float: right" class="action-button shadow animate blue"
                               onclick="rejectRequest(${otherUser.id})"> Reject</a>
                        </div>
                    </div>
                </li>

            </c:if>


        </c:forEach>
    </ul>
</spring:form>

<script>
    function acceptRequest(id) {

        jQuery.ajax({
            url: "/acceptRequest?friendId=" + id,
            success: function (result) {
                $("#contacts").html(result);
            }
        });


    }

    function rejectRequest(id) {
        getRequests();
        jQuery.ajax({
            url: "/rejectRequest?friendId=" + id,
            success: function (result) {
                $("#contacts").html(result);
            }
        });


    }





</script>



