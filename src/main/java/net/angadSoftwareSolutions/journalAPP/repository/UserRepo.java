package net.angadSoftwareSolutions.journalAPP.repository;

import net.angadSoftwareSolutions.journalAPP.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepo extends MongoRepository<User, ObjectId> {

    User findByUsername(String username);
}
