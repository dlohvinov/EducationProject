package iot.lviv.ua.educationproject;

/**
 * Created by Daniil on 4/8/2018.
 */

public class Class {
    private String typeOfClass;
    private boolean positiveRate;
    private boolean negativeRate;
    private boolean neutralRate;

    public String getTypeOfClass() {
        return typeOfClass;
    }

    public void setTypeOfClass(String typeOfClass) {
        this.typeOfClass = typeOfClass;
    }

    public boolean isPositiveRate() {
        return positiveRate;
    }

    public void setPositiveRate(boolean positiveRate) {
        this.positiveRate = positiveRate;
    }

    public boolean isNegativeRate() {
        return negativeRate;
    }

    public void setNegativeRate(boolean negativeRate) {
        this.negativeRate = negativeRate;
    }

    public boolean isNeutralRate() {
        return neutralRate;
    }

    public void setNeutralRate(boolean neutralRate) {
        this.neutralRate = neutralRate;
    }
}
