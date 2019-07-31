<%--
  Created by IntelliJ IDEA.
  User: sergio
  Date: 29/07/2019
  Time: 07:15
  To change this template use File | Settings | File Templates.
--%>
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
        <th>UserName</th>
        <th>Id</th>
        <th>Age</th>
        <td>Email</td>
        <th>Action</th>
    </tr>
    <c:forEach var="user" items="${userList}">
        <tr>
            <td>${user.userName}</td>
            <td>${user.userId}</td>
            <td>${user.age}</td>
            <td>${user.email}</td>

            <form:form method="DELETE" action="${user.userId}">
                <td><input type="submit" value="delete"/></td>
            </form:form>

            <td><a href="${user.userId}/edit">Edit</a></td>
            <td><a href="${user.userId}/task/0">Show list of tasks</a></td>
        </tr>
    </c:forEach>
</table>

<c:if test="${from < size-3}">
    <a href=${from+3}>Next</a>
</c:if>
<c:if test="${from > 0}">
    <a href=${from-3}>Back</a>
</c:if>

<a href="add">Add new user</a>
</body>
</html>
