package br.com.alura.spring.data.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.alura.spring.data.models.Funcionario;
import br.com.alura.spring.data.projecoes.FuncionarioProjecao;

@Repository
public interface FuncionarioRepository extends PagingAndSortingRepository<Funcionario, Integer>, 
	JpaSpecificationExecutor<Funcionario>{

	List<Funcionario> findByNomeLikeOrderByNomeAsc(String nome);
	
	@Query("select f from Funcionario f where f.nome = :nome and f.salario >= :salario and f.dataContratacao = :data")
	List<Funcionario> findNomeSalarioMaiorDataContratacao(String nome,
			Double salario, LocalDate data);
	
	@Query(value = "select * from funcionario f where f.data_contratacao >= :data", nativeQuery = true)
	List<Funcionario> findDataContratacaoMaior(LocalDate data);
	
	@Query(value = "select f.id, f.nome, f.salario from funcionario f", nativeQuery = true)
	List<FuncionarioProjecao> findFuncionarioSalario();
}