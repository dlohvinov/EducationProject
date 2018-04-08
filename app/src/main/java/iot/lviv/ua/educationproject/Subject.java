package iot.lviv.ua.educationproject;

/**
 * Created by Daniil on 4/7/2018.
 */


//TEMPLATE
//Havent ended because of abscence of scale logic
public class Subject {
    private String name;

    private int numOfPositiveRates;
    private int numOfNegativeRates;
    private int numOfNeutralRates;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumOfPositiveRates() {
        return numOfPositiveRates;
    }

    public void setNumOfPositiveRates(int numOfPositiveRates) {
        this.numOfPositiveRates = numOfPositiveRates;
    }

    public int getNumOfNegativeRates() {
        return numOfNegativeRates;
    }

    public void setNumOfNegativeRates(int numOfNegativeRates) {
        this.numOfNegativeRates = numOfNegativeRates;
    }

    public int getNumOfNeutralRates() {
        return numOfNeutralRates;
    }

    public void setNumOfNeutralRates(int numOfNeutralRates) {
        this.numOfNeutralRates = numOfNeutralRates;
    }
}
