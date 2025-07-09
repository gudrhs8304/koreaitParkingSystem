<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- Topbar Include -->
<nav class="sb-topnav navbar navbar-expand navbar-light bg-primary shadow-sm">
    <!-- Navbar Brand -->
    <a class="navbar-brand text-white ps-3 fw-bold" href="/main.do">KOREAIT PARKING</a>

    <!-- Sidebar Toggle -->
    <button class="btn btn-link text-white btn-sm order-1 order-lg-0 me-4 me-lg-0" id="sidebarToggle">
        <i class="fas fa-bars"></i>
    </button>

    <!-- Search Form -->
    <form class="d-none d-md-inline-block form-inline ms-auto me-0 me-md-3 my-2 my-md-0" action="/topbar.do" method="post">
        <div class="input-group">
            <input class="form-control border-0 shadow-sm" type="text" placeholder="차량번호 입력" name="carNumber" />
            <button class="btn btn-light text-primary fw-bold" id="btnNavbarSearch" type="submit">
                <i class="fas fa-search"></i>
            </button>
        </div>
    </form>

    <!-- User Dropdown -->
    <ul class="navbar-nav ms-auto ms-md-0 me-3 me-lg-4">
        <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle text-white" id="navbarDropdown" role="button" data-bs-toggle="dropdown">
                <i class="fas fa-user fa-fw"></i>
            </a>
            <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdown">
                <li><a class="dropdown-item text-dark" href="/logout.do">로그아웃</a></li>
            </ul>
        </li>
    </ul>
</nav>