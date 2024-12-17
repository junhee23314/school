
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="css/style.css">
<title>검사결과입력</title>
<script type="text/javascript">
	function checkValue(){
		if(!document.data.p_no.value){
			alert("환자번호가 입력되지 않았습니다!");
			data.p_no.focus();
			return false;
		} else if(!document.data.t_name.value){
			alert("검사코드가 선택되지 않았습니다!");
			data.t_name.focus();
			return false;
			
		}  else if(!document.data.t_sdate.value){
			alert("검사일자가 입력되지 않았습니다!");
			data.t_sdate.focus();
			return false;
			
		} else if(!document.data.t_result.value){
			alert("검사상태가 선택되지 않았습니다!");
			data.t_result.focus();
			return false;
			
		} else if(!document.data.t_ldate.value){
			alert("검사완료일자가 입력되지 않았습니다!");
			data.t_ldate.focus();
			return false;
			
		} else if(!document.data.p_result.value){
			alert("검사결과가 선택되지 않았습니다!");
			data.p_result.focus();
			return false;
			
		} 
		
		alert("검사결과가 정상적으로 등록 되었습니다!");
		return true;
		
		
		
		
	}
	
	function rewrite() {
		alert("정보를 지우고 처음부터 다시 입력 합니다!");
		location.href = "etresults.jsp";
		vote_data.p_no.focus();
	}





</script>




</head>
<body>
	
	<jsp:include page="layout/header.jsp"></jsp:include>
	<jsp:include page="layout/nav.jsp"></jsp:include>
	

	<section>
	<h2>검사결과입력</h2>
	<form action="etresults_p.jsp" method = "post" name = "data" onsubmit = "return checkValue()">	
		<table>
			<tr>
				<th>환자번호</th>
				<td style="text-align: left;"><input type="text" name="p_no"> 예)1001 </td>
			</tr>
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
			
			<tr>
				<th>검사시작일자</th>
				<td style="text-align: left;"><input type="text" name="t_sdate"> 예)20200101 </td>
			</tr>
			
			<tr>
	<th>검사상태</th>
	<td style="text-align: left;">
		<input type="radio"  name="t_status" value="1">검사중
		<input type="radio"  name="t_status" value="2">검사완료
	</td>
</tr>
			<tr>
				<th>검사완료일자</th>
				<td style="text-align: left;"><input type="text" name="t_ldate"> 예)20200101 </td>
			</tr>
			<tr>
			<th>검사상태</th>
		<td style="text-align: left;">
			<input type="radio" name="t_result" value="X">미입력
			<input type="radio" name="t_result" value="P">양성
			<input type="radio" name="t_result" value="N">음성
			
		</td>
			
			</tr>
		<tr>
		<th colspan="2">
			<input type="submit" value="검사결과등록" >
			<input type="button" value="다시쓰기" onclick="rewrite() ">
		</th>
		</tr>
		</table>
	</form>
	</section>
	
	<jsp:include page="layout/footer.jsp"></jsp:include>
	

</body>
</html>