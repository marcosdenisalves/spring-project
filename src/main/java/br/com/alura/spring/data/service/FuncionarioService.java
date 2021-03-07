package br.com.alura.spring.data.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
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

	private final FuncionarioRepository funcionarioRepository;
	private final UnidadeRepository unidadeRepository;
	private final CargoRepository cargoRepository;

	public FuncionarioService(FuncionarioRepository funcionarioRepository, UnidadeRepository unidadeRepository,
			CargoRepository cargoRepository) {

		this.funcionarioRepository = funcionarioRepository;
		this.unidadeRepository = unidadeRepository;
		this.cargoRepository = cargoRepository;
	}

	public void listar() {
		List<Funcionario> funcionarios = (List<Funcionario>) listarFuncionarios();
		if (!funcionarios.isEmpty()) {
			System.out.println("\nDados carregados com sucesso!!");
			System.out.println();
		} else {
			System.out.println("\nNenhum funcionário cadastrado.");
			System.out.println();
		}
	}

	private Iterable<Funcionario> listarFuncionarios() {
		Iterable<Funcionario> funcionarios = funcionarioRepository.findAll();
		System.out.println();
		funcionarios.forEach(c -> System.out.println(c.getId() + " - " + c.getNome()));
		return funcionarios;
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

		cargoRepository.save(funcionario.getCargo());
		unidadeRepository.save(funcionario.getUnidade());
		funcionarioRepository.save(funcionario);

		if (funcionario != null && funcionario.getId() != null)
			System.out.println("O colaborador " + funcionario.getNome() + " salvo com sucesso!!");

		listarFuncionarios();
		System.out.println();
	}

	public void deletar(Scanner sc) {
		System.out.println("\nSelecione o funcionário a ser deletado: ");

		int id = exibirFuncionarios(sc);
		Optional<Funcionario> optional = funcionarioRepository.findById(id);

		Funcionario funcionario = optional.orElse(new Funcionario());

		funcionarioRepository.deleteById(funcionario.getId());

		System.out.println("removido com sucesso!!");
		System.out.println();
	}

	private int exibirFuncionarios(Scanner sc) {
		listarFuncionarios();
		System.out.println("\n===============================");
		System.out.print("Selecione: ");
		return sc.nextInt();
	}

	private Funcionario preencherFuncionario(Scanner sc, Funcionario funcionario) {

		try {
			Integer value = null;
			do {
				System.out.print(funcionario);
				System.out.println("\n0 - finalizar alterações");
				System.out.print("\nDigite o valor correspondente a alteração desejada: ");

				value = sc.nextInt();

				if (value != null)
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
						SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
						System.out.print("\nData de contratação: ");
						funcionario.setDataContratacao(sdf.parse(sc.nextLine()));
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
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}
}
