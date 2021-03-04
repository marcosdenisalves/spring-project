package br.com.alura.spring.data;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.alura.spring.data.models.Cargo;
import br.com.alura.spring.data.models.Funcionario;
import br.com.alura.spring.data.models.Unidade;
import br.com.alura.spring.data.repository.CargoRepository;
import br.com.alura.spring.data.repository.FuncionarioRepository;
import br.com.alura.spring.data.repository.UnidadeRepository;

@SpringBootApplication
public class SpringDataApplication implements CommandLineRunner {

	private final CargoRepository cargoRepository;
	private final UnidadeRepository unidadeRepository;
	private final FuncionarioRepository funcionarioRepository;
	
	public SpringDataApplication(CargoRepository cargoRepository, FuncionarioRepository fucionarioRepository, UnidadeRepository unidadeRepository) {
		this.cargoRepository = cargoRepository;
		this.funcionarioRepository = fucionarioRepository;
		this.unidadeRepository = unidadeRepository;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(SpringDataApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Cargo cargo = new Cargo(null, "Desenvolvedor Back-End");
		Unidade unidade = new Unidade(null, "Perinity", "SP");
		Funcionario funcionario = new Funcionario(null, "Marcos Dênis",cargo, unidade, "11/12/1993");
		
		cargoRepository.save(cargo);
		unidadeRepository.save(unidade);
		funcionarioRepository.save(funcionario);
	}
}
