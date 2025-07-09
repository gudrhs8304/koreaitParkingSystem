<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8"/>
    <title>코리아 IT 주차관리 시스템</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- side_bar toggle -->
    <script src="../../../assets/js/scripts.js"></script>
    <!-- Bootstrap 5 CSS -->
    <link href="../../../assets/css/styles.css" rel="stylesheet">
    <!--폰트 어썸 -->
    <script src="../../../assets/js/fontAwsome.js"></script>
</head>

<body class="sb-nav-fixed">
<!-- topbar -->
<%@ include file="../layout/topbar.jsp" %>

<div id="layoutSidenav">
    <!-- sidebar -->
    <%@ include file="../layout/sidebar.jsp" %>

    <div id="layoutSidenav_content">

        <main class="container-fluid px-4">
            <h1 class="mt-4">출차 관리</h1>
            <ol class="breadcrumb mb-4">
                <li class="breadcrumb-item active">차량 출차</li>
            </ol>

            <c:if test="${not empty errorMessage}">
                <div class="alert bg-danger-subtle text-danger-emphasis border-0 shadow-sm" role="alert">
                        ${errorMessage}
                </div>
            </c:if>

            <div class="card shadow-sm border-0 mb-5">
                <div class="card-header bg-primary text-white fw-semibold">
                    차량 출차 처리
                </div>

                <div class="card-body">
                    <form action="/vehicleOut.do" method="post" class="row gy-3 mb-4">
                        <div class="col-md-6">
                            <label for="carNumber" class="form-label">차량 번호</label>
                            <input type="text" id="carNumber" name="carNumber" class="form-control"
                                   value="${carNumber != null ? carNumber : ''}" placeholder="차량번호 입력">
                        </div>
                        <div class="col-md-6 d-flex align-items-end">
                            <button type="submit" class="btn btn-outline-primary w-100">검색</button>
                        </div>
                    </form>

                    <div class="row mb-3">
                        <label class="col-sm-3 col-form-label">입차 시간</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" readonly
                                   value="${inTime != null ? inTime : ''}">
                        </div>
                    </div>

                    <div class="row mb-3">
                        <label class="col-sm-3 col-form-label">주차 요금</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" readonly
                                   value="${fee != null ? fee : 0}원">
                        </div>
                    </div>
                </div>

                <div class="row mt-4">
                    <div class="col-md-6 d-grid">
                        <form action="/disCount.do" method="post">
                            <input type="hidden" name="carNumber" value="${carNumber}">
                            <input type="hidden" name="carTypeCode" value="${carTypeCode}">
                            <button type="submit" class="btn btn-outline-success w-100">할인 적용</button>
                        </form>
                    </div>
                    <div class="col-md-6 d-grid">
                        <form action="/out.do" method="post">
                            <input type="hidden" name="carNumber" value="${carNumber}">
                            <button type="submit" class="btn btn-primary w-100">출차 처리</button>
                        </form>
                    </div>
                </div>

            </div>
        </main>
        <%@ include file="../layout/footer.jsp" %>
    </div>
</div>

<!-- Bootstrap Bundle -->
<script src="../../../assets/js/bootStrap.js"></script></body>
</html>