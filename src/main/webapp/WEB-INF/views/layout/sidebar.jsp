<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="layoutSidenav_nav">
    <nav class="sb-sidenav accordion sb-sidenav-light bg-white border-end" id="sidenavAccordion">
        <div class="sb-sidenav-menu">
            <div class="nav">
                <%-- 기본 메뉴 영역 --%>
                <div class="sb-sidenav-menu-heading">MENU</div>

                <a class="nav-link" href="/main.do">
                    <div class="sb-nav-link-icon"><i class="fas fa-tachometer-alt"></i></div>
                    대시 보드
                </a>
                <a class="nav-link" href="/entry.do">
                    <div class="sb-nav-link-icon"><i class="fa-solid fa-square-parking"></i></div>
                    차량 입차
                </a>
                <a class="nav-link" href="/vehicleOut.do">
                    <div class="sb-nav-link-icon"><i class="fa-solid fa-car-rear"></i></div>
                    차량 출차
                </a>
                <a class="nav-link" href="/search.do">
                    <div class="sb-nav-link-icon"><i class="fa-solid fa-magnifying-glass"></i></div>
                    차량 검색
                </a>
                <a class="nav-link" href="/members.do">
                    <div class="sb-nav-link-icon"><i class="fa-solid fa-list"></i></div>
                    회원 관리
                </a>
                <a class="nav-link" href="/pricing.do">
                    <div class="sb-nav-link-icon"><i class="fa-solid fa-won-sign"></i></div>
                    요금 관리
                </a>
            </div>
        </div>
        <div class="sb-sidenav-footer">
            <div class="small">Logged in as:</div>
            ${dto.}님 환영합니다.
        </div>
    </nav>
</div>





