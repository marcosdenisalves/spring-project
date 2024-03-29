package br.com.alura.spring.data;

import java.util.Locale;
import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.alura.spring.data.service.FuncionarioService;
import br.com.alura.spring.data.service.RelatorioFuncionarioDinamico;
import br.com.alura.spring.data.service.RelatorioService;

@SpringBootApplication
public class SpringDataApplication implements CommandLineRunner {

	private final FuncionarioService funcionarioService;
	private final RelatorioService relatorioService;
	private final RelatorioFuncionarioDinamico relatDinamico;

	private boolean system = true;
	
	public SpringDataApplication(FuncionarioService funcionarioService, RelatorioService relatorioService
			, RelatorioFuncionarioDinamico relatDinamico) {
		this.funcionarioService = funcionarioService;
		this.relatorioService = relatorioService;
		this.relatDinamico = relatDinamico;
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
			System.out.println("Digite: 1 - Relatórios de Funcionários ");
			System.out.println("Digite: 2 - Relatórios Dinâmicos de Funcionários ");
			System.out.println("Digite: 3 - Alterar Funcionário ");
			System.out.println("Digite: 4 - Cadastrar Funcionário ");
			System.out.println("Digite: 5 - Deletar Funcionário ");
			System.out.println("Digite: 0 - Sair ");
	
			System.out.print("Selecione: ");
		
			int action = sc.nextInt();
			switch (action) {
			case 1:
				relatorioService.listar(sc);
				break;
			case 2:
				relatDinamico.atributosDinamicos(sc);
				break;
			case 3:
				funcionarioService.alterar(sc);
				break;
			case 4:
				funcionarioService.inserir(sc);
				break;
			case 5:
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
