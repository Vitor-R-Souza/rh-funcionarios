package personal.rh_funcionarios.model;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;

@Entity(name = "TB_ENDERECOS")
public class EnderecoModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "funcionario_id")
    private FuncionarioModel funcionario;

    @Column(nullable = true)
    private String estado;

    @Column(nullable = true)
    private String cidade;

    @Column(nullable = false)
    private String bairro;

    @Column(nullable = false)
    private String rua;

    @Column(nullable = true)
    private String complemento;

    @Column(nullable = false)
    private String cep;
}
