package com.company;

import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("введеите данные");
        String sk = scanner.nextLine();
        System.out.println("введеите способ(stack, recursive)");
        String data = scanner.nextLine();
        switch (data){
            case "stack":
                HashMap<Character, Character> listSk = new HashMap<>();
                listSk.put('{', '}');
                listSk.put('[', ']');
                listSk.put('(', ')');
                Stack<Character> stack = new Stack<>();
                for (char c : sk.toCharArray()){
                    if(c == '{' || c == '[' || c == '(')
                        stack.push(c);
                    else{
                        if(stack.empty() || listSk.get(stack.pop()) != c){
                            System.out.println("неверно");
                            return;
                        }
                    }
                }
                System.out.println("верно");
                break;
            case "recursive":
                if(isBalanced(sk,"")){
                    System.out.println("верно");
                }
                else {
                    System.out.println("неверно");
                }
                break;
        }

    }

    static String open  = "([{";
    static String close = ")]}";

    static boolean isOpen(char ch) {
        return open.indexOf(ch) != -1;
    }
    static boolean isClose(char ch) {
        return close.indexOf(ch) != -1;
    }
    static boolean isMatching(char chOpen, char chClose) {
        return open.indexOf(chOpen) == close.indexOf(chClose);
    }

    static boolean isBalanced(String input, String stack) {
        return
                input.isEmpty() ?
                        stack.isEmpty()
                        : isOpen(input.charAt(0)) ?
                        isBalanced(input.substring(1), input.charAt(0) + stack)
                        : isClose(input.charAt(0)) ?
                        !stack.isEmpty() && isMatching(stack.charAt(0), input.charAt(0))
                                && isBalanced(input.substring(1), stack.substring(1))
                        : isBalanced(input.substring(1), stack);
    }
}
