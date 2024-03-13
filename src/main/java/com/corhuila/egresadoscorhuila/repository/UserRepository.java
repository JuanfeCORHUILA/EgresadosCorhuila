package com.corhuila.egresadoscorhuila.repository;

import com.corhuila.egresadoscorhuila.entity.Users;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.math.BigInteger;
import java.util.List;

public interface UserRepository extends MongoRepository<Users, Long> {

    Users findByNoIdentificacionAndPassword(BigInteger noIdentificacion, String password);

    List<Users> findBySedeUniversitaria(String sede);

}
