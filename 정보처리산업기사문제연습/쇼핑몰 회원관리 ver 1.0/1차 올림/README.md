# 쇼핑물 회원관리 ver 1.0 (1차)

![시작화면](https://github.com/junhee23314/school/blob/main/%EC%A0%95%EB%B3%B4%EC%B2%98%EB%A6%AC%EC%82%B0%EC%97%85%EA%B8%B0%EC%82%AC%EB%AC%B8%EC%A0%9C%EC%97%B0%EC%8A%B5/%EC%87%BC%ED%95%91%EB%AA%B0%20%ED%9A%8C%EC%9B%90%EA%B4%80%EB%A6%AC%20ver%201.0/1%EC%B0%A8%20%EC%98%AC%EB%A6%BC/img/%EC%8B%9C%EC%9E%91%ED%99%94%EB%A9%B4.png)

정보처리산업기사 문제연습입니다.

*0825 1차 올림* <br>
*0828 2차 수정* <br>
*0830 3차 수정* <br>


## 핵심내용

### ---DB연결---

```
package DB;

import java.sql.*;

public class DBConnect {
	
	public static Connection  getConnection() {

		   Connection conn = null; //Connection(연결객체) 변수 conn 선언
		   
		   String url = "jdbc:oracle:thin:@localhost:1521:xe";  // 연결 드라이버 주소
		   String id = "system";   // 계정아이디
		   String pw = "1234";   //계정비번

		 //로그인 실패를 고려한 예외처리
		   try {
			            Class.forName("oracle.jdbc.OracleDriver");
			            conn =DriverManager.getConnection(url, id, pw);
			            System.out.println("DB Connect!");
		   }  catch(Exception e) {  e.printStackTrace(); }
		  return conn; 
	}

}

```
**DB연결코드**

### ---join.jsp---
![회원등록 화면](https://github.com/junhee23314/school/blob/main/%EC%A0%95%EB%B3%B4%EC%B2%98%EB%A6%AC%EC%82%B0%EC%97%85%EA%B8%B0%EC%82%AC%EB%AC%B8%EC%A0%9C%EC%97%B0%EC%8A%B5/%EC%87%BC%ED%95%91%EB%AA%B0%20%ED%9A%8C%EC%9B%90%EA%B4%80%EB%A6%AC%20ver%201.0/1%EC%B0%A8%20%EC%98%AC%EB%A6%BC/img/%ED%9A%8C%EC%9B%90%EB%93%B1%EB%A1%9D%20%ED%99%94%EB%A9%B4.png)

회원 가입 페이지로 보입니다. 데이터베이스 연결(DBConnect)와 SQL 쿼리를 사용해 회원 번호를 조회하는 부분이 포함되어 있습니다. 

ResultSet 객체는 쿼리 결과를 반환하며, 이 경우 가장 큰 회원 번호에 1을 더한 새로운 회원 번호를 생성합니다.
```
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import ="DB.DBConnect" %> <%-- 지시문형식을 통해 DB연결 자바파일 불러오기  --%>
<%@ page import = "java.sql.*" %>  <!-- 지시문형식을 통해 SQL 관련 라이브러리 불러오기  -->
 
 <%
        String sql = "select max(custno) from member_tbl_02"; // 쿼리문 형식의 문자열이 변수명 sql에 저장
 
        Connection conn = DBConnect.getConnection(); // DB 연결 기능을 객체변수 conn 에 저장 -> 1.DB연결
        PreparedStatement pstmt = conn.prepareStatement(sql);  // sql변수에 저장되어 있는 문장이 쿼리문이 됨 ->2.DB연결 후 쿼리문이 생성\
        ResultSet rs = pstmt.executeQuery(); // 변수pstmt에 저장되어있는 SQL문을 실행하여 객체변수 rs에 저장
        rs.next(); //변수 rs에 결과값이 저장되는 경우 next()를 호출하여 마지막 값을 확인
        
        int num = rs.getInt(1) + 1; //num에는 오라클 member 테이블의 마지막 회원번호 + 1 값이 정수로 저장
 %>
```
![미입력 확인](https://github.com/junhee23314/school/blob/main/%EC%A0%95%EB%B3%B4%EC%B2%98%EB%A6%AC%EC%82%B0%EC%97%85%EA%B8%B0%EC%82%AC%EB%AC%B8%EC%A0%9C%EC%97%B0%EC%8A%B5/%EC%87%BC%ED%95%91%EB%AA%B0%20%ED%9A%8C%EC%9B%90%EA%B4%80%EB%A6%AC%20ver%201.0/1%EC%B0%A8%20%EC%98%AC%EB%A6%BC/img/%EB%AF%B8%EC%9E%85%EB%A0%A5%20%ED%99%95%EC%9D%B8.gif)

JavaScript를 사용하여 폼의 입력값을 검증하는 기능을 추가했습니다. 예를 들어, 회원 성명, 전화번호 등이 입력되지 않으면 알림 메시지를 띄웁니다.
```
<!--유효성 검사코드-->
<script type = "text/javascript">
	function checkValue(){
					if(!document.data.custname.value) {
						alert("회원성명이 입력되지 않았습니다.");
						data.custname.focus();
						return false;
					}
					else if(!document.data.phone.value) {
						alert("전화번호가 입력되지 않았습니다.");
						data.phone.focus();
						return false;
					}
					else if (!document.data.address.value) {
						alert("주소를 입력하세요.");
						data.address.focus();
						return false;
					} 
		 		<!-- ==================== 생략 ==================== -->
```


```
<section class="section">
   	 <h2>홈쇼핑 회원 등록</h2><br>

<form name="data" action="join_p.jsp" method="post"  onsubmit="return checkValue()">
			<table class="table_line">
				<tr>
					<th>회원번호(자동발생)</th>
					<td><input type="text" name="custno" value="<%=num %>"  readonly ></td>
				</tr>
				<tr>
					<th>회원성명</th>
					<td><input type="text" name="custname" ></td>
				</tr>
				<!-- ==================== 생략 ==================== -->
				<tr class="center">
					<td  colspan="2" >
						<input type="submit" value="등록">
						<input type="button" value="취소"  onclick = "location.href='join.jsp'  "> 
						<!-- "location.href=는 현재 브라우저에 연결페이지 로딩 -->
						<input type="button" value="조회"  onclick = "location.href='member_list.jsp'  " >
				</tr>
			</table>
		</form>	
   	
 </section>
```

![취소버튼](https://github.com/junhee23314/school/blob/main/%EC%A0%95%EB%B3%B4%EC%B2%98%EB%A6%AC%EC%82%B0%EC%97%85%EA%B8%B0%EC%82%AC%EB%AC%B8%EC%A0%9C%EC%97%B0%EC%8A%B5/%EC%87%BC%ED%95%91%EB%AA%B0%20%ED%9A%8C%EC%9B%90%EA%B4%80%EB%A6%AC%20ver%201.0/1%EC%B0%A8%20%EC%98%AC%EB%A6%BC/img/%EC%B7%A8%EC%86%8C%EB%B2%84%ED%8A%BC.gif)

**`취소`버튼을 누르면 전체 초기화**

### ---join_p.jsp---
실제로 데이터베이스에 회원 정보를 삽입하는 코드입니다. insert 쿼리를 사용하여 데이터베이스에 회원 정보를 저장합니다.
요청(request)으로 받은 데이터를 파라미터로 받아와서 해당 값을 데이터베이스에 입력합니다.
삽입이 완료되면 member_list.jsp로 이동하여 회원 목록을 확인할 수 있도록 리디렉션합니다.

```
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import ="DB.DBConnect" %> <%-- 지시문형식을 통해 DB연결 자바파일 불러오기  --%>
<%@ page import = "java.sql.*" %>  <!-- 지시문형식을 통해 SQL 관련 라이브러리 불러오기  -->
 
 <%
          request.setCharacterEncoding("UTF-8"); // 오라클에 한글 입력 시 깨지지 않도록 셋팅
 
          String sql = "insert into member_tbl_02 values(?,?,?,?,?,?,?)"; 
          //sql 변수선언 후 쿼리문형태의 문자열 저장, 삽입쿼리문에 입력데이터 자리 7개 준비
          
          Connection conn = DBConnect.getConnection(); // DB 연결 기능을 객체변수 conn 에 저장 -> 1.DB연결
          PreparedStatement pstmt = conn.prepareStatement(sql);  // sql변수에 저장되어 있는 문장이 쿼리문이 됨 ->2.DB연결 후 쿼리문이 생성
          
        //    화면으로부터 사용자가 입력한 회원정보 데이터 7개를 쿼리문 각 자리에 셋팅    //
        
          pstmt.setInt(1, Integer.parseInt(request.getParameter("custno")));
          // 화면으로부터 읽어들인 데이터는 문자열이므로 숫자형으로 형변환 필요
          pstmt.setString(2,request.getParameter("custname"));  
          pstmt.setString(3, request.getParameter("phone"));
      	  pstmt.setString(4, request.getParameter("address"));
          pstmt.setString(5, request.getParameter("joindate"));
      	  pstmt.setString(6, request.getParameter("grade"));
      	  pstmt.setString(7, request.getParameter("city"));
      	 
      	  pstmt.executeUpdate();        // 쿼리문을 실행하고, 건별로 오라클 테이블에 누적
  %>    
```
**회원등록하기전 member_tbl_02 테이블**
![회원등록전](https://github.com/junhee23314/school/blob/main/%EC%A0%95%EB%B3%B4%EC%B2%98%EB%A6%AC%EC%82%B0%EC%97%85%EA%B8%B0%EC%82%AC%EB%AC%B8%EC%A0%9C%EC%97%B0%EC%8A%B5/%EC%87%BC%ED%95%91%EB%AA%B0%20%ED%9A%8C%EC%9B%90%EA%B4%80%EB%A6%AC%20ver%201.0/1%EC%B0%A8%20%EC%98%AC%EB%A6%BC/img/%ED%9A%8C%EC%9B%90%EB%93%B1%EB%A1%9D%20%EC%A0%84.png)

**회원등록!**
![회원등록](https://github.com/junhee23314/school/blob/main/%EC%A0%95%EB%B3%B4%EC%B2%98%EB%A6%AC%EC%82%B0%EC%97%85%EA%B8%B0%EC%82%AC%EB%AC%B8%EC%A0%9C%EC%97%B0%EC%8A%B5/%EC%87%BC%ED%95%91%EB%AA%B0%20%ED%9A%8C%EC%9B%90%EA%B4%80%EB%A6%AC%20ver%201.0/1%EC%B0%A8%20%EC%98%AC%EB%A6%BC/img/%ED%9A%8C%EC%9B%90%EB%93%B1%EB%A1%9D.gif)

**회원등록한 후 member_tbl_02 테이블**
![회원등록후](https://github.com/junhee23314/school/blob/main/%EC%A0%95%EB%B3%B4%EC%B2%98%EB%A6%AC%EC%82%B0%EC%97%85%EA%B8%B0%EC%82%AC%EB%AC%B8%EC%A0%9C%EC%97%B0%EC%8A%B5/%EC%87%BC%ED%95%91%EB%AA%B0%20%ED%9A%8C%EC%9B%90%EA%B4%80%EB%A6%AC%20ver%201.0/1%EC%B0%A8%20%EC%98%AC%EB%A6%BC/img/%ED%9A%8C%EC%9B%90%EB%93%B1%EB%A1%9D%20%ED%9B%84.png)

보이는 것 처럼 적었던 회원정보가 들어가 있는걸 볼수있습니다.

###---member_list.jsp---

SQL 쿼리 실행 및 데이터베이스 연결:
![]()

DBConnect 클래스의 getConnection() 메서드를 사용해 데이터베이스에 연결하고, <br>
SQL 쿼리를 실행하여 회원 정보를 조회합니다.
```
<%
    String sql = "SELECT custno, custname, phone, address, "
               + "TO_CHAR(joindate, 'YYYY-MM-DD') AS joindate, "
               + "CASE grade WHEN 'A' THEN 'VIP' WHEN 'B' THEN '일반' ELSE '직원' END AS grade, "
               + "city FROM member_tbl_02 ORDER BY custno";
    
    Connection conn = DBConnect.getConnection();
    
    PreparedStatement pstmt = conn.prepareStatement(sql);
    ResultSet rs = pstmt.executeQuery();
%>
```
ResultSet에서 데이터를 가져와 HTML 표에 표시하는 부분이 또 다른 중요한 부분입니다. while(rs.next()) 루프를 통해 쿼리 결과를 반복하며 각 회원의 정보를 출력합니다.

```
<%
    while(rs.next()) {
%>
<tr class="center">
    <td><%= rs.getString("custno") %></td>
    <td><%= rs.getString("custname") %></td>
    <!-- ========== 생략 ========== -->
</tr>
<%
    }
%>

```


