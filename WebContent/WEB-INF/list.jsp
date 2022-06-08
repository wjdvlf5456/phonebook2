<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.javaex.vo.PersonVo" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>전화번호부</h1>

	<h2>리스트</h2>

	<p>입력한 정보 내역입니다.</p>

	<c:forEach items="pList" var="pList">
		<table border="1" width ="500">
			<tr>
				<td>이름</td>
				<td>${pList.name }</td>
			</tr>
			<tr>
				<td>핸드폰</td>
				<td>${pList.hp}</td>
			</tr>
			<tr>
				<td>회사</td>
				<td>${pList.company }</td>
			</tr>
			<tr>
				<td><a href="./pbc?action=updateForm">[수정폼]</a></td>
				<td><a href="./pbc?action=delete&id=${pList.personId }">[삭제] </a></td>
			</tr>
		</table>
		<br>
	</c:forEach>
	<a href="./pbc?action=writeForm">추가번호 등록</a>

</body>
</html>