<!-- Search Page -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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

<body class="sb-nav-fixed">
<!-- topbar 영역-->
<%@ include file="../layout/topbar.jsp" %>
<div id="layoutSidenav">
    <!-- sidebar 영역 -->
    <%@ include file="../layout/sidebar.jsp" %>
    <div id="layoutSidenav_content">
        <!-- 여기서 부터 메인 작업 시작. -->
        <main class="container-fluid px-4">
            <h1 class="mt-4">차량 검색</h1>
            <form method="get" action="/search.do" class="mb-4">
                <div class="row g-3 align-items-center">
                    <div class="col-auto" style="width: 400px">
                        <input type="search" name="keyword" class="form-control" placeholder="차량번호, 운전자명, 연락처" value="${param.keyword}"/>
                    </div>
                    <div class="col-auto">
                        <button type="submit" class="btn btn-primary">검색</button>
                    </div>
                </div>
            </form>
            <!-- ✅ 오류 메시지 출력 블럭 추가 (폼 위 or 아래에) -->
            <c:if test="${not empty error}">
                <div class="alert alert-danger">${error}</div>
            </c:if>
            <!-- 전체/사용중/빈자리 요약 -->
            <div class="row mb-4">
                <div class="col-md-4">
                    <div class="card text-bg-light">
                        <div class="card-body text-center" style="background-color: black; color: white;">
                            <h5 class="card-title">전체 공간</h5>
                            <p class="card-text fs-3">${total}대</p>
                        </div>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="card text-bg-success">
                        <div class="card-body text-center" style="background-color: #636464">
                            <h5 class="card-title">사용 중</h5>
                            <p class="card-text fs-3">${used}대</p> <!-- 예시 숫자 -->
                        </div>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="card text-bg-info">
                        <div class="card-body text-center" style="background-color: #51585e; color: white">
                            <h5 class="card-title">빈 자리</h5>
                            <p class="card-text fs-3">${emptySpot}대</p> <!-- 예시 숫자 -->
                        </div>
                    </div>
                </div>
            </div>
            <!-- 현재 주차중인 차량 테이블 -->
            <table class="table table-bordered">
                <thead>
                <tr>
                    <th>차량번호</th>
                    <th>차량 유형</th>
                    <th>운전자명</th>
                    <th>연락처</th>
                    <th>입차 시간</th>
                    <th>주차 시간(시간)</th>
                </tr>
                </thead>
                <tbody>

                <c:forEach var="car" items="${carList}">
                    <tr style="<c:if test='${car.over12Hours}'>background-color: #f8d7da;</c:if>">
                        <td>${car.carNumber}</td>
                        <td>${car.carTypeName}</td>
                        <td>${car.driverName}</td>
                        <td>${car.phone}</td>
                        <td>${car.inTimeFormatted}</td>
                        <td>${car.parkedHours}시간 ${car.over12HoursIcon}</td>
                    </tr>
                </c:forEach>
                <c:if test="${empty carList}">
                    <tr>
                        <td colspan="6" class="text-center">검색 결과가 없습니다.</td>
                    </tr>
                </c:if>
                </tbody>
            </table>

            <!-- 페이지네이션 -->
            <nav class="mt-4">
                <ul class="pagination justify-content-center">

                    <!-- 이전 -->
                    <li class="page-item ${page == 1 ? 'disabled' : ''}">
                        <a class="page-link" href="/search.do?page=${page - 1}&keyword=${keyword}">이전</a>
                    </li>

                    <!-- 페이지 번호 -->
                    <c:forEach begin="1" end="${totalPage}" var="p">
                        <li class="page-item ${p == page ? 'active' : ''}">
                            <a class="page-link" href="/search.do?page=${p}&keyword=${keyword}">${p}</a>
                        </li>
                    </c:forEach>

                    <!-- 다음 -->
                    <li class="page-item ${page == totalPage ? 'disabled' : ''}">
                        <a class="page-link" href="/search.do?page=${page + 1}&keyword=${keyword}">다음</a>
                    </li>
                </ul>
            </nav>
        </main>
        <!-- footer 영역 -->
        <%@ include file="../layout/footer.jsp" %>
    </div>
</div>


<!-- Bootstrap JS (Popper + Bootstrap Bundle) -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
        crossorigin="anonymous"></script>


<!-- 선택: Chart.js, jQuery 등 추가 가능 -->
<%--<script src="/assets/js/scripts.js"></script>--%>

<!-- 부트스트랩 기본 js 임포트 -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/umd/simple-datatables.min.js"
        crossorigin="anonymous"></script>
<script src="/assets/js/datatables-simple-demo.js"></script>

</body>
</html>