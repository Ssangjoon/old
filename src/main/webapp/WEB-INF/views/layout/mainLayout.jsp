<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<!DOCTYPE html>
<html lang="ko">
<head>
	<tiles:insertAttribute name="dtd"/>
</head>
<body>

<tiles:insertAttribute name="header"/>
<div id="fullpage" class="main_wrap">
	<tiles:insertAttribute name="content"/>
	<div class="section fp-auto-height" id="section6">
		<tiles:insertAttribute name="footer"/>
	</div>
</div>
</body>
</html>