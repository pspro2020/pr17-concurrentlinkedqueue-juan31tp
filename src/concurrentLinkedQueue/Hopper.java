package concurrentLinkedQueue;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class Hopper implements Runnable {

    Sorter sorter;
    private static final DateTimeFormatter dateTimeFormatter =  DateTimeFormatter.ofPattern("HH:mm:ss", Locale.getDefault());
    private ConcurrentLinkedQueue<Integer> clq = new ConcurrentLinkedQueue<>(List.of(0, 1, 2, 3, 4, 5));

    public Hopper(Sorter sorter) {
        this.sorter=sorter;
    }

    @Override
    public void run() {
        while (!clq.isEmpty()){
            drop();
        }
        System.out.println(LocalTime.now().format(dateTimeFormatter) + " -> " + Thread.currentThread().getName() + ": No more elements");
    }

    private void drop() {
        int id=clq.remove();
        try {
            TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(2)+1);
            sorter.addPlastic(id, Thread.currentThread().getName());
            System.out.println(LocalTime.now().format(dateTimeFormatter) + " -> Plastic dropped");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
