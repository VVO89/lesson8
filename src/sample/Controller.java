package sample;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.Optional;
import java.util.Random;
import java.util.Scanner;

public class Controller {

    private int TRY = 3;
    private int resPK = 0;

    @FXML
    private Label remainTry;

    @FXML
    private TextField userNumber;

    @FXML
    private TextField resultField;

    @FXML
    private Button butonCalc;

    @FXML
    private TextField resultPK;

    @FXML
    void doCalc() {

        if (TRY > 0) {
            Calc();
        } else{
            questionUser();
        }
    }

    public void questionUser() {
        // задаем пользователю вопрос
        Alert al = new Alert(Alert.AlertType.CONFIRMATION);
        al.setTitle("Информация");
        al.setHeaderText("Правильный ответ: ".concat(Integer.toString(resPK)));
        al.setContentText("Попробовать еще раз?");
        // создаем кнопки
        ButtonType button_true = new ButtonType("ДА");
        ButtonType button_false = new ButtonType("НЕТ");
        ButtonType button_cancel = new ButtonType("ОТМЕНА");
        // вывод кнопок на экран
        al.getButtonTypes().setAll(button_true,button_false,button_cancel);

        Optional<ButtonType> result = al.showAndWait();
        // перебираем варианты нажания кнопок
        if (result.get() == button_true){
            // повтор игры
            TRY = 3;
            Random rand = new Random();
            resPK = rand.nextInt(9);
            userNumber.setText("");
            resultField.setText("");
            resultPK.setText("");
            al.close();
            remainTry.setText("Осталось попыток".concat(" ".concat(Integer.toString(TRY))));
        }else if (result.get() == button_false){
            System.exit(0);// выход из приложения
        }else if (result.get() == button_cancel){
            System.exit(0); // выход из приложения
        }
    }

    public void Calc() {

        int usNumber = 0;

        usNumber = getUsNumber();

        if (usNumber == 0){ return;}

        TRY--; // уменьшаем количество попыток
        // Загадваем число, если еще не загадали
        if (resPK == 0) {
            Random rand = new Random();
            resPK = rand.nextInt(9);
        }

        resultPK.setText(Integer.toString(resPK));
        remainTry.setText("");
        remainTry.setText("Осталось попыток".concat(" " + Integer.toString(TRY)));

        Calculation Calc = new Calculation(usNumber, resPK);

        Calc.computation();
        resultField.setText(Calc.getResult());

        if(Calc.getUserNumber() == Calc.getResultPK()){
            questionUser();
        }
    }

    public int getUsNumber() {

        boolean this_Number = false;
        int result = 0;

        while (!this_Number) {

            String resStr = userNumber.getText();

            if (isNumeric(resStr)) {
                result = Integer.parseInt(resStr);
                this_Number = true;
                break;
            } else {
                // откроем форму Alert с информацией
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Внимание!");
                alert.setHeaderText(null);
                alert.setContentText("Вы ввели не числовое значение. Введите число");
                alert.showAndWait();
                break;
            }
        }
        return result;
    }

    private boolean isNumeric(String strNumber){

        try {
            int a = Integer.parseInt(strNumber);
        }catch (NumberFormatException e){
            return false;
        }
        return true;
    }

}

