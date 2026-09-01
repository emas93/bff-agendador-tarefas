package com.emas93.bff_agendadortarefas.business;


import com.emas93.bff_agendadortarefas.business.dtos.in.TarefasDTORequest;
import com.emas93.bff_agendadortarefas.business.dtos.out.TarefasDTOResponse;
import com.emas93.bff_agendadortarefas.business.enums.StatusNotificacaoEnum;
import com.emas93.bff_agendadortarefas.infrastructure.client.TarefasClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor

public class TarefasService {

    private final TarefasClient tarefasClient;

    public TarefasDTOResponse salvaTarefa(TarefasDTORequest tarefasDTO, String token) {
        return tarefasClient.gravarTarefas(tarefasDTO,token);
    }

    public List<TarefasDTOResponse> buscaTarefasPorDataAgendamento(LocalDateTime dataInicial, LocalDateTime dataFinal, String token) {
         return tarefasClient.buscarTarefasPorPeriodoEvento(dataInicial,dataFinal,token);
    }

    public List<TarefasDTOResponse> buscaTarefasPorEmail(String token) {
        return tarefasClient.buscarTarefasPorEmail(token);
    }

    public void deletaTarefaPorId(String id,String token) {
         tarefasClient.deletarTarefa(id,token);
    }

    public TarefasDTOResponse alteraStatus(StatusNotificacaoEnum status, String id, String token) {
       return tarefasClient.alterarStatusNotificacao(status,id,token);
    }

    public TarefasDTOResponse updateTarefas(TarefasDTORequest tarefasDTO, String id, String token) {
      return tarefasClient.updateTarefas(tarefasDTO,id,token);
    }
}
