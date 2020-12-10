import java.math.BigInteger;

/* For this solution, I am treating this problem as one of finding the number of partitions of an integer n into
 * distinct parts. This is a combinatorics problem that can be solved using generating functions.
 * For any integer n, in order to solve how many distinct partitions we can create of that integer, we need to
 * create a generating function of the form
 * Series Product from 1 to n of (1 + x^i). When the resulting product is expanded, the coefficient on the term x^n is equivalent
 * to the number of distinct partitions. Which is the same as the number of steps + 1. (as distinct partitions
 * includes a partition of size one, which we can just subtract off)
 * For example: for n=5, (1+x)(1+x^2)(1+x^3)(1+x^4)(1+x^5) = x^15 + x^14 + x^13 + 2 x^12 + 2 x^11 + 3 x^10 +
 * 3 x^9 + 3 x^8 + 3 x^7 + 3 x^6 + 3 x^5 + 2 x^4 + 2 x^3 + x^2 + x + 1
 * The coefficient on x^5 is 3. There are 3 distinct partitions for 5, (1,4)(2,3)(5). We can't use 5 as a valid
 * staircase for Dr. Lambda, so we subtract 1 from the answer that this generating function gives us to get 2
 * possible staircases.
 *
 * I looked at Princeton's Polynomial class and used that as inspiration for my approach on solving this problem.
 */
public class Solution {

    public static int solution(int n){
        Polynomial poly = new Polynomial();
        for(int i = 2; i <= n; i ++){
            Polynomial temp = new Polynomial(i);
            poly = poly.multiply(temp);
        }
        return poly.coef[n] -1;
    }
    public static class Polynomial {
        private int[] coef;   // coefficients p(x) = sum { coef[i] * x^i }
        private int degree;   // degree of polynomial (-1 for the zero polynomial)

        /**
         * Initializes a new polynomial a x^b
         *
         * @param a the leading coefficient
         * @param b the exponent
         * @throws IllegalArgumentException if {@code b} is negative
         */
        public Polynomial(int a, int b) {
            coef = new int[b + 1];
            coef[b] = a;
            reduce();
        }

        /**
         * Initializes a new polynomial 1 + x^a
         */
        public Polynomial(int a) {
            coef = new int[a + 1];
            coef[0] = 1;
            coef[a] = 1;
            reduce();
        }

        public Polynomial(){
            coef = new int[2];
            coef[0] = 1;
            coef[1] = 1;
            reduce();
        }

        // pre-compute the degree of the polynomial, in case of leading zero coefficients
        // (that is, the length of the array need not relate to the degree of the polynomial)
        private void reduce() {
            degree = -1;
            for (int i = coef.length - 1; i >= 0; i--) {
                if (coef[i] != 0) {
                    degree = i;
                    return;
                }
            }
        }

        public Polynomial multiply(Polynomial that) {
            Polynomial product = new Polynomial(0, this.degree + that.degree);
            for (int i = 0; i <= this.degree; i++)
                for (int j = 0; j <= that.degree; j++)
                    product.coef[i + j] += (this.coef[i] * that.coef[j]);
            product.reduce();
            return product;
        }
    }
}
