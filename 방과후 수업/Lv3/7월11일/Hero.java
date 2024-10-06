package Rpg;

public class Hero {
	String 이름;
	int 체력;
	int 공격력;
	
	public Hero(String 이름, int 체력, int 공격력) {
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
		System.out.printf("영웅 %s의 정보, 체력:%2d, 공격력:%2d \n",
												이름, 체력, 공격력);		
	}
	
	void 공격(Monster 적) {
		자기소개();
		적.자기소개();
		System.out.println("********************************");
		System.out.printf("%s -> %s 공격 ", 이름, 적.get이름());
		int 데미지 = 적.get체력()-공격력;
		적.set체력(데미지);	
		System.out.printf("%s의 체력: %2d\n", 적.get이름(), 적.get체력());
		System.out.println("********************************");
	}
	
}