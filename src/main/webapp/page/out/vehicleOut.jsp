<!-- Vehicle Out Page -->
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
        <main class="container-fluid px-4">
            <h1 class="mt-4">출차 관리</h1>
            <form method="post" class="mt-3">
                <div class="row mb-3">
                    <label for="carNumber" class="col-sm-2 col-form-label">차량번호</label>
                    <div class="col-sm-4">
                        <input type="text" name="carNumber" id="carNumber" class="form-control" />
                    </div>
                    <div class="col-sm-2">
                        <input type="submit" name="search" value="검색" class="btn btn-primary" />
                    </div>
                </div>

                <div class="row mb-3">
                    <label class="col-sm-2 col-form-label">입차 시간</label>
                    <div class="col-sm-4">
                        <input type="text" name="inTime" class="form-control" readonly />
                    </div>
                </div>

                <div class="row mb-3">
                    <label class="col-sm-2 col-form-label">주차 요금</label>
                    <div class="col-sm-4">
                        <input type="text" name="fee" class="form-control" readonly />
                    </div>
                </div>

                <div class="d-flex gap-2">
                    <a href="/page/out/disCount.jsp" class="btn btn-success">할인 적용</a>
                    <a href="/page/out/out.jsp" class="btn btn-success">출차</a>
                </div>
            </form>
        </main>
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
<script src="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/umd/simple-datatables.min.js" crossorigin="anonymous"></script>
<script src="/assets/js/datatables-simple-demo.js"></script>

</body>
</html>
