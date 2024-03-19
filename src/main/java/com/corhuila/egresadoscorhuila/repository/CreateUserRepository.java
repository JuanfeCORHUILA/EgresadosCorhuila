package com.corhuila.egresadoscorhuila.repository;

import com.corhuila.egresadoscorhuila.entity.CreateUsers;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreateUserRepository extends MongoRepository<CreateUsers, Long> {

    boolean existsByNoIdentificacion(Long noIdentificacion);

    boolean existsByEmailInstitucional(String emailInstitucional);
}
