<%-- 
    Document   : login
    Created on : Aug 6, 2023, 11:15:03 AM
    Author     : Trinh Bao Duy
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:if test="${param.error != null}">
    <div class="alert alert-danger">
        Tài khoản, mật khẩu của bạn nhập sai hoặc bạn chưa đến giờ làm !!! Vui lòng nhập lại ...!
    </div>
</c:if>
<c:if test="${param.accessDenied}">
    <div class="alert alert-danger">
        Ban khong co quyen truy cap
    </div>
</c:if>
<c:url value="/login" var="action" />
<div class="border">
<form method="post" action="${action}">
    <div class="login">
        <div class="img_login">
            <img src="https://res.cloudinary.com/dstqvlt8d/image/upload/v1691914443/Login_Web_Animation_fhmgp8.png" alt="alert"/>
        </div>
        <div class="mainLogin">
            <h3>Login</h3>
            <div class="Logincontent logincontent1">
                <label for="username" id="username" >Tên đăng nhập</label><br>
                <input type="text" class="" id="username" placeholder="Nhập username..." name="username">

            </div>

            <div class="Logincontent21 logincontent2">
                <label for="pwd" id="pwd">Mật khẩu</label><br>
                <input type="password" class="" id="pwd" placeholder="Nhập mật khẩu..." name="password">

            </div>

            <div class="buttonLogin">
                <input type="submit" value="Đăng nhập" class="buttonLoginColor" />
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


