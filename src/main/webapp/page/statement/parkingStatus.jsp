<!-- Parking Status Page -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>

<html lang="ko">
<head>
    <meta charset="UTF-8"/>
    <title>코리아 IT 주차관리 시스템</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="/assets/js/scripts.js"></script>
    <!-- Bootstrap 5 CSS (CDN) -->
    <link href="/assets/css/styles.css" rel="stylesheet">

    <!-- SB Admin 스타일 또는 사용자 정의 CSS -->
    <%--    <link href="/assets/css/add_style.css" rel="stylesheet">--%>
    <!--폰트 어썸 임폿 -->
    <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>

</head>

<body class="sb-nav-fixed">
<!-- topbar 영역-->
<%@ include file="/layout/topbar.jsp" %>
<div id="layoutSidenav">
    <!-- sidebar 영역 -->
    <%@ include file="/layout/sidebar.jsp" %>
    <div id="layoutSidenav_content">
        <!-- 여기서 부터 메인 작업 시작. -->
        <main class="container-fluid px-4">
            <h1 class="mt-4">실시간 주차 현황</h1>
            <div class="row mb-3">
                <div class="col">
                    <div class="alert alert-info">총 10대 중 ${usedCount}대 사용 중, ${availableCount}대 빈자리</div>
                </div>
            </div>

            <table class="table table-striped">
                <thead>
                <tr>
                    <th>차량번호</th>
                    <th>운전자명</th>
                    <th>입차 시간</th>
                    <th>차량 유형</th>
                    <th>월정액</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="car" items="${currentCars}">
                    <tr>
                        <td>${car.carNumber}</td>
                        <td>${car.driverName}</td>
                        <td>${car.inTime}</td>
                        <td>${car.carType}</td>
                        <td>${car.membership}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </main>
        <!-- footer 영역 -->
        <%@ include file="/layout/footer.jsp" %>

    </div>
</div>


<!-- Bootstrap JS (Popper + Bootstrap Bundle) -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
        crossorigin="anonymous"></script>


<!-- 선택: Chart.js, jQuery 등 추가 가능 -->
<%--<script src="/assets/js/scripts.js"></script>--%>

<!-- 부트스트랩 기본 js 임포트 -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/umd/simple-datatables.min.js"
        crossorigin="anonymous"></script>
<script src="/assets/js/datatables-simple-demo.js"></script>

</body>
</html>
