import static org.junit.Assert.*;
import org.junit.Test;

public class Testing {
    @Test
    public void test1(){
        assertEquals(2, Solution.solution("4"));
    }
    @Test
    public void test2(){
        assertEquals(5, Solution.solution("15"));
    }
}