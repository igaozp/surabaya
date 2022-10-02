package language.loom.virtualthreads;

import java.time.Duration;
import java.time.Instant;
import java.util.stream.IntStream;

/**
 * Synced Virtual Threads
 * <p>
 * https://github.com/JosePaumard/Loom_demo/blob/main/src/main/java/org/paumard/loom/threads/F1_SyncedThread.java
 *
 * @author igaozp
 * @since 2022/10/2
 */
public class SyncedThread {
    private static final Object lock = new Object();
    private static int counter = 0;

    public static void main(String[] args) throws InterruptedException {
        var threads = IntStream.range(0, 1_000)
                .mapToObj(index -> Thread.ofVirtual().unstarted(() -> {
                    if (index == 10) {
                        System.out.println(Thread.currentThread());
                    }
                    synchronized (lock) {
                        counter++;
                    }
                    if (index == 10) {
                        System.out.println(Thread.currentThread());
                    }
                    synchronized (lock) {
                        counter++;
                    }
                    if (index == 10) {
                        System.out.println(Thread.currentThread());
                    }
                    synchronized (lock) {
                        counter++;
                    }
                    if (index == 10) {
                        System.out.println(Thread.currentThread());
                    }
                })).toList();

        var begin = Instant.now();
        for (var thread : threads) {
            thread.start();
        }
        for (var thread : threads) {
            thread.join();
        }
        var end = Instant.now();
        synchronized (lock) {
            System.out.println("# counter = " + counter);
        }
        System.out.println("Duration = " + Duration.between(begin, end));
    }
}
