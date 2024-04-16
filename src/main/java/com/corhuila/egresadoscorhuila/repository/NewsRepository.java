package com.corhuila.egresadoscorhuila.repository;

import com.corhuila.egresadoscorhuila.entity.News;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepository  extends MongoRepository<News, Long> {
}
