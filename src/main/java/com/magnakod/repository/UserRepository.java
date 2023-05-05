package com.magnakod.repository;

import com.magnakod.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User,String> {
    boolean existsUserByUsernameAndPassword(String username, String password);
}
