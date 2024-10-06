package _0731_RPG;

public class Mage extends Hero{
	int 마나;
	
	Mage(String 이름, int 체력, int 공격력){
		super(이름, 체력, 공격력);
		공격력 = 공격력계산();
		마나 = 30;
		속성 = "물";
	}
	
	int 공격력계산(){
		return 공격력+5;
	}
	
	@Override
	void 자기소개() {
		super.자기소개();
		System.out.printf("마나:%d|속성:%s \n", 마나, 속성);
	}

	@Override
	void 공격(Monster 적) {
		자기소개(); 적.자기소개();
		
		System.out.printf("%s → %s 공격!", 이름, 적.이름);
		적.체력 = 적.체력 - 공격력;
		마나 = 마나 + 15;
		
		자기소개(); 적.자기소개();		
	}

	@Override
	void 특수공격(Monster 적) {
		자기소개(); 적.자기소개();
		int 특수공격력;
		
		if(마나 >= 100) {
			특수공격력 = 공격력*3;
			마나 = 0;
		} else {
			특수공격력 = 공격력;
		}
		
		if(적.속성.equals("불")) {
			System.out.printf("불을 꺼트려 효과가 굉장했다!\n");
			System.err.printf("💦💦💦💦💦💦💦💦💦💦💦💦💦\n");
			System.out.printf("%s → %s 특수공격!", 이름, 적.이름);
			적.체력 = 적.체력 - (특수공격력*3);
		} else {
			System.out.printf("특수공격 실패ㅠㅡㅠ");
		}
		
		자기소개(); 적.자기소개();	
	}
}









