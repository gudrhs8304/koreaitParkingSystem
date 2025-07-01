<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- Vehicle Out Page -->

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

        <%-- 기존 형곤 코드 --%>
        <main class="container-fluid px-4">

            <h1 class="mt-4">출차 관리</h1>
            <c:if test="${not empty errorMessage}">
              <script>
                alert('${errorMessage}');
                history.back();
              </script>
            </c:if>
            <div class="row mb-3">
                <form action="/vehicleOut.do" method="post" class="form-inline">
                    <label for="carNumber" class="col-sm-2 col-form-label">차량번호</label>
                    <div class="col-sm-4">
                        <input type="text" name="carNumber" id="carNumber" class="form-control"
                               value="${carNumber != null ? carNumber : ''}"/>
                    </div>
                    <div class="col-sm-2">
                        <input type="submit" name="search" value="검색" class="btn btn-primary"/>
                    </div>
                </form>
            </div>

            <div class="row mb-3">
                <label class="col-sm-2 col-form-label">입차 시간</label>
                <div class="col-sm-4">
                    <input type="text" name="inTime" class="form-control" readonly
                           value="${inTime != null ? inTime : ''}"/>
                </div>
            </div>

            <form action="/disCount.do" method="post">
                <div class="row mb-3">
                    <label class="col-sm-2 col-form-label">주차 요금</label>
                    <div class="col-sm-4">
                        <input type="text" name="finalFee" class="form-control" readonly
                               value="${fee != null ? fee : 0}"/>
                    </div>
                </div>
                <div class="d-flex gap-2">
                    <input type="hidden" name="carNumber" value="${carNumber}"/>
                    <input type="submit" value="할인 적용" class="btn btn-success"/>
                </div>
            </form>
            <div>
                <form action="/out.do" method="post">
                    <input type="hidden" name="carNumber" value="${carNumber}"/>
                    <input type="submit" name="submitExit" value="출차 처리" class="btn btn-success"/>
                </form>
            </div>
        </main>

        <%-- 병민 리팩토링 --%>

        <%--        <main class="container-fluid px-4">--%>
        <%--            <h1 class="mt-4">출차 관리</h1>--%>

        <%--            &lt;%&ndash; 차량 번호 검색 &ndash;%&gt;--%>
        <%--            <form action="/vehicleOut.do" method="post" class="row mb-3">--%>
        <%--                <label for="carNumber" class="col-sm-2 col-form-label">차량번호</label>--%>
        <%--                <div class="col-sm-4">--%>
        <%--                    <input type="text" name="carNumber" id="carNumber" class="form-control" value="${carNumber}" />--%>
        <%--                </div>--%>
        <%--                <div class="col-sm-2">--%>
        <%--                    <input type="submit" value="검색" class="btn btn-primary" />--%>
        <%--                </div>--%>
        <%--            </form>--%>

        <%--            <c:if test="${not empty errorMessage}">--%>
        <%--                <script>--%>
        <%--                    alert('${errorMessage}');--%>
        <%--                    history.back();--%>
        <%--                </script>--%>
        <%--            </c:if>--%>
        <%--            &lt;%&ndash; 입차 시간 &ndash;%&gt;--%>
        <%--            <div class="row mb-3">--%>
        <%--                <label class="col-sm-2 col-form-label">입차 시간</label>--%>
        <%--                <div class="col-sm-4">--%>
        <%--                    <input type="text" name="inTime" class="form-control" readonly value="${inTime}" />--%>
        <%--                </div>--%>
        <%--            </div>--%>

        <%--            &lt;%&ndash; 주차 요금 &ndash;%&gt;--%>
        <%--            <div class="row mb-3">--%>
        <%--                <label class="col-sm-2 col-form-label">주차 요금</label>--%>
        <%--                <div class="col-sm-4">--%>
        <%--                    <input type="text" name="fee" class="form-control" readonly value="${fee}원" />--%>
        <%--                </div>--%>
        <%--            </div>--%>

        <%--            &lt;%&ndash; 액션 버튼 &ndash;%&gt;--%>
        <%--            <div class="d-flex gap-2 mt-3">--%>
        <%--                <form action="/disCount.do" method="get">--%>
        <%--                    <input type="hidden" name="carNumber" value="${carNumber}" />--%>
        <%--                    <input type="submit" value="할인 적용" class="btn btn-success" />--%>
        <%--                </form>--%>

        <%--                <form action="/out.do" method="post">--%>
        <%--                    <input type="hidden" name="carNumber" value="${carNumber}" />--%>
        <%--                    <input type="submit" name="submitExit" value="출차 처리" class="btn btn-success" />--%>
        <%--                </form>--%>
        <%--            </div>--%>
        <%--        </main>--%>
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
