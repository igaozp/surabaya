package xyz.andornot.caffeine;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.concurrent.TimeUnit;

public class SimpleCacheTest {
    @Test
    public void manual() {
        Cache<String, Set<String>> cache = Caffeine.newBuilder()
                .expireAfterWrite(10, TimeUnit.MINUTES)
                .maximumSize(10_000)
                .build();

        var key = "test_key";
        // 查找一个缓存元素，没有查找到的时候返回 null
        var value = cache.getIfPresent(key);
        // 查找缓存，如果缓存不存在则生成缓存元素,  如果无法生成则返回 null
        value = cache.get(key, _ -> createExpensiveValue(key));
        // 添加或者更新一个缓存元素
        cache.put(key, value);
        // 移除一个缓存元素
        cache.invalidate(key);
    }

    private Set<String> createExpensiveValue(String key) {
        return Set.of(
                "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"
        );
    }
}
