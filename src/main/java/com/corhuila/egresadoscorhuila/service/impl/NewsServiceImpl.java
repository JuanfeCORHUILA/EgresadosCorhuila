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
import java.util.*;
import java.util.stream.Collectors;

@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    NewsRepository newsRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public ResponseGeneric findAllCurrent() {
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
    public ResponseGeneric finAll() {
        try {
            var news = newsRepository.findAll();
            if (news.isEmpty()){
                return ResponseGeneric.builder()
                        .status("Not Content")
                        .message("No hay noticias para mostrar")
                        .codResponse(204)
                        .build();
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
            // Buscar la noticia por ID
            Optional<News> existingNews = newsRepository.findById(id);

            if (existingNews.isPresent()) {
                News newsToUpdate = existingNews.get();

                // Actualizamos los campos necesarios, puedes ajustar según lo que necesites actualizar
                newsToUpdate.setTitle(news.getTitle());
                newsToUpdate.setContent(news.getContent());
                newsToUpdate.setFile(news.getFile());
                newsToUpdate.setStatus(news.getStatus());
                newsToUpdate.setExpirationDate(news.getExpirationDate());

                // Guardar la noticia actualizada
                newsRepository.save(newsToUpdate);

                return ResponseGeneric.builder()
                        .status("Ok")
                        .message("Noticia actualizada exitosamente")
                        .codResponse(200)
                        .object(newsToUpdate)
                        .build();
            } else {
                // Retornamos un error si la noticia no se encuentra
                return ResponseGeneric.builder()
                        .status("Not Found")
                        .message("Noticia con ID " + id + " no encontrada")
                        .codResponse(404)
                        .build();
            }
        } catch (Exception e) {
            // Manejo de excepciones
            return ResponseGeneric.builder()
                    .status("Bad Request")
                    .message("Error al actualizar la noticia: " + e.getMessage())
                    .codResponse(400) // Cambié a 400 ya que usualmente 404 es cuando no se encuentra algo
                    .build();
        }
    }

    @Override
    public ResponseGeneric updateStatus(Long id, Boolean status) {
        try {
            var searchNew = newsRepository.findById(id);

            if (searchNew.isPresent()) {
                News news = searchNew.get();
                news.setStatus(status);
                newsRepository.save(news);

                return ResponseGeneric.builder()
                        .status("Ok")
                        .message("Estado de la noticia actualizado exitosamente")
                        .codResponse(200)
                        .object(news)
                        .build();
            } else {
                return ResponseGeneric.builder()
                        .status("Not Found")
                        .message("No se encontró la noticia con ID: " + id)
                        .codResponse(404)
                        .build();
            }
        } catch (Exception e) {
            return ResponseGeneric.builder()
                    .status("Bad Request")
                    .message(e.getMessage())
                    .codResponse(400)
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
