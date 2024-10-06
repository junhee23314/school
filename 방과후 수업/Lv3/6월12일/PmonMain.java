package sw0409;

public class PmonMain {

	public static void main(String[] args) {
		Qmon 멋쟁이 = new Qmon();
		Pmon 불량이 = new Pmon();
		
		멋쟁이.상태창();
		불량이.상태창();		
		
		불량이.몸통박치기(멋쟁이);
		
		멋쟁이.상태창();
		불량이.상태창();
		
		멋쟁이.몸통박치기(불량이);

		멋쟁이.상태창();
		불량이.상태창();
	}
}
