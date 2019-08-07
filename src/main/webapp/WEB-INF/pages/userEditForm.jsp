<%--
  Created by IntelliJ IDEA.
  User: sergio
  Date: 30/07/2019
  Time: 18:12
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form:form method="PUT" action="/JavaProjects/user/${user.id}">
    <input type="text" name = "userName" value="${user.userName}">
    <input type="email" name = "email" value="${user.email}">
    <input type="number" name = "age" value="${user.age}">
    <input type="submit" value="Save">
</form:form>
</body>
</html>