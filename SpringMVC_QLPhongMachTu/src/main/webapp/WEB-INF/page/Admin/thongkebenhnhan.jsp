<%-- 
    Document   : thongke
    Created on : Aug 30, 2023, 3:49:40 PM
    Author     : Asus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<main class="table tableQLTK">

    <div class="textThongKe">
        <h1>Thống Kê Bệnh Nhân</h1>
    </div>

    <nav class="container_TK">

        <div class="ThongKeBN">
            <div class="TK_Thang">
                <h2>Thống Kê Bệnh Nhân Theo Tháng</h2>
                <form action="${pageContext.request.contextPath}/admin/thongkebenhnhan" method="post">
                    <label for="year">Chọn năm</label>
                    <select id="year" name="year">
                        <c:forEach var="year" begin="2020" end="2030">
                            <option value="${year}">${year}</option>
                        </c:forEach>
                    </select>
                    <br>
                    <label for="month">Chọn tháng</label>
                    <select id="month" name="month">
                        <c:forEach var="month" begin="1" end="12">
                            <option value="${month}">Tháng ${month}</option>
                        </c:forEach>
                    </select>
                    <br>
                    <button type="submit" >Thống kê</button>
                </form>

            </div>


            <div id="chart_div" style="width: 100%; height: 350px"></div>
        </div>




        <div class="ThongKeBN ThongKeBN123">
            <div class="TK_Quy TK_Quy11">
                <h2>Thống Kê Bệnh Nhân Theo Quý</h2>
                <form style="text-align: justify" action="${pageContext.request.contextPath}/admin/thongkebenhnhan" method="post">
                    <label for="year">Chọn năm</label>
                    <select id="year" name="year">
                        <c:forEach var="year" begin="2020" end="2030">
                            <option value="${year}">${year}</option>
                        </c:forEach>
                    </select>
                    <br>
                    <label for="month">Chọn quý</label>
                    <select id="month" name="month">
                        <c:forEach var="month" begin="1" end="4">
                            <option value="${month}">Quý ${month}</option>
                        </c:forEach>
                    </select>
                    <br>
                    <button type="submit" >Thống kê</button>
                </form>

            </div>

            <div id="chart_div1" style="width: 110%; height: 350px"></div>
        </div>


    </nav>









    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
        google.charts.load('current', {'packages': ['corechart']});
        google.charts.setOnLoadCallback(drawChart);

//        var khachHangCount = ${countKhachHang};
        var benhNhanCount = ${countBenhNhan};
        var TrangThaiKhamCount = ${countTrangThaiKham};
        function drawChart() {
            var data = google.visualization.arrayToDataTable([
                ['Loại', 'Số lượng'],

                ['Bệnh Nhân Chưa Khám', benhNhanCount],
                ['Bệnh Nhân Đã Khám', TrangThaiKhamCount]

            ]);

            var options = {
                chart: {
                    title: 'Thống kê số lượng',
                    subtitle: 'số lượng bệnh nhân của bệnh viện'
                },
                bars: 'vertical'
            };

            var chart = new google.visualization.PieChart(document.getElementById('chart_div'));


            chart.draw(data, options);
        }




    </script>



    <script type="text/javascript">
        google.charts.load('current', {'packages': ['corechart']});
        google.charts.setOnLoadCallback(drawChart);

        var benhNhanCount1 = ${countBenhNhan1};
        var TrangThaiKhamCount1 = ${countTrangThaiKham1};
        function drawChart() {
            var data = google.visualization.arrayToDataTable([
                ['Loại', 'Số lượng'],

                ['Bệnh Nhân Chưa Khám', benhNhanCount1],
                ['Bệnh Nhân Đã Khám', TrangThaiKhamCount1]

            ]);

            var options = {
                chart: {
                    title: 'Thống kê số lượng',
                    subtitle: 'số lượng bệnh nhân của bệnh viện'
                },
                bars: 'vertical'
            };

            var chart = new google.visualization.PieChart(document.getElementById('chart_div1'));


            chart.draw(data, options);
        }




    </script>