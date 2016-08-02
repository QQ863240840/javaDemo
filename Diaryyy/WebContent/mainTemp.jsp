<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>个人日记本主页</title>
<jsp:include page="base.jsp"/>
<style type="text/css">
	  body {
        padding-top: 60px;
        padding-bottom: 40px;
      }
</style>
</head>
<body>
<div class="navbar navbar-inverse navbar-fixed-top">
      <div class="navbar-inner">
        <div class="container">
          <a class="brand" href="#">云笔记本</a>
          <div class="nav-collapse collapse">
             <ul class="nav">
              <!--系统导航栏：需要编写对应的主页面的Servlet的相应方法，分别跳转到相应页面  -->
              <li class="active"><a href="main?action=mainPage"><i class="icon-home"></i>&nbsp;主页</a></li>
              <li class="active"><a href="write?action=writepage"><i class="icon-pencil"></i>&nbsp;写日记</a></li>
              <li class="active"><a href="diaryType?action=list" ><i class="icon-book"></i>&nbsp;日记分类管理</a></li>
              <li class="active"><a href="user?action=userMain"><i class="icon-user"></i>&nbsp;个人中心</a></li>
            </ul>
          </div>
          <form name="myForm" class="navbar-form pull-right" method="post" action="main?all=true">
			  <input class="span2" id="s_title" name="s_title"  type="text" style="margin-top:5px;height:30px;" placeholder="往事如烟...">
			  <button type="submit" class="btn" onkeydown=""><i class="icon-search"></i>&nbsp;搜索日志</button>
		  </form>
        </div>
      </div>
</div>
<div class="container">
	<div class="row-fluid">
		<div class="span9">
		<!--通过EL表达式取出页面值  -->
			<jsp:include page="${mainPage}"></jsp:include>
		</div>
		<div class="span3">
			<div class="data_list">
				<div class="data_list_title">
					<img src="images/user_icon.png"/>
					个人中心
				</div>
				<div class="user_image">
				    <img src="${pageContext.request.contextPath}/userImages/${currentUser.imageName}"/>
					<a href = "filetem action=file"></a>
				</div>
				<div class="nickName">小三</div>
				<div class="userSign">写代码的人好帅</div>
			</div>
			
			<div class="data_list">
				<div class="data_list_title">
					<img src="images/byType_icon.png"/>
					按日志类别
				</div>
				<div class="datas">
					<ul>
					<!--返回每种类型的日记的数量  -->
						
							<li><span><a href=""/></a></span></li>
						
					</ul>
				</div>
			</div>
			
			<div class="data_list">
				<div class="data_list_title">
					<img src="images/byDate_icon.png"/>
					按日志日期
				</div>
				<div class="datas">
					<ul>
						
							<li><span><a href=""></a></span></li>
						
					</ul>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>