<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>添加主题--管理后台</title>
<link href="../css/admin.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../js/jquery-1.8.2.min.js"></script>
<script type="text/javascript">
  $(function(){  	
	  $("#subInfo").click(function(){              //value="提交" id="subInfo"
		  alert("点击了提交")
		  // 看是否存在主题的ID
		var topicId=  $("#topicHiddenId").val();   //主题ID   id="topicHiddenId"
		if(topicId==null){
			alert("新增主题")
			// 新增：
			// 表单直接提交
			$("#queryForm").submit(); //提交表单            //提交表单      表单id=queryForm
		}else{
			alert("进入修改");
			
			$("#queryForm").submit(function(){
				return false;//
			});
			
			// 编辑
		  var topicName=$("#tname").val(); 
			//提交到后台
			//ajax  Jquery  ajax
		  $.ajax({
			    type: "POST",
			    url: "../topicServlet",
			    data:  {action:'updateTp',
				    	topicName:topicName,
				    	topicId:topicId}, 
			    success: function (data) {
				    // 跳转到后台URL---》列表页面
				    alert("成功了");
				    var list="list";
				    window.location.href="../topicServlet?action="+list;
			    },
			    error: function (msg) {
			    }
			 }); 	
			
		}
	  })
	  
  })
</script>
</head>
<body>
<%@include file="console_element/top.jsp" %>
<div id="main">
  <%@include file="console_element/left.jsp" %>
  <div id="opt_area">
    <h1 id="opt_type"> 添加主题： </h1>
    <input  type="hidden" value="${topic.tid}" id="topicHiddenId"/>
    <form  id="queryForm"  action="../topicServlet?action=save" method="post" >
      <p><!-- ../index.jsp -->
        <label> 主题名称 </label>
        <input name="tname" type="text" value="${topic.tname}" class="opt_input" id="tname"/>
      </p>
      <input type="submit" value="提交" id="subInfo" class="opt_sub" />
      <a href="../topicServlet?action=list"><input type="button" value="返回" class="opt_sub" /></a>
    </form>
  </div>
</div>
<div id="footer">
  <%@include file="console_element/bottom.html" %>
</div>
</body>
</html>

