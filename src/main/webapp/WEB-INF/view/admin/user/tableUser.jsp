<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
            <!DOCTYPE html>
            <html lang="en">

            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
                <!-- Latest compiled JavaScript -->
                <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
                <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
                <link rel="stylesheet" href="/css/table.css">
                <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
                <title>Document</title>
            </head>

            <body>

                <jsp:include page="../layout/header.jsp" />
                <div class="d-flex " style="height: 100vh;">
                    <jsp:include page="../layout/sidebar.jsp" />
                    <div class="container mt-5 ">
                        <div class=" d-flex justify-content-between">
                            <h1>Table User</h1>
                            <button class="btn btn-primary"> <a href="user/create">Create a User
                                </a></button>

                        </div>

                        <div class="row cl-md-6 mx-auto">


                            <table class="table table-hover">

                                <thead>
                                    <tr>
                                        <th scope="col">Id</th>
                                        <th scope="col">Email</th>
                                        <th scope="col">PassWord</th>
                                        <th scope="col">FullName</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${arrUsers}" var="item">

                                        <tr>
                                            <th scope="row">${item.id}</th>
                                            <td>${item.email}</td>
                                            <td>${item.password}</td>
                                            <td>${item.fullName}</td>
                                            <td><a href="/admin/user/${item.id}" class="btn btn-primary">
                                                    View </a>
                                                <button class="btn btn-success"><a
                                                        href="/admin/user/update/${item.id}">Update</a></button>
                                                <button class="btn btn-warning"><a
                                                        href="/admin/user/delete/${item.id}">Delete</a></button>
                                            </td>
                                        </tr>
                                    </c:forEach>

                                </tbody>
                            </table>
                        </div>

                    </div>
                </div>



                <jsp:include page="../layout/footer.jsp" />

                <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
                    crossorigin="anonymous"></script>
                <script src="/js/scripts.js"></script>
                <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js"
                    crossorigin="anonymous"></script>
                <script src="/js/chart-area-demo.js"></script>
                <script src="/js/chart-bar-demo.js"></script>
                <script src="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/umd/simple-datatables.min.js"
                    crossorigin="anonymous"></script>
                <script src="/js/dx`atatables-simple-demo.js"></script>
            </body>

            </html>