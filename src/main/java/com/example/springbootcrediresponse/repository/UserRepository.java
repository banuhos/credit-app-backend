package com.example.springbootcrediresponse.repository;

import com.example.springbootcrediresponse.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface UserRepository extends MongoRepository<User, Long> {
    User save(User saved);
}
