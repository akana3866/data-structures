package TreesHW;

import java.util.*;

import StacksHW1.*; //imports the Stacks HW

public class ExpressionTree {
	
	Node root;
	
	private static boolean isNumber(char token) {
	    return Character.toString(token).matches("\\d");
	}

	private static boolean isOperator(char token) {
	    return Character.toString(token).matches("[-+*/^]");
	}
	public static Node convert(String postfixExpression) {
	    Stack<Node> stack = new Stack<>();
	    
	    for(int i = 0; i < postfixExpression.length(); i++) {
	        char token = postfixExpression.charAt(i);
	        
	        if (isNumber(token)) {
	            // If the token is a digit, read all consecutive digits
	            int j = i;
	            while (j < postfixExpression.length() && isNumber(postfixExpression.charAt(j))) {
	                j++;
	            }
	            String numStr = postfixExpression.substring(i, j);
	            Node node = new Node(numStr);
	            stack.push(node);
	            i = j - 1;  // update i to skip over the digits we just processed
	        } else if (isOperator(token)) {
	            Node rightChild = stack.pop();
	            Node leftChild = stack.pop();
	            Node operatorNode = new Node(token, leftChild, rightChild);
	            stack.push(operatorNode);
	        }
	    }
	    
	    return stack.pop();
	}
	
	public static String prefix(Node root) {
		if(root == null) {
			return " ";
		}else if(root.leftChild == null && root.rightChild == null) {
			return root.element.toString();
		} else {
			String left = prefix(root.leftChild);
			String right = prefix(root.rightChild);
        	String operator = root.element.toString();
        	
        	return operator + " " + left + " " +  right;
		}
		
	}
	
	public static String infix(Node root) {
		if(root == null){ //while the left is not null we will recursively call printInOrder and print all left values
            return " ";
        }else if(root.leftChild == null && root.rightChild == null) {
        	return root.element.toString();
        }else {
        	String left = infix(root.leftChild);
        	String right = infix(root.rightChild);
        	String operator = root.element.toString();
        	
        	return "(" + left + " " + operator + " " + right + ")";
        }
	}
	
	public static String postfix(Node root) {
		if(root == null) {
			return " ";
		}else if(root.leftChild == null && root.rightChild == null) {
			return root.element.toString();
		} else {
			String left = postfix(root.leftChild);
        	String right = postfix(root.rightChild);
        	String operator = root.element.toString();
        	
        	return left + " " + right + " " +  operator;
		}
	
    
	}
	
	public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Type your infix expression: ");
        String user = input.nextLine();

        PostfixCalculator calculator = new PostfixCalculator(user);
        double result = calculator.evaluate();
   
        
        String postFix = calculator.getConverter().toPostFix();
        
        System.out.println("Converted to infix: " + infix(convert(postFix)));
        
        System.out.println("Converted to postfix: " + postfix(convert(postFix)));
        
        System.out.println("Converted to prefix: " + prefix(convert(postFix)));
        
        System.out.println("Answer is " + result);
        
        
        
    }
	

}
