<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<main class="container-fluid px-4">
    <h1 class="mt-4">주차관리 시스템 반월당점</h1>
    <ol class="breadcrumb mb-4">
        <li class="breadcrumb-item active">대시보드</li>
    </ol>

    <div class="row">
        <div class="col-xl-3 col-md-6">
            <div class="card bg-primary text-white mb-4">
                <div class="card-body">전체 주차 공간: 10대</div>
            </div>
        </div>
        <div class="col-xl-3 col-md-6">
            <div class="card bg-success text-white mb-4">
                <div class="card-body">사용 중: ${usedCount}대</div>
            </div>
        </div>
        <div class="col-xl-3 col-md-6">
            <div class="card bg-warning text-white mb-4">
                <div class="card-body">빈 자리: ${availableCount}대</div>
            </div>
        </div>
        <div class="col-xl-3 col-md-6">
            <div class="card bg-danger text-white mb-4">
                <div class="card-body">장시간 주차: ${overstayedCount}대</div>
            </div>
        </div>
    </div>
</main>

<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>

<%
    // 예시 데이터 (실제는 DB에서 가져와야 함)
    List<Map<String, Object>> parkingSpots = new ArrayList<>();
    for (int i = 1; i <= 10; i++) {
        Map<String, Object> spot = new HashMap<>();
        spot.put("num", i);
        spot.put("isOccupied", i % 3 == 0); // 예시: 3의 배수는 사용 중
        parkingSpots.add(spot);
    }
%>

<main class="container-fluid px-4">
    <h2 class="mt-4">주차 공간 현황</h2>
    <div class="row row-cols-2 row-cols-md-5 g-3 mt-3">
        <% for (Map<String, Object> spot : parkingSpots) {
            int num = (int) spot.get("num");
            boolean isOccupied = (boolean) spot.get("isOccupied");
        %>
        <div class="col">
            <div class="card text-white text-center
            <%= isOccupied ? "bg-danger" : "bg-success" %>">
                <div class="card-body">
                    <h5 class="card-title">P<%= num %></h5>
                    <p class="card-text">
                        <%= isOccupied ? "사용 중" : "비어 있음" %>
                    </p>
                </div>
            </div>
        </div>
        <% } %>
    </div>
</main>