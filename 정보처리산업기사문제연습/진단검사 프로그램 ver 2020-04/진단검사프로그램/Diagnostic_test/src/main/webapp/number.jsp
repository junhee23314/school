<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import = "java.sql.*" %>
<%@ page import = "DB.DBConnect" %>

<%
String sql = "select tp.p_city,"
			+ " case tp.p_city"
			+ " when '10' then '서울'"
			+ " when '20' then '경기'"
			+ " when '30' then '강원'"
			+ " when '40' then '대구'"
	    	+ "end as p_city_name,"
	    	+ " count(tp.p_city) as p_city_count"
	    	+ " from TBL_PATIENT_202004 tp,tbl_result_202004 tr"
			+ " where tp.p_no = tr.p_no group by p_city order by p_city asc";	
	
	Connection conn = DBConnect.getConnection();
	PreparedStatement pstmt = conn.prepareStatement(sql);
	ResultSet rs = pstmt.executeQuery();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel = "stylesheet" href = "css/style.css">
</head>
<body>
	
		<jsp:include page="layout/header.jsp"></jsp:include>
	
	
		<jsp:include page="layout/nav.jsp"></jsp:include>
	
	<section id = "main-section">
		<h2>환자조회</h2>
		<br>
		<table id = "table_line">
			<tr>
				<th>지역코드</th>
				<th>지역명</th>
				<th>검사건수</th>
			</tr>
			<%while(rs.next()){ %>
				<tr>
					<td><%=rs.getString(1) %></td>
					<td><%=rs.getString(2) %></td>
					<td><%=rs.getString(3) %></td>
				</tr>
			<%} %>
		</table>
	</section>
	
		<jsp:include page="layout/footer.jsp"></jsp:include>
	
</body>
</html>