package xyz.andornot;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@Slf4j
@SpringBootTest
public class MyEsClientConfigTest {
    @Autowired
    private ElasticsearchClient myEsClient;

    @Test
    public void test() throws IOException {
        log.info("Es count test {}", myEsClient.count(s -> s.index("books")));
    }
}
