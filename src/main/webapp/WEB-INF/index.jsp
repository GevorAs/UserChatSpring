<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>MGGM</title>
    <link rel="stylesheet" href="../front/login/css/style.css">
</head>

<body>

<span style="color: #F06965">${message}</span>
<div class="bg"></div>

<div class="panel">
    <input type="radio" id="switch-open" name="switch"/>
    <input type="radio" id="switch-close" name="switch"/>


    <div class="login">

        <h1>LOGIN</h1>
        <spring:form action="/" method="post">
            <div class="group"><i class="fa fa-envelope" aria-hidden="true"></i>
                <input type="text" id="vEmail" placeholder="E-Mail"  name="j_email"/>
                <label for="vEmail"></label>
            </div>
            <div class="group"><i class="fa fa-unlock-alt" aria-hidden="true"></i>
                <input type="password" id="vPassword" placeholder="Password" name="j_password"/>
                <label for="vPassword"></label>
            </div>
            <input type="submit" value="LOGIN"/>
        </spring:form>
    </div>


    <div class="register">


        <label class="button-open" for="switch-open"></label>
        <label class="button-close" for="switch-close"></label>
        <div class="inner">
            <h1>REGISTER</h1>
            <spring:form action="/register" modelAttribute="userRegister" method="post" enctype="multipart/form-data">
                <div class="group"><i class="fa fa-user" aria-hidden="true"></i>
                    <spring:input type="text" id="name" path="name" placeholder="Name"></spring:input>
                    <label for="name"></label>
                </div>
                <div class="group"><i class="fa fa-user" aria-hidden="true"></i>
                    <spring:input type="text" id="name" path="surname" placeholder="surname"></spring:input>
                    <label for="name"></label>
                </div>
                <div class="group"><i class="fa fa-envelope" aria-hidden="true"></i>
                    <spring:input type="text" id="email" path="email" placeholder="E-Mail"></spring:input>
                    <label for="email"></label>
                </div>
                <div class="group"><i class="fa fa-unlock-alt" aria-hidden="true"></i>
                    <spring:input type="text" id="password" path="password" placeholder="Password"></spring:input>
                    <label for="password"></label>
                </div>
                <input type="file" title="Picture" name="pic">

                <input type="submit" value="REGISTER"/>
            </spring:form>
        </div>
    </div>


</div>
<script>

document.getElementById("vEmail").value="${vEmail}"
document.getElementById("vPassword").value="${vPassword}"


</script>

</body>

</html>



