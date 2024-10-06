package _0709HighSchool;

public class Main {

	public static void main(String[] args) {
//		Student 학생 = new Student(21110, "SW개발과", "박태성");
//		Student_1 학생1 = new Student_1(11111, "SW개발과", "김승민");
//		Student_2 학생2 = new Student_2(21110, "SW개발과", "곽권");
//		Student_3 학생3 = new Student_3(30900, "스마트과", "양민석");
		
		Student[] 학생들 = {
							new Student_1(11111, "SW개발과", "박태성"),
							new Student_2(21110, "SW개발과", "김승민"),
							new Student_3(30900, "스마트과", "양민석")
						};
		
		for( Student i : 학생들 ) {
			i.자기소개();
			
		}
		
		
	}

}
