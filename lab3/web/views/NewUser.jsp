<%@ page import="model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Registration</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta charset="UTF-8">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
    <link rel="stylesheet" href="js/jquery.timepicker.min.css">
    <script src="js/jquery.timepicker.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/inputStyle.css">
    <link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Open+Sans|Raleway|Candal">
    <link rel="stylesheet" type="text/css" href="css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="css/style.css">

</head>
<body id="myPage" data-spy="scroll" data-target=".navbar" data-offset="60">
<div>
    <jsp:include page="header.jsp"/>
</div>
<div class="title">
    <b>Registration</b>
</div>
<form action="${pageContext.request.contextPath}/registration" method="POST" id="registration" class="registration">
    <div class="group">
        <label for="first_name" class="label2">
            <input type="text" name="first_name" id="first_name" value="${firstName}" pattern="^[A-Z][a-z]{2,14}$" required>
            <span class="bar"></span>
            <label class="myLabel">First name</label>
        </label>
    </div>
    <div class="group">
        <label for="last_name" class="label2">
            <input type="text" name="last_name" id="last_name" value="${lastName}" pattern="^[A-Z][a-z]{2,14}$" required>
            <span class="bar"></span>
            <label class="myLabel">Last name</label>
        </label>
    </div>
    <div class="errorMessage group">
        <c:forEach items="${registerError}" var="error">
            <span class="errorMessage">${error}</span>
        </c:forEach>
    </div>
    <div class="group">
        <label for="login" class="label2">
            <input type="text" name="login" id="login" value="${login}" pattern="^[a-zA-Z][a-zA-Z0-9\\-\\_\\.]{1,18}[a-zA-Z0-9]$" required>
            <span class="bar"></span>
            <label class="myLabel">Login</label>
        </label>
    </div>
    <div class="group">
        <label for="password" class="label2">
            <input type="password" name="password" id="password" required>
            <span class="bar"></span>
            <label class="myLabel">Password</label>
        </label>
    </div>
    <div class="group">
        <label for="confirm_password" class="label2">
            <input type="password" name="confirm_password" id="confirm_password" required>
            <span class="bar"></span>
            <label class="myLabel">Confirm password</label>
        </label>
    </div>
    <div align="center"><input type="submit" name="buttonAdd" value="Register"></div>
</form>

<script src="js/jquery.easing.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/custom.js"></script>

</body>
</html>

