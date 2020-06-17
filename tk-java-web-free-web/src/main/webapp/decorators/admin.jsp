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
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.21/css/jquery.dataTables.css">
</head>

<body>

	<%@ include file="/common/admin/admin-header.jsp"%>
	<div class="row">
		<%@ include file="/common/admin/admin-menu.jsp"%>
		<dec:body/>
	</div>                                                                                                                                                                                                                                                 
<script src = "<c:url value='/template1/admin/js/javascript.js'/>"></script>
<script src="https://use.fontawesome.com/b59cf34d3a.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.21/js/jquery.dataTables.js"></script>
<script src = "<c:url value='/template1/admin/js/pagination.js'/>"></script>
</body>
</html>