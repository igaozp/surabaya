package xyz.andornot.reactive.dao;

import org.springframework.stereotype.Repository;
import xyz.andornot.reactive.domain.City;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class CityRepository {
    private static final AtomicLong idGenerator = new AtomicLong(0);
    private final ConcurrentMap<Long, City> repository = new ConcurrentHashMap<>();

    public Long save(City city) {
        var id = idGenerator.incrementAndGet();
        city.withId(id);
        repository.put(id, city);
        return id;
    }

    public Collection<City> findAll() {
        return repository.values();
    }

    public City findCityById(Long id) {
        return repository.get(id);
    }

    public Long updateCity(City city) {
        repository.put(city.id(), city);
        return city.id();
    }

    public Long deleteCity(Long id) {
        repository.remove(id);
        return id;
    }
}
