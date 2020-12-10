import java.util.ArrayList;
import java.util.HashMap;
public class Solution {
	public static int[] solution(int[] data, int n) {
		// Your code here
		if(n > data.length)
			return data;
		if(n == 0)
			return new int[0];
		HashMap<Integer, Integer> frequency = new HashMap<>();
		for(int i : data){
			if((frequency.containsKey(i))) {
				int temp = frequency.get(i);
				frequency.replace(i, temp + 1);
			}
			else{
				frequency.put(i, 1);
			}
		}
		ArrayList<Integer> result = new ArrayList<>();
		for(int i : data){
			if(frequency.get(i) <= n){
				result.add(i);
			}
		}
		return result.stream().mapToInt(i -> i).toArray();
	}
}