package base.data.structures.chapter2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Map;

public class Task2_1_Binary_Heap {

    private static final BufferedReader READER = new BufferedReader(
            new InputStreamReader(System.in));
    static final String sp = " ";
    static final String nl = "\n";

    public static void main(String[] args) throws IOException {

        READER.readLine();
        final String numbers = READER.readLine();
        final int[] arr = Arrays.stream(numbers.split(sp)).mapToInt(Integer::parseInt).toArray();
        Heap heap = new Heap(arr);
        System.out.println(heap.n);
        System.out.println(heap.history);
    }

    static class Heap {
        Map.Entry<Integer, Integer>[] items;
        int n;
        StringBuilder history;

        public Heap(int[] arr) {
            n = 0;
            history = new StringBuilder();
            items = new Map.Entry[arr.length];
            for (int i = 0; i < arr.length; i++) {
                items[i] = Map.entry(arr[i], i);
            }
            for (int i = items.length / 2; i >= 0; i--) {
                int lastIndex = items.length - 1;
                int leftChildIndex = 2 * i + 1;
                if (leftChildIndex <= lastIndex) {
                    siftDown(i);
                }
            }
        }

        void siftDown(int i) {
            while (2 * i + 1 < items.length) {
                int left = 2 * i + 1;
                int right = 2 * i + 2;
                int j = left;
                if (right < items.length && items[right].getKey() < items[left].getKey()) {
                    j = right;
                }
                if (items[i].getKey() <= items[j].getKey()) {
                    break;
                }
                swap(i, j);
                n++;
                history.append(i);
                history.append(sp);
                history.append(j);
                history.append(nl);
                i = j;
            }
        }

        void swap(int i, int j) {
            Map.Entry<Integer, Integer> temp = items[i];
            items[i] = items[j];
            items[j] = temp;
        }
    }
}
