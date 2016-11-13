package calculator;

import java.math.BigDecimal;
import java.math.MathContext;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

/**
 * Klasa <code>CalculatorFXMLController</code> reprezentuje działanie kalkulatora. 
 * Posiada pola tekstowe do wprowadzania wartości, pole wynikowe oraz przełączniki 
 * wyboru działania. Działania wykonywane są automatycznie po zmianie zawartości pól 
 * lub po zmianie typu operacji. 
 * @author AleksanderSklorz
 */
public class CalculatorFXMLController implements Initializable {
    @FXML
    private RadioButton additionRadioButton, subtractionRadioButton, multiplicationRadioButton, divisionRadioButton; 
    @FXML
    private Label operationLabel;
    @FXML
    private TextField firstNumberTextField, secondNumberTextField, resultTextField;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        additionRadioButton.setOnAction(event -> {
            operationLabel.setText("+");
            calculate();
        });
        subtractionRadioButton.setOnAction(event -> {
            operationLabel.setText("-");
            calculate();
        });
        multiplicationRadioButton.setOnAction(event -> {
            operationLabel.setText("*");
            calculate();
        });
        divisionRadioButton.setOnAction(event -> {
            operationLabel.setText("/");
            calculate();
        });
        firstNumberTextField.textProperty().addListener(listener -> {
            calculate();
        });
        secondNumberTextField.textProperty().addListener(listener -> {
            calculate();
        });
    }
    /**
     * Wykonuje wybrane działanie. Aktualne działanie jest wyświetlone jako etykieta
     * pomiędzy wprowadzonymi wartościami liczbowymi. Wyświetla komunikat w przypadku wprowadzenia
     * niedozwolonych wartości (innych niż liczby całkowite lub zmiennoprzecinkowe). 
     */
    public void calculate(){
        char operation = operationLabel.getText().charAt(0); 
        String sFirstNumber = firstNumberTextField.getText().trim(), sSecondNumber = secondNumberTextField.getText().trim();
        if(sFirstNumber.matches("[0-9]([\\.][0-9]+|[0-9]*)") && sSecondNumber.matches("[0-9]([\\.][0-9]+|[0-9]*)")){
            BigDecimal firstNumber = BigDecimal.valueOf(Double.parseDouble(sFirstNumber)),
                    secondNumber = BigDecimal.valueOf(Double.parseDouble(sSecondNumber));
            switch(operation){
                case '+':
                    resultTextField.setText((firstNumber.add(secondNumber)).toString());
                    break;
                case '-':
                    resultTextField.setText((firstNumber.subtract(secondNumber)).toString());
                    break;
                case '*':
                    resultTextField.setText((firstNumber.multiply(secondNumber)).toString());
                    break;
                case '/':
                    resultTextField.setText((firstNumber.divide(secondNumber, MathContext.DECIMAL128)).toString());
            }
        }else{
            if(sFirstNumber.matches("\\S+") || sSecondNumber.matches("\\S+") || sFirstNumber.matches("[\\S\\s]+") || sSecondNumber.matches("[\\S\\s]+")){
                resultTextField.setText("Niedozwolony znak!");
            }
        }
    }
}
