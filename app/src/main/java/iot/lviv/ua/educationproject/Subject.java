package iot.lviv.ua.educationproject;

/**
 * Created by Daniil on 4/7/2018.
 */


//TEMPLATE
//Havent ended because of abscence of scale logic
public class Subject {
    private String name;

    private int positiveRates;
    private int negativeRates;
    private int neutralRates;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPositiveRates() {
        return positiveRates;
    }

    public void setPositiveRates(int positiveRates) {
        this.positiveRates = positiveRates;
    }

    public int getNegativeRates() {
        return negativeRates;
    }

    public void setNegativeRates(int negativeRates) {
        this.negativeRates = negativeRates;
    }

    public int getNeutralRates() {
        return neutralRates;
    }

    public void setNeutralRates(int neutralRates) {
        this.neutralRates = neutralRates;
    }
}
