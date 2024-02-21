package StacksHW1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Type your infix expression: ");
        String input = scanner.nextLine();

        PostfixCalculator calculator = new PostfixCalculator(input);
        double result = calculator.evaluate();

        System.out.println("Converted to postfix: " + calculator.getConverter().toPostFix());
        System.out.println("Answer is " + result);
    }
}

