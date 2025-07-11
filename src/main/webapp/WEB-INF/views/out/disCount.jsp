<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<!-- Vehicle Out Page -->
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
<style>

</style>
<body class="sb-nav-fixed">
<!-- topbar 영역-->
<%@ include file="../layout/topbar.jsp" %>
<div id="layoutSidenav">
    <!-- sidebar 영역 -->
    <%@ include file="../layout/sidebar.jsp" %>
    <div id="layoutSidenav_content">
        <!-- 여기서 부터 메인 작업 시작. -->
        <main class="container-fluid px-4">
            <h1 class="mt-4">할인 적용</h1>
            <ol class="breadcrumb mb-4">
                <li class="breadcrumb-item active">요금 조정</li>
            </ol>

            <div class="card shadow-sm border-0 mb-5">
                <div class="card-header bg-primary text-white fw-semibold">
                    할인 정보 입력
                </div>

                <div class="card-body">
                    <div>
                        <input type="hidden" name="carNumber" value="${carNumber}">

                        <div class="row g-4">
                            <!-- 차량번호 -->
                            <div class="col-md-6">
                                <label for="carNumber" class="form-label">차량번호</label>
                                <input type="text" class="form-control" id="carNumber" value="${carNumber}" readonly>
                            </div>

                            <!-- 할인 종류 선택 -->
                            <div class="col-md-6">
                                <label class="form-label d-block mb-2">할인 종류</label>
                                <div class="d-flex gap-4">
                                    <div class="form-check">
                                        <input class="form-check-input" type="radio" name="discountType" id="discount1" value="disabled"
                                        ${empty discountType eq 'disabled' ? 'checked="checked"' : ''}>
                                        <label class="form-check-label" for="discount1">장애인</label>
                                    </div>
                                    <div class="form-check">
                                        <input class="form-check-input" type="radio" name="discountType" id="discount2" value="compact"
                                        ${discountType eq 'compact' ? 'checked="checked"' : ''}>
                                        <label class="form-check-label" for="discount2">경차</label>
                                    </div>
                                    <div class="form-check">
                                        <input class="form-check-input" type="radio" name="discountType" id="discount3" value="electric"
                                        ${discountType eq 'electric' ? 'checked="checked"' : ''}>
                                        <label class="form-check-label" for="discount3">전기차</label>
                                    </div>
                                </div>
                            </div>

                            <!-- 할인 금액 -->
                            <div class="col-md-6">
                                <label class="form-label">할인 적용 금액</label>
                                <input type="text" name="discountAmount" class="form-control" value="${discountAmount}원" readonly>
                            </div>

                            <!-- 결제 요금 -->
                            <div class="col-md-6">
                                <label class="form-label">결제 요금</label>
                                <input type="text" name="finalFee" class="form-control" value="${finalFee}원" readonly>
                            </div>
                        </div>

                        <div class="row mt-4">
                            <div class="col-md-6 d-grid">
                                <form action="/disCount.do" method="post">
                                <button type="submit" class="btn btn-outline-primary w-100">할인 적용</button>
                                </form>
                            </div>
                            <div class="col-md-6 d-grid">
                                <form action="/out.do" method="post">
                                    <input type="hidden" name="carNumber" value="${carNumber}">
                                    <button type="submit" class="btn btn-success w-100">출차 처리</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </main>
        <!-- footer 영역 -->
        <%@ include file="../layout/footer.jsp" %>
    </div>
</div>


<!-- Bootstrap JS (Popper + Bootstrap Bundle) -->
<script src="../../../assets/js/bootStrap.js"></script>

</body>
</html>
