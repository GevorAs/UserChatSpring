<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
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







            <%--<li>--%>
            <%--<a class="header-menu-tab" href="#5"><span class="icon fontawesome-star-empty scnd-font-color"></span>Favorites</a>--%>
            <%--</li>--%>
            <li>
                <a class="header-menu-tab" href="/logout"><span
                        class="icon entypo-paper-plane scnd-font-color"></span>Logout</a>
            </li>
        </ul>
        <div class="profile-menu">
            <%--<p>--%>
                <%--<a href="/userPage">--%>
                <%--<span class="scnd-font-color">--%>
                    <%--<span class="profile-picture small-profile-picture">--%>
                    <%--<img width="40px" alt="${user.name}" src="/getPic?filename=${user.picture}">--%>
                    <%--</span>--%>
                <%--</span>--%>
                <%--</a>--%>
            <%--</p>--%>
        </div>
    </header>

    <!-- LEFT-CONTAINER -->
    <div class="left-container container">
        <div class="menu-box block"> <!-- MENU BOX (LEFT-CONTAINER) -->
            <h2 class="titular">MENU BOX</h2>
            <ul class="menu-box-menu">
                <li >
                    <a class="menu-box-tab" href="#" onclick="getDeletedUser()" ><span class="icon entypo-cog scnd-font-color"></span>Deleted Users

                    </a>
                </li>

            </ul>
        </div>



    </div>

    <!-- MIDDLE-CONTAINER -->
    <div class="middle-container container">
        <div id="frame">
            <div id="sidepanel">

                <div id="search">

                </div>
                <div id="contacts" class="deleted" >

                </div>
            </div>

        </div>



    </div>

    <!-- RIGHT-CONTAINER -->
    <div class="right-container container">

        <div id="frame">
            <div id="sidepanel">

                <div id="search">
                    <label for=""><i class="fa fa-search" aria-hidden="true"></i></label>
                    <input type="text" placeholder="Search contacts..." oninput="searchAjax(this)"/>
                </div>
                <div id="contacts" class="searchBox">

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
    function getDeletedUser() {
        jQuery.ajax({
            url: "/getDeletedUser",
            success: function (result) {
                $(".deleted").html(result);
            }
        });

    }

    function searchAjax(text1) {
        jQuery.ajax({
            url: "/searchUser?userNameForSearch=" + text1.value,
            success: function (result) {
                $(".searchBox").html(result);
            }
        });

    }



</script>


</body>

</html>
