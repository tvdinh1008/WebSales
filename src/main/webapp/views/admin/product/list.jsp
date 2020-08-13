<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Danh sách sản phẩm</title>
</head>
<body>
	<div class="container-fluid">

        <!-- Breadcrumbs-->
        <ol class="breadcrumb">
          <li class="breadcrumb-item">
            <a href="#">Dashboard</a>
          </li>
          <li class="breadcrumb-item active">Tables</li>
        </ol>

        <!-- DataTables Example -->
        <div class="card mb-3">
          <div class="card-header">
            <i class="fas fa-table"></i>
            Data Table Example</div>
          <div class="card-body">
            <div class="table-responsive">
              <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                <thead>
                  <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>UserName</th>
                    <th>Password</th>
                    <th>Status</th>
                    <th>Role</th>
                  </tr>
                </thead>
                <tbody>
                  <c:forEach var="item" items="${LIST_CUSTOMER }">
                  <tr>
                  	<td>${item.id}</td>
                  	<td>${item.name}</td>
                  	<td>${item.username}</td>
                  	<td>${item.password}</td>
                  	<td>${item.status}</td>
                  	<td>${item.getRole().name}</td>
                  </tr>
                  </c:forEach>
                </tbody>
              </table>
              <ul class="pagination" id="pagination"></ul>
            </div>
          </div>
          <div class="card-footer small text-muted">Updated yesterday at 11:59 PM</div>
        </div>

      </div>
      <!-- /.container-fluid -->
	<script type="text/javascript">
		    $(function () {
		        window.pagObj = $('#pagination').twbsPagination({
		            totalPages: 35,
		            visiblePages: 10,
		            onPageClick: function (event, page) {
		                console.info(page + ' (from options)');
		            }
		        }).on('page', function (event, page) {
		            console.info(page + ' (from event listening)');
		        });
		    });
	 </script>
</body>
</html>