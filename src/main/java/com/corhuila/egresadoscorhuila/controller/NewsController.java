package com.corhuila.egresadoscorhuila.controller;

import com.corhuila.egresadoscorhuila.dto.NewsDto;
import com.corhuila.egresadoscorhuila.entity.News;
import com.corhuila.egresadoscorhuila.response.ResponseGeneric;
import com.corhuila.egresadoscorhuila.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/egresados")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class NewsController {

    @Autowired
    NewsService newsService;

    ModelMapper modelMapper;

    @PreAuthorize("hasAuthority('ROL_ADMIN')")
    @GetMapping(path = "/listNews", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseGeneric> listNews(){
        return new ResponseEntity<>(newsService.findAll(), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ROL_ADMIN')")
    @PostMapping(path = "/saveNews", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseGeneric> saveNews(@RequestBody @Validated NewsDto newsDto){
        return new ResponseEntity<>(newsService.save(newsDto), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ROL_ADMIN')")
    @PutMapping(path = "/updateNews", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseGeneric> updateNews(@PathVariable("id") Long id,@RequestBody @Validated NewsDto newsDto){
        var news = modelMapper.map(newsDto, News.class);
        return new ResponseEntity<>(newsService.update(id,news), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ROL_ADMIN')")
    @DeleteMapping(path = "/updateNews", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseGeneric> deleteNews(@PathVariable("id") Long id){
        return new ResponseEntity<>(newsService.delete(id), HttpStatus.OK);
    }
}
