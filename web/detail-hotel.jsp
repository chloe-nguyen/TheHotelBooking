
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <title>Detail Hotel Page</title>

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

        <!-- Navigation -->
        <nav class="navbar navbar-light bg-light static-top">
            <div class="container">
                <a class="navbar-brand" href="index-page">Booking.vn</a>
                <div class="my-2 my-md-0 mr-md-3">
                    <c:if test="${empty account}">
                        <a class="btn btn-outline-primary" href="view-cart-page">Cart</a>
                        <a class="btn btn-primary" href="login-page">Login</a>
                    </c:if>
                    <c:if test="${not empty account}">
                        <c:if test="${account.role}">
                            <a class="btn btn-outline-primary" href="add-discount">Add Discount</a>
                        </c:if>
                        <c:if test="${not account.role}">
                            <a class="btn btn-outline-primary" href="view-cart-page">Cart</a>
                        </c:if>  
                        <a class="btn btn-primary" href="logout">Logout</a>
                    </c:if>
                </div>
            </div>
        </nav>

        <!-- Show list of rooms by hotelId -->
        <c:set var="list" value="${sessionScope.LIST_ROOM_BY_HOTEL}"/>
        <c:if test="${empty list}">
            <div class="container text-center py-5" style="height: 510px">
                <img src="img/sorry.svg" width="120" height="120"/>
                <h1>There is no available room</h1>
                <a href="index-page">Home Page</a>
            </div>
        </c:if>

        <c:if test="${not empty list}">
            <c:set var="hotelMap" value="${sessionScope.HOTEL_MAP}"/>
            <c:set var="hotelDto" value="${sessionScope.HOTEL_DTO}"/>
            <div class="container py-5 text-center">
                <h1>${hotelDto.hotelName}</h1>
                <h4><img src="img/gmail.svg" width="24" height="24"> ${hotelDto.hotelEmail}</h4>
                <h4><img src="img/phone.svg" width="24" height="24"> ${hotelDto.hotelPhoneNumber}</h4>
            </div>
            <div class="container py-3">
                <c:forEach var="room" items="${list}">
                    <c:set var="hotel" value="${hotelMap[room.hotelId]}"/>
                    <div class="card mb-3 col-md-12">
                        <div class="row no-gutters">
                            <div class="col-md-5">
                                <img src="img/${room.image}" class="card-img">
                            </div>
                            <div class="col-md-4">
                                <div class="card-body">
                                    <h5 class="card-title">${hotel.hotelName}</h5>
                                    <p class="card-text"><img src="img/placeholder.svg" width="14" height="14"> ${hotel.hotelAddress}</p>
                                    <p class="card-text">Type: ${sessionScope.ROOMTYPE_MAP[room.roomTypeId]}</p>
                                    <p class="card-text">Price: $${room.price}</p>
                                    <p class="card-text">Quantity: ${room.quantity} <small class="text-muted">rooms</small></p>
                                    <form action="add-to-cart" method="POST">
                                        <input type="hidden" name="txtId" value="${room.roomDetailId}" />
                                        <input type="hidden" name="txtQuantity" value="${room.quantity}" />
                                        <input type="hidden" name="txtPrice" value="${room.price}" />
                                        <input type="hidden" name="txtImage" value="${room.image}" />
                                        <input type="hidden" name="txtTypeId" value="${room.roomTypeId}" />
                                        <input type="hidden" name="txtHotelId" value="${room.hotelId}" />
                                        <input type="hidden" name="txtStatusId" value="${room.statusId}" />
                                        <input type="hidden" name="page" value="detail-hotel" />
                                        <input type="hidden" name="txtHotelId" value="${hotelDto.hotelId}" />
                                        <c:if test="${not account.role}">
                                            <input type="submit" class="btn btn-primary" value="Select room" />
                                        </c:if>
                                    </form>
                                </div>
                            </div>
                            <div class="col-md-3">

                            </div>
                        </div>
                    </div>
                </c:forEach>
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
    </body>
</html>
