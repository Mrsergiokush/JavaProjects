<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: sergio
  Date: 30/07/2019
  Time: 15:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="add" method="POST">
    <label>Task Name</label>
    <input type="text" name="taskName">

    <label>Deadline</label>
    <input type="date" name="deadLine">

    <label>IsDone</label>
    <select name="isDone">
        <option>Done</option>
        <option>Not Done</option>
    </select>

    <label>Priority</label>
    <select name="priority">
        <option>Low</option>
        <option>Medium</option>
        <option>High</option>
    </select>

    <input type="submit" value="Add new task">
</form>
</body>
</html>