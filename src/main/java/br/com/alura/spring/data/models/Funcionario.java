package br.com.alura.spring.data.models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "funcionario")
public class Funcionario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private String cpf;
	private Double salario;
	private LocalDate dataContratacao;

	@ManyToOne
	private Cargo cargo;
	
	@ManyToOne
	private Unidade unidade;

	public Funcionario() {
	}
	
	public Funcionario(Integer id, String nome, String cpf, Double salario, LocalDate dataContracao, Cargo cargo,
			Unidade unidade) {
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.salario = salario;
		this.dataContratacao = dataContracao;
		this.cargo = cargo;
		this.unidade = unidade;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Double getSalario() {
		return salario;
	}

	public void setSalario(Double salario) {
		this.salario = salario;
	}

	public LocalDate getDataContratacao() {
		return dataContratacao;
	}

	public void setDataContratacao(LocalDate dataContratacao) {
		this.dataContratacao = dataContratacao;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public Unidade getUnidade() {
		return unidade;
	}

	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}

	@Override
	public String toString() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		StringBuilder builder = new StringBuilder();
		builder.append("Dados do Funcionário")
			.append("\n1 - Nome: ").append(nome == null ? "______" : nome)
			.append("\n2 - Cpf: ").append(cpf == null ? "______" : cpf)
			.append("\n3 - Salário: ").append(salario == null ? "______" : String.format("%.2f", salario))
			.append("\n4 - Data de contracao: ").append(dataContratacao == null ? "______" : formatter.format(dataContratacao))
			.append("\n5 - Cargo: ").append(cargo.getDescricao() == null ? "______" : cargo.getDescricao())
			.append("\n6 - Unidade: ").append(unidade.getNome() == null ? "______" : unidade.getNome());
		
		return builder.toString();
	}
}
