package xyz.andornot.crud;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@Slf4j
@SpringBootTest
public class CrudTests {
    @Autowired
    private ElasticsearchClient myEsClient;

    @Test
    public void count() throws IOException {
        log.info("book count {}", myEsClient.count(s -> s.index("books")).count());
    }
}
