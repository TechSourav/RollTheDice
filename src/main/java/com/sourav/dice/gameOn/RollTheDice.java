package com.sourav.dice.gameOn;

import com.sourav.dice.dto.DiceRollerResult;
import com.sourav.dice.exception.IncorrectRollTheDIceException;
import com.sourav.dice.util.DiceRollerUtils;
import com.sourav.dice.util.FlattenedRollTheDiceInputDetails;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Stack;


/**
 * This class ise responsible to return the final result after randomly rolled the pair of dice.
 **/

@Log4j
@Component
public class RollTheDice {

    public int die = 0;   // Number showing on the first die.
    public int finalResult = 0;   // Number showing on the second die.

    public DiceRollerResult roll(FlattenedRollTheDiceInputDetails rollDiceInputEntity) {
        Stack<String> operatorStack = rollDiceInputEntity.operator();
        Stack<String> operandStack = new Stack<>();
        String formattedInputString = "";
        try {
            for (Map<String, String> pairMap : rollDiceInputEntity.dicePairs()) {
                int noOfRolls = Integer.parseInt(pairMap.get("noOfRolls"));
                int noOfSides = Integer.parseInt(pairMap.get("noOfSides"));
                die = getDiceRollResult(noOfRolls, noOfSides);
                log.info(String.format("Calculated dice roll result for current %s is %s", (noOfRolls + "D" + noOfSides), die));
                operandStack.push(String.valueOf(die));
            }

            Stack<String> reversedOperandStack = DiceRollerUtils.reverseStack(operandStack);

            while (!reversedOperandStack.isEmpty()) {
                formattedInputString = formattedInputString + reversedOperandStack.pop();
                if (!operatorStack.isEmpty()) {
                    formattedInputString = formattedInputString + " " + operatorStack.pop() + " ";
                }
            }
            finalResult = DiceRollerUtils.evaluateExpression(formattedInputString);
        } catch (Exception e) {
            e.printStackTrace();
            throw new IncorrectRollTheDIceException("Could not calculate the final result.");
        }

        return new DiceRollerResult.DiceRollerResultBuilder().with(validationResultBuilder -> {
            validationResultBuilder.valid = true;
            validationResultBuilder.message = "All dice rolled. Show the result";
            validationResultBuilder.isCalculated = true;
            validationResultBuilder.finalResult = this.finalResult;
        }).build();

    }

    protected int getDiceRollResult(int noOfRolls, int noOfSides) {
        int res = 0;
        for (int i = 0; i < noOfRolls; i++) {
            res += (int) (Math.random() * noOfSides) + 1;
        }
        return res;
    }
}
