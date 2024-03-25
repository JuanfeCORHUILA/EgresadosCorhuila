package com.corhuila.egresadoscorhuila.jwt;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.corhuila.egresadoscorhuila.dto.MessageDto;
import com.corhuila.egresadoscorhuila.response.ResponseGeneric;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
public class JwtEntryPoint implements AuthenticationEntryPoint {

    private static final Logger logger = LoggerFactory.getLogger(JwtEntryPoint.class);

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        logger.error("token no encontrado o invalido");
        MessageDto messageDto = new MessageDto(HttpStatus.UNAUTHORIZED,"Token no encontrado o invalido");
        response.setContentType("application/json");
        response.setStatus(messageDto.getStatus().value());
        response.getWriter().write(new ObjectMapper().writeValueAsString(messageDto));
        response.getWriter().flush();
        response.getWriter().close();
    }
}
