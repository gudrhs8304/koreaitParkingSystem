<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="layoutSidenav_nav">
    <nav class="sb-sidenav accordion bg-light border-end shadow-sm" id="sidenavAccordion">
        <div class="sb-sidenav-menu p-3">
            <div class="nav">
                <div class="sb-sidenav-menu-heading text-primary fw-bold">MENU</div>

                <a href="/main.do" class="nav-link active text-dark fw-semibold">
                    <div class="sb-nav-link-icon text-primary"><i class="fas fa-tachometer-alt"></i></div>
                    대시 보드
                </a>

                <a href="/entry.do" class="nav-link active text-dark fw-semibold">
                    <div class="sb-nav-link-icon text-primary"><i class="fa-solid fa-square-parking"></i></div>
                    차량 입차
                </a>

                <a href="/vehicleOut.do" class="nav-link active text-dark fw-semibold">
                    <div class="sb-nav-link-icon text-primary"><i class="fa-solid fa-car-rear"></i></div>
                    차량 출차
                </a>

                <a href="/search.do" class="nav-link active text-dark fw-semibold">
                    <div class="sb-nav-link-icon text-primary"><i class="fa-solid fa-magnifying-glass"></i></div>
                    차량 검색
                </a>

                <a href="/members.do" class="nav-link active text-dark fw-semibold">
                    <div class="sb-nav-link-icon text-primary"><i class="fa-solid fa-list"></i></div>
                    회원 관리
                </a>

                <a href="/pricing.do" class="nav-link active text-dark fw-semibold">
                    <div class="sb-nav-link-icon text-primary"><i class="fa-solid fa-won-sign"></i></div>
                    요금 관리
                </a>
            </div>
        </div>

        <div class="sb-sidenav-footer bg-white text-black">
            <div class="small">Logged in as:</div>
            ${sessionScope.admin.username}님 로그인중
        </div>
    </nav>
</div>