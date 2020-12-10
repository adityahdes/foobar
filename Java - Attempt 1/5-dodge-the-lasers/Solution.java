import java.math.BigInteger;
import java.math.BigDecimal;
import java.lang.Math;

public class Solution {
	static final BigInteger ZERO = BigInteger.valueOf(0);
	static final BigInteger ONE = BigInteger.valueOf(1);
	static final BigInteger TWO = BigInteger.valueOf(2);
	static final double PRIME_COEFF = Math.sqrt(2) -1;
	public static String solution(String s) {
		BigInteger n = new BigInteger(s);
		if (n.compareTo(ZERO) == 0) {
			return "0";
		}
		else if (n.compareTo(ONE) == 0){
			return "1";
		}
		else if (n.compareTo(TWO) == 0){
			return "3";
		}
		else {
			//BigDecimal temp = new BigDecimal(n);
			//temp = temp.multiply(PRIME_COEFF);
			//BigInteger n_prime = temp.toBigInteger();
			BigInteger n_prime = BigInteger.valueOf((long) Math.floor((PRIME_COEFF) * n.doubleValue()));
			BigInteger t1 = n.multiply(n_prime);
			BigInteger t2 = n.add(ONE);
			t2 = t2.multiply(n);
			t2 = t2.divide(TWO);
			BigInteger t3 = n_prime.add(ONE);
			t3 = t3.multiply(n_prime);
			t3 = t3.divide(TWO);
			BigInteger out = t1.add(t2);
			out = out.subtract(t3);
			BigInteger recur = new BigInteger(solution(n_prime.toString()));
			out = out.subtract(recur);
			return out.toString();
		}
	}
}