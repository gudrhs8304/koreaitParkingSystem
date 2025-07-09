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
            <h1 class="mt-5">출차 처리 완료</h1>
            <form action="vehicleOut.do" method="post" class="mt-4">
                <div class="alert alert-success" role="alert">
                    출차 처리되었습니다.
                </div>
                <a href="/main.do" class="btn btn-success">메인메뉴</a>
            </form>
        </main>
        <!-- footer 영역 -->
        <%@ include file="../layout/footer.jsp" %>
    </div>
</div>



<!-- Bootstrap JS (Popper + Bootstrap Bundle) -->
<script src="../../../assets/js/bootStrap.js"></script>
</body>
</html>
