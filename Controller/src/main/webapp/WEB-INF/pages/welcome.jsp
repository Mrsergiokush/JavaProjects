<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Welcome</title>
    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="jumbotron text-center">
    <c:if test="${pageContext.request.userPrincipal.name != null}">
        <h1 class="display-3">Thank You ${pageContext.request.userPrincipal.name}!</h1>
        <p class="lead"><strong>Please check your tasks</strong> for fill in your table with new special tasks.</p>
        <hr>
        <p>
        <form id="logoutForm" method="POST" action="${contextPath}/logout">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
        <a onclick="document.forms['logoutForm'].submit()">Logout</a>
        </p>
        <p class="lead">
            <a class="btn btn-primary btn-sm" href="user/validate" role="button">Start using app</a>
        </p>
    </c:if>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>