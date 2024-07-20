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
                    .information-shop {
                        grid-template-columns: repeat(3, 320px);
                    }
                </style>
            </head>

            <body>

                <!-- Spinner Start -->
                <div id="spinner"
                    class="show w-100 vh-100 bg-white position-fixed translate-middle top-50 start-50  d-flex align-items-center justify-content-center">
                    <div class="spinner-grow text-primary" role="status"></div>
                </div>
                <!-- Spinner End -->


                <!-- Navbar start -->
                <jsp:include page="../layout/header.jsp" />
                <!-- Navbar End -->


                <!-- Modal Search Start -->
                <div class="container-fluid fruite py-5 mt-4 mx-auto">
                    <div class="position-relative mt-5 ">
                        <input class="form-control border-2 border-secondary w-75 py-3 px-4 rounded-pill mx-auto search"
                            type="text" placeholder="Search">
                        <a href=""
                            class="btn btn-primary border-2 border-secondary py-3 px-4 position-absolute rounded-pill text-white h-100 "
                            id="search_button" style="top: 0; right: 12%;">Search Now</a>
                    </div>
                    <div class="container py-5">
                        <div class="row">
                            <div class="col-4 col-md-3">
                                <div class="row g-4">
                                    <div class="col-12">
                                        <div class="mb-2"><b>Hãng sản xuất</b></div>
                                        <div class="form-check form-check-inline">
                                            <input class="form-check-input factory" type="checkbox" id="factory-1"
                                                value="APPLE">
                                            <label class="form-check-label" for="factory-1">Apple</label>
                                        </div>
                                        <div class="form-check form-check-inline">
                                            <input class="form-check-input factory" type="checkbox" id="factory-2"
                                                value="ASUS">
                                            <label class="form-check-label" for="factory-2">Asus</label>
                                        </div>

                                        <div class="form-check form-check-inline">
                                            <input class="form-check-input factory" type="checkbox" id="factory-3"
                                                value="LENOVO">
                                            <label class="form-check-label" for="factory-3">Lenovo</label>
                                        </div>

                                        <div class="form-check form-check-inline">
                                            <input class="form-check-input factory" type="checkbox" id="factory-4"
                                                value="DELL">
                                            <label class="form-check-label" for="factory-4">Dell</label>
                                        </div>
                                        <div class="form-check form-check-inline">
                                            <input class="form-check-input factory" type="checkbox" id="factory-5"
                                                value="LG">
                                            <label class="form-check-label" for="factory-5">LG</label>
                                        </div>
                                        <div class="form-check form-check-inline">
                                            <input class="form-check-input factory" type="checkbox" id="factory-6"
                                                value="ACER">
                                            <label class="form-check-label" for="factory-6">Acer</label>
                                        </div>

                                    </div>
                                    <div class="col-12">
                                        <div class="mb-2"><b>Mục đích sử dụng</b></div>
                                        <div class="form-check form-check-inline">
                                            <input class="form-check-input target" type="checkbox" id="target-1"
                                                value="GAMING">
                                            <label class="form-check-label" for="target-1">Gaming</label>
                                        </div>

                                        <div class="form-check form-check-inline">
                                            <input class="form-check-input target" type="checkbox" id="target-2"
                                                value="SINHVIEN-VANPHONG">
                                            <label class="form-check-label" for="target-2">Sinh viên - văn
                                                phòng</label>
                                        </div>
                                        <div class="form-check form-check-inline">
                                            <input class="form-check-input target" type="checkbox" id="target-3"
                                                value="THIET-KE-DO-HOA">
                                            <label class="form-check-label" for="target-3">Thiết kế đồ
                                                họa</label>
                                        </div>
                                        <div class="form-check form-check-inline">
                                            <input class="form-check-input target" type="checkbox" id="target-4"
                                                value="MONG-NHE">
                                            <label class="form-check-label" for="target-4">Mỏng nhẹ</label>
                                        </div>
                                        <div class="form-check form-check-inline">
                                            <input class="form-check-input target" type="checkbox" id="target-5"
                                                value="DOANH-NHAN">
                                            <label class="form-check-label" for="target-5">Doanh nhân</label>
                                        </div>


                                    </div>
                                    <div class="col-12">
                                        <div class="mb-2"><b>Mức giá</b></div>

                                        <div class="form-check form-check-inline">
                                            <input class="form-check-input price_range" type="radio" id="price-2"
                                                value="duoi-10-trieu" name="price">
                                            <label class="form-check-label" for="price-2">Dưới 10 triệu</label>
                                        </div>

                                        <div class="form-check form-check-inline">
                                            <input class="form-check-input price_range" type="radio" id="price-3"
                                                value="10-15-trieu" name="price">
                                            <label class="form-check-label" for="price-3">Từ 10 - 15
                                                triệu</label>
                                        </div>

                                        <div class="form-check form-check-inline">
                                            <input class="form-check-input  price_range" type="radio" id="price-4"
                                                value="15-20-trieu" name="price">
                                            <label class="form-check-label" for="price-4">Từ 15 - 20
                                                triệu</label>
                                        </div>

                                        <div class="form-check form-check-inline">
                                            <input class="form-check-input price_range" type="radio" id="price-5"
                                                value="tren-20-trieu" name="price">
                                            <label class="form-check-label" for="price-5">Trên 20 triệu</label>
                                        </div>
                                    </div>
                                    <div class="col-12">
                                        <div class="mb-2"><b>Sắp xếp</b></div>

                                        <div class="form-check form-check-inline">
                                            <input class="form-check-input sort" type="radio" id="sort-1"
                                                value="gia-tang-dan" name="radio-sort">
                                            <label class="form-check-label" for="sort-1">Giá tăng dần</label>
                                        </div>

                                        <div class="form-check form-check-inline">
                                            <input class="form-check-input sort" type="radio" id="sort-2"
                                                value="gia-giam-dan" name="radio-sort">
                                            <label class="form-check-label" for="sort-2">Giá giảm dần</label>
                                        </div>

                                        <div class="form-check form-check-inline">
                                            <input class="form-check-input sort" type="radio" id="sort-3"
                                                value="gia-nothing" name="radio-sort">
                                            <label class="form-check-label" for="sort-3">Không sắp xếp</label>
                                        </div>

                                    </div>
                                    <div class="col-12">
                                        <a href="" id="filter"
                                            class="btn border-secondary rounded-pill px-4 py-3 text-primary text-uppercase mb-4">
                                            Lọc Sản Phẩm
                                        </a>
                                    </div>
                                </div>
                            </div>
                            <div class=" col-8 col-md-9  tab-class text-center">

                                <div class="tab-content">
                                    <div id="tab-1" class="tab-pane fade show p-0 active">

                                        <div class="col-lg-12 ">
                                            <c:if test="${not empty arrProduct}">
                                                <div class="information information-shop">
                                                    <c:forEach items="${arrProduct}" var="item">

                                                        <div class=" item border border-secondary border-top-0 rounded-bottom "
                                                            style="padding: 0px 0px;">
                                                            <div class="rounded position-relative fruite-item">
                                                                <div class="fruite-img">
                                                                    <img src="/images/product/${item.image}"
                                                                        class="img-fluid w-100 rounded-top" alt="">
                                                                </div>
                                                                <div class="text-white bg-secondary px-3 py-1 rounded position-absolute"
                                                                    style="top: 10px; left: 10px;">Laptop</div>
                                                                <div class="  p-4 tech ">
                                                                    <h4> <a href="/product/${item.id}">${item.name}</a>
                                                                    </h4>
                                                                    <p class=" shortDesc ">${item.shortDesc}</p>
                                                                    <div
                                                                        class="action d-flex justify-content-between  align-middle">
                                                                        <p class="text-dark fs-5 fw-bold mb-0">$/
                                                                            <fmt:formatNumber type="number"
                                                                                value="${item.price}" />đ

                                                                        </p>
                                                                        <form action="/add-product-to-cart/${item.id}"
                                                                            method="post">
                                                                            <input type="hidden"
                                                                                name="${_csrf.parameterName}"
                                                                                value="${_csrf.token}" />

                                                                            <button
                                                                                class="btn border border-secondary rounded-pill px-3 text-primary"><i
                                                                                    class="fa fa-shopping-bag me-2 text-primary"></i>
                                                                                Add to cart</button>
                                                                        </form>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </c:forEach>


                                                </div>
                                            </c:if>
                                            <c:if test="${empty arrProduct}">
                                                <h1 class="alert my-5">Not Found Product </h1>
                                            </c:if>

                                        </div>
                                    </div>
                                </div>
                                <nav aria-label="Page navigation example" class="mt-3">
                                    <ul class="pagination justify-content-center d-flex">
                                        <c:if test="${currentPage ne 0}">
                                            <li class="page-item">
                                                <a class="page-link" href="/products?page=${currentPage}${qs}"
                                                    aria-label="Previous">
                                                    <span aria-hidden="true">&laquo;</span>
                                                </a>
                                            </li>
                                        </c:if>

                                        <c:forEach var="i" begin="1" end="${totalPage}">
                                            <li class="page-item">
                                                <a class="page-link ${i == currentPage + 1  ? 'active':''}"
                                                    href="/products?page=${i}${qs}">
                                                    ${i}
                                                </a>
                                            </li>
                                        </c:forEach>
                                        <c:if test="${currentPage +1 lt totalPage}">
                                            <li class="page-item">
                                                <a class="page-link" href="/products?page=${currentPage+2}${qs}"
                                                    aria-label="Next">
                                                    <span aria-hidden="true">&raquo;</span>
                                                </a>
                                            </li>
                                        </c:if>

                                    </ul>
                                </nav>
                            </div>
                        </div>

                    </div>

                </div>
                <!-- Modal Search End -->


                <!-- Hero Start -->

                <!-- Hero End -->


                <!-- Featurs Section Start -->


                <!-- Featurs Section End -->


                <!-- Laptop Shop Start-->

                <!-- Laptop Shop End-->


                <!-- Featurs Start -->

                <!-- Featurs End -->


                <!-- Vesitable Shop Start-->

                <!-- Vesitable Shop End -->


                <!-- Banner Section Start-->

                <!-- Banner Section End -->


                <!-- Bestsaler Product Start -->

                <!-- Bestsaler Product End -->


                <!-- Fact Start -->

                <!-- Fact Start -->


                <!-- Tastimonial Start -->

                <!-- Tastimonial End -->


                <!-- Footer Start -->
                <jsp:include page="../layout/footer.jsp" />

                <!-- Copyright End -->



                <!-- Back to Top -->
                <a href="/" class="btn btn-primary border-3 border-primary rounded-circle back-to-top"><i
                        class="fa fa-arrow-up"></i></a>


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