package com.sourav.dice.domain;

import com.sourav.dice.dto.DiceRollerResult;
import com.sourav.dice.exception.FieldTransformationException;
import com.sourav.dice.exception.IncorrectRollTheDIceException;
import com.sourav.dice.exception.InvalidDataException;
import org.springframework.stereotype.Service;

@Service
public interface IDiceRollerExecutor {
    /**
     * Implement this to execute and process the input.
     *
     * @param input {@link String[]} Test information
     * @return DiceRollerResult
     */
    DiceRollerResult execute(String[] input) throws IncorrectRollTheDIceException, FieldTransformationException, InvalidDataException;
}
