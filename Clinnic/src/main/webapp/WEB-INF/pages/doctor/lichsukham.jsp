<%-- 
    Document   : kethuoc
    Created on : Aug 23, 2023, 12:24:04 AM
    Author     : hung
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<div class="infor1">

    <div class="contentdkk2_main contentdkk2_main1">
        <div class="imglogo">
            <img src=" https://res.cloudinary.com/dstqvlt8d/image/upload/v1692731869/zyro-image_ukyhu5.png" alt="logo">
            <h2>LỊCH SỬ KHÁM</h2>

        </div>
        <hr>
        <form class="search-form" action="<c:url value="/doctor/lichsukham/${benhnhan.id}"/>" method="post">
            <div class="search-container">
                <input name="date" type="date" placeholder="Nhập ngày cần tìm" class="search-input">
                <button type="submit" class="search-button">Tìm kiếm</button>
            </div>
        </form>
        <section class="table__body1 table__body11">
            <div class="content2">
                <h2>${benhnhan.name}</h2>

                <table class="table table-striped">
                    <thead>
                        <tr>

                            <th>Ngày khám</th> 
                            <th>Bác Sĩ Khám</th> 
                            <th>Triệu chứng</th>
                            <th>Kết luận</th>
                        </tr>
                    </thead>
                    <c:forEach items="${lishsubenh}" var="ds">
                        <tr>
                            <td><fmt:formatDate value="${ds.appointmentDate}" pattern="dd/MM/yyyy" /></td>
                            <td>${ds.doctorId.name}</td>
                            <td>${ds.prescriptionId.conclusion}</td>
                            <td>${ds.prescriptionId.symptom}</td>
                        </tr>
                    </c:forEach>
                </table>
            </div>    
        </section>

    </div>


</div>
<style>

    .infor1{
        /*display: flex;*/
        padding: 30px;
        border: 0.5px solid #5AAC4E;
        border-radius: 30px;
        margin: 30px;
        background-color: #F5FFFA;

    }
    .add{
        width: 10%;
    }

    .add button{
        margin: 20px;
        width: 70px;
    }
    .submitbooking a button{

        padding: 14px;
        height: 100px;
        width: 200px;
        font-size: 30px;
        /* text-align: -webkit-auto; */
        font-weight: bold;

        border: 0px solid #ADD8E6;
        background-color:orangered;
        color: white;
        box-shadow: 0px 5px 10px 0 #ADD8E6;
        transition: 0.3s;
        border-radius: 6px;

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
    hr {
        border: none;
        border-top: 3px solid #ccc;
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
height: 40px;
    width: 30%;
    border-radius: 10px;
    border-width: 0px;
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
</style>
