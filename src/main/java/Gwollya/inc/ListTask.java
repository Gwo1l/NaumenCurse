package Gwollya.inc;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ListTask {
    private static List<Double> list;
    private static int n;

    public static void start() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Count elements: ");
        n = scanner.nextInt();
        list = fillList(n);

        ArrayList<Double> sortedList = getMergeSortOfList(list);

        System.out.print('\n' + "Sorted list: ");
        sortedList.forEach(el -> System.out.print(el + " "));
    }


    private static ArrayList<Double> getMergeSortOfList(List<Double> list) {
        if (list.size() <= 1) {
            return new ArrayList<>(list);
        }

        int middle = list.size() / 2;
        List<Double> left = list.subList(0, middle);
        List<Double> right = list.subList(middle, list.size());

        left = getMergeSortOfList(left);
        right = getMergeSortOfList(right);

        return merge(left, right);
    }

    private static ArrayList<Double> merge(List<Double> left, List<Double> right) {
        ArrayList<Double> result = new ArrayList<>();
        int leftIndex = 0;
        int rightIndex = 0;

        while (leftIndex < left.size() && rightIndex < right.size()) {
            if (left.get(leftIndex) <= right.get(rightIndex)) {
                result.add(left.get(leftIndex));
                leftIndex++;
            } else {
                result.add(right.get(rightIndex));
                rightIndex++;
            }
        }

        result.addAll(left.subList(leftIndex, left.size()));

        result.addAll(right.subList(rightIndex, right.size()));

        return result;
    }


    private static ArrayList<Double> fillList(int n) {
        Random random = new Random();
        ArrayList<Double> list = new ArrayList<>(n);

        for (int i = 0; i < n; i++) {
            double val = random.nextDouble(201) - 100;
            list.add(val);
            System.out.print(val + " ");
        }
        return list;
    }
}
