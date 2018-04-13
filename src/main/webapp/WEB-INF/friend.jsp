<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>MGGM</title>

    <link rel='stylesheet prefetch' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css'>

    <link rel="stylesheet" href="../front/friend/css/style.css">


</head>

<body>

<a href="/userPage"><button style="width: 100px;height: 40px">Home</button></a>
<br>
<br>
<c:forEach items="${userFriends}" var="userfriend">

<div class="row">

    <div class="col-md-12">

        <div class="media">

            <a class="pull-left" href="/user?otherUserId=${userfriend.id}">
                <img class="media-object dp img-circle" src="/getPic?filename=${userfriend.picture}" style="width: 100px;height:100px;">


            </a>
            <div class="media-body">
                <h4 class="media-heading">${userfriend.name} ${userfriend.surname}</h4>
                <hr style="margin:8px auto">

                <a href="/messages"><span class="label label-success">Send Message</span></a>
                <a href="/removeFriends?friendForRemove=${userfriend.id}"><span class="label label-warning">Remove</span></a>

            </div>

        </div>

    </div>



</div>
</c:forEach>



</body>

</html>
