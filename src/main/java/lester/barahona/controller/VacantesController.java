package lester.barahona.controller;

import java.util.List;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lester.barahona.model.Vacante;
import lester.barahona.service.ICategoriasService;
import lester.barahona.service.IVacantesService;
import lester.barahona.util.Utileria;

@Controller
@RequestMapping("/vacantes")
public class VacantesController {
	
	//---------------------------------------------------DATOS DEL APPLICATION.PROPERTIES
	@Value("${empleosapp.ruta.imgs}")
	private String ruta;
	
	//----------------------------------------------SERVICES
	@Autowired
	private IVacantesService serviceVacante; 
	
	@Autowired
	@Qualifier("CategoriasServicesJpa")
	private ICategoriasService serviceCategorias;
	
	//----------------------------------------------BINDING CONF
	@InitBinder
	public void initBinder(WebDataBinder wdb) { 
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		wdb.registerCustomEditor(Date.class,new CustomDateEditor(sdf, false));
	}
	
	
	@GetMapping("/index")
	public String mostrarIndex(Model model) {
		List<Vacante> lista = serviceVacante.searchAll();
		model.addAttribute("vacantes", lista);
		return "vacantes/listVacantes";
	}
	
	@GetMapping("/create")
	public String create(Vacante vacante,Model model) {
		return "vacantes/formVacante";
	}
	
	@GetMapping("/edit/{id}")
	public ModelAndView editar(@PathVariable("id") int id) {
		ModelAndView mav=new ModelAndView("vacantes/formVacante");
		mav.addObject("vacante",serviceVacante.searchOne(id));
		return mav;
	}
	
	// la lista de categorias que se comparten
	@ModelAttribute
	public void setGenericos(Model model) {
		model.addAttribute("categorias", serviceCategorias.findAll());
	}
	
	//------------------------------------------------------------------------------
	@PostMapping("/save") 
	public ModelAndView saveNew(Vacante vacante,BindingResult result,
			RedirectAttributes reAtr, @RequestParam("archivoImagen") MultipartFile multiPart) {
		ModelAndView mav = new ModelAndView("redirect:/vacantes/index");
		
		//errors 
		if(result.hasErrors()){
			result.getAllErrors().forEach((error)->{
				System.out.println("Ocurrio un error: "+error.getDefaultMessage());});
			mav.setViewName("vacantes/formVacante");
			return mav;
		}
		//upload img
		if (!multiPart.isEmpty()) {
			String imgName = Utileria.guardarArchivo(multiPart, ruta);
			if (imgName != null){ 
			vacante.setImg(imgName);
			}
		}
		serviceVacante.saveNew(vacante);
		
		System.out.println("save : "+vacante);
		
		reAtr.addFlashAttribute("msg", "Vacante Guardada!");
		return mav;
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") int id,RedirectAttributes reAt) {
		serviceVacante.eliminar(id);
		reAt.addFlashAttribute("msg","Vacante eliminada!");
		return "redirect:/vacantes/index";
	}
	
	@GetMapping("/view/{id}")
	public String showDetalle(@PathVariable("id") int id,Model model) {
		Vacante vacante=serviceVacante.searchOne(id);
		model.addAttribute("vacante",vacante);
		return "vacantes/detalle";
	}
	
	//-----------------------------------------------------------paginacion
	
	@GetMapping(value = "/indexPaginate")
	public String mostrarIndexPaginado(Model model, Pageable page) {
	Page<Vacante> lista = serviceVacante.buscarTodas(page);
	model.addAttribute("vacantes", lista);
	return "vacantes/listVacantes";
	}

}
