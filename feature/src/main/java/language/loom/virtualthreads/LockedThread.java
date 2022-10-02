package language.loom.virtualthreads;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

/**
 * Locked Virtual Threads
 * <p>
 * https://github.com/JosePaumard/Loom_demo/blob/main/src/main/java/org/paumard/loom/threads/F3_LockedThread.java
 *
 * @author igaozp
 * @since 2022/10/2
 */
public class LockedThread {

    private static final Lock lock = new ReentrantLock();
    private static int counter = 0;

    public static void main(String[] args) throws InterruptedException {
        var threads = IntStream.range(0, 4_000)
                .mapToObj(index -> Thread.ofVirtual()
                        .unstarted(() -> {
                            if (index == 10) {
                                System.out.println(Thread.currentThread());
                            }
                            lock.lock();
                            try {
                                counter++;
                            } finally {
                                lock.unlock();
                            }
                            if (index == 10) {
                                System.out.println(Thread.currentThread());
                            }
                            lock.lock();
                            try {
                                counter++;
                            } finally {
                                lock.unlock();
                            }
                            if (index == 10) {
                                System.out.println(Thread.currentThread());
                            }
                            lock.lock();
                            try {
                                counter++;
                            } finally {
                                lock.unlock();
                            }
                            if (index == 10) {
                                System.out.println(Thread.currentThread());
                            }
                        }))
                .toList();

        var begin = Instant.now();
        for (var thread : threads) {
            thread.start();
        }
        for (var thread : threads) {
            thread.join();
        }
        var end = Instant.now();
        lock.lock();
        try {
            System.out.println("# counter = " + counter);
        } finally {
            lock.lock();
        }
        System.out.println("Duration = " + Duration.between(begin, end));
    }
}
