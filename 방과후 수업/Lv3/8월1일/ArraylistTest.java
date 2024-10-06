package _0801_ArrayList;

import java.util.ArrayList;

import _0731_RPG.Golem;
import _0731_RPG.Monster;
import _0731_RPG.Ork;

public class ArraylistTest {

	public static void main(String[] args) {
		
		ArrayList<Integer> 어레 = new ArrayList<Integer>();
		
		System.out.println(어레.size());
		어레.add(18);
		어레.add(28);
		어레.add(38);
		System.out.println(어레.size());
		
		for(int i=0; i<어레.size(); i++) {
			System.out.println(어레.get(i));
		}
		
		어레.remove(1);
		System.out.println();
		
		for(Integer i : 어레) {
			System.out.println(i);
		}
		
		ArrayList<Monster> 몬스터들 = new ArrayList<Monster>();

		몬스터들.add(new Ork("풀오크", 80, 12));
		몬스터들.add(new Golem("땅골렘", 100, 10));
		
		for(Monster m : 몬스터들) {
			m.자기소개();
			System.out.println();
		}
		
	}

}
