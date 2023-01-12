package cl.generation.web.api;

import java.util.List;

import javax.print.attribute.standard.Media;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cl.generation.web.models.Auto;
import cl.generation.web.models.Usuario;
import cl.generation.web.services.AutoServiceImpl;
import cl.generation.web.services.UsuarioServiceImpl;

@RestController
@RequestMapping("/api2")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
public class AutoApiRestController {

	@Autowired
	private AutoServiceImpl autoServiceImpl;

	@Autowired
	private UsuarioServiceImpl usuarioServiceImpl;

	@RequestMapping("/guardar/auto")
	public Auto guardarUsuario(@RequestBody Auto auto,
			@RequestParam(value = "usuarioId", required = false) Long usuarioId) {

		Usuario usuario = usuarioServiceImpl.obtenerUsuario(usuarioId);
		auto.setUsuario(usuario);

		return autoServiceImpl.guardarAuto(auto);
	}

	@RequestMapping("/obtener/auto")
	public Auto obtenerUsuario(@RequestParam(value = "id", required = false) Long id) {

		return autoServiceImpl.obtenerAuto(id);
	}

	//localhost:9080/api2/autos/getall
	@RequestMapping(value = "/autos/getall", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Auto> autosGetAll() {

		return autoServiceImpl.listarAutos();
	}
	
	@RequestMapping(value = "/eliminar/auto", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public String eliminarAuto(@RequestParam(value="id",required = false) Long id) {
		System.out.println("pasando back");
		autoServiceImpl.eliminarAuto(id);
		
		return "auto eliminado";
	}

	@PutMapping("/editar/auto")
	public Auto editarAuto(@RequestBody Auto auto,
						   @RequestParam(value="id",required = true) Long id) {
		return autoServiceImpl.editarAuto(id, auto);
	}

	
}
