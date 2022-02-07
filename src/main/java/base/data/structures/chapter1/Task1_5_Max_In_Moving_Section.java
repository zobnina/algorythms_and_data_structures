package base.data.structures.chapter1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Map;
import java.util.Stack;

public class Task1_5_Max_In_Moving_Section {

    private static final BufferedReader READER = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {

        final String sp = " ";
        READER.readLine();
        final String numbers = READER.readLine();
        final int[] arr = Arrays.stream(numbers.split(sp)).mapToInt(Integer::parseInt).toArray();
        final int sectionSize = Integer.parseInt(READER.readLine());
        final StringBuilder stringBuilder = new StringBuilder(numbers.length());
        for (int start = 0; start <= arr.length - sectionSize; start++) {
            final Stack<Map.Entry<Integer, Integer>> queue = new Stack<>();
            Arrays.stream(Arrays.copyOfRange(arr, start, start + sectionSize)).forEach(number -> {
                int max = queue.isEmpty() || number > queue.peek().getValue() ? number : queue.peek().getValue();
                queue.push(Map.entry(number, max));
            });
            stringBuilder.append(queue.peek().getValue());
            stringBuilder.append(sp);
        }
        System.out.println(stringBuilder);
    }
}
