<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>

<head>
    <meta charset="UTF-8">
    <title>MGGM</title>

    <link rel="stylesheet" href="../front/userpage/css/style.css">
    <link rel="stylesheet" href="../front/userpage/css/style1.css">
    <link rel="stylesheet" href="../front/button/css/style2.css">
    <script src="../front/jquery-3.3.1.min.js"></script>

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
                <a class="header-menu-tab" href="/settingPage"><span class="icon entypo-cog scnd-font-color"></span>Settings</a>
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
                    <a class="menu-box-tab" href="/myImages"><span
                            class="icon entypo-calendar scnd-font-color"></span>Images
                        <div class="menu-box-number"></div>
                    </a>
                </li>
                <li>
                    <a class="menu-box-tab" href="/addPost"><span
                            class="icon entypo-chart-line scnd-font-color"></span>Add Post
                        <div class="menu-box-number"></div>
                    </a>
                </li>
                <li>
                    <a class="menu-box-tab" href="/allFriends"><span class="icon fontawesome-user scnd-font-color" ></span>Friends
                        <div class="menu-box-number"></div>
                    </a>
                </li>
                <li>
                    <a class="menu-box-tab" href="/contactTheAdminPage"><span class="icon entypo-cog scnd-font-color"></span>Contact the Admin</a>
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
                <p class="scnd-font-color">Age: ${user.age}&nbsp &nbsp City: ${user.city}</p>
            </div>
            <ul class="profile-options horizontal-list">

                <li><a class="views" href="#41"><p><span class="icon fontawesome-eye-open scnd-font-color"></span>${userVisit}
                </p></a></li>
                <li><a class="likes" href="#42"><p><span class="icon fontawesome-heart-empty scnd-font-color"></span>${rating}
                </p></a></li>
            </ul>
        </div>

        <div class="tweets block" style="overflow: scroll ;height: 500px" > <!-- TWEETS (MIDDLE-CONTAINER) -->
            <h2 class="titular"><span class="icon zocial-twitter"></span>POSTS</h2>
            <c:forEach items="${posts}" var="post">
                <div class="tweet first">
                    <p style="overflow: hidden; text-overflow: ellipsis; white-space: nowrap;">${post.text}
                    </p>
                    <p style="text-align: center"><a class="tweet-link" href="/seePost?postId=${post.id}">See post</a></p>
                    <p style="text-align: center"><a class="time-ago scnd-font-color" href="#18">${post.createdDate}</a></p>
                </div>
            </c:forEach>
            <%--<div class="tweet">--%>
            <%--<p>--%>
            <%--<a class="tweet-link" href="#">Full Post</a>--%>
            <%--</p>--%>
            <%--<p class="scnd-font-color"></p>--%>
            <%--</div>--%>

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


<%--<script--%>
<%--src="http://code.jquery.com/jquery-3.3.1.min.js"--%>
<%--integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="--%>
<%--crossorigin="anonymous">--%>

<%--</script>--%>
<%--<script src='https://code.jquery.com/jquery-2.2.4.min.js'></script>--%>
<script src="../front/userpage/css/index.js"></script>
<script>

    // see new requests
    function getRequests() {
        jQuery.ajax({
            url: "/requests",
            success: function (result) {
                $("#contacts").html(result);
            }
        });

    }

    // search ajax
    function searchAjax(text1) {
        jQuery.ajax({
            url: "/searchUser?userNameForSearch=" + text1.value,
            success: function (result) {
                $("#contacts").html(result);
            }
        });

    }


</script>


</body>

</html>
