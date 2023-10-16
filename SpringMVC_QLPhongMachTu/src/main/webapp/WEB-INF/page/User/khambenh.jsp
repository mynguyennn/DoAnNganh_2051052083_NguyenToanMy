<%-- 
    Document   : khambenh.jsp
    Created on : Aug 22, 2023, 2:36:28 PM
    Author     : Asus
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<c:url value="/bacsi/khambenh" var="actions"/>

<%--<c:if test="${errMsg != null}">
    <div class="alert1">
        ${errMsg}
    </div>
</c:if>--%>

<nav class="dkk_main dkykhambenh">

    <div class="dkk khambenh">

        <form:form class="form_login11" method="post" action="${actions}" modelAttribute="taoPKB" id="phieubenh">
            <form:hidden path="idPhieukham"/>
            <form:hidden path="ngayKhamBenh"/>

            <%--<form:hidden path="pdk"/>--%>
            <input type="hidden" name="pdk" value="${pdkID.idPhieudk}" />

            <div class="contentdkk2_main contentdkk2_main1">
                <div class="contentdkk2 contentdkk21">
                    <h1>PHIẾU KHÁM BỆNH NHÂN</h1>
                </div>




                <div class="contentdkk3 contentdkk31">
                    <div id="input_tt">
                        <h5>ID Bệnh nhân</h5>
                        <input type="text"  placeholder="${idpdk.idBn.idTk}" disabled="true"/>
                    </div>

                    <div>
                        <h5>Họ tên</h5>
                        <input type="text" placeholder="${idpdk.idBn.hoTen}" disabled="true"/>
                    </div>

                </div>


                <div class="contentdkk5 contentdkk51">
                    <h5>Ngày khám</h5>
                    <input type="text" placeholder="${idpdk.chonNgaykham}" disabled="true"/>
                </div>


                <div class="contentdkk2 contentdkk22">
                    <h5>* Thông tin bệnh án</h5>
                </div>

                <div class="contentdkk4 contentdkk41">





                    <c:forEach items="${pk}" var="pk">
                        <!--<div>{pk.idPk.trieuChung}</div>-->


                        <c:choose>
                            <c:when test="${pk.idPk != null}">
                                <div class="contentdkk5 contentdkk51">
                                    <form:input class="custom-input" type="text" id="custom-input1" path="trieuChung" placeholder="${pk.idPk.trieuChung}" disabled="true"/>
                                </div>

                                <div class="contentdkk5 contentdkk51">
                                    <form:input class="custom-input" type="text" id="custom-input1" path="ketLuan" placeholder="${pk.idPk.ketLuan}" disabled="true"/>
                                </div>
                            </c:when>

                            <c:otherwise>
                                <div class="contentdkk5 contentdkk51">
                                    <form:input class="custom-input" type="text" id="custom-input1" path="trieuChung" placeholder="Triệu chứng" />
                                </div>

                                <div class="contentdkk5 contentdkk51">
                                    <form:input class="custom-input" type="text" id="custom-input1" path="ketLuan" placeholder="Kết luận bệnh án" />
                                </div>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>




                </div>

            </div>
            <div class="submitdkk submitdkk111 submitdkk1111">
                <button type="submit" >LƯU THÔNG TIN</button>
            </div>

        </form:form>







        <form:form class="form_login11" method="post" action="${actions}" modelAttribute="dsdv" id="dichvu" >
            <input type="hidden" name="pdk" value="${pdkID.idPhieudk}" />

            <div class="contentdkk2_main contentdkk2_main1">


                <div class="contentdkk2 contentdkk21 contentdkk211">
                    <h1>DỊCH VỤ KHÁM BỆNH</h1>
                </div>


                <div class="dichvu_kb">
                    <form:select path="idDv"  class="form-select form-select1" cssErrorClass="is-invalid">
                        <!--<option>Dịch vụ</option>-->
                        <c:forEach items="${listDv}" var="c" >
                            <option value="${c.idDv}" >${c.tenDv}</option>
                        </c:forEach>
                    </form:select>



                    <div class="submitdkk submitdkk111 submitdkk1111 submitdkk111112">
                        <button  type="submit" >THÊM</button>
                    </div>
                </div>

            </form:form>




            <section class="table__body1 table__body11 table__body111">
                <table id="selectTable">
                    <thead>
                        <tr>
                            <th>ID Phiếu khám bệnh</th>
                            <th>ID Dịch vụ</th>
                            <th>Tên dịch vụ</th>
                            <th>Giá dịch vụ</th>
                            <th></th>

                        </tr>
                    </thead>

                    <tbody>
                        <%--<c:forEach items="${lichSuKham}" var="p">--%>
                        <%--<c:choose>--%>
                        <%--<c:when test="${p.idPk.trieuChung != null}">--%>
                        <!--<tr>-->
                        <!--<td></td>-->
                        <!--<td></td>-->
                        <!--<td></td>-->
                        <!--<td></td>-->
                        <!--<td id="xoaThuoc">-->
                            <!--<div class="admin_submit admin_submit11 admin_submit1111" onclick="xoaThuoc('${apiDelete}')">-->
                        <!--XÓA-->  
                        <!--</div>-->
                        <!--</td>-->
                        <!--</tr>-->
                        <%--</c:when>--%>
                        <%--<c:otherwise>--%>

                        <c:forEach items="${DvDk}" var="ct" >
                            <tr>
                                <td>${ct.idPdk.idPhieudk}</td>
                                <td>${ct.idDv.idDv}</td>
                                <td>${ct.idDv.tenDv}</td>
                                <td>${ct.idDv.giaDv}</td>
                                <td></td>
                            </tr> 
                        </c:forEach>
                        <%--</c:otherwise>--%>
                        <%--</c:choose>--%>
                        <%--</c:forEach>--%>
                    </tbody>
                </table>
            </section>       


        </div>








    </div>

    <script>
        function submitForms() {
//            document.getElementById("dichvu").submit();
//            document.getElementById("phieubenh").submit();


        }
    </script>



    <!--DICH VU KHAM B?NH-->



    <div class="dkk khambenh khambenh1">

        <form>
            <div class="contentdkk2_main contentdkk2_main1 contentdkk2_main11">
                <div class="contentdkk2 lskhambenh">
                    <h1>LỊCH SỬ KHÁM BỆNH</h1>
                    <div class="inputkw inputkw1">
                        <form action="${actions}">
                            <input name="kwDate" type="date"" placeholder="Tìm kiếm theo ngày...">
                            <button type="submit"> <i class="fa-solid fa-magnifying-glass"></i> </button>
                        </form>
                    </div>
                </div>




                <section class="table__body1 table__body11">
                    <table>
                        <thead>
                            <tr>
                                <th>ID Phiếu đăng ký</th>
                                <th>Họ tên</th>
                                <th>Ngày khám</th>
                                <th>Triệu chứng</th>
                                <th>Kết luận</th>
                                <th>Dịch vụ</th>

                            </tr>
                        </thead>

                        <tbody>
                            <c:forEach items="${lichSuKham}" var="p">
                                <c:choose>
                                    <c:when test="${p.idPk.trieuChung != null}">
                                        <tr>
                                            <td>${p.idPhieudk}</td>
                                            <td>${p.idBn.hoTen}</td>
                                            <td>${p.chonNgaykham}</td>
                                            <td>${p.idPk.trieuChung}</td>
                                            <td>${p.idPk.ketLuan}</td>
                                            <th></th>
                                        </tr>
                                    </c:when>
                                    <c:otherwise>
                                        <tr>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <th></th>
                                        </tr>   
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </tbody>
                    </table>
                </section>

            </div>

        </form>

    </div>



</div>


</nav>


<a href="/SpringMVC_QLPhongMachTu/generate-pdf?id=${id}" target="_blank">Generate PDF</a>

