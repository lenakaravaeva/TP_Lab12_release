package ru.ssau.worddist.view;

import ru.ssau.worddist.model.OperatorEnum;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

public class OperatorParser {
    private Map<String, OperatorEnum> operatorMap = new HashMap<>();

    public OperatorParser() {
        operatorMap.put("add", OperatorEnum.OPERATOR_ADDITION);
        operatorMap.put("remove", OperatorEnum.OPERATOR_REMOVE);
        operatorMap.put("check", OperatorEnum.OPERATOR_CHECK);
    }

    public OperatorEnum parseOperator(String token) throws ParseException {
        OperatorEnum operator = operatorMap.get(token);
        if (operator == null) {
            throw new ParseException(String.format(DistUI.ERROR_TOKEN_PARSE_PATTERN, token), 0);
        }
        return operator;
    }
}