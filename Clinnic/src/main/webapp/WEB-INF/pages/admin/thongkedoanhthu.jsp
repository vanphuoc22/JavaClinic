<%-- 
   Document   : thongke
   Created on : Aug 28, 2023, 2:20:37 PM
   Author     : hung
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>

<c:url value="/admin/thongkedoanhthu" var="action" />
<head>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
</head>
<main class="table">

    <h1>Thống kê số lượng doanh thu</h1>

    <form action="${action}" method="post">

        <label for="year">Chọn năm</label>
        <select id="year" name="yearofnd">
            <c:forEach var="year" begin="2020" end="2030">
                <option value="${year}">${year}</option>
            </c:forEach>
        </select>

        <button type="submit" >Thống kê</button>
    </form>
    <h2>Thống kê doanh thu theo tháng của năm ${um}</h2>
    <div class="chart">
    <canvas id="monthlyRevenueChart"></canvas>

    <table>
        <thead>
            <tr>
                <th></th>
                <th>Số lượng doanh thu</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${listdt}" var="t" varStatus="loop">
                <tr>
                    <td>Tháng ${loop.index+1}</td>
                    <td>${t}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    </div>
    <h2>Thống kê doanh thu theo quý của năm ${um}</h2>
    <div class="chart">
    <canvas id="quarterlyRevenueChart"></canvas>
    <table>
        <thead>
            <tr>
                <th></th>
                <th>Số lượng doanh thu</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${listdtq}" var="t" varStatus="loop">
                <tr>
                    <td>Quý ${loop.index+1}</td>
                    <td>${t}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    </div>
    


    <script>
        // Lấy dữ liệu từ model
        const monthData = ${listdt}
        const quarterData = ${listdtq}
//        Phước------------------------------------------------------------------
        // Dữ liệu doanh thu theo tháng, quý và năm
        const monthlyRevenueData = {
            labels: ['Tháng 1', 'Tháng 2', 'Tháng 3', 'Tháng 4', 'Tháng 5', 'Tháng 6', 'Tháng 7', 'Tháng 8', 'Tháng 9', 'Tháng 10', 'Tháng 11', 'Tháng 12'],
            datasets: [{
                    label: 'Doanh thu',
                    data: monthData,
                    backgroundColor: 'rgba(75, 192, 192, 0.2)',
                    borderColor: 'rgba(75, 192, 192, 1)',
                    borderWidth: 1
                }]
        };

        const quarterlyRevenueData = {
            labels: ['Quý 1', 'Quý 2', 'Quý 3', 'Quý 4'],
            datasets: [{
                    label: 'Doanh thu',
                    data: quarterData,
                    backgroundColor: 'rgba(255, 99, 132, 0.2)',
                    borderColor: 'rgba(255, 99, 132, 1)',
                    borderWidth: 1
                }]
        };

        // Biểu đồ doanh thu theo tháng
        const monthlyRevenueChart = new Chart(document.getElementById('monthlyRevenueChart'), {
//            type: 'polarArea',
//            type: 'pie',
            type: 'bar',
            data: monthlyRevenueData,
            options: {
                scales: {
                    y: {
                        beginAtZero: true
                    }
                }
            }
        });

        // Biểu đồ doanh thu theo quý
        const quarterlyRevenueChart = new Chart(document.getElementById('quarterlyRevenueChart'), {
            type: 'bar',
            data: quarterlyRevenueData,
            options: {
                scales: {
                    y: {
                        beginAtZero: true
                    }
                }
            }
        });
    </script>


</main>

<style>
   h1, h2 {
        text-align: center;
    }

    canvas {
        display: block;
        margin: 50px;
        max-width: 600px;
        max-height: 400px;
    }
    .table {
        /*  margin: 20px auto;
          max-width: 600px;*/
        text-align: center;
    }

    .table h1 {
        font-size: 24px;
        margin-bottom: 10px;
    }

    .table form {
        margin-bottom: 20px;
    }

    .table form label {
        font-weight: bold;
        margin-right: 10px;
    }

    .table form select {
        padding: 4px;
    }

    .table form button {
        padding: 8px 16px;
        background-color: #0080ff;
        color: #fff;
        border: none;
        border-radius: 4px;
        text-decoration: none;
        cursor: pointer;
    }

    .table form button:hover {
        background-color: #0059b3;
    }

    .table h2 {
        font-size: 20px;
        margin-top: 20px;
        margin-bottom: 10px;
    }

    .table canvas {
        margin-top: 10px;
    }

    .table table {
        width: 100%;
        margin-right: 128px;
        border-collapse: collapse;
        margin-top: 20px;
    }

    .table th,
    .table td {
        padding: 6px;
        border: 1px solid #ccc;
        font-size: 14px;
    }

    .table th {
        font-weight: bold;
        background-color: #f5f5f5;
    }

    .table tbody tr:nth-child(even) {
        background-color: #f9f9f9;
    }
    .chart{
        display: flex;
        padding-left: 216px;
    }
</style>

