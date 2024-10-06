package _0727_test;

public class Water extends Poketmon{
	int 물공격력;
	
	Water(String 이름, int 체력, int 물공격력){
		super(이름, 체력);
		this.물공격력 = 물공격력;
		속성 = "물";
		공격력 = 공격력계산(물공격력);
	}
	
	@Override
	int 공격력계산(int 특수공격력){
		return 공격력+특수공격력;
	}
	
	@Override
	void 공격(Poketmon 적) {
		자기소개(); 적.자기소개();

		// 공격처리
		System.out.printf("%s -> %s 공격!\n", 이름, 적.이름);
		적.체력 = 적.체력 - 공격력;
		
		자기소개(); 적.자기소개();
	}
	
	@Override
	void 특수공격(Poketmon 적) {
		자기소개(); 적.자기소개();

		// 공격처리
		if(적.속성.equals("불")) {
			System.out.printf("∬∬∬∬∬∬∬∬∬∬∬∬∬∬∬∬∬∬∬∬∬∬∬∬∬∬∬∬∬∬\n");
			System.out.printf("%s -> %s 특수공격!!!\n", 이름, 적.이름);
			System.out.printf("%s는 %s 속성이라서 효과가 3배로 증가했다!\n",
								적.이름, 적.속성);
			System.out.printf("∬∬∬∬∬∬∬∬∬∬∬∬∬∬∬∬∬∬∬∬∬∬∬∬∬∬∬∬∬∬\n");
			적.체력 = 적.체력 - (공격력*3);
		} else {
			System.out.printf("%s -> %s 특수공격!!\n", 이름, 적.이름);
			적.체력 = 적.체력 - (공격력*2);
		}
		자기소개(); 적.자기소개();
	}
}





