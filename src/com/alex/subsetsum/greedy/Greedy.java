package com.alex.subsetsum.greedy;

import java.util.Arrays;

public class Greedy {

	public static long greedySearch(long[] set, long target) {
		long s = 0;
		Arrays.sort(set);
		for (int i = 0; i < set.length; i++) {
			if (s + set[i] < target) {
				s += set[i];
			} else {
				break;
			}
		}
		return s;
	}
	
}
