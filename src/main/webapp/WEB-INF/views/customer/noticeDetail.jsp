<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


	<div class="board_in">
		<table>
			<thead>
				<tr>
					<th><c:out value="${list.title}" /></th>
				</tr>
				<tr>
					<td>
						<span>작성자 : <c:out value="${list.writer}" /></span>
						<span>작성일 : <c:out value="${list.crt_dt}" /></span>
						<span>조회수 : <c:out value="${list.view_count}" /></span>
					</td>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td><textarea class="view_box" readonly="readonly"><c:out value="${list.content}" /></textarea></td>
				</tr>
			</tbody>
			<c:if test="${fn:length(file) > 0}">
			<tfoot>
				<tr>
					<td>
						<dl>
							<dt>첨부파일</dt>
							<dd>
								<c:forEach items="${file}" var="file">
									<a href="#this">${file.org_file}</a>
								</c:forEach>
							</dd>
						</dl>
					</td>
				</tr>
			</tfoot>
			</c:if>
		</table>
	</div>
	<div class="board_bottom">
		<div class="btns tac">
			<a href="/customer/notice.do" class="btn">목록</a>
		</div>
	</div> <!-- //board_bottom -->