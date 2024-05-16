package com.corhuila.egresadoscorhuila.service.impl;

import com.corhuila.egresadoscorhuila.dto.NewsDto;
import com.corhuila.egresadoscorhuila.entity.News;
import com.corhuila.egresadoscorhuila.repository.NewsRepository;
import com.corhuila.egresadoscorhuila.response.ResponseGeneric;
import com.corhuila.egresadoscorhuila.service.NewsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    NewsRepository newsRepository;

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

            for (News notice : news){
                if (notice.getExpirationDate().after(new Date())){
                    news.add(notice);
                }
            }

            return ResponseGeneric.builder()
                    .status("Ok")
                    .message("Noticias listadas exitosamente")
                    .codResponse(200)
                    .listObject(Collections.singletonList(news))
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
