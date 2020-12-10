import java.math.BigDecimal;
import java.math.BigInteger;
public class Solution {
    /* The way I went about solving this problem is to look at the binary representation of each number.
     * What we are trying to do is get to a point where we are left with the 0b1, and our only operations are to
     * either add one, subtract one, or do a binary shift right. If the value ends in zero, it is even and we should
     * shift right to divide by 2. If it's odd, to determine whether we should add one or subtract one, we look at the
     * efficiency of the two possible values. To determine efficiency, we look at how many trailing zeros are there. The
     * number of trailing zeros represents how many times we can shift right (divide by 2) before we have to decide
     * whether we add or subtract by 1 again. So if we have the number 19, in binary that is 10011. If we add 1 to get
     * 20, we end up with 10100, subtracting 1 to get 18 gives us 10010. 20 has two trailing zeros compared to 18 having
     * only one. This means that 20 is more efficient, as we can go from 19 to 1 in 7 steps. 19 -> 20 -> 10 -> 5 -> 4 ->
     * 2 -> 1.
     */
    private BigDecimal max = new BigDecimal("1.0E+310");
    private BigInteger maxval = max.toBigInteger();
    public static int solution(String x) {
        if(x.equals("0"))
            return 1;
        if(x.equals("1"))
            return 0;
        BigInteger num = new BigInteger(x);
        int count = 0;
        while(! num.equals(BigInteger.ONE)){
            if(num.getLowestSetBit() != 0){
                count++;
                num = num.shiftRight(1);
            }
            else{
                int plus = num.add(BigInteger.ONE).getLowestSetBit();
                int minus = num.subtract(BigInteger.ONE).getLowestSetBit();
                if(minus > plus || num.equals(new BigInteger("3"))){
                    num = num.subtract(BigInteger.ONE);
                }
                else{
                    num = num.add(BigInteger.ONE);
                }
                count++;
            }
        }
        return count;
    }
}