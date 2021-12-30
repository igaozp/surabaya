package language.spi;

import java.util.ServiceLoader;

/**
 * SPI demo
 *
 * @author igaozp
 */
public class ServiceProviderInterface {
    public static void main(String[] args) {
        // load service
        ServiceLoader<Search> searches = ServiceLoader.load(Search.class);
        searches.forEach(service -> System.out.println(service.searchDoc("test")));
    }
}

