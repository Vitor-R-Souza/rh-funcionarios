package personal.rh_funcionarios.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import personal.rh_funcionarios.model.FuncionarioModel;

public interface FuncionarioRepository extends JpaRepository<FuncionarioModel, Long> {
    FuncionarioModel findByCpf(String cpf);
    boolean existsByCpf(String cpf);
}
