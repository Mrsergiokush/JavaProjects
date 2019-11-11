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
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>Title</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <style>
        body {
            background-color: rgba(145, 145, 145, 0.26);
        }
    </style>
</head>
<body class="black">
<div class="container-fluid pt-3">
    <div class="card">
        <div class="card-body">
            <table class="table">
                <thead class="thead-dark">
                <tr>
                    <th>Username</th>
                    <th>Id</th>
                    <th>Age</th>
                    <th>Email</th>
                    <th>Action</th>
                    <th/>
                    <th/>
                </tr>
                </thead>
                <c:forEach var="user" items="${userList}">
                    <tr>
                        <td>${user.username}</td>
                        <td>${user.id}</td>
                        <td>${user.age}</td>
                        <td>${user.email}</td>

                        <form:form method="DELETE" action="${user.id}">
                            <td>
                                <button class="btn btn-primary" type="submit"/>
                                <i class="fa fa-trash-alt"/></td>
                        </form:form>

                        <td><a class="btn btn-primary" href="${user.id}/edit"><i class="fa fa-pencil-alt"/></a></td>
                        <td><a class="btn btn-primary" href="${user.id}/task/0"><i class="fa fa-list-alt"/></a></td>
                    </tr>
                </c:forEach>
            </table>

            <c:if test="${from < size-3}">
                <a class="btn btn-primary" href=${from+3}>Next</a>
            </c:if>
            <c:if test="${from > 0}">
                <a class="btn btn-primary" href=${from-3}>Back</a>
            </c:if>

            <a class="btn btn-primary" href="add">Add new user</a>
        </div>
    </div>
</div>
<form:form class="form" method="post" action="0">
    <div class="input-group mb-3">
        <select name="type" class="custom-select" id="inputGroupSelect02">
            <option value="name">Name</option>
            <option value="age">Age</option>
            <option value="email">E-mail</option>
        </select>
        <div class="input-group-append">
            <label class="input-group-text" for="inputGroupSelect02">Filter By</label>
        </div>
    </div>
    <div class="input-group mb-3"/>
    <div class="input-group-prepend">
        <span class="input-group-text" id="inputGroup-sizing-default">Input</span>
    </div>
    <div>
        <input class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default"
               value="value" name="value"/>
    </div>
</form:form>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>
</body>
</html>
