package com.alex.subsetsum.dynamic;

public class Dynamic {

	public static boolean dynamicSearch(long[] set, long target) {
		boolean[][] solution = new boolean[set.length + 1][(int) (target + 1)];
		for (int i = 0; i <= set.length; i++) {
			solution[i][0] = true;
		}
		for (int i = 1; i < target; i++) {
			solution[0][i] = false;
		}
		for (int a = 1; a <= set.length; a++) {
			for (int b = 1; b <= target; b++) {
				solution[a][b] = solution[a - 1][b];
				if (solution[a][b] == false && b >= set[a - 1]) {
					solution[a][b] = solution[a - 1][(int) (b - set[a - 1])] || solution[a][b]; 
				}
			}
		}
		return solution[set.length][(int) target];
	}
	
}
