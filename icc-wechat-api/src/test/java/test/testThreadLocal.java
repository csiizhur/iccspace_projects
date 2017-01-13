package test;
public class testThreadLocal {

	public static void main(String[] args) {
		for(int i=0;i<2;i++){
			Thread t=new Thread();
			t.run();
		}
	}
}
