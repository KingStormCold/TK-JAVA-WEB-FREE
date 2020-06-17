<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<header>
    <div class="row background-header">
        <div class="col-sm-2 logo-header"><a href="/user-role/role">
        <img src=" <c:url value='/image/logo1.png'/>" alt="logo">
                <p class="logo-title">Tuan Kul</p>
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
                <li><a href="#">Tuan Admin</a></li>
                <li><a href="#">Profile</a></li>
                <li><a href="###">Logout</a></li>
            </ul>
        </div>
    </div>
</header>