<%-- 
   Document   : doctorInfor
   Created on : Aug 15, 2023, 1:27:23 PM
   Author     : hung
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>


<c:url value="/nurse" var="actionUpdate" />
<sec:authorize access="hasRole('NURSE')">
    <div th:if="${successMessage}" class="success-message">
        <p th:text="${successMessage}"></p>
    </div>

    <div th:if="${errorMessage}" class="error-message">
        <p th:text="${errorMessage}"></p>
    </div>
    <div class="infor1">
        <div class="div1">
        <nav class="bookingleft">
            <div class="dkk1">
                <div class="booking1">
                    <img src="${nurse.avatar}" alt="alert"/>
                </div>
                <div class="contentbooking2_main">

                    <div class="contentbooking2">
                        <h1>Y tá ${nurse.name}</h1>
                        <h5>Mã: ${nurse.id} </h5>
                        <h5>Ngày sinh: ${nurse.dod}</h5>
                        <h5>Số điện Thoại: ${nurse.phone}</h5>
                        <h5>Địa chỉ: ${nurse.address}</h5>
                        <h5>Email: ${nurse.emaill}</h5>
                        <h5>Giới tính: ${nurse.sex}</h5>
<!--                        <div class="paypal">
                            <c:forEach items="${thanhtoan}" var="tk">
                                <h1>Hóa đơn ${tk.id} vừa khám xong <a><a href="<c:url value="/nurse/thanhtoan/${tk.id}" />">nhấn</a> để thanh toán hóa đơn</h1>
                            </c:forEach>
                        </div>-->
                    </div>

                </div>
            </div>
        </nav>
                        <div class="bookingright right1">
                            <div class="imgright1"><img src="https://res.cloudinary.com/dstqvlt8d/image/upload/v1694333894/Stock_Image__Business_Finance_dm4j3i.jpg" alt=""/> </div>
        <div class="paypal">
            <h1>Thanh Toán Hóa Đơn</h1>
            <hr> 
            <c:forEach items="${thanhtoan}" var="tk">
                <h10>Hóa đơn ${tk.id} vừa khám xong <a href="<c:url value="/nurse/thanhtoan/${tk.id}" />">nhấn</a> để thanh toán hóa đơn</h10><br>
            </c:forEach>
        </div>
                        </div>
        </div>
                        
                        
        <div class="tb1"   >    

            <nav class="table1">
                <section class="table__body1">
                    <table>
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>ID Bệnh nhân</th>
                                <!--                            <th>Email</th>-->
                                <th>Ngày đăng ký</th>
                                <th>Y tá</th>
                                <th>Bác sĩ</th>
                                <th>Trạng thái</th>
                                <th></th>
                            </tr>
                        </thead>

                        <tbody>
                            <c:forEach items="${Apo}" var="p">
                                <tr>
                                    <td>${p.id}</td>
                                    <td>[${p.sickpersonId.id}] ${p.sickpersonId.name}</td>
    <!--                                <td>${p.sickpersonId.emaill}</td>      -->
                                    <td>${p.appointmentDate}</td>
                                    <td>${p.nurseId.name}</td>
                                    <td>${p.doctorId.name}</td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${p.status.toString() eq 0}">
                                                <p id="xacnhan">Chưa xác nhận</p>
                                            </c:when>
                                            <c:otherwise>
                                                <p id="xacnhan1">Đã xác nhận </p>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td>

                                        <c:choose>
                                            <c:when test="${p.status == 0 }"><a href="<c:url value="/nurse/${p.id}"/>" class="btn btn-success xacnhan">Xác nhận </a></c:when>
                                            <c:when test="${p.status == 1 && p.nurseId.id == nurse.id}"><a href="<c:url value="/nurse/huy${p.id}"/>" class="btn btn-success">Hủy </a></c:when>
                                            <c:otherwise >Đã được xát nhận từ y tá ${p.nurseId.name}</c:otherwise>
                                        </c:choose>




                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>

                    </table>
                </section>
            </nav>
            <h1>${msg}</h1>

            <nav class=" border ">

                <form:form method="post" action="${actionUpdate}" modelAttribute="appoment">
                    <form:hidden path="appointmentDate" />
                    <form:hidden path="medicalappointmentDate" />
                    <form:hidden path="status" />
                    <form:hidden path="prescriptionId" />
                    <label class="chonbacsi" >CHỌN BÁC SĨ </label>

                    <div class="form-floating mb-3 mt-3">
                        <form:input type="text" class="form-control" 
                                    path="id" id="id" readonly="true"/>
                        <label for="name">Mã</label>

                    </div>
                    <div class="form-floating mb-3 mt-3">
                        <form:input type="text" class="form-control" 
                                    path="sickpersonId" id="sickpersonId" readonly="true"/>
                        <label for="name">Mã bệnh nhân</label>

                    </div>
                    <div class="form-floating mb-3 mt-3">
                        <form:input value="${nurse.id}" type="text" class="form-control" 
                                    path="nurseId" id="nurseId" readonly="true"/>
                        <label for="name">Mã y tá</label>

                    </div>
                    <div class="form-floating mb-3 mt-3">
                        <form:select class="form-select" id="role" name="doctorId" path="doctorId">
                            <c:forEach items="${getbacsi}" var="r">
                                <c:choose>
                                    <c:when test="${r.id == appoment.doctorId.id}">
                                        <option  value="${r.id}" selected>${r.name}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${r.id}">${r.name}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </form:select>
                        <label for="category" class="form-label">Chọn Bác Sĩ:</label>
                    </div>
                    <div class="form-floating mb-3 mt-3">
                        <button class="btn btn-success" type="submit">Thêm Bác Sĩ</button>
                    </div>

                </form:form>
            </nav>

        </div> 

    </div>

</sec:authorize>

<style>
    .tb1{
        display: flex;
    }
    .admin_submit111{
        width: 160px;
        text-align: center;
        font-size: 17px;
        border-radius: 6px;
        border: none;
        background-color: rgb(130, 201, 30);
        padding: 10px;
        box-shadow: 0px 5px 10px 0 rgba(130, 201, 30, 0.1);
    }
    .table1 {
        display: none;
    }

    .show-table {
        display: block !important;
    }


    .admin_submit111 a{
        text-decoration: none;
        color: white;
    }
    .admin_submit111 a:hover{
        color: white;
    }

    .admin_submit1111{
        margin: auto;
        width: 100px;
    }

    .bookingleft{

    }
    .bookingright{
        width: 70%;
        padding-left: 30px;
        /*background-image: url(https://res.cloudinary.com/dstqvlt8d/image/upload/v1692119274/Free_Vector___Cardio_heartbeat_medical_and_healthcare_background_oxvnvc.jpg);*/
        border: 0.5px solid #5AAC4E;
        border-radius: 30px;
        margin-left: 30px;


    }
    .bookingright h1{

        text-align: center;
        font-weight: bold;
        padding-top: 30px;
    }
    .infor1{
        padding: 30px;

    }
    .schedule{
        display: flex;

    }
    .schedule img{
        width: 20%;
        border-radius: 100%;
        padding: 10px;
    }
    .content1 th a{
        color: black;
        text-decoration: auto;
    }
    .dkk1{

        /*        display: flex;*/
        justify-content:  space-between;
        /*padding: 50px;*/
        border: 0.5px solid #5AAC4E;
        border-radius: 30px;
        display: flex;

    }
    .dkk2{
        /*
         padding-top: 80px;*/
        padding-left: 150px;

        display: flex;
    }
    .dkk2 div {

        padding: 30px;
    }
    .booking1{
        width: 30%;
        /*        width: 300px;
                height: 300px;*/
        display: flex;
        justify-content: center;
        align-items: center;
        overflow: hidden;
    }
    .booking1 img{
        padding: 5px;
        /* border-radius: 20px; */
        border-radius: 50px;
        width: 100%;
        height: 100%;
        object-fit: cover;
    }

    .contentdkk1 h3{
        font-size: 18px;
        color: white;
    }
    .contentbooking2_main
    {
        display: block;
        padding-bottom: 30px;
        width: 70%;
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
        margin-top: 30px;
        display: flex;
        justify-content: center;
    }
    .contentdkk3 input{
        width: 350px;
        border: 1px solid #e0e0e0;
        border-radius: 10px;
        font-size: 15px;
        padding: 10px;
    }

    .contentdkk3 input:first-child{
        margin-right: 20px;
    }

    .contentdkk5{
        margin-top: 10px;
        padding-top: 10px;
    }
    .contentdkk5 input{
        width: 100%;
        border: 1px solid #e0e0e0;
        border-radius: 10px;
        font-size: 15px;
        padding: 10px;
    }
    .contentdkk4{
        margin-top: 20px;
        font-size: 15px;
        text-align: center;
    }
    .contentdkk4 select{
        width: 100%;
        height: 45px;
        border: 1px solid #e0e0e0;
        padding: 10px;
        border-radius: 10px;
    }

    /*    .contentdkk6{
            margin-top: 10px;
            font-size: 15px;
            padding-top: 10px;
            text-align: center;
        }
        .contentdkk6 input{
            width: 100%;
            height: 45px;
            border: 1px solid #e0e0e0;
            padding: 10px;
            border-radius: 10px;
        }*/

    .submitbooking{
        margin-top:30px;
        display: flex;
        justify-content: center;
    }
    .submitbooking a button{

        padding: 14px;
        height: 100px;
        width: 200px;
        font-size: 30px;
        /* text-align: -webkit-auto; */
        font-weight: bold;


    }
    main.table1{
        /*    height: 100%;*/
        width: 84%;
        border-radius: 30px;
        background-color: #fffff;
        box-shadow: 0px 0px 40px 0px #c0f4b9;
        margin-top: 50px;
        /*        display: flex;
                justify-content: center;*/
    }

    .table__body1 {
        width: 100%;
        border-radius: 20px;
        height: 550px;
        margin-top: 10px;
        margin-bottom: 50px;
        background-color: #fffb;
        border-radius: 20p;
        overflow: auto;
        overflow: overlay;
        border: 1px solid #E9F9E7;
        padding: 0px;
    }

    table {
        width: 100%;
    }

    table, th, td {
        border-collapse: collapse;
        padding: 1rem;
        text-align: center;
        font-size: 20px;
    }

    thead th {
        position: sticky;
        top: 0;
        left: 0;
        background-color: #E9F9E7;
        cursor: pointer;
    }

    tbody tr:nth-child(even) {
        background-color: #0000000b;
    }

    #xacnhanLK{
        display: none;
    }

    .admin_submit111{
        width: 160px;
        text-align: center;
        font-size: 17px;
        border-radius: 6px;
        border: none;
        background-color: rgb(130, 201, 30);
        padding: 10px;
        box-shadow: 0px 5px 10px 0 rgba(130, 201, 30, 0.1);
    }


    .admin_submit111 a{
        text-decoration: none;
        color: white;
    }
    .admin_submit111 a:hover{
        color: white;
    }

    .admin_submit1111{
        margin: auto;
        width: 100px;
    }

    #capnhathuoc a{
        color: white;
        text-decoration: none;
    }


    #capnhathuoc{
        font-size: 16.5px;
        width: 110px;
    }

    .table__body1{
        width: 100%;
    }

    .table1{
        display: flex;
        justify-content: space-around;
        padding: 0px;
        border-radius: 0px;
    }

    .change_ac11{
        padding: 60px;
        background: #E9F9E7;
        border-radius: 0px;
        display: block;
    }
    .admin_btn1{
        margin-bottom: 50px;
    }

    .inputkw input{
        width: 250px;
        border-radius: 10px;
        background: #F4F4F4;
        border: none;
        padding: 8px;
        margin-top: 25px;
    }
    .inputkw button{
        background: #F4F4F4;
        width: 50px;
        border-radius: 10px;
        border: none;
        padding: 8px;
    }

    .inputkw button i{
        font-size: 17px;
    }



    #idRole{
        width: 250px;
        margin: auto;
        text-align: center;
    }

    #xacnhan{
        color:#dc3545;
        margin: 0px;
    }

    #xacnhan1{
        color:rgb(130, 201, 30);
        margin: 0px;
    }

    #xoaThuoc{
        padding: 10px;
    }

    .table>:not(caption)>*>*{
        padding: 0px !important;
    }

    .header-lapdskham{
        display: flex;
        margin-top: 30px;
        margin-bottom: 20px;
        justify-content: space-around;
        align-items: center;
    }

    .lapdskham_search{
        align-items: center;
    }

    .lapdskham_search input{
        width: 200px;
        padding: 5px;
        border-radius: 10px;
        border: 1px solid #ced4da;
    }
    .lapdskham_search button{
        margin-left: 10px;
        width: 50px;
        background: rgb(130, 201, 30);
        padding: 5px;
        color: white;
        border-radius: 10px;
        border: 0px solid #ced4da;
        box-shadow: 0px 5px 10px 0 rgba(130, 201, 30, 0.5);
    }
    .border{
        display: flex;
        border: 0.5px solid #5AAC4E;
        border-radius: 30px;

        background-color: #F5FFFA;

        margin: 10px;
        width: 30%;
        padding: 20px;
        height: 550px;
    }
    .chonbacsi{
        font-weight: bold;
    }
    .btaddbacsi{
        background-color: green;
    }

    .paypal {
        text-align: center; 
        padding: 10px; /* Khoảng cách giữa nội dung và viền */
    }

    .paypal h1 {
        font-size: 18px; /* Kích thước chữ */
        margin: 0; /* Xóa khoảng cách giữa các phần tử h1 */
    }

    .paypal a {
        color: #0080ff; /* Màu chữ */
        text-decoration: none; /* Loại bỏ gạch chân */
    }

    .paypal a:hover {
        text-decoration: underline; /* Gạch chân khi hover */
    }
    .div1{
        display: flex;
            
    }
    .right1{
        display: flex!important;
    }
    .imgright1{
        width: 21%;
    }
    .imgright1 img{
        width: 100%;
    }





</style>