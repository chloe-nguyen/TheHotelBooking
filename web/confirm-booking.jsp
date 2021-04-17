
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <title>Confirm Booking Page</title>

        <!-- Bootstrap core CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">

        <style>
            footer.footer {
                padding-top: 4rem;
                padding-bottom: 4rem;
            }
        </style>
    </head>
    <body>
        <c:set var="account" value="${sessionScope.ACCOUNT}"/>

        <!-- If account belongs to the admin -->
        <c:if test="${account.role}">
            <c:redirect url="page_not_found"/>
        </c:if>

        <!-- else -->
        <c:if test="${not account.role}">
            <!-- Navigation -->
            <nav class="navbar navbar-light bg-light static-top">
                <div class="container">
                    <a class="navbar-brand" href="index-page">Booking.vn</a>
                    <div class="my-2 my-md-0 mr-md-3">
                        <a class="btn btn-primary" href="logout">Logout</a>
                    </div>
                </div>
            </nav>

            <c:set var="cart" value="${sessionScope.CART.cart}"/>

            <!-- If empty cart -->
            <c:if test="${empty cart}">
                <div class="index-header px-3 py-3 pt-md-5 pb-md-4 mx-auto text-center">
                    <h1 class="display-4">Your cart is empty</h1>
                </div>
                <div class="container text-center">
                    <img class="mb-2" src="img/empty-cart.svg" alt="" width="300" height="300"><br/>            
                    <div class="mb-5">
                        <a class="p-2 text-primary" href="index-page">Booking now</a>
                    </div>
                </div>
            </c:if>
            
            <!-- If not empty cart -->
            <c:if test="${not empty cart}">
                <!-- Header -->
                <div class="index-header px-3 py-3 pt-md-5 pb-md-4 mx-auto text-center">
                    <h1 class="display-4">Your Order</h1>
                </div>

                <div class="container py-3">
                    
                </div>
            </c:if>
            
            <!-- Footer -->
            <footer class="footer bg-light">
                <div class="container">
                    <div class="row">
                        <div class="col-lg-6 h-100 text-center text-lg-left my-auto">
                            <ul class="list-inline mb-2">
                                <li class="list-inline-item">
                                    <a href="#">About</a>
                                </li>
                                <li class="list-inline-item">&sdot;</li>
                                <li class="list-inline-item">
                                    <a href="#">Contact</a>
                                </li>
                                <li class="list-inline-item">&sdot;</li>
                                <li class="list-inline-item">
                                    <a href="#">Terms of Use</a>
                                </li>
                                <li class="list-inline-item">&sdot;</li>
                                <li class="list-inline-item">
                                    <a href="#">Privacy Policy</a>
                                </li>
                            </ul>
                            <p class="text-muted small mb-4 mb-lg-0">&copy; Booking.vn 2020. All Rights Reserved.</p>
                        </div>
                    </div>
                </div>
            </footer>
        </c:if>
    </body>
</html>
