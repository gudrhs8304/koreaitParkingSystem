<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- Vehicle In Page -->
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
            <ol class="breadcrumb mb-4">
                <li class="breadcrumb-item active">차량 입차</li>
            </ol>

            <!-- 알림 메시지 -->
            <c:if test="${not empty successMessage}">
                <div id="successAlert" class="alert bg-success-subtle text-success-emphasis border-0 shadow-sm">
                        ${successMessage}
                </div>
            </c:if>
            <c:if test="${not empty errorMessage}">
                <div id="errorAlert" class="alert bg-danger-subtle text-danger-emphasis border-0 shadow-sm">
                        ${errorMessage}
                </div>
            </c:if>

            <script>
                window.addEventListener('DOMContentLoaded', () => {
                    ['successAlert', 'errorAlert'].forEach(id => {
                        const el = document.getElementById(id);
                        if (el) setTimeout(() => el.style.display = 'none', 3000);
                    });
                });
            </script>

            <!-- 카드 전체 구조 -->
            <div class="card shadow-sm border-0 mb-5">
                <div class="card-header bg-primary text-white fw-semibold">
                    차량 정보 입력
                </div>

                <div class="card-body">
                    <form action="${pageContext.request.contextPath}/entry.do" method="post">
                        <div class="row g-4">
                            <div class="col-md-6">
                                <label for="carNumber" class="form-label">차량번호</label>
                                <input type="text" name="carNumber" id="carNumber" class="form-control" required>
                            </div>

                            <div class="col-md-6">
                                <label for="carTypeCode" class="form-label">차량 유형</label>
                                <select name="carTypeCode" id="carTypeCode" class="form-select" required>
                                    <option value="normal">일반</option>
                                    <option value="disabled">장애인</option>
                                    <option value="compact">경차</option>
                                </select>
                            </div>

                            <div class="col-md-6">
                                <label for="driverName" class="form-label">운전자명</label>
                                <input type="text" name="driverName" id="driverName" class="form-control" required>
                            </div>

                            <div class="col-md-6">
                                <label for="phone" class="form-label">연락처</label>
                                <input type="text" name="phone" id="phone" class="form-control" required>
                            </div>

                            <div class="col-md-6">
                                <label class="form-label">입차 시간</label>
                                <input type="text" name="inTime" class="form-control" readonly
                                       value="<%= new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date()) %>">
                            </div>
                        </div>

                        <div class="row mt-4">
                            <div class="col-md-6 d-grid">
                                <button type="submit" class="btn btn-primary">입차 등록</button>
                            </div>
                            <div class="col-md-6 d-grid">
                                <a href="/" class="btn btn-outline-secondary">메인으로</a>
                            </div>
                        </div>
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
