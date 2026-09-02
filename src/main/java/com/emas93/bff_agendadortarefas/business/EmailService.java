package com.emas93.bff_agendadortarefas.business;


import com.emas93.bff_agendadortarefas.business.dtos.out.TarefasDTOResponse;
import com.emas93.bff_agendadortarefas.infrastructure.client.EmailClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class EmailService {

    private final EmailClient emailClient;

    public void enviaEmail(TarefasDTOResponse dto) {
        emailClient.enviarEmail(dto);
    }
}
