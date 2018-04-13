<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>


    <meta charset="UTF-8">

    <title>MGGM</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
          integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/lightbox2/2.8.2/css/lightbox.min.css">


    <link rel="stylesheet" href="../front/nkrner/css/style.css">


</head>
<body>


<spring:form action="/saveImage" method="post" id="1111" enctype="multipart/form-data">


</spring:form>


<nav class="navbar navbar-inverse navbar-static-top" role="navigation">
    <div class="container">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/userPage">HOME</a>

            <spring:form action="/saveImage" method="post" id="1111" enctype="multipart/form-data">


                <button class="input-file">
                    <input type="file" id="file-input" name="userImage"/>
                    <label for="file-input">Choose File</label>
                </button>
                <button type="submit" class="input-file" id="1">
                    Upload
                    <label for="1"></label>
                </button>
            </spring:form>

        </div>

    </div>

</nav>

<div class="container">
    <div class="row">

        <c:forEach items="${images}" var="im">


            <div class="col-lg-3 col-md-4 col-xs-6 thumb">
                <a href="/deleteImage?id=${im.id}" style="font-weight: bold;color:red;font-size: 30px"><span
                        class="glyphicon glyphicon-remove"></span></a>
                <a class="thumbnail"
                   href="/getUserPic?filename=${im.picture}"
                   data-lightbox="imgGLR"><img class="img-responsive"
                                               border="0"
                                               height="300"
                                               src="/getUserPic?filename=${im.picture}"

                                               width="400"/></a>
                &nbsp;
                <span style="color: #14d921; font-weight:500;font-size: 35px"><span
                        class="glyphicon glyphicon-star"></span>${im.likeCount}</span>
                <span style="float: right; color: #f60707; font-weight:500;font-size: 35px">${im.dislikeCount} <span
                        class="glyphicon glyphicon-star-empty"></span></span>
            </div>

        </c:forEach>

    </div>
</div>

<script src="https://cdnjs.cloudflare.com/ajax/libs/lightbox2/2.8.2/js/lightbox-plus-jquery.min.js"></script>


<script>


    $("#1111").submit(function (e) {
        e.preventDefault();  // <--------stops the form submission
        var fd = new FormData($("#1111")[0]);
        // fd.append('groupName', 'xxx');
        $.ajax({
            url: "/saveImage",
            type: "POST",
            data: fd,
            enctype: 'multipart/form-data',
            processData: false,
            contentType: false,
            cache: false,
            success: function (result) {
                // $("#concatProfile").html(result);
            }
        });

    });


</script>
</body>
</html>
