package personal.rh_funcionarios.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import personal.rh_funcionarios.model.EnderecoModel;
import personal.rh_funcionarios.model.FuncionarioModel;

public interface EnderecoRepository extends JpaRepository<EnderecoModel, Long> {
    EnderecoModel findByCep(String cep);
}
