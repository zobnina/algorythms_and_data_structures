package base.data.structures.chapter1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Stack;

public class Task1_1_Scopes {

    private static final Map<Character, Character> PAIRS = Map.of(
            '(', ')',
            '{', '}',
            '[', ']'
    );

    public static void main(String[] args) {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            Stack<Bracket> stack = new Stack<>();
            char[] chars = reader.readLine().toCharArray();
            for (int i = 0; i < chars.length; i++) {
                if (PAIRS.containsKey(chars[i])) {
                    stack.add(new Bracket(chars[i], i + 1));
                } else {
                    if (!PAIRS.containsValue(chars[i]))
                        continue;
                    if (stack.isEmpty() || !PAIRS.get(stack.peek().ch).equals(chars[i])) {
                        System.out.println(i + 1);
                        return;
                    } else {
                        stack.pop();
                    }
                }
            }

            System.out.println(stack.isEmpty() ? "Success" : stack.pop().index);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public record Bracket(char ch, int index) {
    }
}
