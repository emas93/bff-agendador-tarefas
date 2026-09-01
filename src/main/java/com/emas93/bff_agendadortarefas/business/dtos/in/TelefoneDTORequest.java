package com.emas93.bff_agendadortarefas.business.dtos.in;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TelefoneDTORequest {

    private String numeroTelefone;
    private String ddd;
}
