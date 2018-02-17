<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.javaprogrammer.userchatspring.model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>
</head>
<body>
<% User user= (User) request.getSession().getAttribute("user");%>
<span>Welcome</span>
<img src="/getResource?filename=<%=user.getPicture()%>" alt="Image" width="30">
<%=user.getName()%>
<%=user.getSurname()%>
${info}
<%--<a href="/logout"><button value="LOGOUT"></button></a>--%>
<%--<a href="/friends"><button value="See Friends"></button></a>--%>
<%--<a href="/messages"> <BUTTON value="See"></BUTTON></a>--%>
<%--<a href="/images"><button value="See Iamges"></button></a>--%>
<%--<a href="/update"> <BUTTON value="Settings"></BUTTON></a>--%>
<%--<a href="/likesPosts" ><button value="See our like's posts> </button></a>--%>
<c:forEach items="${posts}" var="post">
  ${post.text}
</c:forEach>

</body>
</html>
