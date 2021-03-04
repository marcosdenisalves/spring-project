package br.com.alura.spring.data.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.alura.spring.data.models.Cargo;

@Repository
public interface CargoRepository extends CrudRepository<Cargo, Integer>	{

}
