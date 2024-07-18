<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
            <!DOCTYPE html>
            <html lang="en">

            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <link
                    href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@400;600&family=Raleway:wght@600;800&display=swap"
                    rel="stylesheet">

                <!-- Icon Font Stylesheet -->
                <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css" />
                <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css"
                    rel="stylesheet">

                <link rel="stylesheet" href="/client/css/bootstrap.min.css">
                <link rel="stylesheet" href="/client/css/account.css">
                <link href="/client/css/homepage.css" rel="stylesheet">

                <title>Document</title>
            </head>

            <body>
                <jsp:include page="../layout/header.jsp" />

                <div class="container-xl px-5 " style="margin-top: 100px;">
                    <!-- Account page navigation-->
                    <nav class="nav nav-borders">
                        <a class="nav-link  ms-0" href="/manage-account">Profile</a>

                        <a class="nav-link active" href="/change-password">Change Password</a>
                    </nav>
                    <hr class="mt-0 mb-4">
                    <div class="row">
                        <div class="col-xl-4">
                            <!-- Profile picture card-->
                            <div class="card mb-4 mb-xl-0">
                                <div class="card-header">Profile Picture</div>
                                <div class="card-body text-center">
                                    <!-- Profile picture image-->
                                    <img class="img-account-profile mb-2" src="/images/avatar/${currentData.avatar}"
                                        alt="">
                                    <!-- Profile picture help block-->

                                </div>
                            </div>
                        </div>
                        <div class="col-xl-8">
                            <!-- Account details card-->
                            <div class="card mb-4">
                                <div class="card-header">Change-password</div>
                                <div class="card-body">
                                    <form action="/change-password" method="post">
                                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />


                                        <div class="mb-3">
                                            <c:if test="${ not empty errMes}">
                                                <div style="display: block;" id="validationServerUsernameFeedback"
                                                    class="invalid-feedback">
                                                    Password not match</div>
                                            </c:if>
                                            <label for="exampleInputPassword1" class="form-label">Old Password</label>
                                            <input name="oldPassword" type="password"
                                                class="form-control ${ not empty errMes ? 'is-invalid' : ''}"
                                                id="exampleInputPassword1" />
                                        </div>
                                        <div class="mb-3">
                                            <label for="exampleInputPassword1" class="form-label"> New
                                                Password</label>
                                            <input name="newPassword" type="password" class="form-control"
                                                id="exampleInputPassword1" />
                                        </div>

                                        <button class="btn btn-success" type="submit"> Save </button>

                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <jsp:include page="../layout/footer.jsp" />
                <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
                <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
                <script src="client/lib/easing/easing.min.js"></script>
                <script src="client/lib/waypoints/waypoints.min.js"></script>
                <script src="client/lib/lightbox/js/lightbox.min.js"></script>
                <script src="client/lib/owlcarousel/owl.carousel.min.js"></script>
                <script src="/client/js/main2.js"></script>

            </body>

            </html>