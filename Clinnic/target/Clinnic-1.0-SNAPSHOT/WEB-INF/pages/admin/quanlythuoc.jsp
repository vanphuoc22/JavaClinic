<%-- 
    Document   : quanlythuoc
    Created on : Aug 14, 2023, 11:48:09 AM
    Author     : hung
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:url value="/admin/quanlythuoc" var="action" />
<sec:authorize access="hasRole('ROLE_ADMIN')"> 
    <script src="<c:url value="/js/FunctionObject.js" />"></script>
    <div class="container">
        <div class="imglogo">
            <img src="https://res.cloudinary.com/dstqvlt8d/image/upload/v1691993662/Red_and_white_pill_icon_on_white_ez3beg.jpg" alt="logo">
            <h2>Tủ thuốc PISCES</h2>
        </div>
        <div class="main">
            <div class = "addMe">
                <form:form method="post" action="${action}" modelAttribute="medicien" enctype="multipart/form-data">
                    <form:hidden path="id" />
                    <div class="form-floating mb-3 mt-3">
                        <div class="form-group">
                            <label for="product_name">Tên thuốc</label>
                            <form:input type="text" class="form-control" path="name" id="product_name" placeholder="Tên thuốc..." />

                        </div>
                    </div>

                    <div class="form-floating mb-3 mt-3">
                        <div class="form-group">
                            <label for="product_price">Giá</label>
                            <form:input type="number" class="form-control" path="price" id="product_price" placeholder="Giá..." />

                        </div>
                    </div>
                            
                    <div class="form-floating mb-3 mt-3">
                        <div class="form-group">
                            <label for="product_quantity">Số lượng</label>
                            <form:input type="number" class="form-control" path="quantity" id="product_quantity" placeholder="Số lượng..." />

                        </div>
                    </div>

                    <div class="form-floating mb-3 mt-3">
                        <form:select class="form-select" id="unit" name="unit" path="idUnit">
                            <c:forEach items="${units}" var="r">
                                <c:choose>
                                    <c:when test="${r.id == medicien.idUnit.id}">
                                        <option value="${r.id}" selected>${r.name}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${r.id}">${r.name}</option>
                                    </c:otherwise>
                                </c:choose>

                            </c:forEach>
                        </form:select>

                        <label for="category" class="form-label">Đơn vị:</label>
                    </div>

                    <div class="form-floating mb-3 mt-3">
                        <div class="form-group">
                            <label for="product_manufacturer">Nhà sản xuất</label>
                            <form:input type="text" class="form-control" path="provider" id="product_manufacturer" placeholder="Nhà sản xuất..." />

                        </div>
                    </div>

                    <div class="form-floating mb-3 mt-3">
                        <div class="form-group">
                            <label for="product_production_date">Ngày sản xuất</label>
                            <form:input type="date" class="form-control" path="productionDate" id="product_production_date" placeholder="Ngày sản xuất..." />

                        </div>
                    </div>

                    <div class="form-floating mb-3 mt-3">
                        <div class="form-group">
                            <label for="product_expiration_date">Ngày hết hạn</label>
                            <form:input type="date" class="form-control" path="expirationDate" id="product_expiration_date" placeholder="Ngày hết hạn..." />

                        </div>
                    </div>
                    <div class="form-floating mb-3 mt-3 bt1">
                        <button class="btn btn-success" type="submit">
                            <c:choose>
                                <c:when test="${medicien.id == null}">Thêm sản phẩm</c:when>
                                <c:otherwise>Cập nhật sản phẩm</c:otherwise>
                            </c:choose>
                        </button>
                    </div>
                </form:form>
            </div>

            <div class = "infoMe">
                <div class = "infoMeSearch" >
                    <form class="search-form" action="${action}">
                        <input class="form-control me-2" type="text" name="name" placeholder="Nhập tên thuốc...">
                        <button class="btn btn-primary" type="submit"><i class="fas fa-search"></i> Tìm</button>
                    </form>
                </div>
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Tên thuốc</th>
                            <th>Số lượng kho</th>
                            <th>Giá</th>
                            <th>Nhà sản xuất</th>
                            <th>Ngày sản xuất</th>
                            <th>Ngày hết hạn</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${getmediciens}" var="ds">
                            <tr>
                                <td>${ds.id}</td>
                                <td>${ds.name}</td>
                                <td>${ds.quantity} ${ds.idUnit.name}</td>
                                <td>${ds.price} VNĐ</td>
                                <td>${ds.provider}</td>
                                <td>${ds.productionDate}</td>
                                <td>${ds.expirationDate}</td>
                                <td class="bt3">
                                    <c:url value="/api/admin/quanlythuoc/${ds.id}" var="apiDel" />
                                    <a href="<c:url value="/admin/quanlythuoc/${ds.id}"/>" class="btn btn-success">Cập nhật</a>
                                    <button class="btn btn-danger" onclick="delObject('${apiDel}', ${ds.id})">Xóa</button>
                                </td>
                            </tr>
                        </c:forEach>

                    </tbody>
                </table>
            </div>

        </div>    
    </div>
</sec:authorize>
<style>
  
.form-floating label {
    font-weight: bold;
    color: #333;
}

.form-floating input.form-control,
.form-floating select.form-control {
    border-radius: 10px;
    border: 1px solid #ccc;
    padding: 10px;
    font-size: 16px;
    color: #555;
}

.form-floating input.form-control::placeholder {
    color: #888;
}

.form-floating input.form-control:focus {
    outline: none;
    border-color: #80bdff;
    box-shadow: 0 0 0 0.2rem rgba(0, 123, 255, 0.25);
}

.container {
    margin: 0 auto;
    padding: 20px;
        margin-left: 50px;
        max-width: 1082px!important;
}

.form-group {
    margin-bottom: 20px;
}

.form-group label {
    display: block;
    font-weight: bold;
}

.form-group input {
    width: 100%;
    padding: 8px;
    border: 1px solid #ccc;
    border-radius: 4px;
}

.btn-add-product {
    background-color: #4CAF50;
    color: white;
    padding: 10px 20px;
    border: none;
    border-radius: 4px;
    cursor: pointer;
}

.btn-add-product:hover {
    background-color: #45a049;
}
.imglogo {
    display: flex;
    align-items: center;
    margin-bottom: 20px;
}
.imglogo img {
    width: 50px;
    height: 50px;
    margin-right: 10px;
}
.imglogo h2 {
    font-size: 24px;
    font-weight: bold;
}
.search-form {
    display: flex;
    align-items: center;
    margin-bottom: 20px;
}
.search-form input[type="text"] {
    flex-grow: 1;
    margin-right: 10px;

}
.table th, .table td {
    vertical-align: middle;
}
.add-product-form {
    margin-bottom: 20px;
}
.add-product-form .form-control {
    width: 300px;
}
.add-product-form .btn {
    margin-top: 10px;
}
.main{
    display: flex;
}
.addMe{
    width: 20%;
}
.infoMe{
    width: 100%;
     
    margin-right: -15%;
    margin-left: 50px;
}
.infoMeSearch{
    display: flex;
}
.submitAddProduct{
    margin-top:30px;
    display: flex;
    justify-content: center;
}
.submitAddProduct a button{
    width: 200px;
    padding: 14px;
    font-size: 14px;
    border: 0px solid #ADD8E6;
    background-color:#87CEFA;
    color: white;
    box-shadow: 0px 5px 10px 0 #ADD8E6;
    transition: 0.3s;
    border-radius: 6px;
}
.bt1{
    padding-left: 30px!important;
}
.bt2{
    background-color: green;
}
.bt3{
    display: flex;
}
td.bt3 a {
    margin-right: 10px;  
}
</style>