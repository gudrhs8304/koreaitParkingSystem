<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8"/>
    <title>코리아 IT 주차관리 시스템</title>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <link href="/assets/css/styles.css" rel="stylesheet"/>
    <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
</head>

<body class="sb-nav-fixed">
<!-- topbar -->
<%@ include file="../layout/topbar.jsp" %>

<div id="layoutSidenav">
    <!-- sidebar -->
    <%@ include file="../layout/sidebar.jsp" %>

    <div id="layoutSidenav_content">
        <main class="container-fluid px-4">
            <h1 class="mt-4 mb-4">출차 관리</h1>

            <!-- 경고 메시지 -->
            <c:if test="${not empty errorMessage}">
                <div class="alert alert-danger" role="alert">
                        ${errorMessage}
                </div>
            </c:if>

            <!-- 차량 검색 -->
            <form action="/vehicleOut.do" method="post" class="row align-items-center mb-5">
                <div class="col-auto">
                    <label for="carNumber" class="col-form-label fs-3">차량번호</label>
                </div>
                <div class="col-md-6">
                    <input type="text" id="carNumber" name="carNumber"
                           class="form-control form-control-lg w-100"
                           value="${carNumber != null ? carNumber : ''}" placeholder="차량번호 입력"/>
                </div>
                <div class="col-auto">
                    <button type="submit" class="btn btn-primary btn-lg px-4">검색</button>
                </div>
            </form>

            <!-- 입차 정보 -->
            <div class="row mb-4 align-items-center">
                <label class="col-sm-2 col-form-label fs-3">입차 시간</label>
                <div class="col-md-6">
                    <input type="text" class="form-control form-control-lg w-100 text-start" readonly
                           value="${inTime != null ? inTime : ''}"/>
                </div>
            </div>

            <!-- 주차 요금 -->
            <div class="row mb-5 align-items-center">
                <label class="col-sm-2 col-form-label fs-3">주차 요금</label>
                <div class="col-md-6">
                    <input type="text" class="form-control form-control-lg w-100 text-start" readonly
                           value="${fee != null ? fee : 0}원"/>
                </div>
            </div>

            <!-- 할인 적용 & 출차 처리 -->
            <div class="row g-4 mt-5">
                <div class="col-12 col-md-6">
                    <form action="/disCount.do" method="post" class="h-100">
                        <input type="hidden" name="carNumber" value="${carNumber}"/>
                        <input type="hidden" name="carTypeCode" value="${carTypeCode}"/>
                        <button type="submit" class="btn btn-success btn-lg w-100 py-4">할인 적용</button>
                    </form>
                </div>
                <div class="col-12 col-md-6">
                    <form action="/out.do" method="post" class="h-100">
                        <input type="hidden" name="carNumber" value="${carNumber}"/>
                        <button type="submit" class="btn btn-secondary btn-lg w-100 py-4">출차 처리</button>
                    </form>
                </div>
            </div>
        </main>
    </div>3
</div>

<%@ include file="../layout/footer.jsp" %>
</div>


<!-- Bootstrap Bundle -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
        crossorigin="anonymous"></script>
</body>
</html>