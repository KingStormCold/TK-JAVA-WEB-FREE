<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<c:url var="roles" value="/admin/role" />
<c:url var="findAll" value="/admin/role/find-all" />
<c:url var="pageRole" value="/admin/role/list" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>

<div class="col-sm-12 main-content">
      <div>
          <div class="add-role col-sm-2">
              <a title="Add Role" class="btn btn-primary btn-add-role" data-toggle="modal" data-target="#myModal" data-keyboard="false" data-backdrop="static">+</a>
          </div>
          <div id="loader" class = "display-none"></div>
          <!-- The Modal -->
          <div class="modal fade" id="myModal">
              <div class="modal-dialog modal-sm">
                  <div class="modal-content modal-content-role">
	                  <!-- Modal Header -->
	                  <form action='<c:url value= "/admin/role/list"/>' id = "form-role" method="POST">
		                  <div class="modal-header">
		                      <h4 class="modal-title">Insert Role</h4>
		                      <button type="button" class="close" data-dismiss="modal">&times;</button>
		                  </div>
		
		                  <!-- Modal body -->
		                  <div class="modal-body modal-body-role">
		                      <div class ="form-group">
		                          <label for="usr" class="form-label">Name</label>
		                          <input type="text" class="form-control form-input-role" name="role_id" id = "role-id" placeholder = "Ex: ROLE">
		                      </div>
		                      <div class ="form-group">
		                          <label for="usr" class="form-label">Description</label>
		                          <input type="text" class="form-control form-input-role" name = "role_name" id = "role-name" placeholder = "Ex: This role is for ....">
		                          <input type="hidden" id = "is-role-id">
		                      </div>
		                  </div>
		
		                  <!-- Modal footer -->
		                  <div class="modal-footer modal-footer-role">
		                      <button type="button" class="btn btn-danger" id = "btn-close-role" data-dismiss="modal">Close</button>
		                      <button type="submit" class="btn btn-success" id ="btn-create-role"><i class="fa fa-circle-o-notch fa-spin display-none"></i><span>Create</span></button>
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
                                  <th class="table-th role-table-th-name">Name</th>
                                  <th class="table-th role-table-th-description">Description</th>
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
	        	drawTable(table, info.listRoleInfo, 0);
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
	    $('body').on("click", ".editNew", function(){
		    var roleId = $(this).siblings('#roleId').val();
			var roleName = $(this).parents('tr').find('#roleName').val();
 		    $('.modal-body #role-id').val(roleId);
 		  	$('.modal-body #role-name').val(roleName);
 		  	$('.modal-body #is-role-id').val(roleId);
 		  	$('.modal-body #role-id').prop("readonly",true);
 		  	$('.modal-header .modal-title').text("Edit for " + roleId);
		});
	    $('.btn-add-role').click(function () {
	    	$('.modal-body #role-id').val("");
	    	$('.modal-body #role-id').prop("readonly",false);
 		  	$('.modal-body #role-name').val("");
 		  	$('.modal-body #is-role-id').val("");
 		  	$('.modal-header .modal-title').text("Insert user");
		})
		$('body').on("click", ".fa-remove", function(){
			var roleId = $(this).siblings('#roleId').val();
			var roleName = $(this).parents('tr').find('#roleName').val();
			showAlertBeforeDelete(function () {
	            event.preventDefault();
	            $.ajax({
	    	        type: 'POST',
	    	        url: '${roles}',
	    	        dataType: 'json',
	    	        contentType:'application/json',
	    	        data: JSON.stringify({
	    	        	role_id: roleId,
	    	        	role_name: roleName,
	    	        	condition: "delete"
	    	        }),
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
		    	        		window.location.href = "${pageRole}";
		    	        	}, 2000);
	    	        	} else {
	    	        		setTimeout(function(){
		    	        		$.notify(data.message, "error");
		    	        	}, 200);
	    	        	}
	    	        }
	    	    });
	        }, roleId);
		});
	    $('.dataTables_filter input').prop("placeholder", "Search...");

	    var form = $("#form-role").validate({
			rules: {
				role_id: {
					required: true,
					maxlength: 50
				},
				role_name: {
					required: true,
					maxlength: 255
				}
			},
			submitHandler: function(form) {
				openCreate('#btn-create-role');
				openCreate('#btn-close-role');
				$('#btn-create-role').find('span').text(" Loading");
				$('#btn-create-role').find('i').removeClass('display-none');
				$('#btn-create-role').find('i').addClass('display-block');
				
				var roleId = getVal('.modal-body #role-id');
				var roleName = getVal('.modal-body #role-name');
				var isRoleId = getVal('.modal-body #is-role-id');
				var condition = "update";
				if (isRoleId == null || isRoleId == "" || isRoleId == "undefined") {
					condition = "insert";
				}
	            $.ajax({
	    	        type: 'POST',
	    	        url: '${roles}',
	    	        dataType: 'json',
	    	        contentType:'application/json',
	    	        data: JSON.stringify({
	    	        	role_id: roleId,
	    	        	role_name: roleName,
	    	        	condition: condition
	    	        }),
	    	        success: function (data) {
	    	        	console.log(data);
	    	        	if (data.result == "200") {
	    	        		$('.modal-header').find('.close').prop("hidden", true);
	    	        		setTimeout(function(){
		    	        		$.notify(data.message, "success");
		    	        	}, 200);
		    	        	setTimeout(function(){
			    	        	$('#btn-create-role').find('i').removeClass('display-block');
			    	        	$('#btn-create-role').find('i').addClass('display-none');
			    	        	$('#btn-create-role').find('span').text("Create");
		    	        	}, 3000);
		    	        	setTimeout(function(){
		    	        		window.location.href = "${pageRole}";
		    	        	}, 4000);
	    	        	} else {
	    	        		setTimeout(function(){
		    	        		$.notify(data.message, "error");
		    	        	}, 200);
		    	        	setTimeout(function(){
		    	        		closeCreate('#btn-create-role');
		    	        		closeCreate('#btn-close-role');
		    	        		$('#btn-create-role').find('i').removeClass('display-block');
		    	        		$('#btn-create-role').find('i').addClass('display-none');
		    	        		$('#btn-create-role').find('span').text("Create");
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
  	      item.roleId,
  	      item.description,
          '<a class="fa fa-edit editNew" data-toggle="modal" data-target="#myModal" data-keyboard="false" data-backdrop="static" title = "Edit"></a><a class="fa fa-remove" href="#" title = "Delete"></a><input type="hidden" id ="roleId" value="'+item.roleId+'"/><input type="hidden" id ="roleName" value="'+item.description+'"/>'
  	    ];
  	    table.row.add(rowData).draw(false);
  	  });
  	}
  </script>
</body>

</html>