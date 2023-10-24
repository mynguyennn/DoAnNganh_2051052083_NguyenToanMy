<%-- 
    Document   : thongkedanhgia
    Created on : Oct 15, 2023, 9:51:02 PM
    Author     : LENOVO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<main class="table tableTKDT">


    <nav class="container_TK">

        <div class="TK_Quy TK_Quy1111">
            <form action="${pageContext.request.contextPath}/admin/thongkedanhgia" method="post">
                <h2 id="textTKDT">Thống Kê Đánh Giá</h2>        
                <button type="submit" >Thống kê</button>
            </form>
        </div>
    </nav>

    <div style="width: 80%; margin: 0 auto;"  class="bieudo_TK">
        <canvas id="myLineChart"></canvas>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script><!-- comment -->
    <script>
        // Sample data
        var danhgia = ${tb};

        var bs = ${dsbacsi};

//        //Tạo mảng nhãn từ danh sách bs
//        var labels = bs.map(function (dsbacsi) {
//            return bacsi.label;
//        });
//
//// Tạo mảng dữ liệu từ danh sách danhgia
//        var data = danhgia.map(function (tb) {
//            return dg.data;
//        });

        var ctx = document.getElementById('myLineChart').getContext('2d');

        var myChart = new Chart(ctx, {
            type: 'line',
            data: {
                labels: bs,
                datasets: [{
                        label: 'Trung Bình Đánh Giá',
                        data: danhgia,
                        borderColor: 'rgba(75, 192, 192, 1)',
                        borderWidth: 2,
                        pointBackgroundColor: 'rgba(75, 192, 192, 1)',
                        pointRadius: 5
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
