package br.com.alura.spring.data.specifications;

import java.time.LocalDate;

import org.springframework.data.jpa.domain.Specification;

import br.com.alura.spring.data.models.Funcionario;

public class SpecificationFuncionario {

	public static Specification<Funcionario> likeNome(String nome) {
		return (root, criteriaQuery, criteriaBuilder) ->
			criteriaBuilder.like(root.get("nome"), "%" + nome + "%");
	}
	
	public static Specification<Funcionario> cpfEqual(String cpf) {
		return (root, criteriaQuery, criteriaBuilder) ->
			criteriaBuilder.equal(root.get("cpf"), cpf);
	}
	
	public static Specification<Funcionario> salarioLessThan(Double salario) {
		return (root, criteriaQuery, criteriaBuilder) ->
			criteriaBuilder.lessThan(root.get("salario"), salario);
	}
	
	public static Specification<Funcionario> dataContratacao(LocalDate data) {
		return (root, criteriaQuery, criteriaBuilder) ->
			criteriaBuilder.greaterThan(root.get("dataContratacao"), data);
	}
}
