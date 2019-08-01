<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
  Created by IntelliJ IDEA.
  User: sergio
  Date: 30/07/2019
  Time: 23:11
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form:form method="PUT" action="../${taskId}">
    <input type="text" name="taskName" value="${task.taskName}">
    <input type="date" name="date" value="${task.deadline}">
    <label>Priority</label>
    <select name="priority">
        <option>Low</option>
        <option>Medium</option>
        <option>High</option>
    </select>
    <label>IsDone</label>
    <select name="isDone">
        <option>Done</option>
        <option>Not Done</option>
    </select>
    <input type="submit" value="Save">
</form:form>
</body>
</html>
