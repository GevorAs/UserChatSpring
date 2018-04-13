<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ page import="com.javaprogrammer.userchatspring.model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>

<head>
    <meta charset="UTF-8">
    <title>MGGM</title>

    <link rel="stylesheet" href="../front/userpage/css/style.css">
    <link rel="stylesheet" href="../front/userpage/css/style1.css">
    <script src="../front/jquery-3.3.1.min.js"></script>

</head>
<body>


<div class="main-container">

    <!-- HEADER -->
    <header class="block">
        <ul class="header-menu horizontal-list">

            <li>

                <a class="header-menu-tab" href="/admin"><span class="icon fontawesome-user scnd-font-color"></span>Home</a>
            </li>



            <li>
                <a class="header-menu-tab" href="/logout"><span
                        class="icon entypo-paper-plane scnd-font-color"></span>Logout</a>
            </li>
        </ul>
        <div class="profile-menu">
            <p>
                <a href="/admin">
                <span class="scnd-font-color">
                    <span class="profile-picture small-profile-picture">
                    <%--<img width="40px" alt="<%=user.getName()%>" src="/getResource?filename=<%=user.getPicture()%>">--%>
                    <img width="40px" alt="${user.name}" src="/getPic?filename=${user.picture}">
                    </span>
                </span>
                </a>
            </p>
        </div>
    </header>

    <!-- LEFT-CONTAINER -->
    <div class="left-container container">
        <div class="menu-box block"> <!-- MENU BOX (LEFT-CONTAINER) -->
            <h2 class="titular">MENU BOX</h2>
            <ul class="menu-box-menu">
                <li>
                    <a class="menu-box-tab" href="#" onclick="blockUser(${otherUser.id})"><span class="icon fontawesome-envelope scnd-font-color"></span>Block User
                    </a>
                </li>
                <li>
                    <a class="menu-box-tab" href="/removeUser?userId=${otherUser.id}"><span class="icon entypo-paper-plane scnd-font-color"></span>Remove User
                    </a>
                </li>

            </ul>
        </div>


        <iframe width="300" height="180"
                src="https://www.youtube.com/embed/nHWppy0lmpU?list=PLIU2NpAyk8O0-a5iVcSEo3hBp_kWF1fdg"
                frameborder="0" allow="autoplay; encrypted-media" allowfullscreen></iframe>


    </div>

    <!-- MIDDLE-CONTAINER -->
    <div class="middle-container container">
        <div class="profile block"> <!-- PROFILE (MIDDLE-CONTAINER) -->
            <%--<span onclick="sendRequest()"><a class="add-button" href="#"><span--%>
                    <%--class="icon entypo-plus scnd-font-color"></span></a></span>--%>
            <div class="profile-picture big-profile-picture clear">
                <img width="150px" alt="${otherUser.name}"
                     src="/getPic?filename=${otherUser.picture}">
            </div>
            <h1 class="user-name">${otherUser.name} ${otherUser.surname}
            </h1>

            <ul class="profile-options horizontal-list">
                <li><a class="views" href="#41"><p><span class="icon fontawesome-eye-open scnd-font-color"></span>${userVisit}
                </p></a></li>
                <li><a class="likes" href="#42"><p><span class="icon fontawesome-heart-empty scnd-font-color"></span>${rating}
                </p></a></li>
            </ul>
        </div>

        <div class="tweets block" style="overflow: scroll ;height: 500px" > <!-- TWEETS (MIDDLE-CONTAINER) -->
            <h2 class="titular"><span class="icon zocial-twitter"></span>POSTS</h2>


            <c:forEach items="${allPostOtherUser}" var="post">
                <div class="tweet first">
                    <p style="overflow: hidden; text-overflow: ellipsis; white-space: nowrap;">${post.text}
                    </p>
                    <p style="text-align: center"><a class="tweet-link" href="/seePost?postId=${post.id}">See post</a></p>
                    <p style="text-align: center"><a class="time-ago scnd-font-color" href="#18">${post.createdDate}</a></p>
                </div>
            </c:forEach>
        </div>

    </div>

    <!-- RIGHT-CONTAINER -->
    <div class="right-container container">

        <div id="frame">
            <div id="sidepanel">

                <div id="contacts">

                    <ul>
                        <c:forEach items="${otherUsersFriends}" var="otherUser">

                            <c:if test="${otherUser.userStatus.toString()=='ONLINE'}">
                                <a href="/user?otherUserId=${otherUser.id}">
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


                </div>
            </div>

        </div>

    </div> <!-- end right-container -->
</div> <!-- end main-container -->

<%--<script--%>
<%--src="http://code.jquery.com/jquery-3.3.1.min.js"--%>
<%--integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="--%>
<%--crossorigin="anonymous">--%>

<%--</script>--%>
<%--<script src='https://code.jquery.com/jquery-2.2.4.min.js'></script>--%>
<script src="../front/userpage/css/index.js"></script>
<script>

    function blockUser(id) {
        $.ajax({
            url: "/blockUser?userId="+id,
            success: function (result) {
                $("#").html(result);
            }
        })
    }



</script>

</body>

</html>

