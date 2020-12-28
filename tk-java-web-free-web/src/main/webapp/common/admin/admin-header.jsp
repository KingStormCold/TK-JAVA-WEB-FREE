<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<%@ page import="tuan.kul.security.SecurityUtils"%>
<!DOCTYPE html>
<header>

	<div class="container-fluid">
		<div class="row content">
			<div class="col-sm-3 sidenav hidden-xs">
				<h2>Logo</h2>
				<ul class="nav nav-pills nav-stacked">
					<li class="active"><a href="#section1">Dashboard</a></li>
					<li><a href="#section2">Age</a></li>
					<li><a href="#section3">Gender</a></li>
					<li><a href="#section3">Geo</a></li>
				</ul>
				<br>
			</div>
			<br>
			<div class="col-sm-9">
				<div class="well">
					<h4>Dashboard</h4>
					<p>Some text..</p>
				</div>
				<div class="row">
					<div class="col-sm-3">
						<div class="well">
							<h4>Users</h4>
							<p>1 Million</p>
						</div>
					</div>
					<div class="col-sm-3">
						<div class="well">
							<h4>Pages</h4>
							<p>100 Million</p>
						</div>
					</div>
					<div class="col-sm-3">
						<div class="well">
							<h4>Sessions</h4>
							<p>10 Million</p>
						</div>
					</div>
					<div class="col-sm-3">
						<div class="well">
							<h4>Bounce</h4>
							<p>30%</p>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-4">
						<div class="well">
							<p>Text</p>
							<p>Text</p>
							<p>Text</p>
						</div>
					</div>
					<div class="col-sm-4">
						<div class="well">
							<p>Text</p>
							<p>Text</p>
							<p>Text</p>
						</div>
					</div>
					<div class="col-sm-4">
						<div class="well">
							<p>Text</p>
							<p>Text</p>
							<p>Text</p>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-8">
						<div class="well">
							<p>Text</p>
						</div>
					</div>
					<div class="col-sm-4">
						<div class="well">
							<p>Text</p>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>


	<%-- <div class="row background-header">
        <div class="col-sm-2 logo-header"><a href="/user-role/role">
        <img src=" <c:url value='/image/logo1.png'/>" alt="logo">
                <p class="logo-title"><%=SecurityUtils.getPrincipal().getUsername() %></p>
            </a></div>
        <div class="col-sm-9 menu-header">
            <i class="fa fa-bars icon-menu icon-small" aria-hidden="true"></i>
            <h4 class="run-header">Welcome admin page</h4>
            <input type="text" class="content-search" placeholder="Search">
            <i class="fa fa-search icon-search icon-small" aria-hidden="true"></i>
        </div>
        <div class="col-sm-1 avatar-header">
            <img src=" <c:url value='/image/logo1.png'/>" alt="avatar" id="avatar-img">
            <ul class="personal-info-header">
                <li><a href="#"><i class="fa fa-user" aria-hidden="true"></i><span class="profile-info-label"><%=SecurityUtils.getPrincipal().getUsername() %></span></a></li>
                <li><a href='#'><i class="fa fa-key" aria-hidden="true"></i><span class="profile-info-label">Change password</span></a></li>
                <li><a href='<c:url value= "/logout"/>'><i class="fa fa-sign-out" aria-hidden="true"></i><span class="profile-info-label">Logout</span></a></li>
            </ul>
        </div>
    </div> --%>
</header>