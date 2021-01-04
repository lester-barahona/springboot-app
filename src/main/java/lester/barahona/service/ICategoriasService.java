package lester.barahona.service;

import java.util.List;

import lester.barahona.model.Categoria;


public interface ICategoriasService {
	
	void save(Categoria categoria);
	List<Categoria>findAll();
	Categoria findById(int id);
	void deleteById(int id);

}
