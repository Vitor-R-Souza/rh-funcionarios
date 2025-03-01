package personal.rh_funcionarios.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import personal.rh_funcionarios.model.FuncionarioModel;
import personal.rh_funcionarios.repository.EnderecoRepository;
import personal.rh_funcionarios.repository.FuncionarioRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class FuncionarioService {
    private final FuncionarioRepository funcionarioRepository;
    private final EnderecoRepository enderecoRepository;

    public FuncionarioService(FuncionarioRepository funcionarioRepository, EnderecoRepository enderecoRepository){
        this.funcionarioRepository = funcionarioRepository;
        this.enderecoRepository = enderecoRepository;
    }

//    acha um pelo ID
    @Transactional(readOnly = true)
    public FuncionarioModel findById(Long id){
        return funcionarioRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

//    listar todos
    @Transactional(readOnly = true)
    public List<FuncionarioModel> listaTodos(){
        return funcionarioRepository.findAll();
    }

//    criar funcionario
    public FuncionarioModel create(FuncionarioModel funcionario){
        var funcionarioCriado = funcionarioRepository.save(funcionario);
//        if (funcionarioCriado != null && funcionarioCriado.getId() != null){
//            var enderecoCriado = enderecoRepository.findByCep(funcionarioCriado.getEndereco().getCep());
//            enderecoCriado.setFuncionario(funcionarioCriado);
//            enderecoRepository.save(enderecoCriado);
//        }
        return funcionarioCriado;
    }

//    atualizar cadastro buscando pelo CPF
public FuncionarioModel update(FuncionarioModel funcionarioToUpdate, String cpf){
    FuncionarioModel dbFuncionario = funcionarioRepository.findByCpf(cpf);
    if (!dbFuncionario.getCpf().equals(funcionarioToUpdate.getCpf())){
        throw new NoSuchElementException();
    }
    dbFuncionario.setNome(funcionarioToUpdate.getNome());
    dbFuncionario.setCargo(funcionarioToUpdate.getCargo());
    dbFuncionario.setDepartamento(funcionarioToUpdate.getDepartamento());
    dbFuncionario.setEmail(funcionarioToUpdate.getEmail());
    dbFuncionario.setEndereco(funcionarioToUpdate.getEndereco());
    dbFuncionario.setIdade(funcionarioToUpdate.getIdade());
    dbFuncionario.setTelefone(funcionarioToUpdate.getTelefone());

    return funcionarioRepository.save(dbFuncionario);
}

//    deletar funcionario
    public void delete(String cpf){
        FuncionarioModel dbFuncionario = funcionarioRepository.findByCpf(cpf);
        funcionarioRepository.delete(dbFuncionario);
    }
}
