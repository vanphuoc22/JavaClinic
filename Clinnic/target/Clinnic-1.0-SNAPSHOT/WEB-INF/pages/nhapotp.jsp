<%-- 
    Document   : nhapotp
    Created on : Sep 8, 2023, 5:26:54 PM
    Author     : Trinh Bao Duy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!--<form action="<c:url value="/forgot-password" />" method="post">
  <label for="email">Địa chỉ email:</label>
  <input type="email" id="email" name="email" required>
  <button type="submit">Gửi mã OTP</button>
</form>-->
<div class="border">
    <form action="<c:url value="/forgot-password" />" method="post">
        <div class="login">
            <div class="img_login">
                <img src="https://res.cloudinary.com/dstqvlt8d/image/upload/v1691914443/Login_Web_Animation_fhmgp8.png" alt="alert"/>
            </div>
            <div class="mainLogin">
                <h3>Yêu Cầu OTP</h3>
                <div class="Logincontent logincontent1">
                    <label for="username" id="username" >Nhập địa chỉ mail: </label><br>
                    <input type="email" class="" id="username" placeholder="Nhập email..." name="email">

                </div>

               

                <div class="buttonLogin">
                    <button class="buttonLoginColor" type="submit">Gửi mã OTP</button>
                </div>
            </div>
        </div>
    </form>
</div>
<style>

    .border{
        display: flex;
        border: 0.5px solid #5AAC4E;
        border-radius: 30px;
        margin: 50px;
        background-color: #F5FFFA;
    }
    .img_login img{
        border-radius: 34px;
        margin: 30px;
    }
    .login{
        display: flex;
        width: 100%;
    }
    .img_login {
        width: 30%;
    }
    .mainLogin{
        padding-top: 80px;
        padding-left: 200px;
        width: 100%;
    }
    .mainLogin h3{
        color: #198754!important;
        font-weight: bold!important;
    }
    .Logincontent{
        margin: 30px;
        width: 100%;
    }
    .Logincontent21{
        margin: 30px;
        width: 100%;
    }
    #username{
        height: 35px;
        border-radius: 100px;
        width: 100%;
    }
    #pwd:placeholder {
        margin-left: 10px;
    }
    #username::placeholder {
        margin-left: 100px!important;
    }
    #pwd{
        height: 35px;
        border-radius: 100px;
        width: 100%;
    }
    .logincontent2{
        margin-bottom: 0px!important;
        width: 100%;
    }
    .logincontent1 input{
        font-size: 10px;
    }
    .logincontent2 input{
        font-size: 10px;
    }

    .buttonLogin{
        padding-left: 66%;
        width: 10px;
    }
    .buttonLoginColor{
        background-color: #45a049;
        padding: 10px 20px;
        width: 150px;
        color: white;
        border: none;
        border-radius: 4px;
        cursor: pointer;
        margin-left: 64%;
        margin-top: 20px;
    }
    .mainLogin .nav-link{
        padding-left: 75%;
        font-size: 12px;
        margin: 10px;
        width: 100%;


    }
    .mainLogin .nav-link:hover {

        color:#198754;
    }
</style>
