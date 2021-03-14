package br.com.alura.spring.data.service;

import java.util.List;
import java.util.Scanner;

import org.springframework.data.jpa.domain.Specification;

import br.com.alura.spring.data.models.Funcionario;
import br.com.alura.spring.data.repository.FuncionarioRepository;
import br.com.alura.spring.data.specifications.SpecificationFuncionario;

public class RelatorioFuncionarioDinamico {
	private final FuncionarioRepository funcionarioRepository;
	
	public RelatorioFuncionarioDinamico(FuncionarioRepository funcionarioRepository) {
		this.funcionarioRepository = funcionarioRepository;
	}
	
	public void buscarPorLikeNome(Scanner sc) {
		sc.nextLine();
		System.out.print("Imforme um nome: ");
		String nome = sc.nextLine();
		List<Funcionario> list = funcionarioRepository.
				findAll(Specification.where(SpecificationFuncionario.likeNome(nome)));
		
		list.forEach(System.out::println);
	}
}
