package base.data.structures.task12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Task1_2_4 {

    private static final BufferedReader READER = new BufferedReader(
            new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {

        final int commandCount = Integer.parseInt(READER.readLine());
        final String nl = "\n";
        final String sp = " ";
        Stack<Integer> stack = new Stack<>();
        Stack<Integer> maxStack = new Stack<>();
        maxStack.push(0);
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < commandCount; i++) {
            String[] input = READER.readLine().split(sp);
            String command = input[0];
            switch (Commands.valueOf(command)) {
                case push -> {
                    int val = Integer.parseInt(input[1]);
                    stack.push(val);
                    if (maxStack.peek() <= val) {
                        maxStack.push(val);
                    }
                }
                case pop -> {
                    if (maxStack.size() > 1 && stack.peek().equals(maxStack.peek())) {
                        maxStack.pop();
                    }
                    stack.pop();
                }
                case max -> {
                    stringBuilder.append(maxStack.peek());
                    stringBuilder.append(nl);
                }
            }
        }
        System.out.println(stringBuilder);
    }

    enum Commands {
        push,
        pop,
        max
    }
}
