<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
            <h1 class="mt-4">회원 목록</h1>
            <ol class="breadcrumb mb-4">
                <li class="breadcrumb-item active">정기권 회원 정보</li>
            </ol>

            <div class="card shadow-sm border-0 mb-4">
                <div class="card-header bg-primary text-white fw-semibold">
                    등록된 회원 정보
                </div>
                <div class="card-body">
                    <table id="membersTable" class="table table-bordered table-hover align-middle text-center">
                        <thead class="table-light">
                        <tr>
                            <th>차량번호</th>
                            <th>운전자명</th>
                            <th>전화번호</th>
                            <th>시작일</th>
                            <th>종료일</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="member" items="${members}">
                            <tr>
                                <td>
                                    <c:url var="editUrl" value="/editMember.do">
                                        <c:param name="carNumber" value="${member.carNumber}"/>
                                    </c:url>
                                    <a href="${editUrl}" class="text-decoration-none text-dark fw-bold">
                                        <c:out value="${member.carNumber}"/>
                                    </a>
                                </td>
                                <td>
                                    <c:out value="${member.driverName}"/>
                                    <c:if test="${not empty member.badge}">
                                        <c:choose>
                                            <c:when test="${member.badge eq '연정액회원'}">
                                                <span class="badge bg-primary ms-2">${member.badge}</span>
                                            </c:when>
                                            <c:when test="${member.badge eq '월정액회원'}">
                                                <span class="badge bg-success ms-2">${member.badge}</span>
                                            </c:when>
                                            <c:otherwise>
                                                <span class="badge bg-secondary ms-2">${member.badge}</span>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:if>
                                </td>
                                <td><c:out value="${member.phone}"/></td>
                                <td><c:out value="${member.startDate}"/></td>
                                <td><c:out value="${member.endDate}"/></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                <div class="card-footer d-flex justify-content-end bg-light">
                    <a href="/addMember.do" class="btn btn-outline-primary">회원 등록</a>
                </div>
            </div>
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

<!-- 추후 js 파일 out 고려 -->
<script>
    window.addEventListener('DOMContentLoaded', () => {
        const table = document.getElementById('membersTable');
        if (table) {
            new simpleDatatables.DataTable(table, {
                perPage: 20,
                perPageSelect: [5, 10, 20, 50],
                labels: {
                    perPage: "페이지당 항목 수",
                    noRows: "표시할 데이터가 없습니다",
                    info: "총 {rows}건 중 {start}~{end} 표시 중",
                    searchPlaceholder: "검색어 입력...",
                    searchTitle: "검색"
                }
            });
        }
    });
</script>
</body>
</html>
