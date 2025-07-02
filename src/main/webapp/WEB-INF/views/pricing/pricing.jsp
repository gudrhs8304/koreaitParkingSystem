<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
            <c:if test="${not empty message}">
                <div class="alert alert-success" role="alert">
                        ${message}
                </div>
            </c:if>
            <form method="post" action="/pricing.do" class="mt-3">

                <div class="row mb-3">
                    <label for="baseFee" class="col-sm-2 col-form-label">기본 1시간 요금</label>
                    <div class="col-sm-4">
                        <input type="number" name="baseFee" id="baseFee" class="form-control" value="${pricingList[0].price}"/>
                    </div>
                </div>

                <div class="row mb-3">
                    <label for="extraFee" class="col-sm-2 col-form-label">30분당 추가 요금</label>
                    <div class="col-sm-4">
                        <input type="number" name="extraFee" id="extraFee" class="form-control" value="${pricingList[1].price}"/>
                    </div>
                </div>

                <div class="row mb-3">
                    <label for="maxFee" class="col-sm-2 col-form-label">일일 최대 요금</label>
                    <div class="col-sm-4">
                        <input type="number" name="maxFee" id="maxFee" class="form-control" value="${pricingList[2].price}"/>
                    </div>
                </div>
                <div class="row mb-3">
                    <label class="col-sm-2 col-form-label">장애인 할인율 (%)</label>
                    <div class="col-sm-4">
                        <input type="number" name="disabled" class="form-control"
                               value="${discountList[1].discountRate}" required/>
                    </div>
                </div>
                <div class="row mb-3">
                    <label class="col-sm-2 col-form-label">경차 할인율 (%)</label>
                    <div class="col-sm-4">
                        <input type="number" name="compact" class="form-control"
                               value="${discountList[0].discountRate}"
                               required/>
                    </div>
                </div>

                <div class="row mb-3">
                    <label class="col-sm-2 col-form-label">전기차 할인율 (%)</label>
                    <div class="col-sm-4">
                        <input type="number" name="electric" class="form-control"
                               value="${discountList[2].discountRate}"
                               required/>
                    </div>
                </div>
                <div class="row mb-3">
                    <label class="col-sm-2 col-form-label">일반 할인율 (%)</label>
                    <div class="col-sm-4">
                        <input type="number" name="normal" class="form-control"
                               value="${discountList[3].discountRate}"
                               required/>
                    </div>
                </div>
                <%--                    <div class="row mb-3">--%>
                <%--                        <label class="col-sm-2 col-form-label">할인 카테고리</label>--%>
                <%--                        <div class="col-sm-4">--%>
                <%--                            <select name="discountCategory" class="form-select">--%>
                <%--                                <option value="disabled" <c:if test="${discountCategory eq 'disabled'}">selected</c:if>>--%>
                <%--                                    장애인 할인율--%>
                <%--                                </option>--%>
                <%--                                <option value="compact" <c:if test="${discountCategory eq 'compact'}">selected</c:if>>경차--%>
                <%--                                    할인율--%>
                <%--                                </option>--%>
                <%--                                <option value="electric" <c:if test="${discountCategory eq 'electric'}">selected</c:if>>--%>
                <%--                                    전기차 할인율--%>
                <%--                                </option>--%>
                <%--                                <option value="normal" <c:if test="${discountCategory eq 'normal'}">selected</c:if>>일반--%>
                <%--                                    할인율--%>
                <%--                                </option>--%>
                <%--                            </select>--%>
                <button type="submit" class="btn btn-primary">적용</button>
            </form>
        </main>
    </div>
</div>
</body>

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
