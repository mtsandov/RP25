package ggm.trees;

import java.util.Stack;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");
    }
    
    public static BinaryTree<String> buildExpressionTree (String postfixExpression) {
        Stack<BinaryTree<String>> s = new Stack<>();
        String[] tokens = postfixExpression.split(" ");
        for (int i = 0; i < tokens.length; i++) {
            String token = tokens[i];
            BinaryTree<String> t = new BinaryTree<>(token);
            if (isOperator(token)) {
                t.setRight(s.pop());
                t.setLeft(s.pop());
            }
            s.push(t);
        }
        return s.pop();
    }
    
    public static int evaluateExpressionTree (BinaryTree<String> tree) {
        if (tree.isEmpty()) {
            return 0;
        } else if (tree.isLeaf()) {
            return Integer.valueOf(tree.getRoot());
        } else {
            if (tree.getLeft() != null && tree.getRight()!= null) {
                int resultLeft = evaluateExpressionTree(tree.getLeft());
                int resultRight = evaluateExpressionTree(tree.getRight());
                return operate (resultLeft, resultRight, tree.getRoot());
            } else {
                // lanzar una excepción
            }
            return -1;
        }
    }

    private static int operate(int operand1, int operand2, String operator) {
        return switch (operator) {
            case "+" -> operand1 + operand2;
            case "-" -> operand1 - operand2;
            case "*" -> operand1 * operand2;
            default -> operand1 / operand2;
        };
    }
    
    

    private boolean isOperand(String token) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private boolean isOperator(String token) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
 