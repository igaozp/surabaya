package lang.loom.virtualthreads;

import java.util.stream.IntStream;

/**
 * Virtual Treads sleeping
 * <p>
 * <a href="https://youtu.be/UVoGE0GZZPI?t=403">Reference</a>
 *
 * @author igaozp
 * @since 2022/10/2
 */
public class SleepingThread {
    public static void main(String[] args) throws InterruptedException {
        // Virtual Threads 进行睡眠的时候，会产生线程切换
        var threads = IntStream.range(0, 10)
                .mapToObj(
                        index -> Thread.ofVirtual().unstarted(() -> {
                            if (index == 0) {
                                System.out.println(Thread.currentThread());
                            }
                            try {
                                Thread.sleep(10);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                            if (index == 0) {
                                System.out.println(Thread.currentThread());
                            }
                        })
                ).toList();
        threads.forEach(Thread::start);
        for (Thread thread : threads) {
            thread.join();
        }
    }
}
