<%-- 
    Document   : adminhome
    Created on : Aug 27, 2023, 2:11:38 PM
    Author     : hung
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:url value="/admin" var="action" />
<main class="table">
    <div class="containe">

        <div class="adminleft">
            <h2>Trang quản lý tài khoản</h2>
            <img src="https://res.cloudinary.com/dstqvlt8d/image/upload/v1693121721/Customer_Service_Free_Avatar_User_Icon_Business_User_Icon_Users_Group_Icon_Female_User_Icon_User_Icon_Template_Download_on_Pngtree-removebg-preview_tcdrsw.png" alt="alert"/>
        </div>
        <!--        <div class="adminright">
                    <a href="<c:url value="/admin/quanlytaikhoan" />">QUẢN LÝ TÀI KHOẢN</a>
                    <a href="<c:url value="/admin/quanlythuoc" />">QUẢN LÝ THUỐC</a>
                    <a href="<c:url value="/admin/saplichlam" />">QUẢN LÝ LỊCH TRỰC</a>
                    <a href="<c:url value="/admin/thongke" />">THỐNG KÊ</a>
                    
        
                </div>-->
        <div class="adminright">
            <div class="btadmin"> 
                <a href="<c:url value="/admin/quanlytaikhoan" />">
                    <button>

                        <img src="https://res.cloudinary.com/dstqvlt8d/image/upload/v1692731869/zyro-image_ukyhu5.png" alt="Icon">
                        <h3>Quản lý tài khoản</h3>
                    </button>
                </a>
            </div>
            <div class="btadmin">
                <a href="<c:url value="/admin/quanlythuoc" />">
                    <button>
                        <img src="https://res.cloudinary.com/dstqvlt8d/image/upload/v1692731869/zyro-image_ukyhu5.png" alt="Icon">
                        <h3>Quản lý thuốc</h3>
                    </button>
                </a>
            </div>
            <div class="btadmin">
                <a href="<c:url value="/admin/saplichlam" />">
                    <button>
                        <img src="https://res.cloudinary.com/dstqvlt8d/image/upload/v1692731869/zyro-image_ukyhu5.png" alt="Icon">
                        <h3>Xếp Lịch Làm</h3>
                    </button>
                </a>
            </div >
            <div class="btadmin">
                <a href="<c:url value="/admin/thongke" />">
                    <button>
                        <img src="https://res.cloudinary.com/dstqvlt8d/image/upload/v1692731869/zyro-image_ukyhu5.png" alt="Icon">
                        <h3>Thống Kê Bệnh Nhân</h3>
                    </button>
                </a>
            </div>
            <div class="btadmin">
                <a href="<c:url value="/admin/thongkedoanhthu" />">
                    <button>
                        <img src="https://res.cloudinary.com/dstqvlt8d/image/upload/v1692731869/zyro-image_ukyhu5.png" alt="Icon">
                        <h3>Thống Kê Doanh Thu</h3>
                    </button>
                </a>
            </div>
        </div>


    </div>
</main>

<style>


    .containe{
        margin: 30px;
        padding: 20px;
        border: 0.5px solid #5AAC4E;
        border-radius: 30px;
        background-color: #F5FFFA;
        display: flex;
    }
    .adminleft{
        width: 35%;
        margin-right: 10%;
    }
    .adminright{
        width: 60%;
        margin-right: 10%;
        justify-content: center;
        align-items: center;
    }
    .adminleft img{
        width: 100%;

    }

    .adminright a {
        display: inline-block;



    }

    .adminright button {
        background: none;
        border: none;
        padding: 0;
    }

    .adminright img {
        width: 20px; /* Thay đổi kích thước hình ảnh tùy theo yêu cầu */
    }
    div.btadmin{
        background-color: #a1e9d9;
        border-radius: 50px;
        margin: 20px;
        width: 30%;
        width: 100%;
        text-align: center;
    }

</style>



