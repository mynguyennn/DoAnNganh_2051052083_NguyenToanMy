<%-- 
    Document   : quanlytk
    Created on : Aug 6, 2023, 1:16:40 AM
    Author     : Asus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:url value="/admin/quanlytaikhoan" var="actions"/>


<main class="table">
    <div>
        <c:if test="${err != null}">
            <div class="alert1">
                ${err}
            </div>
        </c:if>

        <section class="table__header">
            <h3>DANH SÁCH TÀI KHOẢN</h3>
            <div class="inputkw">
                <form action="${actions}">
                    <input name="kw" type="search" placeholder="Tìm kiếm...">
                    <button type="submit"> <i class="fa-solid fa-magnifying-glass"></i> </button>
                </form>
            </div>
        </section>

        <form:form method="post" modelAttribute="addtaikhoan" action="${actions}" enctype="multipart/form-data">
            <section class="table__body">
                <table>
                    <thead>
                        <tr>
                            <th></th>
                            <th>Avatar</th>
                            <th>ID</th>
                            <th>Họ tên </th>
                            <th id="Username">Username</th>
                            <th id="Password">Password</th>
                            <th id="User_Role">Chức vụ</th>
                            <th>Email</th>
                            <th>SĐT</th>
                            <th>Năm sinh</th>
                            <th>Giới tính</th>
                            <th>Địa chỉ</th>


                        </tr>
                    </thead>

                    <tbody>
                        <c:forEach items="${qltaikhoan}" var="p">


                            <tr>
                                <td>
                                    <c:url value="/admin/quanlytaikhoan/${p.idTk}" var="apiDel"/>
                                    <div class="admin_submit admin_submit11" onclick="xoaTaiKhoan('${apiDel}')">
                                        XÓA  
                                    </div>
                                </td>


                                <td>
                                    <a href="<c:url value="/admin/quanlytaikhoan/${p.idTk}"/>" >
                                        <img src="${p.avt}" alt="null" width="65" />
                                    </a>
                                </td>

                                <td>${p.idTk}</td>
                                <td>${p.hoTen}</td>
                                <td>${p.taiKhoan}</td>
                                <td id="pass-text"><i class="fa-solid fa-eye-slash"></i></td>
                                <td>${p.idRole.chucVu}</td>
                                <td>${p.email}</td>
                                <td>${p.sdt}</td>
                                <td>${p.ngaySinh}</td>
                                <td>${p.gioiTinh}</td>
                                <td>${p.diaChi}</td>


                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </section>

            <section class="admin_btn">
                <!--                <div class="admin_submit">
                                    <button type="submit">THÊM</button>         
                                </div>-->

                <div class="admin_submit">
                    <button type="submit">
                        <c:choose>
                            <c:when test="${addtaikhoan.idTk != null}">
                                CẬP NHẬT
                            </c:when>
                            <c:otherwise>
                                THÊM    
                            </c:otherwise>
                        </c:choose>

                    </button>      
                </div>

                <!--                <div class="admin_submit">
                                    <button type="submit">TẢI LẠI</button>   
                                </div>-->

            </section>

        </div>
        <!--                <div class="change1">
                            <h5>ID</h5>
                            <input type="text" id="idTk" placeholder="" disabled/>
                        </div>-->



        <form:hidden path="idTk"/>
        <form:hidden path="avt"/>
        <%--<form:hidden path="matKhau"/>--%>

        <div class="change_ac change_acQLTK">

            <div class="change_ac1">



                <div class="change1 change11 change11TK">
                    <h5>Họ tên</h5>
                    <form:input type="text" path="hoTen" id="hoTen" placeholder="" oninput="validateInput(event)"/>
                </div>

                <div class="change1 change11">
                    <h5>Ngày sinh</h5>
                    <form:input type="date" path="ngaySinh" id="ngaySinhAdmin" placeholder="" required="true"/>
                </div>

            </div>


            <div class="change_ac1">
                <div class="change1 change11">
                    <h5>Giới tính</h5>
                    <%--<form:input type="text" path="gioiTinh" id="gioiTinh" placeholder=""/>--%>
                    <form:select path="gioiTinh" id="gioiTinh" class="form-select" cssErrorClass="is-invalid">
                        <form:option value="" label="Giới tính" />
                        <form:option value="Nam" label="Nam" />
                        <form:option value="Nữ" label="Nữ" />
                        <form:option value="Khác" label="Khác" />
                    </form:select>

                </div>

                <div class="change1 change11">
                    <h5>Email</h5>
                    <form:input type="email" id="email" path="email"  placeholder="" oninput="validateInput(event)"/>
                </div>

            </div>



            <div class="change_ac1">
                <div class="change1 change11">
                    <h5>Địa chỉ</h5>
                    <form:input type="text" path="diaChi" id="diaChi" placeholder="" oninput="validateInput(event)"/>
                </div>
                <div class="change1 change11">
                    <h5>SÐT</h5>
                    <form:input type="text" path="sdt" id="sdt" placeholder="" oninput="validateInput(event)"/>
                </div>

            </div>


            <div class="change_ac1">


                <div class="change1 change11">
                    <h5>Username</h5>
                    <form:input type="text" path="taiKhoan" id="taiKhoan" placeholder="" oninput=" kyTu(event)"/>
                </div>

                <div class="change1 change11">
                    <h5>Password</h5>
                    <c:choose>
                        <c:when test="${addtaikhoan.idTk == null}"> 
                            <form:input type="text" path="matKhau" id="matKhau" placeholder="" oninput=" kyTu(event)"/>
                        </c:when>
                        <c:otherwise>
                            <form:input type="text" path="matKhau" id="matKhau" placeholder="" readonly="true" oninput=" kyTu(event)"/>
                        </c:otherwise>
                    </c:choose>
                </div>

            </div>


            <div class="change_ac1" id="role_main">
                <div class="change1 change11">
                    <h5>Chức vụ</h5>
                    <form:select class="role" name="role" id="role" path="idRole">
                        <form:option value="" label="Chức Vụ"/>
                        <c:forEach items="${user_role}" var="c" >
                            <c:choose>
                                <c:when test="${c.idRole == addtaikhoan.idRole.idRole}">
                                    <option value="${c.idRole}" selected>${c.chucVu}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${c.idRole}">${c.chucVu}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </form:select>
                </div>

                <div class="change1 change11">
                    <h5>Avatar</h5>
                    <form:input type="file" id="upload" path="file" placeholder="Upload Avatar" required="true"/>
                </div>

            </div>
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
