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
                <h1 class="mt-4">Create Users</h1>
                <ol class="breadcrumb mb-4">
                  <li class="breadcrumb-item"><a href="/admin">Dashboard</a></li>
                  <li class="breadcrumb-item active">Users</li>
                </ol>
                <div class=" mt-3">
                  <div class="row">
                    <div class="col-md-6  mx-auto">
                      <h1>Create A User</h1>
                      <hr>
                      <form:form action="/admin/user/create" method="post" modelAttribute="newUser"
                        enctype="multipart/form-data">
                        <div class="row">
                          <div class="mb-3 col">
                            <label for="exampleInputEmail1" class="form-label">Email address</label>
                            <c:set var="errEmail">
                              <form:errors path="email" />
                            </c:set>

                            <form:input path="email" type="email"
                              class="form-control ${ not empty errEmail ?'is-invalid':''}" id="exampleInputEmail1"
                              aria-describedby="emailHelp" />
                            <form:errors cssClass="invalid-feedback" path="email"></form:errors>
                          </div>
                          <div class="mb-3 col">
                            <label for="exampleInputPassword1" class="form-label">Password</label>
                            <c:set var="errPassword">
                              <form:errors path="password" />
                            </c:set>
                            <form:input path="password" type="password"
                              class="form-control ${not empty errPassword ? 'is-invalid':''}"
                              id="exampleInputPassword1" />

                            <form:errors cssClass="invalid-feedback" path="password"></form:errors>
                          </div>
                        </div>
                        <div class="row">
                          <div class="mb-3 col">
                            <label for="exampleInputPassword1" class="form-label">Phone number:</label>
                            <form:input path="phone" type="text" class="form-control" id="phone" />
                          </div>
                          <div class="mb-3 col">
                            <label for="exampleInputPassword1" class="form-label">Full Name:</label>
                            <c:set var="errFullName">
                              <form:errors path="fullName" />
                            </c:set>
                            <form:input path="fullName" type="text"
                              class="form-control ${ not empty errFullName ?'is-invalid':''}" id="fullName" />
                            <form:errors cssClass="invalid-feedback" path="fullName"></form:errors>
                          </div>
                        </div>

                        <div class="mb-3">
                          <label for="exampleInputPassword1" class="form-label">Address</label>
                          <form:input path="address" type="text" class="form-control" id="address" />
                        </div>
                        <div class="row  ">
                          <div class="mb-3 col">
                            <label for="formFile" class="form-label">Avatar:</label>
                            <input class="form-control" type="file" id="avatarFile" name="file" />
                          </div>
                          <div class="mb-3 col">
                            <label class="mb-2" for=""> Role:</label>
                            <form:select path="role.name" class="form-select " aria-label="Default select example">
                              <form selected>Select Role</form>
                              <form:option value="ADMIN">Admin</form:option>
                              <form:option value="USER">User</form:option>
                            </form:select>

                          </div>
                          <image id="avatarPreview" class="mb-2" style="display: none;"></image>
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