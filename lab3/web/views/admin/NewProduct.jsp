<%@ page import="model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Product</title>
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
<body id="myPage" data-spy="scroll" data-target=".navbar" data-offset="60" >
<div>
    <jsp:include page="../header.jsp"/>
</div>
<div class="title">
    <b>${mode} new product</b>
</div>
<form action="${pageContext.request.contextPath}/product" method="post" id="registration" class="registration">
    <input type="hidden" value="${id}" name="id">
    <div class="group">
        <label for="name" class="label2">
            <input type="text" name="name" id="name" value="${name}" required>
            <span class="bar"></span>
            <label class="myLabel">Name</label>
        </label>
    </div>
    <div class="group">
        <label for="price" class="label2">
            <input type="number" step="0.01" name="price" id="price" value="${price}" required>
            <span class="bar"></span>
            <label class="myLabel">Price</label>
        </label>
    </div>
    <div class="errorMessage group">
        <c:forEach items="${registerError}" var="error">
            <span class="errorMessage">${error}</span>
        </c:forEach>
    </div>
    <div class="group">
        <label for="description" class="label2">
            <input type="text" name="description" id="description" value="${description}">
            <span class="bar"></span>
            <label class="myLabel">Description</label>
        </label>
    </div>
    <div align="center"><input type="submit" name="buttonAdd" value="${mode}"></div>
</form>

<script src="js/jquery.easing.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/custom.js"></script>

</body>
</html>

