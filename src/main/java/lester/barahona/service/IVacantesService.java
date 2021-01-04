package lester.barahona.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;

import lester.barahona.model.Vacante;

public interface IVacantesService {
	
	List<Vacante> searchAll();
	Vacante searchOne(int id);
	void saveNew(Vacante vacante);
	List<Vacante> buscarDestacadas();
	void eliminar(int id);
	List<Vacante> buscarByExample(Example<Vacante> example);
	Page<Vacante>buscarTodas(Pageable page);
}
