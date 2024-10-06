package Rpg;

public class Warrior extends Hero{
	int 분노;
	
	public Warrior(String 이름, int 체력, int 공격력) {
		super(이름, 체력, 공격력);
		분노 = 100;
	}
	public int get분노() {
		return 분노;
	}
	public void set분노(int 분노) {
		this.분노 = 분노;
	}

	@Override
	void 자기소개() {
		System.out.printf("전사 %s의 정보, 체력:%2d, 공격력:%2d, 분노:%2d \n",
				이름, 체력, 공격력, 분노);
	}
	
	@Override
	void 공격(Monster 적) {
		super.공격(적);
		분노 += 20;
		System.out.printf("분노가 %2d 되었습니다.\n", 분노);
	}
	
	void 분노의일격(Monster 적) {
		if(분노 >= 100) {
			자기소개();
			적.자기소개();
			System.out.println("♨　♨　♨　♨　♨　♨　♨　♨　♨");
			System.out.println("　♨　♨　♨　♨　♨　♨　♨　♨　");
			System.out.printf("%s -> %s 분노의 일격!! ", 이름, 적.get이름());
			int 데미지 = 적.get체력()-(공격력*2);
			적.set체력(데미지);	
			System.out.printf("%s의 체력: %2d\n", 적.get이름(), 적.get체력());
			System.out.println("♨　♨　♨　♨　♨　♨　♨　♨　♨");
			System.out.println("　♨　♨　♨　♨　♨　♨　♨　♨　");
			분노 = 0;
		} else {
			System.out.println("분노가 부족하여 실패했습니다..");
		}
	}
	
}









