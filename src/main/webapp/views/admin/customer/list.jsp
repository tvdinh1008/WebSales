<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Danh sách user</title>
</head>
<body>
	<form action="<c:url value='/admin-customer'/>" id="formSubmit" method="get">
		<div class="container-fluid">
			<!-- Breadcrumbs-->
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="#">Dashboard</a></li>
				<li class="breadcrumb-item active">Tables</li>
			</ol>

			<!-- DataTables Example -->
			<div class="card mb-3">
				<div class="card-header">
					<i class="fas fa-table"></i> Data Table Example
				</div>
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
								<c:forEach var="item" items="${model.listResult}">
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
						<!-- chứa giá trị cho phân trang để truyền cho controller-->
						<input type="hidden" value="" id="page" name="page"/>
						<input type="hidden" value="" id="maxPageItem" name="maxPageItem"/>
						<input type="hidden" value="" id="sortName" name="sortName"/>
						<input type="hidden" value="" id="sortBy" name="sortBy"/>
					</div>
				</div>
				<div class="card-footer small text-muted">Updated yesterday at 11:59 PM</div>
			</div>
		</div>
	</form>
	<!-- /.container-fluid -->
	<script type="text/javascript">
	
		var currentPage=${model.page}; // page đang đứng
		var totalPage=${model.totalPage}; //tổng số page
		var limit=2;  //giới hạn số item trong 1 page
		
		$(function() {
			window.pagObj = $('#pagination').twbsPagination({
				totalPages : totalPage,
				visiblePages : 5,  //số page hiển thị để click
				startPage : currentPage,
				onPageClick : function(event, page) {//page là trang đc click
					if(currentPage!=page)
					{	//console.info(page + ' (from options)');
						$('#page').val(page);//truyền vào page cho view(trang ta click vào)
						$('#maxPageItem').val(limit); //truyền maxPage vào view để submit
						
						$('#sortName').val('name');//sắp xếp theo tên
						$('#sortBy').val('desc');// chiều giảm dần
						
						$('#formSubmit').submit();
					}
				}
			}).on('page', function(event, page) {
				console.info(page + ' (from event listening)');
			});
		});
	</script>
</body>
</html>