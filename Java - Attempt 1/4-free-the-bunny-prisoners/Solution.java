import java.util.ArrayList;
import java.util.List;

public class Solution {
	public static int[][] solution(int num_buns, int num_required) {

		ArrayList<int[]> combos = genCombinations(num_buns, num_buns - num_required + 1);
		ArrayList<ArrayList<Integer>> bunny_list = new ArrayList<>(num_buns);

		for (int b = 0; b < num_buns; b++) {
			ArrayList<Integer> keyList = new ArrayList<Integer>();
			bunny_list.add(b, keyList);
		}

		int key = 0;
		for (int j = 0; j < combos.size(); j++) {
			int[] arr = combos.get(j);
			for (int k = 0; k < arr.length; k++) {
				bunny_list.get(arr[k]).add(key);
			}
			key++;
		}

		return bunny_list.stream().map(u -> u.stream().mapToInt(i -> i).toArray()).toArray(int[][]::new);
	}

	public static ArrayList<int[]> genCombinations(int num_buns, int num_required) {
		ArrayList<int[]> combo_list = new ArrayList<>();
		int[] combo = new int[num_required];
		for (int i = 0; i < num_required; i++) {
			combo[i] = i;
		}
		while (combo[num_required - 1] < num_buns) {
			combo_list.add(combo.clone());
			int j = num_required - 1;
			while (j != 0 && combo[j] == num_buns - num_required + j) {
				j--;
			}
			combo[j]++;
			for (int i = j + 1; i < num_required; i++) {
				combo[i] = combo[i - 1] + 1;
			}
		}
		return combo_list;
	}
}

