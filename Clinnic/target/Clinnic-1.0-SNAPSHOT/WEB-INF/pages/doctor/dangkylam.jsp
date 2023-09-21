<%-- 
    Document   : dangkylam
    Created on : Aug 18, 2023, 11:22:51 AM
    Author     : hung
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:url value="/doctor/dangkylam" var="action" />
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
    <script src="<c:url value="/js/dongho.js" />"></script>
    <div class="contentright">
        <h1>ĐĂNG KÝ LỊCH LÀM TỪ NGÀY<p id="future-date"></p></h1>
        <p style="display: none">Giờ hiện tại: <span id="current-time"></span></p>
        <table class=" table ">
            <thead>
                <tr>

                    <th>Ca/thứ</th>
                    <th>THỨ 2 </th>
                    <th>THỨ 3</th>
                    <th>THỨ 4</th>
                    <th>THỨ 5</th>
                    <th>THỨ 6</th>
                    <th>THỨ 7</th>
                    <th>CHỦ NHẬT</th>



                </tr>
            </thead>
            <tbody>
                <form:form id="lichlam"  method="post" action="${action}" modelAttribute="lichlam" >
                    <form:hidden path="id" />
                    <form:hidden path="userId" value="${doctor.id}" />
                    <form:hidden path="status" value="0"/>
                    <form:hidden path="shiftId" />
                    <form:hidden path="dateSchedule"/>
                    <tr>

                        <td>Ca sáng</td>
                        <c:forEach items="${dateList}" var="date">
                            <td>
                                <c:set var="elementExists" value="false" />
                                <c:forEach var="item" items="${lichhientaica1}">
                                    <c:set var="itemDate">
                                        <fmt:formatDate value="${date}" pattern="yyyy-MM-dd" />
                                    </c:set>
                                    <c:if test="${item == itemDate}">
                                        <c:set var="elementExists" value="true" />
                                    </c:if>
                                </c:forEach>
                                <c:choose>
                                    <c:when test="${elementExists eq false}">
                                        <form:checkbox class="checkbox1" path="listdate1" value="${date}" />
                                    </c:when>
                                    <c:otherwise>
                                        <input type="checkbox" name="myCheckbox" value="someValue" style="margin: 0 20px;" checked="true" disabled="disabled">
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </c:forEach>
                    </tr>
                    <tr>

                        <td>Ca chiều</td>
                        <c:forEach items="${dateList}" var="date">
                            <td>
                                <c:set var="elementExists" value="false" />
                                <c:forEach var="item" items="${lichhientaica2}">
                                    <c:set var="itemDate">
                                        <fmt:formatDate value="${date}" pattern="yyyy-MM-dd" />
                                    </c:set>
                                    <c:if test="${item == itemDate}">
                                        <c:set var="elementExists" value="true" />
                                    </c:if>
                                </c:forEach>
                                <c:choose>
                                    <c:when test="${elementExists eq false}">
                                        <form:checkbox class="checkbox1" path="listdate2" value="${date}" />
                                    </c:when>
                                    <c:otherwise>
                                        <input type="checkbox" name="myCheckbox" value="someValue" style="margin: 0 20px;" checked="true" disabled="disabled">
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </c:forEach>
                    </tr>
                    <tr>

                        <td>Ca đêm</td>
                        <c:forEach items="${dateList}" var="date">
                            <td>
                                <c:set var="elementExists" value="false" />
                                <c:forEach var="item" items="${lichhientaica3}">
                                    <c:set var="itemDate">
                                        <fmt:formatDate value="${date}" pattern="yyyy-MM-dd" />
                                    </c:set>
                                    <c:if test="${item == itemDate}">
                                        <c:set var="elementExists" value="true" />
                                    </c:if>
                                </c:forEach>
                                <c:choose>
                                    <c:when test="${elementExists eq false}">
                                        <form:checkbox class="checkbox1" path="listdate3" value="${date}" />
                                    </c:when>
                                    <c:otherwise>
                                        <input type="checkbox" name="myCheckbox" value="someValue" style="margin: 0 20px;" checked="true" disabled="disabled">
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </c:forEach>
                    </tr>

                </form:form >
            </tbody>
        </table>
        <div class="submitbooking">
            <button onclick="submitForms()" type="submit">ĐĂNG KÝ LÀM</button>
        </div>
    </div>

</div>
<script>
    function submitForms() {
        document.getElementById("lichlam").submit();
    }
    function check(value) {
        const my_list = ${lichhientaica1}; // Giả sử biến lichhientaica1 đã được khai báo và chứa một mảng
        if (my_list.includes(value)) {
            return true;
        } else {
            return false;
        }
    }
</script>
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




</style>



