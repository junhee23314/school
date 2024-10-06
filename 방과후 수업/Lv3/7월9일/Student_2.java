package _0709HighSchool;

public class Student_2 extends Student_1{

	Student_2(int 학번, String 학과, String 이름) {
		super(학번, 학과, 이름);
		영교시 = false;
	}

	@Override
	protected void 자기소개(){
		super.자기소개();
		System.out.println("저는 2학년이라서 도제면접을 준비합니다.");
	}
	
	protected void 정산기() {
		System.out.println("정보처리 산업기사를 준비합니다.");
	} 
	
	
}
