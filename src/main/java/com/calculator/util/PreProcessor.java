package com.calculator.util;

import java.util.*;
import java.util.stream.Stream;

public class PreProcessor {

    public static boolean isValidExpression(String expression){
        return checkForParamMatch(expression.toCharArray()) && checkForIllegalCharacters(expression);
    }

    private static boolean checkForIllegalCharacters(String expression) {

        List<Character> characterList = asList(expression);

        Stream filteredList = characterList.
                parallelStream().
                filter(c -> c != '(').
                filter(c -> c != ')').
                filter(c -> c != '*').
                filter(c -> c != '/').
                filter(c -> c != '+').
                filter(c -> c != '-').
                filter(c -> c != ' ').
                filter(c -> !Character.isDigit(c))
                ;

        //If the filtered list contains extra characters(size greater than 0) then return false i.e error
        return filteredList.count() <= 0;
    }


    private static boolean checkForParamMatch(char[] expression){

        Deque<Character> stack = new ArrayDeque<>();

        for (char input : expression) {

            if (input == '(') {
                stack.push(input);

            }

            else if (input == ')') {

                if (stack.isEmpty() || stack.pop() != '(') {
                    return false;
                }
            }
        }

        return stack.isEmpty();

    }

    private static List<Character> asList(final String string) {
        return new AbstractList<Character>() {
            public int size() { return string.length(); }
            public Character get(int index) { return string.charAt(index); }
        };
    }

}
