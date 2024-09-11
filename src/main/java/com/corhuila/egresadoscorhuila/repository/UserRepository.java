package com.corhuila.egresadoscorhuila.repository;

import com.corhuila.egresadoscorhuila.entity.Users;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<Users, Long> {

    List<Users> findBySedeUniversitaria(String sede);

    Users findByNoIdentificacion(Long document);

    List<Users> findByGraduadoTrue();

    @Query(fields = "{ 'primerNombre': 1, 'segundoNombre': 1, 'primerApellido': 1, 'segundoApellido': 1 }", sort = "{ 'id': -1 }")
    List<Users> findTop5ByOrderByIdDesc();

}
