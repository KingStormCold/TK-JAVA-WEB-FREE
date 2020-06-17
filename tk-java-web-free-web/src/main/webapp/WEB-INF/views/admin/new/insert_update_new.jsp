<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<c:url var="formGetInfo" value="/admin/news/get-info?id=" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<div class="main-content-inner">
		<!-- sales report area start -->
		<div class="sales-report-area mt-4 mb-5">
			<div class="row">
				<div class= "col-12 mt-2">
					<div class = "card">
						<div class = "card-body">
							<div class = "row">
							<form action="">
								<div class = "col-sm-12 input-group mb-3">
									<div class="input-group-prepend">
                                        <span class="input-group-text title-width-news" id="basic-addon1">Category</span>
                                    </div>
                                    <div>
				                        <select class="custom-select input-width-new" id ="selectCategory">
										</select>
                                    </div>
								</div>
								<div class = "col-sm-12 input-group mb-3">
									<div class="input-group-prepend">
                                        <span class="input-group-text title-width-news" id="basic-addon1">Title</span>
                                    </div>
                                    <div>
				                        <input type="text" id="newTitle" class="form-control select-option-height input-width-new">
                                    </div>
								</div>
								<div class = "col-sm-12 input-group mb-3">
									<div class="input-group-prepend">
                                        <span class="input-group-text title-width-news" id="basic-addon1">Description</span>
                                    </div>
                                    <div>
				                        <input type="text" id="newDescription" class="form-control select-option-height input-width-new">
                                    </div>
								</div>
								<div class = "col-sm-12 input-group mb-3">
									<div class="input-group-prepend">
                                        <span class="input-group-text title-width-news title-height-preview" id="basic-addon1">Image</span>
                                    </div>
                                    <div class="custom-file col-sm-3">
                                        <input type="file" class="custom-file-input" id="inputGroupFile01">
                                        <label class="custom-file-label" for="inputGroupFile01"></label>
                                    </div>
								</div>
								<div class = "col-sm-12 input-group mb-3">
									<div class="input-group-prepend">
                                        <span class="input-group-text title-width-news title-height-preview" id="basic-addon1">Preview Image</span>
                                    </div>
                                    	<img src="" id="viewImage" class="size-image">
								</div>
								<div class = "col-sm-12 input-group mb-3">
									<div class="input-group-prepend col-sm-2">
                                    </div>
                                    <div class="col-sm-3">
                                       
                                    </div>
								</div>
								<div class = "col-sm-12 input-group mb-3">
									<div >
                                        <span class="input-group-text title-width-news" id="basic-addon1">Content</span>
                                    </div>
                                    <div>
				                        <textarea  cols="80" rows="20" id="newContent"></textarea>
                                    </div>
								</div>
								<input type="hidden" id ="hiddenNewId" value=""/>
							</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
	var editor = '';
	$('#inputGroupFile01').change(function () {
	    openImage(this, "viewImage");
    });
	
	function openImage(input, imageView) {
        if (input.files && input.files[0]) {
            $('.custom-file-label').text(input.files[0].name);
            var reader = new FileReader();
            reader.onload = function (e) {
                $('#' +imageView).attr('src', reader.result);
            }
            reader.readAsDataURL(input.files[0]);
        }
    }
	$(document).ready(function () {
	    editor = CKEDITOR.replace('newContent');
        CKFinder.setupCKEditor( editor, '${pageContext.request.contextPath}/ckfinder/' );
        
    	var newId = localStorage.getItem("newId");
    	$('#hiddenNewId').val(newId);
	    $.ajax({
	        type: 'GET',
	        url: '${formGetInfo}' + newId,
	        success: function (data) {
	            var listCategory = data.objectInfo.listCategory;
	            $.each(listCategory, function (index, item) {
	                $('#selectCategory').append($('<option>', { 
	                    value: item.categoryCode,
	                    text : item.categoryName 
	                }));
	            });
	            if (newId != "") {
	                $('#selectCategory').val(data.objectInfo.category);
	                $('#newTitle').val(data.objectInfo.title);
	                $('#newDescription').val(data.objectInfo.description);
	                $('#newImage').val(data.objectInfo.image);
	                $('#newContent').val(data.objectInfo.content);
	            }
           },
           error: function (error) {
               window.location.href = "<c:url value='/error'/>";
           }
	      });
    });
</script>
</body>
</html>