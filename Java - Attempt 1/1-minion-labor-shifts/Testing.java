import static org.junit.Assert.*;
import org.junit.AfterClass;
import org.junit.Test;

import java.util.Arrays;


public class Testing {
	private static int points = 0;

	@Test
	public void test1(){
		int[] input = {1, 2, 3};
		int[] output = {};
		System.out.println(Arrays.toString(Solution.solution(input, 0)));
		assertArrayEquals(Solution.solution(input, 0), output);
		points+=1;
	}
	@Test
	public void test2(){
		int[] input = {1, 2, 2, 3, 3, 3, 4, 5, 5};
		int[] output = {1,4};
		System.out.println(Arrays.toString(Solution.solution(input, 1)));
		assertArrayEquals(Solution.solution(input, 1), output);
		points+=1;
	}

	@AfterClass
	public static void testNothing(){
		System.out.println("Test cases passed (2 total): " + points);
	}
}