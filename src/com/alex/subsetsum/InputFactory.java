package com.alex.subsetsum;

import java.util.ArrayList;

public class InputFactory {

	public static long getTarget(long max) {
		return (long) (Math.random() * max);
	}
	
	public static long[] getRandomLengthSet(long maxVal) {
		ArrayList<Long> set = new ArrayList<Long>();
		while (Math.random() > 0.01) {
			set.add((long) (Math.random() * maxVal));
		}
		return fromArrayList(set);
	}
	
	public static long[] getFixedLengthSet(int length, long maxVal) {
		ArrayList<Long> set = new ArrayList<Long>();
		for (int i = 0; i < length; i++) {
			set.add((long) (Math.random() * maxVal));
		}
		return fromArrayList(set);
	}
	
	private static long[] fromArrayList(ArrayList<Long> list) {
		long[] returnVal = new long[list.size()];
		for (int i = 0; i < list.size(); i++) {
			returnVal[i] = list.get(i);
		}
		return returnVal;
	}
	
}
