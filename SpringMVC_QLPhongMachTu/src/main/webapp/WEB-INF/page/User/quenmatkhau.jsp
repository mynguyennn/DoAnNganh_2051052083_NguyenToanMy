<%-- 
Document   : quenmatkhau
Created on : Sep 5, 2023, 2:46:55 AM
Author     : Asus
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<c:url value="/quenmatkhau" var="action"/>

<c:if test="${err != null}">
    <div class="alert1">
        ${err}
    </div>
</c:if>


<form:form class="form_login11" method="post" action="${action}" modelAttribute="tk">


    <nav class="login111">

        <div class="login_main1111 login_main11114545">  

            <div class="dky111">
                <p>QUÊN MẬT KHẨU</p>

                <div class="login0111">
                    <div class="one111">
                        <div class="one1111">
                            <div id="tk1111"><i class="fa-solid fa-user"></i></div>
                        </div>

                        <div class="one1111">
                            <form:input type="text" id="taiKhoan" path="taiKhoan" placeholder="Nhập tài khoản của bạn" oninput="validateInput(event)" required="true"/>
                        </div>
                    </div>
                </div>
<!--                <div class="login0111">
                    <div class="one111">
                        <div class="one1111">
                            <div id="tk1111"><i class="fa-solid fa-user"></i></div>
                        </div>

                        <div class="one1111">
                            <%--<form:input type="text" id="sdtOTP" path="sdtOTP" placeholder="Nhập số điện thoại của bạn" oninput="validateInput(event)" required="true"/>--%>
                        </div>
                    </div>
                </div>
            </div>-->

            <div class="login0111">
                <div class="one2111">
                    <button type="submit" >Đổi mật khẩu</button>
                </div>
            </div>

        </div>

    </nav>
</form:form>
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