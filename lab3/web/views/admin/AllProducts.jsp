<%@ page import="model.User" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Product" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Products</title>
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
    <div class="row card col-centered">
        <div class="card-profile">
            <div class="col-lg-12 col-md-12 col-sm-12">
                <h4 class="card-title">Active Products </h4>
                <div class="boxHidden">
                    <span class="errorMessage" style="color: red; font-size: 20px;">${message}</span>
                    <span style="color: #3c763d; font-size: 20px;">${success[0]}</span>
                </div>
                <% if (((List<Product>) request.getAttribute("activeProducts")).size() > 0) { %>
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th scope="col">Name</th>
                        <th scope="col">Description</th>
                        <th scope="col">Price</th>
                        <th scope="col"></th>
                        <th scope="col"></th>
                        <th scope="col"></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${activeProducts}" var="product">
                        <tr>
                            <th scope="row">${product.name}</th>
                            <th scope="row">${product.description}</th>
                            <th scope="row">${product.price}</th>
                            <th>
                                <form method="get" action="${pageContext.request.contextPath}/product">
                                    <input type="hidden" value="${product.id}" name="id">
                                    <input type="submit" class="action-button" style="width: 100%" value="Edit">
                                </form>
                            </th>
                            <th>
                                <form method="post" action="${pageContext.request.contextPath}/changeProductStatus">
                                    <input type="hidden" value="${product.id}" name="idProduct">
                                    <input type="hidden" value="1" name="status">
                                    <input type="submit" class="action-button" style="width: 100%" value="Block">
                                </form>
                            </th>
                            <th>
                            <td>
                                <input type="button" onclick="deleteProduct('${product.id}')" class="action-button"
                                       style="width: 100%" value="Delete">
                            </td>
                            </th>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <% } else { %>
                <br>
                <label>There are no active products now.</label>
                <% }%>
            </div>
        </div>
    </div>
</div>
</div>
<div class="container">
    <div class="row card col-centered" style="margin-top: 0">
        <div class="card-profile">
            <div class="col-lg-12 col-md-12 col-sm-12">
                <h4 class="card-title">Disabled Products </h4>
                <div class="boxHidden">
                    <span class="errorMessage" style="color: red; font-size: 20px;">${errors[0]}</span>
                    <span style="color: #3c763d; font-size: 20px;">${success[0]}</span>
                </div>
                <% if (((List<Product>) request.getAttribute("disabledProducts")).size() > 0) { %>
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th scope="col">Name</th>
                        <th scope="col">Description</th>
                        <th scope="col">Price</th>
                        <th scope="col"></th>
                        <th scope="col"></th>
                        <th scope="col"></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${disabledProducts}" var="product">
                        <tr>
                            <th scope="row">${product.name}</th>
                            <th scope="row">${product.description}</th>
                            <th scope="row">${product.price}</th>
                            <td>
                                <form method="get" action="${pageContext.request.contextPath}/product">
                                    <input type="hidden" value="${product.id}" name="id">
                                    <input type="submit" class="action-button" style="width: 100%" value="Edit">
                                </form>
                            </td>
                            <td>
                                <form method="post" action="${pageContext.request.contextPath}/changeProductStatus">
                                    <input type="hidden" value="${product.id}" name="idProduct">
                                    <input type="hidden" value="0" name="status">
                                    <input type="submit" class="action-button" style="width: 100%" value="UnBlock">
                                </form>
                            </td>
                            <td>
                                <input type="button" onclick="deleteProduct('${product.id}')" class="action-button"
                                       style="width: 100%" value="Delete">
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <% } else { %>
                <br>
                <label>There are no disable products now.</label>
                <% }%>
            </div>
        </div>
    </div>
</div>
</div>
<script type="text/javascript">
    function deleteProduct(id) {
        console.log(id);
        $.ajax({
            url: window.location.origin + '/lab3_war_exploded/product?id=' + id,
            type: 'DELETE',
            dataType: 'json',
            success: function (data, textStatus, xhr) {
                window.location.href = '/lab3_war_exploded/';
                console.log(data);
            },
            error: function (xhr, textStatus, errorThrown) {
                window.location.href = '/lab3_war_exploded/';
                console.log('Error in Operation');
            }
        });
    }
</script>
</body>

</html>
