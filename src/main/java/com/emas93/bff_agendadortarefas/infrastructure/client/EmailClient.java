package com.emas93.bff_agendadortarefas.infrastructure.client;

import com.emas93.bff_agendadortarefas.business.dtos.in.TarefasDTORequest;
import com.emas93.bff_agendadortarefas.business.dtos.out.TarefasDTOResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "notificacao", url = "${notificacao.url}")
public interface EmailClient {
    @PostMapping
    void enviarEmail(@RequestBody TarefasDTOResponse dto);


}
