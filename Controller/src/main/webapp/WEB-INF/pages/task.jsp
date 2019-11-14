<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var="from" value="${from}"/>
<c:set var="size" value="${size}"/>
<html>
<head>
    <title>Task List</title>
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
                    <th>Task_id</th>
                    <th>TaskName</th>
                    <th>Deadline</th>
                    <th>Priority</th>
                    <th>Done</th>
                    <th></th>
                    <th></th>
                </tr>
                </thead>
                <c:forEach var="task" items="${taskList}">
                    <tr>
                        <td>${task.id}</td>
                        <td>${task.taskName}</td>
                        <td>${task.deadline}</td>
                        <td>${task.priority}</td>
                        <td>${task.done}</td>

                        <td><a class="btn btn-primary" href="${task.id}/edit"><i class="fa fa-pencil-alt"/></a></td>
                        <form:form method="delete" action="${task.id}">
                            <td>
                                <button class="btn btn-primary" type="submit"/>
                                <i class="fa fa-trash-alt"/>
                            </td>
                        </form:form>
                    </tr>
                </c:forEach>
            </table>

            <c:if test="${from < size-5}">
                <a class="btn btn-primary" href=${from+5}>Next</a>
            </c:if>
            <c:if test="${from > 0}">
                <a class="btn btn-primary" href=${from-5}>Back</a>
            </c:if>

            <a class="btn btn-primary" href="add">Add new task</a>
            <a class="btn btn-primary" href="/JavaProject/user/0">Show users</a>
        </div>
    </div>
</div>
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