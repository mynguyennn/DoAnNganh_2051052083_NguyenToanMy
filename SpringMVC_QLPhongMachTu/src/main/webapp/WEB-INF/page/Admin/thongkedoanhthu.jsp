<%-- 
    Document   : thongke
    Created on : Aug 30, 2023, 3:49:40 PM
    Author     : Asus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<main class="table tableTKDT">


    <nav class="container_TK">

        <div class="TK_Quy TK_Quy1111">

            <form action="${pageContext.request.contextPath}/admin/thongkedoanhthu" method="post">
                <h2 id="textTKDT">Thống Kê Doanh Thu</h2>
                <label for="year">Chọn năm</label>
                <select id="year" name="year">
                    <c:forEach var="year" begin="2020" end="2030">
                        <option value="${year}">${year}</option>
                    </c:forEach>
                </select>

                <button type="submit" >Thống kê</button>
            </form>

        </div>


    </nav>

    <div class="bieudo_TK">
        <canvas id="myChart"></canvas>
    </div>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/3.7.0/chart.min.js"></script>

    <script>
        let monthlyRevenues = [${thang1},${thang2},${thang3},${thang4},${thang5},${thang6},${thang7},${thang8},${thang9},${thang10},${thang11},${thang12}];

        var ctx = document.getElementById('myChart').getContext('2d');
        var myChart = new Chart(ctx, {
            type: 'bar',
            data: {
                labels: ['Tháng 1', 'Tháng 2', 'Tháng 3', 'Tháng 4', 'Tháng 5', 'Tháng 6', 'Tháng 7', 'Tháng 8', 'Tháng 9', 'Tháng 10', 'Tháng 11', 'Tháng 12'],
                datasets: [{
                        label: 'Doanh thu theo năm ' + ${year},
                        data: monthlyRevenues,
                        backgroundColor: 'rgba(75, 192, 192, 0.2)', // Customize colors
                        borderColor: 'rgba(75, 192, 192, 1)', // Customize colors
                        borderWidth: 1
                    }]
            },
            options: {
                scales: {
                    y: {
                        beginAtZero: true
                    }
                }
            }
        });

    </script>




    <div class="bieudo_TK">
        <canvas id="myChart1"></canvas>
    </div>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/3.7.0/chart.min.js"></script>

    <script>
        let monthlyRevenuesQuy = [${quy1},${quy2},${quy3},${quy4}];

        var ctx = document.getElementById('myChart1').getContext('2d');
        var myChart = new Chart(ctx, {
            type: 'bar',
            data: {
                labels: ['Quý 1', 'Quý 2', 'Quý 3', 'Quý 4'],
                datasets: [{
                        label: 'Doanh thu theo quý ' + ${year},
                        data: monthlyRevenuesQuy,
                        backgroundColor: 'rgba(75, 192, 192, 0.2)', // Customize colors
                        borderColor: 'rgba(75, 192, 192, 1)', // Customize colors
                        borderWidth: 1
                    }]
            },
            options: {
                scales: {
                    y: {
                        beginAtZero: true
                    }
                }
            }
        });

    </script>


