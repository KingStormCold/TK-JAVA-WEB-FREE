<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<c:url var="formUrl" value="/api/admin/category" />
<c:url var="formDeleteUrl" value="/api/admin/category/" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>

	<!-- page title area end -->
	<div class="main-content-inner">
		<!-- sales report area start -->
		<div class="sales-report-area mt-5 mb-5">
			<div class="alert alert-danger" id ="alert-fail">
				<p id = "alert-fail-context" ><strong></strong></p>
			</div>
			<div class="alert alert-success" id ="alert-success">
				<p id = "alert-success-context"><strong></strong></p>
			</div>
			<div class="row">
				<div class="col-md-12">
					<div class="search-box pull-right">
						<button class="btn btn-success mb-3 addNew" data-toggle="modal"
							data-target="#exampleModalCenter">
							<span>+</span>
						</button>
					</div>
				</div>
				<div class="col-12 mt-5">
					<div class="card">
						<div class="card-body">
							<div class="data-tables datatable-dark">
								<table id="example" class="text-center">
									<thead class="text-capitalize">
										<tr>
											<th>No.</th>
											<th>Created By</th>
											<th>Created Date</th>
											<th>Category</th>
											<th>Category Father</th>
											<th>Update Date</th>
											<th>Update By</th>
											<th>Action</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${category.categoryInfos}" var="item"
											varStatus="count">
											<tr>
												<td>${count.index + 1}</td>
												<td>${item.createdBy }</td>
												<td>${item.createdDate }</td>
												<td><p class="text-overflow name">${item.categoryName }</p></td>
												<td><p class="text-overflow fatherName">${item.categoryFatherName }</p>
													<input type="hidden" name="${item.categoryFatherCode}"
														value="${item.categoryFatherCode}" id="category_father_code" /></td>
												<td>${item.updatedDate }</td>
												<td>${item.updatedBy }</td>
												<td><a class="fa fa-edit" data-toggle="modal"
													data-target="#exampleModalCenter"></a><a
													class="fa fa-remove" href="https://www.google.com/"></a> 
													<input type="hidden" name="${item.categoryCode}"
													value="${item.categoryCode}" id="category_code" /></td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
				<div class="modal fade" id="exampleModalCenter"
					data-backdrop="static" data-keyboard="false">
					<div class="modal-dialog modal-dialog-centered" role="document">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title"></h5>
							</div>
							<div class="modal-body">
								<div class="row">
									<div class="col-4">
										<b>Category Name</b>
									</div>
									<div class="col-8">
										<input class="form-control" type="text"
											placeholder="Example : Tin tức" name="category_name" id="category_name" />
									</div>
									<div class="mt-5"></div>
									<div class="col-4">
										<b>Category Father</b>
									</div>
									<div class="col-8">
										<select class="custom-select" id ="selectCategory">
											<option value="">Choose a category.</option>
											<c:forEach items="${category.categoryFatherList}" var="item" varStatus="count">
												<option value="${item.code}">${item.name}</option>
											</c:forEach>
										</select>
									</div>
									<input type="hidden" name="category_id" id="category_id" />
								</div>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-secondary"
									data-dismiss="modal">Close</button>
								<button type="button" class="btn btn-primary" id="submit">Save</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
<script type="text/javascript">
	$(document).ready(function(){
	    $('#alert-fail').css('display', 'none');
	    $('#alert-success').css('display', 'none');
	    if (localStorage.getItem("alertSuccess") != null && localStorage.getItem("alertSuccess") != '') {
	        $('#alert-success').css('display', '');
	        $('#alert-success-context').text(localStorage.getItem("alertSuccess"));
	        localStorage.setItem("alertSuccess", '');
	    }
	    if (localStorage.getItem("alertFail") != null && localStorage.getItem("alertFail") != '') {
	        $('#alert-fail').css('display', '');
	        $('#alert-fail-context').text(localStorage.getItem("alertFail"));
	        localStorage.setItem("alertFail", '');
	    }
	    console.log(localStorage.getItem("alertSuccess"));
	    console.log(localStorage.getItem("alertFail"));
	    $('.fa-edit').click(function(){
		    var categoryCode = $(this).siblings('#category_code').val();
			var name = $(this).parents('tr').find('.name').text();
			var categoryFatherCode = $(this).parents('tr').find('#category_father_code').val();
			$(".modal-body #category_name").val(name);
			$(".modal-body #category_id").val(categoryCode);
			$(".modal-header .modal-title").text("Category -> Edit");
			if (categoryFatherCode == "") {
			    $(".modal-body #selectCategory").val("");
			    $("#selectCategory option:selected").siblings().removeAttr('disabled');
			    $("#selectCategory option:selected").siblings().css('display','');
			    $("#selectCategory option[value="+categoryCode+"]").attr('disabled', 'disabled');
			    $("#selectCategory option[value="+categoryCode+"]").css('display', 'none');
			} else {
			    $("#selectCategory option:selected").siblings().removeAttr('disabled');
			    $("#selectCategory option:selected").siblings().css('display','');
			    $(".modal-body #selectCategory").val(categoryFatherCode);
			}
		});
		$('.addNew').click(function() {
		    //siblings tìm tất cả các anh chị em của nó trừ chính nó
		    $("#selectCategory option:selected").siblings().removeAttr('disabled');
		    $("#selectCategory option:selected").siblings().css('display','');
			$(".modal-body #category_name").val("");
			$(".modal-body #category_id").val("");
			$(".modal-body #selectCategory").val("");
			$(".modal-header .modal-title").text("Category -> New");
			console.log(localStorage.getItem("alertSuccess"));
		});
		
    	$('#example').dataTable({
            "autoWidth" : false,
            "scrollX": true
        });
    	
    	$('#submit').click(function () {
    	    event.preventDefault();
    	    var category_father_code = $('#selectCategory').find(":selected").val();
            var dataArray = {};
            dataArray["code"] = $('#category_id').val();
            dataArray["category_name"] = $('#category_name').val();
            dataArray["category_father_code"] = category_father_code;
    	    $.ajax({
    	        type: 'POST',
    	        url: '${formUrl}',
    	        dataType: 'json',
    	        contentType:'application/json',
    	        data: JSON.stringify(dataArray),
    	        success: function (data) {
    	            var message = data.message;
    	            if (data.result == "200") {
    	                if (dataArray['code'] == "") {
    	                    localStorage.setItem("alertSuccess", "Create Category " + message);
        	            } else {
        	                localStorage.setItem("alertSuccess", "Edit Category " + message);
        	            }
    	            } else {
        	            localStorage.setItem("alertFail", message);
    	            }
    	            window.location.href = "<c:url value='/admin/category/list'/>";
    	            return;
                },
                error: function (error) {
                    window.location.href = "<c:url value='/error'/>";
                }
    	      });
        });
    	
    	$('.fa-remove').click(function(){
            event.preventDefault();
            var code = $(this).siblings('#category_code').val();
            var dataArray = {};
            dataArray["code"] = code;
            $.ajax({
    	        type: 'DELETE',
    	        url: '${formDeleteUrl}' + code,
    	        dataType: 'json',
    	        contentType:'application/json',
    	        data: JSON.stringify(dataArray),
    	        success: function (data) {
    	            var message = data.message;
    	            if (data.result == "200") {
    	                localStorage.setItem("alertSuccess", "Delete Category " + message);
    	            } else {
    	                localStorage.setItem("alertFail", message);
    	            }
                    window.location.href = "<c:url value='/admin/category/list'/>";
                    return;
                },
                error: function (error) {
                	window.location.href = "<c:url value='/error'/>";
                }
    	      });
        });
    })
</script>
</body>

</html>