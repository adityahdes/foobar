import static org.junit.Assert.*;
import org.junit.AfterClass;
import org.junit.Test;
import java.util.Arrays;


public class Testing {
	private static int points = 0;

	@Test
	public void test1(){
		int[] arr = {19, 14, 28};
		int[] out = {21, 15, 29};
		System.out.println(Arrays.toString(Solution.solution(5, arr)));
		assertArrayEquals(Solution.solution(5, arr),out);
		points +=1;
	}
	@Test
	public void test2(){
		int[] arr = {7, 3, 5, 1};
		int[] out = {-1, 7, 6, 3};
		System.out.println(Arrays.toString(Solution.solution(3, arr)));
		assertArrayEquals(Solution.solution(3, arr),out);
		points +=1;
	}

	@AfterClass
	public static void testNothing(){
		System.out.println("Test cases passed (2 total): " + points);
	}
}

