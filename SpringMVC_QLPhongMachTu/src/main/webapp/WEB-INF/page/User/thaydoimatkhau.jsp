<%-- 
    Document   : thaydoimatkhau
    Created on : Sep 5, 2023, 5:30:43 AM
    Author     : Asus
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<c:url value="/thaydoimatkhau" var="action">
    <c:param name="user" value="${user.idTk}" />
</c:url>

<c:if test="${err != null}">
    <div class="alert1">
        ${err}
    </div>
</c:if>


<form class="form_login11" method="post" action="${action}">
    <nav class="login111">
        <%--<c:if test="${not empty quenpass}">--%>
        <!--<p style="color: red">{quenpass}</p>-->
        <%--</c:if>--%>

        <%--<c:if test="${not empty thanhcong}">--%>
        <!--<p style="color: green">{thanhcong}</p>-->
        <%--</c:if>--%>


        <div class="login_main1111 login_main11114545">  

            <div class="dky111">
                <p>LẤY LẠI MẬT KHẨU</p>

                <div class="login0111">
                    <div class="one111">
                        <div class="one1111">
                            <div id="tk1111"><i class="fa-solid fa-envelope-circle-check"></i></div>
                        </div>

                        <div class="one1111">
                            <input type="number" min="0" id="maXacNhan" name="maXacNhan" placeholder="Nhập mã xác nhận" oninput="kyTu(event)"/>
                        </div>
                    </div>
                </div>

                <div class="login0111">
                    <div class="one111">
                        <div class="one1111">
                            <div id="tk1111"><i class="fa-solid fa-unlock-keyhole"></i></div>
                        </div>

                        <div class="one1111">
                            <input type="password" id="matKhauMoi" name="matKhauMoi" placeholder="Nhập mật khẩu mới" oninput="kyTu(event)"/>
                        </div>

                    </div>
                </div>



                <div class="login0111">
                    <div class="one111">
                        <div class="one1111">
                            <div id="tk111"><i class="fa-solid fa-unlock-keyhole"></i></div>
                        </div>

                        <div class="one1111">
                            <input  type="password" id="xacNhanMatKhauMoi" name="xacNhanMatKhauMoi" placeholder="Nhập lại mật khẩu mới" oninput="kyTu(event)"/>
                        </div>

                    </div>
                </div>
            </div>

            <div class="login0111">
                <div class="one2111">
                    <button type="submit" >Xác nhận</button>
                </div>
            </div>

        </div>

    </nav>
</form>

<script>
    function kyTu(event) {
        var inputValue = event.target.value;
        var regex = /^[a-zA-Z0-9]+$/;

        if (!regex.test(inputValue)) {
            event.target.value = '';
            event.preventDefault();
        }
    }
</script>

