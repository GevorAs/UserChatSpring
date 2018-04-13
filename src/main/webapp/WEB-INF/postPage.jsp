<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>MGGM</title>
    <script src="//s.codepen.io/assets/libs/modernizr.js" type="text/javascript"></script>

    <link href="//maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">
    <link href="//fonts.googleapis.com/css?family=Lato:300|Lato:normal|Open+Sans:normal|Open+Sans:600&amp;subset=latin"
          rel="stylesheet" type="text/css">
    <link href="//fonts.googleapis.com/css?family=Raleway:200,400,800|Clicker+Script" rel="stylesheet" type="text/css">
    <link href="../front/postPage/css/style.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <%--<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.5/css/materialize.min.css">--%>




    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/prefixfree/1.0.7/prefixfree.min.js"></script>

    <style>
        body {
            background-color: #3c496a;
        }

        * {
            text-align: center;
        }

        .disclaimer {
            width: 480px;
            color: #646464;
            margin: 20px auto;
            padding: 0 16px;
            text-align: center;
            font: 400 12px Roboto, Helvetica, Arial, sans-serif
        }

        .disclaimer a {
            color: #009688
        }

        #credit {
            display: none
        }
    </style>
</head>

<body style="background-color: #5b88b6 ">


<div class="header-menu" style="background-color: #1f253d">
    <nav>
        <%--<a href="#" class="main"><i class="fa fa-navicon"></i></a>--%>
        <ul class="menu">
            <li><a href="/userPage">HOME</a></li>
            <li><a href="/logout">LOGOUT</a></li>
        </ul>
    </nav>
</div>
<div class="wrapper">

    <div class="parallax">
        <section class="page-main">

            <article class="post-box">
                <div class="post-content">
                    <div class="post-title"><h2><a
                            href="/user?otherUserId=${onePost.user.id}">${onePost.user.name.toUpperCase()} ${onePost.user.surname.toUpperCase()}
                        POSTS</a></h2></div>
                    <div class="post-info">
                        <span><i class="fa fa-user"></i><a
                                href="#"> ${onePost.user.name} ${onePost.user.surname}</a></span>
                        <span><i class="fa fa-calendar"></i>${onePost.createdDate.substring(0,10)}</span>
                        <span><i class="fa fa-clock-o"></i> ${onePost.createdDate.substring(10,19)} </span>
                        <span><i class="fa fa-tags"></i>
                            <c:if test="${!onePost.user.equals(user)}">
        <a href="/sharePostToUserPage?sharePostId=${onePost.id}">Share </a>,
                            </c:if>
       <span>Views ${postVisits}</span>
      </span>
                        <span><i class="fa fa-comments"></i><a href="#"> ${commentCount} comments </a></span>

                        <a href="/likeForPost?postId=${onePost.id}">Like <span
                                style="color: #14d921; font-weight:500;font-size: 25px"><span

                                style="font-size: 20px">${onePost.likeCount}<span class="glyphicon glyphicon-star"></span></a>
                        <a href="/dislikeForPost?postId=${onePost.id}"> Dislike <span
                                style=" color: #f60707; font-weight:500;font-size: 25px"><span style="font-size: 20px">${onePost.dislikeCount} <span class="glyphicon glyphicon-star-empty"></span></a>


                    </div>
                    <div class="post-content-box">
                        <p>${onePost.text}</p>
                        <c:if test="${onePost.picture!=null}">
                            <p><img src="/getPic?filename=${onePost.picture}" alt="post img"
                                    style="margin:0 auto; width: 50% "/></p>

                        </c:if>
                        <p>
                            <c:if test="${onePost.file.endsWith('.mp4')}">
                                <video width="500" height="300" controls>
                                    <source src="/getFile?filename=${onePost.file}" type="video/mp4">
                                    Your browser does not support the video tag.
                                </video>
                            </c:if>
                        </p>
                        <c:if test="${!onePost.file.endsWith('.mp4')}">
                            <p><a href="/getFile?filename=${onePost.file}">${onePost.file.replaceAll("[0-9/_]","")}</a>
                            </p>
                        </c:if>
                    </div>
                </div>

                <div id="comments">
                    <h4 class="nc-title">${commentCount} Comments</h4>
                    <ol class="commentlist">
                        <c:forEach items="${postComements}" var="comment">
                            <li class="comment">
                                <div id="comment">
                                    <div class="comment-author">
                                        <img src="/getPic?filename=${comment.user.picture}" alt="" class="avatar"/>
                                        <span class="fn"><span>${comment.user.name} ${comment.user.surname}</span></span>
                                        <span class="ago">${comment.createDate.substring(0,19)}</span>
                                        <span class="comment-meta"></span>
                                    </div>
                                    <div class="commentmetadata">
                                        <c:if test="${comment.picture!=null}">
                                        <a href="/getPic?filename=${comment.picture}" target="_blank"> <img
                                                src="/getPic?filename=${comment.picture}" style="width: 300px">

                                            </c:if>
                                        </a>
                                        <div class="commenttext">
                                            <p>${comment.text}</p>
                                                <%--<span class="reply"><a href="#" class="comment-reply-link">Reply</a></span>--%>
                                        </div>
                                        <a href="/getFile?filename=${comment.file}" target="_blank">${comment.file}</a>
                                    </div>
                                </div>
                            </li>
                        </c:forEach>

                    </ol>
                </div>


                <div id="commentNew">
                    <div id="boxNew" class="box-New">
                        <h4 class="nc-title">New Comment</h4>
                        <spring:form action="/addComment" id="commentform" method="post" modelAttribute="emptyComment"
                                     enctype="multipart/form-data">
                            <%--${emptyComment.postId=onePost.id}--%>
                            <input type="hidden" value="${onePost.id}" name="po">
                            <div class="nc-form-fields">
                            </div>
                            <p id="comment_text_wrapper">
                                <spring:textarea path="text" id="comment_text" class="placeholder"
                                                 placeholder="Comment Text*" cols="45" rows="16"></spring:textarea>
                            </p>
                            <div class="row">

                                <span>File</span>
                                <input id="files" type="file" name="commentFile">

                            </div>
                            <h5> &nbsp;&nbsp;Or...</h5>
                            <div class="row">

                                <span>Image</span>
                                <input id="image" type="file" name="commentImage">

                            </div>
                            <br>
                            <div style="padding-left: 460px">
                            <p id="comment_submit_wrapper">
                                <input name="submit" id="comment_submit" type="submit" value="Post Comment"/>
                                <input name="" type="hidden" value=""/>
                            </p>
                            </div>
                        </spring:form>
                    </div>
                </div>
            </article>

        </section>
    </div>
</div>

<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
<script src='http://demo.mythemeshop.com/yosemite/wp-content/themes/yosemite/js/parallax.js?ver=4.0.1'></script>


<script src="../front/postPage/js/index.js"></script>
<script src="../front/past_form/js/index.js"></script>

</body>

</html>
