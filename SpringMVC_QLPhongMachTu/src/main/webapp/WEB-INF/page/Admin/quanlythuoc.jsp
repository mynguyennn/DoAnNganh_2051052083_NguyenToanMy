<%-- 
    Document   : quanlyAdmin
    Created on : Aug 2, 2023, 3:45:24 PM
    Author     : Asus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<c:url value="/admin/quanlythuoc" var="actions"/>

<main class="table table1"> 
    <div>
        <c:if test="${err != null}">
            <div class="alert1">
                ${err}
            </div>
        </c:if>
        <section class="table__header">
            <h3>DANH SÁCH THUỐC</h3>
            <div class="inputkw">
                <form action="${actions}">
                    <input name="kw" type="search" placeholder="Tìm kiếm...">
                    <button type="submit"> <i class="fa-solid fa-magnifying-glass"></i> </button>
                </form>
            </div>
        </section>

        <form:form method="post"  action="${actions}" modelAttribute="thuoc" enctype="multipart/form-data">

            <section class="table__body table__body1 table__body1_loaiThuoc">
                <table>
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Tên Thuốc</th>
                            <th>Xuất Xứ</th>
                            <th>Giá Thuốc</th>
                            <th>Đơn Vị </th>
                            <th>Số Lượng</th>
                            <th>Loại thuốc</th>
                            <th></th>
                            <th id="User_Role">Thông báo</th>
                        </tr>
                    </thead>
                    <tbody >
                        <c:forEach items="${qlThuoc}" var="t">
                            <tr >

                                <td>${t.idThuoc}</td>
                                <td>${t.tenThuoc}</td>
                                <td>${t.xuatXu}</td>
                                <td>${t.giaThuoc}</td>
                                <td>${t.donVi.tenDonVi}</td>
                                <td>${t.soLuong}</td>
                                <td>${t.loaiThuoc.tenLoaiThuoc}</td>

                                <td id="xoaThuoc">
                                    <div class="admin_submit admin_submit11" id="capnhathuoc">
                                        <a href="<c:url value="/admin/quanlythuoc/${t.idThuoc}"/>" >
                                            Chọn
                                        </a>
                                    </div>
                                </td>

                                <c:choose>
                                    <c:when test="${t.soLuong <= 10}">
                                        <td>
                                            <div class="admin_submit admin_submit11 admin_submit1111">
                                                Số lượng thuốc sắp hết!  
                                            </div>
                                        </td>
                                    </c:when>
                                    <c:otherwise>
                                        <td>
                                        </td>  
                                    </c:otherwise>
                                </c:choose>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </section>



        </div>
        <form:hidden path="idThuoc"/>

        <div class="change_ac change_ac11">

            <div class="change_ac1">
                <div class="change1">
                    <h5>Tên Thuốc</h5>
                    <form:input type="text" path="tenThuoc" id="tenThuoc" placeholder="" oninput="validateInput(event)"/>
                </div>

                <div class="change1">
                    <h5>Xuất Xứ</h5>
                    <form:input type="text" path="xuatXu" id="xuatXu" placeholder="" oninput="validateInput(event)"/>
                </div>

            </div>

            <div class="change_ac1">
                <div class="change1">
                    <h5>Giá Thuốc</h5>
                    <form:input type="number" min="0" path="giaThuoc" id="giaThuoc" placeholder="" oninput="validateInput(event)" required="true"/>
                </div>

                <!--<div>{thuoc.donVi.iddonVi}</div>-->
                <div class="change1">
                    <h5>Đơn Vị</h5>
                    <form:select class="donvi" name="donVi" id="donVi" path="donVi">
                        <c:forEach items="${donviThuoc}" var="c" >
                            <c:choose>
                                <c:when test="${c.iddonVi == thuoc.donVi.iddonVi}">
                                    <option value="${c.iddonVi}" selected>${c.tenDonVi}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${c.iddonVi}">${c.tenDonVi}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </form:select>
                </div>

            </div>
            <div class="change_ac1">
                <div class="change1">
                    <h5>Số Lượng</h5>
                    <form:input  type="number" min="0" class="custom-input" path="soLuong" id="soLuong" placeholder="" required="true" oninput="validateInput(event)"/>
                </div>
            </div>

            <div class="change_ac1">
                <div class="change1">
                    <h5>Loại thuốc</h5>

                    <form:select class="donvi" name="loaiThuoc" id="loaiThuoc" path="loaiThuoc">
                        <c:forEach items="${loaiThuoc}" var="d" >
                            <c:choose>
                                <c:when test="${d.idloaiThuoc == thuoc.loaiThuoc.idloaiThuoc}">
                                    <option value="${d.idloaiThuoc}" selected>${d.tenLoaiThuoc}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${d.idloaiThuoc}">${d.tenLoaiThuoc}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </form:select>

                </div>
            </div>

            <section class="admin_btn admin_btn1">
                <div class="admin_submit">
                    <button type="submit">
                        <c:choose>

                            <c:when test="${thuoc.idThuoc != null}">
                                CẬP NHẬT
                            </c:when>
                            <c:otherwise>
                                THÊM    
                            </c:otherwise>
                        </c:choose>
                    </button>      
                </div>
            </section>
        </form:form>
    </div>


</main>

<script>

    function validateInput(event) {
        var inputValue = event.target.value;
        if (inputValue.trim() === '') {
            event.target.value = '';
            event.preventDefault();
        }
    }

    function kyTu(event) {
        var inputValue = event.target.value;
        var regex = /^[a-zA-Z0-9]+$/;

        if (!regex.test(inputValue)) {
            event.target.value = '';
            event.preventDefault();
        }
    }
</script>

<script src="<c:url value="/js/main.js" />"></script>


