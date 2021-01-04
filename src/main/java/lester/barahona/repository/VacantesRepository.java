package lester.barahona.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import lester.barahona.model.Vacante;

public interface VacantesRepository extends JpaRepository<Vacante,Integer> {
		
	List<Vacante> findByEstatus(String estatus);
	
	List<Vacante> findByDestacadoAndEstatusOrderByIdDesc(int destacado,String estatus);
	
	List<Vacante> findBySalarioBetween(double init, double end);
	
	List<Vacante> findByEstatusIn(String [] estatus);
	
}
