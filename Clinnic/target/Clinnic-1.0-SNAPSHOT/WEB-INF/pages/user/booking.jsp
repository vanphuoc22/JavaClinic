<%-- 
    Document   : booking
    Created on : Aug 11, 2023, 12:01:28 PM
    Author     : hung
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:url value="/user/booking" var="action" />
<form:form method="post" action="${action}" modelAttribute="phieukhammoi">
    <nav class="booking">
        <div class="dkk">
            <div class="booking1">
                <img src="https://res.cloudinary.com/dstqvlt8d/image/upload/v1691905029/booking1_tlofkq.png" alt="alert"/>
            </div>
            <div class="contentbooking2_main">
                <div class="contentbooking2">
                    <h1>ĐĂNG KÝ KHÁM</h1>
                    <h5>Vui lòng điền thông tin vào form bên dưới để đăng ký khám bệnh theo yêu cầu!</h5>
                </div>
                <%--<form:hidden path="appointmentDate" />--%>
                <form:hidden path="medicalappointmentDate" />
                <form:hidden path="status" />
                <form:hidden path="prescriptionId" />
                <form:hidden path="sickpersonId" value="${user.id}"/>
                <form:hidden path="nurseId" />
                <form:hidden path="doctorId" />
                <form:hidden path="id" />

                <div class="contentdkk3" style="display: block;">
                    <div class="form-group">
                        <label for="usr">Name:</label>
                        <input value="${user.name}" type="text" class="form-control" id="usr">
                    </div>

                    <div class="form-group">
                        <label for="pwd">Số Điện Thoại:</label>
                        <input value="${user.phone}" type="text" class="form-control" id="pwd">
                    </div>
                    <div class="form-group">
                        <label for="email">Email:</label>
                        <input value="${user.emaill}" type="text" class="form-control" id="pwd">
                    </div>

                    <div class="form-group">
                        <label for="appointment_Date">Ngày Khám:</label>
                            <form:input type="date" class="form-control" path="appointmentDate" id="appointment_Date" placeholder="Ngày Khám..." />
                    </div>
                </div>

                <div class="submitbooking">
                    <button type="submit">ĐĂNG KÝ KHÁM</button>
                </div>
            </div>


        </div>
    </nav>
</form:form>
<style>
    .booking{
        padding-top: 10px;
    }


    .dkk{

        display: flex;
        justify-content:  space-between;
        /*padding: 50px;*/
        border: 0.5px solid #5AAC4E;
        border-radius: 30px;


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

    /*    .contentbooking1{
            width: 100%;
            padding: 45px;
            text-align: justify;
        }
        .contentbooking1 img{
            top: 0;
            left: 0;
            width: 100%;  
            height: 100%;  
            object-fit: cover;
        }*/
    /*.booking1{
        position: relative;
            width: 200px;  Độ rộng của khung chứa ảnh 
            height: 200px;
    }
    .contentbooking1 img{
         position: absolute;
            top: 0;
            left: 0;
            width: 100%;  Độ rộng của ảnh 
            height: 100%;  Chiều cao của ảnh 
            object-fit: cover;
    }*/
    .contentdkk1 h3{
        font-size: 18px;
        color: white;
    }
    .contentbooking2_main
    {
        display: block;
    }
    .contentbooking2_main{
        width: 70%;
    }

    .contentbooking2 h1{
        font-size: 23px;
        font-weight: 700;
        color: #4682B4;
    }
    .contentbooking2 h5{
        font-size: 16px;
        color: #4682B4;
    }

    .contentbooking2_main{
        padding: 45px;

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
        width: 160px;
        padding: 14px;
        font-size: 14px;
        border: 0px solid #ADD8E6;
        background-color:#87CEFA;
        color: white;
        box-shadow: 0px 5px 10px 0 #ADD8E6;
        transition: 0.3s;
        border-radius: 6px;
    }
</style>