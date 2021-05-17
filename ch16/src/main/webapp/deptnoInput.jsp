<%@page import="ch16.*,java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html><html><head><meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="common.css"></head><body>
<%	DeptDao dd = DeptDao.getInstance();
	List<Dept> list = dd.list(); 		%>
<!-- <form action="EmpInfo"> -->
<form action="EmpXml">
<table><caption>부서 선택</caption>
	<tr><td><select name="deptno">
		<option value="0">전체(0)</option>
	<%	for(Dept dept:list) { %>
			<option value="<%=dept.getDeptno()%>"><%=dept.getDname() %>
				(<%=dept.getDeptno() %>)</option>
	<%	} 	%>
	</select></td></tr>
	<tr><th colspan="2"><input type="submit"></th></tr>
</table>
</form>
</body>
</html>