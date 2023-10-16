<%-- 
    Document   : lapphieukham
    Created on : Aug 19, 2023, 12:16:09 PM
    Author     : Asus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:url value="/bacsi/lapphieukham" var="actions"/>



<nav class="table1 table123">

    <div>
        <div class="profile_bs">
            <c:if test="${pageContext.request.userPrincipal.name != null}">
                <div class="profile_img">
                    <div class="title_name">THÔNG TIN CÁ NHÂN</div>

                    <img src="<c:url value="${user.avt}"/>" alt="err"/>
                </div>
                <h5>Chức vụ: ${user.idRole.chucVu}</h5>
                <h5>Bác sĩ: ${user.hoTen}</h5>
                <h5>Ngày sinh: ${user.ngaySinh}</h5>
                <h5>Giới tính: ${user.gioiTinh}</h5>
                <h5>Sđt: ${user.sdt}</h5>



                <div class="profile_btn">
                    <button class="admin_submit111 admin_submit11111" type="submit">
                        <a href="<c:url value ="/dangkylich"/>">
                            Đăng ký lịch
                        </a>
                    </button>
                </div>
            </c:if>
        </div>
    </div>


    <section class="table__body1">
        <table>
            <thead>
                <tr>
                    <th>Bác sĩ</th>
                    <th>Bệnh nhân</th>

                    <th>Ngày khám</th>
                    <th>Giờ khám</th>
                    <th  id="Username">Trạng thái</th>
                    <th></th>
                </tr>
            </thead>
            <c:forEach items="${dsbenhnhan}" var="d">
                <tbody>
                    <tr>
                        <td>${d.idBs.hoTen}</td>
                        <td>${d.idBn.hoTen}</td>

                        <td>${d.chonNgaykham}</td>
                        <td>${d.thoiGianKham}</td>

                        <td id="xacnhan1">${d.trangThaidky == 1 ? 'Đã xác nhận' : 'Chưa xác nhận'}</td>
                        <td>
                            <button class="admin_submit111">
                                <a href="<c:url value="/bacsi/khambenh/${d.idPhieudk}"/>" > 
                                    Khám bệnh
                                </a>
                            </button>
                        </td>
                    </tr>
                </tbody>
            </c:forEach>
        </table>
    </section>




</nav>


