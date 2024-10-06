package Rpg;

public class Ork extends Monster{

	int 분노;
	
	public Ork(String 이름, int 체력, int 공격력) {
		super(이름, 체력, 공격력);
		분노 = 30;
	}
	
	@Override
	void 자기소개() {
		System.out.printf("몬스터 %s의 정보, 체력:%2d, 공격력:%2d 분노:%2d\n",
				이름, 체력, 공격력, 분노);
	}
	

	@Override
	void 공격() {
		System.out.println("공격");		
	}

}
