<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>


    <meta charset="UTF-8">

    <title>WEB TEMPLATE: Image Gallary Bootstrap & Lightbox 2.</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
          integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/lightbox2/2.8.2/css/lightbox.min.css">


    <link rel="stylesheet" href="../nkrner/css/style.css">


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


        </div>

    </div>

</nav>

<div class="container">
    <div class="row">

        <c:forEach items="${images}" var="im">


            <div class="col-lg-3 col-md-4 col-xs-6 thumb"><a class="thumbnail"
                                                             href="/getUserPic?filename=${im.picture}"
                                                             data-lightbox="imgGLR"><img class="img-responsive"
                                                                                         border="0"
                                                                                         height="300"
                                                                                         src="/getUserPic?filename=${im.picture}"
                                                                                         width="400"/></a><a
                    href="#">heto kpoxenq</a></div>
        </c:forEach>

    </div>
</div>

<script src="https://cdnjs.cloudflare.com/ajax/libs/lightbox2/2.8.2/js/lightbox-plus-jquery.min.js"></script>
</body>
</html>
