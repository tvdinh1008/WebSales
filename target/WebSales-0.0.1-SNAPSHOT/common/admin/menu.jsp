<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    
    
<!-- Sidebar -->
    <ul class="sidebar navbar-nav">
      <li class="nav-item active">
        <a class="nav-link" href="index.html">
          <i class="fas fa-fw fa-tachometer-alt"></i>
          <span>Dashboard</span>
        </a>
      </li>
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="pagesDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          <i class="fas fa-fw fa-folder"></i>
          <span>Pages</span>
        </a>
        <div class="dropdown-menu" aria-labelledby="pagesDropdown">
          <h6 class="dropdown-header">Login Screens:</h6>
          <a class="dropdown-item" href="<c:url value='/dang-nhap?action=login' />">Login</a>
          <a class="dropdown-item" href="<c:url value='/dang-ky?action=register' />">Register</a>
          <a class="dropdown-item" href="<c:url value='/quen-mat-khau?action=forgot-password' />">Forgot Password</a>
          <div class="dropdown-divider"></div>
          <h6 class="dropdown-header">Other Pages:</h6>
          <a class="dropdown-item" href="404.html">404 Page</a>
          <a class="dropdown-item" href="blank.html">Blank Page</a>
        </div>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="charts.html">
          <i class="fas fa-fw fa-chart-area"></i>
          <span>Charts</span></a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="tables.html">
          <i class="fas fa-fw fa-table"></i>
          <span>Tables</span></a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="<c:url value='#' />">
          <i class="fas fa-users"></i>
          <span>Quản lý khách hàng</span></a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="<c:url value='#' />">
          <i class="fas fa-gift"></i>
          <span>Quản lý sản phẩm</span></a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="<c:url value='#' />">
          <i class="fas fa-shopping-cart"></i>
          <span>Quản lý đơn hàng</span></a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="<c:url value='#' />">
          <i class="fas fa-user-cog"></i>
          <span>Quản lý nhân viên</span></a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="tables.html">
          <i class="fas fa-cog"></i>
          <span>Quản trị web</span></a>
      </li>
    </ul>
    