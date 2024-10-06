package _0731_RPG;

public interface Fire {
	// static final
	int 불공격력 = 40;
	
	// abstract
	static void 불공격(Monster 적){
		적.체력 = 적.체력 - 불공격력;
	}
	void 불쇼();
}
