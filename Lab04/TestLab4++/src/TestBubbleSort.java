import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

public class TestBubbleSort {
	@Test
	public void testBubbleSort() {
		int[] a = {2 ,2,4,21,-7,0,-9};
		int[] b = BubbleSort.BubbleSort(a);
		int[] c = {-9,-7,0,2,2,4,21};
		assertArrayEquals(b,c);
	}

}
