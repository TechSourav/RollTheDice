package com.sourav.dice.validation.validators;

import com.sourav.dice.constants.RollTheDiceConstants;
import com.sourav.dice.dto.DiceRollerResult;
import com.sourav.dice.validation.predicate.IDiceRollerPredicateExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class DiceRollerValidatorDelegateImpl implements IDiceRollerValidator {

    @Autowired
    private IDiceRollerPredicateExecutor diceRollerPredicateExecutorImpl;

    @Override
    public DiceRollerResult validate(String[] args) {
        boolean isValid = diceRollerPredicateExecutorImpl.execute(args);
        if (isValid) {
            return buildResult(true, "Data matches to expected nds+nds format ");
        } else {
            return buildResult(false, String.format("Validation Failed!\\n Expected input should be %s. with n >0 and s = 4,6,8,10,12,20", RollTheDiceConstants.VALID_INPUT_FORMAT));
        }
    }

    /**
     * A Generic Validator which will take up a list, expected count and a message, and check for the list size and return a message
     * if the validation fails.
     *
     * @param args {@link String[]}
     * @return {@link Optional}
     */
    public DiceRollerResult validateGeneralConstructs(String[] args) {
        List<String> listInput = Arrays.asList(args);
        Optional<String> result = getGenericDataConstructs(listInput);

        if (result.isPresent()) {
            return buildResult(false, result.get());
        } else
            return buildResult(true, "Data non empty");
    }

    /**
     * A Generic Validator which will take up a list and check for the list size and return a message
     * if the validation fails.
     *
     * @param listInput {@link List<String>}
     * @return {@link java.util.Optional} empty if validation passes, otherwise will hold a message
     */
    private Optional<String> getGenericDataConstructs(List<String> listInput) {
        if (!CollectionUtils.isEmpty(listInput) && listInput.size() > 0) {
            return Optional.empty();
        }
        return Optional.of(String.format("Validation Failed!\\n Expected input should be %s with n >0 and s = 4,6,8,10,12,20", RollTheDiceConstants.VALID_INPUT_FORMAT));
    }
}
