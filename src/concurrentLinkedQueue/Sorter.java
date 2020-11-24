package concurrentLinkedQueue;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class Sorter implements Runnable{

    private static final DateTimeFormatter dateTimeFormatter =  DateTimeFormatter.ofPattern("HH:mm:ss", Locale.getDefault());
    private ConcurrentLinkedDeque<Integer> clqId = new ConcurrentLinkedDeque<>();
    private ConcurrentLinkedDeque<String> clqName = new ConcurrentLinkedDeque<>();


    @Override
    public void run() {

        while (!clqId.isEmpty()) {
            processPlastic();
        }
        System.out.println(LocalTime.now().format(dateTimeFormatter) + " -> There are not more plastics");
    }

    public void addPlastic(int plasticId, String threadName){
        clqId.add(plasticId);
        clqName.add(threadName);
    }

    private void processPlastic() {
        int id=clqId.remove();
        try {
            TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(2)+1);
            System.out.println(LocalTime.now().format(dateTimeFormatter) + " -> " + Thread.currentThread().getName() + ": The plastic " + id + " from " + clqName.remove() + " was processed");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
