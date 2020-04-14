<%@ page import="model.User" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Users</title>
    <script src="js/jquery.min.js"></script>
    <script src="js/jquery.easing.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/custom.js"></script>
    <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>

    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css"
          integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Open+Sans|Raleway|Candal">
    <link rel="stylesheet" type="text/css" href="css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <link rel="stylesheet" href="css/card.css">


</head>

<body id="myPage" data-spy="scroll" data-target=".navbar" data-offset="60" style="background: #15646E !important">
<jsp:include page="../header.jsp"/>

<div class="container">
    <div class="row card cardWidth col-centered" >
        <div class="card-profile">
            <div class="col-lg-12 col-md-12 col-sm-12">
                <h4 class="card-title">Active Users </h4>
                <div class="boxHidden">
                    <span class="errorMessage" style="color: red; font-size: 20px;">${message}</span>
                    <span style="color: #3c763d; font-size: 20px;">${success[0]}</span>
                </div>
                <% if (((List<User>) request.getAttribute("activeUsers")).size()>0){ %>
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th scope="col">Firs name</th>
                        <th scope="col">Last name</th>
                        <th scope="col">Login</th>
                        <th scope="col"></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${activeUsers}" var="user">
                        <tr>
                            <th scope="row">${user.firstName}</th>
                            <th scope="row">${user.lastName}</th>
                            <th scope="row">${user.login}</th>
                            <td>
                                <form method="post" action="${pageContext.request.contextPath}/changeStatus">
                                    <input type="hidden" value="${user.id}" name="idUser">
                                    <input type="hidden" value="1" name="status">
                                    <input type="submit" class="action-button" style="width: 50%" value="Block">
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <% }else{ %>
                <br>
                <label >There are no active users now.</label>
                <% }%>
            </div>
        </div>
    </div>
</div>
</div>
<div class="container">
    <div class="row card cardWidth col-centered" style="margin-top: 0">
        <div class="card-profile">
            <div class="col-lg-12 col-md-12 col-sm-12">
                <h4 class="card-title">Disabled Users </h4>
                <div class="boxHidden">
                    <span class="errorMessage" style="color: red; font-size: 20px;">${errors[0]}</span>
                    <span style="color: #3c763d; font-size: 20px;">${success[0]}</span>
                </div>
                <% if (((List<User>) request.getAttribute("disabledUsers")).size()>0){ %>
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th scope="col">Firs name</th>
                        <th scope="col">Last name</th>
                        <th scope="col">Login</th>
                        <th scope="col"></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${disabledUsers}" var="user">
                        <tr>
                            <th scope="row">${user.firstName}</th>
                            <th scope="row">${user.lastName}</th>
                            <th scope="row">${user.login}</th>
                            <td>
                                <form method="post" action="${pageContext.request.contextPath}/changeStatus">
                                    <input type="hidden" value="${user.id}" name="idUser">
                                    <input type="hidden" value="0" name="status">
                                    <input type="submit" class="action-button" style="width: 50%" value="Unblock">
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <% }else{ %>
                <br>
                <label >There are no disable users now.</label>
                <% }%>
            </div>
        </div>
    </div>
</div>
</div>
</body>

</html>
