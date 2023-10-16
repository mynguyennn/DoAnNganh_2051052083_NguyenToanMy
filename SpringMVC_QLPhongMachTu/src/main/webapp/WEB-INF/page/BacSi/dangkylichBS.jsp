<%-- 
    Document   : dangkylich
    Created on : Aug 19, 2023, 12:32:49 PM
    Author     : Asus
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:url value="/bacsi/dangkylichBS" var="actions"/>

<%--<c:if test="${err != null}">--%>
<!--<div class="alert1">-->
<!--{err}-->
<!--</div>-->
<%--</c:if>--%>

<main class="table table324234">

    <div>
        <section class="table__header table__header1515">
            <h3>LỊCH ĐĂNG KÝ</h3>
            <div id="current-time"></div>
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
                        <th></th>
                        <!--                        <th>Trạng thái</th>-->
                        <!--<th></th>-->
                        <!--<th></th>-->
                    </tr>
                </thead>

                <tbody>
                    <c:forEach items="${listCTLT}" var="p" varStatus="status">
                        <%--<form:hidden path="idChiTietTgTruc" id="idChiTietTgTruc_${p.idChiTietTgTruc}"/>--%>
                        <%--<form:hidden path="ngayDkyTruc"/>--%>
                        <%--<form:hidden path="idTk"/>--%>
                        <%--<form:hidden path="idTgTruc"/>--%>
                        <tr>
                            <td>${p.idTk.idTk}</td>
                            <td>${p.idTk.hoTen}</td>
                            <td>${p.idTk.idRole.chucVu}</td>                     
                            <td>[${p.idChiTietTgTruc}] ${p.idTgTruc.buoiTruc} (${p.idTgTruc.batDau} - ${p.idTgTruc.ketThuc})</td>
                            <td> <fmt:formatDate value="${p.ngayDkyTruc}" pattern="EEEE, dd-MM-yyyy" /></td>


                            <c:choose>
                                <c:when test="${p.trangThaiTruc eq 1}">
                                    <td>
                                        <p id="xacnhan1"> Đã điểm danh</p>
                                    </td>
                                </c:when>
                                <c:otherwise>
                                    <td>
                                        <c:choose>
                                            <c:when test="${gioHienTai ge p.idTgTruc.batDau and gioHienTai le p.idTgTruc.ketThuc and ngayHienTai eq p.ngayDkyTruc  }">
                                                <input type="hidden" name="idChiTietTgTruc" id="idChiTietTgTruc" value="${p.idChiTietTgTruc}">                                     
                                                <a href="<c:url value="/bacsi/dangkylichBS/${p.idChiTietTgTruc}"/>">Điểm danh</a>

                                            </c:when>
                                            <c:otherwise>
                                                <p id="xacnhan">Ngoài giờ điểm danh</p>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                </c:otherwise>
                            </c:choose>

                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </section>

    </div>

</main> 