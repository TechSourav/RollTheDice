package com.sourav.dice.domain;

import lombok.Getter;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

@Getter
public enum OperatorType {
    ADDITION("+"),
    SUBSTRACTION("-"),
    MULTIPLICATION("*"),
    DIVISION("/");

    private static Map<String, String> operatorTypeMap = new HashMap<>();

    static {
        operatorTypeMap.put("+", ADDITION.name());
        operatorTypeMap.put("-", SUBSTRACTION.name());
        operatorTypeMap.put("*", MULTIPLICATION.name());
        operatorTypeMap.put("/", DIVISION.name());
    }

    private final String operator;

    public static final EnumSet<OperatorType> all = EnumSet.of(ADDITION, SUBSTRACTION, MULTIPLICATION, DIVISION);

    OperatorType(final String opr) {
        this.operator = opr;
    }

    public static Map<String, String> getOperatorTypeMap() {
        return OperatorType.operatorTypeMap;
    }

}
