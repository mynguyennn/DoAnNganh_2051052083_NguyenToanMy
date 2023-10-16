<%-- 
    Document   : header
    Created on : Jul 25, 2023, 4:06:41 PM
    Author     : Asus
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>



<form:form method="post" action="${action}" modelAttribute="user" 
           enctype="multipart/form-data">
    <div class="containerr">
        <header>
            <a href="<c:url value ="/"/>" class="logo">
                <h3>Your Health.</h3>
            </a>        
            <div class="navbar">
                <ul class="list">
                    <c:if test="${pageContext.request.userPrincipal.name == null}">
                        <li class="tab"><a href="<c:url value ="/dangnhap"/>">Đăng nhập</a></li>
                        <li class="tab"><a href="<c:url value ="/dangky"/>">Đăng ký</a></li>
                        </c:if>



                    <c:if test="${pageContext.request.userPrincipal.name != null}">

                        <li class="tab1">
                            <div class="avt_user">
                                <img src="<c:url value="${user.avt}"/>" alt="" />
                            </div>
                        </li> 

                        <li class="tab1"><a href="#">Xin chào, ${user.hoTen}!</a></li>
                        </c:if>

                    <sec:authorize access="hasRole('BENHNHAN')">
                        <li class="tab"><a href="<c:url value ="/benhnhan/dangkykham"/>">Đăng ký khám</a></li>
                        <li class="tab"><a href="<c:url value ="/benhnhan/lichsukham"/>">Lịch sử khám bệnh</a></li>
                        
                        </sec:authorize>


                    <sec:authorize access="hasRole('YTA')">
                        <li class="tab"><a href="<c:url value ="/yta/lapdskham"/>">Lập danh sách khám</a></li>
                        </sec:authorize>

                    <sec:authorize access="hasRole('YTA')">
                        <li class="tab"><a href="<c:url value ="/yta/thanhtoan"/>">Thanh toán hóa đơn</a></li>
                        </sec:authorize>

                    <sec:authorize access="hasRole('BACSI')">
                        <li class="tab"><a href="<c:url value ="/bacsi/lapphieukham"/>">Lập phiếu khám bệnh</a></li>
                        </sec:authorize>


                    <%--<sec:authorize access="hasRole('BACSI')">--%>
                        <!--<li class="tab"><a href="<c:url value ="/bacsi/capthuoc"/>">Cap Thuoc</a></li>-->
                    <%--</sec:authorize>--%>

                    <sec:authorize access="hasRole('BACSI') or hasRole('BACSI')">
                        <li class="tab"><a href="<c:url value ="/bacsi/dangkylichBS"/>">Đăng ký lịch trực</a></li>
                    </sec:authorize>
                        
                        <sec:authorize access="hasRole('YTA') or hasRole('YTA')">
                        <li class="tab"><a href="<c:url value ="/yta/dangkylichYT"/>">Đăng ký lịch trực</a></li>
                    </sec:authorize>


                    <c:if test="${pageContext.request.userPrincipal.name != null}">
                        <li class="tab"><a href="<c:url value ="/doimatkhau"/>">Đổi mật khẩu</a></li>
                        </c:if>


                    <c:if test="${pageContext.request.userPrincipal.name != null}">
                        <li class="tab" style="color: darkred"><a href="<c:url value ="/logout"/>">Đăng xuất</a></li>
                        </c:if>






                </ul>
            </div>
        </header>
    </div>
</form:form>    