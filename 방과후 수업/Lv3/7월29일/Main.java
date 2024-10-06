package _0727_test;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Poketmon[] 포켓몬들 = new Poketmon[2];
		
		포켓몬들[0] = new Fire("파이리", 100, 10);
		포켓몬들[1] = new Water("꼬부기", 100, 10);
		
		Poketmon 나 = 포켓몬들[0];
		Poketmon 적 = 포켓몬들[1];
		
		boolean 진행 = true;
		
		while(진행) {
			진행 = 행동(나, 적, 진행);
			
			if(진행==false) break;
			
			진행 = 행동(적, 나, 진행);
		}
	}
	
	static boolean 행동(Poketmon 나, Poketmon 적, boolean 진행) {
		Scanner sc = new Scanner(System.in);
		System.out.printf("%s의 행동을 선택하세요! ", 나.이름);
		System.out.printf("(1)자기소개 | (2)공격 | (3)특수공격 | (4)도망가기 > \n");
		int 선택 = sc.nextInt(); 
		
		switch(선택) {
		case 1:
			나.자기소개();
			break;
		case 2:
			나.공격(적);
			break;
		case 3:
			나.특수공격(적);
			break;
		default:
			System.out.printf("%s가 도망쳤다.", 나.이름);
			진행 = false;
		}
		return 진행;		
	}
	

}








