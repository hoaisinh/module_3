

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css">
    <title>Teku shop</title>
</head>
<body>
<header>
    <div class="row w-100 navBar m-0">
        <div class="col-2 logo">
            <img src="uploads/image/logo.png"  alt="logo">
        </div>
        <div class="col-8  menu" >
            <a class="" href="http://localhost:8080">Home</a>
            <a class="" href="${pageContext.request.contextPath}/list-clothes">Danh mục</a>
            <a class="" href="${pageContext.request.contextPath}/about-us">About Us</a>
        </div>
        <div class="col-2 loginDiv">
            <c:choose>
                <c:when test="${not empty user}">
                    <span>Hello, ${user.username}</span>
                    <a href="${pageContext.request.contextPath}/logout"><i class="bi bi-box-arrow-right"></i></a>
                </c:when>
                <c:otherwise>
                    <a href="${pageContext.request.contextPath}/login"><i class="bi bi-person-fill-add"></i></a>
                </c:otherwise>
            </c:choose>

        </div>
    </div>
    <div class="headerBanner">
        <img src="uploads/image/shutterstock_2424932097.png" alt="">
    </div>
</header>
