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
	
	<!-- page title area end -->
	<div class="main-content-inner">
		<!-- sales report area start -->
		<div class="sales-report-area mt-4 mb-5">
			<div class="row">
				<div class= "col-12 mt-2">
					<div class = "card">
						<div class = "card-body">
							<div class = "row">
								<div class = "col-sm-6 input-group mb-3">
									<div class="input-group-prepend">
                                        <span class="input-group-text title-width" id="basic-addon1">Start date</span>
                                    </div>
                                    <div>
				                        <input class="form-control input-width datepicker-format" type="text" id="datepickerStart">
                                    </div>
								</div>
								<div class = "col-sm-6 input-group mb-3">
									<div class="input-group-prepend">
                                        <span class="input-group-text title-width" id="basic-addon1">Search by</span>
                                    </div>
                                    <div>
				                        <select class="custom-select select-option-height input-width" id="selectSearchBy">
											<option value="">Please, choose a item!</option>
											<option value="title">Title Name</option>
											<option value="createdBy">Created Name</option>
											<option value="modifiedBy">Update Name</option>
										</select>
                                    </div>
								</div>
								<div class = "col-sm-6 input-group mb-3">
									<div class="input-group-prepend">
                                        <span class="input-group-text title-width" id="basic-addon1">End date</span>
                                    </div>
                                    <div>
				                        <input class="form-control input-width datepicker-format" type="text" id="datepickerEnd">
                                    </div>
								</div>
								<div class = "col-sm-6 input-group mb-3">
									<div class="input-group-prepend">
                                        <span class="input-group-text title-width" id="basic-addon1">Search value</span>
                                    </div>
                                    <div>
				                        <input type="text" id="searchValue" class="form-control select-option-height input-width" placeholder="Thời sự" aria-label="Username" aria-describedby="basic-addon1">
                                    </div>
								</div>
								<div class = "col-sm-6 input-group">
								</div>
								<div class = "col-sm-6 mb-24-negative">
									<button type="button" class="btn btn-success btn-md mb-3 title-width"><p class = "button-font-family">Search</p></button>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-12 mt-2">
					<div class="search-box pull-right">
						<a><button class="btn btn-success mb-3 addNew"><span>+</span></button></a>
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
											<th>Title</th>
											<th>Update Date</th>
											<th>Update By</th>
											<th>Action</th>
										</tr>
									</thead>
									<tbody>

									</tbody>
									
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script>
	$(document).ready(function(){
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