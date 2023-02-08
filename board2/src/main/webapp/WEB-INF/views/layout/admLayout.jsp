<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<!DOCTYPE html>
<html lang="ko">
<head>
	<tiles:insertAttribute name="admDtd"/>
</head>
<body>

<tiles:insertAttribute name="admHeader"/>
<div class="contents">
	<tiles:insertAttribute name="content"/>
</div>
</body>
</html>