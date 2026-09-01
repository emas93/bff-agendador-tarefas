package com.emas93.bff_agendadortarefas.business;

import com.emas93.bff_agendadortarefas.business.dtos.in.EnderecoDTORequest;
import com.emas93.bff_agendadortarefas.business.dtos.in.LoginDTORequest;
import com.emas93.bff_agendadortarefas.business.dtos.in.TelefoneDTORequest;
import com.emas93.bff_agendadortarefas.business.dtos.in.UsuarioDTORequest;
import com.emas93.bff_agendadortarefas.business.dtos.out.EnderecoDTOResponse;
import com.emas93.bff_agendadortarefas.business.dtos.out.TelefoneDTOResponse;
import com.emas93.bff_agendadortarefas.business.dtos.out.UsuarioDTOResponse;
import com.emas93.bff_agendadortarefas.infrastructure.client.UsuarioClient;


import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioClient usuarioClient;

    public UsuarioDTOResponse salvaUsuario(UsuarioDTORequest usuarioDTO) {
      return usuarioClient.salvaUsuario(usuarioDTO);
    }

    public String loginUsuario(LoginDTORequest usuarioDTO){
        return usuarioClient.login(usuarioDTO);
    }

    public UsuarioDTOResponse buscarUsuarioPorEmail(String email, String token) {
        return usuarioClient.buscaUsuarioPorEmail(email, token);
    }

    public void deletaUsuarioPorEmail(String email, String token) {
        usuarioClient.deletaUsuarioPorEmail(email,token);
    }


    public UsuarioDTOResponse alteraDadosUsuario(String token, UsuarioDTORequest usuarioDTO) {
        return usuarioClient.alteraDadosUsuario(usuarioDTO,token);
    }

    public EnderecoDTOResponse alteraDadosEndereco(Long id, EnderecoDTORequest enderecoDTO, String token) {
        return usuarioClient.alteraDadosEndereco(enderecoDTO,id,token);
    }

    public TelefoneDTOResponse alteraDadosTelefone(Long id, TelefoneDTORequest telefoneDTO, String token) {
        return usuarioClient.alteraDadosTelefone(telefoneDTO,id,token);
    }

    public EnderecoDTOResponse cadastraEndereco(String token, EnderecoDTORequest enderecoDTO) {
        return usuarioClient.cadastraEndereco(enderecoDTO,token);
    }

    public TelefoneDTOResponse cadastraTelefone(String token, TelefoneDTORequest telefoneDTO) {
        return usuarioClient.cadastraTelefone(telefoneDTO,token);
    }

}



