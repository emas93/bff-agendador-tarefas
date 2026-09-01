package com.emas93.bff_agendadortarefas.infrastructure.client;


import com.emas93.bff_agendadortarefas.business.dtos.in.TarefasDTORequest;
import com.emas93.bff_agendadortarefas.business.dtos.out.TarefasDTOResponse;
import com.emas93.bff_agendadortarefas.business.enums.StatusNotificacaoEnum;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@FeignClient(name = "agendador-tarefas", url = "${agendador-tarefas.url}")
public interface TarefasClient {
    @PostMapping
    TarefasDTOResponse gravarTarefas(@RequestBody TarefasDTORequest tarefasDTO, @RequestHeader ("Authorization") String token);

    @GetMapping("/eventos")
    List<TarefasDTOResponse> buscarTarefasPorPeriodoEvento(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicial,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal,
            @RequestHeader ("Authorization") String token);

    @GetMapping("/email")
    List<TarefasDTOResponse> buscarTarefasPorEmail(@RequestHeader ("Authorization") String token);

    @DeleteMapping
    void deletarTarefa(String id, @RequestHeader ("Authorization") String token);

    @PatchMapping
    TarefasDTOResponse alterarStatusNotificacao(@RequestParam("status") StatusNotificacaoEnum status,
                                                @RequestParam("id") String id,
                                                @RequestHeader ("Authorization") String token);

    @PutMapping
    TarefasDTOResponse updateTarefas(@RequestBody TarefasDTORequest tarefasDTO, @RequestParam("id") String id, @RequestHeader ("Authorization") String token);
}
