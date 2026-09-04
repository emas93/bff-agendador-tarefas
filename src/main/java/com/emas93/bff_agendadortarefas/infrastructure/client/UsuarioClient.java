package com.emas93.bff_agendadortarefas.infrastructure.client;


import com.emas93.bff_agendadortarefas.business.dtos.in.EnderecoDTORequest;
import com.emas93.bff_agendadortarefas.business.dtos.in.LoginDTORequest;
import com.emas93.bff_agendadortarefas.business.dtos.in.TelefoneDTORequest;
import com.emas93.bff_agendadortarefas.business.dtos.in.UsuarioDTORequest;
import com.emas93.bff_agendadortarefas.business.dtos.out.EnderecoDTOResponse;
import com.emas93.bff_agendadortarefas.business.dtos.out.TelefoneDTOResponse;
import com.emas93.bff_agendadortarefas.business.dtos.out.UsuarioDTOResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "usuario", url = "${usuario.url}")
public interface UsuarioClient {
    @GetMapping
    UsuarioDTOResponse buscaUsuarioPorEmail(@RequestParam("email") String email,
                                            @RequestHeader ("Authorization") String token);


    @PostMapping
    UsuarioDTOResponse salvaUsuario(@RequestBody UsuarioDTORequest usuarioDTO);


    @PostMapping("/login")
    String login(@RequestBody LoginDTORequest usuarioDTO);


    @DeleteMapping("/{email}")
    void deletaUsuarioPorEmail(@PathVariable String email, @RequestHeader ("Authorization") String token);


    @PutMapping
    UsuarioDTOResponse alteraDadosUsuario(@RequestBody UsuarioDTORequest usuarioDTO, @RequestHeader ("Authorization") String token);

    @PutMapping("/endereco")
    EnderecoDTOResponse alteraDadosEndereco(@RequestBody EnderecoDTORequest enderecoDTO, @RequestParam("id") Long id, @RequestHeader ("Authorization") String token);


    @PutMapping("/telefone")
    TelefoneDTOResponse alteraDadosTelefone(@RequestBody TelefoneDTORequest telefoneDTO, @RequestParam("id") Long id, @RequestHeader ("Authorization") String token);


    @PostMapping("/endereco")
    EnderecoDTOResponse cadastraEndereco(@RequestBody EnderecoDTORequest enderecoDTO, @RequestHeader ("Authorization") String token);

    @PostMapping("/telefone")
    TelefoneDTOResponse cadastraTelefone(@RequestBody TelefoneDTORequest telefoneDTO, @RequestHeader ("Authorization") String token);

}
