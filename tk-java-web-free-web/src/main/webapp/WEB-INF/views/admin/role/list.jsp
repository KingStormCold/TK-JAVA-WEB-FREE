<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<c:url var="roles" value="/admin/role" />
<c:url var="findAll" value="/admin/role/find-all" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>

<div class="col-sm-10 main-content">
      <div>
          <div class="add-role">
              <a title="Add Role" class="btn btn-primary btn-add-role" data-toggle="modal" data-target="#myModal" data-keyboard="false" data-backdrop="static">+</a>
          </div>
          <!-- The Modal -->
          <div class="modal" id="myModal">
              <div class="modal-dialog">
                  <div class="modal-content modal-content-role">
	                  <!-- Modal Header -->
	                  <form action="/role/list" id = "form-role" method="POST">
		                  <div class="modal-header">
		                      <h4 class="modal-title">Insert Role</h4>
		                      <button type="button" class="close" data-dismiss="modal">&times;</button>
		                  </div>
		
		                  <!-- Modal body -->
		                  <div class="modal-body modal-body-role">
		                      <div class ="form-group">
		                          <label for="usr" class="form-label">Name</label>
		                          <input type="text" class="form-control form-input-role" name="role_id" id = "role-id">
		                      </div>
		                      <div class ="form-group">
		                          <label for="usr" class="form-label">Description</label>
		                          <input type="text" class="form-control form-input-role" name = "role_name" id = "role-name">
		                      </div>
		                  </div>
		
		                  <!-- Modal footer -->
		                  <div class="modal-footer modal-footer-role">
		                      <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
		                      <button type="submit" class="btn btn-success" id ="btn-create-role">Create</button>
		                  </div>
	                  </form>
                  </div>
              </div>
          </div>
          <div class="card">
              <div class="card-body">
                  <div class="data-tables">
                      <table id="example" class="hover role-table">
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
	    var table = $('#example').DataTable( {
	        "lengthChange": false,
	        "pageLength": 20,
	        "autoWidth" : false,
	        "info": false,
	        "paging": false,
	        scrollY: '60vh',
	        scrollCollapse: true,
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
			var newId = $(this).siblings('#newId').val();
			localStorage.setItem("newId", newId);
			$('.editNew').attr("href", "<c:url value='/admin/news'/>")
		});
	    $('.dataTables_filter input').prop("placeholder", "Search");

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
				$('#btn-create-role').text("Creating...");
				var roleId = getVal('#role-id');
				var roleName = getVal('#role-name');
				var dataArray = {};
	            dataArray["role_id"] = roleId;
	            dataArray["role_name"] = roleName;
	            dataArray["condition"] = 'insert';
	            $.ajax({
	    	        type: 'POST',
	    	        url: '${roles}',
	    	        dataType: 'json',
	    	        contentType:'application/json',
	    	        data: JSON.stringify({
	    	        	role_id: roleId,
	    	        	role_name: roleName,
	    	        	condition: 'insert'
	    	        }),
	    	        success: function (data) {
	    	        	console.log(data);
	    	        	if (data.result == "200") {
	    	        		setTimeout(function(){
		    	        		$.notify(data.message, "success");
		    	        	}, 200);
		    	        	setTimeout(function(){
		    	        		closeCreate('#btn-create-role');
		    	        		$('#btn-create-role').text("Create")
		    	        	}, 4000);
	    	        	} else {
	    	        		setTimeout(function(){
		    	        		$.notify(data.message, "error");
		    	        	}, 200);
		    	        	setTimeout(function(){
		    	        		closeCreate('#btn-create-role');
		    	        		$('#btn-create-role').text("Create")
		    	        	}, 4000);
	    	        	}
	    	        }
	    	    });
			}
		});
	});
  	
  	function openCreate(selector) {
  	  $(selector)
  	    .addClass("disabled-button")
  	    .prop("disabled", true);
  	}
  	function closeCreate(selector) {
  	  $(selector)
  	    .removeClass("disabled-button")
  	  	.prop("disabled", false);
  	}
  	
  	function getVal(selector) {
	  return $(selector).val();
	}
  	
  	function drawTable(table, listData, noIndex) {
  	  $.each(listData, function (index, item) {
  	    let rowData = [
  	      noIndex + index + 1,
  	      item.roleId,
  	      item.description,
          '<a class="fa fa-edit editNew" href="#"></a><a class="fa fa-remove" href="https://www.google.com/"></a><input type="hidden" id ="roleId" value="'+item.roleId+'"/>'
  	    ];
  	    table.row.add(rowData).draw(false);
  	  });
  	}
  </script>
</body>

</html>