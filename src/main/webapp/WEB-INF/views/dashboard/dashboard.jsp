<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<main class="container-fluid px-4">
    <h1 class="mt-4">주차관리 시스템 반월당점</h1>
    <ol class="breadcrumb mb-4">
        <li class="breadcrumb-item active">대시보드</li>
    </ol>

    <div class="row">

        <div class="col-xl-3 col-md-6">
            <div class="card bg-light text-dark mb-4">
                <div class="card-body">전체 주차 공간: ${parkingSpots.size()}대</div>
            </div>
        </div>

        <div class="col-xl-3 col-md-6">
            <div class="card bg-dark text-white mb-4">
                <div class="card-body">사용 중: ${usedCount}대</div>
            </div>
        </div>

        <div class="col-xl-3 col-md-6">
            <div class="card bg-secondary text-white mb-4">
                <div class="card-body">빈 자리: ${availableCount}대</div>
            </div>
        </div>
        <div class="col-xl-3 col-md-6">
            <div class="card mb-4" style="border-left: 5px solid #323232; background-color: #f9f9f9;">
                <div class="card-body text-dark">장시간 주차: ${overstayedCount}대</div>
            </div>
        </div>

    </div>
</main>
<main class="container-fluid px-4">
    <h2 class="mt-4">주차 공간 현황</h2>
    <div class="row row-cols-2 row-cols-md-5 g-3 mt-3">
        <c:forEach var="spot" items="${spots}">
            <div class="col">
                <div class="card text-center
                ${spot.isOccupied ? "bg-dark text-white" : "bg-light text-black" }">
                    <div class="card-body">
                        <h5 class="card-title">P${spot.spotNumber}</h5>
                        <p class="card-text">
                                ${spot.isOccupied ? "사용 중" : "비어 있음"}
                        </p>
                        <c:if test="${spot.isOccupied}">
                            <p class="card-text">
                                차량번호: ${spot.carNumber}
                            </p>
                        </c:if>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</main>