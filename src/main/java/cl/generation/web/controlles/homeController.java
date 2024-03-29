package cl.generation.web.controlles;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cl.generation.web.models.Auto;
import cl.generation.web.services.AutoServiceImpl;


@Controller
@RequestMapping("home")
public class homeController {
	
	@Autowired
	AutoServiceImpl autoServiceImpl;
	
	@GetMapping("")
	public String home(Model model, HttpSession session) {
		
		if(session.getAttribute("usuarioId")!=null) {
			// capturando datos de sesipn
			String email =(String)session.getAttribute("usuarioEmail");
			Long usuarioId = (Long) session.getAttribute("usuarioId");
			String nombre = (String)session.getAttribute("usuarioNombre");
			//obtener y almacenar en variable
			List<Auto> listaAutos = autoServiceImpl.listarAutos();
			//pasar lista de autos
			model.addAttribute("autos", listaAutos);
			//lista para el selector
			List<Auto> listaSelectAutos = autoServiceImpl.listarAutos();
			model.addAttribute("listaSelectAutos", listaSelectAutos);
			
			model.addAttribute("usuarioNombre", nombre);
			return "home.jsp";
		}else {
			return "redirect:/registro/login";
		}
		
		
	}
	//solo respondera a formularios
	@PostMapping("")
	public String autoFiltrado(@RequestParam("autoSeleccionado")Long id, Model model) {
		List<Auto> listaAutos= new ArrayList<Auto>();//lista vacia
		Auto auto = autoServiceImpl.obtenerAuto(id);
		listaAutos.add(auto);//agrego el auto a la lista
		model.addAttribute("autos", listaAutos);//pasar la lista de autos al jsp
		//lista para el selector
		List<Auto> listaSelectAutos = autoServiceImpl.listarAutos();
		model.addAttribute("listaSelectAutos", listaSelectAutos);
		return "home.jsp";
	}
	
	@PostMapping("/nav")
	public String filtrarNav(@RequestParam("marca")String marca, Model model) {
		List<Auto> listaAutos= new ArrayList<Auto>();//lista vacia
		Auto auto = autoServiceImpl.obtenerAutoNombre(marca);
		listaAutos.add(auto);//agrego el auto a la lista
		model.addAttribute("autos", listaAutos);//pasar la lista de autos al jsp
		//lista para el selector
		List<Auto> listaSelectAutos = autoServiceImpl.listarAutos();
		model.addAttribute("listaSelectAutos", listaSelectAutos);
		return "home.jsp";
	}
}

