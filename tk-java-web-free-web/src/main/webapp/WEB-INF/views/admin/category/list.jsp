<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<c:url var="categories" value="/api/admin/category" />
<c:url var="deleteCategory" value="/api/admin/category/" />
<c:url var="findAll" value="/api/admin/category/find-all" />
<c:url var="pageCategory" value="/admin/category/list" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>

<div class="col-sm-12 main-content">
      <div>
          <div class="add-role col-sm-2">
              <a title="Add Role" class="btn btn-primary btn-add-category" data-toggle="modal" data-target="#myModal" data-keyboard="false" data-backdrop="static">+</a>
          </div>
          <div id="loader" class = "display-none"></div>
          <!-- The Modal -->
          <div class="modal fade" id="myModal">
              <div class="modal-dialog modal-sm">
                  <div class="modal-content modal-content-role">
	                  <!-- Modal Header -->
	                  <form action='<c:url value= "/admin/category/list"/>' id = "form-role" method="POST">
		                  <div class="modal-header">
		                      <h4 class="modal-title">Insert Category</h4>
		                      <button type="button" class="close" data-dismiss="modal">&times;</button>
		                  </div>
		
		                  <!-- Modal body -->
		                  <div class="modal-body modal-body-role">
		                      <div class ="form-group">
		                          <label for="usr" class="form-label">Root Category</label>
		                          <select class = "form-control form-input-role" id ="root-category">
		                          	<option value="">Choose a category</option>
		                          	<c:forEach items="${category}" var="item" varStatus="count">
		                          		<option value="${item.categoryCode}">${item.categoryName}</option>
									</c:forEach>
		                          </select>
		                      </div>
		                      <div class ="form-group">
		                          <label for="usr" class="form-label">Category</label>
		                          <input type="text" class="form-control form-input-role" name = "category_name" id = "category-name" placeholder = "Ex: Thể thao, du lịch,...">
		                          <input type="hidden" id = "category-code">
		                      </div>
		                  </div>
		
		                  <!-- Modal footer -->
		                  <div class="modal-footer modal-footer-role">
		                      <button type="button" class="btn btn-danger" id = "btn-close-category" data-dismiss="modal">Close</button>
		                      <button type="submit" class="btn btn-success" id ="btn-create-category"><i class="fa fa-circle-o-notch fa-spin display-none"></i><span>Create</span></button>
		                  </div>
	                  </form>
                  </div>
              </div>
          </div>
          <div class="card">
              <div class="card-body">
                  <div class="data-tables">
                      <table id="role-table" class="hover role-table">
                          <thead class="text-capitalize">
                              <tr>
                                  <th class="table-th table-th-no">No.</th>
                                  <th class="table-th category-table-th-date">Created date</th>
                                  <th class="table-th category-table-th">Root Category</th>
                                  <th class="table-th category-table-th">Category</th>
                                  <th class="table-th category-table-th-date">Updated date</th>
                                  <th class="table-th table-th-action">Action</th>
                              </tr>
                          </thead>
                      </table>
                      <ul class = "pagination-sm" id="pagination-role"></ul>
                  </div>
              </div>
          </div>
      </div>
  </div>
  <script src = "<c:url value='../../template1/admin/js/pagination.js'/>"></script>
  <script type="text/javascript">
  	$(document).ready(function(){
	    var table = $('#role-table').DataTable( {
	        "lengthChange": false,
	        "pageLength": 20,
	        "autoWidth" : false,
	        "info": false,
	        "paging": false,
	        "scrollY": '60vh',
	        "scrollCollapse": true
	    });
	    
	    $.ajax({
	        type: 'GET',
	        url: '${findAll}'+'?page_num=1&page_size=20',
	        dataType: 'json',
	        success: function (data) {
				var info = data.objectInfo;
				if (info.listRoleInfo.length !== 0) {
		        	drawTable(table, info.listRoleInfo, 0);
				}
	        	$('#pagination-role').twbsPagination({
	    	        totalPages: data.objectInfo.pagination.total_page,
	    	        visiblePages: 5,
	    	        onPageClick: function (event, page) {
	    	        	table.clear().draw();
	    	        	var noIndex = (page-1) * 20;
	    	        	$.ajax({
	    	    	        type: 'GET',
	    	    	        url: '${findAll}'+'?page_num='+page+'&page_size=20',
	    	    	        dataType: 'json',
	    	    	        success: function (data1) {
	    	    	        	var info1 = data1.objectInfo;	        	
	    	    	        	drawTable(table, info1.listRoleInfo, noIndex);
	    	    	        }
	    	        	});
	    	    	}
	        	});
	        }
	    });
	    $('body').on("click", ".editCategory", function(){
	    	$('.modal-body #root-category option').show();
		    var categoryCode = $(this).siblings('#categoryCode').val();
		    var categoryName = $(this).parents('tr').find('span#category-name').text();
			var rootCategoryCode = $(this).parents('tr').find('#rootCategoryCode').val();
			$('.modal-body #root-category').val(rootCategoryCode);
 		  	$('.modal-body #category-name').val(categoryName);
 		  	$('.modal-body #category-code').val(categoryCode);
 		  	$('.modal-body #root-category option[value ='+categoryCode+']').hide();
 		  	$('.modal-header .modal-title').text("Edit for " + categoryName);
		});
	    $('.btn-add-category').click(function () {
	    	$('.modal-body #root-category option').show();
	    	$('.modal-body #root-category').val("");
 		  	$('.modal-body #category-name').val("");
 		  	$('.modal-body #category-code').val("");
 		  	$('.modal-header .modal-title').text("Insert category");
		})
		$('body > div.notifyjs-corner > div > div.notifyjs-container > div > span').css({"word-break":"break-all !important","white-space":"break-spaces !important"});
		$('body').on("click", ".fa-remove", function(){
			var categoryCode = $(this).siblings('#categoryCode').val();
			var categoryName = $(this).parents('tr').find('span#category-name').text();
			showAlertBeforeDelete(function () {
	            event.preventDefault();
	            $.ajax({
	    	        type: 'DELETE',
	    	        url: '${deleteCategory}'+categoryCode,
	    	        success: function (data) {
	    	        	console.log(data);
	    	        	if (data.result == "200") {
	    	        		$('.card').css("display", "none");
	    	        		$('#loader').removeClass("display-none");
	    	        		$('#loader').addClass("display-block");
	    	        		setTimeout(function(){
		    	        		$.notify(data.message, "success");
		    	        	}, 200);
		    	        	setTimeout(function(){
		    	        		window.location.href = "${pageCategory}";
		    	        	}, 2000);
	    	        	} else {
	    	        		setTimeout(function(){
		    	        		$.notify(data.message,{
		    	        			autoHideDelay: 10000,
		    	        			clickToHide: false,
		    	        			},"error");
		    	        	}, 200);
	    	        	}
	    	        }
	    	    });
	        }, categoryName);
		});
	    $('.dataTables_filter input').prop("placeholder", "Search...");

	    var form = $("#form-role").validate({
			rules: {
				category_name: {
					required: true,
					maxlength: 255
				}
			},
			submitHandler: function(form) {
				openCreate('#btn-create-category');
				openCreate('#btn-close-category');
				$('#btn-create-category').find('span').text(" Loading");
				$('#btn-create-category').find('i').removeClass('display-none');
				$('#btn-create-category').find('i').addClass('display-block');
				
				var categoryCode = getVal('.modal-body #category-code');
				var categoryName = getVal('.modal-body #category-name');
				var rootCategory = getVal('.modal-body #root-category');
	            $.ajax({
	    	        type: 'POST',
	    	        url: '${categories}',
	    	        dataType: 'json',
	    	        contentType:'application/json',
	    	        data: JSON.stringify({
	    	        	code: categoryCode,
	    	        	category_name: categoryName,
	    	        	root_category: rootCategory
	    	        }),
	    	        success: function (data) {
	    	        	console.log(data);
	    	        	if (data.result == "200") {
	    	        		$('.modal-header').find('.close').prop("hidden", true);
	    	        		setTimeout(function(){
		    	        		$.notify(data.message, "success");
		    	        	}, 200);
		    	        	setTimeout(function(){
			    	        	$('#btn-create-category').find('i').removeClass('display-block');
			    	        	$('#btn-create-category').find('i').addClass('display-none');
			    	        	$('#btn-create-category').find('span').text("Create");
		    	        	}, 3000);
		    	        	setTimeout(function(){
		    	        		window.location.href = "${pageRole}";
		    	        	}, 4000);
	    	        	} else {
	    	        		setTimeout(function(){
		    	        		$.notify(data.message, "error");
		    	        	}, 200);
		    	        	setTimeout(function(){
		    	        		closeCreate('#btn-create-category');
		    	        		closeCreate('#btn-close-category');
		    	        		$('#btn-create-category').find('i').removeClass('display-block');
		    	        		$('#btn-create-category').find('i').addClass('display-none');
		    	        		$('#btn-create-category').find('span').text("Create");
		    	        	}, 2000);
	    	        	}
	    	        }
	    	    });
			}
		});
	});
  	
  	function drawTable(table, listData, noIndex) {
  	  $.each(listData, function (index, item) {
  	    let rowData = [
  	      noIndex + index + 1,
  	      item.createdDate,
  	      '<span id = "root-category">'+item.rootCategoryName+'</span>',
  	      '<span id = "category-name">'+item.categoryName+'</span>',
  	      item.updatedDate,
          '<a class="fa fa-edit editCategory" data-toggle="modal" data-target="#myModal" data-keyboard="false" data-backdrop="static" title = "Edit"></a><a class="fa fa-remove" href="#" title = "Delete"></a><input type="hidden" id ="rootCategoryCode" value="'+item.rootCategoryCode+'"/><input type="hidden" id ="categoryCode" value="'+item.categoryCode+'"/>'
  	    ];
  	    table.row.add(rowData).draw(false);
  	  });
  	}
  </script>
</body>

</html>