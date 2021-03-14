package br.com.alura.spring.data.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import br.com.alura.spring.data.models.Funcionario;
import br.com.alura.spring.data.repository.FuncionarioRepository;
import br.com.alura.spring.data.specifications.SpecificationFuncionario;

@Service
public class RelatorioFuncionarioDinamico {
	private final FuncionarioRepository funcionarioRepository;
	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	public RelatorioFuncionarioDinamico(FuncionarioRepository funcionarioRepository) {
		this.funcionarioRepository = funcionarioRepository;
	}
	
	public void atributosDinamicos(Scanner sc) {
		sc.nextLine();
		System.out.print("Imforme um nome: ");
		String nome = sc.nextLine();

		if(nome.equalsIgnoreCase("null"))
			nome = null;

		System.out.print("Imforme um cpf: ");
		String cpf = sc.nextLine();

		if(cpf.equalsIgnoreCase("null"))
			cpf = null;

		System.out.print("Imforme um salário: ");
		Double salario = sc.nextDouble();
		sc.nextLine();

		if(salario == 0)
			salario = null;
		
		System.out.print("Imforme um data de contratação: ");
		String data = sc.nextLine();

		LocalDate dataContratacao;
		if(data.isEmpty()) {
			dataContratacao = null;
		}else {
			dataContratacao = LocalDate.parse(data, formatter);
		}
		
		List<Funcionario> list = funcionarioRepository.
				findAll(Specification
						.where(
							SpecificationFuncionario.likeNome(nome))
							.or(SpecificationFuncionario.cpfEqual(cpf))
							.or(SpecificationFuncionario.salarioLessThan(salario))
							.or(SpecificationFuncionario.dataContratacao(dataContratacao))
						);
		
		System.out.println();
		if (!list.isEmpty()) {
			list.forEach(System.out::println);
		}else {
			System.out.println("Nenhum registro encontrado!");
		}
	}
}
