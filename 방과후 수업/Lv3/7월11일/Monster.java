package Rpg;

public abstract class Monster {
	String 이름;
	int 체력;
	int 공격력;
	
	public Monster(String 이름, int 체력, int 공격력) {
		this.이름 = 이름;
		this.체력 = 체력;
		this.공격력 = 공격력;
	}
	public String get이름() {
		return 이름;
	}
	public void set이름(String 이름) {
		this.이름 = 이름;
	}
	public int get체력() {
		return 체력;
	}
	public void set체력(int 체력) {
		this.체력 = 체력;
	}
	public int get공격력() {
		return 공격력;
	}
	public void set공격력(int 공격력) {
		this.공격력 = 공격력;
	}	
	
	void 자기소개() {
		System.out.printf("몬스터 %s의 정보, 체력:%2d, 공격력:%2d \n",
				이름, 체력, 공격력);
	}

	abstract void 공격();
	
	
}
