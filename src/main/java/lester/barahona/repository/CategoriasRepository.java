package lester.barahona.repository;

import org.springframework.data.jpa.repository.JpaRepository;
		

import lester.barahona.model.Categoria;

public interface CategoriasRepository extends JpaRepository<Categoria,Integer>{
	
	//CrudRepository
	
}
