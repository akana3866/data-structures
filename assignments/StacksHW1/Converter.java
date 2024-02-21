package StacksHW1;

import java.util.*;

public class Converter {
    private String input;

    public Converter(String input) {
        this.input = input;
    }

    public String toPostFix() {
        List<String> tokens = ParserHelper.parse(input.toCharArray());
        Stack<String> stack = new Stack<String>();
        StringBuilder output = new StringBuilder();

        for (String token : tokens) {
            if (Character.isDigit(token.charAt(0))) {
                output.append(token).append(" ");
            } else if (isOperator(token.charAt(0))) {
                while (!stack.isEmpty() && hasHigherPrecedence(stack.peek().charAt(0), token.charAt(0))) {
                    output.append(stack.pop()).append(" ");
                }
                stack.push(token);
            } else if (token.equals("(")) {
                stack.push(token);
            } else if (token.equals(")")) {
                while (!stack.peek().equals("(")) {
                    output.append(stack.pop()).append(" ");
                }
                stack.pop(); // remove the left parenthesis
            }
        }

        while (!stack.isEmpty()) {
            output.append(stack.pop()).append(" ");
        }

        return output.toString().trim();
    }

    private boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '^';
    }

    private boolean hasHigherPrecedence(char op1, char op2) {
        int op1Precedence = getOperatorPrecedence(op1);
        int op2Precedence = getOperatorPrecedence(op2);

        if (op1Precedence == op2Precedence && op1 == '^') {
            return false; // right associativity
        }

        return op1Precedence >= op2Precedence;
    }

    private int getOperatorPrecedence(char c) {
        switch (c) {
            case '^':
                return 3;
            case '*':
            case '/':
                return 2;
            case '+':
            case '-':
                return 1;
            default:
                return 0;
        }
    }
}


