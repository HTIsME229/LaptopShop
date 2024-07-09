<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
            <!DOCTYPE html>
            <html lang="en">

            <head>
                <meta charset="utf-8" />
                <meta http-equiv="X-UA-Compatible" content="IE=edge" />
                <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
                <meta name="description" content="Hỏi Dân IT - Dự án laptopshop" />
                <meta name="author" content="Hỏi Dân IT" />
                <title>Dashboard - Hỏi Dân IT</title>
                <link href="/css/styles.css" rel="stylesheet" />
                <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
                <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
                <script>
                    $(document).ready(() => {
                        const avatarFile = $("#avatarFile");
                        avatarFile.change(function (e) {
                            const imgURL = URL.createObjectURL(e.target.files[0]);
                            $("#avatarPreview").attr("src", imgURL);
                            $("#avatarPreview").css({ "display": "block" });
                        });
                    });
                </script>
            </head>

            <body class="sb-nav-fixed">
                <jsp:include page="../layout/header.jsp" />
                <div id="layoutSidenav">
                    <jsp:include page="../layout/sidebar.jsp" />
                    <div id="layoutSidenav_content">
                        <main>
                            <div class="container-fluid px-4">
                                <h1 class="mt-4">Create Products</h1>
                                <ol class="breadcrumb mb-4">
                                    <li class="breadcrumb-item"><a href="/admin">Dashboard</a></li>
                                    <li class="breadcrumb-item active">Products</li>
                                </ol>
                                <div class=" mt-3">
                                    <div class="row">
                                        <div class="col-md-6  mx-auto">
                                            <h1>Create A Product </h1>
                                            <hr>
                                            <form:form action="/admin/product/create" method="post"
                                                modelAttribute="newProduct" enctype="multipart/form-data">

                                                <div class="row">
                                                    <div class="mb-3 col">
                                                        <label for="exampleInputEmail1" class="form-label">Name
                                                        </label>
                                                        <c:set var="errName">
                                                            <form:errors path="name" cssClass="invalid-feedback" />
                                                        </c:set>
                                                        <form:input path="name" type="text"
                                                            class="form-control ${not empty errName ? 'is-invalid':''}" />
                                                        ${errName}
                                                    </div>
                                                    <div class="mb-3 col">
                                                        <label for="exampleInputPassword1"
                                                            class="form-label">Price</label>
                                                        <c:set var="errPrice">
                                                            <form:errors path="price" cssClass="invalid-feedback" />
                                                        </c:set>
                                                        <form:input path="price" type="text"
                                                            class="form-control ${not empty errPrice ? 'is-invalid':''}" />
                                                        ${errPrice}
                                                    </div>
                                                </div>
                                                <div class="mb-3 col">
                                                    <label for="" class="form-label">
                                                        Detail Description</label>
                                                    <c:set var="errDesc">
                                                        <form:errors path="detailDesc" cssClass="invalid-feedback" />
                                                    </c:set>
                                                    <form:textarea path="detailDesc" type="text"
                                                        class="form-control ${not empty errDesc ? 'is-invalid':''}" />
                                                    ${errDesc}
                                                </div>
                                                <div class="row">

                                                    <div class="mb-3 col">
                                                        <label for="exampleInputPassword1" class="form-label">Short
                                                            Description:</label>
                                                        <c:set var="errSDesc">
                                                            <form:errors path="shortDesc" cssClass="invalid-feedback" />
                                                        </c:set>
                                                        <form:input path="shortDesc" type="text"
                                                            class="form-control ${not empty errSDesc ? 'is-invalid':''} "
                                                            id="shortDesc" />
                                                        ${errSDesc}
                                                    </div>
                                                    <div class="mb-3 col">
                                                        <label for="exampleInputPassword1"
                                                            class="form-label">Quantity:</label>
                                                        <c:set var="errQuantity">
                                                            <form:errors path="quantity" cssClass="invalid-feedback" />
                                                        </c:set>
                                                        <form:input path="quantity" type="text"
                                                            class="form-control ${not empty errQuantity ? 'is-invalid':''}"
                                                            id="quantity" />
                                                        ${errQuantity}
                                                    </div>
                                                </div>


                                                <div class="row  ">
                                                    <div class="mb-3 col">
                                                        <label for="formFile" class="form-label">Avatar:</label>
                                                        <input class="form-control" type="file" id="avatarFile"
                                                            name="file" />
                                                    </div>
                                                    <div class="mb-3 col">
                                                        <label class="mb-2" for=""> Factory:</label>
                                                        <form:select path="factory" class="form-select "
                                                            aria-label="Default select example">
                                                            <form selected>Select Factory</form>
                                                            <form:option value="APPLE">Appple</form:option>
                                                            <form:option value="SAMSUNG">Samsung</form:option>
                                                        </form:select>

                                                    </div>
                                                    <div class="mb-3 col">
                                                        <label class="mb-2" for=""> Target:</label>
                                                        <form:select path="target" class="form-select "
                                                            aria-label="Default select example">
                                                            <form selected>Select Target</form>
                                                            <form:option value="GAMING">Gaming</form:option>
                                                            <form:option value="MACBOOK">Macbook</form:option>
                                                        </form:select>

                                                    </div>
                                                    <image id="avatarPreview" class="mb-2" style="display: none;">
                                                    </image>
                                                </div>


                                                <button type="submit" class="btn btn-primary">Submit</button>
                                            </form:form>
                                        </div>
                                    </div>



                                </div>
                            </div>
                        </main>
                        <jsp:include page="../layout/footer.jsp" />
                    </div>
                </div>
                <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
                    crossorigin="anonymous"></script>
                <script src="/js/scripts.js"></script>

            </body>

            </html>