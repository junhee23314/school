package _0727_test;

public abstract class Poketmon {
	String 이름;
	int 체력;
	int 공격력;
	String 속성;
	
	Poketmon(String 이름, int 체력){
		this.이름 = 이름;
		this.체력 = 체력;
		공격력 = 5;
	}
	
	void 자기소개(){
		System.out.printf("이름:%s | 체력:%d | 속성:%s\n",
							이름, 체력, 속성);
	}

	abstract void 공격(Poketmon 적);
	abstract void 특수공격(Poketmon 적);
	abstract int 공격력계산(int 특수공격력);
	
}
