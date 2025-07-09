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
            <h1 class="card-body">출차 관리</h1>

            <!-- 경고 메시지 -->
            <c:if test="${not empty errorMessage}">
            <div class="alert alert-danger" role="alert">
                    ${errorMessage}
            </div>
            </c:if>

            <!-- 차량 검색 -->
            <div class="card shadow mb-4">
                <div class="card-header bg-secondary text-white fs-4">
                    차량 검색
                </div>
                <div class="card-body">
                    <form action="/vehicleOut.do" method="post" class="row g-3">
                        <div class="col-12">
                            <label for="carNumber" class="form-label fs-5">차량 번호</label>
                            <input type="text" id="carNumber" name="carNumber"
                                   class="form-control form-control-lg"
                                   value="${carNumber != null ? carNumber : ''}" placeholder="차량번호 입력"/>
                        </div>
                        <div class="col-12">
                            <button type="submit" class="btn btn-primary btn-lg w-50 float-end">검색</button>
                        </div>
                    </form>
                </div>
            </div>

            <!-- 입차 정보 및 주차 요금 -->
            <div class="card shadow mb-4">
                <div class="card-header bg-secondary text-white fs-4">
                    입차 정보
                </div>
                <div class="card-body">
                    <div class="row mb-3">
                        <label class="col-sm-3 col-form-label fs-5">입차 시간</label>
                        <div class="col">
                            <input type="text" class="form-control form-control-lg text-start" readonly
                                   value="${inTime != null ? inTime : ''}"/>
                        </div>
                    </div>
                    <div class="row">
                        <label class="col-sm-3 col-form-label fs-5">주차 요금</label>
                        <div class="col">
                            <input type="text" class="form-control form-control-lg text-start" readonly
                                   value="${fee != null ? fee : 0}원"/>
                        </div>
                    </div>
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
    <%@ include file="../layout/footer.jsp" %>
</div>
</div>

</div>


<!-- Bootstrap Bundle -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
        crossorigin="anonymous"></script>
</body>
</html>