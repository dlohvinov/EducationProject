package iot.lviv.ua.educationproject;

/**
 * Created by Daniil on 4/8/2018.
 */

public class Class {
    private String typeOfClass;
    private int positiveRate;
    private int negativeRate;
    private int neutralRate;

    public String getTypeOfClass() {
        return typeOfClass;
    }

    public void setTypeOfClass(String typeOfClass) {
        this.typeOfClass = typeOfClass;
    }

    public int getPositiveRate() {
        return positiveRate;
    }

    public void setPositiveRate(int positiveRate) {
        this.positiveRate = positiveRate;
    }

    public int getNegativeRate() {
        return negativeRate;
    }

    public void setNegativeRate(int negativeRate) {
        this.negativeRate = negativeRate;
    }

    public int getNeutralRate() {
        return neutralRate;
    }

    public void setNeutralRate(int neutralRate) {
        this.neutralRate = neutralRate;
    }
}
