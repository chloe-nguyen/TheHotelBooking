
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <title>View Cart Page</title>

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
                        <c:if test="${empty account}">
                            <a class="btn btn-primary" href="login-page">Login</a>
                        </c:if>
                        <c:if test="${not empty account}">
                            <a class="btn btn-primary" href="logout">Logout</a>
                        </c:if>
                    </div>
                </div>
            </nav>

            <!-- List items -->
            <c:set var="cart" value="${sessionScope.CART.cart}"/>
            <c:if test="${empty cart}">
                <c:if test="${requestScope.BOOKING_SUCCESS}">
                    <div class="alert alert-success text-center" role="alert">
                        Booking successfully!!
                    </div>
                </c:if>
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

            <c:if test="${not empty cart}">
                <!-- Header -->
                <div class="index-header px-3 py-3 pt-md-5 pb-md-4 mx-auto text-center">
                    <h1 class="display-4">Your Cart</h1>
                </div>

                <div class="container py-3">
                    <c:set var="hotelMap" value="${sessionScope.HOTEL_MAP}"/>
                    <c:forEach var="item" items="${cart}">
                        <c:set var="room" value="${item.roomDetail}"/>
                        <c:set var="hotel" value="${hotelMap[room.hotelId]}"/>
                        <div class="card mb-3 col-md-12">
                            <div class="row no-gutters">
                                <div class="col-md-5">
                                    <img src="img/${room.image}" class="card-img">
                                </div>
                                <div class="col-md-5">
                                    <div class="card-body">
                                        <h5 class="card-title">${hotel.hotelName}</h5>
                                        <p class="card-text"><img src="img/placeholder.svg" width="14" height="14"> ${hotel.hotelAddress}</p>
                                        <p class="card-text">Type: ${sessionScope.ROOMTYPE_MAP[room.roomTypeId]}</p>
                                        <p class="card-text">Price: $${room.price}</p>
                                        <form action="update">
                                            <input type="hidden" name="txtRoomDetailId" value="${room.roomDetailId}" />
                                            <div class="row">
                                                <label for="inputQuantity" class="col-5 col-form-label">Quantity:</label>
                                                <input id="inputQuantity" class="col-4 form-control" min="1" max="${room.quantity}" required type="number" name="txtQuantity" value="${item.quantity}" onchange="submit()">
                                            </div>
                                            <c:if test="${sessionScope.INVALID_QUANTITY}">
                                                <p class="text-danger">Please input a value from 1 to ${room.quantity}</p>
                                            </c:if>
                                        </form>
                                        <p class="card-text">Total Price: $${item.quantity * room.price} </p>

                                    </div>
                                </div>
                                <div class="col-md-2 col align-self-center">
                                    <form action="remove">
                                        <input type="hidden" name="txtRoomDetailId" value="${room.roomDetailId}" />
                                        <button type="submit" class="btn btn-outline-danger" onclick="return confirm('Do you want to remove this item?')">Remove</button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                    <!-- Table Summary -->

                    <table class="table">
                        <tr>
                            <th scope="row">Total Quantity</th>
                            <td>${sessionScope.CART.totalQuantity}</td>
                        </tr>
                        <tr>
                            <th scope="row">Total Price</th>
                            <td>$${sessionScope.CART.totalPrice}</td>
                        </tr>
                        <tr>
                            <th scope="row">Discount</th>
                            <td>- $${requestScope.DEC}</td>
                        </tr>
                        <tr>
                            <th scope="row">Total Payment</th>
                            <c:if test="${not empty requestScope.DEC}">
                            <td>$${sessionScope.CART.totalPrice - requestScope.DEC}</td>
                            </c:if>
                            <c:if test="${empty requestScope.DEC}">
                                <td>$${sessionScope.CART.totalPrice}</td>
                            </c:if>
                        </tr>
                        <tr>
                            <th scope="row">Discount Code</th>
                            <td>
                                <form action="check-discount">
                                    <input type="text" name="txtDiscountId" value="${param.txtDiscountId}" onchange="submit()"/>
                                </form>
                                <c:if test="${not empty param.txtDiscountId}">
                                    <div class="text-danger">${requestScope.NON_AVAILABLE_DISCOUNT}</div>
                                </c:if>
                            </td>
                        </tr>
                    </table>
                    <form action="confirm" method="GET">
                        <div class="py-3 text-center">
                            <input type="hidden" name="txtDiscountId" value="${param.txtDiscountId}" />
                            <input type="submit" class="btn btn-primary" value="Confirm" />
                        </div>
                    </form>
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
