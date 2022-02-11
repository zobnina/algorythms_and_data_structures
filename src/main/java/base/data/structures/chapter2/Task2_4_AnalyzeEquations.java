package base.data.structures.chapter2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

public class Task2_4_AnalyzeEquations {

    private static final BufferedReader READER = new BufferedReader(
            new InputStreamReader(System.in));
    static final String sp = " ";

    public static void main(String[] args) throws IOException {
        int[] inputParams = Arrays.stream(READER.readLine().split(sp)).mapToInt(Integer::parseInt).toArray();
        int n = inputParams[0], e = inputParams[1], d = inputParams[2];
        DisjointSet disjointSet = new DisjointSet();
        for (int i = 1; i <= n; i++) {
            disjointSet.makeSet(i);
        }
        for (int i = 0; i < e; i++) {
            int[] numbers = Arrays.stream(READER.readLine().split(sp)).mapToInt(Integer::parseInt).toArray();
            disjointSet.union(numbers[0], numbers[1]);
        }
        for (int i = 0; i < d; i++) {
            int[] numbers = Arrays.stream(READER.readLine().split(sp)).mapToInt(Integer::parseInt).toArray();
            int parent1 = disjointSet.find(numbers[0]);
            int parent2 = disjointSet.find(numbers[1]);
            if (parent1 == parent2) {
                System.out.println(0);
                return;
            }
        }
        System.out.println(1);
    }

    static class DisjointSet {

        private final Map<Integer, NodeInfo> nodes;

        public DisjointSet() {
            this.nodes = new LinkedHashMap<>();
        }

        void makeSet(int value) {
            nodes.put(value, new NodeInfo(value, 0));
        }

        int find(int value) {

            if (nodes.containsKey(value)) {
                if (nodes.get(value).getParent() == value) {
                    return value;
                }

                int parent = find(nodes.get(value).getParent());
                nodes.get(value).setParent(parent);
                return parent;
            }

            return value;
        }

        void union(int value1, int value2) {

            NodeInfo parent1 = nodes.get(find(value1));
            NodeInfo parent2 = nodes.get(find(value2));
            if (parent1.equals(parent2)) {
                return;
            }

            if (parent1.getRank() < parent2.getRank()) {
                parent1.setParent(value2);
            } else {
                parent2.setParent(value1);
                if (parent1.getRank() == parent2.getRank()) {
                    parent1.setRank(parent1.getRank() + 1);
                }
            }
        }
    }

    static class NodeInfo {

        private int parent;
        private int rank;

        protected NodeInfo(int parent, int rank) {
            this.parent = parent;
            this.rank = rank;
        }

        public void setParent(int parent) {
            this.parent = parent;
        }

        public void setRank(int rank) {
            this.rank = rank;
        }

        public int getParent() {
            return parent;
        }

        public int getRank() {
            return rank;
        }
    }
}
