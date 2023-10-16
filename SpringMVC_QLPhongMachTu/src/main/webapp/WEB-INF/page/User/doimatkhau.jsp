<%-- 
    Document   : doimatkhau
    Created on : Aug 12, 2023, 11:18:41 PM
    Author     : Asus
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<c:if test="${err != null}">
    <div class="alert1">
        ${err}
    </div>
</c:if>

<c:url value="/doimatkhau" var="action">
    <c:param name="idNguoiDung" value="${user.idTk}" />
</c:url>


<form class="form_login11" method="post" action="${action}">

    <%--<c:if test="${not empty error}">--%>
    <!--<p style="color: red">{error}</p>-->
    <%--</c:if>--%>

    <%--<c:if test="${not empty success}">--%>
    <!--<p style="color: green">{success}</p>-->
    <%--</c:if>--%>

    <nav class="login111">

        <div class="login_main1111 login_main11114545">  

            <div class="dky111">
                <p>ĐỔI MẬT KHẨU</p>

                <div class="login0111">
                    <div class="one111">
                        <div class="one1111">
                            <div id="tk1111"><i class="fa-solid fa-unlock"></i></div>
                        </div>

                        <div class="one1111">
                            <input type="password" id="matKhauHienTai" name="matKhauHienTai" placeholder="Nhập mật khẩu hiện tại" oninput="validateInput(event)"/>
                        </div>
                    </div>
                </div>

                <div class="login0111">
                    <div class="one111">
                        <div class="one1111">
                            <div id="tk1111"><i class="fa-solid fa-key"></i></div>
                        </div>

                        <div class="one1111">
                            <input type="password" id="matKhauMoi" name="matKhauMoi" placeholder="Nhập mật khẩu mới" oninput="validateInput(event)"/>
                            <%--<form:errors path="matKhau" element="div" cssClass="alert alert-danger" />--%>
                        </div>

                    </div>
                </div>



                <div class="login0111">
                    <div class="one111">
                        <div class="one1111">
                            <div id="tk111"><i class="fa-solid fa-key"></i></div>
                        </div>

                        <div class="one1111">
                            <input type="password" id="xacNhanMatKhauMoi" name="xacNhanMatKhauMoi" placeholder="Nhập lại mật khẩu mới" oninput="validateInput(event)"/>
                        </div>

                    </div>
                </div>
            </div>

            <div class="login0111">
                <div class="one2111">
                    <button type="submit" >Đổi mật khẩu</button>
                </div>
            </div>

        </div>

    </nav>
</form>
<script>
    function validateInput(event) {
        var inputValue = event.target.value;
        var regex = /^[a-zA-Z0-9]+$/; 

        if (!regex.test(inputValue)) {
            event.target.value = '';
            event.preventDefault();
        }
    }
</script>