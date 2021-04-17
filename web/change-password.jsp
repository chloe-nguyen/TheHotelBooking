
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <title>Change Password Page</title>

        <!-- Bootstrap core CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    </head>
    <body>
        <!-- Navigation -->
        <nav class="navbar navbar-light bg-light static-top">
            <div class="container">
                <a class="navbar-brand" href="index-page">Booking.vn</a>
                <div class="my-2 my-md-0 mr-md-3">
                    <a class="btn btn-primary" href="login-page">Login</a>
                </div>
            </div>
        </nav>

        <div class="container">
            <div class="card py-5 mt-5" style="max-width: 360px; margin: auto;">
                <div class="mb-3 text-center">
                    <h3>Change Password</h3>
                </div>
                <form action="change-password" method="POST" class="mx-3 needs-validation" class="form-reset-pass">
                    <div class="mb-3">
                        <label for="inputEmail"> Email</label>
                        <input id="inputEmail" type="email" name="txtEmail" value="${param.txtEmail}" class="form-control" readonly/>
                    </div>

                    <div class="mb-3">
                        <label for="inputPassword">New Password</label>
                        <input id="inputPassword" type="password" name="txtNewPassword" value="${param.txtNewPassword}" class="form-control" minlength="8" required autofocus/>
                    </div>

                    <div class="mb-4">
                        <label for="inputConfirmPassword">Confirm Password</label>
                        <input id="inputConfirmPassword" type="password" name="txtConfirmPassword" value="" class="form-control" required/>
                        <font color="red">${requestScope.NOT_MATCH}</font>
                    </div>

                    <div class="d-flex justify-content-center">
                        <input type="submit" value="Confirm" class="btn btn-primary" />
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>
