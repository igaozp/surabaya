package xyz.andornot.config;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import org.apache.hc.core5.ssl.SSLContexts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;

@Component
public class EsClientConfig {
    @Value("${spring.elasticsearch.uris}")
    private String url;
    @Value("${spring.elasticsearch.apiKey}")
    private String apiKey;
    @Value("${spring.elasticsearch.caPath}")
    private String caCert;

    @Bean
    public ElasticsearchClient myEsClient() throws CertificateException, IOException, KeyStoreException,
            NoSuchAlgorithmException, KeyManagementException {
        var caCertificatePath = Paths.get(caCert);
        var factory = CertificateFactory.getInstance("X.509");
        Certificate trustedCa;
        try (InputStream is = Files.newInputStream(caCertificatePath)) {
            trustedCa = factory.generateCertificate(is);
        }
        var trustStore = KeyStore.getInstance("pkcs12");
        trustStore.load(null, null);
        trustStore.setCertificateEntry("ca", trustedCa);
        var sslContextBuilder = SSLContexts.custom()
                .loadTrustMaterial(trustStore, null);
        final var sslContext = sslContextBuilder.build();

        var objectMapper = new ObjectMapper();
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
        var jsonpMapper = new JacksonJsonpMapper(objectMapper);

        var esClient = ElasticsearchClient.of(b -> b
                .host(url)
                .apiKey(apiKey)
                .sslContext(sslContext)
                .jsonMapper(jsonpMapper)
        );

        return esClient;
    }
}
