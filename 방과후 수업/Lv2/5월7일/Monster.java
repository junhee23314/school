package sw0409;

public class Monster {
	int 체력;
	float 공격력;
	String 이름;
	
	
	public int get체력() {
		return 체력;
	}

	public void set체력(int 체력) {
		this.체력 = 체력;
	}

	void 포효() {
		System.out.printf("%s이(가) 포효합니다. 크아앙~\n", 이름);
	}


	void 달리기() {
		달리기(1);
	}
	
	void 달리기(int 횟수) {
		for(int i=1; i<=횟수; i++) {			
			체력--;
			System.out.printf("%s이(가) 뜁니다. 체력이 %d가 되었습니다.\n",
					이름, 체력);
			if(체력<=0){
				System.out.printf("%s가 죽었습니다.\n", 이름);
			}
		}
		
	}

	
//		체력 = 100;
//		공격력 = 15.1f;
//		이름 = "엘프";
	// 생성자 오버 로딩
	Monster(){
		this(100, 15.1f, "엘프");
	}

	Monster(String 이름){
		this(100, 15.1f, 이름);
	}

	Monster(int 체력, float 공격력, String 이름) {
		this.체력 = 체력;
		this.공격력 = 공격력;
		this.이름 = 이름;
	}

}
