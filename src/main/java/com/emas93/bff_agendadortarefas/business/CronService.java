package com.emas93.bff_agendadortarefas.business;

import com.emas93.bff_agendadortarefas.business.dtos.in.LoginDTORequest;
import com.emas93.bff_agendadortarefas.business.dtos.out.TarefasDTOResponse;
import com.emas93.bff_agendadortarefas.business.enums.StatusNotificacaoEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
@RequiredArgsConstructor
public class CronService {

    private final TarefasService tarefasService;
    private final EmailService emailService;
    private final UsuarioService usuarioService;

    @Value("${usuario.email}")
    private String email;

    @Value("${usuario.senha}")
    private String senha;

    @Scheduled(cron = "${cron.horario}",zone = "${cron.zone:America/Sao_Paulo}")
    public void buscaTarefasProximaHora() {
        String token = login(converterParaRequestDTO());
        LocalDateTime horaFutura = LocalDateTime.now().plusHours(1);
        LocalDateTime horaFuturaMaisCinco = horaFutura.plusMinutes(5);
        List<TarefasDTOResponse> listaTarefas = tarefasService.buscaTarefasPorDataAgendamento(horaFutura, horaFuturaMaisCinco, token);

        listaTarefas.forEach(tarefas -> {
            emailService.enviaEmail(tarefas);
            tarefasService.alteraStatus(StatusNotificacaoEnum.NOTIFICADO, tarefas.getId(), token);

        });
    }

    public String login(LoginDTORequest dto){
        return usuarioService.loginUsuario(dto);
    }

    public LoginDTORequest converterParaRequestDTO(){
        return LoginDTORequest.builder()
                .email(email)
                .senha(senha)
                .build();
    }
}
