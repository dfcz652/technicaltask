package com.example.technicaltask.repositories;

import com.example.technicaltask.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    Optional<User> findUserById(String id);

    Optional<List<User>> findByBirthDateBetween(LocalDate fromDate, LocalDate toDate);


}
