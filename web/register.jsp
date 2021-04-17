
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <title>Registration Page</title>

        <!-- Bootstrap core CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">

        <!-- Custom styles -->
        <link href="css/register.css" rel="stylesheet">
    </head>
    <body class="text-center">
        <div class="card">
            <div class="align-items-center">
                <img class="mb-4" src="img/building.svg" alt="" width="72" height="72">
            </div>
            <h1 class="h3 mb-3 font-weight-normal">Booking.vn</h1>

            <form action="register" method="POST" class="form-register needs-validation" novalidate>
                <!-- Email Address field -->
                <div class="mb-3">
                    <label for="inputEmail" class="sr-only">email address</label>
                    <input type="email" name="txtEmail" value="${param.txtEmail}" id="inputEmail" class="form-control" placeholder="email address" required autofocus/>
                    <font color="red">${requestScope.DUPLICATED}</font>
                    <div class="invalid-feedback">
                        Please enter a valid email address.
                    </div>
                </div>

                <!-- Password field -->
                <div class="mb-3">
                    <label for="inputPassword" class="sr-only">password</label>
                    <input type="password" name="txtPassword" value="${param.txtPassword}" id="inputPassword" class="form-control" placeholder="password" minlength="8" required/>
                    <div class="invalid-feedback">
                        Password must have at least 8 characters.
                    </div>
                </div>
                
                <!-- Confirm Password field -->
                <div class="mb-3">
                    <label for="inputConfirmPassword" class="sr-only">confirm password</label>
                    <input type="password" name="txtConfirmPassword" value="${param.txtConfirmPassword}" id="inputConfirmPassword" class="form-control" placeholder="confirm password" required/>
                    <font color="red">${requestScope.NOT_MATCH}</font>
                    <div class="invalid-feedback">
                        Confirm password is required.
                    </div>
                </div>
                    
                <!-- Full name field -->
                <div class="mb-3">
                    <label for="inputFullName" class="sr-only">full name</label>
                    <input type="text" name="txtFullName" value="${param.txtFullName}" id="inputFullName" class="form-control" placeholder="full name" required/>
                    <div class="invalid-feedback">
                        Please enter your full name.
                    </div>
                </div>

                <!-- Phone Number field -->
                <div class="mb-3">
                    <label for="inputPhoneNumber" class="sr-only">phone number</label>
                    <input type="tel" pattern="[0-9]{10}" name="txtPhoneNumber" value="${param.txtPhoneNumber}" id="inputPhoneNumber" class="form-control" placeholder="phone number" required/>
                    <div class="invalid-feedback">
                        Please enter your phone number in the correct format 0123456789.
                    </div>
                </div>

                <!-- Address field -->
                <div class="mb-3">
                    <label for="inputAddress" class="sr-only">address</label>
                    <input type="text" name="txtAddress" value="${param.txtAddress}" id="inputAddress" class="form-control" placeholder="address"/>
                </div>

                <!-- Button submit -->
                <input class="btn btn-lg btn-primary btn-block" type="submit"/>

                <div class="py-3">
                    Back to <a href="index-page">Home Page</a>
                </div>
            </form>
        </div>

        <!-- jQuery first, then Popper.js, then Bootstrap JS -->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>

        <!-- Custom JS -->
        <script src="js/form-validation.js"></script>

    </body>
</html>
