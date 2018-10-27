package com.sourav.dice.util;

import com.sourav.dice.domain.OperatorType;
import com.sourav.dice.exception.InvalidDataException;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


public class DiceRollerUtils {

    /**
     * This will convert the input into nDs+nDs format and return.
     *
     * @param input {@link String[]}
     * @return String
     **/
    public static String formatInput(String[] input) {
        List<String> inputList = Arrays.asList(input);
        return inputList.stream().collect(Collectors.joining());
    }

    /**
     * This will return the operator (+ or -).
     *
     * @param formattedInput {@link String}
     * @return Stack<String>
     **/
    public static Stack<String> getInputOperator(String formattedInput) {
        Stack<String> st = new Stack<>();
        Matcher match = Pattern.compile("[+-]+").matcher(formattedInput);
        while (match.find()) {
            String matchedStr = match.group();
            if (OperatorType.getOperatorTypeMap().containsKey(matchedStr))
                st.push(matchedStr);
            else {
                throw new InvalidDataException("Invalid Operator inserted.");
            }
        }
        return st;
    }


    /**
     * This will return String[]
     *
     * @param formattedInput {@link String}
     * @return String[]
     **/
    public static String[] getSplittedStringArrayInNDSFormat(String formattedInput) {
        return formattedInput.split("[+-]");
    }

    /**
     * This will return revered Stack<String>
     *
     * @param stack {@link Stack<String>}
     * @return Stack<String>
     **/
    public static Stack<String> reverseStack(Stack<String> stack) {
        Stack<String> reversedStack = new Stack<String>();
        while (!stack.isEmpty()) {
            reversedStack.push(stack.pop());
        }
        return reversedStack;
    }

    /**
     * This will return final result of rolled dice
     *
     * @param expression {@link String}
     * @return int
     **/
    public static int evaluateExpression(String expression) {
        char[] ch = expression.toCharArray();
        Stack<Integer> values = new Stack<Integer>();
        Stack<Character> charStack = new Stack<Character>();

        for (int i = 0; i < ch.length; i++) {
            if (ch[i] == ' ')
                continue;
            if (ch[i] >= '0' && ch[i] <= '9') {
                StringBuffer strBuffer = new StringBuffer();
                while (i < ch.length && ch[i] >= '0' && ch[i] <= '9')
                    strBuffer.append(ch[i++]);
                values.push(Integer.parseInt(strBuffer.toString()));
            } else if (ch[i] == '+' || ch[i] == '-' ||
                    ch[i] == '*' || ch[i] == '/') {
                while (!charStack.empty() && hasPrecedence(ch[i], charStack.peek()))
                    values.push(applyOperator(charStack.pop(), values.pop(), values.pop()));
                charStack.push(ch[i]);
            }
        }
        while (!charStack.empty())
            values.push(applyOperator(charStack.pop(), values.pop(), values.pop()));

        return values.pop();
    }

    private static boolean hasPrecedence(char op1, char op2) {
        if (op2 == '(' || op2 == ')')
            return false;
        if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-'))
            return false;
        else
            return true;
    }

    private static int applyOperator(char op, int b, int a) {
        switch (op) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                if (b == 0)
                    throw new
                            UnsupportedOperationException("Cannot divide by zero");
                return a / b;
        }
        return 0;
    }
}
