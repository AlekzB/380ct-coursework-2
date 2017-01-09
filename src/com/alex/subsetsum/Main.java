package com.alex.subsetsum;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.alex.subsetsum.dynamic.Dynamic;
import com.alex.subsetsum.exhaustive.Exhaustive;
import com.alex.subsetsum.genetic.Genetic;
import com.alex.subsetsum.grasp.Grasp;
import com.alex.subsetsum.greedy.Greedy;

public class Main {

	public static void main(String[] args) {
		for (int i = 2; i <= 20; i++) {
			System.out.println("Generating set...");
			long[] set = InputFactory.getRandomLengthSet(2000);
			set = InputFactory.getFixedLengthSet(i, 800);
			System.out.println("Set of length " + set.length + " has been generated.");
			System.out.print("Generating target: ");
			long target = InputFactory.getTarget(500) + 500;
			System.out.println(target);
			System.out.println();
			runExhaustive(set, target);
			runDynamic(set, target);
			runGreedy(set, target);
			runGrasp(set, target);
			runGenetic(set, target);
		}
	}
	
	private static void runExhaustive(long[] set, long target) {
		long currentTime = System.nanoTime();
		System.out.println("Running exhaustive search...");
		long result = Exhaustive.exhaustiveSearch(set, target);
		System.out.println("Exhaustive search result: " + (result));
		System.out.println("Exhaustive search runtime: " + (System.nanoTime() - currentTime) + "ns");
		System.out.println();
	}
	
	private static void runDynamic(long[] set, long target) {
		long currentTime = System.nanoTime();
		System.out.println("Running dynamic search...");
		boolean dynamic = Dynamic.dynamicSearch(set, target);
		System.out.println("Dynamic search result: " + dynamic);
		System.out.println("Dynamic search runtime: " + (System.nanoTime() - currentTime) + "ns");
		System.out.println();
	}

	private static void runGreedy(long[] set, long target) {
		long currentTime = System.nanoTime();
		System.out.println("Running greedy search...");
		long result = Greedy.greedySearch(set, target);
		System.out.println("Greedy search result: " + result);
		System.out.println("Greedy search runtime: " + (System.nanoTime() - currentTime) + "ns");
		System.out.println("Greedy search accuracy: " + round(Math.abs((double)result/(double)target)*100, 2) + "%");
		System.out.println();
	}
	
	private static void runGrasp(long[] set, long target) {
		long currentTime = System.nanoTime();
		System.out.println("Running GRASP search with 10000 iterations...");
		long result = Grasp.graspSearch(set, target, 10000);
		System.out.println("GRASP search result: " + result);
		System.out.println("GRASP search runtime: " + (System.nanoTime() - currentTime) + "ns");
		System.out.println("GRASP search accuracy: " + round(Math.abs((double)result/(double)target)*100, 2) + "%");
		System.out.println();
	}
	
	public static void runGenetic(long[] set, long target) {
		long currentTime = System.nanoTime();
		System.out.println("Running genetic search with 1000 iterations");
		long result = Genetic.geneticSearch(set, target, 1000);
		System.out.println("Genetic search result: " + result);
		System.out.println("Genetic search runtime: " + (System.nanoTime() - currentTime) + "ns");
		System.out.println("Genetic search accuracy: " + round(Math.abs((double)result/(double)target)*100, 2) + "%");
		System.out.println();
	}
	
	public static double round(double value, int places) {
	    BigDecimal bd = new BigDecimal(value);
	    bd = bd.setScale(places, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}
	
}
