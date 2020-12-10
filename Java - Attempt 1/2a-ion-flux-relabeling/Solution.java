public class Solution {
	public static int helper(int n, int s){
		// Your code here
		int level = s, index = s;

		while (level > 0) {
			int leftIndex = index - (level + 1)/2;
			int rightIndex = index - 1;

			if (n == leftIndex || n == rightIndex) {
				return index;
			}

			index = (n < leftIndex ? leftIndex : rightIndex);
			level = (level -1)/2;
		}
		return -1;
	}
	public static int[] solution(int h, int[] q){
		int[] output = new int[q.length];
		int parent = (int)Math.pow(2, h)-1;
		for(int i = 0; i < q.length; i++){
			if(q[i] == parent)
				output[i] = -1;
			else
				output[i] = helper(q[i], parent);
		}
		return output;
	}
}
