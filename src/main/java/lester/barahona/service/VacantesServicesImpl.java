package lester.barahona.service;

import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lester.barahona.model.Vacante;

@Service
public class VacantesServicesImpl implements IVacantesService{
	
	private List<Vacante> list =null;
	
	public VacantesServicesImpl() {
		this.list = new LinkedList<Vacante>();
		SimpleDateFormat sdf =new SimpleDateFormat("dd-MM-yyy");
		try {
			this.list.add(new Vacante(1,"Vacante #1","Descripción de vacante #1",sdf.parse("08-02-2020"),90000.0,1,"logo1.png"));
			this.list.add(new Vacante(2,"Vacante #2","Descripción de vacante #2",sdf.parse("08-03-2020"),91000.0,0,"logo2.png"));
			this.list.add(new Vacante(3,"Vacante #3","Descripción de vacante #3",sdf.parse("08-04-2020"),92000.0,1,"logo3.png"));
			this.list.add(new Vacante(4,"Vacante #4","Descripción de vacante #4",sdf.parse("08-05-2020"),93000.0,0,"logo4.png"));
			this.list.add(new Vacante(5,"Vacante #5","Descripción de vacante #5",sdf.parse("08-06-2020"),94000.0,1,"logo5.png"));
			this.list.add(new Vacante(6,"Vacante #6","Descripción de vacante #6",sdf.parse("08-07-2020"),95000.0,0,"logo6.png"));
			this.list.add(new Vacante(7,"Vacante #7","Descripción de vacante #7",sdf.parse("08-08-2020"),96000.0,1,"logo7.png"));
			
		} catch (Exception e) {
			System.err.println("Error: "+e.getMessage());
		}
	}
	
	@Override
	public List<Vacante> searchAll() {
		return this.list;
	}

	@Override
	public Vacante searchOne(int id) {
		Vacante vacante=this.list.stream().filter(v->v.getId()==id).findFirst().orElse(null);;
		return vacante;
	}

	@Override
	public void saveNew(Vacante vacante) {
		this.list.add(vacante);
	}

	@Override
	public List<Vacante> buscarDestacadas() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void eliminar(int id) {
	
		
	}

	@Override
	public List<Vacante> buscarByExample(Example<Vacante> example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Vacante> buscarTodas(Pageable page) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
