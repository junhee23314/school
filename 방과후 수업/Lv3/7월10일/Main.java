package Rpg;

public class Main {

	public static void main(String[] args) {
		Warrior h1 = new Warrior("택", 100, 20);
		Monster m1 = new Monster("몬스터", 100, 15);
		
		h1.분노의일격(m1);
		
	}
}
