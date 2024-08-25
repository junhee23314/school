## 쇼핑물 회원관리 ver 1.0 (1차)

![시작화면](https://github.com/junhee23314/school/blob/main/%EC%A0%95%EB%B3%B4%EC%B2%98%EB%A6%AC%EC%82%B0%EC%97%85%EA%B8%B0%EC%82%AC%EB%AC%B8%EC%A0%9C%EC%97%B0%EC%8A%B5/%EC%87%BC%ED%95%91%EB%AA%B0%20%ED%9A%8C%EC%9B%90%EA%B4%80%EB%A6%AC%20ver%201.0/1%EC%B0%A8%20%EC%98%AC%EB%A6%BC/img/%EC%8B%9C%EC%9E%91%ED%99%94%EB%A9%B4.png)

정보처리산업기사 문제연습입니다.

### 핵심내용

##### ---DB연결---

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


##### ---join.jsp---
![회원등록 화면](https://github.com/junhee23314/school/blob/main/%EC%A0%95%EB%B3%B4%EC%B2%98%EB%A6%AC%EC%82%B0%EC%97%85%EA%B8%B0%EC%82%AC%EB%AC%B8%EC%A0%9C%EC%97%B0%EC%8A%B5/%EC%87%BC%ED%95%91%EB%AA%B0%20%ED%9A%8C%EC%9B%90%EA%B4%80%EB%A6%AC%20ver%201.0/1%EC%B0%A8%20%EC%98%AC%EB%A6%BC/img/%ED%9A%8C%EC%9B%90%EB%93%B1%EB%A1%9D%20%ED%99%94%EB%A9%B4.png)
회원 가입 페이지로 보입니다. 데이터베이스 연결(DBConnect)와 SQL 쿼리를 사용해 회원 번호를 조회하는 부분이 포함되어 있습니다. 

JavaScript를 사용하여 폼의 입력값을 검증하는 기능을 추가했습니다. 예를 들어, 회원 성명, 전화번호 등이 입력되지 않으면 알림 메시지를 띄웁니다.

[](https://github.com/user-attachments/assets/10bc949d-c4e1-439f-8390-6ba9b89c00f8)

submit 버튼을 클릭하여 회원 정보를 데이터베이스에 저장하는 다음 페이지(join_p.jsp)로 전송하는 형식입니다.

##### ---join_p.jsp---
실제로 데이터베이스에 회원 정보를 삽입하는 코드입니다. insert 쿼리를 사용하여 데이터베이스에 회원 정보를 저장합니다.

요청(request)으로 받은 데이터를 파라미터로 받아와서 해당 값을 데이터베이스에 입력합니다.

삽입이 완료되면 member_list.jsp로 이동하여 회원 목록을 확인할 수 있도록 리디렉션합니다.
