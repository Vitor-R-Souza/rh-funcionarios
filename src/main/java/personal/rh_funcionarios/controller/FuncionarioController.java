package personal.rh_funcionarios.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import personal.rh_funcionarios.model.FuncionarioModel;
import personal.rh_funcionarios.service.FuncionarioService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/funcionario")
@Tag(name = "Sistema de gestão de funcionario")
@Transactional
public class FuncionarioController {

    private final FuncionarioService funcionarioService;

    public FuncionarioController (FuncionarioService funcionarioService){
        this.funcionarioService = funcionarioService;
    }

    @Operation(summary = "Realiza o GET usando o ID de um registro", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Funcinario encontrado"),
            @ApiResponse(responseCode = "404", description = "Funcinario não encontrado")
    })
    @GetMapping("{id}")
    public ResponseEntity<FuncionarioModel> getById(@PathVariable Long id){
        var funcionario = funcionarioService.findById(id);
        return ResponseEntity.ok(funcionario);
    }

    @Operation(summary = "Realiza o GET retornando todos os funcionarios cadastrados", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Funcinarios encontrados"),
            @ApiResponse(responseCode = "404", description = "Funcinarios não encontrados")
    })
    @GetMapping
    public ResponseEntity<List<FuncionarioModel>> getAll(){
        var funcionarios = funcionarioService.listaTodos();
        return ResponseEntity.ok(funcionarios);
    }

    @Operation(summary = "Cadastra um Funcinario", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Funcinarios cadastrado"),
            @ApiResponse(responseCode = "422", description = "Funcionario invalido")
    })
    @PostMapping //(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FuncionarioModel> create(@RequestBody FuncionarioModel funcionario){
        var funcCriado = funcionarioService.create(funcionario);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(funcCriado.getId())
                .toUri();
        return ResponseEntity.created(location).body(funcCriado);
    }

    @Operation(summary = "Atualiza os registros de um Funcionario buscando pelo CPF", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Funcionario atualizado"),
            @ApiResponse(responseCode = "404", description = "Funcionario não encontrado"),
            @ApiResponse(responseCode = "422", description = "Funcionario invalido")
    })
    @PutMapping("/{cpf}")
    public ResponseEntity<FuncionarioModel> update(@RequestBody FuncionarioModel funcionario, @PathVariable String cpf){
        var funcUpdated = funcionarioService.update(funcionario, cpf);
        return ResponseEntity.ok(funcUpdated);
    }

    @Operation(summary = "Deleta um funcionaio usando o CPF")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Funcionario deletado"),
            @ApiResponse(responseCode = "404", description = "Funcionario não encontrado")
    })
    @DeleteMapping("/{cpf}")
    public ResponseEntity<Void> delete(@PathVariable String cpf){
        funcionarioService.delete(cpf);
        return  ResponseEntity.noContent().build();
    }
}
