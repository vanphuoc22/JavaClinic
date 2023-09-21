<%-- 
   Document   : kethuoc
   Created on : Aug 23, 2023, 12:24:04 AM
   Author     : hung
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:url value="/doctor/khambenh/kethuoc" var="action" />
<c:url value="/doctor/khambenh/kethuoc/taohoahon" var="actionhd" />
<!DOCTYPE html>
<sec:authorize access="hasRole('ROLE_DOCTOR')">
    <script src="<c:url value="/js/FunctionObject.js" />"></script>
    <div class="infor1">
        <div class="contentdkk2_main contentdkk2_main1">
            <div class="imglogo">
                <img src=" https://res.cloudinary.com/dstqvlt8d/image/upload/v1692731869/zyro-image_ukyhu5.png" alt="logo">
                <h2>Toa Thuốc</h2>
            </div>
            <hr>
            <nav class="bookingright ">
                <div class="dkk2">
                    <c:if test="${msg != null}">
                        <div class="loi">
                            <h3>${msg}</h3>
                        </div>
                    </c:if>
                    <div class="contentdkk3  "  >

                        <div class='content1'>
                            <h2>Tra cứu</h2>
                            <div class = "infoMeSearch" >
                                <form class="search-form" action="${action}">
                                    <input class="form-control me-2" type="text"   name="name" placeholder="Nhập tên thuốc..."/>
                                    <button class="btn btn-primary" type="submit"><i class="fas fa-search"></i> Tìm</button>
                                </form>
                            </div>
                            <table class="table table-striped">
                                <!--<input type="hidden" name="PreId" value="${appo.id}" />-->
                                <thead>
                                    <tr>

                                        <th>Tên thuốc</th>                             
                                        <th>Nhà sản xuất</th>
                                        <th>Ngày hết hạn</th>
                                        <th>Đơn vị</th>
                                        <th> </th>

                                    </tr>

                                </thead>
                                <tbody>
                                    <c:forEach items="${getmediciens}" var="ds">
                                        <tr>
                                            <td>${ds.name}</td>
                                            <td>${ds.provider}</td>
                                            <td>${ds.expirationDate}</td>
                                            <td>${ds.quantity} ${ds.idUnit.name}</td>
                                            <!--                                            <button onclick="showQuantityInput()">Thêm</button>                                           -->
                                            <td>            
                                                <button class="showOverlayButton" >Thêm</button>


                                                <div class="overlay" style="display: none">


                                                    <form:form modelAttribute="phieuthuoc" method="post" action="${action}">
                                                        <form:hidden path="id" />
                                                        <form:hidden path="medicineId" value="${ds.id}"/>
                                                        <form:hidden path="prescriptionId"/>
                                                        <div class="overlay-content">

                                                            <a class="huy" href="<c:url value="/doctor/khambenh/kethuoc/${appo.id}" />">X</a>

                                                            <input name="PreId" value="${appo.id}" type="hidden"/>
                                                            <h1>${ds.id}-${ds.name}</h1>
                                                            <div>
                                                                <label for="quantity">Số lượng:</label>
                                                                <form:input type="number"  path="quantity" placeholder="Nhập số lượng ${ds.idUnit.name} của ${ds.name}..."/>
                                                            </div>
                                                            <div>
                                                                <label for="quantity">Hướng dẫn sử dụng:</label>
                                                                <form:input type="text"  path="instructions" />
                                                            </div>
                                                            <button class="addButton2" type="submit">Thêm</button>
                                                        </div>
                                                    </form:form>
                                                </div>
                                            </td>

                                        </c:forEach>


                                </tbody>
                            </table>
                        </div>


                        <div class="content2">
                            <h2> Toa Thuốc</h2>

                            <table class="table table-striped">
                                <thead>
                                    <tr>

                                        <th>Tên thuốc</th>                             
                                        <th>Số Lượng</th>
                                        <th>Hướng dẫn sử dụng</th>
                                        <th></th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${dsthuoc}" var="t">
                                        <tr>
                                            <td>${t.medicineId.name}</td>
                                            <td>${t.quantity}-${t.medicineId.idUnit.name}</td>
                                            <td>${t.instructions}</td>
                                            <c:url value="/api/doctor/khambenh/kethuoc/${t.id}" var="apiDel" />
                                            <td><button class="btn btn-danger" onclick="delObject('${apiDel}', ${t.id})">Xóa</button></td>
                                        </tr>
                                    </c:forEach>
                                </tbody>

                            </table>
                        </div> 

                    </div>


                </div>
            </nav>
            <div class="submitbooking ">
                <button class="btsearch2">
                    <a href="<c:url value="/doctor/khambenh/kethuoc/export/${appo.id}"/>">XUẤT TOA THUỐC</a>
                </button>

            </div>
            <!--            <div class="submitbooking ">
                            <button class="btsearch2" onclick="hoi()">NEXT</button>
                        </div>-->
            <div >
                <form:form id="taohoadon"  method="post" modelAttribute="hoadonmoi" action="${actionhd}">
                    <form:hidden path="id" />
                    <form:hidden path="payMoney"/>
                    <form:hidden path="appoId" value="${appo.id}" />
                    <form:hidden path="payId" />
                    <form:hidden path="tienkham" value="1"/>
                    <input type="hidden" name="PreId" value="${appo.id}" />
                    <div class="submitbooking " >
                        <button class="btsearch2">Tạo hóa đơn và rời khỏi </button>
                    </div>
                </form:form>
            </div>
        </div>
        </form>
    </div>

</sec:authorize>
<script>
    // JavaScript
    var showOverlayButtons = document.getElementsByClassName("showOverlayButton");
    var overlays = document.getElementsByClassName("overlay");

    for (var i = 0; i < showOverlayButtons.length; i++) {
        showOverlayButtons[i].addEventListener("click", function () {
            var button = this;
            var overlay = button.nextElementSibling;

            overlay.style.display = "block";

        });
    }

    window.addEventListener("DOMContentLoaded", function () {
        // Ẩn tất cả các trang đè khi form được tải
        for (var k = 0; k < overlays.length; k++) {
            overlays[k].style.display = "none";
        }
    });

    function hoi() {
        if (confirm("Bạn đã hoàn thành việc khám?")) {
            window.location.href = "<c:url value='/doctor'/>";

        } else {
            window.location.href = "<c:url value='/doctor/khambenh/kethuoc/${appo.id}'/>";
        }
    }

</script>

<style>

    .loi {
        background-color: #ffdddd;
        padding: 10px;
        margin-top: 10px;
        text-align: center;
        border-radius: 10px;
    }

    .loi h3 {
        color: #ff0000;
        font-size: 16px;
    }


    CSS cho trang đè
    .overlay {
        display: none;
        /*Ẩn trang đè ban đầu*/
        position: fixed;
        top: 0;
        left: 0;
        width: 100%;
        height: 100%;
        background-color: rgba(0, 0, 0, 0.5);
        justify-content: center;
        align-items: center;
        z-index: 9999;
        pointer-events: none;
        opacity: 1px;
    }

    .overlay-content {
        background-color: #fff;
        padding: 20px;
        border-radius: 5px;
        max-width: 400px;
        /*Độ rộng tối đa của trang đè*/
        width: 80%;
        /*Độ rộng thực tế của trang đè*/
        position: absolute;
        top: 50%;
        left: 50%;
        transform: translate(-50%, -50%);
    }

    .overlay-content label,
    .overlay-content input,
    .overlay-content button {
        display: block;
        margin-bottom: 10px;
    }

    .overlay-content button {
        background-color: #4CAF50;
        color: #fff;
        border: none;
        padding: 10px 20px;
        border-radius: 5px;
        cursor: pointer;
    }
    .infor1{
        /*display: flex;*/
        padding: 30px;
        border: 0.5px solid #5AAC4E;
        border-radius: 30px;
        margin: 30px;
        background-color: #F5FFFA;
        position: relative;
        z-index: 1;

    }
    .add{
        width: 10%;
    }
    hr {
        border: none;
        border-top: 3px solid #ccc;
    }
    .add button{
        margin: 20px;
        width: 70px;
    }
    .content1{
        width: 70%;
        padding-left: 30px;
        border: 0.5px solid #5AAC4E;
        border-radius: 30px;
        padding: 20px;


    }
    .content2{
        width: 70%;
        padding-left: 30px;
        border: 0.5px solid #5AAC4E;
        border-radius: 30px;
        padding: 20px;
    }
    .showOverlayButton{

        background-color: #4CAF50;
        color: #fff;
        border: none;
        padding: 10px 20px;
        border-radius: 5px;
        cursor: pointer;

    }
    .dkk1{

        display: flex;
        justify-content:  space-between;
        /*padding: 50px;*/
        border: 0.5px solid #5AAC4E;
        border-radius: 30px;
    }
    .imglogo{
        display: flex;

    }
    .imglogo img{
        width: 50px;
    }
    .imglogo h2{
        padding: 0px;
        margin: 0px;
    }
    /*    .dkk2 h2{
            text-align: center;
    
        }
        .dkk2 div {
    
    
        }*/
    .contentdkk2{
        margin: 20px;
    }
    .booking1{
        width: 30%;
        /*        width: 300px;
                height: 300px;*/
        /*        display: flex;*/
        justify-content: center;
        align-items: center;
        overflow: hidden;
        width: 20%;
    }
    .booking1 img{
        padding: 5px;
        /* border-radius: 20px; */
        border-radius: 50px;
        width: 100%;

        object-fit: cover;
    }
    .form-select{
        border-radius: 10px;
    }


    .contentdkk5 input{
        width: 100%;
        border-radius: 5px;
        border-width: 1px;
        margin-bottom: 5px;
    }
    .contentbooking2_main
    {
        display: block;
        padding-bottom: 30px;
        width: 80%;
    }
    /*    .contentbooking2_main{
            width: 70%;
        }*/

    .contentbooking2 h1{
        font-size: 23px;
        font-weight: 700;
        color: #4682B4;
    }
    .contentbooking2 h5{
        font-size: 16px;
        color: black;
    }

    .contentbooking2_main{
        padding-left: 30px;

    }
    select{

        background-color: #f2f2f2;
        color: #333;
        font-size: 14px;
        padding: 8px;
        border: 1px solid #ccc;
        border-radius: 4px;
        box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        width: 350px;
        height: 40px;
        text-align: center;

    }

    .contentdkk3{

        display: flex;
        justify-content: center;
        margin-top: 50px;
    }
    .contentdkk3 div{

        margin: 20px;
    }
    .contentdkk3 input{
        width: 90%;
        border: 1px solid #e0e0e0;
        border-radius: 10px;
        font-size: 15px;
        padding: 10px;
    }

    .contentdkk3 input:first-child{
        margin-right: 20px;
    }
    .search-container input{
        width: 50%;
        border-radius: 10px;
        border-width: 1px;
    }
    .search-form {
        display: flex;
    }

    .search-button{

        height: 30px;
        width: 10%;
        padding: 0px;
        margin: 0px;


        /* text-align: -webkit-auto; */
        font-weight: bold;

        border: 0px solid #ADD8E6;
        background-color: #4169E1;
        color: white;
        box-shadow: 0px 5px 10px 0 #ADD8E6;
        transition: 0.3s;
        border-radius: 6px;

    }
    .submitbooking{
        margin-top:30px;
        display: flex;
        justify-content: center;
    }


    .submitbooking a button{

        padding: 14px;


        font-size: 30px;
        /* text-align: -webkit-auto; */
        font-weight: bold;

        border: 0px solid #ADD8E6;
        background-color: #4169E1;
        color: white;
        box-shadow: 0px 5px 10px 0 #ADD8E6;
        transition: 0.3s;
        border-radius: 6px;
        width: 100%;
        height: 70px;
    }
    .btsearch2{
        background-color: #099956;
        border-color: #099956;
        height: 40px;
        border: none;
        padding: 13px;
        border-radius: 10px;
        text-decoration: none;
        color: wheat;
        font-weight: bold;
    }
    .btsearch2 a{
        text-decoration: none;
        color: wheat;
        font-weight: bold;
    }
    .addButton2{
        padding-left:40px;
        margin-left: 35%!important;
    }
    a.huy {

        color: #000;
        text-decoration: none;
        margin-left: 91%;
        padding: 5px 10px;


        background-color: #fff;
        border: 1px solid #000;


    }

    a.huy:hover {
        background-color: #000;
        color: #fff;
    }



</style>

