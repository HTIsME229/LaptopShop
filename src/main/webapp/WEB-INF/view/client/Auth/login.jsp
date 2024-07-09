<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
            <!DOCTYPE html>
            <html lang="en">

            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <link rel="stylesheet" href="/client/css/Auth.css" />
                <title>Document</title>
            </head>

            <body>

                <div class="limiter">
                    <div class="container-login100">
                        <div class="wrap-login100">
                            <form acton="/login" method="post" class="login100-form validate-form">
                                <span class="login100-form-title p-b-26">
                                    Welcome
                                </span>
                                <span class="login100-form-title p-b-48">
                                    <i class="zmdi zmdi-font"></i>
                                </span>
                                <c:if test="${param.error != null}">
                                    <div class="my-2" style="color: red;">Invalid email or password.</div>
                                </c:if>
                                <div class="wrap-input100 validate-input" data-validate="Valid email is: a@b.c">
                                    <input class="input100" type="text" name="username">
                                    <span class="focus-input100" data-placeholder="Email"></span>
                                </div>

                                <div class="wrap-input100 validate-input" data-validate="Enter password">
                                    <span class="btn-show-pass">
                                        <i class="zmdi zmdi-eye"></i>
                                    </span>
                                    <input class="input100" type="password" name="password">
                                    <span class="focus-input100" data-placeholder="Password"></span>
                                </div>

                                <div>
                                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                                </div>

                                <div class="container-login100-form-btn">
                                    <div class="wrap-login100-form-btn">
                                        <div class="login100-form-bgbtn"></div>
                                        <button type="submit" class="login100-form-btn">
                                            Login
                                        </button>
                                    </div>
                                </div>


                                <div style="margin-top: 20px;" class="text-center p-t-115">
                                    <span class="txt1">
                                        Don’t have an account?
                                    </span>

                                    <a class="txt2" href="/register">
                                        Sign Up
                                    </a>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>



            </body>

            </html>