package com.sourav.dice.validation.validators;

import com.sourav.dice.dto.DiceRollerResult;
import com.sourav.dice.util.FlattenedRollTheDiceInputDetails;

public interface IDiceRollerValidator {

    DiceRollerResult validate(String[] args);

    default DiceRollerResult validateGeneralConstructs(String[] args) {
        return new DiceRollerResult();
    }

    /**
     * Builds the {@link DiceRollerResult} object
     *
     * @param result  a boolean result to represent if the validation was successful or not
     * @param message a String message, reasoning out the failiure of the validation
     * @return DiceRollerResult
     */
    default DiceRollerResult buildResult(boolean result, String message) {

        return new DiceRollerResult.DiceRollerResultBuilder().with(validationResultBuilder -> {
            validationResultBuilder.valid = result;
            validationResultBuilder.message = message;
            validationResultBuilder.isCalculated = false;
        }).build();
    }
}
