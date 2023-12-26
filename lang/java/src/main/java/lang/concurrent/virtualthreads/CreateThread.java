package lang.concurrent.virtualthreads;

import java.util.concurrent.ForkJoinPool;

/**
 * Create Virtual Threads
 * <p>
 * <a href="https://www.youtube.com/watch?v=UVoGE0GZZPI&list=RDCMUCmRtPmgnQ04CMUpSUqPfhxQ&index=2">Reference</a>
 *
 * @author igaozp
 * @since 2022/10/2
 */
public class CreateThread {
    public static void main(String[] args) throws InterruptedException {
        var pthread = Thread.ofPlatform()
                .unstarted(() -> System.out.println(Thread.currentThread()));
        pthread.start();
        pthread.join();

        var vthread = Thread.ofVirtual()
                .unstarted(() -> System.out.println(Thread.currentThread()));
        vthread.start();
        vthread.join();

        var task = ForkJoinPool.commonPool()
                .submit(() -> System.out.println(Thread.currentThread()));
        task.join();
    }
}
