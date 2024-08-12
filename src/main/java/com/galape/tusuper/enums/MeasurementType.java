package com.galape.tusuper.enums;

public enum MeasurementType {
    GRAMS("g"),
    KILOGRAMS("kg"),
    MILILITERS("ml"),
    LITERS("l"),
    CUBIC_CENTIMETERS("cm3");

    private String symbol;

    MeasurementType(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}
