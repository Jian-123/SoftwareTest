import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

public class TestBackPack {
	@Test
	public void testBackPack() {
		int m = 10;
        int n = 3;
        int w[] = {3, 4, 5};
        int p[] = {4, 5, 6};
        int[][] a = BackPack.BackPack_Solution(m, n, w, p);
        int[][] b = {
        		{0,0,0,0,0,0,0,0,0,0,0},
        		{0,0,0,4,4,4,4,4,4,4,4},
        		{0,0,0,4,5,5,5,9,9,9,9},
        		{0,0,0,4,5,6,6,9,10,11,11},		
        };
        assertArrayEquals(a,b);
        
	}
}
