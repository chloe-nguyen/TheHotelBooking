
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <title>Booking.vn | The best hotels & accommodations</title>

        <!-- Bootstrap core CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">

        <!-- Custom styles -->
        <link href="css/index.css" rel="stylesheet">
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
                            <a class="btn btn-outline-primary" href="view-booking-history">Booking History</a>
                        </c:if>  
                        <a class="btn btn-primary" href="logout">Logout</a>
                    </c:if>
                </div>
            </div>
        </nav>

        <!-- Masthead -->
        <header class="masthead text-white text-center">
            <div class="overlay"></div>
            <div class="container">
                <div class="row">
                    <div class="col-xl-9 mx-auto">
                        <h1 class="mb-5">Welcome ${account.fullname}</h1>
                    </div>
                    <div class="col-md-12 mx-auto">
                        <form action="search" method="GET">
                            <div class="form-row">
                                <div class="col-12 col-md-2 mb-2 mb-md-0">
                                    <select name="cboRegion" class="form-control form-control-lg">
                                        <option selected>Region</option>
                                        <c:forEach var="region" items="${sessionScope.REGION_MAP}">
                                            <option <c:if test="${param.cboRegion eq region.value}">selected</c:if> >
                                                ${region.value}
                                            </option>
                                        </c:forEach>
                                    </select>
                                </div>

                                <div class="col-12 col-md-2 mb-2 mb-md-0">
                                    <select name="cboRoomType" class="form-control form-control-lg">
                                        <option selected>Type of Rooms</option>
                                        <c:forEach var="roomType" items="${sessionScope.ROOMTYPE_MAP}">
                                            <option <c:if test="${param.cboRoomType eq roomType.value}">selected</c:if> >
                                                ${roomType.value}
                                            </option>
                                        </c:forEach>
                                    </select>
                                </div>

                                <div class="col-12 col-md-3 mb-2 mb-md-0">
                                    <input type="date" name="dateCheckin" value="${param.dateCheckin}" min="${sessionScope.TODAY}" class="form-control form-control-lg" required/>
                                </div>

                                <div class="col-12 col-md-3 mb-2 mb-md-0">
                                    <input type="date" name="dateCheckout" value="${param.dateCheckout}" min="${sessionScope.TODAY}" class="form-control form-control-lg" required/>
                                </div>

                                <div class="col-12 col-md-2">
                                    <button type="submit" class="btn btn-block btn-lg btn-primary">Search</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </header>

        <c:set var="hotelMap" value="${sessionScope.HOTEL_MAP}"/>

        <!-- Show All Hotels -->
        <c:if test="${empty requestScope.SEARCH_RESULT && empty param.dateCheckin}">
            <div class="container py-3">
                <div class="card-deck">
                    <c:forEach var="hotel" items="${hotelMap}">
                        <div class="card mb-3 col-lg-4">
                            <img src="img/${hotel.value.hotelImage}" class="card-img-top">
                            <div class="card-body">
                                <c:url var="detailLink" value="detail-hotel">
                                    <c:param name="txtHotelId" value="${hotel.value.hotelId}"/>
                                </c:url>
                                <h5 class="card-title"><a href="${detailLink}">${hotel.value.hotelName}</a></h5>
                                <p class="card-text"><img src="img/placeholder.svg" width="14" height="14"> ${hotel.value.hotelAddress}</p>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </c:if>

        <!-- Show search results -->
        <c:set var="list" value="${requestScope.SEARCH_RESULT}"/>
        <c:if test="${not empty list}">
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
                                        <input type="hidden" name="page" value="index" />
                                        <input type="hidden" name="cboRegion" value="${param.cboRegion}" />
                                        <input type="hidden" name="cboRoomType" value="${param.cboRoomType}" />                                       
                                        <input type="hidden" name="dateCheckin" value="${param.dateCheckin}" />                                       
                                        <input type="hidden" name="dateCheckout" value="${param.dateCheckout}" />                                       
                                        <c:if test="${not account.role}">
                                            <input type="submit" class="btn btn-primary" value="Select room" />
                                        </c:if>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </c:if>

        <c:if test="${empty list && not empty param.dateCheckin}">
            <div class="container">
                <div class="text-center py-5">
                    <h1>No room available</h1>
                </div>
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
