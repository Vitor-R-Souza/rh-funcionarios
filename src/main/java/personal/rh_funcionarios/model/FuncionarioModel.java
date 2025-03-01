package personal.rh_funcionarios.model;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;

@Entity(name = "TB_FUNCIONARIOS")
public class FuncionarioModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private Integer idade;

    @Column(nullable = false)
    private String email;

    @Column(unique = true, nullable = false)
    private String cpf;

    @Column(nullable = false)
    private String cargo;

    @Column(nullable = false)
    private String departamento;

    private String telefone;

    @OneToOne(mappedBy = "funcionario", cascade = CascadeType.ALL)
    private EnderecoModel endereco;


}
