package HW12;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;

public class TimeFromBegin extends Thread {
    private long beginTime;
    public Exchanger<Long> ex;
    Long timeNow = 0L;

    public TimeFromBegin(long beginTime, Exchanger<Long> exchanger) {
        super("Stream 1");
        this.beginTime = beginTime;
        this.ex = exchanger;
    }

    @Override
    public void run() {
        System.out.println("В методі run() нового потоку 1 "
                + Thread.currentThread().getName());

        try {
            while (true)
            {
                timeNow = TimeUnit.NANOSECONDS.toSeconds((System.nanoTime() - beginTime));
                System.out.println("Час, що минув від початку сесії (запуску програми):"
                        + timeNow
                        + " секунд");
                ex.exchange(timeNow);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
