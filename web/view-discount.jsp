
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <title>View Discount Page</title>

        <!-- Bootstrap core CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    </head>
    <body>
        <c:set var="account" value="${sessionScope.ACCOUNT}"/>
        <c:if test="${not account.role}">
            <c:redirect url="page-404"/>
        </c:if>

        <c:if test="${account.role}">
            <!-- Navigation -->
            <nav class="navbar navbar-light bg-light static-top">
                <div class="container">
                    <a class="navbar-brand" href="index-page">Booking.vn</a>
                    <div class="my-2 my-md-0 mr-md-3">
                        <a class="btn btn-outline-primary" href="add-discount">Add Discount</a>
                        <a class="btn btn-primary" href="logout">Logout</a>
                    </div>
                </div>
            </nav>

            <!-- Discount Table -->
            <div class="container">
                <div class="text-center py-3">
                    <img src="img/coupon.svg" width="72" height="72"/>
                    <h1 class="h3 mb-3 font-weight-normal">View Discount</h1>
                </div>

                <table class="table">
                    <thead>
                        <tr>
                            <th scope="col">No.</th>
                            <th scope="col">Discount ID</th>
                            <th scope="col">Discount Name</th>
                            <th scope="col">Discount Percent</th>
                            <th scope="col">Create Date</th>
                            <th scope="col">EXP</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:set var="list" value="${requestScope.DISCOUNT_LIST}"/>
                        <c:forEach var="discount" items="${list}" varStatus="counter">
                            <tr>
                                <th scope="row">${counter.count}</th>
                                <td>${discount.discountId}</td>
                                <td>${discount.discountName}</td>
                                <td>${discount.discountPercent}</td>
                                <td>${discount.createDate}</td>
                                <td>${discount.exp}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </c:if>
    </body>
</html>
