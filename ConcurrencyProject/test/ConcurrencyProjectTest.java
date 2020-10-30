

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import application.AddArray;


class ConcurrencyProjectTest {
	private int[] randArray = new int[200];
	private int arrayTotal;
	@BeforeEach
	void setUp() throws Exception {
//		System.out.println("Array Size " + randArray.length);
		for (int a = 0; a < randArray.length; a++) {
			randArray[a] = (int) ((Math.random() * 9) + 1);
		}
		for (int a = 0; a < randArray.length; a++) {
			arrayTotal += randArray[a];
		}
		System.out.println("arrayTotal " + arrayTotal);
		
	}

	@Test
	@DisplayName("Concurrency Project Test")
	void test() {
		assertEquals(arrayTotal, AddArray.arraySumWithTreads(randArray, 1));
		assertEquals(arrayTotal, AddArray.arraySumWithTreads(randArray, 2));
		assertEquals(arrayTotal, AddArray.arraySumWithTreads(randArray, 3));
		assertEquals(arrayTotal, AddArray.arraySumWithTreads(randArray, 4));
		assertEquals(arrayTotal, AddArray.arraySumWithTreads(randArray, 5));
		assertEquals(arrayTotal, AddArray.arraySumWithTreads(randArray, 6));
			
	}
	@ParameterizedTest
	@ValueSource(ints = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17})
	void testParameterized(int i) {
		assertEquals(arrayTotal, AddArray.arraySumWithTreads(randArray, i));
		assertTrue(200000000/i > AddArray.timeTestThreads(randArray, i));
	}
}






