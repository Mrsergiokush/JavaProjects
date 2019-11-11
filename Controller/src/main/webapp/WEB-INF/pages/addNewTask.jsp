<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form:form action="add" method="post">
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
</form:form>
</body>
</html>