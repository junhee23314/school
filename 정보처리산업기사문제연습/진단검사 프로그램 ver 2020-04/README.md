# 진단검사 프로그램 ver 2020-04

![enter image description here](https://github.com/junhee23314/school/blob/main/%EC%A0%95%EB%B3%B4%EC%B2%98%EB%A6%AC%EC%82%B0%EC%97%85%EA%B8%B0%EC%82%AC%EB%AC%B8%EC%A0%9C%EC%97%B0%EC%8A%B5/%EC%A7%84%EB%8B%A8%EA%B2%80%EC%82%AC%20%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%A8%20ver%202020-04/%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7%202024-12-18%20020058.png)

이 프로그램은 검가결과입력과 결과조회, 지역별검사건수를 확인할 수 있는 프로그램이다.


## 핵심코드

#### 환자조회

    String sql="select p_no,p_name,substr(p_birth,1,4)||'년'||substr(p_birth,5,2)||'월'||substr(p_birth,7,2)||'일' as p_birth,"
	+ " case p_gender when 'M' then '남' when 'F' then '여' end as p_gender,"
	+ " p_tel1||'-'||p_tel2||'-'||p_tel3 as p_tel,"
	+ " case p_city when '10' then '서울' when '20' then '경기' when '30' then '강원' when '40' then '대구' end as p_city"
	+ " from TBL_PATIENT_202004";


> substr(p_birth,1,4)||'년'||substr(p_birth,5,2)||'월'||substr(p_birth,7,2)||'일' as p_birth
> **substr**으로 **p_birth**을 가져온 다음 **||** 연결연산자 사용하여 
> 출력형식인 ***'yyyy년mm월dd일 '*** 형식으로 출력함.

> case p_gender when 'M' then '남' when 'F' then '여' end as p_gender
> **case문**을 이용하여 **M : '남' , F : '여'** 라는 내용을 출력함.

> p_tel1||'-'||p_tel2||'-'||p_tel3 as p_tel
> 테이블에 있는 tel1,2,3 를 불러온 다음 || 연결연산자로 내용출력.

> case p_city when '10' then '서울' when '20' then '경기' when '30' then '강원' when '40' then '대구' end as p_city
> 위 case문과 동일한 방법으로 출력.

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

> **`<%while(rs.next()){ %>`**

 `rs`는 **`ResultSet` 객체**로, 데이터베이스 쿼리 결과.
`rs.next()` 메서드는 **다음 행(row)** 으로 이동하며, 데이터가 있으면 
`true`, 없으면 `false`로  반환.
따라서 이 `while` 는 쿼리 결과에 있는 모든 행을 반복.

> **`<%=rs.getString(1) %>`**

`ResultSet` 객체에서 첫 번째 열의 데이터를 가져옴.
 `getString('')` 메서드는 데이터베이스 열 값을 문자열로 반환.
 숫자(1~6)는 데이터베이스 쿼리에서 선택된 열의 순서를 나타남.

#### 검사결과입력

    function checkValue(){
		if(!document.data.p_no.value){
			alert("환자번호가 입력되지 않았습니다!");
			data.p_no.focus();
			return false;
		} else if(!document.data.t_name.value){
			alert("검사코드가 선택되지 않았습니다!");
			data.t_name.focus();
			return false;
			---------생략---------
			} 
			alert("검사결과가 정상적으로 등록 되었습니다!");
			return true;
			} 
		function reset() {
		alert("정보를 지우고 처음부터 다시 입력 합니다!");
		location.href = "etresults.jsp";
		vote_data.p_no.focus();
	}

> script언어를 사용하여, 유효성검사를 하는 코드이다.
> 만약 칸이 입력하지 않았다면 해당 알림을 띄우고 해당 칸 focus() false가 됨.
> 등록됐다면 해당 알림을 띄우고 true로 전환.

> 만약 다시쓰기 버튼을 누를 경우 reset으로 해당 페이지 다시 실행시켜 지움.
> 후에 첫 번째 focus().

    <tr>
				<th>검사코드</th>
				<td style="text-align: left;"><select name="t_name" >
					<option value="" selected disabled>검사명</option>
					<option value="T001">[T001]결핵</option>
					<option value="T002">[T002]장티푸스</option>
					<option value="T003">[T003]수두</option>
					<option value="T004">[T004]홍역</option>
					<option value="T005">[T005]콜레라</option>
				</select> 
			</td>
			</tr>
			-------생략--------
			<th>검사상태</th>
		<td style="text-align: left;">
			<input type="radio"  name="t_status" value="1">검사중
			<input type="radio"  name="t_status" value="2">검사완료
		</td>

> 해당 칸은 위에서 콤보박스와 라디오하는 방법이다. 콤보 박스 첫 번째 칸에서 selected disabled를 줌으로써 선택되어있고 비활성화을 시킴.


	/*상세 페이지*/
	request.setCharacterEncoding("UTF-8");
	
	String sql = "insert into tbl_result_202004 values(?,?,?,?,?,?)";
	
	Connection conn = DBConnect.getConnection();
	PreparedStatement pstmt = conn.prepareStatement(sql);
	
	pstmt.setString(1,request.getParameter("p_no"));
	pstmt.setString(2,request.getParameter("t_name"));
	pstmt.setString(3,request.getParameter("t_sdate"));
	pstmt.setString(4,request.getParameter("t_status"));
	pstmt.setString(5,request.getParameter("t_ldate"));
	pstmt.setString(6,request.getParameter("t_result"));
	
	pstmt.executeUpdate();

>  **`request.setCharacterEncoding("UTF-8");`**
> 
> HTTP 요청(Request) 데이터를 **UTF-8** 인코딩 방식으로 처리하도록 설정합니다.
> 


> `String sql = "insert into tbl_result_202004 values(?,?,?,?,?,?)";` 
> 
> 이 SQL 쿼리는 데이터베이스 테이블 `tbl_result_202004`에 데이터를 삽입하는 데 사용됩니다.
>    **`values(?,?,?,?,?,?)`**:
>     `?`는 **파라미터 플레이스홀더**로, 나중에 실제 값으로 대체됩니다.
> 

> `Connection conn = DBConnect.getConnection();` 
> 데이터베이스와의 연결을 설정합니다.
> 
> `PreparedStatement pstmt = conn.prepareStatement(sql);` 
>  `PreparedStatement`는 미리 작성된 SQL 쿼리를 실행하기 위한 객체입니다.

> 

    pstmt.setString(1,request.getParameter("p_no"));
    pstmt.setString(2,request.getParameter("t_name"));
    ----생략----

> 클라이언트에서 요청받은 데이터(`request.getParameter`)를 가져와 SQL 쿼리의 파라미터에 설정합니다.






#### 지역별검사건수

    String sql= "select patient.p_no, patient.p_name,"
    	+ " test.t_name, to_char(result.t_sdate, 'YYYY-MM-DD') as t_sdate,  "
    	+ " case result.t_status  when '1' then '검사중' when '2' then '검사완료' end as t_status, "
    	+ " to_char(result.t_ldate, 'YYYY-MM-DD') as t_ldate, "
    	+ " case result.t_result when 'X' then '미입력' when 'P' then '양성'  when 'N' then '음성' end as t_result  "
    	+ " from tbl_patient_202004 patient,  tbl_lab_test_202004 test,  tbl_result_202004 result "
    	+ " where patient.p_no = result.p_no  and result.t_code = test.t_code";

> to_char(result.t_sdate, 'YYYY-MM-DD') as t_sdate
> to_char(result.t_ldate, 'YYYY-MM-DD') as t_ldate
> 
> to_char 함수를 이용해 형식을 변환 시켜줌.
> 
> case result.t_status  when '1' then '검사중' when '2' then '검사완료' end as
> t_status
> case result.t_result when 'X' then '미입력' when 'P' then '양성' when 'N' then '음성' end as t_result
>  case문으로  '1': 검사중, '2': 검사완료 형식을 출력.
> 
> where patient.p_no = result.p_no and result.t_code = test.t_code
> 두가지에 테이블을 연결시켜줌 (조인)


#### (지역별)검사건수통계

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

> case tp.p_city  when '10' then '서울'
> when '20' then '경기' when '30' then '강원' when '40' then '대구' end as p_city_name
> case문으로 정해진 코드에 맞는 지역명을 출력
> count(tp.p_city) as p_city_count
> 지역별로 검사건수가 몇 개 있었는지를 count로 세준다.
> from TBL_PATIENT_202004 tp,tbl_result_202004 tr where tp.p_no = tr.p_no group by 					p_city order by p_city asc;
> 테이블 끼리 조인해주고 

>group by p_city
>데이터를 특정 열 p_city 값을 기준으로 그룹화
> 
> 
>  order by p_city asc
> ORDER BY는 오름차순으로 데이터를 정렬합니다.






	

