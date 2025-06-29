<!-- Vehicle Out Page -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    if (request.getMethod().equalsIgnoreCase("post")) {
        if (request.getParameter("submitExit") != null) {
            response.sendRedirect("out.jsp");
            return;
        }
    }
%>

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
<style>

</style>
<body class="sb-nav-fixed">
<!-- topbar 영역-->
<%@ include file="/layout/topbar.jsp" %>
<div id="layoutSidenav">
    <!-- sidebar 영역 -->
    <%@ include file="/layout/sidebar.jsp" %>
    <div id="layoutSidenav_content">
        <!-- 여기서 부터 메인 작업 시작. -->
        <h1 class="ms-4 mt-4">할인 적용</h1>
        <form class="ms-4" action="" method="post">
            <div class="mb-3 row">
                <label for="carNumber" class="col-sm-2 col-form-label">차량번호</label>
                <div class="col-sm-4">
                    <input type="text" name="carNumber" id="carNumber" class="form-control" readonly/>
                </div>
            </div>

            <div class="mb-3 row">
                <label class="col-sm-2 col-form-label">할인 종류</label>
                <div class="col-sm-10 d-flex align-items-center gap-3">
                    <div class="form-check">
                        <input type="radio" name="discountType" id="discount1" class="form-check-input" value="disabled" />
                        <label for="discount1" class="form-check-label">장애인</label>
                    </div>
                    <div class="form-check">
                        <input type="radio" name="discountType" id="discount2" class="form-check-input" value="compact" />
                        <label for="discount2" class="form-check-label">경차</label>
                    </div>
                    <div class="form-check">
                        <input type="radio" name="discountType" id="discount3" class="form-check-input" value="electric" />
                        <label for="discount3" class="form-check-label">전기차</label>
                    </div>
                    <div class="form-check">
                        <input type="radio" name="discountType" id="discount4" class="form-check-input" value="monthParking" />
                        <label for="discount4" class="form-check-label">월주차</label>
                    </div>
                </div>
            </div>

            <div class="mb-3 row">
                <label for="discountAmount" class="col-sm-2 col-form-label">할인 적용 금액</label>
                <div class="col-sm-4">
                    <input type="text" name="discountAmount" id="discountAmount" class="form-control" readonly />
                </div>
            </div>

            <div class="mb-3 row">
                <label for="finalFee" class="col-sm-2 col-form-label">결제 요금</label>
                <div class="col-sm-4">
                    <input type="text" name="finalFee" id="finalFee" class="form-control" readonly />
                </div>
            </div>

            <div class="mb-3 d-flex gap-2">
                <input type="submit" name="applyDiscount" value="할인 적용" class="btn btn-primary" />
                <input type="submit" name="submitExit" value="출차 처리" class="btn btn-success" />
            </div>
        </form>

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
<script src="/assets/demo/chart-area-demo.js"></script>
<script src="/assets/demo/chart-bar-demo.js"></script>
<script src="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/umd/simple-datatables.min.js"
        crossorigin="anonymous"></script>
<script src="/assets/js/datatables-simple-demo.js"></script>

</body>
</html>
