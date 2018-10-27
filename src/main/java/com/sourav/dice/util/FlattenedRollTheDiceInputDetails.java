package com.sourav.dice.util;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.function.Function;

/**
 * This class will construct the entity from given input
 * where noOfRolls = n of 'nDs'
 * and noOfSides = s of 'nDs'
 *
 * Structure of entity:
 *
 *  ->(operator)  + / -
 *                  ->(Dice 1) noOfRolls : <value>
 *                             noOfSides : <value>
 *                  ->(Dice 2) noOfRolls : <value>
 *                             noOfSides : <value>
 *
 **/

@Getter
@Setter
@Accessors(fluent = true)
public class FlattenedRollTheDiceInputDetails {

    private Stack<String> operator;
    private List<Map<String, String>> dicePairs;

    public static FlattenedRollTheDiceInputDetails newInstance(Function<Builder, Builder> config) {
        return config.andThen(FlattenedRollTheDiceInputDetails.Builder::build)
                .apply(new FlattenedRollTheDiceInputDetails.Builder());
    }

    public static class Builder {

        public Stack<String> operator; // This can be '+' , '-'
        public List<Map<String, String>> dicePairs;

        private Builder() {
        }

        public FlattenedRollTheDiceInputDetails build() {
            FlattenedRollTheDiceInputDetails validationDetail = new FlattenedRollTheDiceInputDetails();
            validationDetail.dicePairs = Collections.unmodifiableList(this.dicePairs);
            validationDetail.operator = this.operator;

            return validationDetail;
        }
    }

}
