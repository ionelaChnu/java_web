<%@ page import="domain.ResultDTO" %>
<%@ page import="domain.Student" %>
<%@ page import="java.util.List" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Результат</title>

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
<% ResultDTO dto = ((ResultDTO) request.getAttribute("result"));
    pageContext.setAttribute("five", dto.getMarkCount().get(5));
    pageContext.setAttribute("four", dto.getMarkCount().get(4));
    pageContext.setAttribute("three", dto.getMarkCount().get(3));
    pageContext.setAttribute("two", dto.getMarkCount().get(2));
%>
<div class="masthead1">
    <div class="masthead-bg1"></div>
    <div class="container h-100">
        <div class="row h-100">
            <div class="col-12 my-auto">
                <div class="masthead-content text-white py-5 py-md-0">
                    <h2 class="mb-3">Результати по предмету:
                        <% if (request.getAttribute("subject").equals("math")) { %>
                        Математика
                        <% } else if (request.getAttribute("subject").equals("history")) { %>
                        Історія
                        <% } else { %>
                        Музика
                        <% }%>
                    </h2>
                    <h5 class="mb-3">Кільтість "5":${pageScope.five}</h5>
                    <h5 class="mb-3">Кільтість "4":${pageScope.four}</h5>
                    <h5 class="mb-3">Кільтість "3":${pageScope.tree}</h5>
                    <h5 class="mb-3">Кільтість "2":${pageScope.two}</h5>
                    <h4 class="mb-3">КСредня оцінка з предмету:${result.average}</h4>
                    <% if (!((ResultDTO) request.getAttribute("result")).getStudents().isEmpty()) { %>
                    <h5 class="mb-3">Студенти:</h5>
                    <table class="table table-hover table-dark">
                        <thead>
                        <tr>
                            <th scope="col">Ім'я</th>
                            <th scope="col">Прізвище</th>
                            <th scope="col">Номер групи</th>
                            <th scope="col">Оцінка</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${result.students}" var="student">
                            <tr>
                                <td>${student.firstName}</td>
                                <td>${student.lastName}</td>
                                <td>${student.groupNumber}</td>
                                <th scope="row"><% if (request.getAttribute("subject").equals("math")) { %>
                                        ${student.mathMark}
                                    <% } else if (request.getAttribute("subject").equals("history")) { %>
                                        ${student.historyMark}
                                    <% } else { %>
                                        ${student.musicMark}
                                    <% }%>
                                </th>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <% } else { %>
                    <br>
                    <label>Студетів немає.</label>
                    <div class="col-md-12 text-center" style="margin-top: 30px">
                        <a href="<c:url value = "/addStudent"/>" class="btn btn btn-secondary center"
                           style="height: 50px">Додати студента</a>
                    </div>
                    <% }%>
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
