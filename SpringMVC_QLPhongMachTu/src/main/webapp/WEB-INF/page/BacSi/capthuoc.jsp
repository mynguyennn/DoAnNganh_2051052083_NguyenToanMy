<%-- 
    Document   : capthuoc
    Created on : Aug 26, 2023, 12:40:10 PM
    Author     : Asus
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<c:url value="/bacsi/capthuoc" var="actions"/>


<c:if test="${err != null}">
    <div class="alert1">
        ${err}
    </div>
</c:if>

<nav class="dkk_main dkykhambenh">

    <div class="dkk khambenh">




        <div class="contentdkk2_main contentdkk2_main1">
            <div class="contentdkk2 contentdkk21">
                <h1>CẤP THUỐC</h1>
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
                <h5>Kết luận bệnh án</h5>
                <input type="text" placeholder="${idpdk.idPk.ketLuan}" disabled="true"/>
            </div>


            <div class="contentdkk2 contentdkk22">
                <h5>* Thông tin thuốc</h5>
            </div>

            <div class="contentdkk4 contentdkk41">

                <input type="hidden" name="idloaiThuoc" value="${idloaiThuoc}" />

                <form id="selectForm" action="${actions}" method="post">

                    <div>
                        <input type="hidden" id="selectedLoaiThuocId" name="idLoaiThuoc" value="" />
                        <select id="loaiThuoccc" class="form-select form-select1 form-select111" cssErrorClass="is-invalid">
                            <c:forEach items="${loaiThuoc}" var="d">
                                <option value="${d.idloaiThuoc}" >${d.tenLoaiThuoc}</option>
                            </c:forEach>
                        </select>
                    </div>
                </form>


                <form:form id="capthuoc" class="form_login11" method="post" action="${actions}" modelAttribute="addChiTietThuoc">
                    <form:hidden path="idChitietThuoc"/>

                    <input type="hidden" name="idPDK" value="${idPDK}" />

                    <div class="contentdkk3 contentdkk31 contentdkk311" id="dynamicFieldsContainer">


                        <c:set var="listThuoc" value="${listThuoc}" />

                        <form:select path="idThuoc" id="idThuoccc" class="form-select form-select1 form-select111" cssErrorClass="is-invalid">
                            <c:forEach items="${thuocByLoaiThuoc}" var="c">
                                <option value="${c.idThuoc}" >${c.tenThuoc}</option>
                            </c:forEach>
                        </form:select>


                        <div class="contentdkk5 contentdkk51">
                            <form:input class="custom-input" type="number" min="0" id="custom-input1" path="soLuongSd" placeholder="Số lượng" required="true" oninput="validateInput(event)"/>
                        </div>

                        <div class="contentdkk5 contentdkk51 contentdkk511">
                            <div  id="addNewFields">
                                <a href="<c:url value ="/bacsi/capthuoc?idPDK"/>" >
                                    <button type="submit" >
                                        <img src="<c:url value='/img/plus.png'/>" alt="" />
                                    </button>
                                </a>
                            </div>
                        </div>

                    </div>



                    <div class="contentdkk5 contentdkk51">
                        <form:input class="custom-input" type="text" id="custom-input1" path="hdsd" placeholder="Hướng dẫn sử dụng thuốc" required="true"  oninput="validateInput(event)"/>
                    </div>
                </form:form>

            </div>


        </div>



        <script>
            function validateInput(event) {
                var inputValue = event.target.value;
                if (inputValue.trim() === '') {
                    event.target.value = '';
                    event.preventDefault();
                }
            }
        </script>




        <!--</form>-->

        <c:url value="/bacsi/capthuoc/taohoadon" var="actionss"/>
        <form:form class="form_login11" method="post" action="${actionss}" modelAttribute="addHoaDon">

            <input type="hidden" name="idPDK" value="${idPDK}" />

            <div class="contentdkk2_main contentdkk2_main1 contentdkk2_main11">

                <section class="table__body1 table__body11 table__body111111">
                    <table>
                        <thead>
                            <tr>
                                <th>Tên thuốc</th>
                                <th>Số lượng</th>
                                <th>Hướng dẫn sử dụng</th>
                                <th>Loại thuốc</th>
                                <th>Tiền thuốc</th>

                            </tr>
                        </thead>

                        <tbody>
                            <c:forEach items="${listThuocByID}" var="p">
                                <tr>
                                    <td>${p.idThuoc.tenThuoc}</td>
                                    <td>${p.soLuongSd} ${p.idThuoc.donVi.tenDonVi}</td>
                                    <td>${p.hdsd}</td>
                                    <td>${p.idThuoc.loaiThuoc.tenLoaiThuoc}</td>
                                    <td>${p.idThuoc.giaThuoc * p.soLuongSd}vnđ</td>

                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </section>

                <c:set var="showPdfLink" value="false" />

                <c:forEach items="${listThuocByID}" var="thuoc">
                    <c:if test="${thuoc.idChitietThuoc != null}">
                        <c:set var="showPdfLink" value="true" />
                    </c:if>
                </c:forEach>

                <c:if test="${showPdfLink eq 'true'}">
                    <div class="submitdkk submitdkk111 submitdkk1111 submitdkk111115">
                        <a href="${pageContext.request.contextPath}/ThongTinThuoc-PDF?idPDK=${idPDK}" target="_blank">XUẤT FILE THUỐC (PDF)</a>
                    </div>


                </c:if>

                <div class="submitdkk submitdkk111 submitdkk1111">
                    <a>
                        <button type="submit" >XUẤT HÓA ĐƠN</button>
                    </a>
                </div>



            </div>

        </form:form>









    </div>


    <div class="dkk khambenh khambenh1">
        <form>
            <div class="contentdkk2_main contentdkk2_main1 contentdkk2_main11">
                <div class="contentdkk2 lskhambenh">
                    <h1>DANH SÁCH THUỐC</h1>
                    <div class="inputkw inputkw1">
                        <form action="${actions}">
                            <input name="kw" type="text"" placeholder="Tìm kiếm thuốc...">
                            <button type="submit"> <i class="fa-solid fa-magnifying-glass"></i> </button>
                        </form>
                    </div>
                </div>




                <section class="table__body1 table__body11 table__body111">
                    <table>
                        <thead>
                            <tr>
                                <th>ID Thuốc</th>
                                <th>Tên thuốc</th>
                                <th>Xuất xứ</th>
                                <th>Giá thuốc</th>
                                <th>Đơn vị</th>
                                <th>Loại thuốc</th>
                                <th>Số lượng</th>


                            </tr>
                        </thead>

                        <tbody>
                            <c:forEach items="${listThuoc}" var="p">
                                <tr>
                                    <td>${p.idThuoc}</td>
                                    <td>${p.tenThuoc}</td>
                                    <td>${p.xuatXu}</td>
                                    <td>${p.giaThuoc}</td>
                                    <td>${p.donVi.tenDonVi}</td>
                                    <td>${p.loaiThuoc.tenLoaiThuoc}</td>
                                    <td>${p.soLuong}</td>

                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </section>

            </div>

        </form>

    </div>



</div>


</nav>


<script>
    var listThuocData = [
    <c:forEach items="${listThuoc}" var="thuoc" varStatus="loop">
    {
    "idThuoc": ${thuoc.idThuoc},
            "tenThuoc": "${thuoc.tenThuoc}",
            "loaiThuocId": ${thuoc.loaiThuoc.idloaiThuoc}
    }
        <c:if test="${!loop.last}">,</c:if>
    </c:forEach>
    ];</script>
<script>
    var loaiThuocSelect = document.getElementById("loaiThuoccc");
    var idThuocSelect = document.getElementById("idThuoccc");

    var loaiThuocData = '<c:out value="${loaiThuoc}" />';


    loaiThuocSelect.addEventListener("change", function () {
        var selectedLoaiThuocId = loaiThuocSelect.value;

        document.getElementById("selectedLoaiThuocId").value = selectedLoaiThuocId;

        console.log(selectedLoaiThuocId);
        console.log(listThuocData);


        idThuocSelect.innerHTML = "";


        listThuocData.forEach(function (thuoc) {
            if (thuoc.loaiThuocId === parseInt(selectedLoaiThuocId)) {
                var option = document.createElement("option");
                option.value = thuoc.idThuoc;
                option.text = thuoc.tenThuoc;
                idThuocSelect.appendChild(option);
            }
        });


    });

    loaiThuocSelect.dispatchEvent(new Event("change"));
</script>




