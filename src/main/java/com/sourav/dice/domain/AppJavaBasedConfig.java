package com.sourav.dice.domain;

import com.sourav.dice.constants.RollTheDiceConstants;
import com.sourav.dice.dto.DiceRollerResult;
import com.sourav.dice.gameOn.RollTheDice;
import com.sourav.dice.mappings.json.Mapping;
import com.sourav.dice.mappings.service.FieldMappingService;
import com.sourav.dice.mappings.service.FieldMappingServiceImpl;
import com.sourav.dice.validation.predicate.DiceRollerPredicateExecutorImpl;
import com.sourav.dice.validation.predicate.IDiceRollerPredicateExecutor;
import com.sourav.dice.validation.validators.DiceRollerValidateManager;
import com.sourav.dice.validation.validators.DiceRollerValidatorDelegateImpl;
import com.sourav.dice.validation.validators.IDiceRollerValidator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({RollTheDiceConstants.ROOT_PACKAGE + ".dto",
        RollTheDiceConstants.ROOT_PACKAGE + ".validate.predicate",
        RollTheDiceConstants.ROOT_PACKAGE + ".validate.validators",
        RollTheDiceConstants.ROOT_PACKAGE + ".mappings.service",
        RollTheDiceConstants.ROOT_PACKAGE + ".json"})
public class AppJavaBasedConfig {

    @Bean
    ApplicationApi applicationApi() {
        return new ApplicationApi();
    }

    @Bean
    IDiceRollerExecutor diceRollerManager() {
        return new DiceRollerManager();
    }

    @Bean
    DiceRollerResult diceRollerResult() {
        return new DiceRollerResult();
    }

    @Bean
    RollTheDice rollTheDice() {
        return new RollTheDice();
    }

    @Bean
    FieldMappingService mappingService() {
        return new FieldMappingServiceImpl();
    }

    @Bean
    @Qualifier("validateManager")
    IDiceRollerValidator validateManager(IDiceRollerValidator diceRollerValidatorDelegateImpl) {
        return new DiceRollerValidateManager(diceRollerValidatorDelegateImpl);
    }

    @Bean
    IDiceRollerValidator diceRollerValidatorDelegateImpl() {
        return new DiceRollerValidatorDelegateImpl();
    }

    @Bean
    IDiceRollerPredicateExecutor diceRollerPredicateExecutorImpl() {
        return new DiceRollerPredicateExecutorImpl();
    }

}
