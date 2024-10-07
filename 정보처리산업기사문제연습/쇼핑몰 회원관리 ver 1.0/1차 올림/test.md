<details><summary> 회원등록
</summary>

*Write here!*
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
