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
<body>
<form class="form-inline" action="add" method="POST">
    <div class="form-group mx-sm-3 mb-2">
        <label></label>
        <input class="form-control" placeholder="Name:" type="text" name="userName">
    </div>
    <div class="form-group mx-sm-3 mb-2">
        <%--@declare id="inputemail4"--%><label for="inputEmail4"></label>
        <input type="email" name="email" class="form-control" id="inputEmail4" placeholder="Email">
    </div>
    <div class="form-group mx-sm-3 mb-2">
        <label></label>
        <input type="number" name="age" class="form-control" placeholder="Age">
    </div>
    <input class="btn btn-primary mb-2" type="submit" value="Add new user">
</form>
</body>
</html>
