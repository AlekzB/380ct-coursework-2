package com.alex.subsetsum.genetic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Genetic {

	private static long target = 0;
	
	private static HashMap<Long, Integer> available = new HashMap<Long, Integer>();

	public static long geneticSearch(long[] set, long target, int iterations) {
		Genetic.target = target;
		populateAvailable(set);
		HashMap<Chromosome, Long> chromosomes = new HashMap<Chromosome, Long>();
		for (int i = 0; i < 1000; i++) {
			Chromosome c = new Genetic().new Chromosome().mutate();
			chromosomes.put(c, c.getDifference());
		}
		for (int i = 0; i < iterations; i++) {
			ArrayList<Long> differences = new ArrayList<Long>(chromosomes.values());
			Collections.sort(differences);
			ArrayList<Chromosome> chromos = new ArrayList<Chromosome>();
			for (long l : differences) {
				Chromosome c = null;
				for (Chromosome test : chromosomes.keySet()) {
					if (chromosomes.get(test) == l) {
						c = test;
						break;
					}
				}
				if (c != null) {
					chromos.add(0, c);
				}
			}
			for (int a = 0; a <= chromosomes.size() * 0.1; a++) {
				for (int b = 0; b <= chromosomes.size() * 0.1; b++) {
					if (b > a) {
						Chromosome c = chromos.get(a).crossover(chromos.get(b));
						chromosomes.put(c, c.getDifference());
						if (c.getDifference() == 0) {
							return c.getValue();
						}
					}
				}
			}
			for (int a = 0; a <= chromosomes.size() * 0.9; a++) {
				Chromosome c = chromos.get(a).mutate();
				if (c.getValue() <= target)
					chromosomes.put(c, c.getDifference());
				if (c.getDifference() == 0) {
					return c.getValue();
				}
			}
		}
		Chromosome best = new Genetic().new Chromosome().mutate();
		for (Chromosome c : chromosomes.keySet()) {
			if (best.getDifference() > c.getDifference()) {
				best = c;
			}
		}
		return best.getValue();
	}

	private static void populateAvailable(long[] set) {
		for (int i = 0; i < set.length; i++) {
			if (available.containsKey(set[i])) {
				int newVal = available.get(set[i]) + 1;
				available.put(set[i], newVal);
			} else {
				available.put(set[i], 1);
			}
		}
	}

	public class Chromosome {

		private long value;

		private HashMap<Long, Integer> used = new HashMap<Long, Integer>();

		public Chromosome() {
			for (long l : available.keySet()) {
				used.put(l, 0);
			}
			value = 0;
		}

		public long getValue() {
			return value;
		}
		
		public long getDifference() {
			return Math.abs(target - value);
		}

		public void recalculate() {
			value = 0;
			for (long l : used.keySet()) {
				value += l * used.get(l);
			}
		}

		public HashMap<Long, Integer> getUsed() {
			return used;
		}

		public Chromosome mutate() {
			ArrayList<Long> keys = new ArrayList<Long>(available.keySet());
			long randomKey = keys.get((int) (Math.random() * keys.size()));
			if (used.get(randomKey) >= available.get(randomKey)) {
				return this;
			} else {
				int newVal = used.get(randomKey) + 1;
				if (newVal <= available.get(randomKey)) {
					used.put(randomKey, newVal);
					this.recalculate();
					return this;
				} else {
					return mutate();
				}
			}
		}

		public Chromosome crossover(Chromosome c) {
			for (long l : c.getUsed().keySet()) {
				if (this.getUsed().get(l) + c.getUsed().get(l) < available.get(l)
						&& this.getValue() + (c.getUsed().get(l) * l) < target) {
					this.used.put(l, this.getUsed().get(l) + c.getUsed().get(l));
				}
			}
			this.recalculate();
			return this;
		}

	}

}
