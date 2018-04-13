<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>MGGM</title>


    <link rel="stylesheet" href="../front/settings/css/style.css">


</head>

<body>
<div class="form_wrapper">
    <a href="/userPage"><button>Home</button></a>
    <div class="form_container">
        <div class="title_container">
            <h2>Update Your profile</h2>
            <span style="color: #F06965">${message}</span>


        </div>
        <div class="row clearfix">
            <div class="">


                <spring:form action="/updateUser" method="post" modelAttribute="user" enctype="multipart/form-data">



                    <div class="row clearfix">
                        <div class="col_half">
                            <div class="input_field"><span><i aria-hidden="true" class="fa fa-user"></i></span>
                                <spring:input type="text" id="name" path="name" placeholder="Name"
                                              value="${user.name}"></spring:input>
                            </div>
                        </div>
                        <div class="col_half">
                            <div class="input_field"><span><i aria-hidden="true" class="fa fa-user"></i></span>
                                <spring:input type="text" id="surname" path="surname"
                                              placeholder="Surname" value="${user.surname}"></spring:input>
                            </div>
                        </div>
                    </div>
                    <div class="row clearfix">
                        <div class="col_half">
                            <div class="input_field"><span><i aria-hidden="true" class="fa fa-user-secret"></i></span>
                                <spring:input type="text" id="age" path="age" placeholder="Age"
                                              value="${user.age}"></spring:input>
                            </div>
                        </div>
                        <div class="col_half">
                            <div class="input_field"><span><i aria-hidden="true" class="fa fa-globe"></i></span>
                                <spring:input type="text" id="city" path="city" placeholder="City"
                                              value="${user.city}"></spring:input>
                            </div>
                        </div>
                    </div>
                    <div class="input_field"><span><i aria-hidden="true" class="fa fa-lock"></i></span>
                        <input type="password" id="password" name="oldPassword" placeholder="Old Password">
                    </div>
                    <div class="input_field"><span><i aria-hidden="true" class="fa fa-lock"></i></span>
                        <spring:input type="password" id="password" path="password"
                                      placeholder="New Password"></spring:input>
                    </div>
                    Profile image: <input type="file" title="Picture" name="pic"><br><br>


                    <input class="button" type="submit" value="Save"/>
                </spring:form>
            </div>
        </div>
    </div>
</div>

<script src='https://use.fontawesome.com/4ecc3dbb0b.js'></script>


</body>

</html>
