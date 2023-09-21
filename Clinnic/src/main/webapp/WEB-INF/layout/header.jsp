<%-- 
    Document   : header
    Created on : Jul 29, 2023, 2:02:16 PM
    Author     : Trinh Bao Duy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:url value="/" var="action" />
<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
    <div class="container-fluid">
        <div class="logo">
            <img src="https://res.cloudinary.com/dstqvlt8d/image/upload/v1692770910/logo-removebg-preview_kiiczs.png" alt="Bệnh viện XYZ Logo">

        </div>
        <a class="navbar-brand" href="#">Pisces Hospital</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#collapsibleNavbar">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="collapsibleNavbar">
            <ul class="navbar-nav me-auto">
                <li class="nav-item">
                    <a class="nav-link" href="${action}">Trang chủ</a>
                </li>
               
                <c:choose>
                    <c:when test="${pageContext.request.userPrincipal.name != null}">
                        <img style="height: 40px; width: auto" src="<c:url value="${user.avatar}"/>" alt="Avatar">
                        <li class="nav-item">
                            <a class="nav-link" href="<c:url value="/" />">${pageContext.request.userPrincipal.name}</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="<c:url value="/logout" />">Đăng xuất</a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="nav-item">
                            <a class="nav-link" href="<c:url value="/login" />">Đăng nhập</a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </div>
    </div>
</nav>

<style>
    .logo img{
        width: 50px;
    }
    .navbar {
        background-color: #d2f6e2!important ;
    }

    .navbar-brand {
        color: #198754 !important;
        font-size: 24px;
        font-weight: bold;
        padding: 10px;

    }

    .navbar-toggler {
        border: none;
    }

/*    .navbar-toggler-icon {
        background-color: #fff;
    }*/

    .navbar-collapse {
        justify-content: flex-end;
        padding-left: 50%;
    }

    .navbar-nav .nav-item {
        margin-right: 10px;
    }

    .navbar-nav .nav-link {
        color: #123653!important;
        font-weight: bold;
    }

/*    .navbar-nav .nav-link:hover {
        color: red;
    }*/

    .form-control {
        border: none;
        border-radius: 20px;
       
    }

    .btn-primary {
        border-radius: 20px;
       
    }

    .btn-primary:hover {
        background-color: #0056b3;
        border-color: #0056b3;
    }
    
</style>