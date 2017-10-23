<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>     
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Redis All Kv View</title>
</head>
<body>
	<h2>Redis List size: ${fn:length(rs) }</h2>
	<table>
		<c:forEach items="${rs}" var="cur" >
			<tr>
				<td>${cur.key }</td>
				<td>${cur.value }</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>