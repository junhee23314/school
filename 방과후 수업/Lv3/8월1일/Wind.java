package _0731_RPG;

public interface Wind {
	// static final
	int 바람공격력 = 40;
	
	// abstract
	static void 바람공격(Monster 적){
		적.체력 = 적.체력 - 바람공격력;
	}
}
