<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<c:url var="findAll" value="/api/admin/user/find-all" />
<c:url var="findAllRole" value="/api/admin/role/find-all" />
<c:url var="findOne" value="/api/admin/user/find-one" />
<c:url var="login" value="/login" />
<c:url var="users" value="/api/admin/user" />
<c:url var="pageUser" value="/admin/user/list" />


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	
<div class="col-sm-12 main-content">
   <div>
       <div class="add-user col-sm-2">
           <a title="Add user" class="btn btn-primary btn-add-user" data-toggle="modal" data-target="#myModal" data-keyboard="false" data-backdrop="static">+</a>
       </div>
       <div id="loader" class = "display-none"></div>
       <!-- The Modal -->
       <div class="modal" id="myModal">
           <div class="modal-dialog">
               <div class="modal-content modal-content-user">
                   <form action='<c:url value= ""/>' method="POST" enctype="multipart/form-data" id = "form-user">
                       <!-- Modal Header -->
                       <div class="modal-header">
                           <h4 class="modal-title"></h4>
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
	                           <input type="file" style="padding-top: 3px;" class="form-control form-input" id = "form-avatar" name = "form_avatar">
	                           <label id="form-avatar-error" class="error" style="display: none;" for="form-avatar">Please enter form avatar.</label>
	                       </div>
	                       <div class ="">
	                           <label class ="form-label-hidden"></label>
	                           <img src="" alt="avatar" class = "form-avatar-profile" id = "avater-review" >
	                       </div>
	                       <div class ="form-group">
	                           	<label for="usr" class="form-label col-sm-4">Role</label>
	                           	<div style=" display: -webkit-inline-box; width: 39.9%;" class = "col-sm-4 have-role mr-left-zero-dot-four">
	                               <div class = "role-header">
	                                   <p class = "span-role">You have right</p>
	                               </div>
	                               <div class = "role-have-right">
	                               </div>
	                           	</div>
	                           	<label class="form-role-error" style="display: none;"></label>
	                           	<div style=" display: -webkit-inline-box; width: 39.9%;" class = "col-sm-4 not-role mr-left-zero-dot-four"> 
	                               <div class = "role-header">
	                                   <p class = "span-role">You have not right</p>
	                               </div>
	                               <div class = "list-role">
	                               </div>
	                           	</div>
	                       </div>
                           <input type="hidden" id = "is-user-id">
                       </div>

                       <!-- Modal footer -->
                        <div class="modal-footer modal-footer-user">
                            <button type="button" class="btn btn-danger" data-dismiss="modal" id ="btn-close-user">Close</button>
                            <button type="submit" class="btn btn-success" id ="btn-create-user"><i class="fa fa-circle-o-notch fa-spin display-none"></i><span>Create</span></button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <div class="card">
            <div class="card-body">
                <div class="data-tables">
                    <table id="user-table" class="hover user-table">
                        <thead class="text-capitalize">
                            <tr>
                                <th class="table-th table-th-no">No.</th>
                                <th class="table-th w-15-percent">Created By</th>
                                <th class="table-th w-15-percent">Created Date</th>
                                <th class="table-th w-15-percent">User Name</th>
                                <th class="table-th w-15-percent">Update Date</th>
                                <th class="table-th w-15-percent">Update By</th>
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
	        "pageLength": 10,
	        "info": false,
	        "autoWidth" : false,
	        "paging": false,
	        "scrollY": '60vh',
	        "scrollCollapse": true
 	    });
  		 
  	    $('.dataTables_filter input').prop("placeholder", "Search");

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
	        url: '${findAll}?page_num=1&page_size=10',
	        dataType: 'json',
	        success: function (data) {
				var info = data.objectInfo;
				if (info.userInfos.length !== 0) {
					drawTable(table, info.userInfos, 0);
				}
	        	
	        	$('#pagination-user').twbsPagination({
	    	        totalPages: data.objectInfo.pagination.total_page,
	    	        visiblePages: 5,
	    	        onPageClick: function (event, page) {
	    	        	table.clear().draw();
	    	        	var noIndex = (page-1) * 10;
	    	        	$.ajax({
	    	    	        type: 'GET',
	    	    	        url: '${findAll}'+'?page_num='+page+'&page_size=10',
	    	    	        dataType: 'json',
	    	    	        success: function (data1) {
	    	    	        	var info1 = data1.objectInfo;
	    	    	        	if (info1.userInfos.length !== 0) {
	    	    	        		drawTable(table, info1.userInfos, noIndex);
	    	    	        	}
	    	    	        }
	    	        	});
	    	    	}
	        	});
	        }
	    }).fail(function() {
  			window.location.href = "${login}";
		});;
		
		var form = $("#form-user").validate({
			rules: {
				user_name: {
					required: true,
					maxlength: 50
				},
				password: {
					required: true,
					maxlength: 30
				},
				full_name: {
					required: true,
					maxlength: 255
				},
				email: {
					required: true,
					maxlength: 255,
					email: true
				},
				phone: {
					required: true,
					maxlength: 11,
					minlength: 10,
					number: true
				},
				address: {
					required: true,
					maxlength: 1000
				},
				role_form: {
					required: true,
					maxlength: 1000
				}
			},
			messages: {
				user_name: {
					required: "Please enter user name.",
					maxlength: "Max length of user name must be 50."
				},
				password: {
					required: "Please enter password.",
					maxlength: "Max length of password must be 30."
				},
				full_name: {
					required: "Please enter full name.",
					maxlength: "Max length of full name must be 255."
				},
				email: {
					required: "Please enter email.",
					maxlength: "Max length of email must be 255.",
					email: "Email invalid."
				},
				phone: {
					required: "Please enter phone.",
					maxlength: "Max length of phone must be 11.",
					minlength: "Min length of phone must be 10.",
					number: "Please type number only."
				},
				address: {
					required: "Please enter address.",
					maxlength: "Max length of adrress must be 1000.",
				},
				form_avatar: {
					required: "Please enter form avatar.",
					maxlength: "Max length of form avatar must be 1000.",
				}
			}, 
			submitHandler: function(form) {
				event.preventDefault();
				openCreate('#btn-create-user');
				openCreate('#btn-close-user');
				$('#btn-create-user').find('span').text(" Loading");
				$('#btn-create-user').find('i').removeClass('display-none');
				$('#btn-create-user').find('i').addClass('display-block');
				var userNameHidden = getVal('.modal-body #is-user-id');
				var userName = getVal('.modal-user #user_name');
				var password = getVal('.modal-user #password');
				var fullName = getVal('.modal-user #full_name');
				var email = getVal('.modal-user #email');
				var phone = getVal('.modal-user #phone');
				var address = getVal('.modal-user #address');
				var addRoles = new Array();
		        $(".not-role").each(function() {
		            if ($(this).prop('checked') == true){ 
		            	addRoles.push($(this).val());
		            }
		        });
		        		        
		        var removeRoles = new Array();
		        $(".have-roles").each(function(){
		            if ($(this).prop('checked') == false){ 
		            	removeRoles.push($(this).val());
		            }
		        });
		        var dataArray = {};
		        dataArray["user_name"] = userName;
		        dataArray["full_name"] = fullName;
		        dataArray["email"] = email;
		        dataArray["phone"] = phone;
		        dataArray["address"] = address;
		        dataArray["add_role"] = addRoles;
		        dataArray["remove_role"] = removeRoles;
		        var files = $('.modal-user #form-avatar')[0].files[0];
		        var reader = new FileReader();
				if (userNameHidden == null || userNameHidden == "" || userNameHidden == "undefined") {
					if (files == undefined) {
				  		addValueWhenValidationForm('.modal-user #form-avatar-error', 'inline-block', 'Please enter form avatar.');
						$('#btn-create-user').text("Create");
						closeCreate('#btn-create-user');
						return false;
					} else if ($.isEmptyObject(addRoles)) {
						addValueWhenValidationForm('.form-role-error', 'inline-block', 'Please choose role.');
						$('#btn-create-user').text("Create");
						closeCreate('#btn-create-user');
						return false;
					} 
		            reader.onload = function (e) {
		            	dataArray["condition"] = "insert";
		                dataArray["file"] = e.target.result;
		                dataArray["image"] = files.name;
		                dataArray["password"] = password;
		                callAPI(dataArray);
		            }
		            reader.readAsDataURL(files);
				} else {
					dataArray["password"] = "";
					dataArray["condition"] = "update";
					if (files == undefined) {
						dataArray["file"] = "";
		                dataArray["image"] = "";
		                callAPI(dataArray);
					} else {
						reader.onload = function (e) {
							dataArray["file"] = e.target.result;
			                dataArray["image"] = files.name;
			                callAPI(dataArray);
			            }
			            reader.readAsDataURL(files);
					}
				}
			}
		});
		
		function callAPI(dataArray) {
			$.ajax({
    	        type: 'POST',
    	        url: '${users}',
                dataType: 'json',
                contentType:'application/json',
    	        data: JSON.stringify(dataArray),
    	        success: function (data) {
    	        	if (data.result == "200") {
    	        		$('.modal-header').find('.close').prop("hidden", true);
    	        		setTimeout(function(){
	    	        		$.notify(data.message, "success");
	    	        	}, 200);
	    	        	setTimeout(function(){
	    	        		$('#btn-create-user').find('i').removeClass('display-block');
		    	        	$('#btn-create-user').find('i').addClass('display-none');
		    	        	$('#btn-create-user').find('span').text("Create");
	    	        	}, 3000);
	    	        	setTimeout(function(){
	    	        		window.location.href = "${pageUser}";
	    	        	}, 4000);
    	        	} else {
    	        		setTimeout(function(){
	    	        		$.notify(data.message, "error");
	    	        	}, 200);
	    	        	setTimeout(function(){
	    	        		closeCreate('#btn-create-user');
	    	        		closeCreate('#btn-close-user');
	    	        		$('#btn-create-user').find('i').removeClass('display-block');
	    	        		$('#btn-create-user').find('i').addClass('display-none');
	    	        		$('#btn-create-user').find('span').text("Create");
	    	        	}, 2000);
    	        	}
    	        }
    	    });
		}
	});
  	 
  	$(".btn-add-user").click(function(){
  		$('.modal-user .role-have-right').find('label').remove();
		$('.modal-user .list-role').find('label').remove();
		$('.modal-user .list-role').find('br').remove();
		$('.modal-header .modal-title').text("Insert user");
		$.each(list, function (index, item) {
			$('.modal-user .list-role').append('<label for = "'+item.roleId+'" title = "'+item.description+'"><input type="checkbox" id = "'+item.roleId+'" value="'+item.roleId+'" class = "checkbox-role not-role"><p class = "role-text">'+item.roleId+'</p></label><br/>');
		});
		$('.modal-user #user_name').val("");
  		$('.modal-user #user_name').prop("readonly", false);
  		$('.modal-user #password').val("");
  		$('.modal-user #password').prop("readonly", false);
  		$('.modal-user #full_name').val("");
  		$('.modal-user #email').val("");
  		$('.modal-user #phone').val("");
  		$('.modal-user #address').val("");
  		$('.modal-user #form-avatar').val('');
  		$('.modal-user #avater-review').attr('src', "");
  		$('.modal-body #is-user-id').val("");
  		addValueWhenValidationForm('.modal-user #form-avatar-error', 'none', '');
  		removeFormUser();
	});

  	$('body').on("click", ".editNew", function(){
  		$('.modal-user .role-have-right').find('label').remove();
		$('.modal-user .role-have-right').find('br').remove();
		$('.modal-user .list-role').find('label').remove();
		$('.modal-user .list-role').find('br').remove();
		var userName = $(this).parents('tr').find('#userName').val();
		$('.modal-header .modal-title').text("Edit for " + userName);
  		removeFormUser();
  		$.ajax({
	        type: 'GET',
	        url: '${findOne}'+'?user_name='+userName,
	        dataType: 'json',
	        success: function (data) {
				var info = data.objectInfo;
			  	var haveRoles = info.haveRoles;
			  	var listRoles = info.listRole;
				$.each(listRoles, function (index, item) {
					$('.modal-user .list-role').append('<label for = "'+item.roleId+'" title = "'+item.description+'"><input type="checkbox" id = "'+item.roleId+'" value="'+item.roleId+'" class = "checkbox-role not-role"><p class = "role-text">'+item.roleId+'</p></label><br/>');
				});	
		  		$.each(haveRoles, function (index, item) {
		  			$('.modal-user .role-have-right').append('<label for = "'+item.roleId+'" title = "'+item.description+'" class = "remove-role"><input type="checkbox" id = "'+item.roleId+'" value="'+item.roleId+'" class = "checkbox-role have-roles" checked><p class = "role-text">'+item.roleId+'</p></label><br/>');
				});
		  		$('.modal-user #user_name').val(info.userName);
		  		$('.modal-user #user_name').prop("readonly", true);
		  		$('.modal-user #password').val("*********");
		  		$('.modal-user #password').prop("readonly", true);
		  		$('.modal-user #full_name').val(info.fullName);
		  		$('.modal-user #email').val(info.email);
		  		$('.modal-user #phone').val(info.phone);
		  		$('.modal-user #address').val(info.address);
		  		$('.modal-body #is-user-id').val(info.userName);
		  		$('.modal-body #avater-review').prop("src","../../" + info.image);
	        }
	    }).fail(function() {
  			window.location.href = "${login}";
		});;
	});

  	$('body').on("click", ".fa-unlock-alt", function(){
		var userName = $(this).parents('tr').find('#userName').val();
		showAlertBeforeDelete(function () {
            event.preventDefault();
            $.ajax({
    	        type: 'POST',
    	        url: '${users}',
    	        dataType: 'json',
    	        contentType:'application/json',
    	        data: JSON.stringify({
    	        	user_name: userName,
    	        	full_name: "delete",
    	        	email: "delete",
    	        	phone: "delete",
    	        	condition: "delete"
    	        }),
    	        success: function (data) {
    	        	if (data.result == "200") {
    	        		$('.card').css("display", "none");
    	        		$('#loader').removeClass("display-none");
    	        		$('#loader').addClass("display-block");
    	        		setTimeout(function(){
	    	        		$.notify(data.message, "success");
	    	        	}, 200);
	    	        	setTimeout(function(){
	    	        		window.location.href = "${pageUser}";
	    	        	}, 2000);
    	        	} else {
    	        		setTimeout(function(){
	    	        		$.notify(data.message, "error");
	    	        	}, 200);
    	        	}
    	        }
    	    });
        }, userName);
  	});

  	$('body').on("click", ".fa-lock", function(){
		var userName = $(this).parents('tr').find('#userName').val();
		showAlertBeforeUnlock(function () {
            event.preventDefault();
            $.ajax({
    	        type: 'POST',
    	        url: '${users}',
    	        dataType: 'json',
    	        contentType:'application/json',
    	        data: JSON.stringify({
    	        	user_name: userName,
    	        	full_name: "unlock",
    	        	email: "unlock",
    	        	phone: "unlock",
    	        	condition: "unlock"
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
	    	        		window.location.href = "${pageUser}";
	    	        	}, 2000);
    	        	} else {
    	        		setTimeout(function(){
	    	        		$.notify(data.message, "error");
	    	        	}, 200);
    	        	}
    	        }
    	    });
        }, userName);
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
	
	function addValueWhenValidationForm(selector, display, message){
		$(selector).css("display",display);
		$(selector).text(message);
	}
	
	function removeFormUser(){
		removeSelector('.modal-user #user_name-error');
  		removeSelector('.modal-user #password-error');
  		removeSelector('.modal-user #full_name-error');
  		removeSelector('.modal-user #email-error');
  		removeSelector('.modal-user #phone-error');
  		removeSelector('.modal-user #address-error');
//   	removeSelector('.modal-user #form-avatar-error');
  		removeSelector('.modal-user #address-error');
	}
	
	function removeSelector(selector){
		return $(selector).remove();
	}
	
	function drawTable(table, listData, noIndex) {
	  	  $.each(listData, function (index, item) {
		  	var edit = '';
		  	var textLineThrough = '';
		  	if (item.online === false) {
		  		edit = '<a class="fa fa-lock" href="#" title="Unlock"></a>'
			  	textLineThrough = 'text-line-through';
			} else {
				edit = '<a class="fa fa-edit editNew" data-toggle="modal" data-target="#myModal" data-keyboard="false" data-backdrop="static" title="Edit"></a><a class="fa fa-unlock-alt" href="#" title="Lock"></a> '
			}
	  	    let rowData = [
	  	      	noIndex + index + 1,
	  	      	'<p class ="'+textLineThrough+'">'+item.createdBy+'</p>',
	  	    	'<p class ="'+textLineThrough+'">'+item.createdDate+'</p>',
	  	  		'<p class ="'+textLineThrough+'">'+item.userName+'</p>',
	  			'<p class ="'+textLineThrough+'">'+item.updatedBy+'</p>',
	  			'<p class ="'+textLineThrough+'">'+item.updatedDate+'</p>',
	       		edit+'<input type="hidden" id ="userName" value="'+item.userName+'"/>'
	  	    ];
	  	    table.row.add(rowData).draw(false);
	  	  });
	  	}
  </script>
</body>
</html>