<%-- 
    Document   : thanhtoan
    Created on : Aug 29, 2023, 3:42:02 PM
    Author     : Asus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:url value="/yta/thanhtoan" var="actions"/>


<nav class="header-lapdskham">
    <div class="text-lsk lsk1">
        <p>Thanh toán hóa đơn</p>
    </div>
    <div class="lapdskham_search thanhtoan_search">
        <form action="${actions}">
            <input name="kw" type="number" min="0" placeholder="Tìm kiếm theo phiếu đăng ký...">
            <button type="submit"> <i class="fa-solid fa-magnifying-glass"></i> </button>
        </form>
    </div>
</nav>

<nav class="table1 table123 table1234">


    <section class="table__body1">
        <form>
            <table>
                <thead>
                    <tr>
                        <!--<th>ID Hóa đơn</th>-->
                        <th>ID Phiếu Đăng Ký</th>

                        <th>Tên bệnh nhân</th>
                        <th>Tiền khám</th>
                        <th>Tiền thuốc</th>
                        <th>Tiền dịch vụ</th>

                        <th id="User_Role">Tổng tiền</th>
                        <th>Ngày thanh toán</th>
                        <th></th>

                    </tr>
                </thead>
                <c:forEach items="${listHÐ}" var="d">
                    <tbody>
                        <tr>
                            <!--<td>{d.idHoadon}</td>-->
                            <td>${d.idPhieudky.idPhieudk}</td>
                            <td>${d.idPhieudky.idBn.hoTen}</td>

                            <td>${d.tienKham.tienKham}</td>
                            <td>${d.tienThuoc}</td>
                            <td>${d.tienDv}</td>

                            <td>${d.tienKham.tienKham + d.tienThuoc + d.tienDv}vnđ</td>
                            <td>
                                <fmt:formatDate value="${d.ngayThanhToan}" pattern="EEEE, dd/MM/yyyy HH:mm:ss"/> 
                            </td>

                            <td>
                                <c:choose>
                                    <c:when test="${d.ngayThanhToan == null}">
                                        <a id="taoHoaDon" href="<c:url value="/yta/taohoadon/${d.idHoadon}"/>" > 
                                            Tạo hóa đơn
                                        </a>
                                    </c:when>
                                    <c:otherwise>
                                        <p id="xacnhan1">Đã thanh toán</p>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                    </tbody>
                </c:forEach>
            </table>
        </form>
    </section>




</nav>

<script src="<c:url value="/js/main.js" />"></script>



