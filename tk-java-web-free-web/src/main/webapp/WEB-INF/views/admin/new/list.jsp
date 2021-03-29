<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<c:url var="formNew" value="/admin/news/search" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>

<div class="col-sm-12 main-content">
    <div>
    	<div class="col-sm-12">
	    	<form action="">
		   		<div class="col-sm-2">
					<input class="form-control input-width datepicker-format bgcl-transparent" type="text" id="datepickerStart" placeholder="Start date">
				</div>
				<div class="col-sm-2">
					<input class="form-control input-width datepicker-format bgcl-transparent" type="text" id="datepickerEnd" placeholder="End date">
				</div>	
				<div class="col-sm-2">
					<select class="form-control custom-select select-option-height input-width mrt-22 bgcl-transparent" id="selectSearchBy">
						<option value="" class="option-new">Please, choose a item!</option>
						<option value="title" class="option-new">Title Name</option>
						<option value="createdBy" class="option-new">Created Name</option>
						<option value="modifiedBy" class="option-new">Update Name</option>
					</select>
				</div>
				<div class="col-sm-5">
					<input type="text" id="searchValue" class="form-control select-option-height input-width mrt-22 bgcl-transparent" placeholder="Chức vô địch giải bóng đá...">
				</div>
				<div class="col-sm-1">
					<button type="button" class="btn btn-success btn-md mb-3 title-width mrt-22 height-35"><p class = "button-font-family">Search</p></button>
				</div>
	   		</form>
    	</div>
	    <div class="add-new col-sm-2 mrt-20 mrb-20">
	        <a title="Add New" class="btn btn-primary btn-add-new" data-toggle="modal" data-target="#myModal" data-keyboard="false" data-backdrop="static">+</a>
	    </div>
	    <div id="loader" class = "display-none"></div>
	    <div class="card">
		    <div class="card-body">
		        <div class="data-tables">
		            <table id="new-table" class="hover new-table">
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
	<script>
	$(document).ready(function(){
	    var table = $('#new-table').DataTable( {
	        "lengthChange": false,
	        "pageLength": 20,
	        "autoWidth" : false,
	        "info": false,
	        "paging": false,
	        "scrollY": '60vh',
	        "scrollCollapse": true
	    });
		
		var dataArray = {};
   		dataArray["start_date"] = '09/01/2020';
   		dataArray["end_date"] = '09/01/2020';
   		dataArray["search_by"] = 'titleName';
   		dataArray["search_value"] = '';	
   		dataArray["page_num"] = '1';
   		dataArray["page_size"] = '4';
   		$.ajax({
	        type: 'POST',
	        url: '${formNew}',
	        dataType: 'json',
	        contentType:'application/json',
	        data: JSON.stringify(dataArray),
	        success: function (data) {
	            var arr = data.listNewInfo;
	            var jsonString = JSON.stringify(arr);
	            console.log(arr);
	            if (data.empty == false) {
	                var table = $('#example').DataTable( {
		                "autoWidth" : false,
				        "scrollX": true,
				        "info": false, //hiden displays infomation of all records from the database
				        "lengthChange": true, // show an option to choose how many records in a page
				        "ordering": true, //enable sort by column
				        "processing": true // with a large amount record so the datatable will display info to tell users to wait
		            } );
					$.each(arr,function(index, data){
						 var tableRow = [
			                  index + 1,
			                  data.createdBy,
			                  data.createdDate,
			                  data.title,
			                  data.updatedDate,
			                  data.updatedBy,
			                  '<a class="fa fa-edit editNew" href="#"></a><a class="fa fa-remove" href="https://www.google.com/"></a><input type="hidden" id ="newId" value="'+data.newId+'"/>'
			                ]
						 table.row.add(tableRow).draw(false);
					})

	            }
	            else {
	                $('#example').DataTable( {
		                "autoWidth" : false,
		                "scrollX": true
	                });
	            }
	            
            },
            error: function (error) {
            	console.log(error);
                window.location.href = "<c:url value='/error'/>";
            }
	      });

	});
	
	
	$('#datepickerStart').attr('readonly', 'readonly');
	$('#datepickerEnd').attr('readonly', 'readonly');
	$("#datepickerStart").datepicker({
	    showOtherMonths: true,
		selectOtherMonths: true,
		showOn: "button",
	    buttonImage: "<c:url value ='/image/datepicker.png'/>",
	    buttonImageOnly: true,
	    showOn: 'both',
	    dateFormat: 'dd/mm/yy',
	});
	
	$("#datepickerEnd").datepicker({
	    showOtherMonths: true,
		selectOtherMonths: true,
		showOn: "button",
	    buttonImage: "<c:url value ='/image/datepicker.png'/>",
	    buttonImageOnly: true,
	    showOn: 'both',
	    dateFormat: 'dd/mm/yy',
	});
	
	$(".ui-datepicker-trigger").css("margin-bottom","1px");
	$(".ui-datepicker-trigger").css("height","40px");
	$('#datepickerStart').keyup().keydown(function( event ) {
	    if (event.which == 46 || event.which == 8) {
	        $('#datepickerStart').val('');
	    }
	});
	
	$('#datepickerEnd').keyup().keydown(function( event ) {
	    if (event.which == 46 || event.which == 8) {
	        $('#datepickerEnd').val('');
	    }
	});
	
	$('.addNew').click(function(){
		localStorage.setItem("newId", "");
		window.location.href = "<c:url value='/admin/news'/>";
	});

	$('body').on("click", ".editNew", function(){
		var newId = $(this).siblings('#newId').val();
		localStorage.setItem("newId", newId);
		$('.editNew').attr("href", "<c:url value='/admin/news'/>")
	});
	$(".button-font-family").click(function(){
		var dataArray = {};
		dataArray["start_date"] = $('#datepickerStart').val();
   		dataArray["end_date"] = $('#datepickerEnd').val();
   		dataArray["search_by"] = $('#selectSearchBy').find(":selected").val();
   		dataArray["search_value"] = $('#searchValue').val();	
   		dataArray["page_num"] = '1';
   		dataArray["page_size"] = '20';
   		$.ajax({
	        type: 'POST',
	        url: '${formNew}',
	        dataType: 'json',
	        contentType:'application/json',
	        data: JSON.stringify(dataArray),
	        success: function (data) {
	            var message = data.message;
	            var arr = data.listNewInfo;
	            console.log(arr);
	            if (data.empty == false) {
	            	$('#example').DataTable().clear().draw();
	            	$.each(arr,function(index, data){
						 var tableRow = [
			                  index + 1,
			                  data.createdBy,
			                  data.createdDate,
			                  data.title,
			                  data.updatedDate,
			                  data.updatedBy,
			                  '<a class="fa fa-edit editNew" href="#"></a><a class="fa fa-remove" href="https://www.google.com/"></a><input type="hidden" id ="newId" value="'+data.newId+'"/>'
			                ]
						 $('#example').DataTable().row.add(tableRow).draw(false);
					})
	            }
	            else {
	                $('#example').DataTable().clear().draw();
	            }
            },
            error: function (error) {
            	console.log(error);
                window.location.href = "<c:url value='/error'/>";
            }
	      });
	});
	</script>
</body>
</html>