package com.sourav.dice.domain;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum DiceType {
    FOUR_SIDED(4),
    SIX_SIDED(6),
    EIGHT_SIDED(8),
    TEN_SIDED(10),
    TWELVE_SIDED(12),
    TWENTY_SIDED(20);

    private final int sideNo;

    private static Map<Integer, String> diceMap = new HashMap<>();

    static {
        diceMap.put(4, FOUR_SIDED.name());
        diceMap.put(6, SIX_SIDED.name());
        diceMap.put(8, EIGHT_SIDED.name());
        diceMap.put(10, TEN_SIDED.name());
        diceMap.put(12, TWELVE_SIDED.name());
        diceMap.put(20, TWENTY_SIDED.name());
    }

    public static final EnumSet<DiceType> all = EnumSet.of(FOUR_SIDED, SIX_SIDED, EIGHT_SIDED, TEN_SIDED, TWELVE_SIDED, TWENTY_SIDED);

    DiceType(final int num) {
        this.sideNo = num;
    }

    public int sideNo() {
        return this.sideNo;
    }

    public static Map<Integer, String> getDiceMap() {
        return DiceType.diceMap;
    }
}
