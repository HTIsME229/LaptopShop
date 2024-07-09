<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
            <!DOCTYPE html>
            <!-- Website - www.codingnepalweb.com -->
            <html lang="en">

            <head>
                <meta charset="UTF-8" />
                <meta name="description"
                    content=" Today in this blog you will learn how to create a responsive Login & Registration Form in HTML CSS & JavaScript. The blog will cover everything from the basics of creating a Login & Registration in HTML, to styling it with CSS and adding with JavaScript." />
                <meta name="keywords" content=" 
 Animated Login & Registration Form,Form Design,HTML and CSS,HTML CSS JavaScript,login & registration form,login & signup form,Login Form Design,registration form,Signup Form,HTML,CSS,JavaScript,
" />

                <meta name="viewport" content="width=device-width, initial-scale=1.0" />

                <title>Login & Signup Form HTML CSS | CodingNepal</title>

                <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
                    integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
                    crossorigin="anonymous"></script>
                <link href="/css/styles.css" rel="stylesheet" />
                <link rel="stylesheet" href="/client/css/Auth.css" />


            </head>

            <body>
                <section class="wrapper">
                    <div class="form signup">
                        <header>Signup</header>
                        <form:form action="/register" method="post" modelAttribute="newRegister">

                            <div class="row">
                                <div class="col">
                                    <c:set var="errFirstName">
                                        <form:errors path="firstName" cssClass="invalid-feedback" />
                                    </c:set>
                                    <form:input class="form-control ${not empty errFirstName ? 'is-invalid':''}"
                                        path="firstName" type="text" placeholder="First name" />
                                    ${errFirstName}
                                </div>
                                <div class="col">
                                    <c:set var="errLastName">
                                        <form:errors path="lastName" cssClass="invalid-feedback" />
                                    </c:set>
                                    <form:input class="form-control ${not empty errLastName ? 'is-invalid':''}"
                                        path="lastName" type="text" placeholder="Last name" />
                                    ${errFirstName}
                                </div>

                            </div>
                            <div class="col">
                                <c:set var="errEmail">
                                    <form:errors path="email" cssClass="invalid-feedback" />
                                </c:set>

                                <form:input class=" form-control ${not empty errEmail ? 'is-invalid':''}" path="email"
                                    type="text" placeholder="Email address" />
                                ${errEmail}
                            </div>


                            <div class="row">
                                <div class="col">
                                    <c:set var="errPassword">
                                        <form:errors path="confirmPassword" cssClass="invalid-feedback" />
                                    </c:set>
                                    <form:input class=" form-control ${not empty errPassword ? 'is-invalid':''} "
                                        path="password" type="password" placeholder="Password" />
                                    ${errPassword}
                                </div>

                                <div class="col">
                                    <form:input class="form-control" path="confirmPassword" type="password"
                                        placeholder="confirmPassword" />
                                </div>


                            </div>

                            <div class="checkbox">
                                <input type="checkbox" id="signupCheck" />
                                <label for="signupCheck">I accept all terms & conditions</label>
                            </div>


                            <input type="submit" value="Signup" />
                        </form:form>
                    </div>



                    <script>
                        const wrapper = document.querySelector(".wrapper"),
                            signupHeader = document.querySelector(".signup header"),
                            loginHeader = document.querySelector(".login header");

                        loginHeader.addEventListener("click", () => {
                            wrapper.classList.add("active");
                        });
                        signupHeader.addEventListener("click", () => {
                            wrapper.classList.remove("active");
                        });
                    </script>
                </section>
            </body>

            </html>