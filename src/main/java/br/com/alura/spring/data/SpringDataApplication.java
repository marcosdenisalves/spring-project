package br.com.alura.spring.data;

import java.util.Locale;
import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.alura.spring.data.service.FuncionarioService;

@SpringBootApplication
public class SpringDataApplication implements CommandLineRunner {

	private final FuncionarioService funcionarioService;
	private boolean system = true;
	
	public SpringDataApplication(FuncionarioService funcionarioService) {
		this.funcionarioService = funcionarioService;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(SpringDataApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		while (system) {
			System.out.println("\nQual ação você quer executar?");
			System.out.println("Digite: 1 - Listar Funcionários Cadastrados ");
			System.out.println("Digite: 2 - Alterar Funcionário ");
			System.out.println("Digite: 3 - Cadastrar Funcionário ");
			System.out.println("Digite: 4 - Deletar Funcionário ");
			System.out.println("Digite: 0 - Sair ");
	
			System.out.print("Selecione: ");
		
			int action = sc.nextInt();
			switch (action) {
			case 1:
				funcionarioService.listar();
				break;
			case 2:
				funcionarioService.alterar(sc);
				break;
			case 3:
				funcionarioService.inserir(sc);
				break;
			case 4:
				funcionarioService.deletar(sc);
				break;
			case 0:
				system = false;
				break;
			}
		}
		sc.close();
	}
}
