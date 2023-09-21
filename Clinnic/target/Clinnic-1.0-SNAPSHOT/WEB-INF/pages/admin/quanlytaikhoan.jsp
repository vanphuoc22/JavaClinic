<%-- 
    Document   : quanlitaikhoan
    Created on : Aug 15, 2023, 1:27:23 PM
    Author     : hung
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:url value="/admin/quanlytaikhoan" var="action" />
<sec:authorize access="hasRole('ROLE_ADMIN')"> 
    <script src="<c:url value="/js/FunctionObject.js" />"></script>
    <div class="container">
        <h1>Trang quản lý tài khoản</h1>
        <h2>Tra cứu</h2>
        <div class="infoMeSearch">
            <form class="search-form" action="${action}">
                <div class="search-container">
                    <input class="form-control me-2" type="text" name="name" placeholder="Nhập tên người dùng..." />
                    <button class="btn btn-primary btsearch" type="submit"><i class="fas fa-search"></i> Tìm</button>
                    <div class="input1"><a class="input" href="<c:url value="/admin/quanlytaikhoan/themtaikhoan"/>">THÊM TÀI KHOẢN</a></div>
                </div>
            </form>
        </div>
        <c:if test="${counter > 1}">
            <ul class="pagination mt-1">
                <li class="page-item"><a class="page-link" href="<c:url value="/admin/quanlytaikhoan" />">Tất cả</a></li>
                    <c:forEach begin="1" end="${counter}" var="i">
                        <c:url value="/admin/quanlytaikhoan" var="pageUrl">
                            <c:param name="page" value="${i}"></c:param>
                        </c:url>
                    <li class="page-item"><a class="page-link" href="${pageUrl}">${i}</a></li>
                    </c:forEach>
            </ul>
        </c:if>
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Avatar</th>
                    <th>Username</th>
                    <th>Họ và tên</th>
                    <th>Chức vụ</th>
                    <th>Số điện thoại</th>
                    <!--                    <th>Địa chỉ</th>
                                        <th>Email</th>-->
                    <th>Ngày sinh</th>
                    <th>Giới tính</th>
                    <th>       
                    </th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${user}" var="u">
                    <tr>
                        <td>${u.id}</td>
                        <td><img style="height: 40px; width: auto" src="<c:url value="${u.avatar}"/>" alt="Avatar"></td>
                        <td>${u.username}</td>
                        <td>${u.name}</td>
                        <td>${u.roleId.name.substring(5)}</td>
                        <td>${u.phone}</td>
<!--                        <td>${u.address}</td>
                        <td>${u.emaill}</td>-->
                        <td><fmt:formatDate value="${u.dod}" pattern="dd/MM/yyyy" /></td>
                        <td>${u.sex} </td>
                        <td> <c:url value="/api/${u.id}" var="apiDel" />
                            <a href="<c:url value="/admin/quanlytaikhoan/themtaikhoan/${u.id}"/>" class="btn btn-success">Cập nhật</a>
                            <button class="btn btn-danger" onclick="delObject('${apiDel}', ${u.id})">Xóa</button></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

    </div>
</sec:authorize>

<style>
    .container {

        /*        margin: 30px auto;
                padding: 20px;
                border: 0.5px solid #5AAC4E;
                border-radius: 30px;
                overflow: auto;
          max-height: 400px;*/
        /*text-align: center;*/

    }

    h1 {
        text-align: center;
    }
    thead>tr>th{
        text-align: center;
    }
    tr>td{
        text-align: center;
    }

    table {

        border-collapse: collapse;
        margin-top: 20px;
        margin-bottom: 40px;
        table-layout: fixed;


    }
    thead {
        color: white;

    }
    th, td {
        padding: 10px;
        text-align: left;
        border-bottom: 1px solid #ddd;
        white-space: nowrap;
        width: 20%;
        word-break: break-all;

    }


    th {
        background-color: #198754;
        ;
    }
    .input{
        background-color: #66af69;

        color: white;
        padding: 12px 12px;
        border: none;
        border-radius: 10px;
        cursor: pointer;
        width: 100%;
        margin-top: 50px;
        margin-left: 660px;
        text-decoration: none;
    }
    .search-container {
        display: flex;
        align-items: center;
    }

    .search-form input {
        margin-right: 5px;

        width: 20%;
        border:0.5px;
        border: 0.5px solid #5AAC4E;
    }
    .btsearch{
        background-color: #099956;
        border-color: #099956;
    }

</style>


