<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- Membership Management Page -->
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
            <h1 class="mt-4">회원 정보 추가</h1>
            <form action="/addMember.do" method="post" class="mt-4">
                <div class="mb-3">
                    <label for="carNumber" class="form-label">차량번호</label>
                    <input type="text" class="form-control" id="carNumber" name="carNumber" required/>
                </div>

                <div class="mb-3">
                    <label for="driverName" class="form-label">운전자명</label>
                    <input type="text" class="form-control" id="driverName" name="driverName" required />
                </div>

                <div class="mb-3">
                    <label for="phone" class="form-label">전화번호</label>
                    <input type="tel" class="form-control" id="phone" name="phone" required />
                </div>

                <div class="mb-3">
                    <label for="startDate" class="form-label">시작일</label>
                    <input type="date" class="form-control" id="startDate" name="startDate" required />
                </div>

                <div class="mb-3">
                    <label for="endDate" class="form-label">종료일</label>
                    <input type="date" class="form-control" id="endDate" name="endDate" required />
                </div>

                <button type="submit" class="btn btn-primary">등록 완료</button>
                <a href="/members.do" class="btn btn-secondary">취소</a>
            </form>
        </main>
        <!-- footer 영역 -->
        <%@ include file="../layout/footer.jsp" %>
    </div>
</div>


<!-- Bootstrap JS (Popper + Bootstrap Bundle) -->
<script src="../../../assets/js/bootStrap.js"></script>

<!-- simpleDB -->
<script src="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/umd/simple-datatables.min.js"
        crossorigin="anonymous"></script>
</body>
</html>
