package com.sourav.dice.validation.predicate;


import com.sourav.dice.constants.RollTheDiceConstants;
import com.sourav.dice.domain.DiceType;
import com.sourav.dice.exception.InvalidDataException;
import com.sourav.dice.mappings.json.Mapping;
import com.sourav.dice.mappings.service.FieldMappingService;
import com.sourav.dice.util.DiceRollerUtils;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Log4j
@Component
public class DiceRollerPredicateExecutorImpl implements IDiceRollerPredicateExecutor {

    @Autowired
    private FieldMappingService mappingService;

    @Override
    public boolean execute(String[] args) {
        String[] ndsArr = DiceRollerUtils.getSplittedStringArrayInNDSFormat(DiceRollerUtils.formatInput(args));
        return matchNDSFormatPredicate.and(matchDiceRollCountPredicate)
                .and(matchDiceSideCountPredicate)
                .test(ndsArr);
    }


    /**
     * This will match the input array to nDs. Returns true if matches.
     **/
    Predicate<String[]> matchNDSFormatPredicate = (ndsArr) -> {
        log.info("Validate input array..");

        for (String nds : ndsArr) {
            Pattern pattern = Pattern.compile(RollTheDiceConstants.MASTER_PATTERN);
            Matcher m = pattern.matcher(nds);
            if (m.find())
                log.info("Matches the current value: " + m.group());
            else {
                log.info(String.format("Does not match the current value %s . Exiting the loop.", m.group()));
                throw new InvalidDataException("Does not match the current value to 'nds' format. ");
            }
        }

        return true;
    };

    /**
     * This will match the input array to nDs. Returns true if matches.
     **/
    Predicate<String[]> matchDiceSideCountPredicate = (ndsArr) -> {
        log.info("Validate input Dice sides..");
        List<Map<String, String>> pairOfDiceList = getPairOfDiceList(ndsArr);
        boolean result = pairOfDiceList.stream().allMatch(map -> DiceType.getDiceMap().containsKey(Integer.parseInt(map.get("noOfSides"))));
        return result;
    };

    /**
     * This will match the input array to nDs. Returns true if matches.
     **/
    Predicate<String[]> matchDiceRollCountPredicate = (ndsArr) -> {
        log.info("Validate input Dice Rolls..");
        List<Map<String, String>> pairOfDiceList = getPairOfDiceList(ndsArr);
        boolean result = pairOfDiceList.stream().allMatch(map -> Integer.parseInt(map.get("noOfRolls")) > 0);
        return result;
    };

    /**
     * This will return List<Map<String, String>>
     *
     * @param formattedInputArr {@link String}
     * @return List<Map       <       String       ,               String>>
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

}
