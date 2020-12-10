import static org.junit.Assert.*;
import org.junit.AfterClass;
import org.junit.Test;

public class Testing {
	@Test
	public void test1() {
		assertEquals("4208", Solution.solution("77"));
	}
	@Test
	public void test2(){
		assertEquals("19", Solution.solution("5"));
	}
	@Test
	public void test3() {
		assertEquals("1639", Solution.solution("48"));
	}
	@Test
	public void test4(){
		assertEquals("3479", Solution.solution("70"));
	}
	@Test
	public void test5() {
		assertEquals("4656", Solution.solution("81"));
	}
	@Test
	public void test6(){
		assertEquals("6951", Solution.solution("99"));
	}
	@Test
	public void test7() {
		assertEquals("5370", Solution.solution("87"));
	}
	@Test
	public void test8(){
		assertEquals("347", Solution.solution("22"));
	}
}