<%-- 
    Document   : dichvu
    Created on : Sep 7, 2023, 12:20:05 AM
    Author     : Asus
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:url value="/admin/dichvu" var="actions"/>


<nav class="dkk_main dkykhambenh themdichvu">
    <c:if test="${err != null}">
        <div class="alert1">
            ${err}
        </div>
    </c:if>
    <div class="dkk khambenh">

        <form:form method="post" modelAttribute="taoDV" action="${actions}">


            <div class="contentdkk2_main contentdkk2_main1 contentdkk2_main111">
                <div class="contentdkk2 contentdkk21 contentdkk21554">
                    <h1>THÊM DỊCH VỤ KHÁM BỆNH</h1>
                </div>

                <div class="div_themDV">
                    <div class="contentdkk5 contentdkk51 contentdkk514t5">
                        <form:input class="custom-input" type="text" id="custom-input1" path="tenDv" placeholder="Tên dịch vụ"  oninput="validateInput(event)"/>
                    </div>

                    <div class="contentdkk5 contentdkk51 contentdkk514t5">
                        <form:input class="custom-input" type="number" min="0" id="custom-input1" path="giaDv" placeholder="Giá dịch vụ" required="true"  oninput="validateInput(event)"/>
                    </div>

                    <div class="login0111">
                        <div class="one2111 one2111_tienkham">
                            <button type="submit" >Xác nhận</button>
                        </div>
                    </div>
                </div>


            </div>

            <div class="contentdkk2_main contentdkk2_main1">


                <!--                <div class="contentdkk2 contentdkk21 contentdkk211">
                                    <h1>DỊCH VỤ KHÁM BỆNH</h1>
                                </div>-->


                <section class="table__body1 table__body11 table__body111 table__body111423">
                    <table id="selectTable">
                        <thead>
                            <tr>
                                <th>ID Dịch vụ</th>
                                <th>Tên dịch vụ</th>
                                <th>Giá dịch vụ</th>
                                <th></th>

                            </tr>
                        </thead>

                        <tbody>

                            <c:forEach items="${listDV}" var="ct" >
                                <tr>
                                    <td>${ct.idDv}</td>
                                    <td>${ct.tenDv}</td>
                                    <td>${ct.giaDv}vnđ</td>
                                    <td></td>
                                </tr> 
                            </c:forEach>
                        </tbody>
                    </table>
                </section>       
            </div>

        </form:form>

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