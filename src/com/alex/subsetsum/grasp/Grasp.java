package com.alex.subsetsum.grasp;

import java.util.ArrayList;

public class Grasp {

	public static long graspSearch(long[] set, long target, int iterations) {
		ArrayList<Integer> selectedIndexes = new ArrayList<Integer>();
		long lastSum = -1;
		while (sum(set, selectedIndexes) < target) {
			if (selectedIndexes.size() == set.length) {
				return sum(set, selectedIndexes);
			} else {
				int newIndex;
				while (selectedIndexes.contains(newIndex = (int) (Math.random() * set.length)))
					;
				if (!(set[newIndex] + sum(set, selectedIndexes) > target)) {
					selectedIndexes.add(Integer.valueOf(newIndex));
				}
			}
			if (lastSum == sum(set, selectedIndexes))
				break;
			else
				lastSum = sum(set, selectedIndexes);
		}
		for (int i = 0; i < iterations; i++) {
			int replace = (int) (selectedIndexes.size() * Math.random());
			int with = -1;
			int difference = 1;
			while (with == -1) {
				if (replace + difference > set.length && replace - difference < 0) {
					break;
				} else if (!selectedIndexes.contains(replace + difference) && replace + difference < set.length) {
					with = replace + difference;
				} else if (!selectedIndexes.contains(replace - difference) && replace - difference >= 0) {
					with = replace - difference;
				} else {
					difference++;
				}
			}
			if (with != -1) {
				if (set[replace] < set[with] && sum(set, selectedIndexes) + set[with] <= target) {
					selectedIndexes.remove(Integer.valueOf(replace));
					selectedIndexes.add(Integer.valueOf(with));
				}
			}
		}
		return sum(set, selectedIndexes);
	}

	private static long sum(long[] set, ArrayList<Integer> indexes) {
		long returnVal = 0;
		for (int i : indexes) {
			returnVal += set[i];
		}
		return returnVal;
	}

}
