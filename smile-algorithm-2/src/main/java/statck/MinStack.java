package statck;

import java.util.Stack;

/**
 * @className: MinStack
 * @description: TODO
 * @author: luweiming
 * @date: 2022/2/11
 **/
public class MinStack {
    Stack<Integer> minStack = new Stack();
    Stack<Integer> normalStack = new Stack();

    /** initialize your data structure here. */
    public MinStack() {
        minStack.push(Integer.MAX_VALUE);
    }

    public void push(int val) {
        normalStack.push(val);
        minStack.push(Math.min(minStack.peek(), val));
    }

    public void pop() {
        normalStack.pop();
        minStack.pop();
    }

    public int top() {
        return normalStack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
}

class MinStack1 {
    public Stack<Node> stack = new Stack();

    /** initialize your data structure here. */
    public MinStack1() {

    }

    public void push(int val) {
        if (stack.isEmpty()) {
            stack.push(new Node(val, val));
        } else {
            stack.push(new Node(val, Math.min(val, stack.peek().min)));
        }
    }

    public void pop() {
        stack.pop();
    }

    public int top() {
        return stack.peek().value;
    }

    public int getMin() {
        return stack.peek().min;
    }

    public static class Node {
        public int value;
        public int min;

        public Node(int value, int min) {
            this.value = value;
            this.min = min;
        }
    }
}