<%--
  Created by IntelliJ IDEA.
  User: sergio
  Date: 28/07/2019
  Time: 20:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="add" method="POST">
    <label>User Name</label>
    <input type="text" name="userName">
    <label>Email</label>
    <input type="email" name="email">
    <label>Age</label>
    <input type="number" name="age">
    <input type="submit" value="Add new user">
</form>
</body>
</html>
