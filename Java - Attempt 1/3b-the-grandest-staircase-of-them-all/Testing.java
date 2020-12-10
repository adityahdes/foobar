import static org.junit.Assert.*;
import org.junit.Test;

public class Testing {
    @Test
    public void test1(){
        assertEquals(1, Solution.solution(3));
    }
    @Test
    public void test2(){
        assertEquals(487067745, Solution.solution(200));
    }
    @Test
    public void test3(){
        assertEquals(19406015, Solution.solution(150));
    }
}
