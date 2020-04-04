<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Lab2</title>

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
                    <h2 class="mb-3">Лупашку Іонела</h2>
                    <h3 class="mb-3">Варіант 14</h3>
                    <p class="mb-5">Інформація про літню сесію знаходиться в БД (номер групи, прізвище та ініціали
                        студента, оцінки з 3-х предметів).
                        <strong>Знайти кількість одержаних оцінок 5,4,3,2 і середній бал із кожного
                            предмета.</strong>
                    </p>
                    <form action="${pageContext.request.contextPath}/StudentServlet" method="GET"
                          class="input-group input-group-newsletter">
                        <select id="subject" name="subject" class="form-control" style="height: 53.2px">
                            <option value="math">Математика</option>
                            <option value="history">Історія</option>
                            <option value="music">Музика</option>
                        </select>
                        <div class="input-group-append">
                            <button class="btn btn-secondary" type="submit">OK</button>
                        </div>
                    </form>
                    <div class="col-md-12 text-center" style="margin-top: 30px">
                        <a href="<c:url value = "/addStudent"/>" class="btn btn btn-secondary center"
                           style="height: 50px">Додати студента</a>
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