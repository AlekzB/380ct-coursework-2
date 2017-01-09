package com.alex.subsetsum.exhaustive;

import java.util.ArrayList;

public class Exhaustive {
	
	public static long exhaustiveSearch(long[] set, long target) {
		ArrayList<Long> results = new ArrayList<Long>();
		results.add((long) 0);
		for (long x : set) {
			ArrayList<Long> lesserSums = new ArrayList<Long>();
			for (long y : results) {
				long test = x + y;
				if (test <= target) {
					lesserSums.add(test);
				}
			}
			for (long y : lesserSums) {
				boolean test = false;
				for (long z : results) {
					if (y == z) {
						test = true;
						break;
					}
				}
				if (!test) {
					results.add(y);
				}
			}
			ArrayList<Long> finalResults = new ArrayList<Long>();
			finalResults.add((long) 0);
			for (long y : results) {
				if (y > finalResults.get(finalResults.size() - 1)) {
					finalResults.add(y);
				}
			}
			results = finalResults;
		}
		return results.get(results.size() - 1);
	}
	
}
