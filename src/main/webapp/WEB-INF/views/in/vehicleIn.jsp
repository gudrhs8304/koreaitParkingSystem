<!-- Vehicle In Page -->
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

    <!--폰트 어썸 임폿 -->
    <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>

</head>

<body class="sb-nav-fixed">
<!-- topbar 영역-->
<%@ include file="../layout/topbar.jsp" %>
<div id="layoutSidenav">
    <!-- sidebar 영역 -->
    <%@ include file="../layout/sidebar.jsp" %>
    <div id="layoutSidenav_content">
        <!-- 여기서 부터 메인 작업 시작. -->
        <main class="container-fluid px-4">
            <h1 class="mt-4">입차 관리</h1>
            <form action="${pageContext.request.contextPath}/entry.do" method="post" class="mt-3">
                <div class="row mb-3">
                    <label for="carNumber" class="col-sm-2 col-form-label">차량번호</label>
                    <div class="col-sm-4">
                        <input type="text" name="carNumber" id="carNumber" class="form-control" required/>
                    </div>
                </div>

                <div class="row mb-3">
                    <label for="carTypeCode" class="col-sm-2 col-form-label">차량 유형</label>
                    <div class="col-sm-4">
                        <select name="carTypeCode" id="carTypeCode" class="form-select" required>
                            <option value="normal" selected>일반</option>
                            <option value="disabled">장애인</option>
                            <option value="compact">경차</option>
                        </select>
                    </div>
                </div>

                <div class="row mb-3">
                    <label for="driverName" class="col-sm-2 col-form-label">운전자명</label>
                    <div class="col-sm-4">
                        <input type="text" name="driverName" id="driverName" class="form-control" required/>
                    </div>
                </div>

                <div class="row mb-3">
                    <label for="phone" class="col-sm-2 col-form-label">연락처</label>
                    <div class="col-sm-4">
                        <input type="text" name="phone" id="phone" class="form-control" required/>
                    </div>
                </div>

                <div class="row mb-3">
                    <label class="col-sm-2 col-form-label">입차 시간</label>
                    <div class="col-sm-4">
                        <input type="text" name="inTime" class="form-control"
                               value="<%= new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date()) %>"
                               readonly/>
                    </div>
                </div>

                <div class="d-flex gap-2">
                    <button type="submit" class="btn btn-primary">입차 등록</button>
                    <a href="/" class="btn btn-secondary">메인으로</a>
                </div>
            </form>
        </main>

        <!-- footer 영역 -->
        <%@ include file="../layout/footer.jsp" %>
    </div>
</div>


<!-- Bootstrap JS (Popper + Bootstrap Bundle) -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
        crossorigin="anonymous"></script>

<!-- 부트스트랩 기본 js 임포트 -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/umd/simple-datatables.min.js"
        crossorigin="anonymous"></script>

</body>
</html>
