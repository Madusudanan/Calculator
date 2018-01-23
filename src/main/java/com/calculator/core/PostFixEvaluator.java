package com.calculator.core;

import com.calculator.exception.ParseException;
import com.calculator.util.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;



class PostFixEvaluator {


    private List<String> inputExpression = new ArrayList<>();
    private Deque<Integer> resultStack = new ArrayDeque<>();

    private static Logger logger = LogManager.getLogger(PostFixEvaluator.class);


    PostFixEvaluator(List<String> postfix) {
        inputExpression = postfix;}


    Integer result() throws ParseException {
        try {
            for (int i = 0; i != inputExpression.size(); ++i) {
                // Determine if current element is digit or not
                if (Character.isDigit(inputExpression.get(i).charAt(0))) {
                    resultStack.addLast(Integer.parseInt(inputExpression.get(i)));
                } else {
                    int evalResult = 0;
                    int rightOperand;

                    switch (inputExpression.get(i)) {
                        case "+":
                            rightOperand = resultStack.removeLast();
                            evalResult = resultStack.removeLast() + rightOperand;
                            break;

                        case "-":
                            rightOperand = resultStack.removeLast();
                            evalResult = resultStack.removeLast() - rightOperand;
                            break;

                        case "*":
                            rightOperand = resultStack.removeLast();
                            evalResult = resultStack.removeLast() * rightOperand;
                            break;

                        case "/":
                            rightOperand = resultStack.removeLast();
                            if(rightOperand == 0) {
                                throw new NumberFormatException();
                            }

                            else {
                                evalResult = resultStack.removeLast() / rightOperand;
                            }
                            break;
                    }
                    resultStack.addLast(evalResult);
                }
            }
        }

        catch (NumberFormatException e) {
            logger.error(Constants.NUMBER_FORMAT_ERROR,e);
            throw new ParseException(Constants.NUMBER_FORMAT_ERROR);
        }

        catch (Exception e){
            logger.error("Exception occurred when parsing input string.",e);
            throw new ParseException(Constants.INVALID_SYNTAX);
        }

        return resultStack.removeLast();

    }
}