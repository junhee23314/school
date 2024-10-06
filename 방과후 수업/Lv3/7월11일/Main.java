package Rpg;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
//		Warrior h1 = new Warrior("택", 100, 20);
//		Mage m2 = new Mage("법사", 100, 20);
		Hero[] 히어로 = { 
					new Warrior("택", 100, 20),
					new Mage("법사", 100, 20)
				};
		Scanner sc = new Scanner(System.in);
		
		System.out.printf("직업을 선택하세요 > (1)전사  (2)법사");
		int 직업선택 = sc.nextInt();
		Hero 직업;
		
		if(직업선택==1) {
			직업 = 히어로[0];
		} else {
			직업 = 히어로[1];
		}
		
		Ork m1 = new Ork("스랄", 100, 15);
		
		m1.자기소개();

		
	}
}
