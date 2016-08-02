<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>   
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="../base.jsp"/>
<div class="data_list">
		<div class="data_list_title">
		<img src="${pageContext.request.contextPath}/images/list_icon.png"/>
		日记列表</div>
		<div class="diary_datas">	
			<ul>
			     <c:forEach var = "diary" items ="${diaryList}">
					<li>『${diary.releaseDate} 』<span>&nbsp;
					<a href="write?action=modify&diaryId=${diary.diaryId }">${diary.title}</a></span></li>
				 </c:forEach>
			</ul>
		</div>
		<div class="pagination pagination-centered">
			<ul>
				分页编码
			</ul>
		</div>
</div>