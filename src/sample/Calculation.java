package sample;

import java.util.Random;

public class Calculation {

    private int userNumber;
    private String result;
    private int resultPK;

    public Calculation(int userNumber, int resultPK) {
        this.userNumber = userNumber;
        this.resultPK = resultPK;
    }

    public String getResult() {
        return result;
    }

    public int getUserNumber() {
        return userNumber;
    }

    public int getResultPK() {
        return resultPK;
    }

    public void computation() {
        result = (userNumber == resultPK)?"Ты угадал!":"Ты не угадал!";
    }

}
