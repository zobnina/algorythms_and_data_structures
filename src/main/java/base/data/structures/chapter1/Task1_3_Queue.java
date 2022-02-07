package base.data.structures.chapter1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;

public class Task1_3_Queue {

    private static final BufferedReader READER = new BufferedReader(
            new InputStreamReader(System.in));
    private static final String SP = " ";
    private static final String NL = "\n";

    public static void main(String[] args) throws IOException {

        var input = Arrays.stream(READER.readLine().split(SP)).mapToLong(Long::parseLong).toArray();
        long bufferSize = input[0];
        long packetCount = input[1];
        long processorTimeStart = 0;
        LinkedList<Long> inBuffer = new LinkedList<>();
        StringBuilder packetStartTime = new StringBuilder();
        for (long i = 0; i < packetCount; i++) {
            input = Arrays.stream(READER.readLine().split(SP)).mapToLong(Long::parseLong).toArray();
            long startTime = input[0];
            long duration = input[1];
            long finishTime = startTime + duration;
            if (!inBuffer.isEmpty() && inBuffer.peekFirst().compareTo(startTime) <= 0) {
                inBuffer.pollFirst();
            }
            if (bufferSize > inBuffer.size()) {
                if (processorTimeStart <= startTime) {
                    packetStartTime.append(startTime);
                } else {
                    packetStartTime.append(processorTimeStart);
                    finishTime = processorTimeStart + duration;
                }
                if (inBuffer.size() != 0) {
                    inBuffer.add(finishTime);
                } else if (duration != 0) {
                    inBuffer.add(finishTime);
                }
                processorTimeStart = finishTime;
            } else {
                packetStartTime.append(-1);
            }
            packetStartTime.append(NL);
        }

        System.out.println(packetStartTime);
    }
}
