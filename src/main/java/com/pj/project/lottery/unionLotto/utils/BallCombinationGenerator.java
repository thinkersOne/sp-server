package com.pj.project.lottery.unionLotto.utils;
import java.util.ArrayList;
import java.util.List;

public class BallCombinationGenerator {
    public static void main(String[] args) {
        List<List<Integer>> combinations = generateBallCombinations();

        for (List<Integer> combination : combinations) {
            System.out.println(combination);
        }
    }

    public static List<List<Integer>> generateBallCombinations() {
        List<List<Integer>> combinations = new ArrayList<>();
        int[] partitions = {2, 3, 1};
        int[] ballRanges = {11, 22, 33};
        int sumRangeMin = 73;
        int sumRangeMax = 78;

        generateBallCombinationsHelper(combinations, new ArrayList<>(), partitions, ballRanges, sumRangeMin, sumRangeMax, 0);

        return combinations;
    }

    private static void generateBallCombinationsHelper(List<List<Integer>> combinations, List<Integer> currentCombination, int[] partitions, int[] ballRanges, int sumRangeMin, int sumRangeMax, int index) {
        if (currentCombination.size() == 6) {
            int sum = currentCombination.stream().mapToInt(Integer::intValue).sum();
            if (sum >= sumRangeMin && sum <= sumRangeMax) {
                combinations.add(new ArrayList<>(currentCombination));
            }
            return;
        }

        if (index >= partitions.length) {
            return;
        }

        int partitionCount = partitions[index];
        int rangeStart = index > 0 ? ballRanges[index - 1] + 1 : 1;
        int rangeEnd = ballRanges[index];

        for (int i = rangeStart; i <= rangeEnd; i++) {
            currentCombination.add(i);

            if (isValidCombination(currentCombination, partitions, index)) {
                generateBallCombinationsHelper(combinations, currentCombination, partitions, ballRanges, sumRangeMin, sumRangeMax, index + 1);
            }

            currentCombination.remove(currentCombination.size() - 1);
        }
    }

    private static boolean isValidCombination(List<Integer> currentCombination, int[] partitions, int index) {
        if (index == 0) {
            return true;
        }

        int start = partitions[index - 1];
        int end = partitions[index];
        int count = 0;

        for (int i = start; i < end; i++) {
            if (currentCombination.get(i) % 2 == 0) {
                count++;
            }
        }

        return count >= 4;
    }
}

