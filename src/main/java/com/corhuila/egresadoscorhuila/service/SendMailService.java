package com.corhuila.egresadoscorhuila.service;

import com.corhuila.egresadoscorhuila.dto.SenMailRequestDto;
import com.corhuila.egresadoscorhuila.response.ResponseGeneric;

public interface SendMailService {

    ResponseGeneric sendMassiveMail(SenMailRequestDto senMailRequestDto);
}
