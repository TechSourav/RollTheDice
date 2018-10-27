package com.sourav.dice.gameOn;

import org.junit.Test;

import static org.junit.Assert.assertNotEquals;

public class RollTheDiceTest {

    @Test
    public void getDiceRollResultRandonGenerationTest(){
        RollTheDice rd1 = new RollTheDice();
        RollTheDice rd2 = new RollTheDice();
        int res1 = rd1.getDiceRollResult(4,6);
        int res2 = rd2.getDiceRollResult(4,6);
        System.out.println("Res1: " + res1 + " , " + "Res2: " + res2);
        assertNotEquals(res1,res2);
    }
}
