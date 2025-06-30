<%@ page import="java.time.LocalDate" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
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
        <%!
            static class Member {
                public String carNumber;
                public String diverName;
                public String phone;
                public LocalDate startDate;
                public LocalDate endDate;

                public Member(String carNumber, String diverName, String phone, LocalDate startDate, LocalDate endDate) {
                    this.carNumber = carNumber;
                    this.diverName = diverName;
                    this.phone = phone;
                    this.startDate = startDate;
                    this.endDate = endDate;
                }
            }
        %>

        <%
        // sql 에서 가져올 데이터 대신 출력할 더미
            List<Member> members = new ArrayList<>();
            for(int i = 0; i < 10; i++) {
                members.add(new Member("car" + i, "driver" + i, "010-1234-567" + i, LocalDate.now(), LocalDate.now().plusDays(30)));
            }
        %>
        <div class="container-fluid px-4">
            <h2 class="mt-4 text-primary">회원 목록</h2>
            <div class="card mb-4">
                <div class="card-header">
                    <i class="fas fa-table me-1"></i>
                    등록된 회원 정보
                </div>
                <div class="card-body">
                    <table id="membersTable" class="table table-bordered table-hover">
                    <thead class="table-light text-center">
                        <tr>
                            <th>차량번호</th>
                            <th>운전자명</th>
                            <th>전화번호</th>
                            <th>시작일</th>
                            <th>종료일</th>
                        </tr>
                        </thead>
                        <tbody>
                        <% for (Member member : members) { %>
                        <tr class="text-center">
                            <td><a href="#"><%= member.carNumber %></a></td>
                            <td><%= member.diverName %></td>
                            <td><%= member.phone %></td>
                            <td><%= member.startDate %></td>
                            <td><%= member.endDate %></td>
                        </tr>
                        <% } %>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>

        <!-- footer 영역 -->
        <%@ include file="/layout/footer.jsp" %>
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
