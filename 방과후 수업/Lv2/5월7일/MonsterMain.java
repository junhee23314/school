package sw0409;

public class MonsterMain {

	public static void main(String[] args) {
		Monster m1 = new Monster();
		Monster m2 = new Monster(50, 30, "골렘");
		Monster m3 = new Monster("오크");
		int num = 18;
		
		System.out.println(m1.체력);
		System.out.println(m1.get체력());
		m1.set체력(200);
		m1.set체력(300);
	
		
		m1.포효();
		
		m3.달리기(100);
		
		
	}

}
