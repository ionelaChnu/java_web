<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Add student</title>

    <!-- Bootstrap core CSS -->
    <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom fonts for this template -->
    <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:200,200i,300,300i,400,400i,600,600i,700,700i,900,900i"
          rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Merriweather:300,300i,400,400i,700,700i,900,900i"
          rel="stylesheet">
    <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">

    <!-- Custom styles for this template -->
    <link href="css/coming-soon.css" rel="stylesheet">

</head>

<body>

<div class="overlay"></div>
<div class="masthead">
    <div class="masthead-bg"></div>
    <div class="container h-100">
        <div class="row h-100">
            <div class="col-12 my-auto">
                <div class="masthead-content text-white py-5 py-md-0">
                    <h3 class="mb-3">Додати студента</h3>
                    <form action="${pageContext.request.contextPath}/addStudent" method="POST" class="input-group input-group-newsletter">
                        <div class="input-group input-group-newsletter">
                            <label for="first_name">
                                <label class="myLabel">Ім'я</label>
                                <input type="text" name="first_name" id="first_name" value="${firstName}" pattern="^[A-Z][a-z]{2,14}$"
                                       required class="form-control" style="height: 30px">
                            </label>
                        </div>
                        <div class="input-group input-group-newsletter">
                            <label for="last_name" class="label2">
                                <label class="myLabel">Прізвище</label>
                                <input type="text" name="last_name" id="last_name" value="${lastName}" pattern="^[A-Z][a-z]{2,14}$"
                                       required class="form-control" style="height: 30px">
                            </label>
                        </div>
                        <div class="input-group input-group-newsletter">
                            <label for="group_number" class="label2">
                                <label class="myLabel">Номер групи</label>
                                <input type="text" name="group_number" id="group_number" value="${groupNumber}"
                                       required class="form-control" style="height: 30px">
                            </label>
                        </div>
                        <div class="input-group input-group-newsletter">
                            <label for="math_mark" class="label2">
                                <label class="myLabel">Оцінка з математики</label>
                                <input type="number" name="math_mark" id="math_mark" value="${mathMark}"
                                       required class="form-control" style="height: 30px">
                            </label>
                        </div>
                        <div class="input-group input-group-newsletter">
                            <label for="history_mark" class="label2">
                                <label class="myLabel">Оцінка з історії</label>
                                <input type="number" name="history_mark" id="history_mark" value="${historyMark}"
                                       required class="form-control" style="height: 30px">
                            </label>
                        </div>
                        <div class="input-group input-group-newsletter">
                            <label for="music_mark" class="label2">
                                <label class="myLabel">Оцінка з музики</label>
                                <input type="number" name="music_mark" id="music_mark" value="${musicMark}"
                                       required class="form-control" style="height: 30px">
                            </label>
                        </div>
                        <div class="input-group input-group-newsletter">
                            <c:forEach items="${errors}" var="error">
                                <span>${error}</span>
                            </c:forEach>
                        </div>
                        <div class="input-group input-group-newsletter">
                            <button class="btn btn btn-secondary center" type="submit" style="height: 50px">Додати</button>
                        </div>
                    </form>
                    <div class="col-md-12 text-center" style="margin-top: 30px">
                        <a href="<c:url value = "/"/>" class="btn btn btn-secondary center" style="height: 50px">Повернутись на головну</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Bootstrap core JavaScript -->
<script src="vendor/jquery/jquery.min.js"></script>
<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<!-- Custom scripts for this template -->
<script src="js/coming-soon.min.js"></script>

</body>

</html>