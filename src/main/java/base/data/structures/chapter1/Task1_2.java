package base.data.structures.chapter1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Task1_2 {

    private static final String SP = " ";

    public static void main(String[] args) {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            Map<Integer, List<Integer>> mapOfVertices = createMapOfVertices(bufferedReader);
            System.out.println(getHeight(mapOfVertices, -1) - 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int getHeight(Map<Integer, List<Integer>> mapOfVertices, int r) {
        AtomicInteger height = new AtomicInteger(1);
        if (!mapOfVertices.containsKey(r)) {
            return height.get();
        }

        mapOfVertices.get(r).forEach(i -> height.set(Math.max(height.get(), getHeight(mapOfVertices, i) + 1)));
        return height.get();
    }

    private static Map<Integer, List<Integer>> createMapOfVertices(BufferedReader bufferedReader) throws IOException {
        bufferedReader.readLine();

        return Arrays.stream(bufferedReader.readLine().split(SP))
                .map(Integer::parseInt)
                .map(Pair::new)
                .collect(Collectors.groupingBy(a -> a.vertex,
                        Collectors.mapping(a -> Pair.index, Collectors.toList())));
    }

    private record Pair(Integer vertex) {
        private static Integer index = -1;

        private Pair(Integer vertex) {
            this.vertex = vertex;
            index++;
        }
    }
}
