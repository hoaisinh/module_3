

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css">
    <script src="https://code.jquery.com/jquery-3.7.1.slim.js" integrity="sha256-UgvvN8vBkgO0luPSUl2s8TIlOSYRoGFAX4jlCIm9Adc=" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    <title>Teku shop</title>
</head>
<body>
<header>
    <div class="row w-100 navBar m-0">
        <div class="col-2 logo">
            <img src="${pageContext.request.contextPath}/uploads/image/logo.png"  alt="logo">
        </div>
        <div class="col-8  menu" >
            <a class="" href="http://localhost:8080">Home</a>
            <a class="" href="${pageContext.request.contextPath}/list-clothes">Something</a>
            <a class="" href="${pageContext.request.contextPath}/about-us">About Us</a>
        </div>
        <div class="col-2 loginDiv">
            <c:choose>
                <c:when test="${not empty user}">
                    <span>Hello, ${user.username} <i class="bi bi-caret-down"></i></span>
                    <div class="hidden-menu">
                        <c:if test="${user.role == 'customer'}">
                            <a href="${pageContext.request.contextPath}/buy-clothing">Purchare History</a>
                            <a href="${pageContext.request.contextPath}/logout">Logout</a>
                        </c:if>
                        <c:if test="${user.role == 'admin' || user.role == 'editor'}">
                            <a href="${pageContext.request.contextPath}/admin">Admin Page</a>
                            <a href="${pageContext.request.contextPath}/logout">Logout</a>
                        </c:if>
                    </div>

                </c:when>
                <c:otherwise>
                    <a href="${pageContext.request.contextPath}/login">Login</a>|
                    <a href="${pageContext.request.contextPath}/register">Register</a>
                </c:otherwise>
            </c:choose>

        </div>
    </div>
    <div class="headerBanner">
        <img src="${pageContext.request.contextPath}/uploads/image/shutterstock_2424932097.png" alt="">
    </div>
</header>
