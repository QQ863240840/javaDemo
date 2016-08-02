<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<html lang="zh">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>云笔记</title>
<jsp:include page="base.jsp"/>
<style type="text/css">
	  body {
        padding-top: 200px;
        padding-bottom: 40px;
        background-image: url('images/star.gif');
      }
      
      .form-signin-heading{
      	text-align: center;
      }

      .form-signin {
        max-width: 300px;
        padding: 19px 29px 0px;
        margin: 0 auto 20px;
        background-color: #fff;
        border: 1px solid #e5e5e5;
        -webkit-border-radius: 5px;
           -moz-border-radius: 5px;
                border-radius: 5px;
        -webkit-box-shadow: 0 1px 2px rgba(0,0,0,.05);
           -moz-box-shadow: 0 1px 2px rgba(0,0,0,.05);
                box-shadow: 0 1px 2px rgba(0,0,0,.05);
      }
      .form-signin .form-signin-heading,
      .form-signin .checkbox {
        margin-bottom: 10px;
      }
      .form-signin input[type="text"],
      .form-signin input[type="password"] {
        font-size: 16px;
        height: auto;
        margin-bottom: 15px;
        padding: 7px 9px;
      }

</style>
<script type="text/javascript">
	function checkForm(){
		var userName=document.getElementById("userName").value;
		var password=document.getElementById("password").value;
		if(userName==null || userName==""){
			document.getElementById("error").innerHTML="用户名不能为空";
			return false;
		}
		if(password==null || password==""){
			document.getElementById("error").innerHTML="密码不能为空";
			return false;
		}
		return true;
	}
</script>
</head>
<body>
<div class="container">
      <form name="myForm" class="form-signin" action="login" method="post" onsubmit="return checkForm()">
        <h2 class="form-signin-heading">云笔记</h2>
        <input id="userName" name="userName" value="${user.userName}"  type="text" class="input-block-level" placeholder="屌丝名...">
        <input id="password" name="password" value="${user.password}"   type="password" class="input-block-level" placeholder="屌丝码..." >
        <label class="checkbox">
          <input id="remember" name="remember" type="checkbox" value="remember-me">记住我 &nbsp;&nbsp;&nbsp;&nbsp; 
          <font id="error" color="red">${error }</font>  
        </label>
        <button class="btn btn-large btn-primary" type="submit">登录</button>
        
        <button class="btn btn-large btn-primary" type="button" >重置</button>

<p align="center" style="padding-top: 15px;">版权所有  2016  瑞才Java1605班  <br/>技术支持：疯狂小先锋</p>
      </form>
</div>
</body>
</html>