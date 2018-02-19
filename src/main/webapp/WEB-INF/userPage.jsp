<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
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
<br>

Search: <input type="text"  onkeypress="searchAjax(this)">

<div id="div1">

</div>


<script>
function searchAjax(text1) {
   jQuery.ajax({url:
        "http://localhost:8080/searchUser?userNameForSearch="+
        text1.val,
        success: function(result){
            $("#div1").html(result);
        }});

}
</script>
</body>
</html>
