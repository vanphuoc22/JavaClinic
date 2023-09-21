<%-- 
    Document   : doctorInfor
    Created on : Aug 15, 2023, 1:27:23 PM
    Author     : hung
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>

<c:url value="/doctor" var="action" />
<sec:authorize access="hasRole('DOCTOR')">
    <div class="infor1">
        <form:form method="post" action="${action}" modelAttribute="doctor"  enctype="multipart/form-data">
            <nav class="bookingleft">
                <div class="dkk1">
                    <div class="booking1">
                        <img src="${doctor.avatar}" alt="alert"/>
                    </div>
                    <div class="contentbooking2_main">

                        <div class="contentbooking2">
                            <h1>Bác sĩ ${doctor.name}</h1>
                            <h5>Ngày sinh: ${doctor.dod}</h5>
                            <h5>Số điện Thoại: ${doctor.phone}</h5>
                            <h5>Địa chỉ: ${doctor.address}</h5>
                            <h5>Email: ${doctor.emaill}</h5>
                            <h5>Giới tính: ${doctor.sex}</h5>
                        </div>


                    </div>


                </div>
            </nav>
        </form:form>
        <script src="<c:url value="/js/dongho.js" />"></script>
        <div class="bookingright" onload="updateCurrentTime()">
            <div class="schedule">
                <img src=" https://res.cloudinary.com/dstqvlt8d/image/upload/v1692119292/Download_free_vector_of_Heart_with_a_red_cross_symbol_vector_by_Ning_about_plus_sign_plus_symbol_healthcare_heart_hospital_and_plus_icon_sign_516146_jafq4t.jpg" alt="Image Description">
                <h1>Lịch khám</h1>
                <!-- Hiển thị giờ hiện tại -->
                <p class="time" > Giờ hiện tại: <span id="current-time"></span></p>
            </div>

            <div class="content1">


                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th> Tên khách hàng</th>
                            <!--<th>Dịch vụ</th>-->
                            <th>Ngày khám</th>
                            <th>Giờ khám</th>
                            <th>Trạng Thái</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${lichkham}" var="ds">
                            <tr>
                                <td><a href="<c:url value="/doctor/lichsukham/${ds.sickpersonId.id}" />">${ds.sickpersonId.name}</a></td>
                                <td><script>
                                        var datetime = new Date("${ds.appointmentDate}");
                                        var date = datetime.getDate();
                                        var month = datetime.getMonth() + 1; // Tháng trong JavaScript được đếm từ 0 đến 11, nên cần cộng 1
                                        var year = datetime.getFullYear();
                                        var formattedDate = date + '/' + month + '/' + year;
                                        document.write(formattedDate);
                                    </script></td>
                                <td>
                                    <script>
                                        var datetime = new Date("${ds.appointmentDate}");
                                        var hours = ("0" + datetime.getHours()).slice(-2); // Lấy giờ và đảm bảo hiển thị dưới dạng hai chữ số
                                        var minutes = ("0" + datetime.getMinutes()).slice(-2); // Lấy phút và đảm bảo hiển thị dưới dạng hai chữ số
                                        var formattedTime = hours + ':' + minutes;
                                        document.write(formattedTime);
                                    </script>
                                </td>
                                <td>${ds.prescriptionId.id}</td>
                                <td><a href="<c:url value="/doctor/khambenh/${ds.id}"/>">Khám</a></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>

            </div>    

        </div>

    </div>
</sec:authorize>
<style>
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
        display: flex;
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
    p.time  {
/*  border: 2px solid #ccc;*/
  padding: 10px;
  border-radius: 5px;
  background-color: #f7f7f7;
  display: inline-block;
  height: 30px;
  margin-top: 48px;
    /* margin-right: 50%; */
    margin-left: 27%;
    padding: 5px;
}

span#current-time {
  font-weight: bold;
  font-size: 18px;
  height: 20px;
  line-height: 20px;
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

    tr > td > a{
            text-decoration: none;
    font-weight: bold;
    color: #078d4e;

    }

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

        border: 0px solid #ADD8E6;
        /*        background-color:orangered;*/
        color: #285cc6;
        box-shadow: 0px 5px 10px 0 #ADD8E6;
        transition: 0.3s;
        border-radius: 6px;
        background-image: url('https://res.cloudinary.com/dstqvlt8d/image/upload/v1692163867/Free_Vector___Blue_dna_background_with_medical_and_healthcare_purpose_askygz.jpg');
        background-size: cover;
        background-position: center;
        padding-top: 5px;
    }
</style>