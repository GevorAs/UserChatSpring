<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Arianna
  Date: 14.02.2018
  Time: 15:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<spring:form action="/login" method="post">
    <input type="email" name="email">
    <input type="password" name="password">
    <input type="submit" value="login">
</spring:form>

</body>
</html>
