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


---




---


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
![회원등록](https://github.com/junhee23314/school/blob/main/%EC%A0%95%EB%B3%B4%EC%B2%98%EB%A6%AC%EC%82%B0%EC%97%85%EA%B8%B0%EC%82%AC%EB%AC%B8%EC%A0%9C%EC%97%B0%EC%8A%B5/%EC%87%BC%ED%95%91%EB%AA%B0%20%ED%9A%8C%EC%9B%90%EA%B4%80%EB%A6%AC%20ver%201.0/1%EC%B0%A8%20%EC%98%AC%EB%A6%BC/img/%ED%9A%8C%EC%9B%90%EB%93%B1%EB%A1%9D!.gif)

**회원등록한 후 member_tbl_02 테이블**
![회원등록후](https://github.com/junhee23314/school/blob/main/%EC%A0%95%EB%B3%B4%EC%B2%98%EB%A6%AC%EC%82%B0%EC%97%85%EA%B8%B0%EC%82%AC%EB%AC%B8%EC%A0%9C%EC%97%B0%EC%8A%B5/%EC%87%BC%ED%95%91%EB%AA%B0%20%ED%9A%8C%EC%9B%90%EA%B4%80%EB%A6%AC%20ver%201.0/1%EC%B0%A8%20%EC%98%AC%EB%A6%BC/img/%ED%9A%8C%EC%9B%90%EB%93%B1%EB%A1%9D%20%ED%9B%84.png)

보이는 것 처럼 적었던 회원정보가 들어가 있는걸 볼수있습니다.

</details>



<details><summary> 회원목록조회/수정
</summary>

### ---member_list.jsp---

![](https://github.com/junhee23314/school/blob/main/%EC%A0%95%EB%B3%B4%EC%B2%98%EB%A6%AC%EC%82%B0%EC%97%85%EA%B8%B0%EC%82%AC%EB%AC%B8%EC%A0%9C%EC%97%B0%EC%8A%B5/%EC%87%BC%ED%95%91%EB%AA%B0%20%ED%9A%8C%EC%9B%90%EA%B4%80%EB%A6%AC%20ver%201.0/1%EC%B0%A8%20%EC%98%AC%EB%A6%BC/img/member_list%20%ED%99%94%EB%A9%B4.png)

---

DBConnect 클래스의 getConnection() 메서드를 사용해 데이터베이스에 연결하고, <br>
SQL 쿼리를 실행하여 회원 정보를 조회합니다. (member_search_list.jsp, sales_list.jsp 포함)
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

### ---update.jsp---

유효성 검사: JavaScript를 사용하여 입력된 값이 유효한지 확인하는 u_checkValue() 함수를 정의하여, 모든 필드가 채워져 있는지 확인합니다. 필드가 비어있으면 경고 메시지를 표시합니다.
```
<script type="text/javascript">
    function u_checkValue(){
        if(!document.u_data.custname.value) {
            alert("회원성명이 입력되지 않았습니다.");
            u_data.custname.focus();
            return false;
        }
        else if(!document.u_data.phone.value) {
            alert("전화번호가 입력되지 않았습니다.");
            u_data.phone.focus();
            return false;
        }
        // 나머지 필드들에 대해서도 유사한 방식으로 체크
        alert("회원정보수정이 완료 되었습니다.");
        return true;
    }
</script>

```

<br>
삭제 기능: 회원 정보를 삭제할 수 있는 기능도 포함되어 있으며, 사용자가 삭제를 확인하는 checkDel() 함수를 통해 삭제 여부를 묻습니다.

```
function checkDel(f_custno) {
    msg = "정말로 삭제하시겠습니까?";
    if(confirm(msg) != 0) {
        alert("삭제되었습니다.");
        location.href = "delete.jsp?d_custno=" + f_custno;
    } else {
        alert("삭제가 취소되었습니다.");
    }
}

```

수정된 정보를 update_p.jsp로 전송하여 데이터베이스에 업데이트합니다.

### ---update_p.jsp---

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

### ---member_search.jsp---


{동영상} <br>
checkValue2() 자바스크립트 함수는 사용자가 회원 번호를 입력했는지 확인하고, 입력값이 없을 경우 경고 메시지를 표시하며 폼 제출을 막습니다.
```
<script type="text/javascript">
    function checkValue2() {
        if (!document.data1.in_custno.value) { // 회원 번호 입력값이 없을 경우
            alert("회원번호를 입력하세요."); // 경고 메시지
            document.data1.in_custno.focus(); // 입력창으로 포커스 이동
            return false; // 폼 제출 중단
        }
        return true; // 입력값이 있으면 폼 제출 진행
    }
</script>
</head>
```

회원 번호가 입력되면, 입력값을 POST 방식으로 `member_search_list.jsp`로 전송합니다.
`member_search_list.jsp`에서 해당 회원 번호에 대한 데이터를 조회하여 화면에 출력하게 됩니다.

### ---member_search_list.jsp---
![](https://github.com/junhee23314/school/blob/main/%EC%A0%95%EB%B3%B4%EC%B2%98%EB%A6%AC%EC%82%B0%EC%97%85%EA%B8%B0%EC%82%AC%EB%AC%B8%EC%A0%9C%EC%97%B0%EC%8A%B5/%EC%87%BC%ED%95%91%EB%AA%B0%20%ED%9A%8C%EC%9B%90%EA%B4%80%EB%A6%AC%20ver%201.0/1%EC%B0%A8%20%EC%98%AC%EB%A6%BC/img/member_search_list%20%ED%99%94%EB%A9%B4.png)

---
request.getParameter("in_custno")를 통해 웹 요청에서 고객 번호(in_custno)를 가져옵니다.
```
 String in_custno = request.getParameter("in_custno");
```
데이터베이스에서 조회한 결과(ResultSet)를 HTML 표 형식으로 출력하는 부분입니다. rs.next() 루프를 통해 데이터를 반복적으로 읽어와 각 열에 출력합니다.
```
<%
    if (rs.next()) { 
%>
    <section class="section">
        <table class="table_line">
            <tr>
                <th>회원번호</th>
                <th>회원성명</th>
                <!--생략-->
            </tr>
            <tr>
                <td><%= rs.getString("custno") %></td>
                <td><%= rs.getString("custname") %></td>
                <!--===== 생략 =====-->
            </tr>
            <tr>
                <td colspan="7" align="center">
                    <input type="button" value="홈으로" onclick="location.href='index.jsp'">
                </td>
            </tr>
        </table>
    </section>
<%
    } else { 
%>
    <p align="center">회원번호 <%= in_custno %>의 회원 정보가 없습니다.</p>
    <p align="center"><input type="button" value="다시조회" onclick="location.href='member_search.jsp'"></p>
<%
    } 
%>

```
이 부분은 회원 정보를 표 형태로 보여주거나, 회원 정보가 없을 경우 메시지를 출력합니다.

**회원정보조회 성공!**
![](https://github.com/junhee23314/school/blob/main/%EC%A0%95%EB%B3%B4%EC%B2%98%EB%A6%AC%EC%82%B0%EC%97%85%EA%B8%B0%EC%82%AC%EB%AC%B8%EC%A0%9C%EC%97%B0%EC%8A%B5/%EC%87%BC%ED%95%91%EB%AA%B0%20%ED%9A%8C%EC%9B%90%EA%B4%80%EB%A6%AC%20ver%201.0/1%EC%B0%A8%20%EC%98%AC%EB%A6%BC/img/%ED%9A%8C%EC%9B%90%EC%A1%B0%ED%9A%8C.gif)

![](https://github.com/junhee23314/school/blob/main/%EC%A0%95%EB%B3%B4%EC%B2%98%EB%A6%AC%EC%82%B0%EC%97%85%EA%B8%B0%EC%82%AC%EB%AC%B8%EC%A0%9C%EC%97%B0%EC%8A%B5/%EC%87%BC%ED%95%91%EB%AA%B0%20%ED%9A%8C%EC%9B%90%EA%B4%80%EB%A6%AC%20ver%201.0/1%EC%B0%A8%20%EC%98%AC%EB%A6%BC/img/100001%20%ED%9A%8C%EC%9B%90.png)

위 보이는 사진처럼 `100001`인 회원정보가 출력된 걸 볼 수 있다.

</details>



<details><summary>회원대출조회
</summary>

### ---sales_list.jsp---

![](https://github.com/junhee23314/school/blob/main/%EC%A0%95%EB%B3%B4%EC%B2%98%EB%A6%AC%EC%82%B0%EC%97%85%EA%B8%B0%EC%82%AC%EB%AC%B8%EC%A0%9C%EC%97%B0%EC%8A%B5/%EC%87%BC%ED%95%91%EB%AA%B0%20%ED%9A%8C%EC%9B%90%EA%B4%80%EB%A6%AC%20ver%201.0/1%EC%B0%A8%20%EC%98%AC%EB%A6%BC/img/sales_list%20%ED%99%94%EB%A9%B4.png)

---

while (rs.next()) 루프를 통해 ResultSet에서 각 행의 데이터를 가져와 테이블의 각 행에 출력합니다.<br>
각 열은 rs.getString() 메서드를 사용하여 고객 번호, 성명, 등급, 매출 가격을 가져옵니다.<br>
매출 총합을 계산하기 위해 i 변수를 사용합니다. 루프를 돌면서 각 고객의 매출을 i에 더합니다.<br>
루프가 끝난 후, 총합을 보여주는 행을 추가하여 사용자에게 전체 매출 합계를 제공합니다.<br>
```
 <% int i = 0;
           while (rs.next()) { %>
        <tr class="center">
            <td><%= rs.getString("custno") %></td>
            <td><%= rs.getString("custname") %></td>
            <td><%= rs.getString("grade") %></td>
            <td><%= rs.getString("price") %></td>
        </tr>

        <% i += Integer.parseInt(rs.getString("price"));
           } %>
        <tr class="center">
            <td colspan="3">총합</td>
            <td><%= i %></td>
        </tr>
```



회원 정보 조회: 데이터베이스에서 회원 번호, 이름, 등급, 매출 정보를 조회합니다.

매출 데이터 표시: 조회된 데이터를 표 형태로 웹 페이지에 표시하여 사용자가 쉽게 확인할 수 있도록 합니다.

총 매출 합산: 모든 회원의 매출을 합산하여 총합을 마지막에 보여줍니다.
</details>








<!--
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
![회원등록](https://github.com/junhee23314/school/blob/main/%EC%A0%95%EB%B3%B4%EC%B2%98%EB%A6%AC%EC%82%B0%EC%97%85%EA%B8%B0%EC%82%AC%EB%AC%B8%EC%A0%9C%EC%97%B0%EC%8A%B5/%EC%87%BC%ED%95%91%EB%AA%B0%20%ED%9A%8C%EC%9B%90%EA%B4%80%EB%A6%AC%20ver%201.0/1%EC%B0%A8%20%EC%98%AC%EB%A6%BC/img/%ED%9A%8C%EC%9B%90%EB%93%B1%EB%A1%9D!.gif)

**회원등록한 후 member_tbl_02 테이블**
![회원등록후](https://github.com/junhee23314/school/blob/main/%EC%A0%95%EB%B3%B4%EC%B2%98%EB%A6%AC%EC%82%B0%EC%97%85%EA%B8%B0%EC%82%AC%EB%AC%B8%EC%A0%9C%EC%97%B0%EC%8A%B5/%EC%87%BC%ED%95%91%EB%AA%B0%20%ED%9A%8C%EC%9B%90%EA%B4%80%EB%A6%AC%20ver%201.0/1%EC%B0%A8%20%EC%98%AC%EB%A6%BC/img/%ED%9A%8C%EC%9B%90%EB%93%B1%EB%A1%9D%20%ED%9B%84.png)

보이는 것 처럼 적었던 회원정보가 들어가 있는걸 볼수있습니다.

### ---member_list.jsp---

![](https://github.com/junhee23314/school/blob/main/%EC%A0%95%EB%B3%B4%EC%B2%98%EB%A6%AC%EC%82%B0%EC%97%85%EA%B8%B0%EC%82%AC%EB%AC%B8%EC%A0%9C%EC%97%B0%EC%8A%B5/%EC%87%BC%ED%95%91%EB%AA%B0%20%ED%9A%8C%EC%9B%90%EA%B4%80%EB%A6%AC%20ver%201.0/1%EC%B0%A8%20%EC%98%AC%EB%A6%BC/img/member_list%20%ED%99%94%EB%A9%B4.png)

---

DBConnect 클래스의 getConnection() 메서드를 사용해 데이터베이스에 연결하고, <br>
SQL 쿼리를 실행하여 회원 정보를 조회합니다. (member_search_list.jsp, sales_list.jsp 포함)
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
### ---member_search_list.jsp---
![](https://github.com/junhee23314/school/blob/main/%EC%A0%95%EB%B3%B4%EC%B2%98%EB%A6%AC%EC%82%B0%EC%97%85%EA%B8%B0%EC%82%AC%EB%AC%B8%EC%A0%9C%EC%97%B0%EC%8A%B5/%EC%87%BC%ED%95%91%EB%AA%B0%20%ED%9A%8C%EC%9B%90%EA%B4%80%EB%A6%AC%20ver%201.0/1%EC%B0%A8%20%EC%98%AC%EB%A6%BC/img/member_search_list%20%ED%99%94%EB%A9%B4.png)

---

request.getParameter("in_custno")를 통해 웹 요청에서 고객 번호(in_custno)를 가져옵니다.
```
 String in_custno = request.getParameter("in_custno");
```
데이터베이스에서 조회한 결과(ResultSet)를 HTML 표 형식으로 출력하는 부분입니다. rs.next() 루프를 통해 데이터를 반복적으로 읽어와 각 열에 출력합니다.
```
<%
    if (rs.next()) { 
%>
    <section class="section">
        <table class="table_line">
            <tr>
                <th>회원번호</th>
                <th>회원성명</th>
                <!--생략-->
            </tr>
            <tr>
                <td><%= rs.getString("custno") %></td>
                <td><%= rs.getString("custname") %></td>
                <!--===== 생략 =====-->
            </tr>
            <tr>
                <td colspan="7" align="center">
                    <input type="button" value="홈으로" onclick="location.href='index.jsp'">
                </td>
            </tr>
        </table>
    </section>
<%
    } else { 
%>
    <p align="center">회원번호 <%= in_custno %>의 회원 정보가 없습니다.</p>
    <p align="center"><input type="button" value="다시조회" onclick="location.href='member_search.jsp'"></p>
<%
    } 
%>

```
이 부분은 회원 정보를 표 형태로 보여주거나, 회원 정보가 없을 경우 메시지를 출력합니다.

**회원정보조회 성공!**
![](https://github.com/junhee23314/school/blob/main/%EC%A0%95%EB%B3%B4%EC%B2%98%EB%A6%AC%EC%82%B0%EC%97%85%EA%B8%B0%EC%82%AC%EB%AC%B8%EC%A0%9C%EC%97%B0%EC%8A%B5/%EC%87%BC%ED%95%91%EB%AA%B0%20%ED%9A%8C%EC%9B%90%EA%B4%80%EB%A6%AC%20ver%201.0/1%EC%B0%A8%20%EC%98%AC%EB%A6%BC/img/%ED%9A%8C%EC%9B%90%EC%A1%B0%ED%9A%8C.gif)

![](https://github.com/junhee23314/school/blob/main/%EC%A0%95%EB%B3%B4%EC%B2%98%EB%A6%AC%EC%82%B0%EC%97%85%EA%B8%B0%EC%82%AC%EB%AC%B8%EC%A0%9C%EC%97%B0%EC%8A%B5/%EC%87%BC%ED%95%91%EB%AA%B0%20%ED%9A%8C%EC%9B%90%EA%B4%80%EB%A6%AC%20ver%201.0/1%EC%B0%A8%20%EC%98%AC%EB%A6%BC/img/100001%20%ED%9A%8C%EC%9B%90.png)

위 보이는 사진처럼 `100001`인 회원정보가 출력된 걸 볼 수 있다.

### ---sales_list.jsp---

![](https://github.com/junhee23314/school/blob/main/%EC%A0%95%EB%B3%B4%EC%B2%98%EB%A6%AC%EC%82%B0%EC%97%85%EA%B8%B0%EC%82%AC%EB%AC%B8%EC%A0%9C%EC%97%B0%EC%8A%B5/%EC%87%BC%ED%95%91%EB%AA%B0%20%ED%9A%8C%EC%9B%90%EA%B4%80%EB%A6%AC%20ver%201.0/1%EC%B0%A8%20%EC%98%AC%EB%A6%BC/img/sales_list%20%ED%99%94%EB%A9%B4.png)

---

while (rs.next()) 루프를 통해 ResultSet에서 각 행의 데이터를 가져와 테이블의 각 행에 출력합니다.<br>
각 열은 rs.getString() 메서드를 사용하여 고객 번호, 성명, 등급, 매출 가격을 가져옵니다.<br>
매출 총합을 계산하기 위해 i 변수를 사용합니다. 루프를 돌면서 각 고객의 매출을 i에 더합니다.<br>
루프가 끝난 후, 총합을 보여주는 행을 추가하여 사용자에게 전체 매출 합계를 제공합니다.<br>
```
 <% int i = 0;
           while (rs.next()) { %>
        <tr class="center">
            <td><%= rs.getString("custno") %></td>
            <td><%= rs.getString("custname") %></td>
            <td><%= rs.getString("grade") %></td>
            <td><%= rs.getString("price") %></td>
        </tr>

        <% i += Integer.parseInt(rs.getString("price"));
           } %>
        <tr class="center">
            <td colspan="3">총합</td>
            <td><%= i %></td>
        </tr>
```

-->
