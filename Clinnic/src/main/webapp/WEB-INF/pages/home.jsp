<%-- 
    Document   : home
    Created on : Aug 12, 2023, 10:56:21 PM
    Author     : hung
--%>



<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<form:form >
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
    </head>
    <nav class="index">
        <div>
            <header class="header">
                <div class="logo">
                    <img src="https://res.cloudinary.com/dstqvlt8d/image/upload/v1691909467/Online_Drug_Store___Infinite_Medical_Cross_Unused_for_Sale_xnf4hq.png" alt="Bệnh viện XYZ Logo">
                    <p>PISCES Hospital</p>
                </div>

                <nav class="navigation">
                    <ul>
                        <li><a href="#">Trang chủ</a></li>
                        <li><a href="#">Đăng nhập</a></li>
                        <li><a href="#">Đăng Ký</a></li>
                        <li><a href="#">Liên hệ</a></li>
                    </ul>
                </nav>
            </header>
        </div>
        <div class="title">
            <div>
                <h3>PISCES hospital</h3>
                <p>Nơi chúng tôi chăm sóc sức khỏe và điều trị bệnh nhân với tận tâm và chất lượng cao. Cam kết an toàn, công nghệ tiên tiến và sự lắng nghe để mang lại sự ủng hộ và tin
                    tưởng từ cộng đồng. Sức khỏe của bạn là ưu tiên hàng đầu của chúng tôi.</p>
            </div>  

        </div>

        <div>

        </div>
    </nav>


</form:form>
<style>
    .header {
        background-color: #E0FFFF!important;
        display: flex;
        justify-content: space-between;
        align-items: center;
    }
    .logo{
        display: flex;
    }
    .logo p {

        font-family: "Times New Roman", serif;
        font-size: 18px;

        text-align: center;
        color: red!important;

        font-weight: bold;
        /* padding: 10px;
            /* text-transform: uppercase; */
        letter-spacing: 2px;
        /* line-height: 1.5; */

    }
    .logo img {
        height: 50px;
    }
    .navigation{
        padding-right: 20px;
    }
    .navigation ul {
        list-style: none;
        margin: 0;
        padding: 0;
        display: flex;
    }

    .navigation ul li {
        margin-left: 20px;
    }

    .navigation ul li a {
        text-decoration: none;
        color: #333;
        font-weight: bold;
    }

    .index{

        width: 1250px;
        height: 500px;
        background-image: url('https://res.cloudinary.com/dstqvlt8d/image/upload/v1691908413/HD_Healthcare_Wallpapers_for_Laptops_PCs_gut5ux.jpg'); /* Đường dẫn đến hình ảnh */
        background-size: cover; /* Thay đổi tỷ lệ để hình ảnh tràn đầy kích thước của vùng chứa */
        /*  background-position: center;*/

    }
    .title{
        
        height: 450px;
    align-items: center;
    width: 44%;
    margin-left: auto;
    margin-top: 116px;
    }
    .title div{
        text-align: justify;
        margin: 0px;
        align-items: center;
    }
    .title div h3{
        font-weight: 600;
        width: 55%;
        font-size:35px;

        margin: 0px;
    }
    .title div p{
        padding-top: 10px;
        font-size:18px;
        width: 54%;
        text-align: justify;
        margin-bottom: 45px;
        margin-left: 30px;
    }
</style>