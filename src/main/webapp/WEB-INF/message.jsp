<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>MGGM</title>
    <link href='https://fonts.googleapis.com/css?family=Source+Sans+Pro:400,600,700,300' rel='stylesheet'
          type='text/css'>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css">
    <link rel='stylesheet prefetch'
          href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.6.2/css/font-awesome.min.css'>
    <link rel="stylesheet" href="../front/chat/css/style.css">

    <script src="../front/jquery-3.3.1.min.js"></script>
</head>
<body>
<div id="frame">

    <div id="sidepanel">
        <div id="profile">
            <div class="wrap">
                <img id="profile-img" src="/getPic?filename=${user.picture}" class="online" alt=""/>
                <%--<img src="/getPic?filename=${user.picture}">--%>
                <p> ${user.name} ${user.surname}</p>
                <%--<i class="fa fa-chevron-down expand-button" aria-hidden="true"></i>--%>
                <%--<div id="status-options">--%>
                <%--<ul>--%>
                <%--<li id="status-online" class="active"><span class="status-circle"></span>--%>
                <%--<p>Online</p></li>--%>
                <%--<li id="status-away"><span class="status-circle"></span>--%>
                <%--<p>Away</p></li>--%>
                <%--<li id="status-busy"><span class="status-circle"></span>--%>
                <%--<p>Busy</p></li>--%>
                <%--<li id="status-offline"><span class="status-circle"></span>--%>
                <%--<p>Offline</p></li>--%>
                <%--</ul>--%>
                <%--</div>--%>
                <%--<div id="expanded">--%>
                <%--<label for="twitter"><i class="fa fa-facebook fa-fw" aria-hidden="true"></i></label>--%>
                <%--<input name="twitter" type="text" value="mikeross"/>--%>
                <%--<label for="twitter"><i class="fa fa-twitter fa-fw" aria-hidden="true"></i></label>--%>
                <%--<input name="twitter" type="text" value="ross81"/>--%>
                <%--<label for="twitter"><i class="fa fa-instagram fa-fw" aria-hidden="true"></i></label>--%>
                <%--<input name="twitter" type="text" value="mike.ross"/>--%>
                <%--</div>--%>
            </div>
        </div>
        <div id="search">
            <label for="555"><i class="fa fa-search" aria-hidden="true"></i></label>
            <input type="text" id="555" placeholder="Search contacts..."/>
        </div>
        <div id="contacts">
            <ul>
                <c:forEach items="${userFriends}" var="userfriend">

                    <c:if test="${userfriend.userStatus.toString()=='ONLINE'}">

                        <li class="contact" onclick="getFriendProfile(${userfriend.id});getMessages(${userfriend.id})">
                            <div class="wrap">
                                <span class="contact-status online"></span>
                                <img src="/getPic?filename=${userfriend.picture}">
                                <div class="meta">
                                    <p class="name"> ${userfriend.name} ${userfriend.surname}</p>
                                </div>
                            </div>
                        </li>

                    </c:if>


                    <c:if test="${userfriend.userStatus.toString()=='OFFLINE'}">

                        <li class="contact" onclick="getFriendProfile(${userfriend.id});getMessages(${userfriend.id})">
                            <div class="wrap">
                                <span class="contact-status busy"></span>
                                <img src="/getPic?filename=${userfriend.picture}">
                                <div class="meta">
                                    <p class="name"> ${userfriend.name} ${userfriend.surname}</p>
                                </div>
                            </div>
                        </li>

                    </c:if>


                </c:forEach>

            </ul>
        </div>
        <div id="bottom-bar">
            <a href="/userPage">
                <button id="addcontact"><i class="fa fa-cog fa-fw" aria-hidden="true"></i> <span>HOME</span>
                </button>
            </a>
            <a href="/logout">
                <button id="settings"><i class="fa fa-cog fa-fw" aria-hidden="true"></i> <span>LOGOUT</span></button>
            </a>
        </div>
    </div>
    <div class="content">
        <div class="contact-profile">

            <%--------------------------AJAX_FRIEND_PROFILE_MESSAGES----------------------------%>

        </div>
        <div class="messages" id="concatProfile">

            <%-----------------------------AJAX_MESSAGES----------------------------%>

        </div>

        <div class="message-input" id="222">
            <div class="wrap">
                <spring:form action="/sendMessage" method="post" modelAttribute="emptyMessage" id="1111"
                             enctype="multipart/form-data">
                <spring:input type="text" id="8888" path="text" placeholder="Write your message..." name="text"/>
                <spring:input path="fromId" value="${user.id}" type="hidden"/>
                    <%--<i class="fa fa-paperclip attachment" aria-hidden="true">--%>


                    <%--</i>--%>
                <button type="submit"><i class="fa fa-paper-plane" aria-hidden="true"></i></button>

            </div>
            <input type="file" name="messageFile" title="file" style="float: left;margin-left: 80px">
            <input type="file" name="messagePic" title="pic">
            </spring:form>
        </div>

    </div>
</div>
<%--<script src='https://code.jquery.com/jquery-2.2.4.min.js'></script>--%>


<script src="../front/chat/js/index.js"></script>
<script>
    var x;
    var currentFriendId;
    var id = ${friendIdForMessage.id};
    if (${friendIdForMessage.id!=""}) {

        getMessages(id);
        // getFriendProfile(id.id);

    }

    //   onclick   see messages the friend
    function getMessages(id) {
        currentFriendId = id;


        if (x) {
            clearInterval(x);
            inter(id);
            x = setInterval(function () {
                inter(id)
            }, 1000);
        }
        else {
            inter(id);
            x = setInterval(function () {
                inter(id)
            }, 1000);
        }
        getFriendProfile(id);
    }

    //contact-profile in message
    function getFriendProfile(id) {
        $.ajax({
            url: "getFriendProfileMessage?id2=" + id,
            success: function (result) {
                $(".contact-profile").html(result);
            }
        })

    }

    //   see messages the friend
    function inter(id) {
        $.ajax({
            url: "/getMessages?id=" + id,
            success: function (result) {
                $("#concatProfile").html(result);
            }
        })
    }


    //send message Ajax
    $("#1111").submit(function (e) {
        e.preventDefault();  // <--------stops the form submission
        var fd = new FormData($("#1111")[0]);
        // fd.append('groupName', 'xxx');
        $.ajax({
            url: "/sendMessage",
            type: "POST",
            data: fd,
            enctype: 'multipart/form-data',
            processData: false,
            contentType: false,
            cache: false,
            success: function (result) {
                $("#concatProfile").html(result);
            }
        });
        document.getElementById("8888").value = "";
    });


</script>

</body>

</html>
