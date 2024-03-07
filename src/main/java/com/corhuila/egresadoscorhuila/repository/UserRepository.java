package com.corhuila.egresadoscorhuila.repository;

import com.corhuila.egresadoscorhuila.entity.Users;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.math.BigInteger;

public interface UserRepository extends MongoRepository<Users, Long> {

    Users findByNoIdentificacionAndPassword(BigInteger noIdentificacion, String password);

}
