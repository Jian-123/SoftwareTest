package calcMoney.edu.tju;

public class Calc {

	private int[] money = {50,20,5,5,1,1,1} ;
	
	public boolean Pay(int x) {
		int count=x;
        for(int i=0;i<money.length;i++) {
        	if(x>money[i]) {
        		count-=money[i];
        	}
        	if(count==0) break;
        }
		if(count!=0) return false; 
		else return true;
	}
	
}
