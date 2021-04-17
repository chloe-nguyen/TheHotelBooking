
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <title>Account Recovery Page</title>   
        
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

        <div class="container text-center">
            <div class="card py-5 mt-5" style="max-width: 300px; margin: auto;">
                <p class="mb-3">
                    Enter your email address and we’ll send you a recovery link.
                </p>
                <form action="send-link-reset-pass" method="POST" class="mx-3 needs-validation">
                    <label for="inputEmail"> Email</label><br/>
                    <input id="inputEmail" type="email" name="txtEmail" value="${param.txtEmail}" class="form-control" required autofocus/><br/>
                    <font color="red">${requestScope.INVALID_EMAIL}</font><br/>
                    <input type="submit" value="Send recovery email" class="btn btn-primary" />
                </form>
            </div>
        </div>
    </body>
</html>