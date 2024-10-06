package _0731_RPG;

public class Ork extends Monster{

	int 분노;
	
	public Ork(String 이름, int 체력, int 공격력) {
		super(이름, 체력, 공격력);
		분노 = 60;
		속성 = "풀";
	}
	
	@Override
	void 자기소개() {
		super.자기소개();
		System.out.printf("분노:%d|속성:%s", 분노, 속성);
	}
	
	void 공격(Hero 적){
		자기소개(); 적.자기소개();
		
		int 특수공격력;
		
		분노 = 분노 + 10;
		
		if(분노>=100) {
			System.err.printf("%s가 화났다!!", 이름);
			특수공격력 = 공격력*2;
			분노=0;
		} else {
			특수공격력 = 공격력;
		}
		
		if(적.속성.equals("물")) {
			System.err.printf("물속성에게 효과가 굉장했다!\n");
			System.out.printf("%s → %s 특수공격!", 이름, 적.이름);
			적.체력 = 적.체력 - (특수공격력*2);
		} else {
			System.out.printf("%s → %s 공격!", 이름, 적.이름);
			적.체력 = 적.체력 - 특수공격력;
		}
		
	}
	
	
	
	
	
}
