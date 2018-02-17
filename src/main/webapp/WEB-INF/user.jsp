<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ page import="com.javaprogrammer.userchatspring.model.User" %><%--
  Created by IntelliJ IDEA.
  User: Arianna
  Date: 15.02.2018
  Time: 18:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>
</head>
<body>
<% User user= (User) request.getSession().getAttribute("user");%>
<span><%=user.getEmail()%></span>


</body>
</html>
