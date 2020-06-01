<%@ page import="model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Profile</title>
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
                    <img id="logo" class="svg img" src="img/id-card-solid.svg">
                </div>
            </div>
            <div class="col-lg-7 col-md-8 col-sm-8">
                <div class="boxHidden">
                    <span class="errorMessage" style="color: red">${error[0]}</span>
                    <span class="errorMessage" style="color: red">${errors}</span>
                </div>
                <h4 class="card-title">${currUser.firstName} ${currUser.lastName}</h4>
                <hr class="hrProfile">
                <div class="row">
                    <div class="col-md-6">
                        <label><i class="fas fa-star-half-alt"> Login </i></label>
                    </div>
                    <div class="col-md-6">
                        <p class="pp">${currUser.login}</p>
                    </div>
                </div>
            </div>
            <div class="col-lg-2 col-md-2 col-sm-2">
                <a href="" class="action-button modalUpd" data-toggle="modal" data-target="#modalUpdateForm">Update</a>
                <a href="" class="action-button " data-toggle="modal" data-target="#modalChangeForm">Password</a>
                <a href="" class="action-button " data-toggle="modal" data-target="#modalDeleteForm">Delete</a>
            </div>
        </div>
    </div>
</div>
</div>

<div class="modal fade" id="modalUpdateForm" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header text-center">
                <h4 class="modal-title w-100 font-weight-bold">Update profile</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <form method="post" action="${pageContext.request.contextPath}/customer">
                <div class="modal-body mx-3">
                    <div class="md-form mb-5">
                        <div class="group">
                            <input type="text" class="putName" name="first_name" id="fName" value="${currUser.firstName}"
                                   pattern="^[A-Z][a-z]{2,14}$" required>
                            <span class="highlight"></span>
                            <span class="bar"></span>
                            <label data-error="wrong" data-success="right" for="fName">First name</label>
                        </div>
                    </div>
                    <div class="md-form mb-5">
                        <div class="group">
                            <input type="text" class="putName" name="last_name" id="lName" value="${currUser.lastName}"
                                   pattern="^[A-Z][a-z]{2,14}$" required>
                            <span class="highlight"></span>
                            <span class="bar"></span>
                            <label data-error="wrong" data-success="right" for="lName">Last name</label>
                        </div>
                    </div>
                    <div class="md-form mb-5">
                        <div class="group">
                            <input type="text" class="putName" name="login" id="login" value="${currUser.login}"
                                   pattern="^[a-zA-Z][a-zA-Z0-9\\-\\_\\.]{1,18}[a-zA-Z0-9]$" required>
                            <span class="highlight"></span>
                            <span class="bar"></span>
                            <label data-error="wrong" data-success="right" for="login">Login</label>
                        </div>
                    </div>
                </div>
                <div class="modal-footer d-flex justify-content-center">
                    <input type="submit" class="action-button" value="Update" style="width: 30%">
                </div>
            </form>
        </div>
    </div>
</div>

<div class="modal fade" id="modalChangeForm" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header text-center">
                <h4 class="modal-title w-100 font-weight-bold">Change password</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <form method="post" action="${pageContext.request.contextPath}/changePassword">
                <div class="modal-body mx-3">
                    <div class="md-form mb-5">
                        <div class="group">
                            <input type="password" class="putName" id="currPass" name="currentPass" required>
                            <span class="highlight"></span>
                            <span class="bar"></span>
                            <label data-error="wrong" data-success="right" for="currPass">Current password</label>
                        </div>
                    </div>
                    <div class="md-form mb-5">
                        <div class="group">
                            <input type="password" class="putName" name="newPass" id="newPass" required>
                            <span class="highlight"></span>
                            <span class="bar"></span>
                            <label data-error="wrong" data-success="right" for="newPass">New password</label>
                        </div>
                    </div>
                    <div class="md-form mb-5">
                        <div class="group">
                            <input type="password" class="putName" name="newConfPass" id="newConfPass" required>
                            <span class="highlight"></span>
                            <span class="bar"></span>
                            <label data-error="wrong" data-success="right" for="newConfPass">Confirm new
                                password</label>
                        </div>
                    </div>
                </div>
                <div class="modal-footer d-flex justify-content-center">
                    <input type="submit" class="action-button" value="Change" style="width: 30%">
                </div>
            </form>
        </div>
    </div>
</div>

<div class="modal fade" id="modalDeleteForm" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header text-center">
                <h4 class="modal-title w-100 font-weight-bold">Delete profile</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <label>Are you sure you want to delete your account?</label>
            </div>
            <form method="post" onsubmit="deleteUser(#currentPass)">
                <div class="modal-body mx-3">
                    <div class="md-form mb-5">
                        <div class="group">
                            <input type="password" class="putName" name="currentPass" id="currentPass" required>
                            <span class="highlight"></span>
                            <span class="bar"></span>
                            <label data-error="wrong" data-success="right" for="currentPass">Current password</label>
                        </div>
                    </div>
                </div>
                <div class="modal-footer d-flex justify-content-center">
                    <input type="submit" class="action-button"
                           style="width: 45%" value="Yes">
                    <input type="button" class="action-button" data-dismiss="modal" style="width: 45%" value="NO">
                </div>
            </form>
        </div>
    </div>
</div>


<script>
    function deleteUser(currentPass) {
        console.log(currentPass);
        $.ajax({
            url: window.location.origin + '/lab3_war_exploded/customer?currentPass=' + currentPass,
            type: 'DELETE',
            dataType: 'json',
            success: function (data, textStatus, xhr) {
                console.log(data);
            },
            error: function (xhr, textStatus, errorThrown) {
                console.log('Error in Operation');
            }
        });
    }

    setTimeout(function () {
        $('.boxHidden').fadeOut('fast')
    }, 3000);

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

