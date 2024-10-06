package _0709HighSchool;

public class Student_3 extends Student_2{
	boolean 취업여부 = true;
	
	Student_3(int 학번, String 학과, String 이름) {
		super(학번, 학과, 이름);
		취업여부 = !취업여부;
	}
	
	@Override
	protected void 자기소개() {
		super.자기소개();
		if(취업여부==false) {
			System.out.println("저는 대학갈래요");
		}
	}
	
	protected void 취업() {
		System.out.println("저 취업했어요!!");
		취업여부 = true;
	}
	
}
