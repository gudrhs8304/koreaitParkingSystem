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

<body class="sb-nav-fixed">
<!-- topbar 영역-->
<%@ include file="../layout/topbar.jsp" %>
<div id="layoutSidenav">
    <!-- sidebar 영역 -->
    <%@ include file="../layout/sidebar.jsp" %>
    <div id="layoutSidenav_content">
        <!-- 여기서 부터 메인 작업 시작. -->
        <main class="container-fluid px-4">
            <h1 class="mt-4">출차 완료</h1>
            <ol class="breadcrumb mb-4">
                <li class="breadcrumb-item active">출차 결과</li>
            </ol>

            <div class="card shadow-sm border-0">
                <div class="card-body">
                    <div class="alert bg-success-subtle text-success-emphasis border-0 fw-semibold" role="alert">
                        출차가 정상적으로 처리되었습니다.
                    </div>
                    <div class="mt-3 d-flex justify-content-end">
                        <a href="/main.do" class="btn btn-primary">메인 메뉴로 이동</a>
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
