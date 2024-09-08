package com.corhuila.egresadoscorhuila.service.impl;

import com.corhuila.egresadoscorhuila.dto.NewsDto;
import com.corhuila.egresadoscorhuila.entity.News;
import com.corhuila.egresadoscorhuila.repository.NewsRepository;
import com.corhuila.egresadoscorhuila.response.ResponseGeneric;
import com.corhuila.egresadoscorhuila.service.NewsService;
import com.corhuila.egresadoscorhuila.utils.Operations;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    NewsRepository newsRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public ResponseGeneric findAll() {
        try {
            var news = newsRepository.findAll();
            if (news.isEmpty()){
                return ResponseGeneric.builder()
                        .status("Not Content")
                        .message("No hay noticias para mostrar")
                        .codResponse(204)
                        .build();
            }

            List<News> newsShow = news.stream()
                    .filter(notice -> notice.getExpirationDate().after(new Date()))
                    .collect(Collectors.toList());

            return ResponseGeneric.builder()
                    .status("Ok")
                    .message("Noticias listadas exitosamente")
                    .codResponse(200)
                    .listObject(Collections.singletonList(newsShow))
                    .build();

        }catch (Exception e){
            return ResponseGeneric.builder()
                    .status("Bad Request")
                    .message(e.getMessage())
                    .codResponse(404)
                    .build();
        }
    }

    @Override
    public ResponseGeneric save(NewsDto newsDto) {

        try {
            var news = modelMapper.map(newsDto, News.class);
            news.setId(Operations.autoIncrement(newsRepository.findAll()));
             newsRepository.save(news);
            return ResponseGeneric.builder()
                    .status("Ok")
                    .message("Noticias guardada exitosamente")
                    .codResponse(200)
                    .object(news)
                    .build();
        }catch (Exception e){
            return ResponseGeneric.builder()
                    .status("Bad Request")
                    .message(e.getMessage())
                    .codResponse(404)
                    .build();
        }

    }

    @Override
    public ResponseGeneric update(Long id, News news) {
        try {
            var searchNew = newsRepository.findById(id);
            if (searchNew.get().getId() == id){
                newsRepository.save(news);
            }

            return ResponseGeneric.builder()
                    .status("Ok")
                    .message("Noticias guardada exitosamente")
                    .codResponse(200)
                    .object(news)
                    .build();
        }catch (Exception e){
            return ResponseGeneric.builder()
                    .status("Bad Request")
                    .message(e.getMessage())
                    .codResponse(404)
                    .build();
        }
    }

    @Override
    public ResponseGeneric delete(Long id) {
        try {
            var news = newsRepository.findById(id);
            if (news.isEmpty()){
                return ResponseGeneric.builder()
                        .status("Ok")
                        .message("Noticia no encontrada")
                        .codResponse(200)
                        .build();
            }
            newsRepository.deleteById(id);
            return ResponseGeneric.builder()
                    .status("Ok")
                    .message("Noticias eliminada exitosamente")
                    .codResponse(200)
                    .build();
        }catch (Exception e){
            return ResponseGeneric.builder()
                    .status("Bad Request")
                    .message(e.getMessage())
                    .codResponse(404)
                    .build();
        }
    }
}
