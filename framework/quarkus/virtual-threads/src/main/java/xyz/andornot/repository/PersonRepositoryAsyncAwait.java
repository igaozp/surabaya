package xyz.andornot.repository;

import io.quarkus.runtime.StartupEvent;
import io.vertx.mutiny.pgclient.PgPool;
import io.vertx.mutiny.sqlclient.Row;
import io.vertx.mutiny.sqlclient.RowSet;
import io.vertx.mutiny.sqlclient.Tuple;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import net.datafaker.Faker;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.logging.Logger;
import xyz.andornot.domain.Person;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class PersonRepositoryAsyncAwait {
    @Inject
    PgPool pgPool;
    @Inject
    Logger log;

    public Person save(Person person) {
        var id = pgPool
                .preparedQuery(" INSERT INTO person(name, age, gender) VALUES ($1, $2, $3) RETURNING id ")
                .executeAndAwait().iterator().next().getLong("id");
        person.setId(id);
        return person;
    }

    public List<Person> findAll() {
        log.info(STR."findAll()\{Thread.currentThread()}");
        RowSet<Row> rows = pgPool.preparedQuery("SELECT id, name, age, gender FROM person")
                .executeAndAwait();
        return iterateAndCreate(rows);
    }

    public Person findById(Long id) {
        var rowSet = pgPool.preparedQuery("SELECT id, name, age, gender FROM person WHERE id = $1")
                .executeAndAwait(Tuple.of(id));
        var persons = iterateAndCreate(rowSet);
        return persons.isEmpty() ? null : persons.getFirst();
    }

    public List<Person> findByName(String name) {
        var rowSet = pgPool.preparedQuery("SELECT id, name, age. gender FROM person WHERE id = $1")
                .executeAndAwait(Tuple.of(name));
        return iterateAndCreate(rowSet);
    }

    public List<Person> findByAgeGreaterThan(int age) {
        var rowSet = pgPool.preparedQuery("SELECT id, name, age, gender FROM person WHERE age > $1")
                .executeAndAwait(Tuple.of(age));
        return iterateAndCreate(rowSet);
    }

    private List<Person> iterateAndCreate(RowSet<Row> rowSet) {
        var personList = new ArrayList<Person>();
        rowSet.forEach(row -> personList.add(Person.from(row)));
        return personList;
    }

    @Inject
    @ConfigProperty(name = "myapp.schema.create", defaultValue = "true")
    boolean schemaCreate;

    void config(@Observes StartupEvent event) {
        if (schemaCreate) {
            initDb();
        }
    }

    private void initDb() {
        List<Tuple> persons = new ArrayList<>();
        Faker faker = new Faker();
        for (int i = 0; i < 1000; i++) {
            var name = faker.name().fullName();
            var gender = faker.gender().binaryTypes().toLowerCase();
            var age = faker.number().numberBetween(18, 65);
            var externalId = faker.number().numberBetween(100000, 999999);
            persons.add(Tuple.of(name, age, gender, externalId));
        }

        pgPool.query("DROP TABLE IF EXISTS person").execute()
                .flatMap(_ -> pgPool.query(
                        """
                                create table person (
                                    id serial primary key,
                                    name varchar(255),
                                    gender varchar(255),
                                    age int,
                                    external_id int
                                )
                                """
                ).execute())
                .flatMap(_ ->
                        pgPool.preparedQuery("INSERT INTO person (name, age, gender, external_id) values ($1, $2, $3, $4)")
                                .executeBatch(persons)
                )
                .await().indefinitely();
    }
}
