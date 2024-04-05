package com.corhuila.egresadoscorhuila.service.impl;

import com.corhuila.egresadoscorhuila.dto.SenMailRequestDto;
import com.corhuila.egresadoscorhuila.response.ResponseGeneric;
import com.corhuila.egresadoscorhuila.service.SendMailService;
import com.corhuila.egresadoscorhuila.utils.SendEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

@Service
public class SendMailServiceImpl implements SendMailService {

    @Autowired
    SendEmail sendEmail;

    @Override
    public ResponseGeneric sendMassiveMail(SenMailRequestDto senMailRequestDto) throws MessagingException {
        for(String mail : senMailRequestDto.getEmails()){
            sendEmail.sendMailSender(mail, senMailRequestDto.getSubject(), senMailRequestDto.getText(), senMailRequestDto.getFile(),senMailRequestDto.getNameFile());
        }
        return ResponseGeneric.builder().codResponse(200).message("Correos enviados exitosamente").status("OK").build();
    }
}
