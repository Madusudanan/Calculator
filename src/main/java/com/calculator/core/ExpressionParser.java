package com.calculator.core;

import com.calculator.exception.ParseException;
import com.calculator.util.Constants;
import com.calculator.util.PreProcessor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



public class ExpressionParser {

    private static Logger logger = LogManager.getLogger(ExpressionParser.class);

    public static String parseExpression(String input) throws ParseException {

        String result;

        if(!PreProcessor.isValidExpression(input)) {
            logger.error("Entered message : " + input + " " + Constants.INVALID_SYNTAX);
            throw new ParseException(Constants.INVALID_SYNTAX);
        }

        else {

            try {
                PostFixConverterUtil pc = new PostFixConverterUtil(input);
                PostFixEvaluator calc = new PostFixEvaluator(pc.getPostfixAsList());
                result = calc.result().toString();
            } catch (ParseException p) {
                logger.error(Constants.INVALID_SYNTAX);
                throw p;
            }
        }

        return result;
    }

}
