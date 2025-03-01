package personal.rh_funcionarios.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import personal.rh_funcionarios.exception.GenericException;
import personal.rh_funcionarios.model.FuncionarioModel;
import personal.rh_funcionarios.repository.EnderecoRepository;
import personal.rh_funcionarios.repository.FuncionarioRepository;

import java.util.List;
import java.util.NoSuchElementException;
import static java.util.Optional.ofNullable;

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
    public FuncionarioModel create(FuncionarioModel funcionarioToCreate){
//      tratamento de erros (campo nulo)
        ofNullable(funcionarioToCreate).orElseThrow(() -> new GenericException("É necessario um funcionario para registrar"));
        ofNullable(funcionarioToCreate.getNome()).orElseThrow(() -> new GenericException("É necessario um nome para registrar"));
        ofNullable(funcionarioToCreate.getIdade()).orElseThrow(() -> new GenericException("É necessario a idade do funcionario para registrar"));
        ofNullable(funcionarioToCreate.getEmail()).orElseThrow(() -> new GenericException("É necessario um email para registrar"));
        ofNullable(funcionarioToCreate.getCpf()).orElseThrow(() -> new GenericException("É necessario um CPF para registrar"));
        ofNullable(funcionarioToCreate.getCargo()).orElseThrow(() -> new GenericException("É necessario um Cargo para registrar"));
        ofNullable(funcionarioToCreate.getDepartamento()).orElseThrow(() -> new GenericException("É necessario um email para registrar"));
        ofNullable(funcionarioToCreate.getEndereco().getBairro()).orElseThrow(() -> new GenericException("É necessario um bairro no endereço para registrar"));
        ofNullable(funcionarioToCreate.getEndereco().getRua()).orElseThrow(() -> new GenericException("É necessario uma rua no endereço para registrar"));
        ofNullable(funcionarioToCreate.getEndereco().getCep()).orElseThrow(() -> new GenericException("É necessario um CEP no endereço para registrar"));
        if (funcionarioRepository.existsByCpf(funcionarioToCreate.getCpf())){
            throw new GenericException("CPF já cadastrado");
        }
        return funcionarioRepository.save(funcionarioToCreate);
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
