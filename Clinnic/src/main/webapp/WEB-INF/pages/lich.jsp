<%-- 
    Document   : lich
    Created on : Aug 10, 2023, 3:16:34 PM
    Author     : Trinh Bao Duy
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<form:form method="post" action="${action}" modelAttribute="user">
<c:url value="/lich" var="action" />
<table class="table table-striped">
    <thead>
        <tr>
            <th>ID</th>
            <th>Tên thằng bệnh</th>
            <th>Ngày nó đăng ký khám</th>
            <th>Đã được nhập chưa</th>
            <th>Y tá</th>
            <th>Bác sĩ</th>
            <th>Ngày được khám</th>
            <th></th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${Apo}" var="ds">
            <tr>
                <td>${ds.id}</td>
                <td>${ds.sickpersonId.name}</td>
                <td>${ds.appointmentDate}</td>
                <<td>${ds.status}</td>
                <td>${ds.nurseId.name}</td>
                <td>${ds.doctorId.name}</td>
                <td>${ds.medicalappointmentDate}</td>
                <td>
                    <a href="<c:url value="/lich/${ds.id}"/>" class="btn btn-success">
                        <c:choose>
                            <c:when test="${ds.status == 0}">
                                Xát nhận
                            </c:when>
                            <c:otherwise>
                                Không xát nhận
                            </c:otherwise>
                        </c:choose>
                    </a>



                </td>
            </c:forEach>
    </tbody>
</table>
</form:form>