package lang.loom.virtualthreads;

import java.time.Duration;
import java.time.Instant;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

/**
 * Bulk Virtual Threads
 * <p>
 * <a href="https://github.com/JosePaumard/Loom_demo/blob/main/src/main/java/org/paumard/loom/threads/E_HowManyVirtualThreads.java">Reference</a>
 *
 * @author igaozp
 * @since 2022/10/2
 */
public class BulkThread {
    public static void main(String[] args) throws InterruptedException {
        var pools = ConcurrentHashMap.newKeySet();
        var pThreads = ConcurrentHashMap.newKeySet();
        var pool = Pattern.compile("ForkJoinPool-[\\d?]");
        var worker = Pattern.compile("worker-[\\d?]");

        var threads = IntStream.range(0, 1_000_000)
                .mapToObj(_ -> Thread.ofVirtual().unstarted(() -> {
                    try {
                        Thread.sleep(2_000);
                        var name = Thread.currentThread().toString();
                        var poolMatcher = pool.matcher(name);
                        if (poolMatcher.find()) {
                            pools.add(poolMatcher.group());
                        }
                        var workMatcher = worker.matcher(name);
                        if (workMatcher.find()) {
                            pThreads.add(workMatcher.group());
                        }
                    } catch (InterruptedException e) {
                        throw new AssertionError(e);
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

        System.out.println(STR."# cores = \{Runtime.getRuntime().availableProcessors()}");
        System.out.println(STR."Time = \{Duration.between(begin, end)}");
        System.out.println("Pools");
        pools.forEach(System.out::println);
        System.out.println(STR."Platform threads (\{pThreads.size()})");
        new TreeSet<>(pThreads).forEach(System.out::println);
    }
}
