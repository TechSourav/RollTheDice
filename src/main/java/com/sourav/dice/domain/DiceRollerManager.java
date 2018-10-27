package com.sourav.dice.domain;

import com.sourav.dice.dto.DiceRollerResult;
import com.sourav.dice.exception.FieldTransformationException;
import com.sourav.dice.exception.IncorrectRollTheDIceException;
import com.sourav.dice.exception.InvalidDataException;
import com.sourav.dice.gameOn.RollTheDice;
import com.sourav.dice.mappings.json.Mapping;
import com.sourav.dice.mappings.service.FieldMappingService;
import com.sourav.dice.util.DiceRollerUtils;
import com.sourav.dice.util.FlattenedRollTheDiceInputDetails;
import com.sourav.dice.validation.validators.DiceRollerValidateManager;
import com.sourav.dice.validation.validators.IDiceRollerValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


/**
 * This class is responsible in flatenning the given input and validate it. If validation
 * passes, it will roll the dice randomly and show the result.
 **/

@Slf4j
@Component
public class DiceRollerManager implements IDiceRollerExecutor {

    @Autowired
    private IDiceRollerValidator validateManager;

    @Autowired
    private RollTheDice rollTheDice;

    @Autowired
    private FieldMappingService mappingService;

    /**
     * This method will validate the input if empty and construct flattened input data to process.
     **/
    @Override
    public DiceRollerResult execute(String[] input) throws IncorrectRollTheDIceException, FieldTransformationException, InvalidDataException {
        log.info("Validate the Details from the given input");
        DiceRollerResult diceRollerResult = validateManager.validate(input);
        if (diceRollerResult.valid()) {
            log.info("Build the FlattenedRollTheDiceInputDetails from the given input");
            FlattenedRollTheDiceInputDetails buildInputDetails = buildFlattenedRollTheDiceInputDetails(input);
            diceRollerResult = rollTheDice.roll(buildInputDetails);
            return diceRollerResult;
        } else {
            return diceRollerResult;
        }
    }

    protected FlattenedRollTheDiceInputDetails buildFlattenedRollTheDiceInputDetails(String[] input) throws InvalidDataException {
        String formattedInput = DiceRollerUtils.formatInput(input); // utility to get nds+nds formatted input
        Stack<String> inputOperator = DiceRollerUtils.getInputOperator(formattedInput); // utility to get + or -

        return FlattenedRollTheDiceInputDetails.newInstance(builder -> {
            builder.operator = DiceRollerUtils.reverseStack(inputOperator);
            builder.dicePairs = getPairOfDiceList(formattedInput);

            return builder;
        });
    }

    private List<Map<String, String>> getPairOfDiceList(String formattedInput) {
        return getPairOfDiceList(DiceRollerUtils.getSplittedStringArrayInNDSFormat(formattedInput));
    }

    /**
     * This will return List<Map<String, String>>
     *
     * @param formattedInputArr {@link String}
     * @return List<Map               <               String               ,                               String>>
     **/
    public List<Map<String, String>> getPairOfDiceList(String[] formattedInputArr) {
        List<String> splittedArrayInputList = Arrays.asList(formattedInputArr);
        List<Map<String, String>> transformedMessageList = new ArrayList<>();
        Mapping mapping = mappingService.getMappings();
        for (String str : splittedArrayInputList) {
            transformedMessageList.add(mapping.getFieldMappings()
                    .entrySet()
                    .stream()
                    .collect(Collectors.toMap(key -> key.getKey(),
                            value -> str.toUpperCase().substring(value.getValue().getStartDelimiter().equals("0") ? 0 : str.indexOf(value.getValue().getStartDelimiter().charAt(0)) + 1,
                                    !value.getValue().getStartDelimiter().equals("0") ? str.length() : str.indexOf(value.getValue().getEndDelimiter().charAt(0))))));

        }
        return transformedMessageList;
    }

   /* public static void main(String[] args) {
        String str = "4000D200";
        str = str.toUpperCase();
        System.out.println(str.substring(0,str.indexOf('D')));
        System.out.println(str.substring(str.indexOf('D')+1,str.length()));

    }*/

}
