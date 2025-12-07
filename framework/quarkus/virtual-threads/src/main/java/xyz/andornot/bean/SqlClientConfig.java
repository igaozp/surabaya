package xyz.andornot.bean;

import io.vertx.mutiny.pgclient.PgPool;
import io.vertx.mutiny.sqlclient.SqlClient;
import io.vertx.pgclient.PgConnectOptions;
import io.vertx.sqlclient.PoolOptions;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Singleton;

@ApplicationScoped
public class SqlClientConfig {

    @Produces
    @Singleton
    public SqlClient initSqlClient() {
        // fixme
        PgConnectOptions connectOptions = new PgConnectOptions()
                .setPort(5432)
                .setHost("the-host")
                .setDatabase("the-db")
                .setUser("user")
                .setPassword("secret");

        // Pool options
        PoolOptions poolOptions = new PoolOptions()
                .setMaxSize(5);

        // Create the client pool
        return PgPool.client(connectOptions, poolOptions);
    }
}
