package sw0409;

import java.util.Scanner;

public class Calc찢기전 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("숫자 몇개?");
		int 배열크기 = sc.nextInt();
		
		int[] 배열 = new int[배열크기];
		int 결과 = 0;
		
		for(int i=0; i<배열.length; i++) {
			System.out.printf("배열[%d]: ", i);
			배열[i] = sc.nextInt();
		}
		
		for(int i=0; i<배열.length; i++) {
			System.out.print(배열[i]+"  ");
		}
		System.out.println();
		
		String 연산자;
		while(true) {
			System.out.print("연산자를 선택하세요 +, -, *");
			연산자 = sc.next();
			if(연산자.equals("+") || 연산자.equals("-") || 연산자.equals("*"))
				break;
		}
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
		System.out.printf("연산(%s) 결과: %d\n", 연산자, 결과);

	}

}
