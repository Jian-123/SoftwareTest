package calcMoney.edu.tju;
import org.junit.Test; //��Ӧע��
import static org.junit.Assert.*; //��Ӧassert����
public class CalcTest {
    private Calc c = new Calc();
    @Test
    public void TestMoney() {
    	assertEquals(true,c.Pay(11));
    	assertEquals(true,c.Pay(12));
    	assertEquals(true,c.Pay(13));
    	assertEquals(false,c.Pay(14));
    	assertEquals(false,c.Pay(66));
    }
}
