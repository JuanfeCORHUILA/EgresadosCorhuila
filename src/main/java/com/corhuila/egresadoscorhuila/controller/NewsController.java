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

    @Autowired
    ModelMapper modelMapper;

    @PreAuthorize("hasAnyAuthority('ROL_ADMIN', 'ROL_EGRESADO')")
    @GetMapping(path = "/listNewsCurrent", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseGeneric> listNewsCurrent(){
        return new ResponseEntity<>(newsService.findAllCurrent(), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('ROL_ADMIN')")
    @GetMapping(path = "/listNews", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseGeneric> listNews(){
        return new ResponseEntity<>(newsService.finAll(), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ROL_ADMIN')")
    @PostMapping(path = "/saveNews", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseGeneric> saveNews(@RequestBody @Validated NewsDto newsDto){
        return new ResponseEntity<>(newsService.save(newsDto), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ROL_ADMIN')")
    @PutMapping(path = "/updateNews", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseGeneric> updateNews(@RequestParam(name = "id") Long id,@RequestBody @Validated NewsDto newsDto){
        var news = modelMapper.map(newsDto, News.class);
        return new ResponseEntity<>(newsService.update(id,news), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ROL_ADMIN')")
    @PutMapping(path = "/updateStatus", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseGeneric> updateNewsStatus(
            @RequestParam(name = "id") Long id,
            @RequestParam(name = "status") Boolean status) {
            ResponseGeneric response = newsService.updateStatus(id, status);
            return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCodResponse()));
    }



    @PreAuthorize("hasAuthority('ROL_ADMIN')")
    @DeleteMapping(path = "/deleteNews", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseGeneric> deleteNews(@PathVariable("id") Long id){
        return new ResponseEntity<>(newsService.delete(id), HttpStatus.OK);
    }
}
