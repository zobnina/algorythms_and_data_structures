package base.data.structures.chapter2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Task2_2_Priority_Queue {

    private static final BufferedReader READER = new BufferedReader(
            new InputStreamReader(System.in));
    static final String sp = " ";
    static final String nl = "\n";

    public static void main(String[] args) throws IOException {
        long[] inputParams = Arrays.stream(READER.readLine().split(sp)).mapToLong(Long::parseLong).toArray();
        long[] inputValues = Arrays.stream(READER.readLine().split(sp)).mapToLong(Long::parseLong).toArray();

        long n = inputParams[0];
        PriorityQueue<Thread> sortedThreads = new PriorityQueue<>((int) n, (o1, o2) -> {
            if (o1.countTicks == o2.countTicks)
                return Long.compare(o1.number, o2.number);
            return o1.countTicks > o2.countTicks ? 1 : -1;
        });

        for (long i = 0; i < n; i++) {
            sortedThreads.add(new Thread(i, 0));
        }

        StringBuilder result = new StringBuilder();
        Thread thread;
        for (long value : inputValues) {
            thread = sortedThreads.poll();
            if (thread != null) {
                result.append(thread.info());
                thread.countTicks += value;
                sortedThreads.add(thread);
            }
        }

        System.out.println(result);
    }

    static class Thread {
        private final long number;
        private long countTicks;

        Thread(long number, long countTicks) {
            this.number = number;
            this.countTicks = countTicks;
        }

        public String info() {
            return number + sp + countTicks + nl;
        }
    }
}
