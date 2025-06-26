<!-- Parking Status Page -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>

<html lang="ko">
<head>
    <meta charset="UTF-8" />
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
        <h1>상태 페이지 테스트</h1>

        <div class="container mt-5">
            <h2 class="mb-4 text-primary">주차 현황 관리</h2>

            <!-- 전체/사용중/빈자리 요약 -->
            <div class="row mb-4">
                <div class="col-md-4">
                    <div class="card text-bg-light">
                        <div class="card-body text-center">
                            <h5 class="card-title">전체 공간</h5>
                            <p class="card-text fs-3">10대</p>
                        </div>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="card text-bg-success">
                        <div class="card-body text-center">
                            <h5 class="card-title">사용 중</h5>
                            <p class="card-text fs-3">7대</p> <!-- 예시 숫자 -->
                        </div>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="card text-bg-info">
                        <div class="card-body text-center">
                            <h5 class="card-title">빈 자리</h5>
                            <p class="card-text fs-3">3대</p> <!-- 예시 숫자 -->
                        </div>
                    </div>
                </div>
            </div>

            <!-- 현재 주차중인 차량 테이블 -->
            <h5>현재 주차 중 차량 목록</h5>
            <table class="table table-bordered table-hover">
                <thead class="table-light">
                <tr>
                    <th>차량번호</th>
                    <th>운전자명</th>
                    <th>연락처</th>
                    <th>차량유형</th>
                    <th>입차시간</th>
                    <th>월정액회원</th>
                </tr>
                </thead>
                <tbody>
                <!-- 예시 데이터 -->
                <tr>
                    <td>12가3456</td>
                    <td>홍길동</td>
                    <td>010-1234-5678</td>
                    <td>일반</td>
                    <td>2025-06-26 09:30</td>
                    <td>아니오</td>
                </tr>
                <tr class="table-warning">
                    <td>88나8888</td>
                    <td>김하늘</td>
                    <td>010-8888-8888</td>
                    <td>장애인</td>
                    <td>2025-06-25 23:00</td> <!-- 장시간 주차 예시 -->
                    <td>예</td>
                </tr>
                </tbody>
            </table>

            <!-- 장시간 주차 알림 -->
            <div class="alert alert-danger mt-4">
                <strong>⚠ 장시간 주차 차량 있음!</strong> 기준 시간(예: 12시간) 초과 차량이 있습니다.
            </div>

        </div>



<%--        <%@ include file="dataTable.jsp"%>--%>
        <!-- footer 영역 -->
        <%@ include file="/layout/footer.jsp" %>
    </div>
</div>



<!-- Bootstrap JS (Popper + Bootstrap Bundle) -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>


<!-- 선택: Chart.js, jQuery 등 추가 가능 -->
<%--<script src="/assets/js/scripts.js"></script>--%>

<!-- 부트스트랩 기본 js 임포트 -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js" crossorigin="anonymous"></script>
<script src="/assets/demo/chart-area-demo.js"></script>
<script src="/assets/demo/chart-bar-demo.js"></script>
<script src="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/umd/simple-datatables.min.js" crossorigin="anonymous"></script>
<script src="/assets/js/datatables-simple-demo.js"></script>

</body>
</html>
