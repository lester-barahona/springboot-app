package lester.barahona.service.db;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lester.barahona.model.Vacante;
import lester.barahona.repository.VacantesRepository;
import lester.barahona.service.IVacantesService;

@Service
@Primary
public class VacantesSerivesJpa implements IVacantesService {
	
	@Autowired
	private VacantesRepository repoVacantes;

	public VacantesSerivesJpa() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Vacante> searchAll() {
		
		return repoVacantes.findAll();
	}

	@Override
	public Vacante searchOne(int id) {
		return repoVacantes.findById(id).orElse(null);
	}

	@Override
	public void saveNew(Vacante vacante) {
		repoVacantes.save(vacante);
	}

	@Override
	public List<Vacante> buscarDestacadas() {
		
		return repoVacantes.findByDestacadoAndEstatusOrderByIdDesc(1,"Aprobada");
	}

	@Override
	public void eliminar(int id) {
		repoVacantes.deleteById(id);
	}

	@Override
	public List<Vacante> buscarByExample(Example<Vacante> example) {
		
		return repoVacantes.findAll(example);
	}

	@Override
	public Page<Vacante> buscarTodas(Pageable page) {
		return repoVacantes.findAll(page);
	}

}
