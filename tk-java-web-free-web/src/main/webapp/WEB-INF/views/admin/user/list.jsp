<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<c:url var="findAll" value="/admin/user/find-all" />
<c:url var="findAllRole" value="/admin/role/find-all" />
<c:url var="findOne" value="/admin/user/find-one" />
<c:url var="login" value="/login" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	
<div class="col-sm-10 main-content">
   <div>
       <div class="add-user">
           <a title="Add user" class="btn btn-primary btn-add-user" data-toggle="modal" data-target="#myModal" data-keyboard="false" data-backdrop="static">+</a>
       </div>
       <!-- The Modal -->
       <div class="modal" id="myModal">
           <div class="modal-dialog">
               <div class="modal-content modal-content-user">
                   <form action="/user-role/user" method="GET" enctype="multipart/form-data">
                       <!-- Modal Header -->
                       <div class="modal-header">
                           <h4 class="modal-title">Insert User</h4>
                           <button type="button" class="close" data-dismiss="modal">&times;</button>
                       </div>

                       <!-- Modal body -->
                       <div class="modal-body modal-user">
                           
                               <div class ="form-group">
                                   <label for="usr" class="form-label">User name</label>
                                   <input type="text" class="form-control form-input" name="user_name" id="user_name" placeholder="Ex: tuankul">
                               </div>
                               <div class ="form-group">
                                   <label for="usr" class="form-label">Password</label>
                                   <input type="text" class="form-control form-input" name = "password" id="password" placeholder="Ex: 123456">
                               </div>
                               <div class ="form-group">
                                   <label for="usr" class="form-label">Full name</label>
                                   <input type="text" class="form-control form-input" name="full_name" id ="full_name" placeholder="Ex: Thái Thanh Tuấn">
                               </div>
                               <div class ="form-group">
                                   <label for="usr" class="form-label">Email</label>
                                   <input type="email" class="form-control form-input" name="email" id ="email" placeholder="Ex: test@gmail.com">
                               </div>
                               <div class ="form-group">
                                   <label for="usr" class="form-label">Phone</label>
                                   <input type="number" class="form-control form-input" name = "phone" id="phone" placeholder="012332323232">
                               </div>
                               <div class ="form-group">
                                   <label for="usr" class="form-label">Address</label>
                                   <input type="text" class="form-control form-input" name = "address" id="address" placeholder="349 Nguyễn Đình Chiểu Quận 1">
                               </div>
                               <div class ="form-group">
                                   <label for="usr" class="form-label">Avatar</label>
                                   <input type="file" style="padding-top: 3px;" class="form-control form-input" id = "form-avatar">
                               </div>
                               <div class ="">
                                   <label class ="form-label-hidden"></label>
                                   <img src="" alt="avatar" class = "form-avatar-profile" id = "avater-review">
                               </div>
                               <div class ="form-group">
                                   <label for="usr" class="form-label">Role</label>
                                   <div style=" display: -webkit-inline-box;" class = "col-sm-4 have-role">
                                       <div class = "role-header">
                                           <p class = "span-role">You have right</p>
                                       </div>
                                       <div class = "role-have-right">
                                       </div>
                                   </div>
                                   <div style=" display: -webkit-inline-box;" class = "col-sm-4 not-role"> 
                                       <div class = "role-header">
                                           <p class = "span-role">You have not right</p>
                                       </div>
                                       <div class = "list-role">
                                       </div>
                                   </div>
                               </div>
                       </div>

                       <!-- Modal footer -->
                        <div class="modal-footer modal-footer-user">
                            <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
                            <button type="submit" class="btn btn-success" >Create</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <div class="card">
            <div class="card-body">
                <div class="data-tables">
                    <table id="user-table" class="hover">
                        <thead class="text-capitalize">
                            <tr>
                                <th class="table-th table-th-no">No.</th>
                                <th class="table-th">Created By</th>
                                <th class="table-th">Created Date</th>
                                <th class="table-th">User Name</th>
                                <th class="table-th">Update Date</th>
                                <th class="table-th">Update By</th>
                                <th class="table-th table-th-action">Action</th>
                            </tr>
                        </thead>
                    </table>
                    <ul class = "pagination-sm" id="pagination-user"></ul>
                </div>
            </div>
        </div>
    </div>
</div>
  <script src = "<c:url value='../../template1/admin/js/pagination.js'/>"></script>
  <script src = "<c:url value='../../template1/admin/js/js-admin-user.js'/>"></script>
  <script type="text/javascript">
  	var list = {};
  	$(document).ready(function (){
  		 var table = $('#user-table').DataTable({
  			"lengthChange": false,
	        "pageLength": 21,
	        "autoWidth" : false,
	        "info": false,
	        "paging": false,
	        "scrollY": '60vh',
	        "scrollCollapse": true
 	    });

  		$.ajax({
			type: 'GET',
	        url: '${findAllRole}'+'?page_num=1&page_size=10000',
	        dataType: 'json',
	        success: function (data) {
				console.log(data);
				list = data.objectInfo.listRoleInfo;
				
	        }
  		}).fail(function() {
  			window.location.href = "${login}";
		});
		
		$.ajax({
	        type: 'GET',
	        url: '${findAll}?page_num=1&page_size=20',
	        dataType: 'json',
	        success: function (data) {
				var info = data.objectInfo;
	        	drawTable(table, info.userInfos, 0);
	        	$('#pagination-user').twbsPagination({
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
	    	    	        	drawTable(table, info1.userInfos, noIndex);
	    	    	        }
	    	        	});
	    	    	}
	        	});
	        }
	    }).fail(function() {
  			window.location.href = "${login}";
		});;
	});
  	 
  	$(".btn-add-user").click(function(){
  		$('.modal-user .role-have-right').find('label').remove();
		$('.modal-user .list-role').find('label').remove();
		$('.modal-user .list-role').find('br').remove();
		$.each(list, function (index, item) {
			$('.modal-user .list-role').append('<label for = "'+item.roleId+'" title = "'+item.description+'"><input type="checkbox" id = "'+item.roleId+'" value="'+item.roleId+'" class = "checkbox-role">'+item.roleId+' </label><br/>');
		});
		$('.modal-user #user_name').val("");
  		$('.modal-user #user_name').prop("readonly", false);
  		$('.modal-user #password').val("");
  		$('.modal-user #password').prop("readonly", false);
  		$('.modal-user #full_name').val("");
  		$('.modal-user #email').val("");
  		$('.modal-user #phone').val("");
  		$('.modal-user #address').val("");
	});

  	$('body').on("click", ".editNew", function(){
  		$('.modal-user .role-have-right').find('label').remove();
		$('.modal-user .role-have-right').find('br').remove();
		$('.modal-user .list-role').find('label').remove();
		$('.modal-user .list-role').find('br').remove();
		var userName = $(this).parents('tr').find('#userName').val();
  		$.ajax({
	        type: 'GET',
	        url: '${findOne}'+'?user_name='+userName,
	        dataType: 'json',
	        success: function (data) {
				var info = data.objectInfo;
			  	var haveRoles = info.haveRoles;
			  	var listRoles = info.listRole;
				$.each(listRoles, function (index, item) {
					$('.modal-user .list-role').append('<label for = "'+item.roleId+'" title = "'+item.description+'"><input type="checkbox" id = "'+item.roleId+'" value="'+item.roleId+'" class = "checkbox-role">'+item.roleId+' </label><br/>');
				});	
		  		$.each(haveRoles, function (index, item) {
		  			$('.modal-user .role-have-right').append('<label for = "'+item.roleId+'" title = "'+item.description+'" class = "remove-role"><input type="checkbox" id = "'+item.roleId+'" value="'+item.roleId+'" class = "checkbox-role" checked>'+item.roleId+'</label><br/>');
				});
		  		$('.modal-user #user_name').val(info.userName);
		  		$('.modal-user #user_name').prop("readonly", true);
		  		$('.modal-user #password').val("*********");
		  		$('.modal-user #password').prop("readonly", true);
		  		$('.modal-user #full_name').val(info.fullName);
		  		$('.modal-user #email').val(info.email);
		  		$('.modal-user #phone').val(info.phone);
		  		$('.modal-user #address').val(info.address);
	        }
	    }).fail(function() {
  			window.location.href = "${login}";
		});;
	});
	
	$('body').on("click", ".remove-role", function(){
		if($(this).find('input[type=checkbox]').is(':checked')) {
			$(this).css("text-decoration", "none");
			$(this).css("color", "black");
		} else {
			$(this).css("text-decoration", "line-through");
			$(this).css("color", "red");
		}
	});
	
	function drawTable(table, listData, noIndex) {
	  	  $.each(listData, function (index, item) {
	  	    let rowData = [
	  	      noIndex + index + 1,
	  	      item.createdBy,
	  	      item.createdDate,
	  	      item.userName,
	  	      item.updatedBy,
		  	  item.updatedDate,
	          '<a class="fa fa-edit editNew" data-toggle="modal" data-target="#myModal"></a><a class="fa fa-remove" href="#"></a><input type="hidden" id ="userName" value="'+item.userName+'"/>'
	  	    ];
	  	    table.row.add(rowData).draw(false);
	  	  });
	  	}
  </script>
</body>
</html>