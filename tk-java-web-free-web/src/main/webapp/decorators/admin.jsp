<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>

<html>

<head>
<meta charset="utf-8">
<meta http-equiv="x-ua-compatible" content="ie=edge">
<head>
    <title>Admin page</title>
    <meta name="viewport" content="initial-scale=1, minimum-scale=1, maximum-scale=1, width=device-width, user-scalable=1.0">
    <link rel="stylesheet" href = "<c:url value='/template1/admin/css/style.css'/>" rel="stylesheet" type="text/css" media="all">
    <script type='text/javascript' src='<c:url value="/template1/admin/js/jquery.min.js"/>'></script><!-- 3.5.1 -->
    <link rel="stylesheet" href = "<c:url value='/template1/admin/css/bootstrap.min.css'/>" rel="stylesheet" type="text/css" media="all"><!-- 4.4.1 -->
    <link rel="stylesheet" href = "<c:url value='/template1/admin/css/jquery.dataTables.css'/>" rel="stylesheet" type="text/css" media="all">
    <script type='text/javascript' src='<c:url value="/template1/admin/sweetalert2/sweetalert2.min.js"/>'></script>
	<link rel="stylesheet" href="<c:url value="/template1/admin/sweetalert2/sweetalert2.min.css"/>">
</head>

<body>

	<%@ include file="/common/admin/admin-header.jsp"%>
	<div class="row">
		<%@ include file="/common/admin/admin-menu.jsp"%>
		<dec:body/>
	</div>
	<img src = "<c:url value = '/template1/admin/img/reload.gif'/>" class = "gif-loading"></img>                                                                                                                                                                                                               
<script src = "<c:url value='/template1/admin/js/javascript.js'/>"></script>
<script src="https://use.fontawesome.com/b59cf34d3a.js"></script>
<script type='text/javascript' src='<c:url value="/template1/admin/js/popper.min.js"/>'></script><!-- 1.16.0 -->
<script type='text/javascript' src='<c:url value="/template1/admin/js/bootstrap.min.js"/>'></script>
<script type='text/javascript' src='<c:url value="/template1/admin/js/jquery.dataTables.js"/>'></script><!--  1.10.21-->
<script src = "<c:url value='/template1/admin/js/pagination.js'/>"></script>
<script src = "<c:url value='/template1/admin/js/validate.js'/>"></script>
<script src = "<c:url value='/template1/admin/js/notify.js'/>"></script>
<script src = "<c:url value='/template1/admin/js/notify.min.js'/>"></script>
<script type="text/javascript">
   function showAlertBeforeDelete(callback, id) {
       swal({
           title: "Comfirm delete",
           text: "Are you sure to delete '" + id + "' ?",
           type: "warning",
           showCancelButton: true,
           confirmButtonText: "Sure",
           cancelButtonText: "Cancel",
           confirmButtonClass: "btn btn-success",
           cancelButtonClass: "btn btn-danger"
       }).then(function (isConfirm) {
           if (isConfirm) {
               callback();
           }
       });
   }

   function showAlertBeforeUnlock(callback, id) {
       swal({
           title: "Comfirm Active",
           text: "Are you sure to active '" + id + "' ?",
           type: "warning",
           showCancelButton: true,
           confirmButtonText: "Sure",
           cancelButtonText: "Cancel",
           confirmButtonClass: "btn btn-success",
           cancelButtonClass: "btn btn-danger"
       }).then(function (isConfirm) {
           if (isConfirm) {
               callback();
           }
       });
   }
</script>
</body>
</html>