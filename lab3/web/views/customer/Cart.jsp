<%@ page import="model.dto.CartDTO" %>
<%@ page import="model.dto.ProductDTO" %>
<%@ page import="model.Product" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Cart</title>
    <script src="js/jquery.min.js"></script>
    <script src="js/jquery.easing.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/custom.js"></script>

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
    <div class="row card cardWidth col-centered">
        <div class="card-profile">
            <div class="col-lg-3 col-md-2 col-sm-2">
                <div class="card-avatar">
                    <img id="logo" class="svg img" src="img/shopping-cart-solid.svg">
                </div>
            </div>
            <div class="col-lg-9 col-md-10 col-sm-10">
                <h4 class="card-title">My cart</h4>
                <hr class="hrProfile">
                <% if (((CartDTO) request.getAttribute("cartInfo")).getProducts().size() > 0) { %>
                <a href="" class="action-button " data-toggle="modal" style="width: 100%" data-target="#modal">Complete
                    purchase</a>
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th scope="col">Name</th>
                        <th scope="col">Quantity</th>
                        <th scope="col">Price</th>
                        <th scope="col">Total</th>
                        <th scope="col"></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${cartInfo.products}" var="product">
                        <tr>
                            <th scope="row">${product.name}</th>
                            <th scope="row">${product.quantity}</th>
                            <th scope="row">${product.price}</th>
                            <th scope="row">${product.totalPrice}</th>
                            <td>
                                <form method="post" action="${pageContext.request.contextPath}/cart">
                                    <input type="hidden" value="${product.id}" name="prodId">
                                    <input type="hidden" value="1" name="status">
                                    <input type="submit" class="action-button" style="text-align: center;"
                                           value="Delete">
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <div class="row">
                    <div class="col-md-6">
                        <label><i class="fas fa-star-half-alt"> Total </i></label>
                    </div>
                    <div class="col-md-6">
                        <p class="pp">${cartInfo.total}</p>
                    </div>
                </div>
                <% } else { %>
                <br>
                <label>Your cart is empty.</label>
                <a href="<c:url value="/userProducts"/>" class="action-button" style="width: 100%">Make a purchase!</a>
                <% }%>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header text-center">
                <h4 class="modal-title w-100 font-weight-bold">Complete purchase</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body mx-3">
                <div class="md-form mb-5">
                    <h4 class="card-title">Would you like to pay for the purchase now or
                        later?</h4>
                </div>
            </div>
            <div class="modal-footer d-flex justify-content-center">
                <input type="submit" onclick="completeOrder()" class="action-button" style="width: 45%" value="Yes">
                <input type="button" class="action-button" data-dismiss="modal" style="width: 45%" value="NO">
            </div>
        </div>
    </div>
</div>

<script>
    function completeOrder() {
        $.ajax({
            url: window.location.origin + '/lab3_war_exploded/completeOrder',
            type: 'POST',
            dataType: 'json',
            success: function (data, textStatus, xhr) {
                console.log(data);
                window.location.href = '/lab3_war_exploded/';
            },
            error: function (xhr, textStatus, errorThrown) {
                window.location.href = '/lab3_war_exploded/';
            }
        });
    }

    jQuery('img.svg').each(function () {
        var $img = jQuery(this);
        var imgID = $img.attr('id');
        var imgClass = $img.attr('class');
        var imgURL = $img.attr('src');

        jQuery.get(imgURL, function (data) {
            var $svg = jQuery(data).find('svg');
            if (typeof imgID !== 'undefined') {
                $svg = $svg.attr('id', imgID);
            }
            if (typeof imgClass !== 'undefined') {
                $svg = $svg.attr('class', imgClass + ' replaced-svg');
            }
            $svg = $svg.removeAttr('xmlns:a');
            $img.replaceWith($svg);

        }, 'xml');

    });

</script>
</body>

</html>

