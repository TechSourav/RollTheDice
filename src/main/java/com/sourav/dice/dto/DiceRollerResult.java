package com.sourav.dice.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Getter
@Accessors(fluent = true)
@NoArgsConstructor
@Component
public class DiceRollerResult {
    private boolean valid;
    private String message;
    private boolean isCalculated = false;
    private int finalResult;

    public final static class DiceRollerResultBuilder {
        public boolean valid;
        public String message;
        public boolean isCalculated = false;
        public int finalResult;

        public DiceRollerResultBuilder with(Consumer<DiceRollerResultBuilder> builderFunction) {
            builderFunction.accept(this);
            return this;
        }

        public DiceRollerResult build() {
            DiceRollerResult result = new DiceRollerResult();
            result.valid = this.valid;
            result.message = this.message;
            result.finalResult = this.finalResult;
            result.isCalculated = this.isCalculated;

            return result;
        }
    }
}
