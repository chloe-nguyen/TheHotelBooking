
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <title>Booking History Page</title>

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
        <c:if test="${account.role}">
            <c:redirect url="page-404"/>
        </c:if>

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

            <!-- Show list order -->
            <div class="container" style="min-height: 510px">
                <div class="text-center py-3">
                    <img src="img/booking.svg" width="72" height="72"/>
                    <h1 class="h3 mb-3 font-weight-normal">Booking History</h1>
                </div>
                <div class="d-flex justify-content-center py-3 mb-3">
                    <form action="search-order" method="POST">
                        <div class="form-row col-md-12">
                            <div class="col-md-5 mx-2">
                                <input type="text" name="txtOrderId" value="${param.txtOrderId}" placeholder="Enter Order ID" class="form-control"/>
                            </div>
                            <div class="col-md-5 mx-2">
                                <input type="date" name="dateOrder" value="${param.dateOrder}" class="form-control" />
                            </div>
                            <div class="col-md-1">
                                <input type="submit" value="Search" class="btn-primary"/>
                            </div>
                        </div>
                    </form>
                </div>
                <c:if test="${empty param.txtOrderId}">
                    <c:set var="list" value="${sessionScope.CART_LIST}"/>
                    <c:if test="${empty list}">
                        <div class="text-center">
                            <h3>There is no order</h3>
                        </div>
                    </c:if>
                    <c:if test="${not empty list}">
                        <table class="table">
                            <thead>
                                <tr>
                                    <th scope="col">No.</th>
                                    <th scope="col">Order ID</th>
                                    <th scope="col">Order Date</th>
                                    <th scope="col">Status</th>
                                    <th scope="col">Action</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="cartDTO" items="${list}" varStatus="counter">
                                    <tr>
                                        <th scope="row">${counter.count}</th>
                                        <td>${cartDTO.cartId}</td>
                                        <td>${cartDTO.cartDate}</td>
                                        <td>${cartDTO.statusId}</td>
                                        <td>
                                            <c:url var="deleteLink" value="delete-order">
                                                <c:param name="txtCartId" value="${cartDTO.cartId}"/>
                                                <c:param name="txtOrderId" value="${param.txtOrderId}"/>
                                                <c:param name="dateOrder" value="${param.dateOrder}"/>
                                            </c:url>
                                            <a href="${deleteLink}">Delete</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </c:if>
                </c:if>

                <c:if test="${not empty param.txtOrderId}">
                    <c:set var="cartDTO" value="${sessionScope.CART_RESULT}"/>
                    <c:if test="${empty cartDTO}">
                        <div class="text-center">
                            <h3>There is no order</h3>
                        </div>
                    </c:if>
                    <c:if test="${not empty cartDTO}">
                        <table class="table">
                            <thead>
                                <tr>
                                    <th scope="col">No.</th>
                                    <th scope="col">Order ID</th>
                                    <th scope="col">Order Date</th>
                                    <th scope="col">Status</th>
                                    <th scope="col">Action</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <th scope="row">1</th>
                                    <td>${cartDTO.cartId}</td>
                                    <td>${cartDTO.cartDate}</td>
                                    <td>${cartDTO.statusId}</td>
                                    <td>
                                        <c:url var="deleteLink" value="delete-order">
                                            <c:param name="txtCartId" value="${cartDTO.cartId}"/>
                                            <c:param name="txtOrderId" value="${param.txtOrderId}"/>
                                            <c:param name="dateOrder" value="${param.dateOrder}"/>
                                        </c:url>
                                        <a href="${deleteLink}">Delete</a>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </c:if>
                </c:if>
            </div>

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
