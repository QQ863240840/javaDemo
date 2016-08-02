<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>      
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="../base.jsp" />
<script type="text/javascript" src="../js/jquery-1.8.2.min.js"></script>
<script type="text/javascript">
  $(document).read(function(){  	
	  $("#subInfo").click(function(){              //value="提交" id="subInfo"
		  alert("点击了保存")
		  // 看是否存在主题的ID
		var diaryId= $("#diaryId").val();       //主题ID   id="topicHiddenId"
		if(diaryId==null){
			alert("新增笔记")
			// 新增：
			// 表单直接提交
			$("#queryForm").submit(); //提交表单            //提交表单      表单id=queryForm
		}else{
			alert("提交表单");
			
			$("#queryForm").submit(function(){
				return false;//
			});
			
			// 编辑
        var diaryName=$("#title").val(); 
		var content=$("#content").val();
		
			//提交到后台
			//ajax  Jquery  ajax
		  $.ajax({
			    type: "POST",
			    url: "write",
			    data:  {action:'updateTp',   //要发送给服务器的数据，以 Key/value 的键值对形式表示。
				    	diaryId:diaryId,
			    	    diaryName:diaryName,
				    	content:content}, 
			    success: function (data) {
				    // 跳转到后台URL---》列表页面
				    alert("成功了");
				    var list="list";
				    window.location.href="DiaryServlet?action="+list;
			    },
			    error: function (msg) {
			    }
			 }); 	
			
		}
	  })
	  
  })
</script>
<div class="data_list">
	<div class="data_list_title">
		<c:choose>
			<c:when test="${diary.diaryId!=null }">
				<img
					src="${pageContext.request.contextPath}/images/diary_type_edit_icon.png" />
				修改日记
	</div>
	</c:when>
	<c:otherwise>
		<img
			src="${pageContext.request.contextPath}/images/diary_add_icon.png" />
				写日记
</div>
</c:otherwise>
</c:choose>
<form id="queryForm" action="write?action=save" method="post"
	onsubmit="return checkForm()">
	<div>
		<div class="diary_title">
			<input type="text" id="title" name="title" value="${diary.title }"
				class="input-xlarge" style="margin-top: 5px; height: 30px;"
				placeholder="日志标题..." />
		</div>
		<div>
			<textarea class="ckeditor" id="content" name="content">${diary.content }</textarea>
		</div>
		<div class="diary_type">
			<select id="typeId" name="typeId">
				<option value="请选择日志类别"></option>
				<c:forEach var="diaryTypeCount" items="${diaryTypelist }">
					<option value="${diaryTypeCount.diaryTypeId }"
						${diaryTypeCount.diaryTypeId==diary.typeId?'selected':'' }>${diaryTypeCount.typeName }</option>
				</c:forEach>
			</select>
		</div>
		<div>
			<input type="hidden" id="diaryId" name="diaryId"value="${diary.diaryId }" /> 
			<input type="submit" class="btn btn-primary" id="subInfo" value="保存" />
			<button class="btn btn-primary" type="button"
				onclick="javascript:history.back()">返回</button>
			<font id="error" color="red">${error}</font>
		</div>
	</div>
</form>
</div>