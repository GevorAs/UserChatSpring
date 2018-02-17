<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<span>${message}</span><br>
<spring:form action="/login" method="post">
    <input type="email" name="emailLogin"><br>
    <input type="password" name="passwordLogin"><br>
    <input type="submit" value="login">
</spring:form>
<br>
<spring:form action="/register" modelAttribute="userRegister" method="post" enctype="multipart/form-data">
    <spring:input path="name"></spring:input><br>
    <spring:input path="surname"></spring:input><br>
    <spring:input path="email" ></spring:input><br>
    <spring:password path="password"></spring:password><br>
    <input type="file" title="Picture" name="pic">
    <input type="submit" value="AddUser">

</spring:form>

</body>
</html>
