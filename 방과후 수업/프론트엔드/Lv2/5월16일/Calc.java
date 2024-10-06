package sw0409;

public class Calc {
	// 필드(변수)
	private int 결과;
	int[] 배열;
	String 연산자;
	
	// 생성자
	Calc(int 배열크기, String 연산자){
		배열 = new int[배열크기];
		this.연산자 = 연산자;
	}

	// getter, setter
	public int[] get배열() {
		return 배열;
	}

	public void set배열(int[] 배열) {
		this.배열 = 배열;
	}
	
	// 메소드(함수, 기능)
	int 연산작업() {
		결과 = 0;
		switch (연산자) {
		case "+":
			for(int i=0; i<배열.length; i++) {
				결과+=배열[i];
			}
			break;
		case "-":
			for(int i=0; i<배열.length; i++) {
				결과-=배열[i];
			}
			break;
		case "*":
			결과 = 1;
			for(int i=0; i<배열.length; i++) {
				결과*=배열[i];
			}
			break;
		default:
			System.out.println("다시 입력하세요.");
			break;
		}
		return 결과;
	}
	
}
