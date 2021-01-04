package lester.barahona.service;


import java.util.LinkedList;
import java.util.List;
import org.springframework.stereotype.Service;
import lester.barahona.model.Categoria;



@Service
public class CategoriasServiceImpl implements ICategoriasService{

	private List<Categoria> lista = null;
	
	public CategoriasServiceImpl() {
		lista = new LinkedList<Categoria>();
		
		lista.add(new Categoria(1,"Contabilidad","Descripcion de la categoria Contabilidad"));
		lista.add(new Categoria(2,"Ventas","Trabajos relacionados con Ventas"));
		lista.add(new Categoria(3,"Comunicaciones","Trabajos relacionados con Comunicaciones"));
		lista.add(new Categoria(4,"Arquitectura","Trabajos de Arquitectura"));
		lista.add(new Categoria(5,"Educacion","Maestros, tutores, etc"));

	}
	
	public void save(Categoria categoria) {		
		lista.add(categoria);
	}

	public List<Categoria> findAll() {
		return lista;
	}

	public Categoria findById(int id) {			
		Categoria categoria=this.lista.stream().filter((cat)->cat.getId()==id).findFirst().orElse(null);
		return categoria;
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		
	}



}