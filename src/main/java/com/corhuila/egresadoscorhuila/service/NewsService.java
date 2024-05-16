package com.corhuila.egresadoscorhuila.service;

import com.corhuila.egresadoscorhuila.dto.NewsDto;
import com.corhuila.egresadoscorhuila.entity.News;
import com.corhuila.egresadoscorhuila.response.ResponseGeneric;

public interface NewsService {

    ResponseGeneric findAll();

    ResponseGeneric save(NewsDto newsDto);

    ResponseGeneric update(Long id, News news);

    ResponseGeneric delete(Long id);
}
