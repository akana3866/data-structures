package StacksHW1;

import java.util.Stack;

public class PostfixCalculator {
    
    private Converter converter;
    
    public PostfixCalculator(String input) {
        setConverter(new Converter(input));
    }
    
    public double evaluate() {
        String postfixExpression = getConverter().toPostFix();
        Stack<Double> stack = new Stack<>();
        
        for (int i = 0; i < postfixExpression.length(); i++) {
            char c = postfixExpression.charAt(i);
            
            if (Character.isDigit(c)) {
                String operand = "";
                while (i < postfixExpression.length() && Character.isDigit(postfixExpression.charAt(i))) {
                    operand += postfixExpression.charAt(i++);
                }
                i--;
                stack.push(Double.parseDouble(operand));
            } else if (c == '+') {
                double operand2 = stack.pop();
                double operand1 = stack.pop();
                stack.push(operand1 + operand2);
            } else if (c == '-') {
                double operand2 = stack.pop();
                double operand1 = stack.pop();
                stack.push(operand1 - operand2);
            } else if (c == '*') {
                double operand2 = stack.pop();
                double operand1 = stack.pop();
                stack.push(operand1 * operand2);
            } else if (c == '/') {
                double operand2 = stack.pop();
                double operand1 = stack.pop();
                stack.push(operand1 / operand2);
            } else if (c == '^') {
                double operand2 = stack.pop();
                double operand1 = stack.pop();
                stack.push(Math.pow(operand1, operand2));
            }
        }
        return stack.pop();
    }

	public Converter getConverter() {
		return converter;
	}

	public void setConverter(Converter converter) {
		this.converter = converter;
	}
}
