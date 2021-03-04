package br.com.alura.spring.data.service;

import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.alura.spring.data.models.Cargo;
import br.com.alura.spring.data.repository.CargoRepository;

@Service
public class CargoService {

	private final CargoRepository repository;

	public CargoService(CargoRepository repository) {
		this.repository = repository;
	}
	
	public boolean listar() {
		listarCargos();
		System.out.println("\nDados carregados com sucesso!!");
		System.out.println();
		return false;
	}
	
	private void listarCargos() {
		Iterable<Cargo> cargos = repository.findAll();
		System.out.println();
		cargos.forEach(c -> System.out.println(c.getId()
				+ " - " + c.getDescricao()));
	}

	public boolean alterar(Scanner sc) {
		sc.nextLine();
		System.out.println("\nSelecione o cargo a ser alterado: ");
		
		int id = exibirCargos(sc);
		sc.nextLine();
		
		Cargo cargo = repository.
				findById(id).orElse(new Cargo());

		System.out.print("Insira uma nova descrição: ");
		String descricao = sc.nextLine();
		cargo.setDescricao(descricao);

		repository.save(cargo);
		System.out.println("\n" + cargo.getDescricao() + " alterado com sucesso!!");
		System.out.println();

		return false;
	}
	
	public boolean inserir(Scanner sc) {
		sc.nextLine();
		System.out.print("\nInforme a descrição do cargo: ");
		String descricao = sc.nextLine();
		repository.save(new Cargo(null, descricao));
		
		if (descricao != null)
			System.out.println("Salvo com sucesso!!");
		
		listarCargos();
		System.out.println();
		
		return false;
	}

	
	public boolean deletar(Scanner sc) {
		System.out.println("\nSelecione o cargo a ser deletado: ");
		
		int id = exibirCargos(sc);
		sc.nextLine();

		repository.deleteById(id);
		System.out.println("removido com sucesso!!");
		System.out.println();
		
		return false;
	}

	private int exibirCargos(Scanner sc) {
		listarCargos();
		System.out.println("\n===============================");
		System.out.print("Selecione: ");
		return sc.nextInt();
	}
}
