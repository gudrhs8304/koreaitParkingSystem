<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div id="layoutSidenav_nav">
    <nav class="sb-sidenav accordion sb-sidenav-dark" id="sidenavAccordion">
        <div class="sb-sidenav-menu">
            <div class="nav">
                <%-- 기본 메뉴 영역 --%>
                <div class="sb-sidenav-menu-heading">MENU</div>

                <%-- 대시보드 링크 --%>
                <a class="nav-link" href="/index.jsp">
                    <div class="sb-nav-link-icon"><i class="fas fa-tachometer-alt"></i></div>
                    대시 보드
                </a>
                <a class="nav-link" href="/page/vehicleIn.jsp">
                    <div class="sb-nav-link-icon"><i class="fa-solid fa-square-parking"></i></div>
                    차량 입차
                </a>
                <a class="nav-link" href="/page/vehicleOut.jsp">
                    <div class="sb-nav-link-icon"><i class="fa-solid fa-car-rear"></i></div>
                    차량 출차
                </a>
                    <a class="nav-link" href="/page/search.jsp">
                        <div class="sb-nav-link-icon"><i class="fa-solid fa-magnifying-glass"></i></div>
                        차량 검색
                    </a>
                    <a class="nav-link" href="/page/parkingStatus.jsp">
                        <div class="sb-nav-link-icon"><i class="fa-solid fa-eye"></i></div>
                        실시간 현황
                    </a>
                    <a class="nav-link" href="/page/members.jsp">
                        <div class="sb-nav-link-icon"><i class="fa-solid fa-list"></i></div>
                        회원 관리
                    </a>
                    <a class="nav-link" href="/page/pricing.jsp">
                        <div class="sb-nav-link-icon"><i class="fa-solid fa-won-sign"></i></div>
                        요금 관리
                    </a>
            </div>
        </div>
        <div class="sb-sidenav-footer">
            <div class="small">Logged in as:</div>
            <%
                String id = "Admin";
            %>
            <%=id%>님 환영합니다.
        </div>
    </nav>
</div>





