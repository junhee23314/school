# 쇼핑물 회원관리 ver 1.0

![시작화면](https://github.com/junhee23314/school/blob/main/%EC%A0%95%EB%B3%B4%EC%B2%98%EB%A6%AC%EC%82%B0%EC%97%85%EA%B8%B0%EC%82%AC%EB%AC%B8%EC%A0%9C%EC%97%B0%EC%8A%B5/%EC%87%BC%ED%95%91%EB%AA%B0%20%ED%9A%8C%EC%9B%90%EA%B4%80%EB%A6%AC%20ver%201.0/1%EC%B0%A8%20%EC%98%AC%EB%A6%BC/img/%EC%8B%9C%EC%9E%91%ED%99%94%EB%A9%B4.png)

정보처리산업기사 문제연습입니다.<br>
이 웹페이지는 회원등록 및 회원,매출 조회를 할 수 있는 웹페이지 입니다.

## 핵심내용💡

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





<details><summary> 회원등록
</summary>

![회원등록 화면](https://github.com/junhee23314/school/blob/main/%EC%A0%95%EB%B3%B4%EC%B2%98%EB%A6%AC%EC%82%B0%EC%97%85%EA%B8%B0%EC%82%AC%EB%AC%B8%EC%A0%9C%EC%97%B0%EC%8A%B5/%EC%87%BC%ED%95%91%EB%AA%B0%20%ED%9A%8C%EC%9B%90%EA%B4%80%EB%A6%AC%20ver%201.0/1%EC%B0%A8%20%EC%98%AC%EB%A6%BC/img/%ED%9A%8C%EC%9B%90%EB%93%B1%EB%A1%9D%20%ED%99%94%EB%A9%B4.png)
회원 가입 페이지로 보입니다. 

### ---join.jsp---
데이터베이스 연결(DBConnect)와 SQL 쿼리를 사용해 회원 번호를 조회하는 부분이 포함되어 있습니다. 
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



</details>



<details><summary> 회원목록조회/수정
</summary>

update.jsp

HTML 폼:

조회된 회원 정보를 수정할 수 있는 입력 필드를 제공합니다.
회원 번호는 읽기 전용으로 설정되어 있으며, 나머지 필드는 사용자가 수정할 수 있습니다.

유효성 검사: JavaScript를 사용하여 입력된 값이 유효한지 확인하는 u_checkValue() 함수를 정의하여, 모든 필드가 채워져 있는지 확인합니다. 필드가 비어있으면 경고 메시지를 표시합니다.

삭제 기능: 회원 정보를 삭제할 수 있는 기능도 포함되어 있으며, 사용자가 삭제를 확인하는 checkDel() 함수를 통해 삭제 여부를 묻습니다.

폼 제출: 수정된 정보를 update_p.jsp로 전송하여 데이터베이스에 업데이트합니다.

update_p.jsp

파라미터 설정: 사용자가 입력한 정보를 기반으로 각 필드를 업데이트하기 위해 pstmt.setString() 메서드를 사용하여 값들을 설정합니다.

업데이트 실행: pstmt.executeUpdate()를 호출하여 데이터베이스의 회원 정보를 실제로 업데이트합니다.

페이지 전환: 업데이트가 완료된 후, member_list.jsp 페이지로 포워딩하여 업데이트된 회원 목록을 표시합니다.

delete.jsp

SQL 삭제 쿼리: member_tbl_02 테이블에서 특정 회원(custno)의 정보를 삭제하는 SQL 쿼리를 구성합니다. 삭제할 회원 번호는 요청 파라미터(d_custno)에서 가져옵니다.

데이터베이스 연결: DBConnect 클래스를 통해 데이터베이스에 연결하고, PreparedStatement를 사용하여 SQL 쿼리를 안전하게 실행합니다.

삭제 실행: pstmt.executeUpdate()를 호출하여 해당 회원 정보를 데이터베이스에서 삭제합니다.
</details>



<details><summary> 회원정보조회
</summary>


</details>



<details><summary>회원대출조회
</summary>

회원 정보 조회: 데이터베이스에서 회원 번호, 이름, 등급, 매출 정보를 조회합니다.

매출 데이터 표시: 조회된 데이터를 표 형태로 웹 페이지에 표시하여 사용자가 쉽게 확인할 수 있도록 합니다.

총 매출 합산: 모든 회원의 매출을 합산하여 총합을 마지막에 보여줍니다.
</details>
