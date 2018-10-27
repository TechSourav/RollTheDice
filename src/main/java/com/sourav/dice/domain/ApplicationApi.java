package com.sourav.dice.domain;


import com.sourav.dice.dto.DiceRollerResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class ApplicationApi {

    @Autowired
    private IDiceRollerExecutor diceRollerManager;

    @Autowired
    private DiceRollerResult result;

    public static void run(ApplicationContext context, String[] args) {
        ApplicationApi api = context.getBean(ApplicationApi.class);
        api.execute(args);
    }

    private void execute(String[] args) {
        try {
            result = diceRollerManager.execute(args);
            if (result.valid() && result.isCalculated()) {
                System.out.println(result.message());
                System.out.println(result.finalResult());
            } else
                System.out.println("Invalid Dice Rolled : " + result.message());
        } catch (Exception e) {
            System.out.println(result.message());
            System.out.println("Exception thrown: " + e.getMessage());
            e.printStackTrace();

        }
    }
}
