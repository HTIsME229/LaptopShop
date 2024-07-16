<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

            <!DOCTYPE html>
            <html lang="en">

            <head>
                <meta charset="utf-8">
                <title>LapTop Shop </title>
                <meta content="width=device-width, initial-scale=1.0" name="viewport">
                <meta content="" name="keywords">
                <meta content="" name="description">

                <!-- Google Web Fonts -->
                <link rel="preconnect" href="https://fonts.googleapis.com">
                <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
                <link
                    href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@400;600&family=Raleway:wght@600;800&display=swap"
                    rel="stylesheet">

                <!-- Icon Font Stylesheet -->
                <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css" />
                <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css"
                    rel="stylesheet">

                <!-- Libraries Stylesheet -->
                <link href="/client/lib/lightbox/css/lightbox.min.css" rel="stylesheet">
                <link href="/client/lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">


                <!-- Customized Bootstrap Stylesheet -->
                <link href="/client/css/bootstrap.min.css" rel="stylesheet">

                <!-- Template Stylesheet -->
                <!-- <link href="/client/css/style.css" rel="stylesheet"> -->
                <link rel="stylesheet" href="/client/css/homepage.css">
                <style>

                </style>
            </head>

            <body>


                <div id="spinner"
                    class="show w-100 vh-100 bg-white position-fixed translate-middle top-50 start-50  d-flex align-items-center justify-content-center">
                    <div class="spinner-grow text-primary" role="status"></div>
                </div>

                <jsp:include page="../layout/header.jsp" />
                <div class="container mx-auto">

                    <main>
                        <div class="container-fluid px-4">
                            <h1 class="mt-4">Manage orderDetails</h1>
                            <ol class="breadcrumb mb-4">
                                <li class="breadcrumb-item"><a href="/admin">Home</a></li>
                                <li class="breadcrumb-item ">Lich Su mua hang</li>
                            </ol>
                            <div class="mt-5">
                                <div class="row">
                                    <div class="col-12 mx-auto">
                                        <div class="d-flex justify-content-between">
                                            <h3>Table Orders</h3>

                                        </div>

                                        <hr />
                                        <table class=" table  table-hover">
                                            <thead>
                                                <tr>
                                                    <th>Sản Phẩm</th>
                                                    <th>Giá cả </th>
                                                    <th>Số Lượng</th>
                                                    <th>Tên</th>
                                                    <th>Thành tiền</th>
                                                    <td rowspan="">
                                                        Trang thai
                                                    </td>
                                                </tr>

                                            </thead>
                                            <tbody>

                                                <c:forEach items="${order}" var="order">
                                                    <tr
                                                        class="alert ${order.status eq  'PENDING' ? 'alert-warning' : order.status eq  'CANCEL' ? 'alert-danger'  :'alert-success' }">
                                                        <td colspan="4">Order Id:${order.id}</td>
                                                        <td>
                                                            <fmt:formatNumber type="number"
                                                                value="${order.toalPrice}" />đ
                                                        </td>
                                                        <td>${order.status}</td>

                                                    </tr>
                                                    <c:forEach items="${order.orderDetails}" var="orderDetails">
                                                        <tr>

                                                            <td> <img style="width: 100px ; height: 100px;"
                                                                    class="img-fluid rounded-circle p-3"
                                                                    src="/images/product/${orderDetails.product.image}"
                                                                    alt="">
                                                            </td>
                                                            <td>
                                                                <fmt:formatNumber type="number"
                                                                    value="${orderDetails.price}" />đ
                                                            </td>
                                                            <td>${orderDetails.quantity}</td>
                                                            <td>${orderDetails.product.name}</td>
                                                            <td>
                                                                <fmt:formatNumber type="number"
                                                                    value="${orderDetails.price *orderDetails.quantity}" />
                                                                đ
                                                            </td>


                                                        </tr>
                                                    </c:forEach>
                                                </c:forEach>

                                            </tbody>
                                        </table>
                                    </div>

                                </div>

                            </div>
                        </div>
                    </main>
                </div>


                <jsp:include page="../layout/feature.jsp" />

                <jsp:include page="../layout/footer.jsp" />



                <!-- JavaScript Libraries -->
                <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
                <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
                <script src="client/lib/easing/easing.min.js"></script>
                <script src="client/lib/waypoints/waypoints.min.js"></script>
                <script src="client/lib/lightbox/js/lightbox.min.js"></script>
                <script src="client/lib/owlcarousel/owl.carousel.min.js"></script>

                <!-- Template Javascript -->
                <script src="/client/js/main2.js"></script>
            </body>

            </html>