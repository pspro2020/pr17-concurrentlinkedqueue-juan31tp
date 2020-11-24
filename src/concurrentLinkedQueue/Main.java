package concurrentLinkedQueue;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class Main {

    private static final int NHOPPERS = 5;
    private static final DateTimeFormatter dateTimeFormatter =  DateTimeFormatter.ofPattern("HH:mm:ss", Locale.getDefault());

    public static void main(String[] args) {

        Sorter sorter = new Sorter();
        Thread hoppers[] = new Thread[NHOPPERS];

        Thread sortearThread = new Thread(sorter);

        for(int i=0; i<NHOPPERS; i++){
            hoppers[i] = new Thread(new Hopper(sorter), "Hopper " + (i+1));
            hoppers[i].start();
        }

        try {
            TimeUnit.SECONDS.sleep(3);
            sortearThread.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
