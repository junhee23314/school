package _0731_RPG;

import java.util.Random;
import java.util.Scanner;

public class RpgMain {

	public static void main(String[] args) {
		Hero[] 히어로들 = new Hero[2];
//		Warrior(String 이름, int 체력, int 공격력)
		// 다형성의 특징!
		히어로들[0] = new Warrior("불전사", 100, 10);
		히어로들[1] = new Mage("물법사", 80, 7);
//		for(int i=0; i<히어로들.length; i++)
//		for(Hero h : 히어로들) {
//			h.자기소개();
//		}

		// 	몬스터 객체배열 만들기
		Monster[] 몬스터들 = new Monster[2];
		//	몬스터 초기화 작업 하기
		몬스터들[0] = new Ork("풀오크", 80, 12);
		몬스터들[1] = new Golem("땅골렘", 100, 10);
		
		//	랜덤으로 몬스터 한마리 뽑기
		Random r = new Random();
		Monster 나타난몬스터 = 몬스터들[r.nextInt(2)]; 
		System.out.printf("%s 가 나타났다!\n", 나타난몬스터.이름);
		나타난몬스터.자기소개();
		
		Scanner sc = new Scanner(System.in);
		System.out.printf("영웅을 선택하세요. (1)불전사 (2)물법사 >  ");
		int 영웅선택 = sc.nextInt()-1;
		Hero 영웅 = 히어로들[영웅선택];
		
		System.out.printf("%s 영웅 등장!\n", 영웅.이름);
		영웅.자기소개();
		
		System.out.printf("%s, %s 전투 시작! \n", 영웅.이름, 나타난몬스터.이름);
		boolean 진행 = true;
		
		while(진행) {
			// 영웅 행동
			System.out.printf("%s 턴, (1)공격 | (2)특수공격 | (3)도망치기 >  ", 영웅.이름);
			int 행동선택 = sc.nextInt();
			
			switch(행동선택) {
			case 1:
				System.out.printf("%s가 %s를 공격!\n", 영웅.이름, 나타난몬스터.이름);
				영웅.공격(나타난몬스터);
				break;
			case 2:
				System.out.printf("%s가 %s를 특수공격!!\n", 영웅.이름, 나타난몬스터.이름);
				영웅.특수공격(나타난몬스터);
				break;
			default:
				System.out.printf("%s가 도망쳤습니다..\n", 영웅.이름);
				진행 = false;
			}
			
			// 몬스터가 죽었나?
			if(나타난몬스터.체력 <= 0) {
				나타난몬스터.죽기();
				System.out.printf("%s 승리\n", 영웅.이름);
				진행 = false;
			}
			// 영웅이 죽거나 도망쳤다면 종료
			if(진행==false) break;
			
			// 몬스터 행동
			나타난몬스터.공격(영웅);
			if(영웅.체력 <= 0) {
				영웅.죽기();
				System.out.printf("%s 승리.. \n", 나타난몬스터.이름);
				진행 = false;
			}
		}
		
	}

}
