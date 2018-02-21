<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>

<head>
    <meta charset="UTF-8">
    <title>Home</title>

    <link rel="stylesheet" href="../front/userpage/css/style.css">
    <link rel="stylesheet" href="../front/userpage/css/style1.css">
    <link rel="stylesheet" href="../front/button/css/style2.css">

</head>
<body>


<div class="main-container">

    <!-- HEADER -->
    <header class="block">
        <ul class="header-menu horizontal-list">

            <li>
                <a class="header-menu-tab" href="/userPage"><span class="icon fontawesome-user scnd-font-color"></span>Home</a>
            </li>


            <li>
                <a class="header-menu-tab" href="#1"><span class="icon entypo-cog scnd-font-color"></span>Settings</a>
            </li>

            <li>
                <a class="header-menu-tab" href="/messages"><span
                        class="icon fontawesome-envelope scnd-font-color"></span>Messages</a>
                <a class="header-menu-number" href="#4">${newMessage}</a>
            </li>

            <li>
                <a class="header-menu-tab" href="#" onclick="getRequests()"><span
                        class="icon fontawesome-star-empty scnd-font-color"></span>Request</a>
                <a class="header-menu-number" href="#4">${newRequest}</a>
            </li>
            <%--<li>--%>
            <%--<a class="header-menu-tab" href="#5"><span class="icon fontawesome-star-empty scnd-font-color"></span>Favorites</a>--%>
            <%--</li>--%>
            <li>
                <a class="header-menu-tab" href="/logout"><span
                        class="icon entypo-paper-plane scnd-font-color"></span>Logout</a>
            </li>
        </ul>
        <div class="profile-menu">
            <p>
                <a href="/userPage">
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
                    <a class="menu-box-tab" href="#6"><span class="icon fontawesome-envelope scnd-font-color"></span>Messages
                        <div class="menu-box-number">24</div>
                    </a>
                </li>
                <li>
                    <a class="menu-box-tab" href="#8"><span class="icon entypo-paper-plane scnd-font-color"></span>Invites
                        <div class="menu-box-number">3</div>
                    </a>
                </li>
                <li>
                    <a class="menu-box-tab" href="#10"><span class="icon entypo-calendar scnd-font-color"></span>Events
                        <div class="menu-box-number">5</div>
                    </a>
                </li>
                <li>
                    <a class="menu-box-tab" href="#12"><span class="icon entypo-cog scnd-font-color"></span>Account
                        Settings</a>
                </li>
                <li>
                    <a class="menu-box-tab" href="#13">
                        <sapn class="icon entypo-chart-line scnd-font-color"></sapn>
                        Statistics</a>
                </li>
            </ul>
        </div>

        <div class="media block"> <!-- MEDIA (LEFT-CONTAINER) -->
            <div id="media-display">
                <a class="media-btn play" href="#23"><span class="fontawesome-play"></span></a>
            </div>
            <div class="media-control-bar">
                <a class="media-btn play" href="#23"><span class="fontawesome-play scnd-font-color"></span></a>
                <p class="time-passed">4:15 <span class="time-duration scnd-font-color">/ 9:23</span></p>
                <a class="media-btn volume" href="#24"><span class="fontawesome-volume-up scnd-font-color"></span></a>
                <a class="media-btn resize" href="#25"><span class="fontawesome-resize-full scnd-font-color"></span></a>
            </div>
        </div>
    </div>

    <!-- MIDDLE-CONTAINER -->
    <div class="middle-container container">
        <div class="profile block"> <!-- PROFILE (MIDDLE-CONTAINER) -->
            <div style="float: right;width: 23px;margin: 10px;"></div>
            <div class="profile-picture big-profile-picture clear">
                <%--<img width="150px" alt="<%=user.getName()%>"--%>
                <img width="150px" alt="${user.name}"
                <%--src="/getResource?filename=<%=user.getPicture()%>">--%>
                     src="/getPic?filename=${user.picture}">
            </div>
            <%--<h1 class="user-name"><%=user.getName()%> <%=user.getSurname()%>--%>
            <h1 class="user-name">${user.name} ${user.surname}
            </h1>
            <div class="profile-description">
                <p class="scnd-font-color">Lorem ipsum dolor sit amet consectetuer adipiscing</p>
            </div>
            <ul class="profile-options horizontal-list">
                <li><a class="comments" href="#40"><p><span class="icon fontawesome-comment-alt scnd-font-color"></span>23
                </p></a></li>
                <li><a class="views" href="#41"><p><span class="icon fontawesome-eye-open scnd-font-color"></span>841
                </p></a></li>
                <li><a class="likes" href="#42"><p><span class="icon fontawesome-heart-empty scnd-font-color"></span>49
                </p></a></li>
            </ul>
        </div>

        <div class="tweets block"> <!-- TWEETS (MIDDLE-CONTAINER) -->
            <h2 class="titular"><span class="icon zocial-twitter"></span>LATEST TWEETS</h2>
            <div class="tweet first">
                <p>Ice-cream trucks only play music when out of ice-cream. Well played dad. On <a class="tweet-link"
                                                                                                  href="#17">@Quora</a>
                </p>
                <p><a class="time-ago scnd-font-color" href="#18">3 minutes ago</a></p>
            </div>
            <c:forEach items="${posts}" var="post">
                <div class="tweet">
                    <p>${post.text}
                        <a class="tweet-link" href="fullPost">Full Post</a>
                    </p>
                    <p class="scnd-font-color">${post.createdDate}</p>
                </div>
            </c:forEach>
        </div>

    </div>

    <!-- RIGHT-CONTAINER -->
    <div class="right-container container">

        <div id="frame">
            <div id="sidepanel">

                <div id="search">
                    <label for="1111"><i class="fa fa-search" aria-hidden="true"></i></label>
                    <input type="text" id="1111" placeholder="Search contacts..." oninput="searchAjax(this)"/>
                </div>
                <div id="contacts">
                    <%---------------------------------------------Ajax-----------------------------------------------------------------------------------------------%>

                </div>
            </div>

        </div>

    </div> <!-- end right-container -->
</div> <!-- end main-container -->


<script
        src="http://code.jquery.com/jquery-3.3.1.min.js"
        integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
        crossorigin="anonymous">

</script>
<script src='https://code.jquery.com/jquery-2.2.4.min.js'></script>
<script src="../front/userpage/css/index.js"></script>
<script>

    // see new requests
    function getRequests() {
        jQuery.ajax({
            url: "http://localhost:8080/requests",
            success: function (result) {
                $("#contacts").html(result);
            }
        });

    }

    // search ajax
    function searchAjax(text1) {
        jQuery.ajax({
            url: "http://localhost:8080/searchUser?userNameForSearch=" + text1.value,
            success: function (result) {
                $("#contacts").html(result);
            }
        });

    }


</script>


</body>

</html>
