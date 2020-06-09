<%@ page language="java"  import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<table  width="600" border="1" cellpadding="0" >
     <c:forEach var="U" items="${list}" > 
      <form > 
       <tr>
	       <td><input type="text" value="${U.classes}" name="classes"></td>
	       <td><input type="text" value="${U.name}" name="name"></td>
	       <td><input type="text" value="${U.calory}" name="calory"></td>
	   </tr>
    </form> 
    </c:forEach>
    </table>
</body>
</html>