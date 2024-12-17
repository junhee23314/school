<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        
<%@ page import = "java.sql.*" %>
<%@ page import = "DB.DBConnect" %>

<%
String sql= "select patient.p_no, patient.p_name,"
	+ " test.t_name, to_char(result.t_sdate, 'YYYY-MM-DD') as t_sdate,  "
	+ " case result.t_status  when '1' then '검사중' when '2' then '검사완료' end as t_status, "
	+ " to_char(result.t_ldate, 'YYYY-MM-DD') as t_ldate, "
	+ " case result.t_result when 'X' then '미입력' when 'P' then '양성'  when 'N' then '음성' end as t_result  "
	+ " from tbl_patient_202004 patient,  tbl_lab_test_202004 test,  tbl_result_202004 result "
	+ " where patient.p_no = result.p_no  and result.t_code = test.t_code";
	
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
	
	<section>
		<h2>검사결과조회</h2>
		<br>
		<table>
			<tr>
				<th>환자번호</th>
				<th>환자성명</th>
				<th>검사종류</th>
				<th>검사시작일</th>
				<th>검사상태</th>
				<th>검사종료일</th>
				<th>검사결과</th>
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
				</tr>
			<%} %>
		</table>
	</section>
	
		<jsp:include page="layout/footer.jsp"></jsp:include>
	
</body>
</html>