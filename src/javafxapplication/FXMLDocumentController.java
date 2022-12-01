package javafxapplication;

import java.awt.Component;
import java.awt.Toolkit;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.SimpleFormatter;

public class FXMLDocumentController implements Initializable {
    
    // Объявление логировцика типа java.util.logging.Logger 
    static java.util.logging.Logger log = java.util.logging.Logger.getLogger(FXMLDocumentController.class.getName());

    // Объявление логировцика типа org.apache.log4j.Logger
    static org.apache.log4j.Logger log4j = org.apache.log4j.Logger.getLogger(FXMLDocumentController.class);
    
    @FXML
    private Button button_Math;
    @FXML
    private Label label_a;
    @FXML
    private Label label_b;
    @FXML
    private Label label_x;
    @FXML
    private Label label_otvet;
    @FXML
    private TextField textField_A;
    @FXML
    private TextField textField_B;
    @FXML
    private TextField textField_X;
    @FXML
    private Label label_c;
    @FXML
    private TextField textField_C;
    @FXML
    private Button button_Clear;
    @FXML
    private Button button_Exit;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void buttonMathAction(ActionEvent event) throws IOException  {
        
        // Инициализация логировцика типа java.util.logging.Logger 
        // с файлом не более 100 КБ и не более 3 файлов-логов с дозаписью логов
        Handler fileHandler = new FileHandler("logging.log", 100 *  1024, 3, true);
        fileHandler.setFormatter(new SimpleFormatter());
        log.addHandler(fileHandler);

        // Технология логирования java.util.logging
        log.setLevel(Level.ALL);
        log.info("Start");
        log.log(Level.INFO, "Запись лога с уровнем INFO (информационная)");
        log.info("Some message");

        try {
            throw new Exception("ERR!");
        } catch (Exception ex) {
            log.log(Level.SEVERE, "My Exception: {0}", ex.getMessage());
        }

        log.info("End");
        // -----------------------------------------

        // Технология логирования log4j
        log4j.info("Start log4j");
        log4j.info("Hi Logger info!");
        org.apache.log4j.LogManager.shutdown();
        
        double a, b, x, c, y;
        try {
            a = Double.parseDouble(textField_A.getText());
            b = Double.parseDouble(textField_B.getText());
            x = Double.parseDouble(textField_X.getText());
            c = Double.parseDouble(textField_C.getText());
        } catch (Exception ex) {
            Toolkit.getDefaultToolkit().beep();
            Component rootPane = null;
            JOptionPane.showMessageDialog(rootPane, "Ошибка введенных данных!", "Ошибка ввода",
                    JOptionPane.ERROR_MESSAGE);
            textField_A.requestFocus();
            label_otvet.setText("В введенных значениях допущены ошибки");
            textField_A.setText("");
            textField_B.setText("");
            textField_X.setText("");
            return;
        }
        if (x < 4) {
            y = (Math.pow(x, 2)+Math.pow(a, 2))*c/(2*b);
            //label_otvet.setText("Ответ: " + String.format("%.2f",y));
        } else {
            y = (Math.pow(x,3)*(a+b));
            //label_otvet.setText("Ответ: " + String.format("%.2f",y));
        }
        if (!(Double.isNaN(y)) && (!Double.isInfinite(y))) {
            label_otvet.setText("Ответ: " + String.format("%.2f", y));
            log4j.info("End log4j");
        } else {
            log.log(Level.SEVERE, "Запись лога с уровнем SEVERE (серъёзная ошибка)");
            log4j.fatal("Fatal error!");
            label_otvet.setText("Нет ответа");
        }
    }

    @FXML
    private void buttonClearAction(ActionEvent event) {
        label_otvet.setText("Ответ: ");
        textField_A.setText("");
        textField_B.setText("");
        textField_X.setText("");
        textField_C.setText("");
    }

    @FXML
    private void buttonExitAction(ActionEvent event) {
        System.exit(0);
    }

}
