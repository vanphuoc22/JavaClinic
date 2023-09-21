<%-- 
   Document   : thanhtoan
   Created on : Aug 15, 2023, 1:27:23 PM
   Author     : hung
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:url value="/nurse/thanhtoan" var="action" />
<div class="main">
    <div class="container">
        <h1>Thanh toán</h1>

        <h2>Thông tin hóa đơn</h2>
        <p>Mã hóa đơn: <span id="invoice-number">${appo.id}</span></p>
        <p>Ngày khám: <span id="appointment-date"><fmt:formatDate value="${appo.appointmentDate}" pattern="dd/MM/yyyy" /></span></p>
        <p>Tên bệnh nhân: <span id="patient-name">${appo.sickpersonId.name}</span></p>
        <p>Số điện thoại: <span id="phone-number">${appo.sickpersonId.phone}</span></p>
        <hr/>

        <h2>Thông tin thuốc đã mua</h2>
        <table id="thuoc-table">
            <thead>
                <tr>
                    <th>Tên thuốc</th>
                    <th>Số lượng</th>
                    <th>Giá tiền</th>
                    <th>Hướng dẫn sử dụng</th>
                    <th>Tổng tiền</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${thuoc}" var="t">
                    <tr>
                        <td>${t.medicineId.name}(${t.medicineId.provider})</td>
                        <td>${t.quantity}</td>
                        <td>${t.medicineId.price} VNĐ</td>
                        <td>${t.instructions}</td>
                        <td>${t.quantity * t.medicineId.price} VNĐ </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <h2>Thông tin dịch vụ đã sử dụng</h2>
        <table id="dichvu-table">
            <thead>
                <tr>
                    <th>Tên dịch vụ</th>
                    <th>Đơn giá</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${dichvu}" var="d">
                    <tr>
                        <td>${d.serviceId.name}</td>
                        <td>${d.serviceId.price} VNĐ</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <hr/>
        <form:form method="post" action="${action}" modelAttribute="bill">
            <div class="payment-section">
                <h2>Phần thanh toán</h2>
                <form:hidden id="payMoney" path="payMoney" value = "${tongtien}"/>
                <form:hidden path="id"/>
                <form:hidden id="tienkham" path="tienkham"/>
                <form:hidden id="appoId" path="appoId"/>
                <p>Tổng tiền: <span id="total-amount">${tongtien} VNĐ</span></p>

                <div class="payment-method">
                    <label>Phương thức thanh toán:</label>
                    <form:select class="form-select" id="pay" name="payId" path="payId">
                        <c:forEach items="${pay}" var="r">
                            <c:choose>
                                <c:when test="${r.id == bill.payId.id}">
                                    <option value="${r.id}" selected>${r.paymentMethod}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${r.id}">${r.paymentMethod}</option>
                                </c:otherwise>
                            </c:choose>

                        </c:forEach>
                    </form:select>
                </div>

                <div id="cash-payment" class="payment-method">
                    <label>Tiền khách đưa:</label>
                    <input type="number" id="customer-payment" oninput="calculateChange()" />
                </div>

                <div id="cash-payment" class="payment-method">
                    <label>Tiền Trả khách:</label>
                    <input type="text" id="change-amount" readonly />
                </div>


                <button class="bt1" type="submit">Thanh toán</button>
            </div>
        </form:form>
        <input type="hidden" value = "/Clinnic/paymomo?id=${bill.id}"  name="momo"/> 
        <input type="hidden" name="id" value="${bill.id}" />
    </div>
</div>

<script>

    function calculateChange() {
        var customerPayment = parseFloat(document.getElementById('customer-payment').value);
        var totalAmount = parseFloat(document.getElementById('total-amount').textContent);
        var changeAmount = customerPayment - totalAmount;

        var formattedChangeAmount = formatCurrency(changeAmount);
        document.getElementById('change-amount').value = formattedChangeAmount;
    }

    function formatCurrency(amount) {
        var formatter = new Intl.NumberFormat('vi-VN', {
            style: 'currency',
            currency: 'VND',
            minimumFractionDigits: 0,
            maximumFractionDigits: 0
        });

        if (amount < 0) {
            return 'Thiếu ' + formatter.format(Math.abs(amount));
        } else {
            return formatter.format(amount);
        }
    }

</script>
<style>


    .container {

        margin: 30px auto;
        padding: 20px;
        border: 0.5px solid #5AAC4E;
        border-radius: 30px;
        overflow: auto;
        


    }
    .main{
        margin-bottom: 10%;
    }

    h1 {
        text-align: center;
    }
    thead>tr>th{
        text-align: center;
    }

    table {

        border-collapse: collapse;
        margin-top: 20px;
        margin-bottom: 40px;
        table-layout: fixed;
        width: 100%;
    }
    thead {
        color: white;

    }
    th, td {
        padding: 10px;
        text-align: left;
        border-bottom: 1px solid #ddd;
        white-space: nowrap;
        width: 20%;
        word-break: break-all;

    }


    th {
        background-color: #198754;
        ;
    }
    .input{
        background-color: #4CAF50;
        color: white;
        padding: 12px 20px;
        border: none;
        border-radius: 10px;
        cursor: pointer;
        width: 100%;
        margin-top: 50px;
        margin: 42%;
        text-decoration: none;
    }
    .payment-section {
        margin-top: 20px;
    }

    .payment-method {
        display: inline-block;
        margin-right: 10px;
    }

    .hidden {
        display: none;
    }

     
    .bt1 {
        background-color: #4CAF50;  
        border: none;  
        color: white;  
        padding: 10px 20px;  
        text-align: center;  
        text-decoration: none;  
        display: inline-block;  
        font-size: 16px;  
        margin: 4px 2px;  
        cursor: pointer;  
        border-radius: 4px;  
    }
 
    .bt1:hover {
        background-color: #45a049;  
    }

     

</style>

