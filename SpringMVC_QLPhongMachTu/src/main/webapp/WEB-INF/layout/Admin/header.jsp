<%-- 
    Document   : header
    Created on : Jul 25, 2023, 4:06:41 PM
    Author     : Asus
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div class="l-navbar" id="navbar">
    <div class="nav">
        <div class="nav_main animate__animated animate__fadeInLeft">
            <a href="#" class="nav__logo">
                <span class="nav__logo-text">Your Health.</span>
            </a>

            <sec:authorize access="hasRole('ADMIN')">
                <ul class="nav__list">
                    <a href="<c:url value ="/admin/quanlytaikhoan"/>" class="nav__link active">
                        <i class='bx bx-user' ></i>
                        <span class="nav__text">Tài khoản</span>
                    </a>

                    <a href="<c:url value ="/admin/quanlythuoc"/>" class="nav__link active">
                        <i class='bx bxs-capsule '></i>
                        <span class="nav__text">Thuốc</span>
                    </a>

                    <a href="<c:url value ="/admin/lichtruc"/>" class="nav__link active">
                        <i class='bx bx-time-five ' ></i>
                        <span class="nav__text">Lịch trực</span>
                    </a>


                    <a href="<c:url value ="/admin/tienkham"/>" class="nav__link active">
                        <i class='bx bx-money-withdraw'></i>
                        <span class="nav__text">Tiền khám</span>
                    </a>

                    <a href="<c:url value ="/admin/dichvu"/>" class="nav__link active">
                        <i class='bx bx-donate-heart'></i>
                        <span class="nav__text">Dịch vụ</span>
                    </a>


                    <!--                    <div class="thongke_admin">
                                            <p>THỐNG KÊ</p>
                                        </div>-->

                    <a href="<c:url value ="/admin/thongkebenhnhan"/>" class="nav__link active">
                        <i class='bx bxs-face'></i>
                        <span class="nav__text">TK Bệnh nhân</span>
                    </a>

                    <a href="<c:url value ="/admin/thongkedoanhthu"/>" class="nav__link active">
                        <i class='bx bx-stats ' ></i>
                        <span class="nav__text">TK Doanh thu</span>
                    </a>
                    <a href="<c:url value ="/admin/thongkedanhgia"/>" class="nav__link active">
                        <i class='bx bx-stats ' ></i>
                        <span class="nav__text">TK Đánh giá</span>
                    </a>

                </ul>
            </div>
        </sec:authorize>

        <c:if test="${pageContext.request.userPrincipal.name != null}">
            <a href="<c:url value ="/logout"/>" class="nav__link active animate__animated animate__fadeInLeft" id="active1">
                <i class='bx bx-log-out-circle' ></i>
                <span class="nav__text">Đăng xuất</span>
            </a>
        </c:if>

    </div>
</div>

    <!--<script src="<c:url value="/js/main.js" />"></script>-->