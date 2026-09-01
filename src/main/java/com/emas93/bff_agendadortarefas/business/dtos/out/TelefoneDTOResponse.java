package com.emas93.bff_agendadortarefas.business.dtos.out;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TelefoneDTOResponse {
    private Long id;
    private String numeroTelefone;
    private String ddd;
}
