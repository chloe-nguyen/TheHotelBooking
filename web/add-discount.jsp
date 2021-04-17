
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <title>Add Discount Page</title>

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
        <c:if test="${not account.role}">
            <c:redirect url="page-404"/>
        </c:if>

        <c:if test="${account.role}">
            <!-- Navigation -->
            <nav class="navbar navbar-light bg-light static-top">
                <div class="container">
                    <a class="navbar-brand" href="index-page">Booking.vn</a>
                    <div class="my-2 my-md-0 mr-md-3">
                        <a class="btn btn-primary" href="logout">Logout</a>
                    </div>
                </div>
            </nav>

            <!-- Form Add Discount -->
            <div class="container" style="max-width: 500px;">

                <div class="text-center py-3">
                    <img src="img/coupon.svg" width="72" height="72"/>
                    <h1 class="h3 mb-3 font-weight-normal">Add Discount</h1>
                </div>
                <div class="card mb-5">
                    <form action="add-discount" class="mx-3 mt-3" method="POST">
                        <div class="mb-3">
                            <div class="form-row mb-3">
                                <label for="inputId">ID</label>
                                <input type="text" name="txtId" value="${param.txtId}" maxlength="20" class="form-control" id="inputId" required/>
                                <font color="red">${requestScope.DUPPLICATED_ID}</font>
                            </div> 
                            <div class="form-row mb-3">
                                <label for="inputName">Name</label>
                                <input type="text" name="txtName" value="${param.txtName}" class="form-control" id="inputName" required/>
                            </div> 
                            <div class="form-row mb-3">
                                <label for="inputPercent">Percent</label>
                                <input type="number" name="numPercent" value="${param.numPercent}" min="0" max="100" class="form-control" id="inputPercent" required/>
                            </div> 
                            <div class="form-row mb-3">
                                <label for="inputExp">EXP</label>
                                <input type="date" name="dateExp" value="${param.dateExp}" min="${sessionScope.TODAY}" class="form-control" id="inputExp" required/>
                            </div> 

                            <div class="text-center">
                                <input type="submit" value="Add" class="btn btn-primary" />
                            </div>
                        </div>
                    </form>
                </div>
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
