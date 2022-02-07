package base.data.structures.chapter2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

public class Task2_3_Disjoint_Set {

    private static final BufferedReader READER = new BufferedReader(
            new InputStreamReader(System.in));
    static final String sp = " ";
    static final String nl = "\n";

    public static void main(String[] args) throws IOException {

        int[] inputParams = Arrays.stream(READER.readLine().split(sp)).mapToInt(Integer::parseInt).toArray();
        int n = inputParams[0];
        int m = inputParams[1];

        int[] rows = Arrays.stream(READER.readLine().split(sp)).mapToInt(Integer::parseInt).toArray();
        DisjointSet disjointSet = new DisjointSet();
        int max = 0;
        for (int i = 1; i <= n; i++) {
            TableInfo tableInfo1 = new TableInfo(i, 0, rows[i - 1]);
            disjointSet.getNodes().put(i, tableInfo1);
            if (max < tableInfo1.getRecordCount()) {
                max = tableInfo1.getRecordCount();
            }
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < m; i++) {
            int[] tables = Arrays.stream(READER.readLine().split(sp)).mapToInt(Integer::parseInt).toArray();
            disjointSet.union(tables[0], tables[1]);
            TableInfo tableInfo1 = disjointSet.getNodes().get(disjointSet.find(tables[0]));
            if (max < tableInfo1.getRecordCount()) {
                max = tableInfo1.getRecordCount();
            }
            result.append(max);
            result.append(sp);
            result.append(nl);
        }

        System.out.println(result);
    }

    static class DisjointSet {

        private final Map<Integer, TableInfo> nodes;

        public DisjointSet() {
            this.nodes = new LinkedHashMap<>();
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

            TableInfo parent1 = nodes.get(find(value1));
            TableInfo parent2 = nodes.get(find(value2));
            if (parent1.equals(parent2)) {
                return;
            }

            if (parent1.getRank() < parent2.getRank()) {
                parent1.setParent(value2);
                parent2.setRecordCount(parent1.getRecordCount() + parent2.getRecordCount());
            } else {
                parent2.setParent(value1);
                if (parent1.getRank() == parent2.getRank()) {
                    parent1.setRank(parent1.getRank() + 1);
                }
                parent1.setRecordCount(parent1.getRecordCount() + parent2.getRecordCount());
            }
        }

        public Map<Integer, TableInfo> getNodes() {
            return nodes;
        }
    }

    static class TableInfo {

        private int parent;
        private int rank;
        private int recordCount;

        protected TableInfo(int parent, int rank, int recordCount) {
            this.parent = parent;
            this.rank = rank;
            this.recordCount = recordCount;
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

        public int getRecordCount() {
            return recordCount;
        }

        public void setRecordCount(int recordCount) {
            this.recordCount = recordCount;
        }

    }
}
