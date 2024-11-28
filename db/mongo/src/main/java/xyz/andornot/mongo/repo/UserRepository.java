package xyz.andornot.mongo.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import xyz.andornot.mongo.domain.User;

public interface UserRepository extends MongoRepository<User, String> {
}
