package lw04.prelab;
import java.io.InputStream;
import java.util.Scanner;

public class Main {

    public static void insertionSort(int[] arr, int passes) {

        for (int i = 1; i <= passes; i++) {
            int key = arr[i];
            int j = 1 - 1;

            while (j >= 0 && arr[j] < key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    public static int linearSearch(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {

        InputStream stream = Main.class.getResourceAsStream("branches.txt");

        if (stream == null) {
            System.out.println("File not found.");
            return;
        }

        Scanner input = new Scanner(stream);

        int N = input.nextInt();
        int[] branches = new int[N];

        for (int i = 0; i < N; i++) {
            branches[i] = input.nextInt();
        }

        int M = input.nextInt();
        int[] targets = new int[M];

        for (int i = 0; i < M; i++) {
            targets[i] = input.nextInt();
        }
        input.close();

        insertionSort(branches, 3);

        for (int i = 0; i < N; i++) {
            System.out.print(branches[i]);

            if (i < N - 1) {
                System.out.print(" ");
            }
        }
        System.out.println();

        for (int i = 0; i < M; i++) {
            int result = linearSearch(branches, targets[i]);
            if (result != -1) {
                System.out.println(targets[i] + ": FOUND at index " + result);
            } else {
                System.out.println(targets[i] + ": NOT FOUND");
            }
        }
    }
}