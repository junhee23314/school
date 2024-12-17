<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="_1217.DBConnect"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
  	String sql ="select sno, sname, substr(sno,1,1), substr(sno,2,2), substr(sno,4,2),"
  	+ " case sgender when 'M' then '남' when 'F' then '여'end , sphone, saddress"
  	+" from student_tbl_03";

	Connection conn = DBConnect.getConnection();
	PreparedStatement pstmt = conn.prepareStatement(sql);
	ResultSet rs = pstmt.executeQuery();
	
	


%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="css/style.css">
<title>학생목록</title>

</head>
<body>
	
	<jsp:include page="layout/header.jsp"></jsp:include>
	<jsp:include page="layout/nav.jsp"></jsp:include>
	
	
	<section>
	<h2>학생목록</h2>
	
	<table>
	<tr>
		<th>학번</th><th>이름</th><th>학년</th><th>반</th>
		<th>번호</th><th>성별</th><th>전화번호</th><th>주소</th>
	</tr>
	<%while(rs.next()){ %>
	<tr>
		<td><%=rs.getString(1) %></td>
		<td><%=rs.getString(2) %></td>
		<td><%=rs.getString(3) %></td>
		<td><%=rs.getString(4) %></td>
		<td><%=rs.getString(5) %></td>
		<td><%=rs.getString(6) %></td>
		<td><%=rs.getString(7) %></td>
		<td><%=rs.getString(8) %></td>
	</tr>
	<%} %>
	</table>
	</section>

	<jsp:include page="layout/footer.jsp"></jsp:include>
	
	

</body>
</html>