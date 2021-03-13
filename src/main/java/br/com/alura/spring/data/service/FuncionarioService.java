package br.com.alura.spring.data.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.alura.spring.data.models.Cargo;
import br.com.alura.spring.data.models.Funcionario;
import br.com.alura.spring.data.models.Unidade;
import br.com.alura.spring.data.repository.CargoRepository;
import br.com.alura.spring.data.repository.FuncionarioRepository;
import br.com.alura.spring.data.repository.UnidadeRepository;

@Service
public class FuncionarioService {

	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private final FuncionarioRepository funcionarioRepository;
	private final UnidadeRepository unidadeRepository;
	private final CargoRepository cargoRepository;
	private final RelatorioService relatorioService;

	public FuncionarioService(FuncionarioRepository funcionarioRepository, UnidadeRepository unidadeRepository,
			CargoRepository cargoRepository, RelatorioService relatorioService) {

		this.funcionarioRepository = funcionarioRepository;
		this.unidadeRepository = unidadeRepository;
		this.cargoRepository = cargoRepository;
		this.relatorioService = relatorioService;
	}

	public void alterar(Scanner sc) {
		sc.nextLine();
		System.out.println("\nSelecione o cargo a ser alterado: ");

		int id = exibirFuncionarios(sc);
		sc.nextLine();

		Funcionario funcionario = funcionarioRepository.findById(id).orElse(new Funcionario());

		funcionario = preencherFuncionario(sc, funcionario);

		funcionarioRepository.save(funcionario);

		System.out.println("\n" + funcionario.getNome() + " alterado com sucesso!!");
		System.out.println();
	}

	public void inserir(Scanner sc) {
		sc.nextLine();
		Funcionario novoFuncionario = new Funcionario();
		novoFuncionario.setCargo(new Cargo());
		novoFuncionario.setUnidade(new Unidade());

		Funcionario funcionario = preencherFuncionario(sc, novoFuncionario);
		funcionario.setId(null);

		cargoRepository.save(funcionario.getCargo());
		unidadeRepository.save(funcionario.getUnidade());
		funcionarioRepository.save(funcionario);

		if (funcionario != null && funcionario.getId() != null)
			System.out.println("O colaborador " + funcionario.getNome() + " salvo com sucesso!!");

		relatorioService.listarFuncionarios();
		System.out.println();
	}

	public void deletar(Scanner sc) {
		System.out.println("\nSelecione o funcionário a ser deletado: ");

		int id = exibirFuncionarios(sc);
		Optional<Funcionario> optional = funcionarioRepository.findById(id);

		Funcionario funcionario = optional.orElse(new Funcionario());

		unidadeRepository.deleteById(funcionario.getUnidade().getId());
		cargoRepository.deleteById(funcionario.getCargo().getId());
		funcionarioRepository.deleteById(funcionario.getId());

		System.out.println("removido com sucesso!!");
		System.out.println();
	}

	private int exibirFuncionarios(Scanner sc) {
		relatorioService.listarFuncionarios();
		System.out.println("\n===============================");
		System.out.print("Selecione: ");
		return sc.nextInt();
	}

	private Funcionario preencherFuncionario(Scanner sc, Funcionario funcionario) {

		Integer value = 0;
		do {
			System.out.print(funcionario);
			System.out.println("\n0 - finalizar alterações");
			System.out.print("\nDigite o valor correspondente a alteração desejada: ");
			value = sc.nextInt();
			if (value != null || value instanceof Integer)
				switch (value) {
				case 1:
					sc.nextLine();
					System.out.print("\nNome: ");
					funcionario.setNome(sc.nextLine());
					break;
				case 2:
					sc.nextLine();
					System.out.print("\nCPF: ");
					funcionario.setCpf(sc.nextLine());
					break;
				case 3:
					sc.nextLine();
					System.out.print("\nSalário: ");
					funcionario.setSalario(sc.nextDouble());
					break;
				case 4:
					sc.nextLine();
					System.out.print("\nData de contratação: ");
					funcionario.setDataContratacao(LocalDate.parse(sc.nextLine(), formatter));
					break;
				case 5:
					sc.nextLine();
					System.out.print("\nDescrição do cargo: ");
					funcionario.getCargo().setDescricao(sc.nextLine());
					break;
				case 6:
					sc.nextLine();
					System.out.print("\nNome da unidade: ");
					funcionario.getUnidade().setNome(sc.nextLine());
					System.out.print("\nEndereco da unidade: ");
					funcionario.getUnidade().setEndereco(sc.nextLine());
					break;
				case 0:
					break;
				}

		} while (value != 0);

		return funcionario;
	}
}
