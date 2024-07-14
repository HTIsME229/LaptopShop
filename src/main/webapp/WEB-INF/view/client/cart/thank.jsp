<%@page contentType="text/html" pageEncoding="UTF-8" %>

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
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">

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
        <div class="container mx-auto" style="margin-top: 150px;">
            <h1 class="thank alert alert-success text-center   ">Cảm Ơn Bạn Đã Đặt Hàng

            </h1>
            <div class="mt-4 text-center ">
                <i class="fas fa-arrow-left "></i>
                <a href="/">Quay lại trang chủ</a>
            </div>
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