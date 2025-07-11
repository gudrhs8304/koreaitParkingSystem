<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8"/>
    <title>코리아 IT 주차관리 시스템</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="../../../assets/css/styles.css" rel="stylesheet">
    <script src="../../../assets/js/scripts.js"></script>
    <script src="../../../assets/js/fontAwsome.js"></script>
</head>
<body class="sb-nav-fixed">

<!-- Topbar -->
<%@ include file="../layout/topbar.jsp" %>

<div id="layoutSidenav">
    <%@ include file="../layout/sidebar.jsp" %>

    <div id="layoutSidenav_content">
        <!-- MAIN -->
        <main class="container-fluid px-4">
            <h1 class="mt-4">차량 검색</h1>
            <ol class="breadcrumb mb-4">
                <li class="breadcrumb-item active">주차 현황</li>
            </ol>

            <!-- 검색 폼 카드 -->
            <div class="card shadow-sm border-0 mb-4">
                <div class="card-header bg-primary text-white fw-semibold">차량 검색</div>
                <div class="card-body">
                    <form method="get" action="/search.do">
                        <div class="row g-3 align-items-center">
                            <div class="col-md-6 col-lg-4">
                                <input type="search" name="keyword" class="form-control"
                                       placeholder="차량번호, 운전자명, 연락처"
                                       value="${fn:escapeXml(param.keyword)}${carNumber != null ? carNumber : ""}" required>
                            </div>
                            <div class="col-auto">
                                <button type="submit" class="btn btn-primary">검색</button>
                            </div>
                        </div>
                    </form>

                    <c:if test="${not empty error}">
                        <div class="alert bg-danger-subtle text-danger-emphasis border-0 shadow-sm mt-3">
                                ${error}
                        </div>
                    </c:if>
                </div>
            </div>

            <!-- 요약 카드 -->
            <div class="row mb-4">
                <c:forEach var="info" items="${summaryInfo}">
                    <div class="col-md-4">
                        <div class="card shadow-sm border-0 text-center">
                            <div class="card-header ${info.bg} text-white">${info.title}</div>
                            <div class="card-body fs-3">${info.count}대</div>
                        </div>
                    </div>
                </c:forEach>
            </div>

            <!-- 차량 테이블 카드 -->
            <div class="card shadow-sm border-0">
                <div class="card-header bg-primary text-white fw-semibold">현재 주차중인 차량 목록</div>
                <div class="card-body p-0">
                    <table class="table table-bordered mb-0">
                        <thead class="table-light text-center">
                        <tr>
                            <th>차량번호</th>
                            <th>차량 유형</th>
                            <th>운전자명</th>
                            <th>연락처</th>
                            <th>입차 시간</th>
                            <th>주차 시간</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="car" items="${carList}">
                            <tr style="${car.over12Hours ? 'background-color:#5c9dff' : ''}">
                                <td>${car.carNumber}</td>
                                <td>${car.carTypeName}</td>
                                <td>${car.driverName}</td>
                                <td>${car.phone}</td>
                                <td>${car.inTimeFormatted}</td>
                                <td>${car.parkedHours}시간 ${car.over12HoursIcon}</td>
                            </tr>
                        </c:forEach>

                        <c:if test="${empty carList}">
                            <tr>
                                <td colspan="6" class="text-center">검색 결과가 없습니다.</td>
                            </tr>
                        </c:if>
                        </tbody>
                    </table>
                </div>
            </div>

            <!-- 페이지네이션 -->
            <nav class="mt-4">
                <ul class="pagination justify-content-center">

                    <!-- 이전 -->
                    <c:choose>
                        <c:when test="${page <= 1}">
                            <li class="page-item disabled"><span class="page-link">이전</span></li>
                        </c:when>
                        <c:otherwise>
                            <li class="page-item">
                                <a class="page-link" href="/search.do?page=${page - 1}&keyword=${keyword}">이전</a>
                            </li>
                        </c:otherwise>
                    </c:choose>

                    <!-- 페이지 번호 -->
                    <c:forEach begin="1" end="${totalPage}" var="p">
                        <li class="page-item ${p == page ? 'active' : ''}">
                            <a class="page-link" href="/search.do?page=${p}&keyword=${keyword}">${p}</a>
                        </li>
                    </c:forEach>

                    <!-- 다음 -->
                    <c:choose>
                        <c:when test="${page >= totalPage}">
                            <li class="page-item disabled"><span class="page-link">다음</span></li>
                        </c:when>
                        <c:otherwise>
                            <li class="page-item">
                                <a class="page-link" href="/search.do?page=${page + 1}&keyword=${keyword}">다음</a>
                            </li>
                        </c:otherwise>
                    </c:choose>
                </ul>
            </nav>
        </main>

        <%@ include file="../layout/footer.jsp" %>
    </div>
</div>

<script src="../../../assets/js/bootStrap.js"></script>
</body>
</html>