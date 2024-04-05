package com.corhuila.egresadoscorhuila.service;

import com.corhuila.egresadoscorhuila.dto.SenMailRequestDto;
import com.corhuila.egresadoscorhuila.response.ResponseGeneric;

import javax.mail.MessagingException;

public interface SendMailService {

    ResponseGeneric sendMassiveMail(SenMailRequestDto senMailRequestDto) throws MessagingException;
}
