package com.emas93.bff_agendadortarefas.controller;


import com.emas93.bff_agendadortarefas.business.UsuarioService;
import com.emas93.bff_agendadortarefas.business.dtos.in.EnderecoDTORequest;
import com.emas93.bff_agendadortarefas.business.dtos.in.LoginDTORequest;
import com.emas93.bff_agendadortarefas.business.dtos.in.TelefoneDTORequest;
import com.emas93.bff_agendadortarefas.business.dtos.in.UsuarioDTORequest;
import com.emas93.bff_agendadortarefas.business.dtos.out.EnderecoDTOResponse;
import com.emas93.bff_agendadortarefas.business.dtos.out.TelefoneDTOResponse;
import com.emas93.bff_agendadortarefas.business.dtos.out.UsuarioDTOResponse;
import com.emas93.bff_agendadortarefas.infrastructure.security.SecurityConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
@Tag(name = "Usuário", description = "Cadastro e login de usuários")
@SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)
public class UsuarioController {
    private final UsuarioService usuarioService;


    @PostMapping
    @Operation(summary = "Salvar Usuários", description = "Cria um novo usuário")
    @ApiResponse(responseCode = "200", description = "Usuário salvo com sucesso")
    @ApiResponse(responseCode = "400", description = "Usuário já cadastrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<UsuarioDTOResponse> salvaUsuario(@RequestBody UsuarioDTORequest usuarioDTO) {
        return ResponseEntity.ok(usuarioService.salvaUsuario(usuarioDTO));
    }

    @PostMapping("/login")
    @Operation(summary = "Login Usuários", description = "Realiza o login do usuário")
    @ApiResponse(responseCode = "200", description = "Usuário autenticado")
    @ApiResponse(responseCode = "401", description = "Credenciais do usuário inválidas")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public String login(@RequestBody LoginDTORequest usuarioDTO) {
        return usuarioService.loginUsuario(usuarioDTO);
    }

    @GetMapping
    @Operation(summary = "Consulta dados de Usuários", description = "Realiza a consulta de usuários por e-mail")
    @ApiResponse(responseCode = "200", description = "Usuário encontrado")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<UsuarioDTOResponse> buscaUsuarioPorEmail(@RequestParam("email") String email, @RequestHeader(value = "Authorization", required = false) String token) {
        return ResponseEntity.ok(usuarioService.buscarUsuarioPorEmail(email,token));

    }

    @DeleteMapping("/{email}")
    @Operation(summary = "Deleta Usuário", description = "Exclui o usuário informado por e-mail")
    @ApiResponse(responseCode = "200", description = "Usuário excluído com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<Void> deletaUsuarioPorEmail(@PathVariable String email,@RequestHeader(value = "Authorization", required = false) String token) {
        usuarioService.deletaUsuarioPorEmail(email,token);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    @Operation(summary = "Atualizar dados do Usuário", description = "Atualiza dados do usuário")
    @ApiResponse(responseCode = "200", description = "Dados do usuário atualizados com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<UsuarioDTOResponse> alteraDadosUsuario(@RequestBody UsuarioDTORequest usuarioDTO, @RequestHeader(value = "Authorization", required = false) String token) {
        return ResponseEntity.ok(usuarioService.alteraDadosUsuario(token,usuarioDTO));
    }

    @PutMapping("/endereco")
    @Operation(summary = "Atualiza endereços do Usuário", description = "Atualiza dados de endereços do usuário")
    @ApiResponse(responseCode = "200", description = "Endereços do usuário atualizados com sucesso")
    @ApiResponse(responseCode = "404", description = "Endereço não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<EnderecoDTOResponse> alteraDadosEndereco(@RequestBody EnderecoDTORequest enderecoDTO, @RequestParam("id") Long id, @RequestHeader(value = "Authorization", required = false) String token){
        return ResponseEntity.ok(usuarioService.alteraDadosEndereco(id,enderecoDTO,token));
    }

    @PutMapping("/telefone")
    @Operation(summary = "Atualiza telefones do Usuário", description = "Atualiza dados de telefones do usuário")
    @ApiResponse(responseCode = "200", description = "Telefones do usuário atualizados com sucesso")
    @ApiResponse(responseCode = "404", description = "Telefone não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<TelefoneDTOResponse> alteraDadosTelefone(@RequestBody TelefoneDTORequest telefoneDTO, @RequestParam("id") Long id, @RequestHeader(value = "Authorization", required = false) String token){
        return ResponseEntity.ok(usuarioService.alteraDadosTelefone(id,telefoneDTO,token));
    }

    @PostMapping("/endereco")
    @Operation(summary = "Cadastra endereços do Usuário", description = "Cadastra dados de endereços do usuário")
    @ApiResponse(responseCode = "200", description = "Endereços do usuário cadastrados com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<EnderecoDTOResponse> cadastraEndereco(@RequestBody EnderecoDTORequest enderecoDTO, @RequestHeader(value = "Authorization", required = false) String token){
        return ResponseEntity.ok(usuarioService.cadastraEndereco(token,enderecoDTO));
    }
    @PostMapping("/telefone")
    @Operation(summary = "Cadastra telefones do Usuário", description = "Cadastra dados de telefones do usuário")
    @ApiResponse(responseCode = "200", description = "Telefones do usuário cadastrados com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<TelefoneDTOResponse> cadastraTelefone(@RequestBody TelefoneDTORequest telefoneDTO, @RequestHeader(value = "Authorization", required = false) String token){
        return ResponseEntity.ok(usuarioService.cadastraTelefone(token,telefoneDTO));
    }


}