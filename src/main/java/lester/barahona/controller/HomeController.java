package lester.barahona.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.Date;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lester.barahona.model.Perfil;
import lester.barahona.model.Usuario;
import lester.barahona.model.Vacante;
import lester.barahona.service.ICategoriasService;
import lester.barahona.service.IUsuariosService;
import lester.barahona.service.IVacantesService;

@Controller
public class HomeController {

	@Autowired
	private IVacantesService serviceVacantes;
	@Autowired
	private IUsuariosService serviceUsuarios;

	@Autowired
	@Qualifier("CategoriasServicesJpa")
	private ICategoriasService serviceCategorias;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping("/")
	public String mostrarHome(Model model) {
		return "home";
	}

	@GetMapping("/index")
	public String mostarIndex(Authentication auth,HttpSession session) {

		if(session.getAttribute("usuario")==null) {
			Usuario user=serviceUsuarios.buscarPorUserName(auth.getName());
			user.setPassword(null);
			session.setAttribute("usuario",user);
			System.out.println("Imprimindo al mae: "+user);
		}
		return "redirect:/";
	}

	@GetMapping("/login")
	public String mostarLogin() {
		return "formLogin";
	}
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest request){
	SecurityContextLogoutHandler logoutHandler =
	new SecurityContextLogoutHandler();
	logoutHandler.logout(request, null, null);
	return "redirect:/";
	}

	
	
	@GetMapping("/signup")
	public String registrarse(Usuario usuario) {
		return "formRegistro";
	}

	@PostMapping("/signup")
	public String guardarRegistro(Usuario usuario, RedirectAttributes attributes) {
		
		//encryptacion de la contrasena
		String txtPlano=usuario.getPassword();
		String txtCryp=passwordEncoder.encode(txtPlano);
		usuario.setPassword(txtCryp);
		
		usuario.setEstatus(1); // Activado por defecto
		usuario.setFechaIngreso(new Date()); // Fecha de Registro, la fecha actual del servidor

		// Creamos el Perfil que le asignaremos al usuario nuevo
		Perfil perfil = new Perfil();
		perfil.setId(3); // Perfil USUARIO
		usuario.addPerfil(perfil);

		/**
		 * Guardamos el usuario en la base de datos. El Perfil se guarda automaticamente
		 */
		serviceUsuarios.guardar(usuario);

		attributes.addFlashAttribute("msg", "El registro fue guardado correctamente!");

		return "redirect:/usuarios/index";
	}

	@GetMapping("/search")
	public String buscar(@ModelAttribute("search") Vacante vacante,Model model) {
		System.out.println(vacante);
		ExampleMatcher matcher=ExampleMatcher.matching().withMatcher("descripcion",ExampleMatcher.GenericPropertyMatchers.contains());
		Example<Vacante> example=Example.of(vacante,matcher);
		List<Vacante> list=serviceVacantes.buscarByExample(example);
		model.addAttribute("vacantes",list);
		return "home";
	}

	//para que los valores vacios se pasen a null
	@InitBinder
	public void initBinder(WebDataBinder binder) {

		binder.registerCustomEditor(String.class,new StringTrimmerEditor(true));
	}

	
	@ModelAttribute
	public void setGenericos(Model model) {
		Vacante vacanteSearch=new Vacante();
		vacanteSearch.reset();
		model.addAttribute("vacantes", serviceVacantes.buscarDestacadas());
		model.addAttribute("categorias", serviceCategorias.findAll());
		model.addAttribute("search",vacanteSearch);
	}
	
	//para obtener contra encriptada para guardar en db
	@GetMapping("/bcrypt/{texto}")
	@ResponseBody
	public String encripter(@PathVariable("texto") String texto) {
		return texto+" Encryptado es : "+passwordEncoder.encode(texto);
	}



}
