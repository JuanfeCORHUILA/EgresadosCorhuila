package com.corhuila.egresadoscorhuila.repository;

import com.corhuila.egresadoscorhuila.entity.Users;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<Users, Long> {

    List<Users> findBySedeUniversitaria(String sede);

}
