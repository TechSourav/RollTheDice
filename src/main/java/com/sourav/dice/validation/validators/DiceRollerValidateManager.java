package com.sourav.dice.validation.validators;

import com.sourav.dice.dto.DiceRollerResult;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;


@Component
public class DiceRollerValidateManager implements IDiceRollerValidator {

    @Autowired
    private IDiceRollerValidator diceRollerValidatorDelegateImpl;

    public DiceRollerValidateManager(IDiceRollerValidator diceRollerValidatorDelegateImpl) {
        this.diceRollerValidatorDelegateImpl = diceRollerValidatorDelegateImpl;
    }

    @Override
    public DiceRollerResult validate(String[] args) {
        DiceRollerResult result = diceRollerValidatorDelegateImpl.validateGeneralConstructs(args);
        if (result.valid())
            return diceRollerValidatorDelegateImpl.validate(args);
        else {
            return result;
        }
    }

}
