package _0731_RPG;

public class Warrior extends Hero{
	int 분노;
	
	Warrior(String 이름, int 체력, int 공격력){
		super(이름, 체력, 공격력);
		공격력 = 공격력계산();
		분노 = 30;
		속성 = "불";
	}
	
	int 공격력계산(){
		return 공격력+10;
	}
	
	@Override
	void 자기소개() {
		super.자기소개();
		System.out.printf("분노:%d|속성:%s \n", 분노, 속성);
	}

	@Override
	void 공격(Monster 적) {
		자기소개(); 적.자기소개();
		
		System.out.printf("%s → %s 공격!\n", 이름, 적.이름);
		적.체력 = 적.체력 - 공격력;
		분노 = 분노 + 10;
		
		자기소개(); 적.자기소개();		
	}

	@Override
	void 특수공격(Monster 적) {
		자기소개(); 적.자기소개();
		int 특수공격력;
		
		if(분노 >= 100) {
			특수공격력 = 공격력*2;
			분노 = 0;
		} else {
			특수공격력 = 공격력;
		}
		
		if(적.속성.equals("풀")) {
			System.out.printf("풀에 불이 붙어 효과가 굉장했다!\n");
			System.err.printf("💥💥💥💥💥💥💥💥💥💥💥💥\n");
			System.out.printf("%s → %s 특수공격!\n", 이름, 적.이름);
			적.체력 = 적.체력 - (특수공격력*3);
		} else {
			System.out.printf("%s → %s 특수공격!\n", 이름, 적.이름);
			적.체력 = 적.체력 - (특수공격력*2);
		}
		
		자기소개(); 적.자기소개();	
	}
}









