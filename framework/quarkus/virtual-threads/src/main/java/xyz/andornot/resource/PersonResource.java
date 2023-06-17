package xyz.andornot.resource;

import io.smallrye.common.annotation.RunOnVirtualThread;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import org.jboss.logging.Logger;
import xyz.andornot.domain.Person;
import xyz.andornot.repository.PersonRepositoryAsyncAwait;

import java.util.List;

@Path("/persons")
@RunOnVirtualThread
@Produces("application/json")
@Consumes("application/json")
public class PersonResource {
    @Inject
    PersonRepositoryAsyncAwait personRepository;
    @Inject
    Logger log;

    @POST
    public Person addPerson(Person person) {
        return personRepository.save(person);
    }

    @GET
    public List<Person> getPersons() {
        return personRepository.findAll();
    }

    @GET
    @Path("/name/{name}")
    public List<Person> getPersonsByName(@PathParam("name") String name) {
        return personRepository.findByName(name);
    }

    @GET
    @Path("/age-greater-than/{age}")
    public List<Person> getPersonsByName(@PathParam("age") int age) {
        return personRepository.findByAgeGreaterThan(age);
    }

    @GET
    @Path("/{id}")
    public Person getPersonById(@PathParam("id") Long id) {
        log.infof("(%s) getPersonById(%d)", Thread.currentThread(), id);
        return personRepository.findById(id);
    }
}
