<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Sign in</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta charset="UTF-8">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
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
    <b>Sign in</b>
</div>
<form action="${pageContext.request.contextPath}/signIn" method="POST" id="registration" class="registration">
    <div class="errorMessage group">
        <span class="errorMessage">${loginError}</span>
    </div>
    <div class="group ">
        <label for="login" class="label2">
            <input type="text" name="login" id="login" value="${login}" required>
            <span class="bar"></span>
            <label class="myLabel">Login</label>
        </label>
    </div>
    <div class="group ">
        <label for="password" class="label2">
            <input type="password" name="password" id="password" required>
            <span class="bar"></span>
            <label class="myLabel">Password</label>
        </label>
    </div>
    <div align="center"><input type="submit" name="buttonAdd" value="Sign in"></div>
</form>
<script src="js/jquery.min.js"></script>
<script src="js/jquery.easing.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/custom.js"></script>

</body>
</html>
