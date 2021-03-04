package br.com.alura.spring.data;

import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.alura.spring.data.repository.FuncionarioRepository;
import br.com.alura.spring.data.repository.UnidadeRepository;
import br.com.alura.spring.data.service.CargoService;

@SpringBootApplication
public class SpringDataApplication implements CommandLineRunner {

	private final CargoService cargoService;
	private boolean system = true;
	
	public SpringDataApplication(CargoService cargoService, FuncionarioRepository fucionarioRepository, UnidadeRepository unidadeRepository) {
		this.cargoService = cargoService;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(SpringDataApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner sc = new Scanner(System.in);

		System.out.println("\nQual ação você quer executar?");
		System.out.println("Digite: 0 - Sair ");
		System.out.println("Digite: 1 - Listar Cargos ");
		System.out.println("Digite: 2 - Alterar Cargos ");
		System.out.println("Digite: 3 - Inserir Cargos ");
		System.out.println("Digite: 4 - Deletar Cargos ");

		System.out.print("Selecione: ");
		
		while (system) {
			int action = sc.nextInt();
			switch (action) {
			case 1:
				system = cargoService.listar();
				break;
			case 2:
				system = cargoService.alterar(sc);
				break;
			case 3:
				system = cargoService.inserir(sc);
				break;
			case 4:
				system = cargoService.inserir(sc);
				break;
			case 0:
				system = false;
				break;
			}
		}
		sc.close();
	}
}
