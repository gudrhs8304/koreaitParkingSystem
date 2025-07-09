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
            <h1 class="card-body">할인 적용</h1>
            <div class="card shadow mb-4">
                <div class="card-header bg-secondary text-white fs-4">
                    할인 적용
                </div>
                <div class="card-body">

                    <div class="row mb-3">
                        <label for="carNumber" class="col-sm-2 col-form-label">차량번호</label>
                        <div class="col-sm-4">
                            <input type="text" name="carNumber" id="carNumber" class="form-control" readonly
                                   value="${carNumber}"/>
                        </div>
                    </div>

                    <form action="/disCount.do" method="post" class="row mb-3">
                        <input type="hidden" name="carNumber" value="${carNumber}"/>

                        <label class="col-sm-2 col-form-label">할인 종류</label>
                        <div class="col-sm-10 d-flex align-items-center gap-3">
                            <div class="form-check">
                                <input type="radio" name="discountType" value="disabled"
                                ${empty discountType eq 'disabled' ? 'checked="checked"' : ''} />
                                <label for="discount1" class="form-check-label">장애인</label>
                            </div>
                            <div class="form-check">
                                <input type="radio" name="discountType" id="discount2" value="compact"
                                       class="form-check-input"
                                ${discountType eq 'compact' ? 'checked="checked"' : ''}/>
                                <label for="discount2" class="form-check-label">경차</label>
                            </div>
                            <div class="form-check">
                                <input type="radio" name="discountType" id="discount3" value="electric"
                                       class="form-check-input"
                                ${discountType eq 'electric' ? 'checked="checked"' : ''}/>
                                <label for="discount3" class="form-check-label">전기차</label>
                            </div>
                        </div>
                    </form>

                    <div class="row mb-3">
                        <label class="col-sm-2 col-form-label">할인 적용 금액</label>
                        <div class="col-sm-4">
                            <input type="text" name="discountAmount" class="form-control" value="${discountAmount}원"
                                   readonly/>
                        </div>
                    </div>

                    <div class="row mb-3">
                        <label class="col-sm-2 col-form-label">결제 요금</label>
                        <div class="col-sm-4">
                            <input type="text" name="finalFee" class="form-control" value="${finalFee}원" readonly/>
                        </div>
                    </div>
                </div>
                <div class="card-footer">
                    <form action="/out.do" method="post">
                        <input type="hidden" name="carNumber" value="${carNumber}"/>
                        <input type="submit" name="submitExit" value="출차 처리" class="btn btn-success"/>
                    </form>
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
