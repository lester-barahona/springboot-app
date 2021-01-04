package lester.barahona.service.db;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lester.barahona.model.Categoria;
import lester.barahona.repository.CategoriasRepository;
import lester.barahona.service.ICategoriasService;

@Service("CategoriasServicesJpa")
//@Primary
public class CategoriasServicesJpa implements ICategoriasService {

	@Autowired
	private CategoriasRepository repoCategorias;
	
	public CategoriasServicesJpa() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void save(Categoria categoria) {
		repoCategorias.save(categoria);
	}

	@Override
	public List<Categoria> findAll() {
		
		return repoCategorias.findAll();
	}

	@Override
	public Categoria findById(int id) {
		return repoCategorias.findById(id).orElse(null);
	}

	@Override
	public void deleteById(int id) {
		repoCategorias.deleteById(id);
		
	}

}
