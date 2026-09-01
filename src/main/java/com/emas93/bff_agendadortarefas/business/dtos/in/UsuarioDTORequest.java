package com.emas93.bff_agendadortarefas.business.dtos.in;

import com.emas93.bff_agendadortarefas.business.dtos.out.TelefoneDTOResponse;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioDTORequest {
    private String email;
    private String senha;
    private String nome;
    private List<EnderecoDTORequest> enderecos;
    private List<TelefoneDTOResponse> telefones;
}
