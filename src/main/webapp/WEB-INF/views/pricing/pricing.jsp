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
            <div class="card mb-4">
                <c:if test="${not empty message}">
                    <div class="alert alert-success" role="alert">
                            ${message}
                    </div>
                </c:if>
                <form method="post" action="/pricing.do" class="mt-3">
                    <div class="card-body">
                        <table class="table table-bordered">
                            <tr>
                                <td><label for="baseFee" class="">기본 1시간 요금</label></td>

                                <td><input type="number" name="baseFee" id="baseFee" class="form-control"
                                           value="${pricingList[0].price}"/></td>
                            </tr>

                            <tr>
                                <td><label for="extraFee" class="">30분당 추가 요금</label></td>
                                <td><input type="number" name="extraFee" id="extraFee" class="form-control"
                                           value="${pricingList[1].price}"/></td>
                            </tr>

                            <tr>
                                <td><label for="maxFee" class="col-sm-2 col-form-label">일일 최대 요금</label></td>
                                <td class="">
                                    <input type="number" name="maxFee" id="maxFee" class="form-control"
                                           value="${pricingList[2].price}"/></td>
                            </tr>
                            <tr>
                                <td><label class="col-sm-2 col-form-label">장애인 할인율 (%)</label></td>
                                <td class="col-sm-4">
                                    <input type="number" name="disabled" class="form-control"
                                           value="${discountList[1].discountRate}" required/></td>
                            </tr>
                            <tr>
                                <td><label class="col-sm-2 col-form-label">경차 할인율 (%)</label></td>
                                <td class="col-sm-4">
                                    <input type="number" name="compact" class="form-control"
                                           value="${discountList[0].discountRate}"
                                           required/>
                                </td>
                            </tr>
                            <tr>
                                <td><label class="col-sm-2 col-form-label">전기차 할인율 (%)</label></td>
                                <td class="col-sm-4">
                                    <input type="number" name="electric" class="form-control"
                                           value="${discountList[2].discountRate}"
                                           required/>
                                </td>
                            </tr>
                            <tr>
                                <td><label class="col-sm-2 col-form-label">일반 할인율 (%)</label></td>
                                <td class="col-sm-4">
                                    <input type="number" name="normal" class="form-control"
                                           value="${discountList[3].discountRate}"
                                           required/>
                                </td>
                            </tr>
                        </table>
                    </div>

                    <button type="submit" class="btn btn-primary">적용</button>

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
