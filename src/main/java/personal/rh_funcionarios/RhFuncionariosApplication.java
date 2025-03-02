package personal.rh_funcionarios;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
		info = @Info(title = "Sistema de gestão de funcionario", version = "1", description = "API desenvolvido para testes de API gerindo resgistros de empregados"),
		servers = {@Server(url = "/", description = "Default Server URL")})
@SpringBootApplication
public class RhFuncionariosApplication {

	public static void main(String[] args) {
		SpringApplication.run(RhFuncionariosApplication.class, args);
	}

}
