package Utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Clasa folosita pentru lucru cu date
 * Principala functie e de a converti saptamana din an
 * in saptamana scolara
 */
public class Cronos {

    private static Cronos instance = null;
    private Cronos() {}

    public static Cronos getInstance() {

        if (instance == null) {
            instance = new Cronos();
        }
        return instance;
    }

    public int getSaptCurent() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int week = cal.get(Calendar.WEEK_OF_YEAR);

        if(week > 3) {
            return week-39;
        }
        else if (week == 2) {
            return 2;
        }
        else if (week == 3) {
            return 14;
        }
        else {
            return -1;
        }

    }
}


