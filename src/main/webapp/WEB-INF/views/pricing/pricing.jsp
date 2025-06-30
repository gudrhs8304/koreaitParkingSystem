<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>

<html lang="ko">
<head>
    <meta charset="UTF-8"/>
    <title>코리아 IT 주차관리 시스템</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="/assets/js/scripts.js"></script>
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
            <h1 class="mt-4">요금 설정</h1>
            <form method="post" action="/updateFees" class="mt-3">
                <div class="row mb-3">
                    <label class="col-sm-2 col-form-label">기본 1시간 요금</label>
                    <div class="col-sm-4">
                        <input type="number" name="baseFee" class="form-control" value="${fee.baseFee}" required
                               placeholder="3000원"/>
                    </div>
                </div>

                <div class="row mb-3">
                    <label class="col-sm-2 col-form-label">30분당 추가 요금</label>
                    <div class="col-sm-4">
                        <input type="number" name="extraFee" class="form-control" value="${fee.extraFee}" required
                               placeholder="1000원"/>
                    </div>
                </div>

                <div class="row mb-3">
                    <label class="col-sm-2 col-form-label">일일 최대 요금</label>
                    <div class="col-sm-4">
                        <input type="number" name="maxFee" class="form-control" value="${fee.maxFee}" required
                               placeholder="15000원"/>
                    </div>
                </div>
<%--                <div class="row mb-3">--%>
<%--                    <label class="col-sm-2 col-form-label">장애인 할인율 (%)</label>--%>
<%--                    <div class="col-sm-4">--%>
<%--                        <input type="number" name="disabledDiscount" class="form-control"--%>
<%--                               value="${fee.disabledDiscount}" required/>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--                <div class="row mb-3">--%>
<%--                    <label class="col-sm-2 col-form-label">경차 할인율 (%)</label>--%>
<%--                    <div class="col-sm-4">--%>
<%--                        <input type="number" name="compactDiscount" class="form-control" value="${fee.compactDiscount}"--%>
<%--                               required/>--%>
<%--                    </div>--%>
<%--                </div>--%>

<%--                <div class="row mb-3">--%>
<%--                    <label class="col-sm-2 col-form-label">전기차 할인율 (%)</label>--%>
<%--                    <div class="col-sm-4">--%>
<%--                        <input type="number" name="electricDiscount" class="form-control" value="${fee.compactDiscount}"--%>
<%--                               required/>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--                <div class="row mb-3">--%>
<%--                    <label class="col-sm-2 col-form-label">일반 할인율 (%)</label>--%>
<%--                    <div class="col-sm-4">--%>
<%--                        <input type="number" name="normalDiscount" class="form-control" value="${fee.compactDiscount}"--%>
<%--                               required/>--%>
<%--                    </div>--%>
<%--                </div>--%>
                <div class="row mb-3">
                    <label class="col-sm-2 col-form-label">할인 카테고리</label>
                    <div class="col-sm-4">
                        <%
                            String discountCategory = request.getParameter("discountCategory");
                            if (discountCategory == null) discountCategory = "";
                        %>
                        <select name="discountCategory" class="form-select" required>
                            <option value="disabled" <%= "disabled".equals(discountCategory) ? "selected" : "" %>>장애인 할인율</option>
                            <option value="compact" <%= "compact".equals(discountCategory) ? "selected" : "" %>>경차 할인율</option>
                            <option value="electric" <%= "electric".equals(discountCategory) ? "selected" : "" %>>전기차 할인율</option>
                            <option value="normal" <%= "normal".equals(discountCategory) ? "selected" : "" %>>일반 할인율</option>
                        </select>
                    </div>
                </div>

                <div class="row mb-3">
                    <label class="col-sm-2 col-form-label">선택한 할인율 (%)</label>
                    <div class="col-sm-4">
                        <input type="number" name="discountValue" class="form-control"
                               readonly />
                    </div>
                </div>

                <div class="d-flex gap-2">
                    <button type="submit" class="btn btn-primary">저장</button>
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
<script>
    document.addEventListener("DOMContentLoaded", function () {
        const discountSelect = document.querySelector("select[name='discountCategory']");
        const discountInput = document.querySelector("input[name='discountValue']");

        function updateDiscountValue() {
            const selected = discountSelect.value;
            let value = 0;
            if (selected === "disabled") value = 50;
            else if (selected === "compact") value = 30;
            else value = 0;
            discountInput.value = value;
        }

        discountSelect.addEventListener("change", updateDiscountValue);
        updateDiscountValue(); // 초기 로딩 시 실행
    });
</script>
</body>
</html>
