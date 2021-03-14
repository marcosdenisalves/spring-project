package br.com.alura.spring.data.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.alura.spring.data.models.Funcionario;
import br.com.alura.spring.data.repository.FuncionarioRepository;

@Service
public class RelatorioService {
	
	private final FuncionarioRepository repository;
	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	public RelatorioService(FuncionarioRepository repository) {
		this.repository = repository;
	}

	public void listar(Scanner sc) {
		System.out.println("Selecione uma das opções");
		System.out.println("1 - listar por nome");
		System.out.println("2 - listar por nome, maior sálario e data de contratação");
		System.out.println("3 - listar maior data de contratação");
		System.out.println("4 - listar todos");
		System.out.println("0 - cancelar");
		System.out.print("Selecione: ");
		
		int acao = sc.nextInt();
		
		switch (acao) {
		case 1:
			validaLista(listarPorNome(sc));
			break;
		case 2:
			validaLista(listarTodosComFiltro(sc));
			break;
		case 3:
			validaLista(listarPorDataMaior(sc));			
			break;
		case 4:
			listarFuncionarios(sc);			
			break;
		case 0:
			break;
		}
	}

	private void validaLista(List<Funcionario> funcionarios) {
		if(!funcionarios.isEmpty()) {
			System.out.println("\nDados carregados com sucesso!!");
			System.out.println();
		}else {
			System.out.println("\nNenhum funcionário cadastrado.");
			System.out.println();
		}
	}

	public void listarFuncionarios(Scanner sc) {
		sc.nextLine();
		System.out.print("\nQual página deseja visualizar: ");
		int pagina = sc.nextInt();
		
		Pageable pageable = PageRequest.of(pagina, 2, Sort.by(Sort.Direction.ASC, "nome"));
		
		Page<Funcionario> funcionarios = repository.findAll(pageable);
		System.out.println();

		System.out.println("página atual: " + funcionarios.getNumber());
		System.out.println("total de páginas " + funcionarios.getTotalPages());
		System.out.println("total de registros carregados: " + funcionarios.getTotalElements());
		System.out.println();

		if(!funcionarios.isEmpty()) {
			funcionarios.forEach(System.out::println);
			System.out.println("\nDados carregados com sucesso!!");
			System.out.println();
		}else {
			System.out.println("\nNenhum funcionário cadastrado.");
			System.out.println();
		}
	}
	
	public List<Funcionario> listarPorNome(Scanner sc) {
		sc.nextLine();
		listarFuncionarios(sc);
		System.out.print("\nInforme o nome do funcionário: ");
		String nome = sc.nextLine();
		List<Funcionario> list = repository.findByNomeLikeOrderByNomeAsc("%" + nome + "%");
		list.forEach(System.out::println);
		return list;
	}
	
	public List<Funcionario> listarTodosComFiltro(Scanner sc) {
		sc.nextLine();
		System.out.print("\nInforme o nome: ");
		String nome = sc.nextLine();
		
		System.out.print("Informe o valor do salário: ");
		Double salario = sc.nextDouble();
		sc.nextLine();

		System.out.print("Informe a data de contratação: ");
		LocalDate data = LocalDate.parse(sc.nextLine(), formatter);
		
		List<Funcionario> list = repository.findNomeSalarioMaiorDataContratacao
				(nome, salario, data);
		
		list.forEach(System.out::println);

		return list;
	}

	public List<Funcionario> listarPorDataMaior(Scanner sc) {
		sc.nextLine();
		System.out.print("\nInforme a data");
		List<Funcionario> list = repository.findDataContratacaoMaior(
				LocalDate.parse(sc.nextLine(), formatter));
		
		list.forEach(System.out::println);
		
		return list;
	}
}
