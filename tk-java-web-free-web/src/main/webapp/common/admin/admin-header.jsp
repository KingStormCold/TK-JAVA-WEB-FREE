<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<%@ page import="tuan.kul.security.SecurityUtils"%>
<!DOCTYPE html>
<header>
	<div class="row background-header">
        <div class="avatar-header">
            <img src=" <c:url value='/image/logo1.png'/>" alt="avatar" id="avatar-img">
            <ul class="personal-info-header">
                <li><a href="#"><i class="fa fa-user" aria-hidden="true"></i><span class="profile-info-label"><%=SecurityUtils.getPrincipal().getUsername() %></span></a></li>
                <li><a href='#'><i class="fa fa-key" aria-hidden="true"></i><span class="profile-info-label">Change password</span></a></li>
                <li><a href='<c:url value= "/logout"/>'><i class="fa fa-sign-out" aria-hidden="true"></i><span class="profile-info-label">Logout</span></a></li>
            </ul>
        </div>
    </div>
</header>