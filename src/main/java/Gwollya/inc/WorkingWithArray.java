package Gwollya.inc;

import java.util.Random;
import java.util.Scanner;

public class WorkingWithArray {
    private static int[] array;
    private static int n;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        array = fillArray(n);

        printMaxAbsValueInArray(array);
    }

    private static void printMaxAbsValueInArray(int[] array) {
        int maxVal = 0;
        for (int i = 0; i < array.length; i++) {
            maxVal = Math.max(maxVal, Math.abs(array[i]));
        }
        System.out.println('\n' + "Max abs value: " + maxVal);
    }

    private static int[] fillArray(int n) {
        Random random = new Random();
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = random.nextInt(201) - 100;
            System.out.print(arr[i] + " ");
        }
        return arr;
    }
}
