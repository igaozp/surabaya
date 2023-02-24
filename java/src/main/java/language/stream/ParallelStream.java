package language.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ParallelStream {
    private static final Integer N = 100_000_000;

    public static void main(String[] args) {
        System.out.println(parallelDiceRolls());
        var manualDiceRolls = new ManualDiceRolls();
        manualDiceRolls.simulateDiceRoles();
    }

    public static Map<Integer, Double> parallelDiceRolls() {
        var fraction = 1.0 / N;
        return IntStream.range(0, N).parallel()
                .mapToObj(s -> twoDiceThrows())
                .collect(Collectors.groupingBy(side -> side, Collectors.summingDouble(n -> fraction)));
    }

    private static Integer twoDiceThrows() {
        var random = ThreadLocalRandom.current();
        var firstThrow = random.nextInt(1, 7);
        var secondThrow = random.nextInt(1, 7);
        return firstThrow + secondThrow;
    }

    static class ManualDiceRolls {
        private static final int N = 100_000_000;
        private final Map<Integer, Double> results;
        private final int numberOfThreads;
        private final ExecutorService executor;
        private final int workPerThread;

        private final double fraction;

        public ManualDiceRolls() {
            this.fraction = 1.0 / N;
            this.numberOfThreads = Runtime.getRuntime().availableProcessors();
            this.results = new ConcurrentHashMap<>();
            this.executor = Executors.newFixedThreadPool(numberOfThreads);
            this.workPerThread = N / numberOfThreads;
        }

        public void simulateDiceRoles() {
            var futures = submitJob();
            awaitCompletion(futures);
            printResults();
        }

        private void printResults() {
            results.entrySet().forEach(System.out::println);
        }

        private List<Future<?>> submitJob() {
            var futures = new ArrayList<Future<?>>();
            for (int i = 0; i < numberOfThreads; i++) {
                futures.add(executor.submit(makeJob()));
            }
            return futures;
        }

        private Runnable makeJob() {
            return () -> {
                var random = ThreadLocalRandom.current();
                for (int i = 0; i < workPerThread; i++) {
                    var entry = twoDiceThrows(random);
                    accumulateResult(entry);
                }
            };
        }

        private void accumulateResult(int entry) {
            results.compute(entry, (key, previous) ->
                    previous == null ? fraction : previous + fraction);
        }

        private int twoDiceThrows(ThreadLocalRandom random) {
            var firstThrow = random.nextInt(1, 7);
            var secondThrow = random.nextInt(1, 7);
            return firstThrow + secondThrow;
        }

        private void awaitCompletion(List<Future<?>> futures) {
            futures.forEach((future) -> {
                try {
                    future.get();
                } catch (InterruptedException | ExecutionException e) {
                    throw new RuntimeException(e);
                }
            });
            executor.shutdown();
        }
    }
}
