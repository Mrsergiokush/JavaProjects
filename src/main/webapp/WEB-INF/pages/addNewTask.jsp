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
    <input type="text" name = "taskName">
    <label>Deadline</label>
    <input type = "date" name = "deadLine">
    <input type="submit" value="Add new task">
</form>
</body>
</html>

