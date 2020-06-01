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
                <h4 class="card-title">Products </h4>
                <div class="boxHidden">
                    <span class="errorMessage" style="color: red; font-size: 20px;">${message}</span>
                    <span style="color: #3c763d; font-size: 20px;">${success[0]}</span>
                </div>
                <% if (((List<Product>) request.getAttribute("products")).size() > 0) { %>
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
                    <c:forEach items="${products}" var="product">
                        <tr>
                            <th scope="row">${product.name}</th>
                            <th scope="row">${product.description}</th>
                            <th scope="row">${product.price}</th>
                            <th>
                                <a href=""
                                   class="action-button modalUpd"
                                   data-toggle="modal"
                                   data-target="#modalForm"
                                   data-product-id="${product.id}"
                                   data-product-name="${product.name}"
                                   data-product-price="${product.price}"
                                >Add to cart</a>
                            </th>
                            </th>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <% } else { %>
                <br>
                <label>There are no products now.</label>
                <% }%>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="modalForm" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header text-center">
                <h4 class="modal-title w-100 font-weight-bold">Add product to cart</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <form method="post" action="${pageContext.request.contextPath}/addToCart">
                <div class="modal-body mx-3">
                    <input type="text" class="putName" name="pId" id="pId" required hidden>
                    <input type="text" class="putName" name="pPrice1" id="pPrice1" required hidden>
                    <div class="md-form mb-5">
                        <div class="group" style="white-space:nowrap">
                            <span class="highlight"></span>
                            <input type="text" class="putName" name="pName" id="pName" readonly>
                        </div>
                    </div>
                    <div class="md-form mb-5">
                        <div class="group">
                            <input type="number" min="1" class="putName" name="pQuantity" id="pQuantity" required>
                            <span class="highlight"></span>
                            <span class="bar"></span>
                            <label data-error="wrong" data-success="right" for="pQuantity" >Quantity</label>
                        </div>
                    </div>
                    <div class="md-form mb-5">
                        <div class="group">
                            <label style="display:inline;" for="result">Total price: <span class="bar" id="result" ></span> </label>
                        </div>
                    </div>
                </div>
                <div class="modal-footer d-flex justify-content-center">
                    <input type="submit" class="action-button" value="Add" style="width: 30%">
                </div>
            </form>
        </div>
    </div>
</div>

</div>
</div>
<script>
    //triggered when modal is about to be shown
    $('#modalForm').on('show.bs.modal', function (e) {
        //get data-id attribute of the clicked element
        var id = $(e.relatedTarget).data('product-id');
        var name = $(e.relatedTarget).data('product-name');
        var price = $(e.relatedTarget).data('product-price');
        //populate the textbox
        $(e.currentTarget).find('input[name="pName"]').val(name);
        $(e.currentTarget).find('input[name="pId"]').val(id);
        $(e.currentTarget).find('input[name="pPrice1"]').val(price);
    });

    var inputQ = document.getElementById('pQuantity');
    inputQ.oninput = function() {
        console.info(inputQ.value);
        document.getElementById('result').innerHTML = inputQ.value * document.getElementById('pPrice1').value;
    };
</script>
</body>

</html>
