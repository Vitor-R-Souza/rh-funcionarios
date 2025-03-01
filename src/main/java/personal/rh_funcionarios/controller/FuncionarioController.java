package personal.rh_funcionarios.controller;

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
@Transactional
public class FuncionarioController {

    private final FuncionarioService funcionarioService;

    public FuncionarioController (FuncionarioService funcionarioService){
        this.funcionarioService = funcionarioService;
    }

    @GetMapping("{id}")
    public ResponseEntity<FuncionarioModel> getById(@PathVariable Long id){
        var funcionario = funcionarioService.findById(id);
        return ResponseEntity.ok(funcionario);
    }

    @GetMapping
    public ResponseEntity<List<FuncionarioModel>> getAll(){
        var funcionarios = funcionarioService.listaTodos();
        return ResponseEntity.ok(funcionarios);
    }

    @PostMapping
    public ResponseEntity<FuncionarioModel> create(@RequestBody FuncionarioModel funcionario){
        var funcCriado = funcionarioService.create(funcionario);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(funcCriado.getId())
                .toUri();
        return ResponseEntity.created(location).body(funcCriado);
    }

    @PutMapping("/{cpf}")
    public ResponseEntity<FuncionarioModel> update(@RequestBody FuncionarioModel funcionario, @PathVariable String cpf){
        var funcUpdated = funcionarioService.update(funcionario, cpf);
        return ResponseEntity.ok(funcUpdated);
    }

    @DeleteMapping("/{cpf}")
    public ResponseEntity<Void> delete(@PathVariable String cpf){
        funcionarioService.delete(cpf);
        return  ResponseEntity.noContent().build();
    }
}
