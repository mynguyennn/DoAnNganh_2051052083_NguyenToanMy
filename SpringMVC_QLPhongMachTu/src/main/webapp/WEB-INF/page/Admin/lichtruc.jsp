<%-- 
    Document   : lichtruc
    Created on : Aug 30, 2023, 5:00:50 PM
    Author     : LENOVO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:url value="/admin/lichtruc" var="actions"/>





<main class="table">

    <c:if test="${msg != null}">
        <div class="alert1">
            ${msg}
        </div>
    </c:if>


    <div>
        <div class="textThongKe">
            <h1>ĐĂNG KÝ LỊCH TRỰC</h1>
        </div>


        <section class="table__body">
            <table>
                <thead>
                    <tr>
                        <!--                        <th></th>-->
                        <th>Avatar</th>
                        <th>ID</th>
                        <th>Họ tên </th>
                        <th id="Username">Username</th>
                        <!--                        <th id="Password">Password</th>-->
                        <th id="User_Role">Chức vụ</th>
                        <!--<th>Email</th>-->
                        <!--<th>SĐT</th>-->
                        <!--<th>Năm sinh</th>-->
                        <!--<th>Giới tính</th>-->
                        <!--<th>Địa chỉ</th>-->


                    </tr>
                </thead>

                <tbody>
                    <c:forEach items="${tk}" var="p">


                        <tr>



                            <td>
                                <a href="<c:url value="/admin/lichtruc/${p.idTk}"/>" >
                                    <img src="${p.avt}" alt="null" width="65" />
                                </a>
                            </td>

                            <td>${p.idTk}</td>
                            <td>${p.hoTen}</td>    
                            <td>${p.taiKhoan}</td>


                            <td>${p.idRole.chucVu}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </section>
        </form>
    </div>
</main>


<div class="contentdkk3 contentdkk31 contentdkk3122">
    <div >
        <h5>ID </h5>
        <input type="text" path="idTk" placeholder="${idtk.idTk}" disabled="true"/>
    </div>

    <div>
        <h5>Họ tên</h5>
        <input type="text" path="hoTen" placeholder="${idtk.hoTen}" disabled="true"/>
    </div>

    <div>
        <h5>Chức vụ</h5>
        <input type="text" path="chucVu" placeholder="${idtk.idRole.chucVu}" disabled="true"/>
    </div>


</div>



<c:choose>
    <c:when test="${idtk.idTk != null}">
        <form:form id="myForm"  method="post" modelAttribute="lichtruc" action="${actions}" enctype="multipart/form-data">
            <!--    <div class="text-lsk text-lsk111">
                    <p>Đăng ký lịch trực</p>
                </div>-->

            <main class="table lskham saplichlam">
                <div>

                    <section class="table__body lskham1 dkylich dkylichtruc">
                        <div class="thoigianadmin">
                            <div class="text-lsk text-lsk111 text-lsk11122">
                                <p>Đăng ký lịch trực</p>
                            </div>
                            <div id="current-time"></div>

                        </div>

                        <table>
                            <thead>

                                <tr>
                                    <th>Buổi/Ca</th>
                                        <c:forEach items="${dateList}" var="date">
                                        <td>
                                            <%--<form:checkbox path="listDate" value="${date}"/>--%>
                                            <fmt:formatDate value="${date}" pattern="EEEE - dd MMMM"/>
                                        </td>
                                    </c:forEach>

                                </tr>

                            </thead>

                            <tbody>
                                <c:forEach items="${caTruc}" var="caTruc" >
                                    <tr id="input_dky">
                                        <td>${caTruc.buoiTruc}</td>
                                        <c:forEach items="${dateList}" var="date">
                                            <td><input type="checkbox" id="checkbox${caTruc.idtgTruc}-${date.year+1900}-${date.month+1}-${date.date}" name="selectedDates" value="${caTruc.idtgTruc}-${date.year+1900}-${date.month+1}-${date.date}"></td>


                                        </c:forEach>
                                    </tr>
                                </c:forEach>

                            </tbody>
                            <input type="hidden" name="caTrucId" value="${caTrucId.idtgTruc}" />
                            <input type="hidden" id="dateField" name="selectedDates" value="">
                            <input type="hidden" name="id" value="${idtk.idTk}" />
                        </table>


                    </section>

                    <div class="login0111 login01111515">
                        <div class="one2111 one2111 one211122123">
                            <button onclick="showConfirm()" type="submit" >Xác nhận</button>
                        </div>
                    </div>
                </div>

            </main>



        </form:form>
    </c:when>
</c:choose>

<main class="table">
    <div>
        <section class="table__header table__header1515">
            <h3>DANH SÁCH LỊCH ĐĂNG KÝ</h3>
        </section>


        <section class="table__body">
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Họ tên</th>
                        <th>Chức vụ</th>
                        <th>Ca trực</th>
                        <th>Ngày đăng ký</th>
                        <th>Trạng thái</th>
                        <th></th>
                    </tr>
                </thead>

                <tbody>
                    <c:forEach items="${listCTLT}" var="p">


                        <tr>
                            <td>${p.idChiTietTgTruc}</td>
                            <td>${p.idTk.hoTen} [${p.idTk.idTk}]</td>
                            <td>${p.idTk.idRole.chucVu}</td>                     
                            <td>${p.idTgTruc.buoiTruc}</td>
                            <td>${p.ngayDkyTruc}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${p.trangThaiTruc.toString() eq 0}">
                                        <p id="xacnhan">Chưa xác nhận</p>
                                    </c:when>
                                    <c:otherwise>
                                        <p id="xacnhan1">Đã xác nhận</p>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                <c:url value="/admin/lichtruc/${p.idChiTietTgTruc}" var="apiDel"/>
                                <div class="admin_submit admin_submit11" onclick="xoaLichTruc('${apiDel}')">
                                    XÓA  
                                </div>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </section>
        </form>
    </div>
</main> 

<script src="<c:url value="/js/main.js" />"></script>

<script>
                                    function displayCurrentTime() {
                                        var currentTime = new Date();

                                        var daysOfWeek = ["Chủ Nhật", "Thứ Hai", "Thứ Ba", "Thứ Tư", "Thứ Năm", "Thứ Sáu", "Thứ Bảy"];
                                        var dayOfWeek = daysOfWeek[currentTime.getDay()];

                                        var day = currentTime.getDate();
                                        var month = currentTime.getMonth() + 1; // Lưu ý: Tháng bắt đầu từ 0
                                        var year = currentTime.getFullYear();

                                        var hours = currentTime.getHours();
                                        var minutes = currentTime.getMinutes();
                                        var seconds = currentTime.getSeconds();

                                        var formattedTime = dayOfWeek + ", " + day + "-" + month + "-" + year + " " + hours + ":" + minutes + ":" + seconds;

                                        document.getElementById("current-time").textContent = formattedTime;
                                    }

                                    // Gọi hàm displayCurrentTime một lần khi trang được tải và sau đó mỗi giây
                                    displayCurrentTime();
                                    setInterval(displayCurrentTime, 1000);
</script>
