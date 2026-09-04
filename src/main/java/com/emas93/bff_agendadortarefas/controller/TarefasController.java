package com.emas93.bff_agendadortarefas.controller;


import com.emas93.bff_agendadortarefas.business.TarefasService;
import com.emas93.bff_agendadortarefas.business.dtos.in.TarefasDTORequest;
import com.emas93.bff_agendadortarefas.business.dtos.out.TarefasDTOResponse;
import com.emas93.bff_agendadortarefas.business.enums.StatusNotificacaoEnum;
import com.emas93.bff_agendadortarefas.infrastructure.security.SecurityConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/tarefas")
@RequiredArgsConstructor
@Tag(name = "Tarefas", description = "Cadastro de tarefas de usuários")
@SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)
public class TarefasController {
    private final TarefasService tarefasService;

    @PostMapping
    @Operation(summary = "Cria tarefas", description = "Cria tarefas para o usuário")
    @ApiResponse(responseCode = "200", description = "Tarefa cadastrada com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @ApiResponse(responseCode = "401", description = "Usuário não autorizado")
    public ResponseEntity<TarefasDTOResponse> gravarTarefas(@RequestBody TarefasDTORequest tarefasDTO, @RequestHeader(value = "Authorization", required = false) String token) {
        return ResponseEntity.ok(tarefasService.salvaTarefa(tarefasDTO, token));
    }

    @GetMapping("/eventos")
    @Operation(summary = "Consulta tarefas por data", description = "Consulta tarefas por período")
    @ApiResponse(responseCode = "200", description = "Tarefas encontradas")
    @ApiResponse(responseCode = "404", description = "Tarefas não encontradas nesse período")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @ApiResponse(responseCode = "401", description = "Usuário não autorizado")
    public ResponseEntity<List<TarefasDTOResponse>> buscarTarefasPorPeriodoEvento(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicial,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal,
            @RequestHeader(value = "Authorization", required = false) String token) {
        return ResponseEntity.ok(tarefasService.buscaTarefasPorDataAgendamento(dataInicial, dataFinal,token));

    }

    @GetMapping("/email")
    @Operation(summary = "Consulta tarefas por e-mail", description = "Consulta tarefas por e-mail de usuário")
    @ApiResponse(responseCode = "200", description = "Tarefas encontradas")
    @ApiResponse(responseCode = "403", description = "Tarefas não encontradas para esse usuário")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @ApiResponse(responseCode = "401", description = "Usuário não autorizado")
    public ResponseEntity<List<TarefasDTOResponse>> buscarTarefasPorEmail(@RequestHeader(value = "Authorization", required = false) String token) {
        return ResponseEntity.ok(tarefasService.buscaTarefasPorEmail(token));

    }

    @DeleteMapping
    @Operation(summary = "Deleta tarefas de usuário", description = "Deleta tarefas de usuário")
    @ApiResponse(responseCode = "200", description = "Tarefas encontradas")
    @ApiResponse(responseCode = "403", description = "Tarefas não encontradas para esse usuário")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @ApiResponse(responseCode = "401", description = "Usuário não autorizado")
    public ResponseEntity<Void> deletarTarefa(String id,@RequestHeader(value = "Authorization", required = false) String token) {
        tarefasService.deletaTarefaPorId(id,token);
        return ResponseEntity.ok().build();

    }

    @PatchMapping
    @Operation(summary = "Atualiza status das tarefas", description = "Atualiza status das tarefas de usuário")
    @ApiResponse(responseCode = "200", description = "Status das tarefas atualizados")
    @ApiResponse(responseCode = "403", description = "Tarefas não encontradas")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @ApiResponse(responseCode = "401", description = "Usuário não autorizado")
    public ResponseEntity<TarefasDTOResponse> alterarStatusNotificacao(@RequestParam("status") StatusNotificacaoEnum status,
                                                                       @RequestParam("id") String id,
                                                                       @RequestHeader(value = "Authorization", required = false) String token){
        return ResponseEntity.ok(tarefasService.alteraStatus(status,id,token));
    }

    @PutMapping
    @Operation(summary = "Atualiza dados da tarefa", description = "Atualiza dados das tarefas do usuário")
    @ApiResponse(responseCode = "200", description = "Tarefa atualizada")
    @ApiResponse(responseCode = "403", description = "Tarefa não encontradas")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @ApiResponse(responseCode = "401", description = "Usuário não autorizado")
    public ResponseEntity<TarefasDTOResponse> updateTarefas(@RequestBody TarefasDTORequest tarefasDTO, @RequestParam("id") String id, @RequestHeader(value = "Authorization", required = false) String token){
        return ResponseEntity.ok(tarefasService.updateTarefas(tarefasDTO,id,token));
    }
}
