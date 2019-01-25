package Utils;

import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/*
    TODO sa fac sa nu poate scrie litere
 */
public class CustomStringConverter {
    public static int convertStringToInt(TextField field) {
        String aux = field.getText();
        if(aux.compareTo("")==0 || aux == null) {
            return -1;
        }
        else return Integer.parseInt(aux);
    }
    public static int convertStringToInt(ComboBox comboBox) {
        if(comboBox.getSelectionModel().isEmpty()) {
            return -1;
        }
        else {
            String aux = (String) comboBox.getSelectionModel().getSelectedItem();
            if(aux.compareTo("")==0) {
                return -1;
            }
            return Integer.parseInt(aux);

        }

    }
    public static double convertStringToDouble(ComboBox comboBox) {
        if(comboBox.getSelectionModel().isEmpty()) {
            return -1;
        }
        else {
            String aux = (String) comboBox.getSelectionModel().getSelectedItem();
            if(aux.compareTo("")==0) {
                return -1;
            }
            return Integer.parseInt(aux);

        }
    }
}
