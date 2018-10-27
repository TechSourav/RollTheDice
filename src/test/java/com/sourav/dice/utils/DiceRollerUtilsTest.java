package com.sourav.dice.utils;

import com.sourav.dice.util.DiceRollerUtils;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class DiceRollerUtilsTest {

    @Test
    public void evaluateExpressionTest(){
        assertEquals(DiceRollerUtils.evaluateExpression("9 + 12 -80 + 7"),-52);
    }

    @Test
    public void formatInputTest(){
        String[] arr = {"4d5","+","6D8"};
        assertEquals(DiceRollerUtils.formatInput(arr),"4d5+6D8");
    }


}
