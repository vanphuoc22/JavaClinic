<%-- 
    Document   : dangkylam
    Created on : Aug 18, 2023, 11:22:51 AM
    Author     : hung
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:url value="/doctor/xemlichlam" var="action" />
<div class="content">

    <div class="contentleft">
        <div class="dkk1">
            <div class="booking1">
                <img src="${doctor.avatar}" alt="alert"/>
            </div>
            <div class="contentbooking2_main">

                <div class="contentbooking2">
                    <h3>Bác sĩ ${doctor.name}</h3>
                    <h5>Ngày sinh: ${doctor.dod}</h5>
                    <h5>Số điện Thoại: ${doctor.phone}</h5>
                    <h5>Địa chỉ: ${doctor.address}</h5>
                    <h5>Email: ${doctor.emaill}</h5>
                    <h5>Giới tính: ${doctor.sex}</h5>
                </div>


            </div>


        </div>
    </div>
<!--    <script src="<c:url value="/js/dongho.js" />"></script>
    <div class="contentright">
        <h1>Lịch làm đã đăng ký </h1>
        <p>Giờ hiện tại: <span id="current-time"></span></p>
        <table class=" table ">
            <thead>
                <tr>
                    <th>Ngày làm</th>
                    <th>Ca làm</th>
                    <th>Thời gian bắt đầu</th>
                    <th>Thời gian kết ca</th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${lichhientaica}" var="t">
                    <tr>
                        <td>${t.dateSchedule}</td>
                        <td>${t.shiftId.name}</td>
                        <td>${t.shiftId.start}</td>
                        <td>${t.shiftId.end}</td>
                        <td><a href="<c:url value="/doctor/xemlichlam/huy/${t.id}"/>" >Hủy</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

    </div>-->
    <div class="contentright contentright2 ">
        <h1>Lịch làm hiện tại </h1>
        <p>Giờ hiện tại: <span id="current-time"></span></p>
        <table class=" table ">
            <thead>
                <tr>
                    <th>Ngày làm</th>
                    <th>Ca làm</th>
                    <th>Thời gian bắt đầu</th>
                    <th>Thời gian kết ca</th>
<!--                    <th>Nút</th>-->
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${lichhientai}" var="t">
                    <tr>
                        <td>${t.dateSchedule}</td>
                        <td>${t.shiftId.name}</td>
                        <td>${t.shiftId.start}</td>
                        <td>${t.shiftId.end}</td>
                        <!--<td><a href="<c:url value="/doctor/xemlichlam/huy/${t.id}"/>" >Hủy</a></td>-->
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

</div>
    <script src="<c:url value="/js/dongho.js" />"></script>
    <div class="contentright contentright1">
        <h1>Lịch làm đã đăng ký </h1>
        <p>Giờ hiện tại: <span id="current-time"></span></p>
        <table class=" table ">
            <thead>
                <tr>
                    <th>Ngày làm</th>
                    <th>Ca làm</th>
                    <th>Thời gian bắt đầu</th>
                    <th>Thời gian kết ca</th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${lichhientaica}" var="t">
                    <tr>
                        <td>${t.dateSchedule}</td>
                        <td>${t.shiftId.name}</td>
                        <td>${t.shiftId.start}</td>
                        <td>${t.shiftId.end}</td>
                        <td class="bthuy"><button><a href="<c:url value="/doctor/xemlichlam/huy/${t.id}"/>" >Hủy</a></button></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

    </div>
<style>
    .checkbox1,.checkbox2,.checkbox3{
        margin: 0 20px;
    }

    .content{
        display: flex;
        padding: 20px;

    }
    .contentleft{
        width: 30%;
        padding-left: 30px;
        /*background-image: url(https://res.cloudinary.com/dstqvlt8d/image/upload/v1692119274/Free_Vector___Cardio_heartbeat_medical_and_healthcare_background_oxvnvc.jpg);*/
        border: 0.5px solid #5AAC4E;
        border-radius: 30px;
        margin-left: 30px;
        height: 100%;


    }
    .contentright {
        width: 70%;
        padding: 20px;
        border: 0.5px solid #5AAC4E;
        border-radius: 30px;
        margin-left: 30px;
    }

    .contentright h1{
        font-family: 'Helvetica Neue', Arial, sans-serif;
        font-size: 24px;
        color: #333;
        margin-bottom: 10px;
    }
    .contentbooking2 h5{
        font-size: 16px;
        color: black;
    }
    .dkl {
        margin-bottom: 20px;
    }

    .dkl label {
        display: block;
        margin-top: 10px;
    }

    .dkl input[type="date"],
    .dkl select {
        width: 100%;
        padding: 10px;
        margin-top: 5px;
        font-size: 16px;
        border: 1px solid #ccc;
        border-radius: 4px;
    }

    .dkl select {
        height: 40px;
    }
    .submitbooking {
        text-align: center;
        margin-top: 20px;
    }

    .submitbooking a {
        text-decoration: none;
    }

    .submitbooking button[type="submit"] {
        padding: 10px 20px;
        font-size: 16px;
        background-color: #4CAF50;
        color: white;
        border: none;
        border-radius: 4px;
        cursor: pointer;
    }

    .submitbooking button[type="submit"]:hover {
        background-color: #45a049;
    }
    .booking1 img{
        width: 50%;
        margin-top: 20px;
    }
    th {
        background-color: red;
    }
    .table>:not(caption)>*>* {
        padding: 0.2rem 0.4rem;
    }
    td>img{
        width: 20px;

    }
    .contentright1{
        margin: 0 auto!important;
    width: 93%!important;
    
    }
    .contentright2{
        margin-right: 31px;
    }
    
    .bthuy {
  text-align: center;
}

.bthuy button {
  padding: 8px 16px;
  background-color: #0080ff;
  color: #fff;
  border: none;
  border-radius: 4px;
  text-decoration: none;
  cursor: pointer;
}

.bthuy button a {
  color: #fff;
  text-decoration: none;
}

.bthuy button:hover {
  background-color: #0059b3;
}
 




</style>