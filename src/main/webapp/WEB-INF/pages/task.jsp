<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var="from" value="${from}"/>
<c:set var="size" value="${size}"/>
<html>
<head>
    <title>Title</title>
    <style>
        table {
            font-family: arial, sans-serif;
            border-collapse: collapse;
            width: 100%;
        }

        td, th {
            border: 1px solid #dddddd;
            text-align: left;
            padding: 8px;
        }

        tr:nth-child(even) {
            background-color: #dddddd;
        }
    </style>
</head>
<body>
<table>
    <tr>
        <th>Task_id</th>
        <th>TaskName</th>
        <th>Deadline</th>
    </tr>
    <c:forEach var="task" items="${taskList}">
        <tr>
            <td>${task.taskId}</td>
            <td>${task.taskName}</td>
            <td>${task.deadline}</td>
            <td><a href="${task.taskId}/edit">Edit</a></td>
            <form:form method="DELETE" action="${task.taskId}">
                <td><input type="submit" value="Delete"></td>
            </form:form>
        </tr>
    </c:forEach>
</table>

<c:if test="${from < size-3}">
    <a href=${from+3}>Next</a>
</c:if>
<c:if test="${from > 0}">
    <a href=${from-3}>Back</a>
</c:if>

<a href="add">Add new task</a>
<a href="/JavaProjects/user/0">Show users</a>
</body>
</html>