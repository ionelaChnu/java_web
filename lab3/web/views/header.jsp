<%@ page import="model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<nav class="navbar navbar-default navbar-fixed-top" style="background-color: rgba(141,178,183,0.1)">
    <div class="container">
        <div class="col-md-12">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="<c:url value = "/"/>"><img src="img/logo.png"
                                                                         class="img-responsive-my"
                                                                         style="width: 90px; margin-top: -16px;"></a>
            </div>
            <div class="collapse navbar-collapse navbar-right" id="myNavbar">
                <ul class="nav navbar-nav">
                    <% if (session.getAttribute("currUser") == null) {%>
                    <li class=""><a href="<c:url value = "/registration"/>">Registration</a></li>
                    <li class=""><a href="<c:url value = "/signIn"/>">Sign in</a></li>
                    <%}%>
                    <% User user = (User) session.getAttribute("currUser");
                        if (user != null) {
                            if (user.getAuthority().toString().equals(String.valueOf("CUSTOMER"))) {%>
                    <li><a href="<c:url value = "/userProducts"/>">Products</a></li>
                    <li class="dropdown" style="    min-width: 180px;">
                        <a href="" class="dropdown-toggle"
                           data-toggle="dropdown" role="button" aria-haspopup="true"
                           aria-expanded="false">Orders<span class="caret"></span></a>
                        <ul class="myMenu dropdown-menu">
                            <li><a href="<c:url value = "/orders"/>" class="myLi">My Orders</a></li>
                            <li role="separator" class="divider"></li>
                            <li><a href="<c:url value="/cart"/>" class="myLi">Make an Order</a></li>
                        </ul>
                    </li>
                    <li class="dropdown">
                        <a href="" class="dropdown-toggle"
                           data-toggle="dropdown" role="button" aria-haspopup="true"
                           aria-expanded="false">${currUser.firstName} ${currUser.lastName}<span
                                class="caret"></span></a>
                        <ul class="myMenu dropdown-menu">
                            <li><a href="<c:url value = "/customer"/>" class="myLi">Profile</a></li>
                            <li role="separator" class="divider"></li>
                            <li><a href="<c:url value="/logout"/>" class="myLi">Log out</a></li>
                        </ul>
                    </li>
                    <li>
                        <a href="<c:url value = "/cart"/>">
                            <div style="
                                          width:30px;
                                          height:30px;
                                          position: relative;">
                                <img src="img/shopping-cart-solid.svg" alt="none" width="100%" height="100%">
                                <div style="    background-color:red;
                                                width:18px !important;
                                                height:18px !important;
                                                text-align: center;
                                                font-size:10px;
                                                position: absolute;
                                                padding-right:3px;
                                                padding-left:3px;
                                                top: -10px;
                                                left: 23px;
                                                border-radius: 25px;">
                                    ${cart.count}
                                </div>
                            </div>
                        </a>
                    </li>
                    <%
                        }
                        if (user.getAuthority().toString().equals(String.valueOf("ADMIN"))) {
                    %>
                    <li><a href="<c:url value = "/users"/>">Users</a></li>
                    <li class="dropdown">
                        <a href="" class="dropdown-toggle"
                           data-toggle="dropdown" role="button" aria-haspopup="true"
                           aria-expanded="false">Products<span class="caret"></span></a>
                        <ul class="dropdown-menu myMenu">
                            <li><a href="<c:url value = "/products"/>" class="myLi">All Products</a></li>
                            <li role="separator" class="divider"></li>
                            <li><a href="<c:url value = "/product"/>" class="myLi">Add Product</a></li>
                        </ul>
                    </li>
                    <li><a href="<c:url value="/logout"/>" class="myLi">Log out</a></li>
                    <%}%>
                    <%}%>
                </ul>
            </div>
        </div>
    </div>
</nav>
