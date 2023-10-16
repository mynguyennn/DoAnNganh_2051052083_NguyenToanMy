<%-- 
    Document   : danhgia
    Created on : Oct 6, 2023, 1:18:15 PM
    Author     : LENOVO
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link rel='stylesheet prefetch' href='https://netdna.bootstrapcdn.com/font-awesome/3.2.1/css/font-awesome.css'>



<c:url value="/benhnhan/danhgia/${bacsi.idTk}" var="action"/>

<div class="mainprofile">
    <div>
        <div class="profile_bs">
            <%--<c:forEach items="${dskham}" var="p">--%> 
            <div class="profile_img">
                <div class="title_name">THÔNG TIN CÁ NHÂN</div>

                <img src="${bacsi.avt}"/>
            </div>

            <h5>Bác sĩ: ${bacsi.hoTen}</h5>
            <h5>Ngày sinh: ${bacsi.ngaySinh}</h5>
            <h5>Giới tính: ${bacsi.gioiTinh}</h5>
            <h5>Sđt: ${bacsi.sdt}</h5>            
            <%--</c:forEach>--%>
        </div>
    </div>

    <div>
        <form:form action="${action}" method="post" modelAttribute="danhgia">
            <form:hidden path="id"/>
            <form:hidden path="idBs"/>


            <div class="stars">
                <form:radiobutton class="star star-5" id="star-5"  path="danhGia"  value="5"/>
                <label class="star star-5" for="star-5"></label>
                <form:radiobutton class="star star-4" id="star-4"   value="4"   path="danhGia"/>
                <label class="star star-4" for="star-4"></label>
                <form:radiobutton path="danhGia" class="star star-3" id="star-3"  value="3"/>
                <label class="star star-3" for="star-3"></label>
                <form:radiobutton path="danhGia" class="star star-2" id="star-2"  value="2"/>
                <label class="star star-2" for="star-2"></label>
                <form:radiobutton path="danhGia" class="star star-1" id="star-1"  value="1"/>
                <label class="star star-1" for="star-1"></label>

            </div>
            <div class="danhgia0">
                <div class="danhgia">

                    <div class="danhgia1">
                        <form:input type="text" id="danhgia" name="danhgia" path="binhLuan" placeholder="Đánh Giá"  required="true" />
                    </div>
                </div>
                
                <div class="danhgia">
                    <div class="danhgia2">
                        <button type="submit">Đánh giá</button>
                    </div>
                </div>

            </div>

        </form:form>
        <h1>Các Bài Đánh Giá</h1>
        <ul class="comment-list">
            <c:forEach items="${dsdanhgia}" var="p">

                <li class="comment">
                    <div class="comment-avatar">
                        <img src="${p.idBn.avt}" alt="Avatar">
                    </div>
                    <div class="comment-content">
                        <h4 class="comment-author">${p.idBn.hoTen}</h4>
                        <p class="comment-text">${p.binhLuan}</p>
                    </div>
                </li>
                <div class="starss">                    
                    <input type="radio" class="star star-5" id="stars-5" path="danhGia" value="5" disabled="disabled" ${p.danhGia == 5? 'checked' : ''} />
                    <label class="star star-5" for="stars-5"></label>
                    <input type="radio" class="star star-4" id="stars-4" value="4" path="danhGia" disabled="disabled" ${p.danhGia == 4? 'checked' : ''} />
                    <label class="star star-4" for="stars-4"></label>
                    <input type="radio" path="danhGia" class="star star-3" id="stars-3" value="3" disabled="disabled" ${p.danhGia == 3? 'checked' : ''} />
                    <label class="star star-3" for="stars-3"></label>
                    <input type="radio" path="danhGia" class="star star-2" id="stars-2" value="2" disabled="disabled" ${p.danhGia == 2? 'checked' : ''}/>
                    <label class="star star-2" for="stars-2"></label>
                    <input type="radio" path="danhGia" class="star star-1" id="stars-1" value="1" disabled="disabled" ${p.danhGia == 1? 'checked' : ''} />
                    <label class="star star-1" for="stars-1"></label>                   
                </div>
            </c:forEach>
        </ul>
        <ul class="pagination mt-1">
            <c:if test="${pages > 1}">
                <ul class="pagination mt-1">
                    <li class="page-item"><a class="page-link" href="${action}">Tất cả</a></li>
                        <c:forEach begin="1" end="${pages}" var="i">
                            <c:url value="/benhnhan/danhgia/${bacsi.idTk}" var="pageUrl">
                                <c:param name="page" value="${i}"/> 
                            </c:url>
                        <li class="page-item"><a class="page-link" href="${pageUrl}">${i}</a></li>
                        </c:forEach>
                    <!--            <li class="page-item">
                                    <form action="{action}" id="pageNumber">
                                        <input type="number" min="1" max="${pages}" name="page" onchange="document.getElementById('pageNumber').submit()" />
                                    </form>
                                </li>-->
                </ul>

            </c:if>
        </ul>

    </div>
</div>