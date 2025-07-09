<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<main class="container-fluid px-4">

    <!-- 설명 -->
    <h1 class="mt-4">주차관리 시스템 반월당점</h1>
    <ol class="breadcrumb mb-4">
        <li class="breadcrumb-item active">대시보드</li>
    </ol>

    <!-- 요약 카드 -->
    <div class="row mb-4">
        <div class="col-xl-3 col-md-6">
            <div class="card bg-primary-subtle text-primary-emphasis mb-3 shadow-sm">
                <div class="card-body fw-semibold">전체 주차 공간: ${parkingSpots}대</div>
            </div>
        </div>
        <div class="col-xl-3 col-md-6">
            <div class="card bg-info-subtle text-info-emphasis mb-3 shadow-sm">
                <div class="card-body fw-semibold">사용 중: ${usedCount}대</div>
            </div>
        </div>
        <div class="col-xl-3 col-md-6">
            <div class="card bg-light text-dark border-start border-3 border-info mb-3 shadow-sm">
                <div class="card-body fw-semibold">빈 자리: ${availableCount}대</div>
            </div>
        </div>
        <div class="col-xl-3 col-md-6">
            <div class="card bg-white text-dark border-start border-3 border-primary mb-3 shadow-sm">
                <div class="card-body fw-semibold">장시간 주차: ${overstayedCount}대</div>
            </div>
        </div>
    </div>

    <!-- 주차 공간 현황 -->
    <h2 class="mt-5">주차 공간 현황</h2>
    <div class="row row-cols-2 row-cols-sm-3 row-cols-md-5 g-4 mt-3">
        <c:forEach var="spot" items="${spots}">
            <div class="col">
                <div class="card text-center shadow-sm
        ${spot.isOccupied
            ? 'bg-danger-subtle text-danger-emphasis border-start border-3 border-primary'
            : 'bg-success-subtle text-success-emphasis border-start border-3 border-black'}"
                     style="min-height: 170px;">

                    <div class="card-body d-flex flex-column justify-content-center">
                        <h5 class="card-title">
                            <i class="fa-solid fa-square-parking me-1"></i> P${spot.spotNumber}
                        </h5>
                        <p class="fw-semibold mb-1">${spot.isOccupied ? '사용 중' : '비어 있음'}</p>
                        <c:if test="${spot.isOccupied}">
                            <p class="card-text small mb-0">차량번호: ${spot.carNumber}</p>
                        </c:if>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>

</main>