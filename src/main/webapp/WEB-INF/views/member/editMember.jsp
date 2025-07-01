<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- Membership Management Page -->
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
      <h1 class="mt-4">회원 정보 수정</h1>
      <form action="/editMember.do" method="post" class="mt-4">
        <div class="mb-3">
          <label for="carNumber" class="form-label">차량번호</label>
          <input type="text" class="form-control" id="carNumber" name="carNumber"
                 value="${member.carNumber}" readonly />
        </div>

        <div class="mb-3">
          <label for="driverName" class="form-label">운전자명</label>
          <input type="text" class="form-control" id="driverName" name="driverName"
                 value="${member.driverName}" required />
        </div>

        <div class="mb-3">
          <label for="phone" class="form-label">전화번호</label>
          <input type="tel" class="form-control" id="phone" name="phone"
                 value="${member.phone}" required />
        </div>

        <div class="mb-3">
          <label for="startDate" class="form-label">시작일</label>
          <input type="date" class="form-control" id="startDate" name="startDate"
                 value="${member.startDate}" required />
        </div>

        <div class="mb-3">
          <label for="endDate" class="form-label">종료일</label>
          <input type="date" class="form-control" id="endDate" name="endDate"
                 value="${member.endDate}" required />
        </div>

        <button type="submit" class="btn btn-primary">수정 완료</button>
        <a href="/members.do" class="btn btn-secondary">취소</a>
      </form>
    </main>
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
