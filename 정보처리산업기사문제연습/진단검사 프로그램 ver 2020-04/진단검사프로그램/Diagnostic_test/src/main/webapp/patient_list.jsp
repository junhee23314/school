<%@page import="java.sql.*"%>
<%@page import="DB.DBConnect"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%

String sql="select p_no,p_name,substr(p_birth,1,4)||'년'||substr(p_birth,5,2)||'월'||substr(p_birth,7,2)||'일' as p_birth,"
	+ " case p_gender when 'M' then '남' when 'F' then '여' end as p_gender,"
	+ " p_tel1||'-'||p_tel2||'-'||p_tel3 as p_tel,"
	+ " case p_city when '10' then '서울' when '20' then '경기' when '30' then '강원' when '40' then '대구' end as p_city"
	+ " from TBL_PATIENT_202004";

	Connection conn = DBConnect.getConnection();
	PreparedStatement pstmt = conn.prepareStatement(sql);
	ResultSet rs = pstmt.executeQuery();
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="css/style.css">
<title>환자조회</title>
</head>
<body>
	
	<jsp:include page="layout/header.jsp"></jsp:include>
	<jsp:include page="layout/nav.jsp"></jsp:include>
	

	<section>
	<h2>환자조회</h2> <br>
	
	
	<table>
		<tr>
			<td>환자번호</td>
			<td>환자성명</td>
			<td>생년월일</td>
			<td>성별</td>
			<td>전화번호</td>
			<td>지역</td>
		</tr>
		
		<%while(rs.next()){ %>
		<tr>
		<td><%=rs.getString(1) %></td>
		<td><%=rs.getString(2) %></td>
		<td><%=rs.getString(3) %></td>
		<td><%=rs.getString(4) %></td>
		<td><%=rs.getString(5) %></td>
		<td><%=rs.getString(6) %></td>
		
		
		
		</tr>
	<%} %>
	</table>
	
	</section>
	
	<jsp:include page="layout/footer.jsp"></jsp:include>
	

</body>
</html>