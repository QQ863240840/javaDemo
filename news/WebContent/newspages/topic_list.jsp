<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>添加主题--管理后台</title>
<a href="topic_add.jsp"> <input type ="button" value ="添加主题"/></a>
<link href="../css/admin.css" rel="stylesheet" type="text/css" />
</head>
<body>

<jsp:include page="console_element/top.jsp" flush="false"/>
<div id="main">
<jsp:include page="console_element/left.jsp" flush="false"/>
  <div id="opt_area">
    <ul class="classlist">
    
    <!-- c:forEach 遍历主题数据得到一个集合 -->
    
    <c:forEach items = "${topicList}"   var="topic">    
         <li> 主题：${topic.tname} <a href='../topicServlet?action=update & topicId=${topic.tid}'>修改</a> &#160;&#160;&#160;&#160;
                                 <a href='../topicservlet?action=delete & topicId=${topic.tid}'>删除</a> </li>
    </c:forEach>
    </ul>
  </div>
</div>
<div id="footer">
  <jsp:include page="console_element/bottom.html" flush="true" />
</div>
</body>
</html>
