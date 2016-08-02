<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="../base.jsp"></jsp:include>
<script type="text/javascript">
  // 进行删除验证
	
</script>
<div class="data_list">
	<div class="data_list_title">
		<img
			src="${pageContext.request.contextPath}/images/diary_show_icon.png" />
		日记信息
	</div>
	<div>
		<div class="diary_title">
			<h3>${diary.title }</h3>
				
		<a href="write?action=modify&diaryId=${diary.diaryId }">${diary.title}</a>
		
		</div>
		<div class="diary_info">发布时间：『${diary.releaseDate}』&nbsp;&nbsp;日志类别：</div>
		<div class="diary_content">${diary.content }</div>
		<div class="diary_action">
		<button class="btn btn-primary" type="button"	
		onclick="javascript:window.location='write?action=changeDiary&diaryId=${diary.diaryId}'">修改日志</button>
		<button class="btn btn-primary" type="button" onclick="javascript:history.back()">返回</button>
		<button class="btn btn-danger" type="button" 
		onclick="javascript:window.location='write?action=deleteDiary&diaryId=${diary.diaryId}'">删除日志</button>
		</div>
	</div>
</div>