<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
<!-- topbar 영역-->
<%@ include file="../layout/topbar.jsp" %>
<div id="layoutSidenav">
    <!-- sidebar 영역 -->
    <%@ include file="../layout/sidebar.jsp" %>
    <div id="layoutSidenav_content">
        <!-- 여기서 부터 메인 작업 시작. -->
        <main class="container-fluid px-4">
            <h1 class="mt-4">요금 설정</h1>
            <ol class="breadcrumb mb-4">
                <li class="breadcrumb-item active">주차 요금 정책</li>
            </ol>

            <div class="card shadow-sm border-0 mb-5">
                <div class="card-header bg-primary text-white fw-semibold">
                    기본 요금 및 할인율 설정
                </div>

                <c:if test="${not empty message}">
                    <div class="alert bg-success-subtle text-success-emphasis border-0 rounded-0 fw-semibold m-0 px-4 py-3">
                            ${message}
                    </div>
                </c:if>

                <form method="post" action="/pricing.do">
                    <div class="card-body">
                        <div class="row g-4">
                            <!-- 기본 요금 -->
                            <div class="col-md-4">
                                <label for="baseFee" class="form-label">기본 1시간 요금</label>
                                <input type="number" name="baseFee" id="baseFee" class="form-control"
                                       value="${pricingList[0].price}" required min="0">
                            </div>

                            <div class="col-md-4">
                                <label for="extraFee" class="form-label">30분당 추가 요금</label>
                                <input type="number" name="extraFee" id="extraFee" class="form-control"
                                       value="${pricingList[1].price}" required min="0">
                            </div>

                            <div class="col-md-4">
                                <label for="maxFee" class="form-label">일일 최대 요금</label>
                                <input type="number" name="maxFee" id="maxFee" class="form-control"
                                       value="${pricingList[2].price}" required min="0">
                            </div>

                            <!-- 할인율 설정 -->
                            <div class="col-md-3">
                                <label for="disabled" class="form-label">장애인 할인율 (%)</label>
                                <input type="number" name="disabled" id="disabled" class="form-control"
                                       value="${discountList[1].discountRate}" required min="0" max="100">
                            </div>

                            <div class="col-md-3">
                                <label for="compact" class="form-label">경차 할인율 (%)</label>
                                <input type="number" name="compact" id="compact" class="form-control"
                                       value="${discountList[0].discountRate}" required min="0" max="100">
                            </div>

                            <div class="col-md-3">
                                <label for="electric" class="form-label">전기차 할인율 (%)</label>
                                <input type="number" name="electric" id="electric" class="form-control"
                                       value="${discountList[2].discountRate}" required min="0" max="100">
                            </div>

                            <div class="col-md-3">
                                <label for="normal" class="form-label">일반 할인율 (%)</label>
                                <input type="number" name="normal" id="normal" class="form-control"
                                       value="${discountList[3].discountRate}" required min="0" max="100">
                            </div>
                        </div>

                        <div class="row mt-4">
                            <div class="col-md-6 d-grid">
                                <button type="submit" class="btn btn-primary">설정 적용</button>
                            </div>
                            <div class="col-md-6 d-grid">
                                <a href="/main.do" class="btn btn-outline-secondary">메인으로</a>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </main>
    </div>
</div>
<!-- footer 영역 -->
<%@ include file="../layout/footer.jsp" %>

<!-- Bootstrap JS (Popper + Bootstrap Bundle) -->
<script src="../../../assets/js/bootStrap.js"></script>

</body>
</html>
