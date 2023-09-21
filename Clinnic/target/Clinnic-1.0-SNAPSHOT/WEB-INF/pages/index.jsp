<%-- 
    Document   : index
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
        <div class="title">
            <div class='doctorhomeimg'>
            <img src="https://res.cloudinary.com/dstqvlt8d/image/upload/v1693683484/nytwfjdoezhbqssjrwyn.png" alt="logo"/>
            </div>
            <div class  = "content ">
                
                <h1 class="animated-heading">
                    <span>Bệnh viện PISCES</span>
                </h1>
                <p>Nơi chúng tôi chăm sóc sức khỏe và điều trị bệnh nhân với tận tâm và chất lượng cao. Cam kết an toàn, công nghệ tiên tiến và sự lắng nghe để mang lại sự ủng hộ và tin
                    tưởng từ cộng đồng. Sức khỏe của bạn là ưu tiên hàng đầu của chúng tôi.</p>
            </div>  

        </div>

        
    </nav>
     


</form:form>
<style>
     .index{
    width: 100%;
    height: 100%;
    background-image: url('https://res.cloudinary.com/dstqvlt8d/image/upload/v1692163868/Green_Color_Abstract_Healthy_Medical_Background_sjkj2l.png'); 
    background-size: cover;
}
.title{
    padding-top: 100px;
    height: 450px;
    align-items: center;
    
    /* margin-left: 52%; */
    display: flex;

}
.index div.content{
  width: 50%;
}
.doctorhomeimg{
  margin: 10%;
}
/* .title div{
    text-align: justify;
    margin: 0px;
    align-items: center;
} */
 
.title div p{
  padding-top: 10px;
  font-size: 20px;
  width: 88%;
  text-align: justify;

}
.animated-heading {
  overflow: hidden; /* Để ẩn phần vượt quá khung hiển thị */
  white-space: nowrap; /* Để không xuống dòng khi vượt quá khung hiển thị */
  color: green; /* Màu chữ xanh lá */
}

.animated-heading span {
  display: inline-block; /* Hiển thị dòng chữ trên cùng một dòng */
  animation: slide-in 2s ease-in-out; /* Sử dụng hiệu ứng slide-in trong 2 giây */
}

/* Định nghĩa hiệu ứng slide-in */
@keyframes slide-in {
  0% {
    transform: translateX(-100%); /* Di chuyển chữ về bên trái ngoài khung hiển thị */
  }
  100% {
    transform: translateX(0); /* Di chuyển chữ về vị trí ban đầu */
  }
}
</style>