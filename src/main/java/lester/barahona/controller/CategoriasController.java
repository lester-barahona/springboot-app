package lester.barahona.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lester.barahona.model.Categoria;

import lester.barahona.service.ICategoriasService;


@Controller
@RequestMapping("/categories")
public class CategoriasController {
	
	@Autowired
	@Qualifier("CategoriasServicesJpa")
   	private ICategoriasService _sCategories;
	
	@RequestMapping(value="/index", method=RequestMethod.GET)
	public String mostrarIndex(Model model) {
		List<Categoria> lista = _sCategories.findAll();
    	model.addAttribute("categorias", lista);
		return "categories/listCategories";		
	}
	
	@RequestMapping(value="/create", method=RequestMethod.GET)
	public String create(Categoria categoria) {
		return "categories/formCategorie";
	}
	
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public String save(Categoria categoria, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()){		
			System.out.println("Existieron errores validar en fields!");
			return "categories/formCategorie";
		}	
		_sCategories.save(categoria);
		attributes.addFlashAttribute("msg", "Los datos de la categoría fueron guardados!");		
		return "redirect:/categories/index";
	}
	
	@GetMapping("/edit/{id}")
	public ModelAndView edit(@PathVariable("id") int id) {
		ModelAndView mav=new ModelAndView("categories/formCategorie");
		mav.addObject("categoria",_sCategories.findById(id));
		return mav;
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") int id, RedirectAttributes attr) {
		_sCategories.deleteById(id);
		attr.addFlashAttribute("msg", "Categoría eliminada!");		
		return "redirect:/categories/index";
	}
	
}

