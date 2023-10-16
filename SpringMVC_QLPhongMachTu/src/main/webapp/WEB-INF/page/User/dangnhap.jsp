<%-- 
    Document   : dangnhap
    Created on : Jul 26, 2023, 5:10:05 PM
    Author     : Asus
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:url value="/dangnhap" var="action"/>

<c:if test="${not empty requestScope.loginError}">
    <div class="alert1">
        ${requestScope.loginError}
    </div>
</c:if>


<c:if test="${param.error !=null}">
    <div class="alert1">
        Sai tài khoản hoặc mật khẩu!
    </div>
</c:if>


<c:if test="${param.accessDenied != null}">
    <div class="alert1">
        Không có quyền truy cập!
    </div>
</c:if>





<nav class="login">
    <div class="login_main1">  
        <form class="form_login" method="post" action="${action}">

            <p>ĐĂNG NHẬP</p>
            <div class="login0">
                <div class="one">
                    <div class="one1">
                        <div id="tk1"><i class="fa-solid fa-user"></i></div>
                    </div>

                    <div class="one1">
                        <input type="text" id="username" name="username" placeholder="Nhập tài khoản" oninput="validateInput(event)" required="true"/>
                    </div>
                </div>
            </div>

            <div class="login0">
                <div class="one">
                    <div class="one1">
                        <div id="tk1"><i class="fas fa-lock"></i></div>
                    </div>

                    <div class="one1">
                        <input type="password" id="password" name="password" placeholder="Nhập mật khẩu" oninput="validateInput(event)" required="true">
                    </div>
                </div>
            </div>

            <div class="login0">
                <div class="one2">
                    <button type="submit">Đăng nhập</button>
                </div>
            </div>

            <div class="password">
                <a href="<c:url value ="/quenmatkhau"/>" style="color: rgb(20, 20, 20); font-size: 15px"><p>Quên mật khẩu?</p></a>
            </div>
        </form>
    </div>
</nav>
<script>
    function validateInput(event) {
        var inputValue = event.target.value;
        if (inputValue.trim() === '') {
            event.target.value = '';
            event.preventDefault();
        }
    }
</script>