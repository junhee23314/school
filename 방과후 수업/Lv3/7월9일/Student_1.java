package _0709HighSchool;

public class Student_1 extends Student{

	protected boolean 영교시;
	
	Student_1(int 학번, String 학과, String 이름) {
		super(학번, 학과, 이름);
		영교시 = true;
	}

	@Override
	protected void 자기소개() {
		super.자기소개();
		if(영교시==true)
		System.out.printf("저는 1학년이라서 0교시를 합니다!^^\n");
	}
	
	protected void 자격증() {
		System.out.println("저는 ITQ 한글, 엑셀, 파포, 컴활 자격증을 취득했습니다.");
	}
	
}
