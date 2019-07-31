<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: sergio
  Date: 30/07/2019
  Time: 23:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form:form method="PUT" action="../${taskId}">
    <input type="text" name="taskName" value="${task.taskName}">
    <input type="date" name="date" value="${task.deadline}">
    <input type="submit" value="Save">
</form:form>
</body>
</html>
